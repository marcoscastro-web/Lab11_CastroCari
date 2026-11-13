package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VetSpecialtyServiceTest {

    @Autowired
    private VetSpecialtyService vetSpecialtyService;

    @Test
    public void testAssignSpecialtyToVet() {
        Integer VET_ID = 1;
        Integer SPECIALTY_ID = 2;

        VetSpecialtyDTO dto = VetSpecialtyDTO.builder()
                .vetId(VET_ID)
                .specialtyId(SPECIALTY_ID)
                .build();

        VetSpecialtyDTO newDto = this.vetSpecialtyService.create(dto);
        assertNotNull(newDto.getVetId());
        assertEquals(VET_ID, newDto.getVetId());
    }

    @Test
    public void testFindSpecialtiesByVetId() {
        Integer VET_ID = 1;
        VetSpecialtyDTO dto = VetSpecialtyDTO.builder().vetId(VET_ID).specialtyId(1).build();
        this.vetSpecialtyService.create(dto);

        var list = this.vetSpecialtyService.findByVetId(VET_ID);
        assertTrue(list.size() > 0);
        assertEquals(VET_ID, list.get(0).getVetId());
    }

    @Test
    public void testFindVetsBySpecialtyId() {
        Integer SPECIALTY_ID = 1;
        VetSpecialtyDTO dto = VetSpecialtyDTO.builder().vetId(1).specialtyId(SPECIALTY_ID).build();
        this.vetSpecialtyService.create(dto);

        var list = this.vetSpecialtyService.findBySpecialtyId(SPECIALTY_ID);
        assertTrue(list.size() > 0);
        assertEquals(SPECIALTY_ID, list.get(0).getSpecialtyId());
    }

    @Test
    public void testDeleteAssignment() {
        Integer VET_ID = 1;
        Integer SPECIALTY_ID = 2;

        VetSpecialtyDTO dto = VetSpecialtyDTO.builder().vetId(VET_ID).specialtyId(SPECIALTY_ID).build();
        this.vetSpecialtyService.create(dto);

        VetSpecialtyId id = new VetSpecialtyId(VET_ID, SPECIALTY_ID);

        try {
            this.vetSpecialtyService.delete(id);

            assertThrows(Exception.class, () -> {
                this.vetSpecialtyService.findById(id);
            });
        } catch (Exception e) {
            fail("Error al eliminar: " + e.getMessage());
        }
    }
}