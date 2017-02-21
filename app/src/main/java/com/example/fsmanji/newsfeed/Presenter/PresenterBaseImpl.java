package com.example.fsmanji.newsfeed.Presenter;

import com.example.fsmanji.domain.interactor.UseCase;

import rx.Subscription;

/**
 * Created by fsmanji on 2/20/17.
 */

public abstract class PresenterBaseImpl implements Presenter {

    protected UseCase mUseCase;

    protected Subscription subscription;

    public abstract UseCase onCreateUseCase();

    PresenterBaseImpl() {
        mUseCase = onCreateUseCase();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
