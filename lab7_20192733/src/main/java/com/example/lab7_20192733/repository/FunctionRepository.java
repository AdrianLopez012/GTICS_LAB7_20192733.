package com.example.lab7_20192733.repository;

import com.example.lab7_20192733.entity.Funciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Funciones, Integer> {
}
