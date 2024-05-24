package org.makerminds.javaweb.service;

import java.util.List;

import org.makeminds.javaweb.Exeption.MjeketNotFoundExeption;
import org.makerminds.javaweb.Entity.Mjeket;
import org.makerminds.javaweb.Entity.Reparti;
import org.makerminds.javaweb.Entity.Spitali;
import org.makerminds.javaweb.repository.MjeketRepository;
import org.makerminds.javaweb.repository.RepartiRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;



@Service

@AllArgsConstructor
public class MjeketService {

    private final MjeketRepository mjeketrepository;
    private final RepartiRepository repartirepository;
    private final RepartiService repartiservice;
    
   
    public Mjeket teOrUpdateMjeket(Mjeket mjeket,Long qytetiId,Long spitaliid,Long repartiid)
    {
    	Reparti employee= repartiservice.getTask(qytetiId, spitaliid,repartiid);
    	
    	mjeket.setReparti(employee);
    	return mjeketrepository.save(mjeket);
    	}
    

    public Mjeket findMyId(Long id) {return mjeketrepository.findById(id).orElse(null);}
   
    
    public List<Mjeket> getMjeketList(Long repartiId){	
    	return repartirepository.findById(repartiId).get().getMjeketliste();
    	}
    
    public ResponseEntity<?> deleteMjeketById(Long repartiId ,Long id){Mjeket mjeket = findMyId(id);
    if(mjeket != null){
    	if(mjeket.getReparti().getId() == repartiId) {
    	
    		mjeketrepository.delete(mjeket);
    String massage = "Employee with id:" + mjeket.getId()+ "has been deleted";
    return ResponseEntity.ok().body(massage);
    
    	}
    	else {String massage = "Employee was not found";
        throw new MjeketNotFoundExeption(massage);
    		
    	}
    }
    else{String massage = "Employee was not found";
         throw new MjeketNotFoundExeption(massage);
      }
    } 
 
    public Mjeket getMjeket(Long repartiId,Long mjeketID) {
    	
    	Mjeket mjeket = findMyId(mjeketID);
    	
    	  if(mjeket == null ||mjeket.getReparti().getId() != repartiId ) {
    		  throw new MjeketNotFoundExeption("Employe not found ");
    	  }
    
    	return mjeket;
    }
}

