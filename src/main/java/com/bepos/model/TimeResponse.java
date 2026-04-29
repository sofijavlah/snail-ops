package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class TimeResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_response_seq")
    @SequenceGenerator(name = "time_response_seq", sequenceName = "time_response_seq", allocationSize = 1)
    private Long id;

    private String ipAddress;
    private String date;
    private String dateTime;
    private Integer day;
    private String dayOfWeek;
    private Boolean dstActive;
    private Integer hour;
    private Integer milliSeconds;
    private Integer minute;
    private Integer month;
    private Integer seconds;
    private String time;
    private String timeZone;
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wanted_pirate_id")
    @JsonIgnore
    private WantedPirate wantedPirate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Boolean getDstActive() {
        return dstActive;
    }

    public void setDstActive(Boolean dstActive) {
        this.dstActive = dstActive;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMilliSeconds() {
        return milliSeconds;
    }

    public void setMilliSeconds(Integer milliSeconds) {
        this.milliSeconds = milliSeconds;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public WantedPirate getWantedPirate() {
        return wantedPirate;
    }

    public void setWantedPirate(WantedPirate wantedPirate) {
        if (this.wantedPirate == wantedPirate) {
            return;
        }

        WantedPirate previousWantedPirate = this.wantedPirate;
        this.wantedPirate = wantedPirate;

        if (previousWantedPirate != null && previousWantedPirate.getTimeResponses().contains(this)) {
            previousWantedPirate.getTimeResponses().remove(this);
        }

        if (wantedPirate != null && !wantedPirate.getTimeResponses().contains(this)) {
            wantedPirate.getTimeResponses().add(this);
        }
    }
}
