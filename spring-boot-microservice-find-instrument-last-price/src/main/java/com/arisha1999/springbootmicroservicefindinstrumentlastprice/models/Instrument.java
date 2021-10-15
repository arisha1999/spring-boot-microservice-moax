package com.arisha1999.springbootmicroservicefindinstrumentlastprice.models;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Configuration
@ComponentScan(basePackages = {"com.arisha1999.springbootmicroservicefindinstrumentlastprice.models"})
public class Instrument {
    private @Id String ticker;
    private Double price;
    private LocalDate date;
    private int port;

    public Instrument(){}

    public Instrument(String ticker, Double price, LocalDate date){
        this.ticker = ticker;
        this.price=price;
        this.date=date;
    }

    public Double getPrice() {
        return price;
    }

    public String getTicker() {
        return ticker;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getPort() {
        return port;
    }
    public void setPrice(Double price) {
        this.price=price;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
