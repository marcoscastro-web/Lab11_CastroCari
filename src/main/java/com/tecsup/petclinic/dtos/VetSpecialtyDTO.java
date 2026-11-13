package com.tecsup.petclinic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VetSpecialtyDTO {
    // Ya no tiene ID propio, solo las dos llaves foráneas
    private Integer vetId;
    private Integer specialtyId;
}