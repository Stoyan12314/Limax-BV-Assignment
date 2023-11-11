package org.example.buisness;

import org.example.domain.InventoryItem;

import java.util.List;

public interface InventoryService {
    InventoryItem saveInventoryItem(InventoryItem inventoryItem);
    InventoryItem getInventoryItemById(Long id);
    List<InventoryItem> getAllInventoryItems();
    void deleteInventoryItem(Long id);
}
