package p042rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p042rx.Scheduler;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.functions.Action0;

/* renamed from: rx.internal.operators.SingleDelay */
/* loaded from: classes6.dex */
public final class SingleDelay<T> implements Single.OnSubscribe<T> {
    final long delay;
    final Scheduler scheduler;
    final Single.OnSubscribe<T> source;
    final TimeUnit unit;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public SingleDelay(Single.OnSubscribe<T> onSubscribe, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.source = onSubscribe;
        this.scheduler = scheduler;
        this.delay = j;
        this.unit = timeUnit;
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        ObserveOnSingleSubscriber observeOnSingleSubscriber = new ObserveOnSingleSubscriber(singleSubscriber, createWorker, this.delay, this.unit);
        singleSubscriber.add(createWorker);
        singleSubscriber.add(observeOnSingleSubscriber);
        this.source.call(observeOnSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.SingleDelay$ObserveOnSingleSubscriber */
    /* loaded from: classes6.dex */
    public static final class ObserveOnSingleSubscriber<T> extends SingleSubscriber<T> implements Action0 {
        final SingleSubscriber<? super T> actual;
        final long delay;
        Throwable error;
        final TimeUnit unit;
        T value;

        /* renamed from: w */
        final Scheduler.Worker f2568w;

        public ObserveOnSingleSubscriber(SingleSubscriber<? super T> singleSubscriber, Scheduler.Worker worker, long j, TimeUnit timeUnit) {
            this.actual = singleSubscriber;
            this.f2568w = worker;
            this.delay = j;
            this.unit = timeUnit;
        }

        @Override // p042rx.SingleSubscriber
        public void onSuccess(T t) {
            this.value = t;
            this.f2568w.schedule(this, this.delay, this.unit);
        }

        @Override // p042rx.SingleSubscriber
        public void onError(Throwable th) {
            this.error = th;
            this.f2568w.schedule(this, this.delay, this.unit);
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
                this.f2568w.unsubscribe();
            }
        }
    }
}
