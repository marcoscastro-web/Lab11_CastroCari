package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.VetSpecialtyDTO;
import com.tecsup.petclinic.entities.VetSpecialty;
import com.tecsup.petclinic.entities.VetSpecialtyId;
import com.tecsup.petclinic.exceptions.VetSpecialtyNotFoundException;
import com.tecsup.petclinic.mappers.VetSpecialtyMapper;
import com.tecsup.petclinic.repositories.VetSpecialtyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VetSpecialtyServiceImpl implements VetSpecialtyService {

    private final VetSpecialtyRepository vetSpecialtyRepository;
    private final VetSpecialtyMapper vetSpecialtyMapper;

    @Override
    public VetSpecialtyDTO create(VetSpecialtyDTO dto) {
        VetSpecialty entity = vetSpecialtyRepository.save(vetSpecialtyMapper.mapToEntity(dto));
        return vetSpecialtyMapper.mapToDto(entity);
    }

    @Override
    public void delete(VetSpecialtyId id) throws VetSpecialtyNotFoundException {
        findById(id);
        vetSpecialtyRepository.deleteById(id);
    }

    @Override
    public VetSpecialtyDTO findById(VetSpecialtyId id) throws VetSpecialtyNotFoundException {
        Optional<VetSpecialty> entity = vetSpecialtyRepository.findById(id);
        if (!entity.isPresent()) {
            throw new VetSpecialtyNotFoundException("Record not found...!");
        }
        return vetSpecialtyMapper.mapToDto(entity.get());
    }

    @Override
    public List<VetSpecialtyDTO> findByVetId(Integer vetId) {
        return vetSpecialtyRepository.findByVetId(vetId)
                .stream().map(vetSpecialtyMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<VetSpecialtyDTO> findBySpecialtyId(Integer specialtyId) {
        return vetSpecialtyRepository.findBySpecialtyId(specialtyId)
                .stream().map(vetSpecialtyMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<VetSpecialtyDTO> findAll() {
        return vetSpecialtyRepository.findAll().stream()
                .map(vetSpecialtyMapper::mapToDto).collect(Collectors.toList());
    }
}