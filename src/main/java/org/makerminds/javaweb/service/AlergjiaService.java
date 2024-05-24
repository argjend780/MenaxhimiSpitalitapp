package org.makerminds.javaweb.service;

import org.makerminds.javaweb.Entity.Alergjia;
import org.makerminds.javaweb.Entity.Mjeket;
import org.makerminds.javaweb.Entity.Pacienti;
import org.makerminds.javaweb.Entity.Reparti;
import org.makerminds.javaweb.Entity.Spitali;
import org.makerminds.javaweb.Entity.Takimet;
import org.makerminds.javaweb.repository.AlergjiaRepository;
import org.makerminds.javaweb.repository.PacientiRepository;
import org.makerminds.javaweb.repository.TakimetRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlergjiaService {
	
	  private final AlergjiaRepository alergjiaRepository;
	    private final PacinetiService pacinetiService;
	
	
	public Alergjia createNewTakimet(Alergjia newTask, Long employeeId, Long departmentId) {
        Pacienti reparti = pacinetiService.getMjeket(departmentId, employeeId);
        newTask.setPacineti(reparti);
        
        return alergjiaRepository.save(newTask);
    }

}
