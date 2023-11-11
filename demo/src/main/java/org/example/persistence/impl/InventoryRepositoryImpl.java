package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.persistence.InventoryRepository;
import org.example.persistence.JPAFarmerRepository;
import org.example.persistence.JPAInventoryRepository;
import org.example.persistence.entity.InventoryItemEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {
    private final JPAInventoryRepository jpaInventoryRepository;

    @Override
    public InventoryItemEntity save(InventoryItemEntity inventoryItemEntity) {
        return jpaInventoryRepository.save(inventoryItemEntity);
    }

    @Override
    public Optional<InventoryItemEntity> findById(Long id) {
        return jpaInventoryRepository.findById(id);
    }

    @Override
    public List<InventoryItemEntity> findAll() {
        return jpaInventoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaInventoryRepository.deleteById(id);
    }

}
