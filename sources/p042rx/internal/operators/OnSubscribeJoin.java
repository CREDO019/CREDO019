package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.functions.Func2;
import p042rx.observers.SerializedSubscriber;
import p042rx.subscriptions.CompositeSubscription;
import p042rx.subscriptions.SerialSubscription;

/* renamed from: rx.internal.operators.OnSubscribeJoin */
/* loaded from: classes6.dex */
public final class OnSubscribeJoin<TLeft, TRight, TLeftDuration, TRightDuration, R> implements Observable.OnSubscribe<R> {
    final Observable<TLeft> left;
    final Func1<TLeft, Observable<TLeftDuration>> leftDurationSelector;
    final Func2<TLeft, TRight, R> resultSelector;
    final Observable<TRight> right;
    final Func1<TRight, Observable<TRightDuration>> rightDurationSelector;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeJoin(Observable<TLeft> observable, Observable<TRight> observable2, Func1<TLeft, Observable<TLeftDuration>> func1, Func1<TRight, Observable<TRightDuration>> func12, Func2<TLeft, TRight, R> func2) {
        this.left = observable;
        this.right = observable2;
        this.leftDurationSelector = func1;
        this.rightDurationSelector = func12;
        this.resultSelector = func2;
    }

    public void call(Subscriber<? super R> subscriber) {
        new ResultSink(new SerializedSubscriber(subscriber)).run();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink */
    /* loaded from: classes6.dex */
    public final class ResultSink extends HashMap<Integer, TLeft> {
        private static final long serialVersionUID = 3491669543549085380L;
        boolean leftDone;
        int leftId;
        boolean rightDone;
        int rightId;
        final Subscriber<? super R> subscriber;
        final CompositeSubscription group = new CompositeSubscription();
        final Map<Integer, TRight> rightMap = new HashMap();

        HashMap<Integer, TLeft> leftMap() {
            return this;
        }

        public ResultSink(Subscriber<? super R> subscriber) {
            this.subscriber = subscriber;
        }

        public void run() {
            this.subscriber.add(this.group);
            LeftSubscriber leftSubscriber = new LeftSubscriber();
            RightSubscriber rightSubscriber = new RightSubscriber();
            this.group.add(leftSubscriber);
            this.group.add(rightSubscriber);
            OnSubscribeJoin.this.left.unsafeSubscribe(leftSubscriber);
            OnSubscribeJoin.this.right.unsafeSubscribe(rightSubscriber);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$LeftSubscriber */
        /* loaded from: classes6.dex */
        public final class LeftSubscriber extends Subscriber<TLeft> {
            LeftSubscriber() {
            }

            protected void expire(int r3, Subscription subscription) {
                boolean z;
                synchronized (ResultSink.this) {
                    z = ResultSink.this.leftMap().remove(Integer.valueOf(r3)) != null && ResultSink.this.leftMap().isEmpty() && ResultSink.this.leftDone;
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(subscription);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p042rx.Observer
            public void onNext(TLeft tleft) {
                int r2;
                int r1;
                synchronized (ResultSink.this) {
                    ResultSink resultSink = ResultSink.this;
                    r2 = resultSink.leftId;
                    resultSink.leftId = r2 + 1;
                    ResultSink.this.leftMap().put(Integer.valueOf(r2), tleft);
                    r1 = ResultSink.this.rightId;
                }
                try {
                    LeftDurationSubscriber leftDurationSubscriber = new LeftDurationSubscriber(r2);
                    ResultSink.this.group.add(leftDurationSubscriber);
                    OnSubscribeJoin.this.leftDurationSelector.call(tleft).unsafeSubscribe(leftDurationSubscriber);
                    ArrayList<Object> arrayList = new ArrayList();
                    synchronized (ResultSink.this) {
                        for (Map.Entry<Integer, TRight> entry : ResultSink.this.rightMap.entrySet()) {
                            if (entry.getKey().intValue() < r1) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object obj : arrayList) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(tleft, obj));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this) {
                    z = true;
                    ResultSink.this.leftDone = true;
                    if (!ResultSink.this.rightDone && !ResultSink.this.leftMap().isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(this);
            }

            /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$LeftSubscriber$LeftDurationSubscriber */
            /* loaded from: classes6.dex */
            final class LeftDurationSubscriber extends Subscriber<TLeftDuration> {

                /* renamed from: id */
                final int f2560id;
                boolean once = true;

                public LeftDurationSubscriber(int r2) {
                    this.f2560id = r2;
                }

                @Override // p042rx.Observer
                public void onNext(TLeftDuration tleftduration) {
                    onCompleted();
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    LeftSubscriber.this.onError(th);
                }

                @Override // p042rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        LeftSubscriber.this.expire(this.f2560id, this);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$RightSubscriber */
        /* loaded from: classes6.dex */
        public final class RightSubscriber extends Subscriber<TRight> {
            RightSubscriber() {
            }

            void expire(int r3, Subscription subscription) {
                boolean z;
                synchronized (ResultSink.this) {
                    z = ResultSink.this.rightMap.remove(Integer.valueOf(r3)) != null && ResultSink.this.rightMap.isEmpty() && ResultSink.this.rightDone;
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(subscription);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // p042rx.Observer
            public void onNext(TRight tright) {
                int r2;
                int r1;
                synchronized (ResultSink.this) {
                    ResultSink resultSink = ResultSink.this;
                    r2 = resultSink.rightId;
                    resultSink.rightId = r2 + 1;
                    ResultSink.this.rightMap.put(Integer.valueOf(r2), tright);
                    r1 = ResultSink.this.leftId;
                }
                ResultSink.this.group.add(new SerialSubscription());
                try {
                    RightDurationSubscriber rightDurationSubscriber = new RightDurationSubscriber(r2);
                    ResultSink.this.group.add(rightDurationSubscriber);
                    OnSubscribeJoin.this.rightDurationSelector.call(tright).unsafeSubscribe(rightDurationSubscriber);
                    ArrayList<Object> arrayList = new ArrayList();
                    synchronized (ResultSink.this) {
                        for (Map.Entry<Integer, TLeft> entry : ResultSink.this.leftMap().entrySet()) {
                            if (entry.getKey().intValue() < r1) {
                                arrayList.add(entry.getValue());
                            }
                        }
                    }
                    for (Object obj : arrayList) {
                        ResultSink.this.subscriber.onNext(OnSubscribeJoin.this.resultSelector.call(obj, tright));
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                ResultSink.this.subscriber.onError(th);
                ResultSink.this.subscriber.unsubscribe();
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                boolean z;
                synchronized (ResultSink.this) {
                    z = true;
                    ResultSink.this.rightDone = true;
                    if (!ResultSink.this.leftDone && !ResultSink.this.rightMap.isEmpty()) {
                        z = false;
                    }
                }
                if (z) {
                    ResultSink.this.subscriber.onCompleted();
                    ResultSink.this.subscriber.unsubscribe();
                    return;
                }
                ResultSink.this.group.remove(this);
            }

            /* renamed from: rx.internal.operators.OnSubscribeJoin$ResultSink$RightSubscriber$RightDurationSubscriber */
            /* loaded from: classes6.dex */
            final class RightDurationSubscriber extends Subscriber<TRightDuration> {

                /* renamed from: id */
                final int f2561id;
                boolean once = true;

                public RightDurationSubscriber(int r2) {
                    this.f2561id = r2;
                }

                @Override // p042rx.Observer
                public void onNext(TRightDuration trightduration) {
                    onCompleted();
                }

                @Override // p042rx.Observer
                public void onError(Throwable th) {
                    RightSubscriber.this.onError(th);
                }

                @Override // p042rx.Observer
                public void onCompleted() {
                    if (this.once) {
                        this.once = false;
                        RightSubscriber.this.expire(this.f2561id, this);
                    }
                }
            }
        }
    }
}
