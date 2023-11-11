package org.example.persistence;

import org.example.persistence.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAArticleRepository extends JpaRepository<ArticleEntity, Long> {
}
