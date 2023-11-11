package org.example.persistence;

import org.example.persistence.entity.ScheduleEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
        ScheduleEntity save(ScheduleEntity scheduleEntity);
        Optional<ScheduleEntity> findById(Long id);
        List<ScheduleEntity> findAll();
        void deleteById(Long id);
        }
