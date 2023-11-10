package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String location; // Can be an Enum for predefined locations
    private boolean isSpecialStatus;

    // If there's a relationship with articles
    @OneToMany(mappedBy = "farmer")
    private Set<ArticleEntity> articles;

}
