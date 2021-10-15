package com.arisha1999.springbootmicroservicefindhistorybydates.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class InstrumentHistory {
    private @Id
    String ticker;
    @OneToMany
    private static List<History> historyList;

    public InstrumentHistory(){}

    public InstrumentHistory(String ticker, List<History> historyList){
        this.ticker = ticker;
        this.historyList=historyList;
    }

    public String getTicker() {
        return ticker;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public History findIfPriceIsInDB(String ticker, LocalDate date){
        for(int i = 0; i < historyList.size(); i++){
            LocalDate find = historyList.get(i).getDate();
            if(historyList.get(i).getDate().equals(date)){
                return historyList.get(i);
            }
        }
        return null;
    }
}
