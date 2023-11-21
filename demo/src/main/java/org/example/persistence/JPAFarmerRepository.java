package org.example.persistence;

import org.example.persistence.entity.FarmerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  JPAFarmerRepository extends JpaRepository<FarmerEntity, Long> {

    @Query("SELECT f FROM FarmerEntity f WHERE f.inventoryItemId IS NULL")
    Optional<FarmerEntity> findFarmersWithoutInventoryItem();

}
