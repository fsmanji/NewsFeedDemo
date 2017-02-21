package com.example.fsmanji.domain.interactor;

import com.example.fsmanji.data.model.ArticleEntity;
import com.example.fsmanji.data.repository.ArticleRepository;
import com.example.fsmanji.domain.mapper.ArticleMapper;
import com.example.fsmanji.domain.model.Article;

import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

import static com.example.fsmanji.data.net.NewsService.API_KEY;

/**
 * Created by fsmanji on 2/19/17.
 */

public class GetBuzzFeedNews extends UseCase {

    private ArticleRepository mArticleRepo;
    private static HashMap<String, String> sOptions;
    static {
        //source=buzzfeed&sortBy=top&apiKey=16e77f7ea54c44df88c76af56f004ec9
        sOptions = new HashMap<String, String>();
        sOptions.put("source", "buzzfeed");
        sOptions.put("sortBy", "top");
        sOptions.put("apiKey", API_KEY);
    }

    public GetBuzzFeedNews() {
        mArticleRepo = ArticleRepository.getSharedInstance();
    }

    @Override
    public Observable buildUseCaseObservable() {
        return this.mArticleRepo.getArticleList(sOptions).map(
                new Func1<List<ArticleEntity>, List<Article>>() {
                    @Override
                    public List<Article> call(List<ArticleEntity> entities) {

                        return new ArticleMapper().transform(entities);
                    }
                });
    }
}
