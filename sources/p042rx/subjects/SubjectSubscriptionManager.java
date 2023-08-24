package p042rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Actions;
import p042rx.internal.operators.NotificationLite;
import p042rx.subscriptions.Subscriptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: rx.subjects.SubjectSubscriptionManager */
/* loaded from: classes6.dex */
public final class SubjectSubscriptionManager<T> extends AtomicReference<State<T>> implements Observable.OnSubscribe<T> {
    private static final long serialVersionUID = 6035251036011671568L;
    boolean active;
    volatile Object latest;
    Action1<SubjectObserver<T>> onAdded;
    Action1<SubjectObserver<T>> onStart;
    Action1<SubjectObserver<T>> onTerminated;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public SubjectSubscriptionManager() {
        super(State.EMPTY);
        this.active = true;
        this.onStart = Actions.empty();
        this.onAdded = Actions.empty();
        this.onTerminated = Actions.empty();
    }

    public void call(Subscriber<? super T> subscriber) {
        SubjectObserver<T> subjectObserver = new SubjectObserver<>(subscriber);
        addUnsubscriber(subscriber, subjectObserver);
        this.onStart.call(subjectObserver);
        if (!subscriber.isUnsubscribed() && add(subjectObserver) && subscriber.isUnsubscribed()) {
            remove(subjectObserver);
        }
    }

    void addUnsubscriber(Subscriber<? super T> subscriber, final SubjectObserver<T> subjectObserver) {
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.subjects.SubjectSubscriptionManager.1
            @Override // p042rx.functions.Action0
            public void call() {
                SubjectSubscriptionManager.this.remove(subjectObserver);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLatest(Object obj) {
        this.latest = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getLatest() {
        return this.latest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubjectObserver<T>[] observers() {
        return get().observers;
    }

    boolean add(SubjectObserver<T> subjectObserver) {
        State<T> state;
        do {
            state = get();
            if (state.terminated) {
                this.onTerminated.call(subjectObserver);
                return false;
            }
        } while (!compareAndSet(state, state.add(subjectObserver)));
        this.onAdded.call(subjectObserver);
        return true;
    }

    void remove(SubjectObserver<T> subjectObserver) {
        State<T> state;
        State<T> remove;
        do {
            state = get();
            if (state.terminated || (remove = state.remove(subjectObserver)) == state) {
                return;
            }
        } while (!compareAndSet(state, remove));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubjectObserver<T>[] next(Object obj) {
        setLatest(obj);
        return get().observers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubjectObserver<T>[] terminate(Object obj) {
        setLatest(obj);
        this.active = false;
        if (get().terminated) {
            return State.NO_OBSERVERS;
        }
        return getAndSet(State.TERMINATED).observers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: rx.subjects.SubjectSubscriptionManager$State */
    /* loaded from: classes6.dex */
    public static final class State<T> {
        static final State EMPTY;
        static final SubjectObserver[] NO_OBSERVERS;
        static final State TERMINATED;
        final SubjectObserver[] observers;
        final boolean terminated;

        static {
            SubjectObserver[] subjectObserverArr = new SubjectObserver[0];
            NO_OBSERVERS = subjectObserverArr;
            TERMINATED = new State(true, subjectObserverArr);
            EMPTY = new State(false, subjectObserverArr);
        }

        public State(boolean z, SubjectObserver[] subjectObserverArr) {
            this.terminated = z;
            this.observers = subjectObserverArr;
        }

        public State add(SubjectObserver subjectObserver) {
            SubjectObserver[] subjectObserverArr = this.observers;
            int length = subjectObserverArr.length;
            SubjectObserver[] subjectObserverArr2 = new SubjectObserver[length + 1];
            System.arraycopy(subjectObserverArr, 0, subjectObserverArr2, 0, length);
            subjectObserverArr2[length] = subjectObserver;
            return new State(this.terminated, subjectObserverArr2);
        }

        public State remove(SubjectObserver subjectObserver) {
            SubjectObserver[] subjectObserverArr = this.observers;
            int length = subjectObserverArr.length;
            if (length == 1 && subjectObserverArr[0] == subjectObserver) {
                return EMPTY;
            }
            if (length == 0) {
                return this;
            }
            int r2 = length - 1;
            SubjectObserver[] subjectObserverArr2 = new SubjectObserver[r2];
            int r6 = 0;
            for (SubjectObserver subjectObserver2 : subjectObserverArr) {
                if (subjectObserver2 != subjectObserver) {
                    if (r6 == r2) {
                        return this;
                    }
                    subjectObserverArr2[r6] = subjectObserver2;
                    r6++;
                }
            }
            if (r6 == 0) {
                return EMPTY;
            }
            if (r6 < r2) {
                SubjectObserver[] subjectObserverArr3 = new SubjectObserver[r6];
                System.arraycopy(subjectObserverArr2, 0, subjectObserverArr3, 0, r6);
                subjectObserverArr2 = subjectObserverArr3;
            }
            return new State(this.terminated, subjectObserverArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: rx.subjects.SubjectSubscriptionManager$SubjectObserver */
    /* loaded from: classes6.dex */
    public static final class SubjectObserver<T> implements Observer<T> {
        final Subscriber<? super T> actual;
        volatile boolean caughtUp;
        boolean emitting;
        boolean fastPath;
        boolean first = true;
        private volatile Object index;
        List<Object> queue;

        public SubjectObserver(Subscriber<? super T> subscriber) {
            this.actual = subscriber;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.actual.onCompleted();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void emitNext(Object obj) {
            if (!this.fastPath) {
                synchronized (this) {
                    this.first = false;
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList();
                        }
                        this.queue.add(obj);
                        return;
                    }
                    this.fastPath = true;
                }
            }
            NotificationLite.accept(this.actual, obj);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void emitFirst(Object obj) {
            synchronized (this) {
                if (this.first && !this.emitting) {
                    this.first = false;
                    this.emitting = obj != null;
                    if (obj != null) {
                        emitLoop(null, obj);
                    }
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x0038  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void emitLoop(java.util.List<java.lang.Object> r5, java.lang.Object r6) {
            /*
                r4 = this;
                r0 = 1
                r1 = 1
            L2:
                r2 = 0
                if (r5 == 0) goto L1a
                java.util.Iterator r5 = r5.iterator()     // Catch: java.lang.Throwable -> L17
            L9:
                boolean r3 = r5.hasNext()     // Catch: java.lang.Throwable -> L17
                if (r3 == 0) goto L1a
                java.lang.Object r3 = r5.next()     // Catch: java.lang.Throwable -> L17
                r4.accept(r3)     // Catch: java.lang.Throwable -> L17
                goto L9
            L17:
                r5 = move-exception
                r0 = 0
                goto L36
            L1a:
                if (r1 == 0) goto L20
                r4.accept(r6)     // Catch: java.lang.Throwable -> L17
                r1 = 0
            L20:
                monitor-enter(r4)     // Catch: java.lang.Throwable -> L17
                java.util.List<java.lang.Object> r5 = r4.queue     // Catch: java.lang.Throwable -> L2e
                r3 = 0
                r4.queue = r3     // Catch: java.lang.Throwable -> L2e
                if (r5 != 0) goto L2c
                r4.emitting = r2     // Catch: java.lang.Throwable -> L2e
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L34
                return
            L2c:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L2e
                goto L2
            L2e:
                r5 = move-exception
                r0 = 0
            L30:
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L34
                throw r5     // Catch: java.lang.Throwable -> L32
            L32:
                r5 = move-exception
                goto L36
            L34:
                r5 = move-exception
                goto L30
            L36:
                if (r0 != 0) goto L40
                monitor-enter(r4)
                r4.emitting = r2     // Catch: java.lang.Throwable -> L3d
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L3d
                goto L40
            L3d:
                r5 = move-exception
                monitor-exit(r4)     // Catch: java.lang.Throwable -> L3d
                throw r5
            L40:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.subjects.SubjectSubscriptionManager.SubjectObserver.emitLoop(java.util.List, java.lang.Object):void");
        }

        void accept(Object obj) {
            if (obj != null) {
                NotificationLite.accept(this.actual, obj);
            }
        }

        Observer<? super T> getActual() {
            return this.actual;
        }

        public <I> I index() {
            return (I) this.index;
        }

        public void index(Object obj) {
            this.index = obj;
        }
    }
}
