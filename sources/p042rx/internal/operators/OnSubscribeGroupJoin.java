package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.functions.Func2;
import p042rx.observers.SerializedObserver;
import p042rx.observers.SerializedSubscriber;
import p042rx.subjects.PublishSubject;
import p042rx.subscriptions.CompositeSubscription;
import p042rx.subscriptions.RefCountSubscription;

/* renamed from: rx.internal.operators.OnSubscribeGroupJoin */
/* loaded from: classes6.dex */
public final class OnSubscribeGroupJoin<T1, T2, D1, D2, R> implements Observable.OnSubscribe<R> {
    final Observable<T1> left;
    final Func1<? super T1, ? extends Observable<D1>> leftDuration;
    final Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector;
    final Observable<T2> right;
    final Func1<? super T2, ? extends Observable<D2>> rightDuration;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeGroupJoin(Observable<T1> observable, Observable<T2> observable2, Func1<? super T1, ? extends Observable<D1>> func1, Func1<? super T2, ? extends Observable<D2>> func12, Func2<? super T1, ? super Observable<T2>, ? extends R> func2) {
        this.left = observable;
        this.right = observable2;
        this.leftDuration = func1;
        this.rightDuration = func12;
        this.resultSelector = func2;
    }

    public void call(Subscriber<? super R> subscriber) {
        ResultManager resultManager = new ResultManager(new SerializedSubscriber(subscriber));
        subscriber.add(resultManager);
        resultManager.init();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager */
    /* loaded from: classes6.dex */
    public final class ResultManager extends HashMap<Integer, Observer<T2>> implements Subscription {
        private static final long serialVersionUID = -3035156013812425335L;
        final RefCountSubscription cancel;
        final CompositeSubscription group;
        boolean leftDone;
        int leftIds;
        boolean rightDone;
        int rightIds;
        final Map<Integer, T2> rightMap = new HashMap();
        final Subscriber<? super R> subscriber;

        Map<Integer, Observer<T2>> leftMap() {
            return this;
        }

        public ResultManager(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.group = compositeSubscription;
            this.cancel = new RefCountSubscription(compositeSubscription);
        }

        public void init() {
            LeftObserver leftObserver = new LeftObserver();
            RightObserver rightObserver = new RightObserver();
            this.group.add(leftObserver);
            this.group.add(rightObserver);
            OnSubscribeGroupJoin.this.left.unsafeSubscribe(leftObserver);
            OnSubscribeGroupJoin.this.right.unsafeSubscribe(rightObserver);
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            this.cancel.unsubscribe();
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancel.isUnsubscribed();
        }

        void errorAll(Throwable th) {
            ArrayList<Observer> arrayList;
            synchronized (this) {
                arrayList = new ArrayList(leftMap().values());
                leftMap().clear();
                this.rightMap.clear();
            }
            for (Observer observer : arrayList) {
                observer.onError(th);
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        void errorMain(Throwable th) {
            synchronized (this) {
                leftMap().clear();
                this.rightMap.clear();
            }
            this.subscriber.onError(th);
            this.cancel.unsubscribe();
        }

        void complete(List<Observer<T2>> list) {
            if (list != null) {
                for (Observer<T2> observer : list) {
                    observer.onCompleted();
                }
                this.subscriber.onCompleted();
                this.cancel.unsubscribe();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$LeftObserver */
        /* loaded from: classes6.dex */
        public final class LeftObserver extends Subscriber<T1> {
            LeftObserver() {
            }

            @Override // p042rx.Observer
            public void onNext(T1 t1) {
                int r4;
                ArrayList<Object> arrayList;
                try {
                    PublishSubject create = PublishSubject.create();
                    SerializedObserver serializedObserver = new SerializedObserver(create);
                    synchronized (ResultManager.this) {
                        ResultManager resultManager = ResultManager.this;
                        r4 = resultManager.leftIds;
                        resultManager.leftIds = r4 + 1;
                        ResultManager.this.leftMap().put(Integer.valueOf(r4), serializedObserver);
                    }
                    Observable unsafeCreate = Observable.unsafeCreate(new WindowObservableFunc(create, ResultManager.this.cancel));
                    LeftDurationObserver leftDurationObserver = new LeftDurationObserver(r4);
                    ResultManager.this.group.add(leftDurationObserver);
                    OnSubscribeGroupJoin.this.leftDuration.call(t1).unsafeSubscribe(leftDurationObserver);
                    R call = OnSubscribeGroupJoin.this.resultSelector.call(t1, unsafeCreate);
                    synchronized (ResultManager.this) {
                        arrayList = new ArrayList(ResultManager.this.rightMap.values());
                    }
                    ResultManager.this.subscriber.onNext(call);
                    for (Object obj : arrayList) {
                        serializedObserver.onNext(obj);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (ResultManager.this) {
                    ResultManager.this.leftDone = true;
                    if (ResultManager.this.rightDone) {
                        arrayList = new ArrayList(ResultManager.this.leftMap().values());
                        ResultManager.this.leftMap().clear();
                        ResultManager.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                ResultManager.this.complete(arrayList);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorAll(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$RightObserver */
        /* loaded from: classes6.dex */
        public final class RightObserver extends Subscriber<T2> {
            RightObserver() {
            }

            @Override // p042rx.Observer
            public void onNext(T2 t2) {
                int r2;
                ArrayList<Observer> arrayList;
                try {
                    synchronized (ResultManager.this) {
                        ResultManager resultManager = ResultManager.this;
                        r2 = resultManager.rightIds;
                        resultManager.rightIds = r2 + 1;
                        ResultManager.this.rightMap.put(Integer.valueOf(r2), t2);
                    }
                    RightDurationObserver rightDurationObserver = new RightDurationObserver(r2);
                    ResultManager.this.group.add(rightDurationObserver);
                    OnSubscribeGroupJoin.this.rightDuration.call(t2).unsafeSubscribe(rightDurationObserver);
                    synchronized (ResultManager.this) {
                        arrayList = new ArrayList(ResultManager.this.leftMap().values());
                    }
                    for (Observer observer : arrayList) {
                        observer.onNext(t2);
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                ArrayList arrayList;
                synchronized (ResultManager.this) {
                    ResultManager.this.rightDone = true;
                    if (ResultManager.this.leftDone) {
                        arrayList = new ArrayList(ResultManager.this.leftMap().values());
                        ResultManager.this.leftMap().clear();
                        ResultManager.this.rightMap.clear();
                    } else {
                        arrayList = null;
                    }
                }
                ResultManager.this.complete(arrayList);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorAll(th);
            }
        }

        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$LeftDurationObserver */
        /* loaded from: classes6.dex */
        final class LeftDurationObserver extends Subscriber<D1> {

            /* renamed from: id */
            final int f2558id;
            boolean once = true;

            public LeftDurationObserver(int r2) {
                this.f2558id = r2;
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                Observer<T2> remove;
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this) {
                        remove = ResultManager.this.leftMap().remove(Integer.valueOf(this.f2558id));
                    }
                    if (remove != null) {
                        remove.onCompleted();
                    }
                    ResultManager.this.group.remove(this);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorMain(th);
            }

            @Override // p042rx.Observer
            public void onNext(D1 d1) {
                onCompleted();
            }
        }

        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$ResultManager$RightDurationObserver */
        /* loaded from: classes6.dex */
        final class RightDurationObserver extends Subscriber<D2> {

            /* renamed from: id */
            final int f2559id;
            boolean once = true;

            public RightDurationObserver(int r2) {
                this.f2559id = r2;
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                if (this.once) {
                    this.once = false;
                    synchronized (ResultManager.this) {
                        ResultManager.this.rightMap.remove(Integer.valueOf(this.f2559id));
                    }
                    ResultManager.this.group.remove(this);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                ResultManager.this.errorMain(th);
            }

            @Override // p042rx.Observer
            public void onNext(D2 d2) {
                onCompleted();
            }
        }
    }

    /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$WindowObservableFunc */
    /* loaded from: classes6.dex */
    static final class WindowObservableFunc<T> implements Observable.OnSubscribe<T> {
        final RefCountSubscription refCount;
        final Observable<T> underlying;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public WindowObservableFunc(Observable<T> observable, RefCountSubscription refCountSubscription) {
            this.refCount = refCountSubscription;
            this.underlying = observable;
        }

        public void call(Subscriber<? super T> subscriber) {
            Subscription subscription = this.refCount.get();
            WindowSubscriber windowSubscriber = new WindowSubscriber(subscriber, subscription);
            windowSubscriber.add(subscription);
            this.underlying.unsafeSubscribe(windowSubscriber);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeGroupJoin$WindowObservableFunc$WindowSubscriber */
        /* loaded from: classes6.dex */
        public final class WindowSubscriber extends Subscriber<T> {
            private final Subscription ref;
            final Subscriber<? super T> subscriber;

            public WindowSubscriber(Subscriber<? super T> subscriber, Subscription subscription) {
                super(subscriber);
                this.subscriber = subscriber;
                this.ref = subscription;
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                this.subscriber.onNext(t);
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                this.subscriber.onError(th);
                this.ref.unsubscribe();
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                this.subscriber.onCompleted();
                this.ref.unsubscribe();
            }
        }
    }
}
