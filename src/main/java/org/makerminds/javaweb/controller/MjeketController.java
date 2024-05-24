package org.makerminds.javaweb.controller;

import java.util.HashMap;
import java.util.List;

import org.makerminds.javaweb.Entity.Mjeket;
import org.makerminds.javaweb.Entity.Reparti;
import org.makerminds.javaweb.service.MjeketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@RestController
@RequestMapping("/api/mjeket")
@RequiredArgsConstructor
@CrossOrigin
public class MjeketController {

    private final MjeketService mjeketservice;

    @PostMapping(path="/{qytetiId}/{SpitaliID}/{repartiID}")

    public ResponseEntity<?> createMjeket(@PathVariable Long qytetiId,@PathVariable Long SpitaliID,
    		@PathVariable Long repartiID,@Valid @RequestBody Mjeket mjeket, BindingResult bindingResult) {
    	if(bindingResult.hasErrors()) {
    		Map<String,String>errors = new HashMap<>();
    		for (FieldError filedError : bindingResult.getFieldErrors()) {
    			errors.put(filedError.getField() , filedError.getDefaultMessage());
    			
    		}
    		return new ResponseEntity<Map<String,String>>(errors ,HttpStatus.BAD_REQUEST);
    	}
    	return ResponseEntity.ok(mjeketservice.teOrUpdateMjeket(mjeket,qytetiId,SpitaliID,repartiID));
       
    }
    

    @GetMapping(path = "/o/{depid}/{employid}")
    public Mjeket getEmployeeById(@PathVariable Long depid, @PathVariable Long employid) {
        return mjeketservice.getMjeket(depid, employid);
    }
    @GetMapping(path = "/all/{id}")
    public List<Mjeket>getEmployeeList(@PathVariable Long id){
    	return mjeketservice.getMjeketList(id);
    }
    @DeleteMapping(path = "/delete/{departmentId}/{id}")
    public ResponseEntity<?>deleteEmployeeById(@PathVariable Long departmentId, @PathVariable Long id){
    	return mjeketservice.deleteMjeketById(departmentId,id);
    	
    }
}


