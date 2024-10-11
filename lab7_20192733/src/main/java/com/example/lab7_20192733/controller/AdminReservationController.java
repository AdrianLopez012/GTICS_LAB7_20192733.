package com.example.lab7_20192733.controller;


import com.example.lab7_20192733.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reservas")
public class AdminReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public String listAllReservations(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "admin/reservas";
    }

    @GetMapping("/cancel/{id}")
    public String cancelReservation(@PathVariable Integer id) {
        reservationRepository.deleteById(id);
        return "redirect:/admin/reservas";
    }
}
