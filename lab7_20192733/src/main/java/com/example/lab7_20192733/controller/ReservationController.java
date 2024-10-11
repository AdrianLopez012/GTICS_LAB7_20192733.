package com.example.lab7_20192733.controller;

import com.example.lab7_20192733.entity.Reservations;
import com.example.lab7_20192733.entity.Users;
import com.example.lab7_20192733.repository.FunctionRepository;
import com.example.lab7_20192733.repository.ReservationRepository;
import com.example.lab7_20192733.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/cliente/reservas")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listReservations(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Users user = userRepository.findByEmail(userDetails.getUsername()).orElse(null);

        if (user != null) {
            List<Reservations> reservations = reservationRepository.findByUser(user);
            model.addAttribute("reservations", reservations);
        }
        return "cliente/reservas";
    }

    @PostMapping("/save")
    public String saveReservation(@RequestParam("functionId") Integer functionId, Authentication authentication) {
        Users user = (Users) authentication.getPrincipal();

        Reservations reservation = new Reservations();
        reservation.setUser(user);
        reservation.setFunction(functionRepository.findById(functionId).orElse(null));
        reservation.setStartDateTime(LocalDateTime.now());

        reservationRepository.save(reservation);
        return "redirect:/cliente/reservas";
    }

}