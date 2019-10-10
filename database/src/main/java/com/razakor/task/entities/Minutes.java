package com.razakor.task.entities;

import javax.persistence.*;

@Entity
@Table(name = "minutes")
public class Minutes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;
    @Column(name = "hour_id")
    private Integer hourId;
    @Column(name = "val")
    private String value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "hour_id", nullable = false, insertable = false, updatable = false)
    private Hours hour;

    public Minutes() {
    }

    public Minutes(Integer hourId, String value) {
        this.hourId = hourId;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHourId() {
        return hourId;
    }

    public void setHourId(Integer hourId) {
        this.hourId = hourId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Hours getHour() {
        return hour;
    }

    public void setHour(Hours hour) {
        this.hour = hour;
    }
}