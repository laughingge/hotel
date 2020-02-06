package com.example.hotel.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LineChart {
    public Integer value;
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date name;

    public LineChart(Integer value, Date name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getName() {
        return name;
    }

    public void setName(Date name) {
        this.name = name;
    }
}
