package p042rx.internal.operators;

import p042rx.Subscriber;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.DeferredScalarSubscriberSafe */
/* loaded from: classes6.dex */
public abstract class DeferredScalarSubscriberSafe<T, R> extends DeferredScalarSubscriber<T, R> {
    protected boolean done;

    public DeferredScalarSubscriberSafe(Subscriber<? super R> subscriber) {
        super(subscriber);
    }

    @Override // p042rx.internal.operators.DeferredScalarSubscriber, p042rx.Observer
    public void onError(Throwable th) {
        if (!this.done) {
            this.done = true;
            super.onError(th);
            return;
        }
        RxJavaHooks.onError(th);
    }

    @Override // p042rx.internal.operators.DeferredScalarSubscriber, p042rx.Observer
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        super.onCompleted();
    }
}
