package org.example.controller;

import lombok.AllArgsConstructor;

import org.example.buisness.ArticleService;
import org.example.controller.converters.ArticleRequestConverter;
import org.example.controller.dto.CreateArticleRequest;
import org.example.controller.dto.CreateArticleResponse;
import org.example.domain.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {
    private final ArticleService articleManager;

    @PostMapping
    public ResponseEntity<CreateArticleResponse> createArticle(@RequestBody CreateArticleRequest article) {
        Long savedArticleId = articleManager.saveArticle(ArticleRequestConverter.convertRequest(article));
        CreateArticleResponse response = new CreateArticleResponse();
        response.setId(savedArticleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleManager.getArticle(id);
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleManager.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleManager.deleteArticle(id);
        return ResponseEntity.ok().build();
    }

}
