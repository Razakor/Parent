package com.razakor.task.parser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Hour {
    private String value;
    private List<String> minutes = new ArrayList<>();

    public Hour(String value) {
        this.value = value;
    }

    public List<LocalTime> getTime() {
        List<LocalTime> times = new ArrayList<>();
        minutes.forEach(minute ->
                times.add(LocalTime.of(Integer.parseInt(value), Integer.parseInt(minute))));
        return times;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getMinutes() {
        return minutes;
    }

    public void setMinutes(List<String> minutes) {
        this.minutes = minutes;
    }
}
