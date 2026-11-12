package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class TypeServiceTest { // Si el archivo se llama PetTypeServiceTest, cambia el nombre de la clase

    @Autowired
    private TypeService typeService;

    @Test
    public void testCreateType() {
        String NAME = "Perro";

        TypeDTO dto = TypeDTO.builder().name(NAME).build();
        TypeDTO newDto = this.typeService.create(dto);

        log.info("TYPE CREATED :" + newDto.toString());
        assertNotNull(newDto.getId());
        assertEquals(NAME, newDto.getName());
    }

    @Test
    public void testUpdateType() {
        String NAME = "Gato";
        String UP_NAME = "Gato Persa";

        TypeDTO dto = TypeDTO.builder().name(NAME).build();
        TypeDTO createdDto = this.typeService.create(dto);

        createdDto.setName(UP_NAME);
        TypeDTO updatedDto = this.typeService.update(createdDto);

        assertEquals(UP_NAME, updatedDto.getName());
    }

    @Test
    public void testFindTypeByName() {
        String FIND_NAME = "Ave";
        TypeDTO dto = TypeDTO.builder().name(FIND_NAME).build();
        this.typeService.create(dto);

        var types = this.typeService.findByName(FIND_NAME);
        assertTrue(types.size() > 0);
        assertEquals(FIND_NAME, types.get(0).getName());
    }

    @Test
    public void testDeleteType() {
        TypeDTO dto = TypeDTO.builder().name("Temporal").build();
        TypeDTO newDto = this.typeService.create(dto);

        try {
            this.typeService.delete(newDto.getId());

            // Verificamos que ya no exista
            assertThrows(Exception.class, () -> {
                this.typeService.findById(newDto.getId());
            });
        } catch (Exception e) {
            fail("Error al eliminar: " + e.getMessage());
        }
    }
}