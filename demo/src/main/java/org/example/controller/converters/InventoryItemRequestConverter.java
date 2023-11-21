package org.example.controller.converters;

import org.example.controller.dto.CreateInventoryItemRequest;
import org.example.domain.InventoryItem;

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
