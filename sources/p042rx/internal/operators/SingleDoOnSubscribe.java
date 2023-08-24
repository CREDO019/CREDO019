package p042rx.internal.operators;

import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;

/* renamed from: rx.internal.operators.SingleDoOnSubscribe */
/* loaded from: classes6.dex */
public final class SingleDoOnSubscribe<T> implements Single.OnSubscribe<T> {
    final Action0 onSubscribe;
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleDoOnSubscribe(Single.OnSubscribe<T> onSubscribe, Action0 action0) {
        this.source = onSubscribe;
        this.onSubscribe = action0;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        try {
            this.onSubscribe.call();
            this.source.call(singleSubscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}
