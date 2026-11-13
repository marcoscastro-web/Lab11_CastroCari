package com.tecsup.petclinic.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "vet_specialties")
@NoArgsConstructor
@Data
@AllArgsConstructor
@IdClass(VetSpecialtyId.class)
public class VetSpecialty {

    @Id
    @Column(name = "vet_id")
    private Integer vetId;

    @Id
    @Column(name = "specialty_id")
    private Integer specialtyId;
}