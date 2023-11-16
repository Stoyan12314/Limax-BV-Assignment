package org.example.controller.dto;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.example.domain.Location;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateArticleRequest {

    @NotNull
    private String name;
    @NotNull
    private String description;
}
