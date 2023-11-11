package org.example.persistence;

import org.example.persistence.entity.FarmerEntity;
import org.example.persistence.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAInventoryRepository extends JpaRepository<InventoryItemEntity, Long> {
}
