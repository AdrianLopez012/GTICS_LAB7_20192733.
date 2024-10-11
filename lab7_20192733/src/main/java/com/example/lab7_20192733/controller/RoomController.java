package com.example.lab7_20192733.controller;

import com.example.lab7_20192733.entity.Rooms;
import com.example.lab7_20192733.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gerente/salas")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String listRooms(Model model) {
        List<Rooms> rooms = roomRepository.findAll();
        model.addAttribute("rooms", rooms);
        return "gerente/salas";
    }

    @GetMapping("/create")
    public String createRoomForm(Model model) {
        model.addAttribute("room", new Rooms());
        return "gerente/createSala";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute("room") Rooms room) {
        roomRepository.save(room);
        return "redirect:/gerente/salas";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable Integer id, Model model) {
        Rooms room = roomRepository.findById(id).orElse(null);
        model.addAttribute("room", room);
        return "gerente/editSala";
    }

    @PostMapping("/update/{id}")
    public String updateRoom(@PathVariable Integer id, @ModelAttribute("room") Rooms room) {
        Rooms existingRoom = roomRepository.findById(id).orElse(null);

        if (existingRoom != null) {
            existingRoom.setName(room.getName());
            existingRoom.setCapacity(room.getCapacity());

            roomRepository.save(existingRoom);
        }
        return "redirect:/gerente/salas";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        roomRepository.deleteById(id);
        return "redirect:/gerente/salas";
    }
}