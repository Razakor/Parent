package com.razakor.task.repositories;

import com.razakor.task.entities.Stops;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Stops, String> {
}