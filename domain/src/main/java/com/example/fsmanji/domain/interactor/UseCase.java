package com.example.fsmanji.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by fsmanji on 2/19/17.
 */

public abstract class UseCase {

    private Subscription subscription = Subscriptions.empty();

    public void execute(Subscriber subscriber) {
        subscription = buildUseCaseObservable().subscribeOn(getExecutorScheduler())
                .observeOn(getObserveScheduler())
                .subscribe(subscriber);
    }

    public void stop() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    public abstract Observable buildUseCaseObservable();

    public Scheduler getObserveScheduler() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler getExecutorScheduler() {
        return Schedulers.io();
    }
}
