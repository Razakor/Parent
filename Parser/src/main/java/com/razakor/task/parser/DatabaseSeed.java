package com.razakor.task.parser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.razakor.task.repositories.*;

@Component
class DatabaseSeed implements CommandLineRunner {

    private TrolleybusRepository trolleybusRepository;
    private StopRepository stopRepository;
    private HourRepository hourRepository;
    private MinuteRepository minuteRepository;

    public DatabaseSeed(TrolleybusRepository trolleybusRepository,
                        StopRepository stopRepository,
                        HourRepository hourRepository,
                        MinuteRepository minuteRepository) {
        this.trolleybusRepository = trolleybusRepository;
        this.stopRepository = stopRepository;
        this.hourRepository = hourRepository;
        this.minuteRepository = minuteRepository;
    }

    @Override
    public void run(String... args) {
        Loader.load();
        System.out.println("Load is over");
        minuteRepository.deleteAllInBatch();
        hourRepository.deleteAllInBatch();
        stopRepository.deleteAllInBatch();
        trolleybusRepository.deleteAllInBatch();
        System.out.println("Deletion is over");
        minuteRepository.saveAll(Loader.getMinutes());
        hourRepository.saveAll(Loader.getHours());
        stopRepository.saveAll(Loader.getStops());
        trolleybusRepository.saveAll(Loader.getTrolleybuses());
        System.out.println("All Done");
        System.exit(0);
    }
}