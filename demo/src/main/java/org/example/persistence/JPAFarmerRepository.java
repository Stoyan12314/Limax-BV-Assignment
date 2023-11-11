package org.example.persistence;

import org.example.persistence.entity.FarmerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  JPAFarmerRepository extends JpaRepository<FarmerEntity, Long> {
}
