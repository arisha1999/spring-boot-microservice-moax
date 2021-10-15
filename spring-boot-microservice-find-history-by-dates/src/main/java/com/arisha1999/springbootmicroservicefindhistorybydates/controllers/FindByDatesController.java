package com.arisha1999.springbootmicroservicefindhistorybydates.controllers;

import com.arisha1999.springbootmicroservicefindhistorybydates.FindByDatesProxy;
import com.arisha1999.springbootmicroservicefindhistorybydates.models.History;
import com.arisha1999.springbootmicroservicefindhistorybydates.models.InstrumentHistory;
import com.arisha1999.springbootmicroservicefindhistorybydates.repositories.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class FindByDatesController {

    @Autowired
    private InstrumentRepository instrumentRepository;
    @Autowired
    private FindByDatesProxy proxy;

    public FindByDatesController(InstrumentRepository instrumentRepository){
        this.instrumentRepository = instrumentRepository;
    }

    @GetMapping("/instruments/{ticker}/from/{from}/to/{to}")
    public InstrumentHistory findInstrumentByDates(@PathVariable String ticker, @PathVariable String from, @PathVariable String to) {
        List<History> historyList = new ArrayList<>();
        Optional<InstrumentHistory> history = instrumentRepository.findById(ticker);
        InstrumentHistory new_history = history.orElse(new InstrumentHistory());
        LocalDate from_l = LocalDate.parse(from);
        LocalDate to_l = LocalDate.parse(to);
        int days = (int) from_l.until(to_l, ChronoUnit.DAYS);
        List<LocalDate> list_dates = Stream.iterate(from_l, d -> d.plusDays(1))
                .limit(days+1)
                .collect(Collectors.toList());
        list_dates.parallelStream().forEach(date -> {
            if (new_history.getTicker() == null || new_history.findIfPriceIsInDB(ticker, date) == null)
            // if there is no info about current date => use service find-instrument-last-place to find that info
            {
                History response = proxy.retrieveInstrument(ticker, date.toString());
                History new_item = new History(response.getDate(), response.getPrice(), response.getPort());
                historyList.add(new_item);
                if (new_history.getTicker() != null) {
                    new_history.getHistoryList().add(new_item);
                }
            } else {
                historyList.add(new_history.findIfPriceIsInDB(ticker, date));
            }
        });
        Collections.sort(historyList);
        new_history.setTicker(ticker);
        instrumentRepository.save(new_history);
        return new InstrumentHistory(ticker,historyList);
    }
}
