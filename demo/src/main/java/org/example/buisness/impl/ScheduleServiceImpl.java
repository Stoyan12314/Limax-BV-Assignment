package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.ScheduleService;
import org.example.buisness.exceptions.FarmerNotFoundException;
import org.example.buisness.exceptions.InventoryItemNotFoundException;
import org.example.buisness.exceptions.ScheduleNotFoundException;
import org.example.controller.converters.ScheduleConverter;
import org.example.domain.Schedule;
import org.example.domain.Status;
import org.example.persistence.FarmerRepository;
import org.example.persistence.InventoryRepository;
import org.example.persistence.ScheduleRepository;
import org.example.persistence.entity.FarmerEntity;
import org.example.persistence.entity.InventoryItemEntity;
import org.example.persistence.entity.ScheduleEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final InventoryRepository inventoryRepository;
    private final FarmerRepository farmerRepository;

    private LocalDate determineScheduleDate() {
        return LocalDate.now().plusDays(1);
    }


    private Schedule autoCreateSchedule() {
        Optional<FarmerEntity> freeFarmer = farmerRepository.returnFreeFarmer();
        if(freeFarmer.equals(null))
        {
            throw new FarmerNotFoundException("Farmer not found");
        }


        Optional<InventoryItemEntity> highPriorityItem = inventoryRepository.findAllHighPriorityItems();
        if(highPriorityItem.equals(null))
        {
            throw new InventoryItemNotFoundException("Inventory Item not found");
        }

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setDate(determineScheduleDate());
        scheduleEntity.setFarmerId(freeFarmer.get().getId());
        scheduleEntity.setInventoryItemId(highPriorityItem.get().getId());
        scheduleEntity.setStatus(Status.InProgress);
        scheduleEntity = scheduleRepository.save(scheduleEntity);

        return ScheduleConverter.entityToDto(scheduleEntity);
    }

    @Override
    public Schedule createSchedule() {

        return autoCreateSchedule();
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
        scheduleEntity = scheduleRepository.save(scheduleEntity);
        return ScheduleConverter.entityToDto(scheduleEntity);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}
