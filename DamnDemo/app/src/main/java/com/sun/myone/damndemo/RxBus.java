package com.sun.myone.damndemo;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by Sun
 * on 2017/3/6.
 */

public class RxBus {
    private final Subject<Object> _bus;


    private static class RxHolder{
        private static volatile RxBus sRxBus = new RxBus();
    }

    private RxBus() {
        _bus = PublishSubject.create().toSerialized();
    }
    public static RxBus getInstance(){
        return RxHolder.sRxBus;
    }
    public void send(Object o){
        if (hasObserve()) {
            _bus.onNext(o);
        }
    }

    private boolean hasObserve() {
        return _bus.hasObservers();
    }
    public Observable<Object> toObservable(){
        return _bus;
    }
    // 根据传入的事件类型,返回特定类型的eventType的被观察者
    public <T extends Object> Observable<T> toObservable(Class<T> event){
        return _bus.ofType(event);

    }
}

