package com.razakor.task.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hours")
public class Hours {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "trolleybus_number")
    private String trolleybusNumber;
    @Column(name = "stop_name")
    private String stopName;
    @Column(name = "val")
    private String value;
    @Column(name = "is_work_day")
    private Boolean isWorkDay;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = Minutes.class, mappedBy = "hour")
    private Set<Minutes> minutes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "trolleybus_number", nullable = false, insertable = false, updatable = false)
    private Trolleybuses trolleybus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "stop_name", nullable = false, insertable = false, updatable = false)
    private Stops stop;

    public Hours() {
    }

    public Hours(Integer id, String trolleybusNumber, String stopName, String value, Boolean isWorkDay) {
        this.id = id;
        this.trolleybusNumber = trolleybusNumber;
        this.stopName = stopName;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getWorkDay() {
        return isWorkDay;
    }

    public void setWorkDay(Boolean workDay) {
        isWorkDay = workDay;
    }

    public Set<Minutes> getMinutes() {
        return minutes;
    }

    public void setMinutes(Set<Minutes> minutes) {
        this.minutes = minutes;
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