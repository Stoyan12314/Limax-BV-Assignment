package org.example.domain;

import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Farmer {
    private Long id;
    private String name;
    private String location;
    private boolean isSpecialStatus;

    private Set<Article> articles; // ArticleDTO is a simplified version of ArticleEntity suitable for data transfer
}
