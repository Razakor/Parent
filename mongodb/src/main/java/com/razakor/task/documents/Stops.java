package com.razakor.task.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Stops {
    @Id
    private String name;
    @DBRef
    private Set<Trolleybuses> trolleybuses;
    @DBRef
    private Set<Times> times;

    public Stops() {
    }

    public Stops(String name) {
        this.name = name;
        this.trolleybuses = new HashSet<>();
        this.times = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Trolleybuses> getTrolleybuses() {
        return trolleybuses;
    }

    public void setTrolleybuses(Set<Trolleybuses> trolleybuses) {
        this.trolleybuses = trolleybuses;
    }

    public Set<Times> getTimes() {
        return times;
    }

    public void setTimes(Set<Times> times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stops stops = (Stops) o;
        return name.equals(stops.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}