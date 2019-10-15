package com.razakor.task.mongorepositories;

import com.razakor.task.documents.Trolleybuses;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrolleybusRepository extends MongoRepository<Trolleybuses, String> {
}