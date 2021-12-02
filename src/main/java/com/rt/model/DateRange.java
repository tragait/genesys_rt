package com.rt.model;

import java.sql.Date;


public class DateRange{
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    private Date from;
    private Date to;
}