package com.razakor.task.service.service;

import com.razakor.task.documents.Times;
import com.razakor.task.mongorepositories.TimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TimeService {
    private  final TimeRepository timeRepository;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<Times> getTimes(String trolleybusNumber, String stopName, boolean isWorkDay, LocalTime currentTime, LocalTime currentTimePlusHour) {
        return timeRepository.findTimesByTrolleybusNumberAndStopNameAndIsWorkDayAndTimeBetweenOrderByTimeAsc(
                trolleybusNumber, stopName, isWorkDay, currentTime, currentTimePlusHour);
    }
}
