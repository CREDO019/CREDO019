package p042rx;

import p042rx.functions.Cancellable;

/* renamed from: rx.Emitter */
/* loaded from: classes4.dex */
public interface Emitter<T> extends Observer<T> {

    /* renamed from: rx.Emitter$BackpressureMode */
    /* loaded from: classes4.dex */
    public enum BackpressureMode {
        NONE,
        ERROR,
        BUFFER,
        DROP,
        LATEST
    }

    long requested();

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}
