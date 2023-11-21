package org.example.buisness;

import org.example.domain.Schedule;

import java.util.List;

public interface ScheduleService {
    Schedule createSchedule();
    Schedule getScheduleById(Long id);
    List<Schedule> getAllSchedules();
    Schedule updateSchedule(Schedule scheduleDto);
    void deleteSchedule(Long id);
}
