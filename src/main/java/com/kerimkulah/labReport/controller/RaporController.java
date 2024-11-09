package com.kerimkulah.labReport.controller;

import com.kerimkulah.labReport.entity.Rapor;
import com.kerimkulah.labReport.service.RaporService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rapor")
public class RaporController {

    private final RaporService raporService;

    @PostMapping("/create")
    public ResponseEntity<String> createRapor(@RequestBody Rapor rapor, @RequestParam long laborantId) {
        raporService.createRapor(rapor, laborantId);
        return ResponseEntity.ok("Rapor oluşturuldu");
    }

    @DeleteMapping("/delete/{raporId}")
    public ResponseEntity<String> deleteRapor(@PathVariable Long raporId) {
        raporService.deleteRaporById(raporId);
        return ResponseEntity.ok("Rapor silindi");
    }

    @GetMapping("/get/{raporId}")
    public ResponseEntity<Rapor> getRapor(@PathVariable Long raporId) {
        return ResponseEntity.ok(raporService.getRaporById(raporId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<Iterable<Rapor>> getAllRapor() {
        return ResponseEntity.ok(raporService.getAllRapor());
    }

    @PutMapping("/update/{raporId}")
    public ResponseEntity<String> updateRapor(@RequestBody Rapor rapor, @PathVariable Long raporId) {
        raporService.updateRapor(rapor, raporId);
        return ResponseEntity.ok("Rapor güncellendi");
    }

    @GetMapping
    public ResponseEntity<Iterable<Rapor>> searchRapor(@RequestParam String search) {
        return ResponseEntity.ok(raporService.searchRapor(search));
    }

    @GetMapping("/getAllOrderedByDate")
    public ResponseEntity<Iterable<Rapor>> getAllRaporOrderedByDate() {
        return ResponseEntity.ok(raporService.getAllRaporOrderedByDate());
    }

}
