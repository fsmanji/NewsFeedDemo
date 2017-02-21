package com.example.fsmanji.domain.interactor;

import com.example.fsmanji.data.model.ArticleEntity;
import com.example.fsmanji.data.repository.ArticleRepository;
import com.example.fsmanji.domain.mapper.ArticleMapper;
import com.example.fsmanji.domain.model.Article;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by fsmanji on 2/20/17.
 */

public class GetArticleDetail extends UseCase {

    private ArticleRepository mArticleRepo;
    private String articleId;

    public GetArticleDetail(String id) {
        mArticleRepo = ArticleRepository.getSharedInstance();
        articleId = id;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return mArticleRepo.getAriticleEntity(articleId).map(new Func1<ArticleEntity, Article>() {
            @Override
            public Article call(ArticleEntity entity) {
                return new ArticleMapper().transform(entity);
            }
        });
    }
}
