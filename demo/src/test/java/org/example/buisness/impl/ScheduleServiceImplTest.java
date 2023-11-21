package org.example.buisness.impl;

import org.example.buisness.exceptions.ScheduleNotFoundException;
import org.example.domain.Location;
import org.example.domain.Schedule;
import org.example.domain.Status;
import org.example.persistence.FarmerRepository;
import org.example.persistence.InventoryRepository;
import org.example.persistence.ScheduleRepository;
import org.example.persistence.entity.FarmerEntity;
import org.example.persistence.entity.InventoryItemEntity;
import org.example.persistence.entity.ScheduleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)

class ScheduleServiceImplTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private FarmerRepository farmerRepository;

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Test
    @DisplayName("Should create and return a schedule")
    void createScheduleTest() {
        FarmerEntity freeFarmer = new FarmerEntity(1L, "John Doe", true, 1L, 1L);
        InventoryItemEntity highPriorityItem = new InventoryItemEntity(1L, 2L, true, true, 50, Location.WestLocation);
        ScheduleEntity scheduleEntity = new ScheduleEntity(1L, 1L, LocalDate.now(), 1L, Status.InProgress);

        when(farmerRepository.returnFreeFarmer()).thenReturn(Optional.of(freeFarmer));
        when(inventoryRepository.findAllHighPriorityItems()).thenReturn(Optional.of(highPriorityItem));
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenReturn(scheduleEntity);

        Schedule result = scheduleService.createSchedule();

        assertNotNull(result);
        assertEquals(freeFarmer.getId(), result.getFarmerId());
        assertEquals(highPriorityItem.getId(), result.getInventoryItemId());
    }

    @Test
    @DisplayName("Should return a schedule by ID")
    void getScheduleByIdTest() {
        Long id = 1L;
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(id);

        when(scheduleRepository.findById(id)).thenReturn(Optional.of(scheduleEntity));

        Schedule result = scheduleService.getScheduleById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("Should throw ScheduleNotFoundException when schedule is not found")
    void getScheduleNotFoundTest() {
        Long id = 1L;
        when(scheduleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ScheduleNotFoundException.class, () -> scheduleService.getScheduleById(id));
    }

    @Test
    @DisplayName("Should get all schedules")
    void getAllSchedulesTest() {
        List<ScheduleEntity> scheduleEntities = Collections.singletonList(new ScheduleEntity());
        when(scheduleRepository.findAll()).thenReturn(scheduleEntities);

        List<Schedule> results = scheduleService.getAllSchedules();

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    @DisplayName("Should update and return a schedule")
    void updateScheduleTest() {

        Schedule scheduleDto = new Schedule(1L, 2L, LocalDate.now(), 3L, Status.InProgress);



        scheduleDto.setId(1L);
        ScheduleEntity scheduleEntity = new ScheduleEntity(1L, 2L, LocalDate.now(), 3L, Status.Done);

        when(scheduleRepository.findById(scheduleDto.getId())).thenReturn(Optional.of(scheduleEntity));
        when(scheduleRepository.save(any(ScheduleEntity.class))).thenReturn(scheduleEntity);

        Schedule updatedSchedule = scheduleService.updateSchedule(scheduleDto);

        assertNotNull(updatedSchedule);
        verify(scheduleRepository).save(any(ScheduleEntity.class));
    }

    @Test
    @DisplayName("Should delete a schedule by ID")
    void deleteScheduleTest() {
        Long id = 1L;
        doNothing().when(scheduleRepository).deleteById(id);

        scheduleService.deleteSchedule(id);

        verify(scheduleRepository).deleteById(id);
    }
}