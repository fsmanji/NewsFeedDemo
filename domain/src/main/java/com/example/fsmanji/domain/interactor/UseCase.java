package com.example.fsmanji.domain.interactor;

import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by fsmanji on 2/19/17.
 */

public abstract class UseCase {

    public abstract Subscription execute(Subscriber subscriber);

    public Scheduler getObserveScheduler() {
        return AndroidSchedulers.mainThread();
    }

    public Scheduler getExecutorScheduler() {
        return Schedulers.io();
    }
}
