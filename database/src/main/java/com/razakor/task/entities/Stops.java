package com.razakor.task.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stops")
public class Stops {
    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(targetEntity = Trolleybuses.class, fetch = FetchType.EAGER, mappedBy = "stops")
    private Set<Trolleybuses> trolleybuses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stop")
    private Set<Hours> hours;

    public Stops() {
    }

    public Stops(String name) {
        this.name = name;
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

    public Set<Hours> getHours() {
        return hours;
    }

    public void setHours(Set<Hours> hours) {
        this.hours = hours;
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