package p042rx.subjects;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.internal.operators.BackpressureUtils;

/* renamed from: rx.subjects.PublishSubject */
/* loaded from: classes6.dex */
public final class PublishSubject<T> extends Subject<T, T> {
    final PublishSubjectState<T> state;

    public static <T> PublishSubject<T> create() {
        return new PublishSubject<>(new PublishSubjectState());
    }

    protected PublishSubject(PublishSubjectState<T> publishSubjectState) {
        super(publishSubjectState);
        this.state = publishSubjectState;
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.state.onError(th);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    @Override // p042rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error != null;
    }

    public boolean hasCompleted() {
        return this.state.get() == PublishSubjectState.TERMINATED && this.state.error == null;
    }

    public Throwable getThrowable() {
        if (this.state.get() == PublishSubjectState.TERMINATED) {
            return this.state.error;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.PublishSubject$PublishSubjectState */
    /* loaded from: classes6.dex */
    public static final class PublishSubjectState<T> extends AtomicReference<PublishSubjectProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final PublishSubjectProducer[] EMPTY = new PublishSubjectProducer[0];
        static final PublishSubjectProducer[] TERMINATED = new PublishSubjectProducer[0];
        private static final long serialVersionUID = -7568940796666027140L;
        Throwable error;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public PublishSubjectState() {
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> subscriber) {
            PublishSubjectProducer<T> publishSubjectProducer = new PublishSubjectProducer<>(this, subscriber);
            subscriber.add(publishSubjectProducer);
            subscriber.setProducer(publishSubjectProducer);
            if (add(publishSubjectProducer)) {
                if (publishSubjectProducer.isUnsubscribed()) {
                    remove(publishSubjectProducer);
                    return;
                }
                return;
            }
            Throwable th = this.error;
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onCompleted();
            }
        }

        boolean add(PublishSubjectProducer<T> publishSubjectProducer) {
            PublishSubjectProducer<T>[] publishSubjectProducerArr;
            PublishSubjectProducer[] publishSubjectProducerArr2;
            do {
                publishSubjectProducerArr = get();
                if (publishSubjectProducerArr == TERMINATED) {
                    return false;
                }
                int length = publishSubjectProducerArr.length;
                publishSubjectProducerArr2 = new PublishSubjectProducer[length + 1];
                System.arraycopy(publishSubjectProducerArr, 0, publishSubjectProducerArr2, 0, length);
                publishSubjectProducerArr2[length] = publishSubjectProducer;
            } while (!compareAndSet(publishSubjectProducerArr, publishSubjectProducerArr2));
            return true;
        }

        void remove(PublishSubjectProducer<T> publishSubjectProducer) {
            PublishSubjectProducer<T>[] publishSubjectProducerArr;
            PublishSubjectProducer[] publishSubjectProducerArr2;
            do {
                publishSubjectProducerArr = get();
                if (publishSubjectProducerArr == TERMINATED || publishSubjectProducerArr == EMPTY) {
                    return;
                }
                int length = publishSubjectProducerArr.length;
                int r2 = -1;
                int r4 = 0;
                while (true) {
                    if (r4 >= length) {
                        break;
                    } else if (publishSubjectProducerArr[r4] == publishSubjectProducer) {
                        r2 = r4;
                        break;
                    } else {
                        r4++;
                    }
                }
                if (r2 < 0) {
                    return;
                }
                if (length == 1) {
                    publishSubjectProducerArr2 = EMPTY;
                } else {
                    PublishSubjectProducer[] publishSubjectProducerArr3 = new PublishSubjectProducer[length - 1];
                    System.arraycopy(publishSubjectProducerArr, 0, publishSubjectProducerArr3, 0, r2);
                    System.arraycopy(publishSubjectProducerArr, r2 + 1, publishSubjectProducerArr3, r2, (length - r2) - 1);
                    publishSubjectProducerArr2 = publishSubjectProducerArr3;
                }
            } while (!compareAndSet(publishSubjectProducerArr, publishSubjectProducerArr2));
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            for (PublishSubjectProducer<T> publishSubjectProducer : get()) {
                publishSubjectProducer.onNext(t);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            ArrayList arrayList = null;
            for (PublishSubjectProducer<T> publishSubjectProducer : getAndSet(TERMINATED)) {
                try {
                    publishSubjectProducer.onError(th);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            for (PublishSubjectProducer<T> publishSubjectProducer : getAndSet(TERMINATED)) {
                publishSubjectProducer.onCompleted();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.PublishSubject$PublishSubjectProducer */
    /* loaded from: classes6.dex */
    public static final class PublishSubjectProducer<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        private static final long serialVersionUID = 6451806817170721536L;
        final Subscriber<? super T> actual;
        final PublishSubjectState<T> parent;
        long produced;

        public PublishSubjectProducer(PublishSubjectState<T> publishSubjectState, Subscriber<? super T> subscriber) {
            this.parent = publishSubjectState;
            this.actual = subscriber;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            long j2;
            if (BackpressureUtils.validate(j)) {
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                } while (!compareAndSet(j2, BackpressureUtils.addCap(j2, j)));
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
            }
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            long j = get();
            if (j != Long.MIN_VALUE) {
                long j2 = this.produced;
                if (j != j2) {
                    this.produced = j2 + 1;
                    this.actual.onNext(t);
                    return;
                }
                unsubscribe();
                this.actual.onError(new MissingBackpressureException("PublishSubject: could not emit value due to lack of requests"));
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.actual.onError(th);
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (get() != Long.MIN_VALUE) {
                this.actual.onCompleted();
            }
        }
    }
}