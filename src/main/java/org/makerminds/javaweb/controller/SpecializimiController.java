
package org.makerminds.javaweb.controller;

import org.makerminds.javaweb.Entity.DosjaMejeksore;
import org.makerminds.javaweb.Entity.Specializimi;
import org.makerminds.javaweb.service.SpecializimiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializimet")
public class SpecializimiController {

    private final SpecializimiService specializimiService;

    @Autowired
    public SpecializimiController(SpecializimiService specializimiService) {
        this.specializimiService = specializimiService;
    }

    @PostMapping("/create/{mjekuId}")
    public ResponseEntity<Specializimi> createDosjaMejeksore(@RequestBody Specializimi specializimi, @PathVariable Long mjekuId) {
        Specializimi newDosjaMejeksore = specializimiService.createNewSpecializimi(specializimi, mjekuId);
        return ResponseEntity.ok(newDosjaMejeksore);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specializimi> getSpecializimi(@PathVariable Long id) {
        Specializimi specializimi = specializimiService.findSpecializimiById(id);
        if (specializimi != null) {
            return ResponseEntity.ok(specializimi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specializimi> updateSpecializimi(@PathVariable Long id, @RequestBody Specializimi updatedSpecializimi) {
        Specializimi specializimi = specializimiService.updateSpecializimi(id, updatedSpecializimi);
        if (specializimi != null) {
            return ResponseEntity.ok(specializimi);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecializimi(@PathVariable Long id) {
        specializimiService.deleteSpecializimi(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Specializimi>> getAllSpecializimet() {
        List<Specializimi> specializimet = specializimiService.getAllSpecializimet();
        return ResponseEntity.ok(specializimet);
    }
}