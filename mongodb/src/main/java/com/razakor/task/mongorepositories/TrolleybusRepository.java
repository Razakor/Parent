package com.razakor.task.mongorepositories;

import com.razakor.task.documents.Trolleybuses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrolleybusRepository extends MongoRepository<Trolleybuses, String> {

    @Query("{'stops.name': {$all: [?0, ?1]}}")
    List<Trolleybuses> findTrolleybusesByStopNames(String firstStopName, String secondStopName);



}