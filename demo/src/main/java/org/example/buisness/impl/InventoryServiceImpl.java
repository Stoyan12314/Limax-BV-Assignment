package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.InventoryService;
import org.example.buisness.exceptions.InventoryItemNotFoundException;
import org.example.controller.converters.ArticleConverter;
import org.example.controller.converters.InventoryConverter;
import org.example.domain.Article;
import org.example.domain.InventoryItem;
import org.example.domain.Season;
import org.example.domain.Supplier;
import org.example.persistence.ArticleRepository;
import org.example.persistence.InventoryRepository;
import org.example.persistence.entity.ArticleEntity;
import org.example.persistence.entity.InventoryItemEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ArticleRepository articleRepository;

    private boolean isWeekend() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }
    private Season getCurrentSeason() {
        int month = LocalDate.now().getMonthValue();
        if (month >= 3 && month <= 5) {
            return Season.SPRING;
        } else if (month >= 6 && month <= 8) {
            return Season.SUMMER;
        } else if (month >= 9 && month <= 11) {
            return Season.AUTUMN;
        } else {
            return Season.WINTER;
        }
    }

    private boolean checkIfHighPriority(int quantity, Article article) {
        if (article == null) {
            return false;
        }

        int maxCapacity = 100; // Fixed max capacity for each article
        double thresholdPercentage = 0.2; // Example: 20% of max capacity
        int thresholdQuantity = (int) (maxCapacity * thresholdPercentage);

        boolean isLowStock = quantity < thresholdQuantity;
        boolean isSeasonalDemand = checkSeasonalDemand(article);
        boolean isLongReplenishmentTime = false;
        boolean isUnreliableSupplier = false;

        // Check for null in replenishmentLeadTime and supplierReliability
        if (article.getReplenishmentLeadTime() != null) {
            isLongReplenishmentTime = article.getReplenishmentLeadTime().compareTo(Duration.ofDays(5)) > 0;
        }
        if (article.getSupplierReliability() != null) {
            isUnreliableSupplier = article.getSupplierReliability().equals(Supplier.Low);
        }

        return isLowStock || isSeasonalDemand || isLongReplenishmentTime || isUnreliableSupplier;
    }

    private boolean checkSeasonalDemand(Article article) {
        if (article == null || article.getSeasonalDemand() == null) {
            return false;
        }

        Season currentSeason = getCurrentSeason();
        return article.getSeasonalDemand().contains(currentSeason);
    }
    @Override
    public InventoryItem updateInventoryItem(InventoryItem inventoryItem) {
        Long id = inventoryItem.getId();
        Optional<InventoryItemEntity> existingEntityOpt = inventoryRepository.findById(id);
        if (!existingEntityOpt.isPresent()) {
            throw new InventoryItemNotFoundException("Inventory item not found with id: " + id);
        }

        InventoryItemEntity existingEntity = InventoryConverter.dtoToEntity(inventoryItem);
        List<InventoryItemEntity> all =  inventoryRepository.findAll();

        InventoryItemEntity savedEntity = inventoryRepository.update(existingEntity);
        return InventoryConverter.entityToDto(savedEntity);
    }

    @Override
    public InventoryItem saveInventoryItem(InventoryItem inventoryItem) {
        InventoryItemEntity entity = InventoryConverter.dtoToEntity(inventoryItem);
        Long articleId = entity.getArticleId();

        ArticleEntity articleEntity = articleRepository.findById(articleId)
                .orElseThrow(() -> new InventoryItemNotFoundException("Inventory item not found with id: " + articleId));
        Boolean check = checkIfHighPriority(entity.getQuantity(), ArticleConverter.entityToDto(articleEntity));
        entity.setPriority(check);
        entity.setIsWeekend(isWeekend());
        entity = inventoryRepository.save(entity);
        return InventoryConverter.entityToDto(entity);
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
