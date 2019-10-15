package com.razakor.task.mongorepositories;

import com.razakor.task.documents.Times;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TimesRepository extends MongoRepository<Times, String> {
}
