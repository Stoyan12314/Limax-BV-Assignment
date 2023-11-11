package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;


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
    private Long article;

    private int quantity;
    private String location; // Can be an Enum for predefined locations
}
