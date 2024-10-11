package com.example.lab7_20192733.repository;

import com.example.lab7_20192733.entity.Roles;
import com.example.lab7_20192733.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByName(String name);
}
