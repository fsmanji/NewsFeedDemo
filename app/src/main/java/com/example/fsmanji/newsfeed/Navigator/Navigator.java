package com.example.fsmanji.newsfeed.Navigator;

import android.content.Context;
import android.content.Intent;

import com.example.fsmanji.newsfeed.ArticleDetailActivity;
import com.example.fsmanji.newsfeed.ArticleListActivity;

/**
 * Created by fsmanji on 2/20/17.
 */

public class Navigator {
    public static void navigateToArticleDetailView(Context context, String articleId) {
        Intent intent = ArticleDetailActivity.getCallingIntent(context, articleId);
        context.startActivity(intent);
    }

    public static void navigateToArticleListView(Context context) {
        Intent intent = ArticleListActivity.getCallingIntent(context);
        context.startActivity(intent);
    }
}
