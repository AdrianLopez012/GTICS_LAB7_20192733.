package com.example.lab7_20192733.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "roomseats")
public class RoomSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private Rooms room;

    @Column(name = "seatNumber", nullable = false, length = 10)
    private String seatNumber;

    @Column(name = "rowNumber", nullable = false, length = 2)
    private String rowNumber;

    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;
}
