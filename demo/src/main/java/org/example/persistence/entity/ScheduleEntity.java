package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
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
    private LocalTime time; // Optional

    @JoinColumn(name = "articleId")
    private Long articleId;

    private int quantity; // Optional
    private String status; // Consider using Enum for predefined statuses

}
