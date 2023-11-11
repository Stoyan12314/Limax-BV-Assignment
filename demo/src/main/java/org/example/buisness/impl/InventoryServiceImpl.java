package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.InventoryService;
import org.example.buisness.exceptions.InventoryItemNotFoundException;
import org.example.controller.converters.InventoryConverter;
import org.example.domain.InventoryItem;
import org.example.persistence.InventoryRepository;
import org.example.persistence.entity.InventoryItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {


    private final InventoryRepository inventoryRepository;

    @Override
    public InventoryItem saveInventoryItem(InventoryItem inventoryItem) {
        InventoryItemEntity entity = InventoryConverter.dtoToEntity(inventoryItem);
        entity = inventoryRepository.save(entity);
        return inventoryItem;
    }

    @Override
    public InventoryItem getInventoryItemById(Long id) {
        InventoryItemEntity entity = inventoryRepository.findById(id)
                .orElseThrow(() -> new InventoryItemNotFoundException("Inventory item not found with id: " + id));
        return InventoryConverter.entityToDto(entity);
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryRepository.findAll().stream()
                .map(InventoryConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }

}
