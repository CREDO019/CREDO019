package p042rx.internal.operators;

import p042rx.Completable;
import p042rx.CompletableSubscriber;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;

/* renamed from: rx.internal.operators.CompletableFlatMapSingleToCompletable */
/* loaded from: classes6.dex */
public final class CompletableFlatMapSingleToCompletable<T> implements Completable.OnSubscribe {
    final Func1<? super T, ? extends Completable> mapper;
    final Single<T> source;

    public CompletableFlatMapSingleToCompletable(Single<T> single, Func1<? super T, ? extends Completable> func1) {
        this.source = single;
        this.mapper = func1;
    }

    @Override // p042rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        SourceSubscriber sourceSubscriber = new SourceSubscriber(completableSubscriber, this.mapper);
        completableSubscriber.onSubscribe(sourceSubscriber);
        this.source.subscribe(sourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.CompletableFlatMapSingleToCompletable$SourceSubscriber */
    /* loaded from: classes6.dex */
    public static final class SourceSubscriber<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        final CompletableSubscriber actual;
        final Func1<? super T, ? extends Completable> mapper;

        public SourceSubscriber(CompletableSubscriber completableSubscriber, Func1<? super T, ? extends Completable> func1) {
            this.actual = completableSubscriber;
            this.mapper = func1;
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                Completable call = this.mapper.call(t);
                if (call == null) {
                    onError(new NullPointerException("The mapper returned a null Completable"));
                } else {
                    call.subscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // p042rx.CompletableSubscriber
        public void onCompleted() {
            this.actual.onCompleted();
        }

        @Override // p042rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            add(subscription);
        }
    }
}
