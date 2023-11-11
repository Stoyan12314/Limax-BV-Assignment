package org.example.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Article {
    private Long id;
    private String name;
    private int quantity;
    private boolean isHighPriority;
    private String location; // Can still be a string or an enum depending on how you want to represent it in the API
    private boolean isWeekendSpecial;
    private boolean isNonRegularUserSpecial;
}
