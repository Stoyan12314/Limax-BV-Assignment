package org.example.persistence;

import org.example.persistence.entity.FarmerEntity;
import org.example.persistence.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
}
