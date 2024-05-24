package org.makerminds.javaweb.service;



import org.makerminds.javaweb.Entity.Mjeket;

import org.makerminds.javaweb.Entity.Pacienti;

import org.makerminds.javaweb.Entity.Specializimi;

import org.makerminds.javaweb.repository.MjeketRepository;

import org.makerminds.javaweb.repository.SpecializimiRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import java.util.List;



@Service

public class SpecializimiService {



    private final SpecializimiRepository specializimiRepository;

    private final MjeketRepository mjeketRepository;



    @Autowired

    public SpecializimiService(SpecializimiRepository specializimiRepository, MjeketRepository mjeketRepository) {

        this.specializimiRepository = specializimiRepository;

        this.mjeketRepository=mjeketRepository;

        

    }



    public Specializimi createNewSpecializimi(Specializimi specializimi, Long idMjeku) {

    	 Mjeket mejku = mjeketRepository.findById(idMjeku).orElseThrow(() -> new RuntimeException("Pacienti not found"));

    	 specializimi.setMjeket(mejku);

    	 return specializimiRepository.save(specializimi);

    }



    public Specializimi findSpecializimiById(Long id) {

        return specializimiRepository.findById(id).orElse(null);

    }



    public Specializimi updateSpecializimi(Long id, Specializimi updatedSpecializimi) {

        Specializimi existingSpecializimi = specializimiRepository.findById(id).orElse(null);

        if (existingSpecializimi != null) {

            existingSpecializimi.setEmri(updatedSpecializimi.getEmri());

            return specializimiRepository.save(existingSpecializimi);

        } else {

            return null;

        }

    }



    public void deleteSpecializimi(Long id) {

        specializimiRepository.deleteById(id);

    }



    public List<Specializimi> getAllSpecializimet() {

        return specializimiRepository.findAll();

    }

}