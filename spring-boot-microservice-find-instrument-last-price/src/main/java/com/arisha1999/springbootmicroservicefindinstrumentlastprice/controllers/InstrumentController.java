package com.arisha1999.springbootmicroservicefindinstrumentlastprice.controllers;

import com.arisha1999.springbootmicroservicefindinstrumentlastprice.models.Instrument;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.arisha1999.springbootmicroservicefindinstrumentlastprice.clients.Client.findInstrument;
import static com.arisha1999.springbootmicroservicefindinstrumentlastprice.clients.Client.findLastDate;

@RestController
public class InstrumentController {

    @Autowired
    private Environment environment;

    @GetMapping("/instruments/{ticker}/")
    public Instrument findOne(@PathVariable String ticker) throws IOException, JSONException {
        Instrument find_instr = findInstrument(ticker,findLastDate(ticker));

        find_instr.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));
        return find_instr;
    }
    @GetMapping("/instruments/{ticker}/{date}")
    public Instrument findOneByDate(@PathVariable String ticker, @PathVariable String date) throws IOException, JSONException {
        Instrument find_instr = findInstrument(ticker,date);

        find_instr.setPort(
                Integer.parseInt(environment.getProperty("local.server.port")));
        return find_instr;
    }
}
