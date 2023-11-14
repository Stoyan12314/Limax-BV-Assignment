package org.example.buisness.impl;

import lombok.AllArgsConstructor;
import org.example.buisness.ArticleService;
import org.example.buisness.exceptions.ArticleNotFoundException;
import org.example.controller.ArticleController;
import org.example.controller.converters.ArticleConverter;
import org.example.domain.Article;
import org.example.persistence.ArticleRepository;
import org.example.persistence.entity.ArticleEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;



    private boolean checkIfHighPriority(int quantity) {
        int threshold = 10; // Example threshold
        return quantity < threshold;
    }
    public Article saveArticle(Article newArticle) {
        // findById returns Optional<ArticleEntity>
        Optional<ArticleEntity> optionalEntity = articleRepository.findById(newArticle.getId());

        // Convert Optional<ArticleEntity> to Optional<Article> using map
        Optional<Article> existingArticleOpt = optionalEntity.map(ArticleConverter::entityToDto);

        Article savedArticle;

        if (existingArticleOpt.isPresent()) {
            Article existingArticle = existingArticleOpt.get();
            int totalQuantity = existingArticle.getQuantity() + newArticle.getQuantity();
            existingArticle.setQuantity(totalQuantity);

            boolean isHighPriority = checkIfHighPriority(totalQuantity);
            existingArticle.setHighPriority(isHighPriority);

            // Save and set the returned entity as savedArticle
            savedArticle = ArticleConverter.entityToDto(articleRepository.save(ArticleConverter.dtoToEntity(existingArticle)));
        } else {
            boolean isHighPriority = checkIfHighPriority(newArticle.getQuantity());
            newArticle.setHighPriority(isHighPriority);

            // Save and set the returned entity as savedArticle
            savedArticle = ArticleConverter.entityToDto(articleRepository.save(ArticleConverter.dtoToEntity(newArticle)));
        }

        return savedArticle; // Return the saved article
    }



    // Method to create or update an article
//    @Override
//    public Article saveArticle(Article request) {
//        request.setHighPriority(isPriority(request));
//        ArticleEntity articleEntity = ArticleConverter.dtoToEntity(request);
//        ArticleEntity savedEntity = articleRepository.save(articleEntity);
//        return ArticleConverter.entityToDto(savedEntity);
//    }

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
                .collect(Collectors.toList()); // Stream simplified
    }

    // Method to delete an article
    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
