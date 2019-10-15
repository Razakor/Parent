package com.razakor.task.service.controllers;

import com.razakor.task.documents.*;
import com.razakor.task.service.model.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
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

        LocalTime localTime = LocalTime.now();
        int currentHour = localTime.getHour();
        int currentMinute = localTime.getMinute();


        List<String> scheduleList = new ArrayList<>();


        trolleybuses.forEach(trolleybus -> {
            scheduleList.add(trolleybus.getName());
            trolleybus.getStops().stream().filter(s -> s.getName().equals(firstStop)).forEach(stop -> {
                if (stop.getName().equals(firstStop)) {
                    setTime(stop.getTimes(), true, "Робочі дні", scheduleList, currentHour, currentMinute);
                    setTime(stop.getTimes(), false, "Вихідні дні", scheduleList, currentHour, currentMinute);
                }
            });
        });

        model.addAttribute("schedule", scheduleList);
        return "main";
    }

    private void setTime(Set<Times> allTimes, boolean isWorkDay, String message, List<String> scheduleList, int currentHour, int currentMinute) {
        scheduleList.add(message);
        StringBuilder stringBuilder = new StringBuilder();

        Set<Times> times;
        if (isWorkDay) {
            times = allTimes.stream().filter(Times::getWorkDay).collect(Collectors.toSet());
        } else {
            times = allTimes.stream().filter(time -> !time.getWorkDay()).collect(Collectors.toSet());
        }


        times.stream().sorted(Comparator.comparingInt(c ->
                c.getTime().toSecondOfDay())).forEach(time -> {
            int hour = time.getTime().getHour();
            int minute = time.getTime().getMinute();
            if (hour == currentHour  && minute > currentMinute || hour == currentHour + 1) {
                stringBuilder.append(LocalTime.of(hour, minute)).append(", ");
            }
        });

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        scheduleList.add(stringBuilder.toString());
    }
}
