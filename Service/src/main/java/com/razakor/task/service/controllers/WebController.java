package com.razakor.task.service.controllers;

import com.razakor.task.documents.*;
import com.razakor.task.service.service.StopService;
import com.razakor.task.service.service.TimeService;
import com.razakor.task.service.service.TrolleybusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private final TrolleybusService trolleybusService;
    private final StopService stopService;
    private final TimeService timeService;

    public WebController(TrolleybusService trolleybusService,
                         StopService stopService,
                         TimeService timeService) {
        this.trolleybusService = trolleybusService;
        this.stopService = stopService;
        this.timeService = timeService;
    }

    @GetMapping()
    public String main(Model model) {
        List<String> hollow = new ArrayList<>();
        model.addAttribute("schedule", hollow);
        return "main";
    }

    @PostMapping()
    public String add(@RequestParam String firstStopName, @RequestParam String secondStopName, Model model) {

        List<Trolleybuses> trolleybuses = trolleybusService.getTrolleybusesWithStops(firstStopName, secondStopName);
        LocalTime localTime = LocalTime.now();
        int currentHour = localTime.getHour();
        int currentMinute = localTime.getMinute();

        List<String> scheduleList = new ArrayList<>();

        trolleybuses.forEach(trolleybus -> {
            scheduleList.add(trolleybus.getName());
            Stops stop = stopService.getStopsByTrolleybusNumberAndName(trolleybus.getNumber(), firstStopName);
            setTime(trolleybus.getNumber(), stop.getName(), true, "Робочі дні", scheduleList, currentHour, currentMinute);
            setTime(trolleybus.getNumber(), stop.getName(), false, "Вихідні дні", scheduleList, currentHour, currentMinute);
        });

        model.addAttribute("schedule", scheduleList);
        return "main";
    }

    private void setTime(String trolleybusNumber, String stopName, boolean isWorkDay, String message, List<String> scheduleList, int currentHour, int currentMinute) {
        scheduleList.add(message);

        List<Times> times = timeService.getTimes(trolleybusNumber, stopName, isWorkDay, LocalTime.now(), LocalTime.now().plusHours(1));

        StringBuilder stringBuilder = new StringBuilder();
        times.forEach(time -> stringBuilder.append(time.getTime()).append(", "));
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        scheduleList.add(stringBuilder.toString());
    }
}