package org.example.controller.converters;

import org.example.controller.dto.CreateArticleRequest;
import org.example.domain.Article;
import org.example.domain.Location;

public class ArticleRequestConverter {
    private ArticleRequestConverter() {
    }
    public static Article convertRequest(CreateArticleRequest createArticleRequest)
    {


        return Article.builder()
                .name(createArticleRequest.getName())
                .description(createArticleRequest.getDescription())

                .build();
    }
}
