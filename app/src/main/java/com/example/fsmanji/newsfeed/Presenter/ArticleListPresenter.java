package com.example.fsmanji.newsfeed.Presenter;

import com.example.fsmanji.domain.interactor.GetBuzzFeedNews;
import com.example.fsmanji.domain.interactor.UseCase;
import com.example.fsmanji.domain.model.Article;
import com.example.fsmanji.newsfeed.View.ArticleListView;
import com.example.fsmanji.newsfeed.View.ViewState;

import java.util.List;

import rx.Subscriber;

/**
 * Created by fsmanji on 2/20/17.
 */

public class ArticleListPresenter extends PresenterBaseImpl {

    private ArticleListView mView;

    public ArticleListPresenter(ArticleListView view) {
        assert view != null;
        mView = view;
    }

    @Override
    public UseCase onCreateUseCase() {
        return new GetBuzzFeedNews();
    }

    public void loadArticles() {
        mUseCase.execute(new Subscriber<List<Article>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (mView != null) {
                    mView.setViewState(ViewState.Error);
                }
            }

            @Override
            public void onNext(List<Article> articles) {
                if (mView != null) {
                    if ((articles == null || articles.isEmpty()))
                        mView.setViewState(ViewState.Empty);
                    else
                        mView.showArticleList(articles);
                }

            }
        });
    }
}
