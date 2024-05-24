package org.makerminds.javaweb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.makerminds.javaweb.Entity.Qyteti;
import org.makerminds.javaweb.service.QytetiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/qyteti")
@CrossOrigin
public class QytetiController {

    private final QytetiService qytetiService;

    public QytetiController(QytetiService qytetiService) {
        this.qytetiService = qytetiService;
    }

    // Create a new Qyteti
    @PostMapping
    public ResponseEntity<?> createQyteti(@Valid @RequestBody Qyteti qyteti, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(qytetiService.createQyteti(qyteti));
    }

    // Get Qyteti by id
    @GetMapping("/{id}")
    public ResponseEntity<Qyteti> getQytetiById(@PathVariable Long id) {
        Qyteti foundQyteti = qytetiService.findById(id);
        if (foundQyteti != null) {
            return ResponseEntity.ok(foundQyteti);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Qyteti by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQyteti(@PathVariable Long id) {
        return qytetiService.deleteQytetiById(id);
    }

    // Get all Qytetet
    @GetMapping("/all")
    public ResponseEntity<List<Qyteti>> getAllQytetet() {
        List<Qyteti> qytetiList = qytetiService.getAllQytetet();
        return ResponseEntity.ok(qytetiList);
    }
}
