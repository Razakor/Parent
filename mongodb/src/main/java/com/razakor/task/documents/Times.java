package com.razakor.task.documents;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "times")
public class Times {
    @Id
    private ObjectId id;
    private String trolleybusNumber;
    private String stopName;
    private LocalTime time;
    private Boolean isWorkDay;

    public Times() {
    }

    public Times(String trolleybusNumber, String stopName, LocalTime time, Boolean isWorkDay) {
        this.trolleybusNumber = trolleybusNumber;
        this.stopName = stopName;
        this.time = time;
        this.isWorkDay = isWorkDay;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTrolleybusNumber() {
        return trolleybusNumber;
    }

    public void setTrolleybusNumber(String trolleybusNumber) {
        this.trolleybusNumber = trolleybusNumber;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
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
}
