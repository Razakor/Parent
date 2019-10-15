package com.razakor.task.service.bootstrap;

import com.razakor.task.mongorepositories.*;
import com.razakor.task.service.model.Data;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements ApplicationRunner {

    private final TrolleybusRepository trolleybusRepository;


    public BootStrapData(TrolleybusRepository trolleybusRepository) {
        this.trolleybusRepository = trolleybusRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("Loading Data");
        System.out.println("Trolleybuses Saved: " + trolleybusRepository.count());
        Data.trolleybuses = trolleybusRepository.findAll();
    }
}