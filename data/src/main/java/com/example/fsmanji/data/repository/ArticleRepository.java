package com.example.fsmanji.data.repository;

import com.example.fsmanji.data.model.ArticleEntity;
import com.example.fsmanji.data.net.NewsService;
import com.example.fsmanji.data.net.ServiceFactory;
import com.example.fsmanji.data.net.response.NewsFeedResponse;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by fsmanji on 2/20/17.
 */

public class ArticleRepository {
    private InMemoryCache<ArticleEntity> articleCache;
    private InMemoryCache<List<ArticleEntity>> articlesPageCache;
    private NewsService apiClient;

    private static ArticleRepository sInstance;

    private ArticleRepository() {
        apiClient = ServiceFactory.getNewsService();
        articleCache = new InMemoryCache<>();
        articlesPageCache = new InMemoryCache<>();
    }

    public static ArticleRepository getSharedInstance() {
        if (sInstance == null) {
            sInstance = new ArticleRepository();
        }
        return sInstance;
    }

    public rx.Observable getArticleList(final Map options) {
        if (articlesPageCache.contains(options.toString())) {
            return Observable.just(articlesPageCache.get(options.toString()));
        }
        return apiClient.getNewsFeed(options).map(new Func1<NewsFeedResponse, List<ArticleEntity>>() {

            @Override
            public List<ArticleEntity> call(NewsFeedResponse response) {
                List<ArticleEntity> list = response.getArticleEntityList();
                articlesPageCache.set(options.toString(), list);
                for (ArticleEntity entity:list) {
                    articleCache.set(entity.getUrl(), entity);
                }
                return list;
            }
        });
    }

    public rx.Observable getAriticleEntity(String id) {
        if (articleCache.contains(id)) {
            return Observable.just(articleCache.get(id));
        }

        //no network api available
        return null;
    }
}
