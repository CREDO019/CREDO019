package p042rx;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Actions;
import p042rx.functions.Func0;
import p042rx.functions.Func1;
import p042rx.functions.Func2;
import p042rx.functions.Func3;
import p042rx.functions.Func4;
import p042rx.functions.Func5;
import p042rx.functions.Func6;
import p042rx.functions.Func7;
import p042rx.functions.Func8;
import p042rx.functions.Func9;
import p042rx.functions.FuncN;
import p042rx.internal.observers.AssertableSubscriberObservable;
import p042rx.internal.operators.CompletableFlatMapSingleToCompletable;
import p042rx.internal.operators.SingleDelay;
import p042rx.internal.operators.SingleDoAfterTerminate;
import p042rx.internal.operators.SingleDoOnEvent;
import p042rx.internal.operators.SingleDoOnSubscribe;
import p042rx.internal.operators.SingleDoOnUnsubscribe;
import p042rx.internal.operators.SingleFromCallable;
import p042rx.internal.operators.SingleFromEmitter;
import p042rx.internal.operators.SingleFromFuture;
import p042rx.internal.operators.SingleFromObservable;
import p042rx.internal.operators.SingleLiftObservableOperator;
import p042rx.internal.operators.SingleObserveOn;
import p042rx.internal.operators.SingleOnErrorReturn;
import p042rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther;
import p042rx.internal.operators.SingleOnSubscribeMap;
import p042rx.internal.operators.SingleOnSubscribeUsing;
import p042rx.internal.operators.SingleOperatorCast;
import p042rx.internal.operators.SingleOperatorOnErrorResumeNext;
import p042rx.internal.operators.SingleOperatorZip;
import p042rx.internal.operators.SingleTakeUntilCompletable;
import p042rx.internal.operators.SingleTakeUntilObservable;
import p042rx.internal.operators.SingleTakeUntilSingle;
import p042rx.internal.operators.SingleTimeout;
import p042rx.internal.operators.SingleToObservable;
import p042rx.internal.util.ScalarSynchronousSingle;
import p042rx.internal.util.UtilityFunctions;
import p042rx.observers.AssertableSubscriber;
import p042rx.observers.SafeSubscriber;
import p042rx.plugins.RxJavaHooks;
import p042rx.schedulers.Schedulers;
import p042rx.singles.BlockingSingle;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.Single */
/* loaded from: classes4.dex */
public class Single<T> {
    final OnSubscribe<T> onSubscribe;

    /* renamed from: rx.Single$OnSubscribe */
    /* loaded from: classes4.dex */
    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    /* renamed from: rx.Single$Transformer */
    /* loaded from: classes4.dex */
    public interface Transformer<T, R> extends Func1<Single<T>, Single<R>> {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Single(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }

    @Deprecated
    protected Single(Observable.OnSubscribe<T> onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(new SingleFromObservable(onSubscribe));
    }

    public static <T> Single<T> create(OnSubscribe<T> onSubscribe) {
        return new Single<>(onSubscribe);
    }

    public final <R> Single<R> lift(Observable.Operator<? extends R, ? super T> operator) {
        return create(new SingleLiftObservableOperator(this.onSubscribe, operator));
    }

    public <R> Single<R> compose(Transformer<? super T, ? extends R> transformer) {
        return (Single) transformer.call(this);
    }

    private static <T> Observable<T> asObservable(Single<T> single) {
        return Observable.unsafeCreate(new SingleToObservable(single.onSubscribe));
    }

    public final <R> Single<R> cast(Class<R> cls) {
        return map(new SingleOperatorCast(cls));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2) {
        return Observable.concat(asObservable(single), asObservable(single2));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8, Single<? extends T> single9) {
        return Observable.concat(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8), asObservable(single9));
    }

    public static <T> Single<T> error(final Throwable th) {
        return create(new OnSubscribe<T>() { // from class: rx.Single.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(SingleSubscriber<? super T> singleSubscriber) {
                singleSubscriber.onError(th);
            }
        });
    }

    public static <T> Single<T> from(Future<? extends T> future) {
        return create(new SingleFromFuture(future, 0L, null));
    }

    public static <T> Single<T> from(Future<? extends T> future, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "unit is null");
        return create(new SingleFromFuture(future, j, timeUnit));
    }

    public static <T> Single<T> from(Future<? extends T> future, Scheduler scheduler) {
        return from(future).subscribeOn(scheduler);
    }

    public static <T> Single<T> fromCallable(Callable<? extends T> callable) {
        return create(new SingleFromCallable(callable));
    }

    public static <T> Single<T> fromEmitter(Action1<SingleEmitter<T>> action1) {
        Objects.requireNonNull(action1, "producer is null");
        return create(new SingleFromEmitter(action1));
    }

    public static <T> Single<T> just(T t) {
        return ScalarSynchronousSingle.create(t);
    }

    public static <T> Single<T> merge(Single<? extends Single<? extends T>> single) {
        if (single instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) single).scalarFlatMap(UtilityFunctions.identity());
        }
        return create(new OnSubscribe<T>() { // from class: rx.Single.2
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                SingleSubscriber<Single<? extends T>> singleSubscriber2 = new SingleSubscriber<Single<? extends T>>() { // from class: rx.Single.2.1
                    @Override // p042rx.SingleSubscriber
                    public /* bridge */ /* synthetic */ void onSuccess(Object obj) {
                        onSuccess((Single) ((Single) obj));
                    }

                    public void onSuccess(Single<? extends T> single2) {
                        single2.subscribe(singleSubscriber);
                    }

                    @Override // p042rx.SingleSubscriber
                    public void onError(Throwable th) {
                        singleSubscriber.onError(th);
                    }
                };
                singleSubscriber.add(singleSubscriber2);
                Single.this.subscribe(singleSubscriber2);
            }
        });
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2) {
        return Observable.merge(asObservable(single), asObservable(single2));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8, Single<? extends T> single9) {
        return Observable.merge(asObservable(single), asObservable(single2), asObservable(single3), asObservable(single4), asObservable(single5), asObservable(single6), asObservable(single7), asObservable(single8), asObservable(single9));
    }

    public static <T> Observable<T> merge(Observable<? extends Single<? extends T>> observable) {
        return merge(observable, Integer.MAX_VALUE);
    }

    public static <T> Observable<T> merge(Observable<? extends Single<? extends T>> observable, int r3) {
        return (Observable<T>) observable.flatMapSingle(UtilityFunctions.identity(), false, r3);
    }

    public static <T> Observable<T> mergeDelayError(Observable<? extends Single<? extends T>> observable) {
        return merge(observable, Integer.MAX_VALUE);
    }

    public static <T> Observable<T> mergeDelayError(Observable<? extends Single<? extends T>> observable, int r3) {
        return (Observable<T>) observable.flatMapSingle(UtilityFunctions.identity(), true, r3);
    }

    public static <T1, T2, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, final Func2<? super T1, ? super T2, ? extends R> func2) {
        return SingleOperatorZip.zip(new Single[]{single, single2}, new FuncN<R>() { // from class: rx.Single.3
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func2.this.call(objArr[0], objArr[1]);
            }
        });
    }

    public static <T1, T2, T3, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, final Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3}, new FuncN<R>() { // from class: rx.Single.4
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func3.this.call(objArr[0], objArr[1], objArr[2]);
            }
        });
    }

    public static <T1, T2, T3, T4, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, final Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func4) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4}, new FuncN<R>() { // from class: rx.Single.5
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func4.this.call(objArr[0], objArr[1], objArr[2], objArr[3]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, final Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func5) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5}, new FuncN<R>() { // from class: rx.Single.6
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func5.this.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, final Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func6) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6}, new FuncN<R>() { // from class: rx.Single.7
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func6.this.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, final Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func7) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7}, new FuncN<R>() { // from class: rx.Single.8
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func7.this.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Single<? extends T8> single8, final Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func8) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7, single8}, new FuncN<R>() { // from class: rx.Single.9
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func8.this.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
            }
        });
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Single<? extends T8> single8, Single<? extends T9> single9, final Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> func9) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7, single8, single9}, new FuncN<R>() { // from class: rx.Single.10
            @Override // p042rx.functions.FuncN
            public R call(Object... objArr) {
                return (R) Func9.this.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
            }
        });
    }

    public static <R> Single<R> zip(Iterable<? extends Single<?>> iterable, FuncN<? extends R> funcN) {
        return SingleOperatorZip.zip(iterableToArray(iterable), funcN);
    }

    public final Single<T> cache() {
        return toObservable().cacheWithInitialCapacity(1).toSingle();
    }

    public final Observable<T> concatWith(Single<? extends T> single) {
        return concat(this, single);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Single<R> flatMap(Func1<? super T, ? extends Single<? extends R>> func1) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarFlatMap(func1);
        }
        return merge(map(func1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> flatMapObservable(Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.merge(asObservable(map(func1)));
    }

    public final Completable flatMapCompletable(Func1<? super T, ? extends Completable> func1) {
        return Completable.create(new CompletableFlatMapSingleToCompletable(this, func1));
    }

    public final <R> Single<R> map(Func1<? super T, ? extends R> func1) {
        return create(new SingleOnSubscribeMap(this, func1));
    }

    public final Observable<T> mergeWith(Single<? extends T> single) {
        return merge(this, single);
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        Objects.requireNonNull(scheduler, "scheduler is null");
        return create(new SingleObserveOn(this.onSubscribe, scheduler));
    }

    public final Single<T> onErrorReturn(Func1<Throwable, ? extends T> func1) {
        return create(new SingleOnErrorReturn(this.onSubscribe, func1));
    }

    public final Single<T> onErrorResumeNext(Single<? extends T> single) {
        return new Single<>(SingleOperatorOnErrorResumeNext.withOther(this, single));
    }

    public final Single<T> onErrorResumeNext(Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new Single<>(SingleOperatorOnErrorResumeNext.withFunction(this, func1));
    }

    public final Subscription subscribe() {
        return subscribe(Actions.empty(), Actions.errorNotImplemented());
    }

    public final Subscription subscribe(Action1<? super T> action1) {
        return subscribe(action1, Actions.errorNotImplemented());
    }

    public final Subscription subscribe(final Action1<? super T> action1, final Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 == null) {
                throw new IllegalArgumentException("onError can not be null");
            }
            return subscribe(new SingleSubscriber<T>() { // from class: rx.Single.11
                @Override // p042rx.SingleSubscriber
                public final void onError(Throwable th) {
                    try {
                        action12.call(th);
                    } finally {
                        unsubscribe();
                    }
                }

                @Override // p042rx.SingleSubscriber
                public final void onSuccess(T t) {
                    try {
                        action1.call(t);
                    } finally {
                        unsubscribe();
                    }
                }
            });
        }
        throw new IllegalArgumentException("onSuccess can not be null");
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        return unsafeSubscribe(subscriber, true);
    }

    private Subscription unsafeSubscribe(Subscriber<? super T> subscriber, boolean z) {
        if (z) {
            try {
                subscriber.onStart();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                try {
                    subscriber.onError(RxJavaHooks.onSingleError(th));
                    return Subscriptions.unsubscribed();
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                    RxJavaHooks.onSingleError(runtimeException);
                    throw runtimeException;
                }
            }
        }
        RxJavaHooks.onSingleStart(this, this.onSubscribe).call(SingleLiftObservableOperator.wrap(subscriber));
        return RxJavaHooks.onSingleReturn(subscriber);
    }

    public final Subscription subscribe(final Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        return subscribe(new SingleSubscriber<T>() { // from class: rx.Single.12
            @Override // p042rx.SingleSubscriber
            public void onSuccess(T t) {
                observer.onNext(t);
                observer.onCompleted();
            }

            @Override // p042rx.SingleSubscriber
            public void onError(Throwable th) {
                observer.onError(th);
            }
        });
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        if (subscriber == null) {
            throw new IllegalArgumentException("observer can not be null");
        }
        subscriber.onStart();
        if (!(subscriber instanceof SafeSubscriber)) {
            return unsafeSubscribe(new SafeSubscriber(subscriber), false);
        }
        return unsafeSubscribe(subscriber, true);
    }

    public final Subscription subscribe(SingleSubscriber<? super T> singleSubscriber) {
        if (singleSubscriber == null) {
            throw new IllegalArgumentException("te is null");
        }
        try {
            RxJavaHooks.onSingleStart(this, this.onSubscribe).call(singleSubscriber);
            return RxJavaHooks.onSingleReturn(singleSubscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            try {
                singleSubscriber.onError(RxJavaHooks.onSingleError(th));
                return Subscriptions.empty();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                RxJavaHooks.onSingleError(runtimeException);
                throw runtimeException;
            }
        }
    }

    public final Single<T> subscribeOn(final Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        return create(new OnSubscribe<T>() { // from class: rx.Single.13
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                final Scheduler.Worker createWorker = scheduler.createWorker();
                singleSubscriber.add(createWorker);
                createWorker.schedule(new Action0() { // from class: rx.Single.13.1
                    @Override // p042rx.functions.Action0
                    public void call() {
                        SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.Single.13.1.1
                            @Override // p042rx.SingleSubscriber
                            public void onSuccess(T t) {
                                try {
                                    singleSubscriber.onSuccess(t);
                                } finally {
                                    createWorker.unsubscribe();
                                }
                            }

                            @Override // p042rx.SingleSubscriber
                            public void onError(Throwable th) {
                                try {
                                    singleSubscriber.onError(th);
                                } finally {
                                    createWorker.unsubscribe();
                                }
                            }
                        };
                        singleSubscriber.add(singleSubscriber2);
                        Single.this.subscribe(singleSubscriber2);
                    }
                });
            }
        });
    }

    public final Single<T> takeUntil(Completable completable) {
        return create(new SingleTakeUntilCompletable(this.onSubscribe, completable));
    }

    public final <E> Single<T> takeUntil(Observable<? extends E> observable) {
        return create(new SingleTakeUntilObservable(this.onSubscribe, observable));
    }

    public final <E> Single<T> takeUntil(Single<? extends E> single) {
        return create(new SingleTakeUntilSingle(this.onSubscribe, single));
    }

    /* renamed from: to */
    public final <R> R m1to(Func1<? super Single<T>, R> func1) {
        return func1.call(this);
    }

    public final Observable<T> toObservable() {
        return asObservable(this);
    }

    public final Completable toCompletable() {
        return Completable.fromSingle(this);
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit) {
        return timeout(j, timeUnit, null, Schedulers.computation());
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout(j, timeUnit, null, scheduler);
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit, Single<? extends T> single) {
        return timeout(j, timeUnit, single, Schedulers.computation());
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit, Single<? extends T> single, Scheduler scheduler) {
        if (single == null) {
            single = defer(new Func0<Single<T>>() { // from class: rx.Single.14
                @Override // p042rx.functions.Func0, java.util.concurrent.Callable
                public Single<T> call() {
                    return Single.error(new TimeoutException());
                }
            });
        }
        return create(new SingleTimeout(this.onSubscribe, j, timeUnit, scheduler, single.onSubscribe));
    }

    public final BlockingSingle<T> toBlocking() {
        return BlockingSingle.from(this);
    }

    public final <T2, R> Single<R> zipWith(Single<? extends T2> single, Func2<? super T, ? super T2, ? extends R> func2) {
        return zip(this, single, func2);
    }

    public final Single<T> doOnError(final Action1<Throwable> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onError is null");
        }
        return create(new SingleDoOnEvent(this, Actions.empty(), new Action1<Throwable>() { // from class: rx.Single.15
            @Override // p042rx.functions.Action1
            public void call(Throwable th) {
                action1.call(th);
            }
        }));
    }

    public final Single<T> doOnEach(final Action1<Notification<? extends T>> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNotification is null");
        }
        return create(new SingleDoOnEvent(this, new Action1<T>() { // from class: rx.Single.16
            @Override // p042rx.functions.Action1
            public void call(T t) {
                action1.call(Notification.createOnNext(t));
            }
        }, new Action1<Throwable>() { // from class: rx.Single.17
            @Override // p042rx.functions.Action1
            public void call(Throwable th) {
                action1.call(Notification.createOnError(th));
            }
        }));
    }

    public final Single<T> doOnSuccess(Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onSuccess is null");
        }
        return create(new SingleDoOnEvent(this, action1, Actions.empty()));
    }

    public final Single<T> doOnSubscribe(Action0 action0) {
        return create(new SingleDoOnSubscribe(this.onSubscribe, action0));
    }

    public final Single<T> delay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return create(new SingleDelay(this.onSubscribe, j, timeUnit, scheduler));
    }

    public final Single<T> delay(long j, TimeUnit timeUnit) {
        return delay(j, timeUnit, Schedulers.computation());
    }

    public static <T> Single<T> defer(final Callable<Single<T>> callable) {
        return create(new OnSubscribe<T>() { // from class: rx.Single.18
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(SingleSubscriber<? super T> singleSubscriber) {
                try {
                    ((Single) callable.call()).subscribe(singleSubscriber);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    singleSubscriber.onError(th);
                }
            }
        });
    }

    public final Single<T> doOnUnsubscribe(Action0 action0) {
        return create(new SingleDoOnUnsubscribe(this.onSubscribe, action0));
    }

    public final Single<T> doAfterTerminate(Action0 action0) {
        return create(new SingleDoAfterTerminate(this, action0));
    }

    static <T> Single<? extends T>[] iterableToArray(Iterable<? extends Single<? extends T>> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return (Single[]) collection.toArray(new Single[collection.size()]);
        }
        Single<? extends T>[] singleArr = new Single[8];
        int r2 = 0;
        for (Single<? extends T> single : iterable) {
            if (r2 == singleArr.length) {
                Single<? extends T>[] singleArr2 = new Single[(r2 >> 2) + r2];
                System.arraycopy(singleArr, 0, singleArr2, 0, r2);
                singleArr = singleArr2;
            }
            singleArr[r2] = single;
            r2++;
        }
        if (singleArr.length == r2) {
            return singleArr;
        }
        Single<? extends T>[] singleArr3 = new Single[r2];
        System.arraycopy(singleArr, 0, singleArr3, 0, r2);
        return singleArr3;
    }

    public final Single<T> retry() {
        return toObservable().retry().toSingle();
    }

    public final Single<T> retry(long j) {
        return toObservable().retry(j).toSingle();
    }

    public final Single<T> retry(Func2<Integer, Throwable, Boolean> func2) {
        return toObservable().retry(func2).toSingle();
    }

    public final Single<T> retryWhen(Func1<Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return toObservable().retryWhen(func1).toSingle();
    }

    public static <T, Resource> Single<T> using(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1) {
        return using(func0, func1, action1, false);
    }

    public static <T, Resource> Single<T> using(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        Objects.requireNonNull(func0, "resourceFactory is null");
        Objects.requireNonNull(func1, "singleFactory is null");
        Objects.requireNonNull(action1, "disposeAction is null");
        return create(new SingleOnSubscribeUsing(func0, func1, action1, z));
    }

    public final Single<T> delaySubscription(Observable<?> observable) {
        Objects.requireNonNull(observable);
        return create(new SingleOnSubscribeDelaySubscriptionOther(this, observable));
    }

    public final Single<T> unsubscribeOn(final Scheduler scheduler) {
        return create(new OnSubscribe<T>() { // from class: rx.Single.19
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super T> singleSubscriber) {
                final SingleSubscriber<T> singleSubscriber2 = new SingleSubscriber<T>() { // from class: rx.Single.19.1
                    @Override // p042rx.SingleSubscriber
                    public void onSuccess(T t) {
                        singleSubscriber.onSuccess(t);
                    }

                    @Override // p042rx.SingleSubscriber
                    public void onError(Throwable th) {
                        singleSubscriber.onError(th);
                    }
                };
                singleSubscriber.add(Subscriptions.create(new Action0() { // from class: rx.Single.19.2
                    @Override // p042rx.functions.Action0
                    public void call() {
                        final Scheduler.Worker createWorker = scheduler.createWorker();
                        createWorker.schedule(new Action0() { // from class: rx.Single.19.2.1
                            @Override // p042rx.functions.Action0
                            public void call() {
                                try {
                                    singleSubscriber2.unsubscribe();
                                } finally {
                                    createWorker.unsubscribe();
                                }
                            }
                        });
                    }
                }));
                Single.this.subscribe(singleSubscriber2);
            }
        });
    }

    public final AssertableSubscriber<T> test() {
        AssertableSubscriberObservable create = AssertableSubscriberObservable.create(Long.MAX_VALUE);
        subscribe((Subscriber) create);
        return create;
    }
}
