package com.example.lab7_20192733.controller;

import com.example.lab7_20192733.entity.Roles;
import com.example.lab7_20192733.entity.Users;
import com.example.lab7_20192733.repository.ReservationRepository;
import com.example.lab7_20192733.repository.RolesRepository;
import com.example.lab7_20192733.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        Roles roleCliente = rolesRepository.findByName("ROLE_CLIENTE");
        user.setRole(roleCliente);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}















