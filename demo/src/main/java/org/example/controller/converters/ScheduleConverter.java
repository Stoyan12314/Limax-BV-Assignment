package org.example.controller.converters;

import org.example.domain.Schedule;
import org.example.persistence.entity.ArticleEntity;
import org.example.persistence.entity.FarmerEntity;
import org.example.persistence.entity.ScheduleEntity;

public class ScheduleConverter {
    private ScheduleConverter() {
    }

    // Converts a ScheduleEntity to a Schedule (DTO)
    public static Schedule entityToDto(ScheduleEntity scheduleEntity) {
        if (scheduleEntity == null) {
            return null;
        }

        Schedule scheduleDto = new Schedule();
        scheduleDto.setId(scheduleEntity.getId());
        scheduleDto.setDate(scheduleEntity.getDate());
        scheduleDto.setFarmerId(scheduleEntity.getFarmerId());
        scheduleDto.setInventoryItemId(scheduleEntity.getInventoryItemId());
        scheduleDto.setStatus(scheduleEntity.getStatus());

        return scheduleDto;
    }

    // Converts a Schedule (DTO) to a ScheduleEntity
    public static ScheduleEntity dtoToEntity(Schedule scheduleDto) {


        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(scheduleDto.getId());
        scheduleEntity.setDate(scheduleDto.getDate());
        scheduleEntity.setFarmerId(scheduleDto.getFarmerId());
        scheduleEntity.setStatus(scheduleDto.getStatus());

        return scheduleEntity;
    }
}
