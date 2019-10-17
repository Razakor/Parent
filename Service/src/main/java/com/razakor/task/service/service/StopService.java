package com.razakor.task.service.service;

import com.razakor.task.documents.Stops;
import com.razakor.task.mongorepositories.StopRepository;
import org.springframework.stereotype.Service;

@Service
public class StopService {

    private final StopRepository stopRepository;

    public StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    public Stops getStopsByTrolleybusNumberAndName(String trolleybusNumber, String name) {
        return stopRepository.findStopsByTrolleybusNumberAndName(trolleybusNumber, name);
    }
}
