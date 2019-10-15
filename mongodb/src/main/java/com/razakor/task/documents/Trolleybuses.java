package com.razakor.task.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Document(collection = "trolleybuses")
public class Trolleybuses {
    @Id
    private String number;
    private String name;
    @DBRef
    private Set<Stops> stops;
    @DBRef
    private Set<Times> times;

    public Trolleybuses() {
    }

    public Trolleybuses(String number, String name) {
        this.number = number;
        this.name = name;
        this.stops = new HashSet<>();
        this.times = new HashSet<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Stops> getStops() {
        return stops;
    }

    public void setStops(Set<Stops> stops) {
        this.stops = stops;
    }

    public Set<Times> getTimes() {
        return times;
    }

    public void setTimes(Set<Times> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return name;
    }
}