package com.example.fsmanji.data.net;

import com.example.fsmanji.data.net.response.NewsFeedResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by fsmanji on 2/20/17.
 */

public interface NewsService {
    public static final String BASE_URL = "https://newsapi.org/";
    final String API_KEY = "16e77f7ea54c44df88c76af56f004ec9";
    @Headers( "Content-Type: application/json" )

    @GET("/v1/articles")
    Observable<NewsFeedResponse> getNewsFeed (@QueryMap Map<String, String> options);

}
