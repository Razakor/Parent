package com.razakor.task.service.model;

import com.razakor.task.entities.Hours;
import com.razakor.task.entities.Trolleybuses;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Trolleybuses> trolleybuses = new ArrayList<>();
    public static List<Hours> workDayHours = new ArrayList<>();
    public static List<Hours> weekEndHours = new ArrayList<>();
}