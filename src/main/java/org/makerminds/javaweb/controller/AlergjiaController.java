package org.makerminds.javaweb.controller;

import java.util.HashMap;
import java.util.Map;

import org.makerminds.javaweb.Entity.Alergjia;
import org.makerminds.javaweb.service.AlergjiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/alergjia")
@RequiredArgsConstructor
@CrossOrigin
public class AlergjiaController {

	    private final AlergjiaService alergjiaService;

	    @PostMapping(path = "/create/{departmentId}/{employeeId}")
	    public ResponseEntity<?> createAlergjia(
	            @PathVariable Long departmentId,
	            @PathVariable Long employeeId,
	            @Valid @RequestBody Alergjia newTask, BindingResult results) {

	        if (results.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            for (FieldError fieldError : results.getFieldErrors()) {
	                errors.put(fieldError.getField(), fieldError.getDefaultMessage());
	            }
	            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<>(alergjiaService.createNewTakimet(newTask, employeeId, departmentId), HttpStatus.CREATED);
	    }

}
