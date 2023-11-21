package org.example.persistence;

import org.example.persistence.entity.FarmerEntity;

import java.util.List;
import java.util.Optional;

public interface FarmerRepository {
    Optional<FarmerEntity> returnFreeFarmer();
    FarmerEntity save(FarmerEntity farmerEntity);
    Optional<FarmerEntity> findById(Long id);
    List<FarmerEntity> findAll();
    void deleteById(Long id);
}
