package p042rx.internal.operators;

import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.functions.Action0;

/* renamed from: rx.internal.operators.OperatorDelay */
/* loaded from: classes6.dex */
public final class OperatorDelay<T> implements Observable.Operator<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorDelay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        subscriber.add(createWorker);
        return new C56141(subscriber, createWorker, subscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorDelay$1 */
    /* loaded from: classes6.dex */
    public class C56141 extends Subscriber<T> {
        boolean done;
        final /* synthetic */ Subscriber val$child;
        final /* synthetic */ Scheduler.Worker val$worker;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56141(Subscriber subscriber, Scheduler.Worker worker, Subscriber subscriber2) {
            super(subscriber);
            this.val$worker = worker;
            this.val$child = subscriber2;
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.1
                @Override // p042rx.functions.Action0
                public void call() {
                    if (C56141.this.done) {
                        return;
                    }
                    C56141.this.done = true;
                    C56141.this.val$child.onCompleted();
                }
            }, OperatorDelay.this.delay, OperatorDelay.this.unit);
        }

        @Override // p042rx.Observer
        public void onError(final Throwable th) {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.2
                @Override // p042rx.functions.Action0
                public void call() {
                    if (C56141.this.done) {
                        return;
                    }
                    C56141.this.done = true;
                    C56141.this.val$child.onError(th);
                    C56141.this.val$worker.unsubscribe();
                }
            });
        }

        @Override // p042rx.Observer
        public void onNext(final T t) {
            this.val$worker.schedule(new Action0() { // from class: rx.internal.operators.OperatorDelay.1.3
                /* JADX WARN: Multi-variable type inference failed */
                @Override // p042rx.functions.Action0
                public void call() {
                    if (C56141.this.done) {
                        return;
                    }
                    C56141.this.val$child.onNext(t);
                }
            }, OperatorDelay.this.delay, OperatorDelay.this.unit);
        }
    }
}
