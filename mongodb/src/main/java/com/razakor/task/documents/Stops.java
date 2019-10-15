package com.razakor.task.documents;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Stops {
    private String name;
    private Set<Times> times;

    public Stops() {
    }

    public Stops(String name) {
        this.name = name;
        this.times = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Times> getTimes() {
        return times;
    }

    public void setTimes(Set<Times> times) {
        this.times = times;
    }
}