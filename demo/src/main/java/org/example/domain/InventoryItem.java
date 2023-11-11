package org.example.domain;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItem {
    private Long id;
    private String articleName;
    private int quantity;
    private String location;
    private Long articleId;
}
