package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.CompositeException;
import p042rx.functions.FuncN;
import p042rx.internal.util.RxRingBuffer;
import p042rx.internal.util.atomic.SpscLinkedArrayQueue;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.internal.operators.OnSubscribeCombineLatest */
/* loaded from: classes6.dex */
public final class OnSubscribeCombineLatest<T, R> implements Observable.OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN) {
        this(null, iterable, funcN, RxRingBuffer.SIZE, false);
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] observableArr, Iterable<? extends Observable<? extends T>> iterable, FuncN<? extends R> funcN, int r4, boolean z) {
        this.sources = observableArr;
        this.sourcesIterable = iterable;
        this.combiner = funcN;
        this.bufferSize = r4;
        this.delayError = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void call(p042rx.Subscriber<? super R> r9) {
        /*
            r8 = this;
            rx.Observable<? extends T>[] r0 = r8.sources
            if (r0 != 0) goto L47
            java.lang.Iterable<? extends rx.Observable<? extends T>> r0 = r8.sourcesIterable
            boolean r1 = r0 instanceof java.util.List
            if (r1 == 0) goto L1c
            java.util.List r0 = (java.util.List) r0
            int r1 = r0.size()
            rx.Observable[] r1 = new p042rx.Observable[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            rx.Observable[] r0 = (p042rx.Observable[]) r0
            rx.Observable[] r0 = (p042rx.Observable[]) r0
            int r1 = r0.length
            goto L48
        L1c:
            r1 = 8
            rx.Observable[] r1 = new p042rx.Observable[r1]
            java.util.Iterator r0 = r0.iterator()
            r2 = 0
            r3 = 0
        L26:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L44
            java.lang.Object r4 = r0.next()
            rx.Observable r4 = (p042rx.Observable) r4
            int r5 = r1.length
            if (r3 != r5) goto L3e
            int r5 = r3 >> 2
            int r5 = r5 + r3
            rx.Observable[] r5 = new p042rx.Observable[r5]
            java.lang.System.arraycopy(r1, r2, r5, r2, r3)
            r1 = r5
        L3e:
            int r5 = r3 + 1
            r1[r3] = r4
            r3 = r5
            goto L26
        L44:
            r0 = r1
            r4 = r3
            goto L49
        L47:
            int r1 = r0.length
        L48:
            r4 = r1
        L49:
            if (r4 != 0) goto L4f
            r9.onCompleted()
            return
        L4f:
            rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator r7 = new rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator
            rx.functions.FuncN<? extends R> r3 = r8.combiner
            int r5 = r8.bufferSize
            boolean r6 = r8.delayError
            r1 = r7
            r2 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            r7.subscribe(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p042rx.internal.operators.OnSubscribeCombineLatest.call(rx.Subscriber):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCombineLatest$LatestCoordinator */
    /* loaded from: classes6.dex */
    public static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final AtomicReference<Throwable> error;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested;
        final CombinerSubscriber<T, R>[] subscribers;

        public LatestCoordinator(Subscriber<? super R> subscriber, FuncN<? extends R> funcN, int r3, int r4, boolean z) {
            this.actual = subscriber;
            this.combiner = funcN;
            this.bufferSize = r4;
            this.delayError = z;
            Object[] objArr = new Object[r3];
            this.latest = objArr;
            Arrays.fill(objArr, MISSING);
            this.subscribers = new CombinerSubscriber[r3];
            this.queue = new SpscLinkedArrayQueue<>(r4);
            this.requested = new AtomicLong();
            this.error = new AtomicReference<>();
        }

        public void subscribe(Observable<? extends T>[] observableArr) {
            CombinerSubscriber<T, R>[] combinerSubscriberArr = this.subscribers;
            int length = combinerSubscriberArr.length;
            for (int r3 = 0; r3 < length; r3++) {
                combinerSubscriberArr[r3] = new CombinerSubscriber<>(this, r3);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int r2 = 0; r2 < length && !this.cancelled; r2++) {
                observableArr[r2].subscribe((Subscriber<? super Object>) combinerSubscriberArr[r2]);
            }
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (r2 != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                drain();
            }
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                cancel(this.queue);
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void cancel(Queue<?> queue) {
            queue.clear();
            for (CombinerSubscriber<T, R> combinerSubscriber : this.subscribers) {
                combinerSubscriber.unsubscribe();
            }
        }

        void combine(Object obj, int r10) {
            boolean z;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[r10];
            synchronized (this) {
                Object[] objArr = this.latest;
                int length = objArr.length;
                Object obj2 = objArr[r10];
                int r4 = this.active;
                Object obj3 = MISSING;
                if (obj2 == obj3) {
                    r4++;
                    this.active = r4;
                }
                int r6 = this.complete;
                if (obj == null) {
                    r6++;
                    this.complete = r6;
                } else {
                    objArr[r10] = NotificationLite.getValue(obj);
                }
                boolean z2 = false;
                z = r4 == length;
                if (r6 == length || (obj == null && obj2 == obj3)) {
                    z2 = true;
                }
                if (z2) {
                    this.done = true;
                } else if (obj != null && z) {
                    this.queue.offer(combinerSubscriber, this.latest.clone());
                } else if (obj == null && this.error.get() != null && (obj2 == obj3 || !this.delayError)) {
                    this.done = true;
                }
            }
            if (!z && obj != null) {
                combinerSubscriber.requestMore(1L);
            } else {
                drain();
            }
        }

        void drain() {
            long j;
            if (getAndIncrement() != 0) {
                return;
            }
            SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
            Subscriber<? super R> subscriber = this.actual;
            boolean z = this.delayError;
            AtomicLong atomicLong = this.requested;
            int r12 = 1;
            while (!checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue, z)) {
                long j2 = atomicLong.get();
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        j = j3;
                        break;
                    }
                    boolean z2 = this.done;
                    CombinerSubscriber combinerSubscriber = (CombinerSubscriber) spscLinkedArrayQueue.peek();
                    boolean z3 = combinerSubscriber == null;
                    long j4 = j3;
                    if (checkTerminated(z2, z3, subscriber, spscLinkedArrayQueue, z)) {
                        return;
                    }
                    if (z3) {
                        j = j4;
                        break;
                    }
                    spscLinkedArrayQueue.poll();
                    Object[] objArr = (Object[]) spscLinkedArrayQueue.poll();
                    if (objArr == null) {
                        this.cancelled = true;
                        cancel(spscLinkedArrayQueue);
                        subscriber.onError(new IllegalStateException("Broken queue?! Sender received but not the array."));
                        return;
                    }
                    try {
                        subscriber.onNext((R) this.combiner.call(objArr));
                        combinerSubscriber.requestMore(1L);
                        j3 = j4 + 1;
                    } catch (Throwable th) {
                        this.cancelled = true;
                        cancel(spscLinkedArrayQueue);
                        subscriber.onError(th);
                        return;
                    }
                }
                if (j != 0 && j2 != Long.MAX_VALUE) {
                    BackpressureUtils.produced(atomicLong, j);
                }
                r12 = addAndGet(-r12);
                if (r12 == 0) {
                    return;
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue, boolean z3) {
            if (this.cancelled) {
                cancel(queue);
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error.get();
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = this.error.get();
                if (th2 != null) {
                    cancel(queue);
                    subscriber.onError(th2);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        void onError(Throwable th) {
            Throwable th2;
            Throwable th3;
            AtomicReference<Throwable> atomicReference = this.error;
            do {
                th2 = atomicReference.get();
                if (th2 == null) {
                    th3 = th;
                } else if (th2 instanceof CompositeException) {
                    ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                    arrayList.add(th);
                    th3 = new CompositeException(arrayList);
                } else {
                    th3 = new CompositeException(Arrays.asList(th2, th));
                }
            } while (!atomicReference.compareAndSet(th2, th3));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribeCombineLatest$CombinerSubscriber */
    /* loaded from: classes6.dex */
    public static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> latestCoordinator, int r2) {
            this.parent = latestCoordinator;
            this.index = r2;
            request(latestCoordinator.bufferSize);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.parent.combine(NotificationLite.next(t), this.index);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.parent.onError(th);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.combine(null, this.index);
        }

        public void requestMore(long j) {
            request(j);
        }
    }
}
