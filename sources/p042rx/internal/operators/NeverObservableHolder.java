package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.NeverObservableHolder */
/* loaded from: classes6.dex */
public enum NeverObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;
    
    static final Observable<Object> NEVER = Observable.unsafeCreate(INSTANCE);

    @Override // p042rx.functions.Action1
    public void call(Subscriber<? super Object> subscriber) {
    }

    public static <T> Observable<T> instance() {
        return (Observable<T>) NEVER;
    }
}
