package com.example.lab7_20192733.repository;


import com.example.lab7_20192733.entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Integer> {
}
