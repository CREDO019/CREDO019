package p042rx.plugins;

import p042rx.Completable;

/* renamed from: rx.plugins.RxJavaCompletableExecutionHook */
/* loaded from: classes6.dex */
public abstract class RxJavaCompletableExecutionHook {
    @Deprecated
    public Completable.OnSubscribe onCreate(Completable.OnSubscribe onSubscribe) {
        return onSubscribe;
    }

    @Deprecated
    public Completable.Operator onLift(Completable.Operator operator) {
        return operator;
    }

    @Deprecated
    public Throwable onSubscribeError(Throwable th) {
        return th;
    }

    @Deprecated
    public Completable.OnSubscribe onSubscribeStart(Completable completable, Completable.OnSubscribe onSubscribe) {
        return onSubscribe;
    }
}
