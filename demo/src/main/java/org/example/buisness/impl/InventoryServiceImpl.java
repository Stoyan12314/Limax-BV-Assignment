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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Clock;
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
    private final Clock clock;
    @Value("${inventory.max-capacity}")
    private int maxCapacity;

    @Value("${inventory.threshold-percentage}")
    private double thresholdPercentage;

    private boolean isWeekend() {
        DayOfWeek today = LocalDate.now(clock).getDayOfWeek();
        return today == DayOfWeek.SATURDAY || today == DayOfWeek.SUNDAY;
    }

    private Season getCurrentSeason() {
        int month = LocalDate.now(clock).getMonthValue();
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

        int thresholdQuantity = (int) (maxCapacity * thresholdPercentage);

        boolean isLowStock = quantity < thresholdQuantity;
        boolean isSeasonalDemand = checkSeasonalDemand(article);
        boolean isLongReplenishmentTime = Optional.ofNullable(article.getReplenishmentLeadTime())
                .map(leadTime -> leadTime.compareTo(Duration.ofDays(5)) > 0)
                .orElse(false);
        boolean isUnreliableSupplier = Optional.ofNullable(article.getSupplierReliability())
                .map(reliability -> reliability.equals(Supplier.Low))
                .orElse(false);

        return isLowStock || isSeasonalDemand || isLongReplenishmentTime || isUnreliableSupplier;
    }

    private boolean checkSeasonalDemand(Article article) {
        return Optional.ofNullable(article)
                .map(Article::getSeasonalDemand)
                .map(seasons -> seasons.contains(getCurrentSeason()))
                .orElse(false);
    }
    @Override
    public InventoryItem updateInventoryItem(InventoryItem inventoryItem) {
        Long id = inventoryItem.getId();
        Optional<InventoryItemEntity> existingEntityOpt = inventoryRepository.findById(id);
        if (!existingEntityOpt.isPresent()) {
            throw new InventoryItemNotFoundException("Inventory item not found with id: " + id);
        }

        InventoryItemEntity existingEntity = InventoryConverter.dtoToEntity(inventoryItem);
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
