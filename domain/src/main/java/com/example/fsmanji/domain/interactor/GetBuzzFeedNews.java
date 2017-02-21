package com.example.fsmanji.domain.interactor;

import com.example.fsmanji.data.net.NewsService;
import com.example.fsmanji.data.net.ServiceFactory;
import com.example.fsmanji.data.net.response.NewsFeedResponse;
import com.example.fsmanji.domain.mapper.ArticleMapper;
import com.example.fsmanji.domain.model.Article;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

import static com.example.fsmanji.data.net.NewsService.API_KEY;

/**
 * Created by fsmanji on 2/19/17.
 */

public class GetBuzzFeedNews extends UseCase {

    private NewsService serviceApi;
    private static HashMap<String, String> sOptions;
    static {
        //source=buzzfeed&sortBy=top&apiKey=16e77f7ea54c44df88c76af56f004ec9
        sOptions = new HashMap<String, String>();
        sOptions.put("source", "buzzfeed");
        sOptions.put("sortBy", "top");
        sOptions.put("apiKey", API_KEY);
    }

    public GetBuzzFeedNews() {
        this.serviceApi = ServiceFactory.getNewsService();
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return this.serviceApi.getNewsFeed(sOptions).map(
                new Func1<NewsFeedResponse, List<Article>>() {
                    @Override
                    public List<Article> call(NewsFeedResponse response) {
                        if (response == null) return null;
                        return new ArticleMapper().transform(response.getArticleEntityList());
                    }
                }).subscribeOn(getExecutorScheduler())
                .observeOn(getObserveScheduler())
                .subscribe(subscriber);
    }
}
