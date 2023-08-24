package p042rx.internal.operators;

import p042rx.Scheduler;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.functions.Action0;

/* renamed from: rx.internal.operators.SingleObserveOn */
/* loaded from: classes6.dex */
public final class SingleObserveOn<T> implements Single.OnSubscribe<T> {
    final Scheduler scheduler;
    final Single.OnSubscribe<T> source;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleObserveOn(Single.OnSubscribe<T> onSubscribe, Scheduler scheduler) {
        this.source = onSubscribe;
        this.scheduler = scheduler;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        ObserveOnSingleSubscriber observeOnSingleSubscriber = new ObserveOnSingleSubscriber(singleSubscriber, createWorker);
        singleSubscriber.add(createWorker);
        singleSubscriber.add(observeOnSingleSubscriber);
        this.source.call(observeOnSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleObserveOn$ObserveOnSingleSubscriber */
    /* loaded from: classes6.dex */
    public static final class ObserveOnSingleSubscriber<T> extends SingleSubscriber<T> implements Action0 {
        final SingleSubscriber<? super T> actual;
        Throwable error;
        T value;

        /* renamed from: w */
        final Scheduler.Worker f2569w;

        public ObserveOnSingleSubscriber(SingleSubscriber<? super T> singleSubscriber, Scheduler.Worker worker) {
            this.actual = singleSubscriber;
            this.f2569w = worker;
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            this.value = t;
            this.f2569w.schedule(this);
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            this.error = th;
            this.f2569w.schedule(this);
        }

        @Override // p042rx.functions.Action0
        public void call() {
            try {
                Throwable th = this.error;
                if (th != null) {
                    this.error = null;
                    this.actual.onError(th);
                } else {
                    T t = this.value;
                    this.value = null;
                    this.actual.onSuccess(t);
                }
            } finally {
                this.f2569w.unsubscribe();
            }
        }
    }
}
