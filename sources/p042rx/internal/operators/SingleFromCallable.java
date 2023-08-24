package p042rx.internal.operators;

import java.util.concurrent.Callable;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.exceptions.Exceptions;

/* renamed from: rx.internal.operators.SingleFromCallable */
/* loaded from: classes6.dex */
public final class SingleFromCallable<T> implements Single.OnSubscribe<T> {
    final Callable<? extends T> callable;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        try {
            singleSubscriber.onSuccess((T) this.callable.call());
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}
