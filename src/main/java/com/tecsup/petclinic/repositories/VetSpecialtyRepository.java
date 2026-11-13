package com.tecsup.petclinic.repositories;

import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId; // <-- Agrega este import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// Cambiamos Integer por VetSpecialtyId aquí abajo
public interface VetSpecialtyRepository extends JpaRepository<VetSpecialty, VetSpecialtyId> {
    List<VetSpecialty> findByVetId(Integer vetId);
    List<VetSpecialty> findBySpecialtyId(Integer specialtyId);
}