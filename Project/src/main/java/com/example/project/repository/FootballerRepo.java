package com.example.project.repository;

import com.example.project.model.Footballer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootballerRepo extends JpaRepository<Footballer, Long> {
    List<Footballer> findByNameContaining(String keyword);
}
