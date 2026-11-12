package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VisitDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VisitServiceTest {

    @Autowired
    private VisitService visitService;

    @Test
    public void testCreateVisit() {
        int PET_ID = 1;
        LocalDate VISIT_DATE = LocalDate.now();
        String DESCRIPTION = "Chequeo general";

        VisitDTO dto = VisitDTO.builder()
                .petId(PET_ID)
                .visitDate(VISIT_DATE)
                .description(DESCRIPTION)
                .build();

        VisitDTO newDto = this.visitService.create(dto);
        log.info("VISIT CREATED :" + newDto.toString());

        assertNotNull(newDto.getId());
        assertEquals(PET_ID, newDto.getPetId());
        assertEquals(DESCRIPTION, newDto.getDescription());
    }

    @Test
    public void testUpdateVisit() {
        String DESC = "Vacunación";
        String UP_DESC = "Vacunación actualizada";

        VisitDTO dto = VisitDTO.builder()
                .petId(2)
                .visitDate(LocalDate.now())
                .description(DESC)
                .build();
        VisitDTO createdDto = this.visitService.create(dto);

        createdDto.setDescription(UP_DESC);
        VisitDTO updatedDto = this.visitService.update(createdDto);

        assertEquals(UP_DESC, updatedDto.getDescription());
    }

    @Test
    public void testFindVisitByPetId() {
        int FIND_PET_ID = 3;
        VisitDTO dto = VisitDTO.builder()
                .petId(FIND_PET_ID)
                .visitDate(LocalDate.now())
                .description("Control")
                .build();
        this.visitService.create(dto);

        var visits = this.visitService.findByPetId(FIND_PET_ID);
        assertTrue(visits.size() > 0);
        assertEquals(FIND_PET_ID, visits.get(0).getPetId());
    }

    @Test
    public void testDeleteVisit() {
        VisitDTO dto = VisitDTO.builder()
                .petId(4)
                .visitDate(LocalDate.now())
                .description("Para borrar")
                .build();
        VisitDTO newDto = this.visitService.create(dto);

        try {
            this.visitService.delete(newDto.getId());

            // Verificamos que ya no exista
            assertThrows(Exception.class, () -> {
                this.visitService.findById(newDto.getId());
            });
        } catch (Exception e) {
            fail("Error al eliminar: " + e.getMessage());
        }
    }
}