package com.example.lab7_20192733.repository;

import com.example.lab7_20192733.entity.Reservations;
import com.example.lab7_20192733.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservations, Integer> {
    List<Reservations> findByUser(Users user);
}