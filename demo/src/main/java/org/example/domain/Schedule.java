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

    // Depending on your needs, this could be just the farmer ID, or a simplified version of FarmerEntity
    private Long farmerId; // Replace with Long if you only need the farmer ID

    private LocalDate date;

    // Similar to farmer, this could be just the article ID, or a simplified version of ArticleEntity
    private Long articleId; // Replace with Long if you only need the article ID

    private int quantity; // Optional
    private String status; // Can be a string or an Enum depending on how you want to represent it in the API

}
