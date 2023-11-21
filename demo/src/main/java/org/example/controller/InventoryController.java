package org.example.controller;


import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.example.buisness.InventoryService;
import org.example.controller.converters.InventoryItemRequestConverter;
import org.example.controller.dto.CreateInventoryItemRequest;
import org.example.domain.InventoryItem;
import org.example.security.isauthenticated.IsAuthenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/inventory")
@AllArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    @IsAuthenticated
    @RolesAllowed("ROLE_regular")
    @PostMapping
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody CreateInventoryItemRequest inventoryItem) {
        InventoryItem savedInventoryItem = inventoryService.saveInventoryItem(InventoryItemRequestConverter.convert(inventoryItem));
        return ResponseEntity.ok(savedInventoryItem);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_regular")
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
        InventoryItem inventoryItem = inventoryService.getInventoryItemById(id);
        return ResponseEntity.ok(inventoryItem);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_regular")
    @GetMapping
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = inventoryService.getAllInventoryItems();
        return ResponseEntity.ok(inventoryItems);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_regular")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long id, @RequestBody CreateInventoryItemRequest updateRequest) {
        InventoryItem updatedInventoryItem = InventoryItemRequestConverter.convert(updateRequest);
        updatedInventoryItem.setId(id); // Set the ID from the path variable to ensure correct item is updated
        updatedInventoryItem = inventoryService.updateInventoryItem(updatedInventoryItem);
        return ResponseEntity.ok(updatedInventoryItem);
    }

    @IsAuthenticated
    @RolesAllowed("ROLE_regular")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.ok().build();
    }
}
