package com.example.fsmanji.newsfeed.Presenter;

import com.example.fsmanji.domain.interactor.GetBuzzFeedNews;
import com.example.fsmanji.domain.interactor.UseCase;
import com.example.fsmanji.domain.model.Article;
import com.example.fsmanji.newsfeed.View.ArticleDetailView;
import com.example.fsmanji.newsfeed.View.ViewState;

import rx.Subscriber;

/**
 * Created by fsmanji on 2/20/17.
 */

public class ArticleDetailPresenter extends PresenterBaseImpl {
    private ArticleDetailView mView;
    public ArticleDetailPresenter(ArticleDetailView view) {
        mView = view;
        assert mView != null;
    }

    @Override
    public UseCase onCreateUseCase() {
        return new GetBuzzFeedNews();
    }

    public void getArticle(final String articleId) {
        subscription = mUseCase.execute(new Subscriber<Article>() {
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
            public void onNext(Article article) {
                if (mView != null) {
                    mView.showArticle(article);
                }
            }
        });
    }
}
