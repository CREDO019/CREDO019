package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.exceptions.CompositeException;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.exceptions.OnErrorThrowable;
import p042rx.internal.util.RxRingBuffer;
import p042rx.internal.util.ScalarSynchronousObservable;
import p042rx.internal.util.atomic.SpscAtomicArrayQueue;
import p042rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import p042rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import p042rx.internal.util.unsafe.Pow2;
import p042rx.internal.util.unsafe.SpscArrayQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;
import p042rx.subscriptions.CompositeSubscription;

/* renamed from: rx.internal.operators.OperatorMerge */
/* loaded from: classes6.dex */
public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorMerge$HolderNoDelay */
    /* loaded from: classes6.dex */
    public static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);

        HolderNoDelay() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorMerge$HolderDelayErrors */
    /* loaded from: classes6.dex */
    public static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);

        HolderDelayErrors() {
        }
    }

    public static <T> OperatorMerge<T> instance(boolean z) {
        if (z) {
            return (OperatorMerge<T>) HolderDelayErrors.INSTANCE;
        }
        return (OperatorMerge<T>) HolderNoDelay.INSTANCE;
    }

    public static <T> OperatorMerge<T> instance(boolean z, int r3) {
        if (r3 > 0) {
            if (r3 == Integer.MAX_VALUE) {
                return instance(z);
            }
            return new OperatorMerge<>(z, r3);
        }
        throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + r3);
    }

    OperatorMerge(boolean z, int r2) {
        this.delayErrors = z;
        this.maxConcurrent = r2;
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        MergeSubscriber mergeSubscriber = new MergeSubscriber(subscriber, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> mergeProducer = new MergeProducer<>(mergeSubscriber);
        mergeSubscriber.producer = mergeProducer;
        subscriber.add(mergeSubscriber);
        subscriber.setProducer(mergeProducer);
        return mergeSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorMerge$MergeProducer */
    /* loaded from: classes6.dex */
    public static final class MergeProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> mergeSubscriber) {
            this.subscriber = mergeSubscriber;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 <= 0) {
                if (r2 < 0) {
                    throw new IllegalArgumentException("n >= 0 required");
                }
            } else if (get() == Long.MAX_VALUE) {
            } else {
                BackpressureUtils.getAndAddRequest(this, j);
                this.subscriber.emit();
            }
        }

        public long produced(int r3) {
            return addAndGet(-r3);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorMerge$MergeSubscriber */
    /* loaded from: classes6.dex */
    public static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;

        @Override // p042rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        public MergeSubscriber(Subscriber<? super T> subscriber, boolean z, int r3) {
            this.child = subscriber;
            this.delayErrors = z;
            this.maxConcurrent = r3;
            if (r3 == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.scalarEmissionLimit = Math.max(1, r3 >> 1);
            request(r3);
        }

        Queue<Throwable> getOrCreateErrorQueue() {
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (concurrentLinkedQueue == null) {
                synchronized (this) {
                    concurrentLinkedQueue = this.errors;
                    if (concurrentLinkedQueue == null) {
                        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                        this.errors = concurrentLinkedQueue;
                    }
                }
            }
            return concurrentLinkedQueue;
        }

        CompositeSubscription getOrCreateComposite() {
            CompositeSubscription compositeSubscription;
            CompositeSubscription compositeSubscription2 = this.subscriptions;
            if (compositeSubscription2 == null) {
                boolean z = false;
                synchronized (this) {
                    compositeSubscription = this.subscriptions;
                    if (compositeSubscription == null) {
                        CompositeSubscription compositeSubscription3 = new CompositeSubscription();
                        this.subscriptions = compositeSubscription3;
                        compositeSubscription = compositeSubscription3;
                        z = true;
                    }
                }
                if (z) {
                    add(compositeSubscription);
                }
                return compositeSubscription;
            }
            return compositeSubscription2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void onNext(Observable<? extends T> observable) {
            if (observable == null) {
                return;
            }
            if (observable == Observable.empty()) {
                emitEmpty();
            } else if (observable instanceof ScalarSynchronousObservable) {
                tryEmit(((ScalarSynchronousObservable) observable).get());
            } else {
                long j = this.uniqueId;
                this.uniqueId = 1 + j;
                InnerSubscriber innerSubscriber = new InnerSubscriber(this, j);
                addInner(innerSubscriber);
                observable.unsafeSubscribe(innerSubscriber);
                emit();
            }
        }

        void emitEmpty() {
            int r0 = this.scalarEmissionCount + 1;
            if (r0 == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore(r0);
                return;
            }
            this.scalarEmissionCount = r0;
        }

        private void reportError() {
            ArrayList arrayList = new ArrayList(this.errors);
            if (arrayList.size() == 1) {
                this.child.onError((Throwable) arrayList.get(0));
            } else {
                this.child.onError(new CompositeException(arrayList));
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            getOrCreateErrorQueue().offer(th);
            this.done = true;
            emit();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* JADX WARN: Multi-variable type inference failed */
        void addInner(InnerSubscriber<T> innerSubscriber) {
            getOrCreateComposite().add(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[length + 1];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, length);
                innerSubscriberArr2[length] = innerSubscriber;
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        void removeInner(InnerSubscriber<T> innerSubscriber) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer != null) {
                rxRingBuffer.release();
            }
            this.subscriptions.remove(innerSubscriber);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                int length = innerSubscriberArr.length;
                int r3 = -1;
                int r5 = 0;
                while (true) {
                    if (r5 >= length) {
                        break;
                    } else if (innerSubscriber.equals(innerSubscriberArr[r5])) {
                        r3 = r5;
                        break;
                    } else {
                        r5++;
                    }
                }
                if (r3 < 0) {
                    return;
                }
                if (length == 1) {
                    this.innerSubscribers = EMPTY;
                    return;
                }
                InnerSubscriber<?>[] innerSubscriberArr2 = new InnerSubscriber[length - 1];
                System.arraycopy(innerSubscriberArr, 0, innerSubscriberArr2, 0, r3);
                System.arraycopy(innerSubscriberArr, r3 + 1, innerSubscriberArr2, r3, (length - r3) - 1);
                this.innerSubscribers = innerSubscriberArr2;
            }
        }

        void tryEmit(InnerSubscriber<T> innerSubscriber, T t) {
            long j = this.producer.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.producer.get();
                    if (!this.emitting && j != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                RxRingBuffer rxRingBuffer = innerSubscriber.queue;
                if (rxRingBuffer == null || rxRingBuffer.isEmpty()) {
                    emitScalar(innerSubscriber, t, j);
                    return;
                }
                queueScalar(innerSubscriber, t);
                emitLoop();
                return;
            }
            queueScalar(innerSubscriber, t);
            emit();
        }

        protected void queueScalar(InnerSubscriber<T> innerSubscriber, T t) {
            RxRingBuffer rxRingBuffer = innerSubscriber.queue;
            if (rxRingBuffer == null) {
                rxRingBuffer = RxRingBuffer.getSpscInstance();
                innerSubscriber.add(rxRingBuffer);
                innerSubscriber.queue = rxRingBuffer;
            }
            try {
                rxRingBuffer.onNext(NotificationLite.next(t));
            } catch (IllegalStateException e) {
                if (innerSubscriber.isUnsubscribed()) {
                    return;
                }
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e);
            } catch (MissingBackpressureException e2) {
                innerSubscriber.unsubscribe();
                innerSubscriber.onError(e2);
            }
        }

        protected void emitScalar(InnerSubscriber<T> innerSubscriber, T t, long j) {
            try {
                this.child.onNext(t);
                if (j != Long.MAX_VALUE) {
                    this.producer.produced(1);
                }
                innerSubscriber.requestMore(1L);
                synchronized (this) {
                    try {
                        if (!this.missed) {
                            this.emitting = false;
                            return;
                        }
                        this.missed = false;
                        emitLoop();
                    }
                }
            }
        }

        public void requestMore(long j) {
            request(j);
        }

        void tryEmit(T t) {
            long j = this.producer.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.producer.get();
                    if (!this.emitting && j != 0) {
                        this.emitting = true;
                        z = true;
                    }
                }
            }
            if (z) {
                Queue<Object> queue = this.queue;
                if (queue == null || queue.isEmpty()) {
                    emitScalar(t, j);
                    return;
                }
                queueScalar(t);
                emitLoop();
                return;
            }
            queueScalar(t);
            emit();
        }

        protected void queueScalar(T t) {
            Queue<Object> spscExactAtomicArrayQueue;
            Queue<Object> queue = this.queue;
            if (queue == null) {
                int r0 = this.maxConcurrent;
                if (r0 == Integer.MAX_VALUE) {
                    queue = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else {
                    if (Pow2.isPowerOfTwo(r0)) {
                        if (UnsafeAccess.isUnsafeAvailable()) {
                            spscExactAtomicArrayQueue = new SpscArrayQueue<>(r0);
                        } else {
                            spscExactAtomicArrayQueue = new SpscAtomicArrayQueue<>(r0);
                        }
                    } else {
                        spscExactAtomicArrayQueue = new SpscExactAtomicArrayQueue<>(r0);
                    }
                    queue = spscExactAtomicArrayQueue;
                }
                this.queue = queue;
            }
            if (queue.offer(NotificationLite.next(t))) {
                return;
            }
            unsubscribe();
            onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
        }

        protected void emitScalar(T t, long j) {
            try {
                this.child.onNext(t);
                if (j != Long.MAX_VALUE) {
                    this.producer.produced(1);
                }
                int r5 = this.scalarEmissionCount + 1;
                if (r5 == this.scalarEmissionLimit) {
                    this.scalarEmissionCount = 0;
                    requestMore(r5);
                } else {
                    this.scalarEmissionCount = r5;
                }
                synchronized (this) {
                    try {
                        if (!this.missed) {
                            this.emitting = false;
                            return;
                        }
                        this.missed = false;
                        emitLoop();
                    }
                }
            }
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        void emitLoop() {
            boolean z;
            long j;
            int r6;
            boolean z2;
            int r12;
            try {
                Subscriber<? super T> subscriber = this.child;
                while (!checkTerminate()) {
                    Queue<Object> queue = this.queue;
                    long j2 = this.producer.get();
                    boolean z3 = j2 == Long.MAX_VALUE;
                    if (queue != null) {
                        int r0 = 0;
                        while (true) {
                            j = j2;
                            int r7 = 0;
                            r6 = r0;
                            Object obj = null;
                            while (true) {
                                if (j <= 0) {
                                    break;
                                }
                                Object poll = queue.poll();
                                if (checkTerminate()) {
                                    return;
                                }
                                if (poll == null) {
                                    obj = poll;
                                    break;
                                }
                                subscriber.onNext((Object) NotificationLite.getValue(poll));
                                r6++;
                                r7++;
                                j--;
                                obj = poll;
                            }
                            if (r7 > 0) {
                                j = z3 ? Long.MAX_VALUE : this.producer.produced(r7);
                            }
                            if (j == 0 || obj == null) {
                                break;
                            }
                            r0 = r6;
                            j2 = j;
                        }
                    } else {
                        j = j2;
                        r6 = 0;
                    }
                    boolean z4 = this.done;
                    Queue<Object> queue2 = this.queue;
                    InnerSubscriber<?>[] innerSubscriberArr = this.innerSubscribers;
                    int length = innerSubscriberArr.length;
                    if (z4 && ((queue2 == null || queue2.isEmpty()) && length == 0)) {
                        ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
                        if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
                            reportError();
                            return;
                        }
                        subscriber.onCompleted();
                        return;
                    }
                    if (length > 0) {
                        long j3 = this.lastId;
                        int r02 = this.lastIndex;
                        if (length <= r02 || innerSubscriberArr[r02].f2564id != j3) {
                            if (length <= r02) {
                                r02 = 0;
                            }
                            for (int r2 = 0; r2 < length && innerSubscriberArr[r02].f2564id != j3; r2++) {
                                r02++;
                                if (r02 == length) {
                                    r02 = 0;
                                }
                            }
                            this.lastIndex = r02;
                            this.lastId = innerSubscriberArr[r02].f2564id;
                        }
                        z2 = false;
                        for (int r22 = 0; r22 < length; r22++) {
                            if (checkTerminate()) {
                                return;
                            }
                            InnerSubscriber<?> innerSubscriber = innerSubscriberArr[r02];
                            Object obj2 = null;
                            do {
                                int r122 = 0;
                                while (j > 0) {
                                    if (checkTerminate()) {
                                        return;
                                    }
                                    RxRingBuffer rxRingBuffer = innerSubscriber.queue;
                                    if (rxRingBuffer == null || (obj2 = rxRingBuffer.poll()) == null) {
                                        break;
                                    }
                                    try {
                                        subscriber.onNext((Object) NotificationLite.getValue(obj2));
                                        j--;
                                        r122++;
                                    }
                                }
                                if (r122 > 0) {
                                    j = !z3 ? this.producer.produced(r122) : Long.MAX_VALUE;
                                    innerSubscriber.requestMore(r122);
                                }
                                r12 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                                if (r12 == 0) {
                                    break;
                                }
                            } while (obj2 != null);
                            boolean z5 = innerSubscriber.done;
                            RxRingBuffer rxRingBuffer2 = innerSubscriber.queue;
                            if (z5 && (rxRingBuffer2 == null || rxRingBuffer2.isEmpty())) {
                                removeInner(innerSubscriber);
                                if (checkTerminate()) {
                                    return;
                                }
                                r6++;
                                z2 = true;
                            }
                            if (r12 == 0) {
                                break;
                            }
                            r02++;
                            if (r02 == length) {
                                r02 = 0;
                            }
                        }
                        this.lastIndex = r02;
                        this.lastId = innerSubscriberArr[r02].f2564id;
                    } else {
                        z2 = false;
                    }
                    if (r6 > 0) {
                        request(r6);
                    }
                    if (!z2) {
                        synchronized (this) {
                            try {
                                if (!this.missed) {
                                    try {
                                        this.emitting = false;
                                        return;
                                    } catch (Throwable th) {
                                        th = th;
                                        z = true;
                                        while (true) {
                                            try {
                                                break;
                                            } catch (Throwable th2) {
                                                th = th2;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                this.missed = false;
                            } catch (Throwable th3) {
                                th = th3;
                                z = false;
                            }
                        }
                        try {
                            break;
                            throw th;
                        } catch (Throwable th4) {
                            th = th4;
                            if (!z) {
                                synchronized (this) {
                                    this.emitting = false;
                                }
                            }
                            throw th;
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                z = false;
            }
        }

        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
            if (this.delayErrors || concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorMerge$InnerSubscriber */
    /* loaded from: classes6.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int LIMIT = RxRingBuffer.SIZE / 4;
        volatile boolean done;

        /* renamed from: id */
        final long f2564id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> mergeSubscriber, long j) {
            this.parent = mergeSubscriber;
            this.f2564id = j;
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request(RxRingBuffer.SIZE);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.parent.getOrCreateErrorQueue().offer(th);
            this.done = true;
            this.parent.emit();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        public void requestMore(long j) {
            int r0 = this.outstanding - ((int) j);
            if (r0 > LIMIT) {
                this.outstanding = r0;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int r2 = RxRingBuffer.SIZE - r0;
            if (r2 > 0) {
                request(r2);
            }
        }
    }
}
