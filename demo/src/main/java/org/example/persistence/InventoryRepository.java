package org.example.persistence;

import org.example.persistence.entity.InventoryItemEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository {
    InventoryItemEntity save(InventoryItemEntity inventoryItemEntity);
    Optional<InventoryItemEntity> findById(Long id);
    List<InventoryItemEntity> findAll();
    void deleteById(Long id);
}
