package com.rt.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

public class SensorQuery {
    private BigDecimal id;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Set<QueryParam> getQueryParamSet() {
        return queryParamSet;
    }

    public void setQueryParamSet(Set<QueryParam> queryParamSet) {
        this.queryParamSet = queryParamSet;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<Date> dates) {
        this.dates = dates;
    }

    private Set<QueryParam> queryParamSet;
    private DateRange dateRange;
    private Set<Date> dates;
}



