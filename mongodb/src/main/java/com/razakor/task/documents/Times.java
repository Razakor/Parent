package com.razakor.task.documents;

import java.time.LocalTime;

public class Times {
    private LocalTime time;
    private Boolean isWorkDay;

    public Times() {
    }

    public Times(LocalTime time, Boolean isWorkDay) {
        this.time = time;
        this.isWorkDay = isWorkDay;
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
