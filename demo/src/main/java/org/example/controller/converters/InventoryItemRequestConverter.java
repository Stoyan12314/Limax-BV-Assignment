package org.example.controller.converters;

import jakarta.validation.constraints.NotNull;
import org.example.controller.dto.CreateInventoryItemRequest;
import org.example.domain.Article;
import org.example.domain.InventoryItem;
import org.example.domain.Location;

public class InventoryItemRequestConverter {
    private InventoryItemRequestConverter() {


    }




    public static InventoryItem convert(CreateInventoryItemRequest request)
    {
        return InventoryItem.builder()
                .quantity(request.getQuantity())
                .location(request.getLocation())
                .articleId(request.getArticleId())
                .build();
    }



}
