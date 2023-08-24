package p042rx.observers;

import p042rx.CompletableSubscriber;
import p042rx.Subscription;
import p042rx.exceptions.CompositeException;
import p042rx.exceptions.Exceptions;
import p042rx.exceptions.OnCompletedFailedException;
import p042rx.exceptions.OnErrorFailedException;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.observers.SafeCompletableSubscriber */
/* loaded from: classes6.dex */
public final class SafeCompletableSubscriber implements CompletableSubscriber, Subscription {
    final CompletableSubscriber actual;
    boolean done;

    /* renamed from: s */
    Subscription f2587s;

    public SafeCompletableSubscriber(CompletableSubscriber completableSubscriber) {
        this.actual = completableSubscriber;
    }

    @Override // p042rx.CompletableSubscriber
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw new OnCompletedFailedException(th);
        }
    }

    @Override // p042rx.CompletableSubscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaHooks.onError(th);
            return;
        }
        this.done = true;
        try {
            this.actual.onError(th);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            throw new OnErrorFailedException(new CompositeException(th, th2));
        }
    }

    @Override // p042rx.CompletableSubscriber
    public void onSubscribe(Subscription subscription) {
        this.f2587s = subscription;
        try {
            this.actual.onSubscribe(this);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscription.unsubscribe();
            onError(th);
        }
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        this.f2587s.unsubscribe();
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.done || this.f2587s.isUnsubscribed();
    }
}
