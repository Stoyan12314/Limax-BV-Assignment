package org.example.persistence.impl;


import lombok.AllArgsConstructor;
import org.example.persistence.ArticleRepository;
import org.example.persistence.JPAArticleRepository;
import org.example.persistence.entity.ArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {
    private final JPAArticleRepository jpaArticleRepository;


    @Override
    public ArticleEntity save(ArticleEntity articleEntity) {
        // Add any pre-save logic here
        return jpaArticleRepository.save(articleEntity);
    }

    @Override
    public Optional<ArticleEntity> findById(Long id) {
        // Add any custom logic you need when fetching by ID
        return jpaArticleRepository.findById(id);
    }

    @Override
    public List<ArticleEntity> findAll() {
        // If you need custom logic for fetching all entities
        return jpaArticleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        // Additional logic before deletion, if needed
        jpaArticleRepository.deleteById(id);
    }
}
