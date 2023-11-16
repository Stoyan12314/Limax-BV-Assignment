package org.example.domain;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule {
    private Long id;

    private Long farmerId;

    private LocalDate date;


    private Long inventoryItemId;

    private Status status;

}
