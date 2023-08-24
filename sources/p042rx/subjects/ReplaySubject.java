package p042rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.internal.operators.BackpressureUtils;
import p042rx.plugins.RxJavaHooks;
import p042rx.schedulers.Schedulers;

/* renamed from: rx.subjects.ReplaySubject */
/* loaded from: classes6.dex */
public final class ReplaySubject<T> extends Subject<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    final ReplayState<T> state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.ReplaySubject$ReplayBuffer */
    /* loaded from: classes6.dex */
    public interface ReplayBuffer<T> {
        void complete();

        void drain(ReplayProducer<T> replayProducer);

        Throwable error();

        void error(Throwable th);

        boolean isComplete();

        boolean isEmpty();

        T last();

        void next(T t);

        int size();

        T[] toArray(T[] tArr);
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> create(int r3) {
        if (r3 <= 0) {
            throw new IllegalArgumentException("capacity > 0 required but it was " + r3);
        }
        return new ReplaySubject<>(new ReplayState(new ReplayUnboundedBuffer(r3)));
    }

    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeBoundBuffer(Integer.MAX_VALUE)));
    }

    static <T> ReplaySubject<T> createUnboundedTime() {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeAndTimeBoundBuffer(Integer.MAX_VALUE, Long.MAX_VALUE, Schedulers.immediate())));
    }

    public static <T> ReplaySubject<T> createWithSize(int r1) {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeBoundBuffer(r1)));
    }

    public static <T> ReplaySubject<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return createWithTimeAndSize(j, timeUnit, Integer.MAX_VALUE, scheduler);
    }

    public static <T> ReplaySubject<T> createWithTimeAndSize(long j, TimeUnit timeUnit, int r4, Scheduler scheduler) {
        return new ReplaySubject<>(new ReplayState(new ReplaySizeAndTimeBoundBuffer(r4, timeUnit.toMillis(j), scheduler)));
    }

    ReplaySubject(ReplayState<T> replayState) {
        super(replayState);
        this.state = replayState;
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

    int subscriberCount() {
        return this.state.get().length;
    }

    @Override // p042rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.state.isTerminated() && this.state.buffer.error() != null;
    }

    public boolean hasCompleted() {
        return this.state.isTerminated() && this.state.buffer.error() == null;
    }

    public Throwable getThrowable() {
        if (this.state.isTerminated()) {
            return this.state.buffer.error();
        }
        return null;
    }

    public int size() {
        return this.state.buffer.size();
    }

    public boolean hasAnyValue() {
        return !this.state.buffer.isEmpty();
    }

    public boolean hasValue() {
        return hasAnyValue();
    }

    public T[] getValues(T[] tArr) {
        return this.state.buffer.toArray(tArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = EMPTY_ARRAY;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    public T getValue() {
        return this.state.buffer.last();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.ReplaySubject$ReplayState */
    /* loaded from: classes6.dex */
    public static final class ReplayState<T> extends AtomicReference<ReplayProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        static final ReplayProducer[] EMPTY = new ReplayProducer[0];
        static final ReplayProducer[] TERMINATED = new ReplayProducer[0];
        private static final long serialVersionUID = 5952362471246910544L;
        final ReplayBuffer<T> buffer;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public ReplayState(ReplayBuffer<T> replayBuffer) {
            this.buffer = replayBuffer;
            lazySet(EMPTY);
        }

        public void call(Subscriber<? super T> subscriber) {
            ReplayProducer<T> replayProducer = new ReplayProducer<>(subscriber, this);
            subscriber.add(replayProducer);
            subscriber.setProducer(replayProducer);
            if (add(replayProducer) && replayProducer.isUnsubscribed()) {
                remove(replayProducer);
            } else {
                this.buffer.drain(replayProducer);
            }
        }

        boolean add(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED) {
                    return false;
                }
                int length = replayProducerArr.length;
                replayProducerArr2 = new ReplayProducer[length + 1];
                System.arraycopy(replayProducerArr, 0, replayProducerArr2, 0, length);
                replayProducerArr2[length] = replayProducer;
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
            return true;
        }

        void remove(ReplayProducer<T> replayProducer) {
            ReplayProducer<T>[] replayProducerArr;
            ReplayProducer[] replayProducerArr2;
            do {
                replayProducerArr = get();
                if (replayProducerArr == TERMINATED || replayProducerArr == EMPTY) {
                    return;
                }
                int length = replayProducerArr.length;
                int r2 = -1;
                int r4 = 0;
                while (true) {
                    if (r4 >= length) {
                        break;
                    } else if (replayProducerArr[r4] == replayProducer) {
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
                    replayProducerArr2 = EMPTY;
                } else {
                    ReplayProducer[] replayProducerArr3 = new ReplayProducer[length - 1];
                    System.arraycopy(replayProducerArr, 0, replayProducerArr3, 0, r2);
                    System.arraycopy(replayProducerArr, r2 + 1, replayProducerArr3, r2, (length - r2) - 1);
                    replayProducerArr2 = replayProducerArr3;
                }
            } while (!compareAndSet(replayProducerArr, replayProducerArr2));
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.next(t);
            for (ReplayProducer<T> replayProducer : get()) {
                replayBuffer.drain(replayProducer);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.error(th);
            ArrayList arrayList = null;
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                try {
                    replayBuffer.drain(replayProducer);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            ReplayBuffer<T> replayBuffer = this.buffer;
            replayBuffer.complete();
            for (ReplayProducer<T> replayProducer : getAndSet(TERMINATED)) {
                replayBuffer.drain(replayProducer);
            }
        }

        boolean isTerminated() {
            return get() == TERMINATED;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.ReplaySubject$ReplayUnboundedBuffer */
    /* loaded from: classes6.dex */
    public static final class ReplayUnboundedBuffer<T> implements ReplayBuffer<T> {
        final int capacity;
        volatile boolean done;
        Throwable error;
        final Object[] head;
        volatile int size;
        Object[] tail;
        int tailIndex;

        public ReplayUnboundedBuffer(int r1) {
            this.capacity = r1;
            Object[] objArr = new Object[r1 + 1];
            this.head = objArr;
            this.tail = objArr;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            if (this.done) {
                return;
            }
            int r0 = this.tailIndex;
            Object[] objArr = this.tail;
            if (r0 == objArr.length - 1) {
                Object[] objArr2 = new Object[objArr.length];
                objArr2[0] = t;
                this.tailIndex = 1;
                objArr[r0] = objArr2;
                this.tail = objArr2;
            } else {
                objArr[r0] = t;
                this.tailIndex = r0 + 1;
            }
            this.size++;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
            if (r17 != 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006e, code lost:
            if (r2.isUnsubscribed() == false) goto L36;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0070, code lost:
            r20.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0072, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
            r11 = r19.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0077, code lost:
            if (r10 != r19.size) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
            r16 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x007c, code lost:
            r16 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007e, code lost:
            if (r11 == false) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0080, code lost:
            if (r16 == false) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
            r20.node = null;
            r1 = r19.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0086, code lost:
            if (r1 == null) goto L46;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x0088, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x008c, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x008f, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0094, code lost:
            if (r13 == 0) goto L57;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x009d, code lost:
            if (r6 == Long.MAX_VALUE) goto L57;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x009f, code lost:
            p042rx.internal.operators.BackpressureUtils.produced(r20.requested, r13);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00a4, code lost:
            r20.index = r10;
            r20.tailIndex = r9;
            r20.node = r8;
            r5 = r20.addAndGet(-r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
            return;
         */
        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(p042rx.subjects.ReplaySubject.ReplayProducer<T> r20) {
            /*
                r19 = this;
                r0 = r19
                r1 = r20
                int r2 = r20.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                int r3 = r0.capacity
                r4 = 1
                r5 = 1
            L11:
                java.util.concurrent.atomic.AtomicLong r6 = r1.requested
                long r6 = r6.get()
                java.lang.Object r8 = r1.node
                java.lang.Object[] r8 = (java.lang.Object[]) r8
                java.lang.Object[] r8 = (java.lang.Object[]) r8
                if (r8 != 0) goto L21
                java.lang.Object[] r8 = r0.head
            L21:
                int r9 = r1.tailIndex
                int r10 = r1.index
                r13 = 0
            L27:
                r15 = 0
                int r17 = (r13 > r6 ? 1 : (r13 == r6 ? 0 : -1))
                if (r17 == 0) goto L68
                boolean r18 = r2.isUnsubscribed()
                if (r18 == 0) goto L35
                r1.node = r15
                return
            L35:
                boolean r11 = r0.done
                int r12 = r0.size
                if (r10 != r12) goto L3d
                r12 = 1
                goto L3e
            L3d:
                r12 = 0
            L3e:
                if (r11 == 0) goto L50
                if (r12 == 0) goto L50
                r1.node = r15
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L4c
                r2.onError(r1)
                goto L4f
            L4c:
                r2.onCompleted()
            L4f:
                return
            L50:
                if (r12 == 0) goto L53
                goto L68
            L53:
                if (r9 != r3) goto L5c
                r8 = r8[r9]
                java.lang.Object[] r8 = (java.lang.Object[]) r8
                java.lang.Object[] r8 = (java.lang.Object[]) r8
                r9 = 0
            L5c:
                r11 = r8[r9]
                r2.onNext(r11)
                r11 = 1
                long r13 = r13 + r11
                int r9 = r9 + r4
                int r10 = r10 + 1
                goto L27
            L68:
                if (r17 != 0) goto L90
                boolean r11 = r2.isUnsubscribed()
                if (r11 == 0) goto L73
                r1.node = r15
                return
            L73:
                boolean r11 = r0.done
                int r12 = r0.size
                if (r10 != r12) goto L7c
                r16 = 1
                goto L7e
            L7c:
                r16 = 0
            L7e:
                if (r11 == 0) goto L90
                if (r16 == 0) goto L90
                r1.node = r15
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L8c
                r2.onError(r1)
                goto L8f
            L8c:
                r2.onCompleted()
            L8f:
                return
            L90:
                r11 = 0
                int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
                if (r15 == 0) goto La4
                r11 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r15 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r15 == 0) goto La4
                java.util.concurrent.atomic.AtomicLong r6 = r1.requested
                p042rx.internal.operators.BackpressureUtils.produced(r6, r13)
            La4:
                r1.index = r10
                r1.tailIndex = r9
                r1.node = r8
                int r5 = -r5
                int r5 = r1.addAndGet(r5)
                if (r5 != 0) goto L11
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.subjects.ReplaySubject.ReplayUnboundedBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            int r0 = this.size;
            if (r0 == 0) {
                return null;
            }
            Object[] objArr = this.head;
            int r2 = this.capacity;
            while (r0 >= r2) {
                objArr = objArr[r2];
                r0 -= r2;
            }
            return (T) objArr[r0 - 1];
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            return this.size;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.size == 0;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            int r0 = this.size;
            if (tArr.length < r0) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), r0));
            }
            Object[] objArr = this.head;
            int r2 = this.capacity;
            int r4 = 0;
            while (true) {
                int r5 = r4 + r2;
                if (r5 >= r0) {
                    break;
                }
                System.arraycopy(objArr, 0, tArr, r4, r2);
                objArr = objArr[r2];
                r4 = r5;
            }
            System.arraycopy(objArr, 0, tArr, r4, r0 - r4);
            if (tArr.length > r0) {
                tArr[r0] = null;
            }
            return tArr;
        }
    }

    /* renamed from: rx.subjects.ReplaySubject$ReplaySizeBoundBuffer */
    /* loaded from: classes6.dex */
    static final class ReplaySizeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile Node<T> head;
        final int limit;
        int size;
        Node<T> tail;

        public ReplaySizeBoundBuffer(int r2) {
            this.limit = r2;
            Node<T> node = new Node<>(null);
            this.tail = node;
            this.head = node;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            Node<T> node = new Node<>(t);
            this.tail.set(node);
            this.tail = node;
            int r2 = this.size;
            if (r2 == this.limit) {
                this.head = this.head.get();
            } else {
                this.size = r2 + 1;
            }
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            this.error = th;
            this.done = true;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            this.done = true;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
            if (r14 != 0) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
            if (r2.isUnsubscribed() == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0066, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0068, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0069, code lost:
            r3 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006f, code lost:
            if (r7.get() != null) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0071, code lost:
            r12 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0072, code lost:
            if (r3 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0074, code lost:
            if (r12 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0076, code lost:
            r18.node = null;
            r1 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007a, code lost:
            if (r1 == null) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007c, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0080, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0083, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0086, code lost:
            if (r10 == 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x008f, code lost:
            if (r5 == Long.MAX_VALUE) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0091, code lost:
            p042rx.internal.operators.BackpressureUtils.produced(r18.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0096, code lost:
            r18.node = r7;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
            return;
         */
        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(p042rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r4 = 1
            Le:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                java.lang.Object r7 = r1.node
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node r7 = (p042rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.Node) r7
                r8 = 0
                if (r7 != 0) goto L1e
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node<T> r7 = r0.head
            L1e:
                r10 = r8
            L1f:
                r12 = 0
                r13 = 0
                int r14 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                if (r14 == 0) goto L5e
                boolean r15 = r2.isUnsubscribed()
                if (r15 == 0) goto L2e
                r1.node = r13
                return
            L2e:
                boolean r15 = r0.done
                java.lang.Object r16 = r7.get()
                r3 = r16
                rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node r3 = (p042rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.Node) r3
                if (r3 != 0) goto L3d
                r16 = 1
                goto L3f
            L3d:
                r16 = 0
            L3f:
                if (r15 == 0) goto L51
                if (r16 == 0) goto L51
                r1.node = r13
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L4d
                r2.onError(r1)
                goto L50
            L4d:
                r2.onCompleted()
            L50:
                return
            L51:
                if (r16 == 0) goto L54
                goto L5e
            L54:
                T r7 = r3.value
                r2.onNext(r7)
                r12 = 1
                long r10 = r10 + r12
                r7 = r3
                goto L1f
            L5e:
                if (r14 != 0) goto L84
                boolean r3 = r2.isUnsubscribed()
                if (r3 == 0) goto L69
                r1.node = r13
                return
            L69:
                boolean r3 = r0.done
                java.lang.Object r14 = r7.get()
                if (r14 != 0) goto L72
                r12 = 1
            L72:
                if (r3 == 0) goto L84
                if (r12 == 0) goto L84
                r1.node = r13
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L80
                r2.onError(r1)
                goto L83
            L80:
                r2.onCompleted()
            L83:
                return
            L84:
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r3 == 0) goto L96
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r3 == 0) goto L96
                java.util.concurrent.atomic.AtomicLong r3 = r1.requested
                p042rx.internal.operators.BackpressureUtils.produced(r3, r10)
            L96:
                r1.node = r7
                int r3 = -r4
                int r4 = r1.addAndGet(r3)
                if (r4 != 0) goto Le
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.subjects.ReplaySubject.ReplaySizeBoundBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        /* renamed from: rx.subjects.ReplaySubject$ReplaySizeBoundBuffer$Node */
        /* loaded from: classes6.dex */
        static final class Node<T> extends AtomicReference<Node<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final T value;

            public Node(T t) {
                this.value = t;
            }
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            Node<T> node = this.head;
            while (true) {
                Node<T> node2 = node.get();
                if (node2 == null) {
                    return node.value;
                }
                node = node2;
            }
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            Node<T> node = this.head.get();
            int r1 = 0;
            while (node != null && r1 != Integer.MAX_VALUE) {
                node = node.get();
                r1++;
            }
            return r1;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return this.head.get() == null;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (Node<T> node = this.head.get(); node != null; node = node.get()) {
                arrayList.add(node.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer */
    /* loaded from: classes6.dex */
    public static final class ReplaySizeAndTimeBoundBuffer<T> implements ReplayBuffer<T> {
        volatile boolean done;
        Throwable error;
        volatile TimedNode<T> head;
        final int limit;
        final long maxAgeMillis;
        final Scheduler scheduler;
        int size;
        TimedNode<T> tail;

        public ReplaySizeAndTimeBoundBuffer(int r4, long j, Scheduler scheduler) {
            this.limit = r4;
            TimedNode<T> timedNode = new TimedNode<>(null, 0L);
            this.tail = timedNode;
            this.head = timedNode;
            this.maxAgeMillis = j;
            this.scheduler = scheduler;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void next(T t) {
            TimedNode<T> timedNode;
            long now = this.scheduler.now();
            TimedNode<T> timedNode2 = new TimedNode<>(t, now);
            this.tail.set(timedNode2);
            this.tail = timedNode2;
            long j = now - this.maxAgeMillis;
            int r9 = this.size;
            TimedNode<T> timedNode3 = this.head;
            if (r9 == this.limit) {
                timedNode = timedNode3.get();
            } else {
                r9++;
                timedNode = timedNode3;
            }
            while (true) {
                TimedNode<T> timedNode4 = timedNode.get();
                if (timedNode4 == null || timedNode4.timestamp > j) {
                    break;
                }
                r9--;
                timedNode = timedNode4;
            }
            this.size = r9;
            if (timedNode != timedNode3) {
                this.head = timedNode;
            }
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void error(Throwable th) {
            evictFinal();
            this.error = th;
            this.done = true;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public void complete() {
            evictFinal();
            this.done = true;
        }

        void evictFinal() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> timedNode = this.head;
            TimedNode<T> timedNode2 = timedNode;
            while (true) {
                TimedNode<T> timedNode3 = timedNode2.get();
                if (timedNode3 == null || timedNode3.timestamp > now) {
                    break;
                }
                timedNode2 = timedNode3;
            }
            if (timedNode != timedNode2) {
                this.head = timedNode2;
            }
        }

        TimedNode<T> latestHead() {
            long now = this.scheduler.now() - this.maxAgeMillis;
            TimedNode<T> timedNode = this.head;
            while (true) {
                TimedNode<T> timedNode2 = timedNode.get();
                if (timedNode2 == null || timedNode2.timestamp > now) {
                    break;
                }
                timedNode = timedNode2;
            }
            return timedNode;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0060, code lost:
            if (r14 != 0) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0066, code lost:
            if (r2.isUnsubscribed() == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006a, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006b, code lost:
            r3 = r17.done;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
            if (r7.get() != null) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
            r12 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            if (r3 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0076, code lost:
            if (r12 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0078, code lost:
            r18.node = null;
            r1 = r17.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
            if (r1 == null) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007e, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0085, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0088, code lost:
            if (r10 == 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0091, code lost:
            if (r5 == Long.MAX_VALUE) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0093, code lost:
            p042rx.internal.operators.BackpressureUtils.produced(r18.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0098, code lost:
            r18.node = r7;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
            return;
         */
        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void drain(p042rx.subjects.ReplaySubject.ReplayProducer<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r4 = 1
            Le:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                java.lang.Object r7 = r1.node
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r7 = (p042rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.TimedNode) r7
                r8 = 0
                if (r7 != 0) goto L20
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r7 = r17.latestHead()
            L20:
                r10 = r8
            L21:
                r12 = 0
                r13 = 0
                int r14 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                if (r14 == 0) goto L60
                boolean r15 = r2.isUnsubscribed()
                if (r15 == 0) goto L30
                r1.node = r13
                return
            L30:
                boolean r15 = r0.done
                java.lang.Object r16 = r7.get()
                r3 = r16
                rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode r3 = (p042rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.TimedNode) r3
                if (r3 != 0) goto L3f
                r16 = 1
                goto L41
            L3f:
                r16 = 0
            L41:
                if (r15 == 0) goto L53
                if (r16 == 0) goto L53
                r1.node = r13
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L4f
                r2.onError(r1)
                goto L52
            L4f:
                r2.onCompleted()
            L52:
                return
            L53:
                if (r16 == 0) goto L56
                goto L60
            L56:
                T r7 = r3.value
                r2.onNext(r7)
                r12 = 1
                long r10 = r10 + r12
                r7 = r3
                goto L21
            L60:
                if (r14 != 0) goto L86
                boolean r3 = r2.isUnsubscribed()
                if (r3 == 0) goto L6b
                r1.node = r13
                return
            L6b:
                boolean r3 = r0.done
                java.lang.Object r14 = r7.get()
                if (r14 != 0) goto L74
                r12 = 1
            L74:
                if (r3 == 0) goto L86
                if (r12 == 0) goto L86
                r1.node = r13
                java.lang.Throwable r1 = r0.error
                if (r1 == 0) goto L82
                r2.onError(r1)
                goto L85
            L82:
                r2.onCompleted()
            L85:
                return
            L86:
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r3 == 0) goto L98
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r3 == 0) goto L98
                java.util.concurrent.atomic.AtomicLong r3 = r1.requested
                p042rx.internal.operators.BackpressureUtils.produced(r3, r10)
            L98:
                r1.node = r7
                int r3 = -r4
                int r4 = r1.addAndGet(r3)
                if (r4 != 0) goto Le
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.subjects.ReplaySubject.ReplaySizeAndTimeBoundBuffer.drain(rx.subjects.ReplaySubject$ReplayProducer):void");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.subjects.ReplaySubject$ReplaySizeAndTimeBoundBuffer$TimedNode */
        /* loaded from: classes6.dex */
        public static final class TimedNode<T> extends AtomicReference<TimedNode<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            final long timestamp;
            final T value;

            public TimedNode(T t, long j) {
                this.value = t;
                this.timestamp = j;
            }
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isComplete() {
            return this.done;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public Throwable error() {
            return this.error;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public T last() {
            TimedNode<T> latestHead = latestHead();
            while (true) {
                TimedNode<T> timedNode = latestHead.get();
                if (timedNode == null) {
                    return latestHead.value;
                }
                latestHead = timedNode;
            }
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public int size() {
            TimedNode<T> timedNode = latestHead().get();
            int r1 = 0;
            while (timedNode != null && r1 != Integer.MAX_VALUE) {
                timedNode = timedNode.get();
                r1++;
            }
            return r1;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public boolean isEmpty() {
            return latestHead().get() == null;
        }

        @Override // p042rx.subjects.ReplaySubject.ReplayBuffer
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (TimedNode<T> timedNode = latestHead().get(); timedNode != null; timedNode = timedNode.get()) {
                arrayList.add(timedNode.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.ReplaySubject$ReplayProducer */
    /* loaded from: classes6.dex */
    public static final class ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
        private static final long serialVersionUID = -5006209596735204567L;
        final Subscriber<? super T> actual;
        int index;
        Object node;
        final AtomicLong requested = new AtomicLong();
        final ReplayState<T> state;
        int tailIndex;

        public ReplayProducer(Subscriber<? super T> subscriber, ReplayState<T> replayState) {
            this.actual = subscriber;
            this.state = replayState;
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            this.state.remove(this);
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                this.state.buffer.drain(this);
            } else if (r2 >= 0) {
            } else {
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
        }
    }
}
