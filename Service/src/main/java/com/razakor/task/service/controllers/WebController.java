package com.razakor.task.service.controllers;

import com.razakor.task.entities.*;
import com.razakor.task.service.model.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @GetMapping()
    public String main(Model model) {
        List<String> hollow = new ArrayList<>();
        model.addAttribute("schedule", hollow);
        return "main";
    }


    @PostMapping()
    public String add(@RequestParam String firstStop, @RequestParam String secondStop, Model model) {
        List<Trolleybuses> trolleybuses = Data.trolleybuses;

        trolleybuses = trolleybuses.stream().filter(trolleybus ->
                trolleybus.getStops().stream().anyMatch(stop ->
                        stop.getName().equals(firstStop)) &&
                        trolleybus.getStops().stream().anyMatch(stop ->
                                stop.getName().equals(secondStop))).collect(Collectors.toList());


        Date date = new Date();
        SimpleDateFormat hourFormatter = new SimpleDateFormat("H");
        SimpleDateFormat minutesFormatter = new SimpleDateFormat("mm");
        String currentHour = hourFormatter.format(date);
        String currentMinute = minutesFormatter.format(date);

        List<String> scheduleList = new ArrayList<>();

        trolleybuses.forEach(trolleybus -> {
            scheduleList.add(trolleybus.getName());
            trolleybus.getStops().forEach(stop -> {
                if (stop.getName().equals(firstStop)) {
                    scheduleList.add("Робочі дні");
                    Data.workDayHours.forEach(hour -> {
                        if (hour.getStopName().equals(stop.getName()) &&
                                hour.getTrolleybusNumber().equals(trolleybus.getNumber())) {
                            if(hour.getValue().equals(currentHour)) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(hour.getValue()).append(": ");
                                hour.getMinutes().forEach(minute ->
                                        stringBuilder.append(minute.getValue()).append(" "));
                                scheduleList.add(stringBuilder.toString());
                            }
                        }
                    });
                    scheduleList.add("Вихідні дні");
                    Data.weekEndHours.forEach(hour -> {
                        if (hour.getStopName().equals(stop.getName()) &&
                                hour.getTrolleybusNumber().equals(trolleybus.getNumber())) {
                            if(hour.getValue().equals(currentHour)) {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(hour.getValue()).append(": ");
                                hour.getMinutes().forEach(minute ->
                                        stringBuilder.append(minute.getValue()).append(" "));
                                scheduleList.add(stringBuilder.toString());
                            }
                        }
                    });
                }
            });
        });

        model.addAttribute("schedule", scheduleList);
        return "main";
    }
}
