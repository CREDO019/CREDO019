package com.polidea.rxandroidble.internal.util;

import p042rx.Observable;

/* loaded from: classes3.dex */
public class ObservableUtil {
    private static final Observable.Transformer<?, ?> IDENTITY_TRANSFORMER = new Observable.Transformer<Object, Object>() { // from class: com.polidea.rxandroidble.internal.util.ObservableUtil.1
        @Override // p042rx.functions.Func1
        public Observable<Object> call(Observable<Object> observable) {
            return observable;
        }
    };

    private ObservableUtil() {
    }

    public static <T> Observable<T> justOnNext(T t) {
        return Observable.never().startWith((Observable) t);
    }

    public static <T> Observable.Transformer<T, T> identityTransformer() {
        return (Observable.Transformer<T, T>) IDENTITY_TRANSFORMER;
    }
}
