package com.razakor.task.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "stops")
public class Stops {
    @Id
    private ObjectId id;
    private String name;
    private String trolleybusNumber;
    private Set<Times> times;

    public Stops() {
    }

    public Stops(String name, String trolleybusNumber) {
        this.name = name;
        this.trolleybusNumber = trolleybusNumber;
        this.times = new HashSet<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrolleybusNumber() {
        return trolleybusNumber;
    }

    public void setTrolleybusNumber(String trolleybusNumber) {
        this.trolleybusNumber = trolleybusNumber;
    }

    public Set<Times> getTimes() {
        return times;
    }

    public void setTimes(Set<Times> times) {
        this.times = times;
    }
}