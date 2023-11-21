package org.example.persistence;

import org.example.persistence.entity.FarmerEntity;
import org.example.persistence.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JPAInventoryRepository extends JpaRepository<InventoryItemEntity, Long> {
    @Query("SELECT i FROM InventoryItemEntity i WHERE i.priority = true")
    Optional<InventoryItemEntity> findHighPriorityItem();
}
