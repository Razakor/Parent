package com.razakor.task.entities;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "times")
public class Times {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Column(name = "trolleybus_number")
    private String trolleybusNumber;
    @Column(name = "stop_name")
    private String stopName;
    @Column(name = "time")
    private LocalTime time;
    @Column(name = "is_work_day")
    private Boolean isWorkDay;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "trolleybus_number", nullable = false, insertable = false, updatable = false)
    private Trolleybuses trolleybus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "stop_name", nullable = false, insertable = false, updatable = false)
    private Stops stop;

    public Times() {
    }

    public Times(String trolleybusNumber, String stopName, LocalTime time, Boolean isWorkDay) {
        this.trolleybusNumber = trolleybusNumber;
        this.stopName = stopName;
        this.time = time;
        this.isWorkDay = isWorkDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
