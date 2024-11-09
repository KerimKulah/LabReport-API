package com.kerimkulah.labReport.controller;

import com.kerimkulah.labReport.entity.Laborant;
import com.kerimkulah.labReport.service.LaborantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laborant")
public class LaborantController {

    private final LaborantService laborantService;

    @GetMapping("/get/{laborantId}")
    public ResponseEntity<Laborant> getLaborant(@PathVariable Long laborantId) {
        return ResponseEntity.ok(laborantService.getLaborantById(laborantId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Laborant>> getAllLaborant() {
        return ResponseEntity.ok(laborantService.getAllLaborant());
    }
}
