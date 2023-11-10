package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
    private int quantity;
    private boolean isHighPriority;
    private String location; // Can be an enum for predefined locations
    private boolean isWeekendSpecial;
    private boolean isNonRegularUserSpecial;



}
