package com.kerimkulah.labReport.controller;

import com.kerimkulah.labReport.entity.Laborant;
import com.kerimkulah.labReport.entity.User;
import com.kerimkulah.labReport.repository.UserRepository;
import com.kerimkulah.labReport.service.LaborantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final LaborantService laborantService;
    private final UserRepository userRepository;

    @PutMapping("/makeAdmin/{UserId}")
    public ResponseEntity<String> makeAdmin(@PathVariable long UserId) {
        return ResponseEntity.ok("Admin yapıldı");
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/removeAdmin/{UserId}")
    public ResponseEntity<String> removeAdmin(@PathVariable long UserId) {
        return ResponseEntity.ok("Adminlik kaldırıldı");
    }

    @PostMapping("/createLaborant")
    public ResponseEntity<String> createLaborant(@RequestBody Laborant laborant) {
        laborantService.createLaborant(laborant);
        return ResponseEntity.ok("Laborant eklendi.");
    }


    @DeleteMapping("/deleteLaborant/{laborantId}")
    public ResponseEntity<String> deleteLaborant(@PathVariable Long laborantId) {
        laborantService.deleteLaborantById(laborantId);
        return ResponseEntity.ok("Laborant silindi.");
    }



}

