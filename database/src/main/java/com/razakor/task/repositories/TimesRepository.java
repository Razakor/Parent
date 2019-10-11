package com.razakor.task.repositories;

import com.razakor.task.entities.Times;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesRepository extends JpaRepository<Times, String> {
}
