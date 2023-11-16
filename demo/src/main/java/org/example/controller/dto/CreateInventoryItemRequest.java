package org.example.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.domain.Location;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInventoryItemRequest {
    @NotNull
    private int quantity;
    @NotNull
    private Location location;
    @NotNull
    private Long articleId;
}
