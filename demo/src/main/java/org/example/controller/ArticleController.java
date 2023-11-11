package org.example.controller;

import lombok.AllArgsConstructor;

import org.example.buisness.ArticleService;
import org.example.domain.Article;
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
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article savedArticle = articleManager.saveArticle(article);
        return ResponseEntity.ok(savedArticle);
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
