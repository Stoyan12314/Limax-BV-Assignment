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
        articleDto.setName(articleEntity.getName());
        articleDto.setDescription(articleEntity.getDescription());
        articleDto.setSeasonalDemand(articleEntity.getSeasonalDemand());
        articleDto.setSupplierReliability(articleEntity.getSupplierReliability());

        articleDto.setReplenishmentLeadTime(articleEntity.getReplenishmentLeadTime());
        return articleDto;
    }

    public static ArticleEntity dtoToEntity(Article articleDto) {
        if (articleDto == null) {
            return null;
        }
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setName(articleDto.getName());
        articleEntity.setDescription(articleDto.getDescription());
        return articleEntity;
    }
}
