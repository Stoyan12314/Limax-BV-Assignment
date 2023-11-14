package org.example.persistence;

import org.example.persistence.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAArticleRepository extends JpaRepository<ArticleEntity, Long> {


}
