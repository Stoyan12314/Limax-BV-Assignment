package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "farmer_id", nullable = false)
    private Long farmerId;

    private LocalDate date;

    @JoinColumn(name = "InventortItem")
    private Long inventoryItemId;


    private Status status;

}
