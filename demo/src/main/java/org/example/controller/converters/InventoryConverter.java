package org.example.controller.converters;

import org.example.domain.InventoryItem;
import org.example.domain.Location;
import org.example.persistence.entity.ArticleEntity;
import org.example.persistence.entity.InventoryItemEntity;

public class InventoryConverter {
    private InventoryConverter() {
    }
    // Converts an InventoryItemEntity to an InventoryItem (DTO)
    public static InventoryItem entityToDto(InventoryItemEntity inventoryItemEntity) {
        if (inventoryItemEntity == null) {
            return null;
        }

        InventoryItem inventoryItemDto = new InventoryItem();
        inventoryItemDto.setId(inventoryItemEntity.getId());
        inventoryItemDto.setQuantity(inventoryItemEntity.getQuantity());
        inventoryItemDto.setLocation(inventoryItemEntity.getLocation());
        inventoryItemDto.setArticleId(inventoryItemEntity.getArticleId());


        return inventoryItemDto;
    }

    // Converts an InventoryItem (DTO) to an InventoryItemEntity
    public static InventoryItemEntity dtoToEntity(InventoryItem inventoryItemDto) {
        if (inventoryItemDto == null) {
            return null;
        }


        InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();
        inventoryItemEntity.setId(inventoryItemDto.getId());
        inventoryItemEntity.setLocation(inventoryItemDto.getLocation());
        inventoryItemEntity.setPriority(inventoryItemDto.getIsHighPriority());
        inventoryItemEntity.setIsWeekend(inventoryItemDto.getIsWeekend());
        inventoryItemEntity.setQuantity(inventoryItemDto.getQuantity());
        inventoryItemEntity.setArticleId(inventoryItemDto.getArticleId());


        return inventoryItemEntity;
    }
}
