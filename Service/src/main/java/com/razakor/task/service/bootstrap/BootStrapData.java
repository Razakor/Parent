package com.razakor.task.service.bootstrap;

import com.razakor.task.repositories.*;
import com.razakor.task.entities.*;
import com.razakor.task.service.model.Data;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements ApplicationRunner {

    private final TrolleybusRepository trolleybusRepository;
    private final StopRepository stopRepository;
    private final HourRepository hourRepository;
    private final MinuteRepository minuteRepository;


    public BootStrapData(
            TrolleybusRepository trolleybusRepository,
            StopRepository stopRepository,
            HourRepository hourRepository,
            MinuteRepository minuteRepository
    ) {
        this.trolleybusRepository = trolleybusRepository;
        this.stopRepository = stopRepository;
        this.hourRepository = hourRepository;
        this.minuteRepository = minuteRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Loading Data");
        System.out.println("Trolleybuses Saved: " + trolleybusRepository.count());
        System.out.println("Stops Saved: " + stopRepository.count());
        System.out.println("Hours Saved: " + hourRepository.count());
        System.out.println("Minutes Saved: " + minuteRepository.count());
        Data.trolleybuses = trolleybusRepository.findAll();
        List<Hours> hours = hourRepository.findAll();
        hours.forEach(hour -> {
            if(hour.getWorkDay()) {
                Data.workDayHours.add(hour);
            } else {
                Data.weekEndHours.add(hour);
            }
        });
    }
}