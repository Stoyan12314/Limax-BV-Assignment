package org.example.persistence.impl;

import lombok.AllArgsConstructor;
import org.example.persistence.JPAInventoryRepository;
import org.example.persistence.JPAScheduleRepository;
import org.example.persistence.ScheduleRepository;
import org.example.persistence.entity.ScheduleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JPAScheduleRepository jpaScheduleRepository;

    @Override
    public ScheduleEntity save(ScheduleEntity scheduleEntity) {
        return jpaScheduleRepository.save(scheduleEntity);
    }

    @Override
    public Optional<ScheduleEntity> findById(Long id) {
        return jpaScheduleRepository.findById(id);
    }

    @Override
    public List<ScheduleEntity> findAll() {
        return jpaScheduleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        jpaScheduleRepository.deleteById(id);
    }


}
