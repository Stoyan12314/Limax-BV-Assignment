package org.example.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.Farmer;
import org.example.domain.Location;
import org.example.domain.Season;
import org.example.domain.Supplier;

import java.time.Duration;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private Supplier supplierReliability;
    private Set<Season> seasonalDemand;
    private Duration replenishmentLeadTime;

}
