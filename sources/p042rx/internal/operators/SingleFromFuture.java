package p042rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.exceptions.Exceptions;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.SingleFromFuture */
/* loaded from: classes6.dex */
public final class SingleFromFuture<T> implements Single.OnSubscribe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.future = future;
        this.timeout = j;
        this.unit = timeUnit;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Object obj;
        Future<? extends T> future = this.future;
        singleSubscriber.add(Subscriptions.from(future));
        try {
            long j = this.timeout;
            if (j == 0) {
                obj = (T) future.get();
            } else {
                obj = (T) future.get(j, this.unit);
            }
            singleSubscriber.onSuccess(obj);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}
