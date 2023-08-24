package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;

/* renamed from: rx.internal.operators.OnSubscribeThrow */
/* loaded from: classes6.dex */
public final class OnSubscribeThrow<T> implements Observable.OnSubscribe<T> {
    private final Throwable exception;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeThrow(Throwable th) {
        this.exception = th;
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.onError(this.exception);
    }
}
