package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.OwnerDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class OwnerServiceTest {

	@Autowired
	private OwnerService ownerService;

	@Test
	public void testCreateOwner() {
		String FIRST_NAME = "Maria";
		String LAST_NAME = "Lopez";
		String ADDRESS = "Av. Siempre Viva 123";
		String CITY = "Lima";
		String TELEPHONE = "987654321";

		OwnerDTO ownerDTO = OwnerDTO.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.address(ADDRESS)
				.city(CITY)
				.telephone(TELEPHONE)
				.build();

		OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);
		log.info("OWNER CREATED :" + newOwnerDTO.toString());

		assertNotNull(newOwnerDTO.getId());
		assertEquals(FIRST_NAME, newOwnerDTO.getFirstName());
		assertEquals(LAST_NAME, newOwnerDTO.getLastName());
		assertEquals(CITY, newOwnerDTO.getCity());
	}

	@Test
	public void testUpdateOwner() {
		String FIRST_NAME = "Pedro";
		String UP_TELEPHONE = "999888777";

		// Crear
		OwnerDTO ownerDTO = OwnerDTO.builder()
				.firstName(FIRST_NAME)
				.lastName("Perez")
				.telephone("123456789")
				.build();
		OwnerDTO createdOwner = this.ownerService.create(ownerDTO);

		// Actualizar
		createdOwner.setTelephone(UP_TELEPHONE);
		OwnerDTO updatedOwner = this.ownerService.update(createdOwner);

		// Verificar
		assertEquals(UP_TELEPHONE, updatedOwner.getTelephone());
	}

	@Test
	public void testFindOwnerByName() {
		String FIND_NAME = "Ana";
		OwnerDTO ownerDTO = OwnerDTO.builder().firstName(FIND_NAME).lastName("Gomez").build();
		this.ownerService.create(ownerDTO);

		var owners = this.ownerService.findByName(FIND_NAME);
		assertTrue(owners.size() > 0);
		assertEquals(FIND_NAME, owners.get(0).getFirstName());
	}

	@Test
	public void testDeleteOwner() {
		OwnerDTO ownerDTO = OwnerDTO.builder().firstName("Temporal").lastName("ParaBorrar").build();
		OwnerDTO newOwnerDTO = this.ownerService.create(ownerDTO);

		try {
			this.ownerService.delete(newOwnerDTO.getId());

			// Verificar que ya no existe
			assertThrows(Exception.class, () -> {
				this.ownerService.findById(newOwnerDTO.getId());
			});
		} catch (Exception e) {
			fail("Error al eliminar: " + e.getMessage());
		}
	}
}