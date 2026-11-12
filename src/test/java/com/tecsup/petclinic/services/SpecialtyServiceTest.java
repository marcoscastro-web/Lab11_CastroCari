package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.SpecialtyDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class SpecialtyServiceTest {

	@Autowired
	private SpecialtyService specialtyService;

	@Test
	public void testCreateSpecialty() {
		String NAME = "Cirugía";

		SpecialtyDTO dto = SpecialtyDTO.builder().name(NAME).build();
		SpecialtyDTO newDto = this.specialtyService.create(dto);

		log.info("SPECIALTY CREATED :" + newDto.toString());
		assertNotNull(newDto.getId());
		assertEquals(NAME, newDto.getName());
	}

	@Test
	public void testUpdateSpecialty() {
		String NAME = "Dermatología";
		String UP_NAME = "Dermatología Avanzada";

		SpecialtyDTO dto = SpecialtyDTO.builder().name(NAME).build();
		SpecialtyDTO createdDto = this.specialtyService.create(dto);

		createdDto.setName(UP_NAME);
		SpecialtyDTO updatedDto = this.specialtyService.update(createdDto);

		assertEquals(UP_NAME, updatedDto.getName());
	}

	@Test
	public void testFindSpecialtyByName() {
		String FIND_NAME = "Cardiología";
		SpecialtyDTO dto = SpecialtyDTO.builder().name(FIND_NAME).build();
		this.specialtyService.create(dto);

		var specialties = this.specialtyService.findByName(FIND_NAME);
		assertTrue(specialties.size() > 0);
		assertEquals(FIND_NAME, specialties.get(0).getName());
	}

	@Test
	public void testDeleteSpecialty() {
		SpecialtyDTO dto = SpecialtyDTO.builder().name("Temporal").build();
		SpecialtyDTO newDto = this.specialtyService.create(dto);

		try {
			this.specialtyService.delete(newDto.getId());

			// Verificamos que ya no exista
			assertThrows(Exception.class, () -> {
				this.specialtyService.findById(newDto.getId());
			});
		} catch (Exception e) {
			fail("Error al eliminar: " + e.getMessage());
		}
	}
}