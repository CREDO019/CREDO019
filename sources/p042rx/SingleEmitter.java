package p042rx;

import p042rx.functions.Cancellable;

/* renamed from: rx.SingleEmitter */
/* loaded from: classes4.dex */
public interface SingleEmitter<T> {
    void onError(Throwable th);

    void onSuccess(T t);

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
