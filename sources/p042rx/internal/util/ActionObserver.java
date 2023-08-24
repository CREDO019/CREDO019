package p042rx.internal.util;

import p042rx.Observer;
import p042rx.functions.Action0;
import p042rx.functions.Action1;

/* renamed from: rx.internal.util.ActionObserver */
/* loaded from: classes6.dex */
public final class ActionObserver<T> implements Observer<T> {
    final Action0 onCompleted;
    final Action1<? super Throwable> onError;
    final Action1<? super T> onNext;

    public ActionObserver(Action1<? super T> action1, Action1<? super Throwable> action12, Action0 action0) {
        this.onNext = action1;
        this.onError = action12;
        this.onCompleted = action0;
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.onNext.call(t);
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.onError.call(th);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.onCompleted.call();
    }
}
