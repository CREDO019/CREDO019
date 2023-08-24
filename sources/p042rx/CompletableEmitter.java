package p042rx;

import p042rx.functions.Cancellable;

/* renamed from: rx.CompletableEmitter */
/* loaded from: classes4.dex */
public interface CompletableEmitter {
    void onCompleted();

    void onError(Throwable th);

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
