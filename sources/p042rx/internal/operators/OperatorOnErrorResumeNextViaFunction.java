package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.internal.producers.ProducerArbiter;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.OperatorOnErrorResumeNextViaFunction */
/* loaded from: classes6.dex */
public final class OperatorOnErrorResumeNextViaFunction<T> implements Observable.Operator<T, T> {
    final Func1<? super Throwable, ? extends Observable<? extends T>> resumeFunction;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(final Func1<? super Throwable, ? extends T> func1) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.1
            @Override // p042rx.functions.Func1
            public Observable<? extends T> call(Throwable th) {
                return Observable.just(Func1.this.call(th));
            }
        });
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(final Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.2
            @Override // p042rx.functions.Func1
            public Observable<? extends T> call(Throwable th) {
                return Observable.this;
            }
        });
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(final Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<>(new Func1<Throwable, Observable<? extends T>>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.3
            @Override // p042rx.functions.Func1
            public Observable<? extends T> call(Throwable th) {
                if (th instanceof Exception) {
                    return Observable.this;
                }
                return Observable.error(th);
            }
        });
    }

    public OperatorOnErrorResumeNextViaFunction(Func1<? super Throwable, ? extends Observable<? extends T>> func1) {
        this.resumeFunction = func1;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final ProducerArbiter producerArbiter = new ProducerArbiter();
        final SerialSubscription serialSubscription = new SerialSubscription();
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4
            private boolean done;
            long produced;

            @Override // p042rx.Observer
            public void onCompleted() {
                if (this.done) {
                    return;
                }
                this.done = true;
                subscriber.onCompleted();
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                if (this.done) {
                    Exceptions.throwIfFatal(th);
                    RxJavaHooks.onError(th);
                    return;
                }
                this.done = true;
                try {
                    unsubscribe();
                    Subscriber<T> subscriber3 = new Subscriber<T>() { // from class: rx.internal.operators.OperatorOnErrorResumeNextViaFunction.4.1
                        @Override // p042rx.Observer
                        public void onNext(T t) {
                            subscriber.onNext(t);
                        }

                        @Override // p042rx.Observer
                        public void onError(Throwable th2) {
                            subscriber.onError(th2);
                        }

                        @Override // p042rx.Observer
                        public void onCompleted() {
                            subscriber.onCompleted();
                        }

                        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
                        public void setProducer(Producer producer) {
                            producerArbiter.setProducer(producer);
                        }
                    };
                    serialSubscription.set(subscriber3);
                    long j = this.produced;
                    if (j != 0) {
                        producerArbiter.produced(j);
                    }
                    OperatorOnErrorResumeNextViaFunction.this.resumeFunction.call(th).unsafeSubscribe(subscriber3);
                } catch (Throwable th2) {
                    Exceptions.throwOrReport(th2, subscriber);
                }
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                if (this.done) {
                    return;
                }
                this.produced++;
                subscriber.onNext(t);
            }

            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                producerArbiter.setProducer(producer);
            }
        };
        serialSubscription.set(subscriber2);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        return subscriber2;
    }
}
