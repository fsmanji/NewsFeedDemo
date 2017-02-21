package com.example.fsmanji.data.net.response;

import com.example.fsmanji.data.model.ArticleEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fsmanji on 2/20/17.
 */

public class NewsFeedResponse {
    /**
     * "status": "ok",
     "source": "techcrunch",
     "sortBy": "top",
     -"articles": [
     */
    private String status;
    private String source;
    private String sortBy;

    @SerializedName("articles")
    private List<ArticleEntity> articleEntityList;

    public List<ArticleEntity> getArticleEntityList() {
        return articleEntityList;
    }
}
