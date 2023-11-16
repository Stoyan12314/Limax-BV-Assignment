package org.example.domain;

import lombok.*;

import java.time.Duration;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Article {

    private Long articleId;
    private String name;
    private String description;
    private Supplier supplierReliability;
    private Set<Season> seasonalDemand;
    private Duration replenishmentLeadTime;



}
