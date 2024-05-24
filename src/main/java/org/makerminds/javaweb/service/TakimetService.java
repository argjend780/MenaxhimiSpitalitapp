package org.makerminds.javaweb.service;

import java.util.List;

import org.makeminds.javaweb.Exeption.TakimetNotFoundException;
import org.makerminds.javaweb.Entity.Mjeket;
import org.makerminds.javaweb.Entity.Takimet;
import org.makerminds.javaweb.repository.TakimetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TakimetService {

    private final TakimetRepository takimetRepository;
    private final MjeketService mjeketService;

    public Takimet createNewTakimet(Takimet newTask, Long employeeId, Long departmentId) {
        Mjeket reparti = mjeketService.getMjeket(departmentId, employeeId);
        newTask.setEmployee(reparti);

        switch (newTask.getStatus()) {
            case "INPUT QUEUE":
            case "IN PROGRESS":
                break;
            default:
                newTask.setStatus("DONE");
                break;
        }
        return takimetRepository.save(newTask);
    }

    public List<Takimet> getTaskList(Long departamentiID, Long employeeID) {
        return mjeketService.getMjeket(departamentiID, employeeID).getTakimetlist();
    }

    public ResponseEntity<?> deleteTask(Long departmentId, Long employeeId, Long taskId) {
        mjeketService.getMjeket(departmentId, employeeId);
        Takimet task = takimetRepository.findById(taskId)
                .orElseThrow(() -> new TakimetNotFoundException("Task not found"));
        if (task.getEmployee().getId() == employeeId) {
            takimetRepository.delete(task);
            return ResponseEntity.ok().body("Task with id " + taskId + " has been deleted");
        }
        throw new TakimetNotFoundException("Task not found");
    }

    public Takimet getTask(Long departmentId, Long employeeId, Long taskId) {
        mjeketService.getMjeket(departmentId, employeeId);
        Takimet task = takimetRepository.findById(taskId)
                .orElseThrow(() -> new TakimetNotFoundException("Task not found"));
        if (task.getEmployee().getId() == employeeId) {
            return task;
        }
        throw new TakimetNotFoundException("Task not found");
    }
}