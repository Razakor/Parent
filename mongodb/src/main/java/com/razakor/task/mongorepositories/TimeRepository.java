package com.razakor.task.mongorepositories;

import com.razakor.task.documents.Times;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimeRepository extends MongoRepository<Times, String> {

    List<Times> findTimesByTrolleybusNumberAndStopNameAndIsWorkDayAndTimeBetweenOrderByTimeAsc(
            String trolleybusNumber, String stopName, boolean isWorkDay, LocalTime currentTime, LocalTime currentTimePlusHour);
}
