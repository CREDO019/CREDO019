package p042rx.internal.operators;

import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.exceptions.Exceptions;
import p042rx.exceptions.OnErrorThrowable;
import p042rx.functions.Func1;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.SingleOnSubscribeMap */
/* loaded from: classes6.dex */
public final class SingleOnSubscribeMap<T, R> implements Single.OnSubscribe<R> {
    final Single<T> source;
    final Func1<? super T, ? extends R> transformer;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleOnSubscribeMap(Single<T> single, Func1<? super T, ? extends R> func1) {
        this.source = single;
        this.transformer = func1;
    }

    public void call(SingleSubscriber<? super R> singleSubscriber) {
        MapSubscriber mapSubscriber = new MapSubscriber(singleSubscriber, this.transformer);
        singleSubscriber.add(mapSubscriber);
        this.source.subscribe(mapSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleOnSubscribeMap$MapSubscriber */
    /* loaded from: classes6.dex */
    public static final class MapSubscriber<T, R> extends SingleSubscriber<T> {
        final SingleSubscriber<? super R> actual;
        boolean done;
        final Func1<? super T, ? extends R> mapper;

        public MapSubscriber(SingleSubscriber<? super R> singleSubscriber, Func1<? super T, ? extends R> func1) {
            this.actual = singleSubscriber;
            this.mapper = func1;
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                this.actual.onSuccess(this.mapper.call(t));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.done = true;
            this.actual.onError(th);
        }
    }
}
