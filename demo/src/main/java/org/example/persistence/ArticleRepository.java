package org.example.persistence;

import org.example.persistence.entity.ArticleEntity;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    ArticleEntity save(ArticleEntity articleEntity);
    Optional<ArticleEntity> findById(Long id);

    List<ArticleEntity> findAll();
    void deleteById(Long id);
}
