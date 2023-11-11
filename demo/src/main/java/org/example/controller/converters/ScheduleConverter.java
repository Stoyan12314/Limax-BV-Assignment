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
        scheduleDto.setFarmerId(scheduleEntity.getFarmerId()); // Assuming you have farmer's ID in the DTO
        scheduleDto.setArticleId(scheduleEntity.getArticleId()); // Assuming you have article's ID in the DTO
        scheduleDto.setQuantity(scheduleEntity.getQuantity());
        scheduleDto.setStatus(scheduleEntity.getStatus());

        // Additional fields can be set here

        return scheduleDto;
    }

    // Converts a Schedule (DTO) to a ScheduleEntity
    public static ScheduleEntity dtoToEntity(Schedule scheduleDto) {


        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setId(scheduleDto.getId());
        scheduleEntity.setDate(scheduleDto.getDate());
        scheduleEntity.setFarmerId(scheduleDto.getFarmerId()); // Set the provided FarmerEntity
        scheduleEntity.setArticleId(scheduleEntity.getArticleId()); // Set the provided ArticleEntity
        scheduleEntity.setQuantity(scheduleDto.getQuantity());
        scheduleEntity.setStatus(scheduleDto.getStatus());

        // Additional fields can be set here

        return scheduleEntity;
    }
}
