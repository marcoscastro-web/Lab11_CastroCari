package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import java.util.List;

public interface VetSpecialtyService {
    VetSpecialtyDTO create(VetSpecialtyDTO dto);
    void delete(VetSpecialtyId id) throws VetSpecialtyNotFoundException;
    VetSpecialtyDTO findById(VetSpecialtyId id) throws VetSpecialtyNotFoundException;
    List<VetSpecialtyDTO> findByVetId(Integer vetId);
    List<VetSpecialtyDTO> findBySpecialtyId(Integer specialtyId);
    List<VetSpecialtyDTO> findAll();
}