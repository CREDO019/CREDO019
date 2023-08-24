package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func0;
import p042rx.observers.SerializedSubscriber;
import p042rx.observers.Subscribers;

/* renamed from: rx.internal.operators.OperatorBufferWithSingleObservable */
/* loaded from: classes6.dex */
public final class OperatorBufferWithSingleObservable<T, TClosing> implements Observable.Operator<List<T>, T> {
    final Func0<? extends Observable<? extends TClosing>> bufferClosingSelector;
    final int initialCapacity;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorBufferWithSingleObservable(Func0<? extends Observable<? extends TClosing>> func0, int r2) {
        this.bufferClosingSelector = func0;
        this.initialCapacity = r2;
    }

    public OperatorBufferWithSingleObservable(final Observable<? extends TClosing> observable, int r3) {
        this.bufferClosingSelector = new Func0<Observable<? extends TClosing>>() { // from class: rx.internal.operators.OperatorBufferWithSingleObservable.1
            @Override // p042rx.functions.Func0, java.util.concurrent.Callable
            public Observable<? extends TClosing> call() {
                return observable;
            }
        };
        this.initialCapacity = r3;
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        try {
            Observable<? extends TClosing> call = this.bufferClosingSelector.call();
            final BufferingSubscriber bufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber(subscriber));
            Subscriber<TClosing> subscriber2 = new Subscriber<TClosing>() { // from class: rx.internal.operators.OperatorBufferWithSingleObservable.2
                @Override // p042rx.Observer
                public void onNext(TClosing tclosing) {
                    bufferingSubscriber.emit();
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    bufferingSubscriber.onError(th);
                }

                @Override // p042rx.Observer
                public void onCompleted() {
                    bufferingSubscriber.onCompleted();
                }
            };
            subscriber.add(subscriber2);
            subscriber.add(bufferingSubscriber);
            call.unsafeSubscribe(subscriber2);
            return bufferingSubscriber;
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
            return Subscribers.empty();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorBufferWithSingleObservable$BufferingSubscriber */
    /* loaded from: classes6.dex */
    public final class BufferingSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        List<T> chunk;
        boolean done;

        public BufferingSubscriber(Subscriber<? super List<T>> subscriber) {
            this.child = subscriber;
            this.chunk = new ArrayList(OperatorBufferWithSingleObservable.this.initialCapacity);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunk.add(t);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunk = null;
                this.child.onError(th);
                unsubscribe();
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    List<T> list = this.chunk;
                    this.chunk = null;
                    this.child.onNext(list);
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.child);
            }
        }

        void emit() {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                List<T> list = this.chunk;
                this.chunk = new ArrayList(OperatorBufferWithSingleObservable.this.initialCapacity);
                try {
                    this.child.onNext(list);
                } catch (Throwable th) {
                    unsubscribe();
                    synchronized (this) {
                        if (this.done) {
                            return;
                        }
                        this.done = true;
                        Exceptions.throwOrReport(th, this.child);
                    }
                }
            }
        }
    }
}
