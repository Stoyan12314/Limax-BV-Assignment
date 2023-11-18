package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.buisness.InventoryService;
import org.example.controller.converters.InventoryItemRequestConverter;
import org.example.controller.dto.CreateInventoryItemRequest;
import org.example.domain.InventoryItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody CreateInventoryItemRequest inventoryItem) {
        InventoryItem savedInventoryItem = inventoryService.saveInventoryItem(InventoryItemRequestConverter.convert(inventoryItem));
        return ResponseEntity.ok(savedInventoryItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
        InventoryItem inventoryItem = inventoryService.getInventoryItemById(id);
        return ResponseEntity.ok(inventoryItem);
    }

    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = inventoryService.getAllInventoryItems();
        return ResponseEntity.ok(inventoryItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long id, @RequestBody CreateInventoryItemRequest updateRequest) {
        InventoryItem updatedInventoryItem = InventoryItemRequestConverter.convert(updateRequest);
        updatedInventoryItem.setId(id); // Set the ID from the path variable to ensure correct item is updated
        updatedInventoryItem = inventoryService.updateInventoryItem(updatedInventoryItem);
        return ResponseEntity.ok(updatedInventoryItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.ok().build();
    }
}
