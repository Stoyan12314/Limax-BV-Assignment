package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.ArticleService;
import org.example.buisness.exceptions.ArticleNotFoundException;
import org.example.controller.converters.ArticleConverter;
import org.example.domain.Article;
import org.example.persistence.ArticleRepository;
import org.example.persistence.entity.ArticleEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    // Method to create or update an article
    @Override
    public Article saveArticle(Article request) {
        ArticleEntity articleEntity = ArticleConverter.dtoToEntity(request);
        articleEntity = articleRepository.save(articleEntity);
        return request;
    }

    // Method to get an article by ID
    @Override
    public Article getArticle(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with id: " + id));
        return ArticleConverter.entityToDto(articleEntity);
    }

    // Method to get all articles
    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(ArticleConverter::entityToDto)
                .collect(Collectors.toList());
    }

    // Method to delete an article
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }



}
