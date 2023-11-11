package org.example.controller.converters;


import org.example.domain.Article;
import org.example.persistence.entity.ArticleEntity;


public class ArticleConverter {
    private ArticleConverter() {
    }

    public static Article entityToDto(ArticleEntity articleEntity) {
        if (articleEntity == null) {
            return null;
        }

        Article articleDto = new Article();
        articleDto.setId(articleEntity.getId());
        articleDto.setName(articleEntity.getName());
        articleDto.setQuantity(articleEntity.getQuantity());
        articleDto.setHighPriority(articleEntity.isHighPriority());
        articleDto.setLocation(articleEntity.getLocation());
        articleDto.setWeekendSpecial(articleEntity.isWeekendSpecial());
        articleDto.setNonRegularUserSpecial(articleEntity.isNonRegularUserSpecial());

        // Additional fields can be set here

        return articleDto;
    }

    // Converts an ArticleDTO to an ArticleEntity
    public static ArticleEntity dtoToEntity(Article articleDto) {
        if (articleDto == null) {
            return null;
        }

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(articleDto.getId());
        articleEntity.setName(articleDto.getName());
        articleEntity.setQuantity(articleDto.getQuantity());
        articleEntity.setHighPriority(articleDto.isHighPriority());
        articleEntity.setLocation(articleDto.getLocation());
        articleEntity.setWeekendSpecial(articleDto.isWeekendSpecial());
        articleEntity.setNonRegularUserSpecial(articleDto.isNonRegularUserSpecial());

        // Additional fields can be set here

        return articleEntity;
    }
}
