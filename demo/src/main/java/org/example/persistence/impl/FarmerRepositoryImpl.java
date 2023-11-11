package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.persistence.FarmerRepository;
import org.example.persistence.JPAFarmerRepository;
import org.example.persistence.entity.FarmerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FarmerRepositoryImpl implements FarmerRepository {
    private final JPAFarmerRepository jpaFarmerRepository;
    @Override
    public FarmerEntity save(FarmerEntity farmerEntity) {
        return jpaFarmerRepository.save(farmerEntity);
    }

    @Override
    public Optional<FarmerEntity> findById(Long id) {
        return jpaFarmerRepository.findById(id);
    }

    @Override
    public List<FarmerEntity> findAll() {
        return jpaFarmerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaFarmerRepository.deleteById(id);
    }
}
