package org.example.domain;


import lombok.*;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {
    private Long Id;
    private int quantity;
    private Location location;
    private Long articleId;
    private Boolean isHighPriority;
    private Boolean IsWeekend;
    private Long farmerId;

}
