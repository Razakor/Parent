package com.razakor.task.parser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.razakor.task.mongorepositories.*;

@Component
class DatabaseSeed implements CommandLineRunner {

    private TrolleybusRepository trolleybusRepository;

    public DatabaseSeed(TrolleybusRepository trolleybusRepository) {
        this.trolleybusRepository = trolleybusRepository;
    }

    @Override
    public void run(String... args) {
        Loader.load();
        System.out.println("Load is over");
        trolleybusRepository.deleteAll();
        System.out.println("Deletion is over");
        trolleybusRepository.saveAll(Loader.getTrolleybuses());
        System.out.println("All Done");
        System.exit(0);
    }
}