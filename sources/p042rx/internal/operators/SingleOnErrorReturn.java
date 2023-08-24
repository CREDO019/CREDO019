package p042rx.internal.operators;

import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;

/* renamed from: rx.internal.operators.SingleOnErrorReturn */
/* loaded from: classes6.dex */
public final class SingleOnErrorReturn<T> implements Single.OnSubscribe<T> {
    final Func1<Throwable, ? extends T> resumeFunction;
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleOnErrorReturn(Single.OnSubscribe<T> onSubscribe, Func1<Throwable, ? extends T> func1) {
        this.source = onSubscribe;
        this.resumeFunction = func1;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        OnErrorReturnsSingleSubscriber onErrorReturnsSingleSubscriber = new OnErrorReturnsSingleSubscriber(singleSubscriber, this.resumeFunction);
        singleSubscriber.add(onErrorReturnsSingleSubscriber);
        this.source.call(onErrorReturnsSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleOnErrorReturn$OnErrorReturnsSingleSubscriber */
    /* loaded from: classes6.dex */
    public static final class OnErrorReturnsSingleSubscriber<T> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final Func1<Throwable, ? extends T> resumeFunction;

        public OnErrorReturnsSingleSubscriber(SingleSubscriber<? super T> singleSubscriber, Func1<Throwable, ? extends T> func1) {
            this.actual = singleSubscriber;
            this.resumeFunction = func1;
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            this.actual.onSuccess(t);
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            try {
                this.actual.onSuccess(this.resumeFunction.call(th));
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.actual.onError(th2);
            }
        }
    }
}
