package org.example.persistence;

import org.example.persistence.entity.InventoryItemEntity;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository {
    List<InventoryItemEntity> findAllHighPriorityItems();
    InventoryItemEntity save(InventoryItemEntity inventoryItemEntity);
    Optional<InventoryItemEntity> findById(Long id);
    InventoryItemEntity update(InventoryItemEntity inventoryItemEntity);
    List<InventoryItemEntity> findAll();
    void deleteById(Long id);
}
