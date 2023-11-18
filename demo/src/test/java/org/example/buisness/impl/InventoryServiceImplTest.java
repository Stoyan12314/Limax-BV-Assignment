package org.example.buisness.impl;


import org.example.buisness.exceptions.InventoryItemNotFoundException;
import org.example.controller.converters.InventoryConverter;
import org.example.domain.InventoryItem;
import org.example.domain.Location;
import org.example.domain.Season;
import org.example.domain.Supplier;
import org.example.persistence.ArticleRepository;
import org.example.persistence.InventoryRepository;
import org.example.persistence.entity.ArticleEntity;
import org.example.persistence.entity.InventoryItemEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class InventoryServiceImplTest {




    @Test
    @DisplayName("Should save and return an inventory item")
    void saveInventoryItemTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);

        Clock clock = mock(Clock.class);
        // Configure the mocked Clock
        Instant fixedInstant = Instant.parse("2023-01-01T10:00:00Z"); // Example instant
        ZoneId fixedZoneId = ZoneOffset.UTC; // Example zone
        when(clock.instant()).thenReturn(fixedInstant);
        when(clock.getZone()).thenReturn(fixedZoneId);
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(
                inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage
        );
        ArticleEntity mockArticleEntity = new ArticleEntity();
        mockArticleEntity.setId(2L);
        mockArticleEntity.setName("Test");
        mockArticleEntity.setDescription("Test Test");
        Set<Season> season = new HashSet<>();
        season.add(Season.SUMMER);
        season.add(Season.WINTER);
        season.add(Season.AUTUMN);

        mockArticleEntity.setSeasonalDemand(season);
        mockArticleEntity.setSupplierReliability(Supplier.Low);

        mockArticleEntity.setReplenishmentLeadTime(Duration.ofDays(2));

        InventoryItem inventoryItem = InventoryItem.builder()
                .Id(1L)
                .quantity(50)
                .location(Location.EastLocation)
                .articleId(2L)
                .isHighPriority(true)
                .IsWeekend(false)
                .farmerId(3L)
                .build();
        InventoryItemEntity inventoryEntity = InventoryItemEntity.builder()
                .id(1L)
                .articleId(2L)
                .priority(true)
                .isWeekend(false)
                .quantity(50)
                .location(Location.EastLocation)
                .build();
        when(articleRepository.findById(2L)).thenReturn(Optional.of(mockArticleEntity));

        when(inventoryRepository.save(any(InventoryItemEntity.class))).thenReturn(inventoryEntity);

        InventoryItem savedItem = inventoryService.saveInventoryItem(inventoryItem);


        assertNotNull(savedItem);
        verify(inventoryRepository).save(any(InventoryItemEntity.class));
        verify(articleRepository).findById(2L);
    }

    @Test
    @DisplayName("Should throw exception when saving inventory item with non-existent article")
    void saveInventoryItemWithNonExistentArticleTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);

        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);
        long nonExistentArticleId = 99L;
        InventoryItem inventoryItem = new InventoryItem(1L, 50, Location.EastLocation, nonExistentArticleId, true, false, 3L);

        when(articleRepository.findById(nonExistentArticleId)).thenReturn(Optional.empty());

        assertThrows(InventoryItemNotFoundException.class, () -> inventoryService.saveInventoryItem(inventoryItem));

        verify(articleRepository).findById(nonExistentArticleId);
        verify(inventoryRepository, never()).save(any(InventoryItemEntity.class));
    }



    @Test
    @DisplayName("Should throw InventoryItemNotFoundException when item ID is invalid")
    void getInventoryItemByInvalidIdTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);

        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);
        long invalidId = -1L;
        when(inventoryRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(InventoryItemNotFoundException.class, () -> inventoryService.getInventoryItemById(invalidId));

        verify(inventoryRepository).findById(invalidId);
    }
    @Test
    @DisplayName("Should handle deletion of non-existent inventory item")
    void deleteNonExistentInventoryItemTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);
        long nonExistentItemId = 99L;
        doNothing().when(inventoryRepository).deleteById(nonExistentItemId);

        assertDoesNotThrow(() -> inventoryService.deleteInventoryItem(nonExistentItemId));

        verify(inventoryRepository).deleteById(nonExistentItemId);
    }

    @Test
    @DisplayName("Should throw InventoryItemNotFoundException when item does not exist")
    void getInventoryItemNotFoundTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage

        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);

        long id = 1L;
        when(inventoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(InventoryItemNotFoundException.class, () -> inventoryService.getInventoryItemById(id));
    }

    @Test
    @DisplayName("Should return an inventory item by ID")
    void getInventoryItemByIdTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);

        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage
        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);

        long id = 1L;
        InventoryItemEntity inventoryEntity = new InventoryItemEntity();
        when(inventoryRepository.findById(id)).thenReturn(Optional.of(inventoryEntity));

        InventoryItem result = inventoryService.getInventoryItemById(id);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Should delete an inventory item by ID")
    void deleteInventoryItemByIdTest() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage
        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);

        long id = 1L;
        doNothing().when(inventoryRepository).deleteById(id);

        assertDoesNotThrow(() -> inventoryService.deleteInventoryItem(id));
        verify(inventoryRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Should get all inventory items")
    void getAllInventoryItemsTest() {
        // Manually create mocks
        InventoryRepository inventoryRepository = Mockito.mock(InventoryRepository.class);
        ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage

        // Create an instance of InventoryServiceImpl with mocks
        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);

        // Create a sample inventory item entity
        InventoryItemEntity inventoryEntity = new InventoryItemEntity();
        inventoryEntity.setId(1L);
        inventoryEntity.setQuantity(50);
        inventoryEntity.setLocation(Location.EastLocation);
        List<InventoryItemEntity> inventoryEntities = Collections.singletonList(inventoryEntity);

        // Mock the behavior of the Inventory Repository
        when(inventoryRepository.findAll()).thenReturn(inventoryEntities);

        // Call the method under test
        List<InventoryItem> results = inventoryService.getAllInventoryItems();

        // Assertions
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        InventoryItem resultItem = results.get(0);
        assertEquals(1L, resultItem.getId());
        assertEquals(50, resultItem.getQuantity());
        assertEquals(Location.EastLocation, resultItem.getLocation());
    }

    @Test
    @DisplayName("Should successfully update an inventory item")
    void shouldUpdateInventoryItem() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage
        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);
        Long itemId = 1L;

        InventoryItem updatedItem = new InventoryItem(itemId, 30, Location.EastLocation, 2L, true, false, 3L);
        InventoryItemEntity existingEntity = new InventoryItemEntity(itemId, 2L, Boolean.TRUE, Boolean.FALSE,  30, Location.WestLocation);
        InventoryItemEntity savedEntity = InventoryConverter.dtoToEntity(updatedItem);

        when(inventoryRepository.findById(itemId)).thenReturn(Optional.of(existingEntity));
        when(inventoryRepository.update(any(InventoryItemEntity.class))).thenReturn(savedEntity);

        InventoryItem result = inventoryService.updateInventoryItem(updatedItem);

        assertNotNull(result);
        assertEquals(30, result.getQuantity());
        assertEquals(Location.EastLocation, result.getLocation());

        // Verify that update method was called with any InventoryItemEntity
        verify(inventoryRepository).update(any(InventoryItemEntity.class));
    }

    @Test
    @DisplayName("Should throw InventoryItemNotFoundException for non-existent item")
    void shouldThrowExceptionForNonExistentItem() {
        InventoryRepository inventoryRepository = mock(InventoryRepository.class);
        ArticleRepository articleRepository = mock(ArticleRepository.class);
        Clock clock = mock(Clock.class); // Mocked Clock object
        int maxCapacity = 100; // Example max capacity value
        double thresholdPercentage = 0.2; // Example threshold percentage
        InventoryServiceImpl inventoryService = new InventoryServiceImpl(inventoryRepository, articleRepository, clock, maxCapacity, thresholdPercentage);
        Long itemId = 99L;
        InventoryItem updatedItem = new InventoryItem(itemId, 30, Location.EastLocation, 2L, true, false, 3L);

        when(inventoryRepository.findById(itemId)).thenReturn(Optional.empty());

        assertThrows(InventoryItemNotFoundException.class, () -> inventoryService.updateInventoryItem(updatedItem));
        verify(inventoryRepository, never()).update(any());
    }
}