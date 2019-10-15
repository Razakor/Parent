package com.razakor.task.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "trolleybuses")
public class Trolleybuses {
    @Id
    private String number;
    private String name;
    private Set<Stops> stops;

    public Trolleybuses() {
    }

    public Trolleybuses(String number, String name) {
        this.number = number;
        this.name = name;
        this.stops = new HashSet<>();
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

    @Override
    public String toString() {
        return name;
    }
}