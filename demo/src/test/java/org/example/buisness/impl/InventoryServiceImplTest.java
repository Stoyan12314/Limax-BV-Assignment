package org.example.buisness.impl;

import org.example.buisness.exceptions.InventoryItemNotFoundException;
import org.example.domain.InventoryItem;
import org.example.persistence.InventoryRepository;
import org.example.persistence.entity.InventoryItemEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    @DisplayName("Should save and return an inventory item")
    void saveInventoryItemTest() {
        InventoryItem inventoryItem = new InventoryItem(); // Prepare your InventoryItem DTO
        InventoryItemEntity inventoryItemEntity = new InventoryItemEntity(); // Assume this is what dtoToEntity would return

        when(inventoryRepository.save(any(InventoryItemEntity.class))).thenReturn(inventoryItemEntity);

        InventoryItem savedItem = inventoryService.saveInventoryItem(inventoryItem);

        assertNotNull(savedItem);
        verify(inventoryRepository).save(any(InventoryItemEntity.class));
    }

    @Test
    @DisplayName("Should return an inventory item by ID")
    void getInventoryItemByIdTest() {
        Long id = 1L;
        InventoryItemEntity inventoryItemEntity = new InventoryItemEntity();
        inventoryItemEntity.setId(id);

        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventoryItemEntity));

        InventoryItem result = inventoryService.getInventoryItemById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    @DisplayName("Should throw InventoryItemNotFoundException when item is not found")
    void getInventoryItemNotFoundTest() {
        Long id = 1L;
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(InventoryItemNotFoundException.class, () -> inventoryService.getInventoryItemById(id));
    }

    @Test
    @DisplayName("Should get all inventory items")
    void getAllInventoryItemsTest() {
        List<InventoryItemEntity> inventoryItemEntities = Collections.singletonList(new InventoryItemEntity());
        when(inventoryRepository.findAll()).thenReturn(inventoryItemEntities);

        List<InventoryItem> results = inventoryService.getAllInventoryItems();

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    @DisplayName("Should delete an inventory item by ID")
    void deleteInventoryItemTest() {
        Long id = 1L;
        doNothing().when(inventoryRepository).deleteById(id);

        inventoryService.deleteInventoryItem(id);

        verify(inventoryRepository).deleteById(id);
    }
}