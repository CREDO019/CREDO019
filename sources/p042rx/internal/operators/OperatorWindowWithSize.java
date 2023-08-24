package p042rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.functions.Action0;
import p042rx.internal.util.atomic.SpscLinkedArrayQueue;
import p042rx.subjects.Subject;
import p042rx.subjects.UnicastSubject;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorWindowWithSize */
/* loaded from: classes6.dex */
public final class OperatorWindowWithSize<T> implements Observable.Operator<Observable<T>, T> {
    final int size;
    final int skip;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorWindowWithSize(int r1, int r2) {
        this.size = r1;
        this.skip = r2;
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        int r0 = this.skip;
        int r1 = this.size;
        if (r0 == r1) {
            WindowExact windowExact = new WindowExact(subscriber, r1);
            subscriber.add(windowExact.cancel);
            subscriber.setProducer(windowExact.createProducer());
            return windowExact;
        } else if (r0 > r1) {
            WindowSkip windowSkip = new WindowSkip(subscriber, r1, r0);
            subscriber.add(windowSkip.cancel);
            subscriber.setProducer(windowSkip.createProducer());
            return windowSkip;
        } else {
            WindowOverlap windowOverlap = new WindowOverlap(subscriber, r1, r0);
            subscriber.add(windowOverlap.cancel);
            subscriber.setProducer(windowOverlap.createProducer());
            return windowOverlap;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorWindowWithSize$WindowExact */
    /* loaded from: classes6.dex */
    public static final class WindowExact<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel;
        int index;
        final int size;
        Subject<T, T> window;
        final AtomicInteger wip = new AtomicInteger(1);

        public WindowExact(Subscriber<? super Observable<T>> subscriber, int r2) {
            this.actual = subscriber;
            this.size = r2;
            Subscription create = Subscriptions.create(this);
            this.cancel = create;
            add(create);
            request(0L);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            int r0 = this.index;
            UnicastSubject unicastSubject = this.window;
            if (r0 == 0) {
                this.wip.getAndIncrement();
                unicastSubject = UnicastSubject.create(this.size, this);
                this.window = unicastSubject;
                this.actual.onNext(unicastSubject);
            }
            int r02 = r0 + 1;
            unicastSubject.onNext(t);
            if (r02 == this.size) {
                this.index = 0;
                this.window = null;
                unicastSubject.onCompleted();
                return;
            }
            this.index = r02;
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            Subject<T, T> subject = this.window;
            if (subject != null) {
                this.window = null;
                subject.onError(th);
            }
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            Subject<T, T> subject = this.window;
            if (subject != null) {
                this.window = null;
                subject.onCompleted();
            }
            this.actual.onCompleted();
        }

        Producer createProducer() {
            return new Producer() { // from class: rx.internal.operators.OperatorWindowWithSize.WindowExact.1
                @Override // p042rx.Producer
                public void request(long j) {
                    int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                    if (r2 < 0) {
                        throw new IllegalArgumentException("n >= 0 required but it was " + j);
                    } else if (r2 != 0) {
                        WindowExact.this.request(BackpressureUtils.multiplyCap(WindowExact.this.size, j));
                    }
                }
            };
        }

        @Override // p042rx.functions.Action0
        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                unsubscribe();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorWindowWithSize$WindowSkip */
    /* loaded from: classes6.dex */
    public static final class WindowSkip<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel;
        int index;
        final int size;
        final int skip;
        Subject<T, T> window;
        final AtomicInteger wip = new AtomicInteger(1);

        public WindowSkip(Subscriber<? super Observable<T>> subscriber, int r2, int r3) {
            this.actual = subscriber;
            this.size = r2;
            this.skip = r3;
            Subscription create = Subscriptions.create(this);
            this.cancel = create;
            add(create);
            request(0L);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            int r0 = this.index;
            UnicastSubject unicastSubject = this.window;
            if (r0 == 0) {
                this.wip.getAndIncrement();
                unicastSubject = UnicastSubject.create(this.size, this);
                this.window = unicastSubject;
                this.actual.onNext(unicastSubject);
            }
            int r02 = r0 + 1;
            if (unicastSubject != null) {
                unicastSubject.onNext(t);
            }
            if (r02 == this.size) {
                this.index = r02;
                this.window = null;
                unicastSubject.onCompleted();
            } else if (r02 == this.skip) {
                this.index = 0;
            } else {
                this.index = r02;
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            Subject<T, T> subject = this.window;
            if (subject != null) {
                this.window = null;
                subject.onError(th);
            }
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            Subject<T, T> subject = this.window;
            if (subject != null) {
                this.window = null;
                subject.onCompleted();
            }
            this.actual.onCompleted();
        }

        Producer createProducer() {
            return new WindowSkipProducer();
        }

        @Override // p042rx.functions.Action0
        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OperatorWindowWithSize$WindowSkip$WindowSkipProducer */
        /* loaded from: classes6.dex */
        public final class WindowSkipProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 4625807964358024108L;

            WindowSkipProducer() {
            }

            @Override // p042rx.Producer
            public void request(long j) {
                int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (r2 < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (r2 != 0) {
                    WindowSkip windowSkip = WindowSkip.this;
                    if (get() || !compareAndSet(false, true)) {
                        windowSkip.request(BackpressureUtils.multiplyCap(j, windowSkip.skip));
                    } else {
                        windowSkip.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(j, windowSkip.size), BackpressureUtils.multiplyCap(windowSkip.skip - windowSkip.size, j - 1)));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorWindowWithSize$WindowOverlap */
    /* loaded from: classes6.dex */
    public static final class WindowOverlap<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super Observable<T>> actual;
        final Subscription cancel;
        volatile boolean done;
        Throwable error;
        int index;
        int produced;
        final Queue<Subject<T, T>> queue;
        final int size;
        final int skip;
        final AtomicInteger wip = new AtomicInteger(1);
        final ArrayDeque<Subject<T, T>> windows = new ArrayDeque<>();
        final AtomicInteger drainWip = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();

        public WindowOverlap(Subscriber<? super Observable<T>> subscriber, int r4, int r5) {
            this.actual = subscriber;
            this.size = r4;
            this.skip = r5;
            Subscription create = Subscriptions.create(this);
            this.cancel = create;
            add(create);
            request(0L);
            this.queue = new SpscLinkedArrayQueue((r4 + (r5 - 1)) / r5);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            int r0 = this.index;
            ArrayDeque<Subject<T, T>> arrayDeque = this.windows;
            if (r0 == 0 && !this.actual.isUnsubscribed()) {
                this.wip.getAndIncrement();
                UnicastSubject create = UnicastSubject.create(16, this);
                arrayDeque.offer(create);
                this.queue.offer(create);
                drain();
            }
            Iterator<Subject<T, T>> it = this.windows.iterator();
            while (it.hasNext()) {
                it.next().onNext(t);
            }
            int r5 = this.produced + 1;
            if (r5 == this.size) {
                this.produced = r5 - this.skip;
                Subject<T, T> poll = arrayDeque.poll();
                if (poll != null) {
                    poll.onCompleted();
                }
            } else {
                this.produced = r5;
            }
            int r02 = r0 + 1;
            if (r02 == this.skip) {
                this.index = 0;
            } else {
                this.index = r02;
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            Iterator<Subject<T, T>> it = this.windows.iterator();
            while (it.hasNext()) {
                it.next().onError(th);
            }
            this.windows.clear();
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            Iterator<Subject<T, T>> it = this.windows.iterator();
            while (it.hasNext()) {
                it.next().onCompleted();
            }
            this.windows.clear();
            this.done = true;
            drain();
        }

        Producer createProducer() {
            return new WindowOverlapProducer();
        }

        @Override // p042rx.functions.Action0
        public void call() {
            if (this.wip.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
            if (r11 != 0) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
            if (checkTerminated(r15.done, r2.isEmpty(), r1, r2) == false) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
            if (r9 == 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
            if (r5 == Long.MAX_VALUE) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0056, code lost:
            r15.requested.addAndGet(-r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
            r4 = r0.addAndGet(-r4);
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                r15 = this;
                java.util.concurrent.atomic.AtomicInteger r0 = r15.drainWip
                int r1 = r0.getAndIncrement()
                if (r1 == 0) goto L9
                return
            L9:
                rx.Subscriber<? super rx.Observable<T>> r1 = r15.actual
                java.util.Queue<rx.subjects.Subject<T, T>> r2 = r15.queue
                r3 = 1
                r4 = 1
            Lf:
                java.util.concurrent.atomic.AtomicLong r5 = r15.requested
                long r5 = r5.get()
                r7 = 0
                r9 = r7
            L18:
                int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                if (r11 == 0) goto L3a
                boolean r12 = r15.done
                java.lang.Object r13 = r2.poll()
                rx.subjects.Subject r13 = (p042rx.subjects.Subject) r13
                if (r13 != 0) goto L28
                r14 = 1
                goto L29
            L28:
                r14 = 0
            L29:
                boolean r12 = r15.checkTerminated(r12, r14, r1, r2)
                if (r12 == 0) goto L30
                return
            L30:
                if (r14 == 0) goto L33
                goto L3a
            L33:
                r1.onNext(r13)
                r11 = 1
                long r9 = r9 + r11
                goto L18
            L3a:
                if (r11 != 0) goto L49
                boolean r11 = r15.done
                boolean r12 = r2.isEmpty()
                boolean r11 = r15.checkTerminated(r11, r12, r1, r2)
                if (r11 == 0) goto L49
                return
            L49:
                int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r11 == 0) goto L5c
                r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r11 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r11 == 0) goto L5c
                java.util.concurrent.atomic.AtomicLong r5 = r15.requested
                long r6 = -r9
                r5.addAndGet(r6)
            L5c:
                int r4 = -r4
                int r4 = r0.addAndGet(r4)
                if (r4 != 0) goto Lf
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.internal.operators.OperatorWindowWithSize.WindowOverlap.drain():void");
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super Subject<T, T>> subscriber, Queue<Subject<T, T>> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            } else if (z) {
                Throwable th = this.error;
                if (th != null) {
                    queue.clear();
                    subscriber.onError(th);
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

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: rx.internal.operators.OperatorWindowWithSize$WindowOverlap$WindowOverlapProducer */
        /* loaded from: classes6.dex */
        public final class WindowOverlapProducer extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 4625807964358024108L;

            WindowOverlapProducer() {
            }

            @Override // p042rx.Producer
            public void request(long j) {
                int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (r2 < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (r2 != 0) {
                    WindowOverlap windowOverlap = WindowOverlap.this;
                    if (!get() && compareAndSet(false, true)) {
                        windowOverlap.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(windowOverlap.skip, j - 1), windowOverlap.size));
                    } else {
                        WindowOverlap.this.request(BackpressureUtils.multiplyCap(windowOverlap.skip, j));
                    }
                    BackpressureUtils.getAndAddRequest(windowOverlap.requested, j);
                    windowOverlap.drain();
                }
            }
        }
    }
}
