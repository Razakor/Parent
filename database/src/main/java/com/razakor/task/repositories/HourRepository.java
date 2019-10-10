package com.razakor.task.repositories;

import com.razakor.task.entities.Hours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HourRepository extends JpaRepository<Hours, String> {
}