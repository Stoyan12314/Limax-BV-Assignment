package org.example.buisness;


import org.example.domain.Article;

import java.util.List;

public interface ArticleService {

    Long saveArticle(Article request);
    Article getArticle(Long id);
    List<Article> getAllArticles();
    void deleteArticle(Long id);

}
