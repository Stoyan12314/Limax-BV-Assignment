package org.example.buisness.impl;

import org.example.buisness.exceptions.FarmerNotFoundException;
import org.example.domain.Farmer;
import org.example.persistence.FarmerRepository;
import org.example.persistence.entity.FarmerEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class FarmerServiceImplTest {

    @Mock
    private FarmerRepository farmerRepository;

    @InjectMocks
    private FarmerServiceImpl farmerService;

    @Test
    @DisplayName("Should create and return a farmer")
    void createFarmerTest() {
        Farmer farmer = new Farmer(); // Prepare your Farmer DTO
        FarmerEntity farmerEntity = new FarmerEntity(); // Assume this is what dtoToEntity would return

        when(farmerRepository.save(any(FarmerEntity.class))).thenReturn(farmerEntity);

        Farmer createdFarmer = farmerService.createFarmer(farmer);

        assertNotNull(createdFarmer);
        verify(farmerRepository).save(any(FarmerEntity.class));
    }

    @Test
    @DisplayName("Should return a farmer by ID")
    void getFarmerByIdTest() {
        Long id = 1L;
        FarmerEntity farmerEntity = new FarmerEntity();
        farmerEntity.setId(id);

        when(farmerRepository.findById(id)).thenReturn(Optional.of(farmerEntity));

        Farmer result = farmerService.getFarmerById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("Should throw FarmerNotFoundException when farmer is not found")
    void getFarmerNotFoundTest() {
        Long id = 1L;
        when(farmerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(FarmerNotFoundException.class, () -> farmerService.getFarmerById(id));
    }

    @Test
    @DisplayName("Should get all farmers")
    void getAllFarmersTest() {
        List<FarmerEntity> farmerEntities = Collections.singletonList(new FarmerEntity());
        when(farmerRepository.findAll()).thenReturn(farmerEntities);

        List<Farmer> results = farmerService.getAllFarmers();

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    @DisplayName("Should update a farmer")
    void updateFarmerTest() {
        Long id = 1L;
        Farmer farmer = new Farmer();
        FarmerEntity farmerEntity = new FarmerEntity();
        farmerEntity.setId(id);

        when(farmerRepository.findById(id)).thenReturn(Optional.of(farmerEntity));
        when(farmerRepository.save(any(FarmerEntity.class))).thenReturn(farmerEntity);

        Farmer updatedFarmer = farmerService.updateFarmer(id, farmer);

        assertNotNull(updatedFarmer);
        verify(farmerRepository).save(any(FarmerEntity.class));
    }

    @Test
    @DisplayName("Should delete a farmer by ID")
    void deleteFarmerTest() {
        Long id = 1L;
        doNothing().when(farmerRepository).deleteById(id);

        farmerService.deleteFarmer(id);

        verify(farmerRepository).deleteById(id);
    }
}