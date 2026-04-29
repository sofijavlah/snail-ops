package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class CurrencyResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency_response_seq")
    @SequenceGenerator(name = "currency_response_seq", sequenceName = "currency_response_seq", allocationSize = 1)
    Long id;

    String date;

    @Column(name = "from_currency")
    String from;

    double rate;
    String source;

    @Column(name = "to_currency")
    String to;

    double value;
    double convertedValue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wanted_pirate_id")
    @JsonIgnore
    WantedPirate wantedPirate;


    // getters
    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public double getRate() {
        return rate;
    }

    public String getSource() {
        return source;
    }

    public String getTo() {
        return to;
    }

    public double getValue() {
        return value;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    public WantedPirate getWantedPirate() {
        return wantedPirate;
    }


    // setters
    public void setDate(String date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setConvertedValue(double convertedValue) {
        this.convertedValue = convertedValue;
    }

    public void setWantedPirate(WantedPirate wantedPirate) {
        this.wantedPirate = wantedPirate;
    }


    // overrides
    @Override
    public String toString() {
        return "CurrencyResponse [date=" + date + ", from=" + from + ", rate=" + rate + ", source=" + source + ", to="
                + to + ", value=" + value + ", convertedValue=" + convertedValue + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return Double.compare(rate, that.rate) == 0 && Double.compare(value, that.value) == 0 && Double.compare(convertedValue, that.convertedValue) == 0 && Objects.equals(date, that.date) && Objects.equals(from, that.from) && Objects.equals(source, that.source) && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, from, rate, source, to, value, convertedValue);
    }
}
