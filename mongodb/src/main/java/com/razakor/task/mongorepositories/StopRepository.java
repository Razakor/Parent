package com.razakor.task.mongorepositories;

import com.razakor.task.documents.Stops;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StopRepository extends MongoRepository<Stops, String> {
}