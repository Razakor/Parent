package com.razakor.task.service.service;

import com.razakor.task.documents.Trolleybuses;
import com.razakor.task.mongorepositories.TrolleybusRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrolleybusService {
    private final TrolleybusRepository trolleybusRepository;

    public TrolleybusService(TrolleybusRepository trolleybusRepository) {
        this.trolleybusRepository = trolleybusRepository;
    }

    public List<Trolleybuses> getTrolleybusesWithStops(String firstStopName, String secondStopName) {
        return trolleybusRepository.findTrolleybusesByStopNames(firstStopName, secondStopName);
    }

}
