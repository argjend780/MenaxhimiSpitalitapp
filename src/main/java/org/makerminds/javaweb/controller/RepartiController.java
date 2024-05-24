package org.makerminds.javaweb.controller;

import org.makerminds.javaweb.Entity.Reparti;
import org.makerminds.javaweb.service.RepartiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repartet")
public class RepartiController {

    @Autowired
    private RepartiService repartiService;

    @PostMapping(path="/{departmentId}/{employeeId}")

	public ResponseEntity<?> addNewTask(@PathVariable Long departmentId, @PathVariable Long employeeId, @Valid @RequestBody Reparti newTask, BindingResult result) {

		if(result.hasErrors()) {

			Map<String, String> errors=new HashMap<>();

			for(FieldError fieldError : result.getFieldErrors()) {

				errors.put(fieldError.getField(), fieldError.getDefaultMessage());

			}

			return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);

		}

		return new ResponseEntity<> (repartiService.createNewReparti(newTask, employeeId, departmentId),  HttpStatus.CREATED);

	}

    @GetMapping("/list/{departamentiId}/{employeeId}")
    public ResponseEntity<List<Reparti>> getRepartetList(@PathVariable Long departamentiId, @PathVariable Long employeeId) {
        List<Reparti> repartetList = repartiService.getTaskList(departamentiId, employeeId);
        return new ResponseEntity<>(repartetList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{departmentId}/{employeeId}/{taskId}")
    public ResponseEntity<?> deleteReparti(@PathVariable Long departmentId, @PathVariable Long employeeId, @PathVariable Long taskId) {
        return repartiService.deleteTask(departmentId, employeeId, taskId);
    }

    @GetMapping("/get/{departmentId}/{employeeId}/{taskId}")
    public ResponseEntity<Reparti> getReparti(@PathVariable Long departmentId, @PathVariable Long employeeId, @PathVariable Long taskId) {
        Reparti reparti = repartiService.getTask(departmentId, employeeId, taskId);
        return new ResponseEntity<>(reparti, HttpStatus.OK);
    }
}
