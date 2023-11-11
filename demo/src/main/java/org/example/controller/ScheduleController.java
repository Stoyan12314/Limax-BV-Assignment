package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.buisness.ScheduleService;
import org.example.domain.Schedule;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/shcedule")
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> createOrUpdateSchedule(@RequestBody Schedule schedule) {
        Schedule savedSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(savedSchedule);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
