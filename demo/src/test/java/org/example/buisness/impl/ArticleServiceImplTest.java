package org.example.buisness.impl;

import org.example.buisness.exceptions.ArticleNotFoundException;
import org.example.domain.Article;
import org.example.domain.Season;
import org.example.domain.Supplier;
import org.example.persistence.ArticleRepository;
import org.example.persistence.entity.ArticleEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    @DisplayName("Should save and return an article")
    void saveArticleTest() {
        // rewrite
//        // Create an Article with data
//        Article article = new Article("Test Article", 10, false, Location.WestLocation, false, false);
//
//        // Create a corresponding ArticleEntity
//        ArticleEntity articleEntity = new ArticleEntity(1L, null, "Test Article", 10, false, Location.WestLocation, false, false);
//
//        // Stubbing to return the entity
//        when(articleRepository.save(any(ArticleEntity.class))).thenReturn(articleEntity);
//
//        Article savedArticle = articleService.saveArticle(article);
//
//        assertNotNull(savedArticle);
//        assertEquals("Test Article", savedArticle.getName());
//        verify(articleRepository).save(any(ArticleEntity.class));
    }

    @Test
    @DisplayName("Should return an article by ID")
    void getArticleByIdTest() {
        Long id = 1L;


        Set<Season> seasonalDemand = new HashSet<>();
        seasonalDemand.add(Season.SUMMER);
        // Create a populated ArticleEntity
        ArticleEntity articleEntity = new ArticleEntity(1L, "Test Article", "something",Supplier.High, seasonalDemand, Duration.ofDays(2) );

        when(articleRepository.findById(id)).thenReturn(Optional.of(articleEntity));

        Article result = articleService.getArticle(id);

        assertNotNull(result);
        assertEquals(id, result.getArticleId());
        assertEquals("Test Article", result.getName());
    }

    @Test
    @DisplayName("Should throw ArticleNotFoundException when article is not found")
    void getArticleNotFoundTest() {
        Long id = 1L;

        when(articleRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ArticleNotFoundException.class, () -> articleService.getArticle(id));
    }

    @Test
    @DisplayName("Should get all articles")
    void getAllArticlesTest() {
        Set<Season> seasonalDemand = new HashSet<>();
        seasonalDemand.add(Season.SUMMER);
        ArticleEntity articleEntity = new ArticleEntity(1L, "Test Article", "something",Supplier.High, seasonalDemand, Duration.ofDays(2) );
        List<ArticleEntity> articleEntities = Collections.singletonList(articleEntity);

        when(articleRepository.findAll()).thenReturn(articleEntities);

        List<Article> results = articleService.getAllArticles();

        assertNotNull(results);
        assertFalse(results.isEmpty());
    }

    @Test
    @DisplayName("Should delete an article by ID")
    void deleteArticleTest() {
        Long id = 1L;

        doNothing().when(articleRepository).deleteById(id);

        articleService.deleteArticle(id);

        verify(articleRepository).deleteById(id);
    }
}