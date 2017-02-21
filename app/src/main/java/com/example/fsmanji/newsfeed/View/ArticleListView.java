package com.example.fsmanji.newsfeed.View;

import com.example.fsmanji.domain.model.Article;

import java.util.List;

/**
 * Created by fsmanji on 2/20/17.
 */

public interface ArticleListView extends View {
    public void showArticleList(List<Article> articleList);
}
