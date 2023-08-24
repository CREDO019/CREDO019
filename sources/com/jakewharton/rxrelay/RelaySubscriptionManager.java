package com.jakewharton.rxrelay;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Actions;
import p042rx.subscriptions.Subscriptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class RelaySubscriptionManager<T> extends AtomicReference<State<T>> implements Observable.OnSubscribe<T> {
    boolean active;
    volatile Object latest;
    Action1<RelayObserver<T>> onAdded;
    Action1<RelayObserver<T>> onStart;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RelaySubscriptionManager() {
        super(State.EMPTY);
        this.active = true;
        this.onStart = Actions.empty();
        this.onAdded = Actions.empty();
    }

    public void call(Subscriber<? super T> subscriber) {
        RelayObserver<T> relayObserver = new RelayObserver<>(subscriber);
        addUnsubscriber(subscriber, relayObserver);
        this.onStart.call(relayObserver);
        if (subscriber.isUnsubscribed()) {
            return;
        }
        add(relayObserver);
        if (subscriber.isUnsubscribed()) {
            remove(relayObserver);
        }
    }

    private void addUnsubscriber(Subscriber<? super T> subscriber, final RelayObserver<T> relayObserver) {
        subscriber.add(Subscriptions.create(new Action0() { // from class: com.jakewharton.rxrelay.RelaySubscriptionManager.1
            @Override // p042rx.functions.Action0
            public void call() {
                RelaySubscriptionManager.this.remove(relayObserver);
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
    public RelayObserver<T>[] observers() {
        return get().observers;
    }

    private void add(RelayObserver<T> relayObserver) {
        State<T> state;
        do {
            state = get();
        } while (!compareAndSet(state, state.add(relayObserver)));
        this.onAdded.call(relayObserver);
    }

    void remove(RelayObserver<T> relayObserver) {
        State<T> state;
        State<T> remove;
        do {
            state = get();
            remove = state.remove(relayObserver);
            if (remove == state) {
                return;
            }
        } while (!compareAndSet(state, remove));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RelayObserver<T>[] next(Object obj) {
        setLatest(obj);
        return get().observers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class State<T> {
        static final State EMPTY = new State(new RelayObserver[0]);
        final RelayObserver[] observers;

        State(RelayObserver[] relayObserverArr) {
            this.observers = relayObserverArr;
        }

        State add(RelayObserver relayObserver) {
            RelayObserver[] relayObserverArr = this.observers;
            int length = relayObserverArr.length;
            RelayObserver[] relayObserverArr2 = new RelayObserver[length + 1];
            System.arraycopy(relayObserverArr, 0, relayObserverArr2, 0, length);
            relayObserverArr2[length] = relayObserver;
            return new State(relayObserverArr2);
        }

        State remove(RelayObserver relayObserver) {
            RelayObserver[] relayObserverArr = this.observers;
            int length = relayObserverArr.length;
            if (length == 1 && relayObserverArr[0] == relayObserver) {
                return EMPTY;
            }
            if (length == 0) {
                return this;
            }
            int r2 = length - 1;
            RelayObserver[] relayObserverArr2 = new RelayObserver[r2];
            int r6 = 0;
            for (RelayObserver relayObserver2 : relayObserverArr) {
                if (relayObserver2 != relayObserver) {
                    if (r6 == r2) {
                        return this;
                    }
                    relayObserverArr2[r6] = relayObserver2;
                    r6++;
                }
            }
            if (r6 == 0) {
                return EMPTY;
            }
            if (r6 < r2) {
                RelayObserver[] relayObserverArr3 = new RelayObserver[r6];
                System.arraycopy(relayObserverArr2, 0, relayObserverArr3, 0, r6);
                relayObserverArr2 = relayObserverArr3;
            }
            return new State(relayObserverArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class RelayObserver<T> implements Observer<T> {
        final Observer<? super T> actual;
        protected volatile boolean caughtUp;
        boolean emitting;
        boolean fastPath;
        boolean first = true;
        private volatile Object index;
        List<Object> queue;

        RelayObserver(Observer<? super T> observer) {
            this.actual = observer;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            throw new AssertionError();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            throw new AssertionError();
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
        private void emitLoop(java.util.List<java.lang.Object> r5, java.lang.Object r6) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.jakewharton.rxrelay.RelaySubscriptionManager.RelayObserver.emitLoop(java.util.List, java.lang.Object):void");
        }

        private void accept(Object obj) {
            if (obj != null) {
                NotificationLite.accept(this.actual, obj);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public <I> I index() {
            return (I) this.index;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void index(Object obj) {
            this.index = obj;
        }
    }
}
