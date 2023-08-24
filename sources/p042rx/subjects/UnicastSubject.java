package p042rx.subjects;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;
import p042rx.internal.operators.BackpressureUtils;
import p042rx.internal.operators.NotificationLite;
import p042rx.internal.util.atomic.SpscLinkedAtomicQueue;
import p042rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import p042rx.internal.util.unsafe.SpscLinkedQueue;
import p042rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;

/* renamed from: rx.subjects.UnicastSubject */
/* loaded from: classes6.dex */
public final class UnicastSubject<T> extends Subject<T, T> {
    final State<T> state;

    public static <T> UnicastSubject<T> create() {
        return create(16);
    }

    public static <T> UnicastSubject<T> create(int r3) {
        return new UnicastSubject<>(new State(r3, false, null));
    }

    public static <T> UnicastSubject<T> create(boolean z) {
        return new UnicastSubject<>(new State(16, z, null));
    }

    public static <T> UnicastSubject<T> create(int r2, Action0 action0) {
        return new UnicastSubject<>(new State(r2, false, action0));
    }

    public static <T> UnicastSubject<T> create(int r1, Action0 action0, boolean z) {
        return new UnicastSubject<>(new State(r1, z, action0));
    }

    private UnicastSubject(State<T> state) {
        super(state);
        this.state = state;
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
        return this.state.subscriber.get() != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.subjects.UnicastSubject$State */
    /* loaded from: classes6.dex */
    public static final class State<T> extends AtomicLong implements Producer, Observer<T>, Observable.OnSubscribe<T>, Subscription {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        final boolean delayError;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final Queue<Object> queue;
        final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference<>();
        final AtomicReference<Action0> terminateOnce;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public State(int r2, boolean z, Action0 action0) {
            Queue<Object> spscLinkedQueue;
            this.terminateOnce = action0 != null ? new AtomicReference<>(action0) : null;
            this.delayError = z;
            if (r2 > 1) {
                spscLinkedQueue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(r2) : new SpscUnboundedAtomicArrayQueue<>(r2);
            } else {
                spscLinkedQueue = UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue<>() : new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedQueue;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (!this.caughtUp) {
                boolean z = false;
                synchronized (this) {
                    if (!this.caughtUp) {
                        this.queue.offer(NotificationLite.next(t));
                        z = true;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            Subscriber<? super T> subscriber = this.subscriber.get();
            try {
                subscriber.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, t);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                return;
            }
            doTerminate();
            this.error = th;
            boolean z = true;
            this.done = true;
            if (!this.caughtUp) {
                synchronized (this) {
                    if (this.caughtUp) {
                        z = false;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            this.subscriber.get().onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            doTerminate();
            boolean z = true;
            this.done = true;
            if (!this.caughtUp) {
                synchronized (this) {
                    if (this.caughtUp) {
                        z = false;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            this.subscriber.get().onCompleted();
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (r2 > 0) {
                BackpressureUtils.getAndAddRequest(this, j);
                replay();
            } else if (this.done) {
                replay();
            }
        }

        public void call(Subscriber<? super T> subscriber) {
            if (this.subscriber.compareAndSet(null, subscriber)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                return;
            }
            subscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
            if (r7 == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0087, code lost:
            if (r0.isEmpty() == false) goto L53;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0089, code lost:
            r15.caughtUp = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x008b, code lost:
            r15.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void replay() {
            /*
                r15 = this;
                monitor-enter(r15)
                boolean r0 = r15.emitting     // Catch: java.lang.Throwable -> L97
                r1 = 1
                if (r0 == 0) goto La
                r15.missed = r1     // Catch: java.lang.Throwable -> L97
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L97
                return
            La:
                r15.emitting = r1     // Catch: java.lang.Throwable -> L97
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L97
                java.util.Queue<java.lang.Object> r0 = r15.queue
                boolean r2 = r15.delayError
            L11:
                java.util.concurrent.atomic.AtomicReference<rx.Subscriber<? super T>> r3 = r15.subscriber
                java.lang.Object r3 = r3.get()
                rx.Subscriber r3 = (p042rx.Subscriber) r3
                r4 = 0
                if (r3 == 0) goto L7b
                boolean r5 = r15.done
                boolean r6 = r0.isEmpty()
                boolean r5 = r15.checkTerminated(r5, r6, r2, r3)
                if (r5 == 0) goto L29
                return
            L29:
                long r5 = r15.get()
                r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r9 != 0) goto L38
                r7 = 1
                goto L39
            L38:
                r7 = 0
            L39:
                r8 = 0
                r10 = r8
            L3c:
                int r12 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r12 == 0) goto L70
                boolean r12 = r15.done
                java.lang.Object r13 = r0.poll()
                if (r13 != 0) goto L4a
                r14 = 1
                goto L4b
            L4a:
                r14 = 0
            L4b:
                boolean r12 = r15.checkTerminated(r12, r14, r2, r3)
                if (r12 == 0) goto L52
                return
            L52:
                if (r14 == 0) goto L55
                goto L70
            L55:
                java.lang.Object r12 = p042rx.internal.operators.NotificationLite.getValue(r13)
                r3.onNext(r12)     // Catch: java.lang.Throwable -> L61
                r12 = 1
                long r5 = r5 - r12
                long r10 = r10 + r12
                goto L3c
            L61:
                r1 = move-exception
                r0.clear()
                p042rx.exceptions.Exceptions.throwIfFatal(r1)
                java.lang.Throwable r0 = p042rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r12)
                r3.onError(r0)
                return
            L70:
                if (r7 != 0) goto L7c
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r3 == 0) goto L7c
                long r5 = -r10
                r15.addAndGet(r5)
                goto L7c
            L7b:
                r7 = 0
            L7c:
                monitor-enter(r15)
                boolean r3 = r15.missed     // Catch: java.lang.Throwable -> L94
                if (r3 != 0) goto L8f
                if (r7 == 0) goto L8b
                boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L94
                if (r0 == 0) goto L8b
                r15.caughtUp = r1     // Catch: java.lang.Throwable -> L94
            L8b:
                r15.emitting = r4     // Catch: java.lang.Throwable -> L94
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L94
                return
            L8f:
                r15.missed = r4     // Catch: java.lang.Throwable -> L94
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L94
                goto L11
            L94:
                r0 = move-exception
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L94
                throw r0
            L97:
                r0 = move-exception
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L97
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.subjects.UnicastSubject.State.replay():void");
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            doTerminate();
            this.done = true;
            synchronized (this) {
                if (this.emitting) {
                    return;
                }
                this.emitting = true;
                this.queue.clear();
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.done;
        }

        boolean checkTerminated(boolean z, boolean z2, boolean z3, Subscriber<? super T> subscriber) {
            if (subscriber.isUnsubscribed()) {
                this.queue.clear();
                return true;
            } else if (z) {
                Throwable th = this.error;
                if (th != null && !z3) {
                    this.queue.clear();
                    subscriber.onError(th);
                    return true;
                } else if (z2) {
                    if (th != null) {
                        subscriber.onError(th);
                    } else {
                        subscriber.onCompleted();
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        void doTerminate() {
            Action0 action0;
            AtomicReference<Action0> atomicReference = this.terminateOnce;
            if (atomicReference == null || (action0 = atomicReference.get()) == null || !atomicReference.compareAndSet(action0, null)) {
                return;
            }
            action0.call();
        }
    }
}
