package com.example.lab7_20192733.controller;

import com.example.lab7_20192733.entity.Funciones;
import com.example.lab7_20192733.entity.Obras;
import com.example.lab7_20192733.entity.Rooms;
import com.example.lab7_20192733.repository.FunctionRepository;
import com.example.lab7_20192733.repository.ObrasRepository;
import com.example.lab7_20192733.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/admin/funciones")
public class FunctionController {

    @Autowired
    private FunctionRepository functionRepository;

    @Autowired
    private ObrasRepository obrasRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public String listFunciones(Model model) {
        List<Funciones> funciones = functionRepository.findAll();
        model.addAttribute("funciones", funciones);
        return "admin/funciones";
    }

    @GetMapping("/create")
    public String createFuncionForm(Model model) {
        model.addAttribute("funcion", new Funciones());
        model.addAttribute("obras", obrasRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        return "admin/createFuncion";
    }

    @PostMapping("/save")
    public String saveFuncion(@ModelAttribute("funcion") Funciones funcion, @RequestParam("obraId") Integer obraId, @RequestParam("roomId") Integer roomId) {
        Obras obra = obrasRepository.findById(obraId).orElse(null);
        Rooms room = roomRepository.findById(roomId).orElse(null);

        funcion.setObra(obra);
        funcion.setRoom(room);
        funcion.setFuncionDate(LocalDateTime.now());

        functionRepository.save(funcion);
        return "redirect:/admin/funciones";
    }
}