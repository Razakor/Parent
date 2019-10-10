package com.razakor.task.repositories;

import com.razakor.task.entities.Minutes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinuteRepository extends JpaRepository<Minutes, String> {
}