package com.razakor.task.parser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.razakor.task.mongorepositories.*;

@Component
class DatabaseSeed implements CommandLineRunner {

    private final TrolleybusRepository trolleybusRepository;
    private final StopRepository stopRepository;
    private final TimeRepository timeRepository;

    public DatabaseSeed(TrolleybusRepository trolleybusRepository,
                        StopRepository stopRepository,
                        TimeRepository timeRepository) {
        this.trolleybusRepository = trolleybusRepository;
        this.stopRepository = stopRepository;
        this.timeRepository = timeRepository;
    }

    @Override
    public void run(String... args) {
        Loader.load();
        System.out.println("Load is over");
        timeRepository.deleteAll();
        stopRepository.deleteAll();
        trolleybusRepository.deleteAll();
        System.out.println("Deletion is over");
        timeRepository.saveAll(Loader.getTimes());
        stopRepository.saveAll(Loader.getStops());
        trolleybusRepository.saveAll(Loader.getTrolleybuses());
        System.out.println("All Done");
        System.exit(0);
    }
}