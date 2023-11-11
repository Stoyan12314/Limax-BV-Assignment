package org.example.buisness.impl;


import lombok.AllArgsConstructor;
import org.example.buisness.FarmerService;
import org.example.buisness.exceptions.FarmerNotFoundException;
import org.example.controller.converters.FarmerConverter;
import org.example.domain.Farmer;
import org.example.persistence.FarmerRepository;
import org.example.persistence.entity.FarmerEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FarmerServiceImpl implements FarmerService {
    private final FarmerRepository farmerRepository;

    @Override
    public Farmer createFarmer(Farmer farmerDto) {
        FarmerEntity farmerEntity = FarmerConverter.dtoToEntity(farmerDto);
        farmerRepository.save(farmerEntity);
        return farmerDto;
    }

    @Override
    public Farmer getFarmerById(Long id) {
        FarmerEntity farmerEntity = farmerRepository.findById(id)
                .orElseThrow(() -> new FarmerNotFoundException("Farmer not found with id: " + id));
        return FarmerConverter.entityToDto(farmerEntity);
    }


    @Override
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll().stream()
                .map(FarmerConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Farmer updateFarmer(Long id, Farmer farmerDto) {
        FarmerEntity farmerEntity = farmerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Farmer not found"));
        // Update the entity's fields here
        farmerEntity = farmerRepository.save(farmerEntity);
        return farmerDto;
    }

    @Override
    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }


}
