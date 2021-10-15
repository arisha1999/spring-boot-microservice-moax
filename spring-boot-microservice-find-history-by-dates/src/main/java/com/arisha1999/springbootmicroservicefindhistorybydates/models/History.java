package com.arisha1999.springbootmicroservicefindhistorybydates.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class History implements Comparable<History>{
    private @Id
    LocalDate date;
    private Double price;
    private int port;

    public History(){}

    public History(LocalDate date, Double price, int port){
        this.date=date;
        this.price=price;
        this.port=port;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }

    public int getPort() {
        return port;
    }

    @Override
    public int compareTo(History u) {
        if (getDate() == null || u.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(u.getDate());
    }
}
