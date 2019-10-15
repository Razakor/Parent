package com.razakor.task.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "times")
public class Times {
    @Id
    private String id;
    @DBRef
    private Trolleybuses trolleybus;
    @DBRef
    private Stops stop;
    private LocalTime time;
    private Boolean isWorkDay;

    public Times() {
    }

    public Times(Trolleybuses trolleybus, Stops stop, LocalTime time, Boolean isWorkDay) {
        this.trolleybus = trolleybus;
        this.stop = stop;
        this.time = time;
        this.isWorkDay = isWorkDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Boolean getWorkDay() {
        return isWorkDay;
    }

    public void setWorkDay(Boolean workDay) {
        isWorkDay = workDay;
    }

    public Trolleybuses getTrolleybus() {
        return trolleybus;
    }

    public void setTrolleybus(Trolleybuses trolleybus) {
        this.trolleybus = trolleybus;
    }

    public Stops getStop() {
        return stop;
    }

    public void setStop(Stops stop) {
        this.stop = stop;
    }
}
