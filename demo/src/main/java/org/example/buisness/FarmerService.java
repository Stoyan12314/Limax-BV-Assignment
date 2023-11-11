package org.example.buisness;

import org.example.domain.Farmer;

import java.util.List;

public interface FarmerService {
    Farmer createFarmer(Farmer farmerDto);
    Farmer getFarmerById(Long id);
    List<Farmer> getAllFarmers();
    Farmer updateFarmer(Long id, Farmer farmerDto);
    void deleteFarmer(Long id);
}
