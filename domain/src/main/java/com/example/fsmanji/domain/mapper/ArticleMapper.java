package com.example.fsmanji.domain.mapper;

import com.example.fsmanji.data.model.ArticleEntity;
import com.example.fsmanji.domain.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fsmanji on 2/19/17.
 */

public class ArticleMapper {
    public Article transform(ArticleEntity articleEntity) {
        if (articleEntity == null) return null;

        Article article = new Article();
        article.setAuthor(articleEntity.getAuthor());
        article.setDescription(articleEntity.getDescription());
        article.setTitle(articleEntity.getTitle());
        article.setUrl(articleEntity.getUrl());
        article.setUrlToImage(articleEntity.getUrlToImage());
        article.setPublishedAt(articleEntity.getPublishedAt());

        return article;
    }

    public List<Article> transform(List<ArticleEntity> articleEntities) {
        List<Article> result = new ArrayList<Article>();

        for (ArticleEntity entity:articleEntities) {
            Article article = transform(entity);
            if (article != null) {
                result.add(article);
            }
        }
        return result;
    }
}
