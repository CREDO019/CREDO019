package p042rx.observers;

import java.util.Arrays;
import p042rx.Subscriber;
import p042rx.exceptions.CompositeException;
import p042rx.exceptions.Exceptions;
import p042rx.exceptions.OnCompletedFailedException;
import p042rx.exceptions.OnErrorFailedException;
import p042rx.exceptions.OnErrorNotImplementedException;
import p042rx.exceptions.UnsubscribeFailedException;
import p042rx.plugins.RxJavaHooks;
import p042rx.plugins.RxJavaPlugins;

/* renamed from: rx.observers.SafeSubscriber */
/* loaded from: classes6.dex */
public class SafeSubscriber<T> extends Subscriber<T> {
    private final Subscriber<? super T> actual;
    boolean done;

    public SafeSubscriber(Subscriber<? super T> subscriber) {
        super(subscriber);
        this.actual = subscriber;
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        UnsubscribeFailedException unsubscribeFailedException;
        if (this.done) {
            return;
        }
        this.done = true;
        try {
            this.actual.onCompleted();
            try {
                unsubscribe();
            } finally {
            }
        } catch (Throwable th) {
            try {
                Exceptions.throwIfFatal(th);
                RxJavaHooks.onError(th);
                throw new OnCompletedFailedException(th.getMessage(), th);
            } catch (Throwable th2) {
                try {
                    unsubscribe();
                    throw th2;
                } finally {
                }
            }
        }
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.done) {
            return;
        }
        this.done = true;
        _onError(th);
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        try {
            if (this.done) {
                return;
            }
            this.actual.onNext(t);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, this);
        }
    }

    protected void _onError(Throwable th) {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        try {
            this.actual.onError(th);
            try {
                unsubscribe();
            } catch (Throwable th2) {
                RxJavaHooks.onError(th2);
                throw new OnErrorFailedException(th2);
            }
        } catch (OnErrorNotImplementedException e) {
            try {
                unsubscribe();
                throw e;
            } catch (Throwable th3) {
                RxJavaHooks.onError(th3);
                throw new OnErrorNotImplementedException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(th, th3)));
            }
        } catch (Throwable th4) {
            RxJavaHooks.onError(th4);
            try {
                unsubscribe();
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(th, th4)));
            } catch (Throwable th5) {
                RxJavaHooks.onError(th5);
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(th, th4, th5)));
            }
        }
    }

    public Subscriber<? super T> getActual() {
        return this.actual;
    }
}
