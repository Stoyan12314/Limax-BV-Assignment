package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.ScheduleService;
import org.example.buisness.exceptions.ScheduleNotFoundException;
import org.example.controller.converters.ScheduleConverter;
import org.example.domain.Schedule;
import org.example.persistence.ScheduleRepository;
import org.example.persistence.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    @Override
    public Schedule createSchedule(Schedule scheduleDto) {
        ScheduleEntity scheduleEntity = ScheduleConverter.dtoToEntity(scheduleDto);
        scheduleEntity = scheduleRepository.save(scheduleEntity);
        return scheduleDto;
    }

    @Override
    public Schedule getScheduleById(Long id) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found with id: " + id));
        return ScheduleConverter.entityToDto(scheduleEntity);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Schedule updateSchedule(Schedule scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleDto.getId())
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        // Update scheduleEntity fields here based on scheduleDto
        scheduleEntity = scheduleRepository.save(scheduleEntity);
        return ScheduleConverter.entityToDto(scheduleEntity);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
