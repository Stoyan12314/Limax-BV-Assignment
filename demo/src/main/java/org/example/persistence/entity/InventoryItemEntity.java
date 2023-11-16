package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.Location;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "article_id", nullable = false)
    private Long articleId;
    private Boolean priority;
    private Boolean isWeekend;
    private int quantity;
    private Location location;
}
