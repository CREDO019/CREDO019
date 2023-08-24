package p042rx.internal.operators;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.FuncN;
import p042rx.observers.SerializedSubscriber;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OperatorWithLatestFromMany */
/* loaded from: classes6.dex */
public final class OperatorWithLatestFromMany<T, R> implements Observable.OnSubscribe<R> {
    final FuncN<R> combiner;
    final Observable<T> main;
    final Observable<?>[] others;
    final Iterable<Observable<?>> othersIterable;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OperatorWithLatestFromMany(Observable<T> observable, Observable<?>[] observableArr, Iterable<Observable<?>> iterable, FuncN<R> funcN) {
        this.main = observable;
        this.others = observableArr;
        this.othersIterable = iterable;
        this.combiner = funcN;
    }

    public void call(Subscriber<? super R> subscriber) {
        int r3;
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        Observable<?>[] observableArr = this.others;
        int r2 = 0;
        if (observableArr != null) {
            r3 = observableArr.length;
        } else {
            observableArr = new Observable[8];
            int r4 = 0;
            for (Observable<?> observable : this.othersIterable) {
                if (r4 == observableArr.length) {
                    observableArr = (Observable[]) Arrays.copyOf(observableArr, (r4 >> 2) + r4);
                }
                observableArr[r4] = observable;
                r4++;
            }
            r3 = r4;
        }
        WithLatestMainSubscriber withLatestMainSubscriber = new WithLatestMainSubscriber(subscriber, this.combiner, r3);
        serializedSubscriber.add(withLatestMainSubscriber);
        while (r2 < r3) {
            if (serializedSubscriber.isUnsubscribed()) {
                return;
            }
            int r5 = r2 + 1;
            WithLatestOtherSubscriber withLatestOtherSubscriber = new WithLatestOtherSubscriber(withLatestMainSubscriber, r5);
            withLatestMainSubscriber.add(withLatestOtherSubscriber);
            observableArr[r2].unsafeSubscribe(withLatestOtherSubscriber);
            r2 = r5;
        }
        this.main.unsafeSubscribe(withLatestMainSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorWithLatestFromMany$WithLatestMainSubscriber */
    /* loaded from: classes6.dex */
    public static final class WithLatestMainSubscriber<T, R> extends Subscriber<T> {
        static final Object EMPTY = new Object();
        final Subscriber<? super R> actual;
        final FuncN<R> combiner;
        final AtomicReferenceArray<Object> current;
        boolean done;
        final AtomicInteger ready;

        public WithLatestMainSubscriber(Subscriber<? super R> subscriber, FuncN<R> funcN, int r4) {
            this.actual = subscriber;
            this.combiner = funcN;
            AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(r4 + 1);
            for (int r3 = 0; r3 <= r4; r3++) {
                atomicReferenceArray.lazySet(r3, EMPTY);
            }
            this.current = atomicReferenceArray;
            this.ready = new AtomicInteger(r4);
            request(0L);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.ready.get() == 0) {
                AtomicReferenceArray<Object> atomicReferenceArray = this.current;
                int length = atomicReferenceArray.length();
                atomicReferenceArray.lazySet(0, t);
                Object[] objArr = new Object[atomicReferenceArray.length()];
                for (int r2 = 0; r2 < length; r2++) {
                    objArr[r2] = atomicReferenceArray.get(r2);
                }
                try {
                    this.actual.onNext(this.combiner.call(objArr));
                    return;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    onError(th);
                    return;
                }
            }
            request(1L);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.done = true;
            unsubscribe();
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            unsubscribe();
            this.actual.onCompleted();
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            super.setProducer(producer);
            this.actual.setProducer(producer);
        }

        void innerNext(int r2, Object obj) {
            if (this.current.getAndSet(r2, obj) == EMPTY) {
                this.ready.decrementAndGet();
            }
        }

        void innerError(int r1, Throwable th) {
            onError(th);
        }

        void innerComplete(int r2) {
            if (this.current.get(r2) == EMPTY) {
                onCompleted();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorWithLatestFromMany$WithLatestOtherSubscriber */
    /* loaded from: classes6.dex */
    public static final class WithLatestOtherSubscriber extends Subscriber<Object> {
        final int index;
        final WithLatestMainSubscriber<?, ?> parent;

        public WithLatestOtherSubscriber(WithLatestMainSubscriber<?, ?> withLatestMainSubscriber, int r2) {
            this.parent = withLatestMainSubscriber;
            this.index = r2;
        }

        @Override // p042rx.Observer
        public void onNext(Object obj) {
            this.parent.innerNext(this.index, obj);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.parent.innerError(this.index, th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.parent.innerComplete(this.index);
        }
    }
}
