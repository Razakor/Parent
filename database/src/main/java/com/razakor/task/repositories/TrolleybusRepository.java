package com.razakor.task.repositories;

import com.razakor.task.entities.Trolleybuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrolleybusRepository extends JpaRepository<Trolleybuses, String> {
}