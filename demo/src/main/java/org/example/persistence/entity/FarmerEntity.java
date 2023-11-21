package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.InventoryItem;
import org.example.domain.Location;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FarmerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private boolean isSpecialStatus;
    private Long scheduleId;

    @JoinColumn(name = "inventoryItem_id", nullable = false)
    private Long inventoryItemId;

}
