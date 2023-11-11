package org.example.buisness.impl;

import org.example.buisness.exceptions.ArticleNotFoundException;
import org.example.controller.converters.ArticleConverter;
import org.example.domain.Article;
import org.example.persistence.ArticleRepository;
import org.example.persistence.entity.ArticleEntity;
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
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    @DisplayName("Should save and return an article")
    void saveArticleTest() {
        Article article = new Article(); // Prepare your Article DTO

        // Stubbing with a lenient matcher
        when(articleRepository.save(any(ArticleEntity.class))).thenReturn(new ArticleEntity());

        Article savedArticle = articleService.saveArticle(article);

        assertNotNull(savedArticle);
        // Verify with the same lenient matcher
        verify(articleRepository).save(any(ArticleEntity.class));
    }

    @Test
    @DisplayName("Should return an article by ID")
    void getArticleByIdTest() {
        Long id = 1L;
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(id);

        when(articleRepository.findById(id)).thenReturn(Optional.of(articleEntity));

        Article result = articleService.getArticle(id);

        assertNotNull(result);

        assertEquals(id, result.getId());

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
        ArticleEntity articleEntity = new ArticleEntity();
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