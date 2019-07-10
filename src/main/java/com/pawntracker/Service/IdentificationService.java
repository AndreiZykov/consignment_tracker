package com.pawntracker.service;


import com.pawntracker.entity.id.Identification;
import com.pawntracker.repository.IdentificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IdentificationService {
    @Autowired
    private IdentificationRepository identificationRepository;

    public Identification saveOrUpdate(Identification identification) {
        return identificationRepository.save(identification);
    }

    public Identification getIdentificationById( Long id) {
        return identificationRepository.getIdentificationById(id);
    }


    public void delete(Identification identification) {
        identificationRepository.delete(identification);
    }
}
