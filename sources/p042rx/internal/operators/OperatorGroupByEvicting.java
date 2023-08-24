package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;
import p042rx.functions.Action1;
import p042rx.functions.Func1;
import p042rx.internal.producers.ProducerArbiter;
import p042rx.internal.util.RxRingBuffer;
import p042rx.internal.util.UtilityFunctions;
import p042rx.observables.GroupedObservable;
import p042rx.observers.Subscribers;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorGroupByEvicting */
/* loaded from: classes6.dex */
public final class OperatorGroupByEvicting<T, K, V> implements Observable.Operator<GroupedObservable<K, V>, T> {
    final int bufferSize;
    final boolean delayError;
    final Func1<? super T, ? extends K> keySelector;
    final Func1<Action1<Object>, Map<K, Object>> mapFactory;
    final Func1<? super T, ? extends V> valueSelector;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorGroupByEvicting(Func1<? super T, ? extends K> func1) {
        this(func1, UtilityFunctions.identity(), RxRingBuffer.SIZE, false, null);
    }

    public OperatorGroupByEvicting(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, RxRingBuffer.SIZE, false, null);
    }

    public OperatorGroupByEvicting(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int r3, boolean z, Func1<Action1<Object>, Map<K, Object>> func13) {
        this.keySelector = func1;
        this.valueSelector = func12;
        this.bufferSize = r3;
        this.delayError = z;
        this.mapFactory = func13;
    }

    public Subscriber<? super T> call(Subscriber<? super GroupedObservable<K, V>> subscriber) {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        Map<K, Object> call;
        if (this.mapFactory == null) {
            concurrentLinkedQueue = null;
            call = new ConcurrentHashMap<>();
        } else {
            concurrentLinkedQueue = new ConcurrentLinkedQueue();
            try {
                call = this.mapFactory.call(new EvictionAction(concurrentLinkedQueue));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber);
                Subscriber<? super T> empty = Subscribers.empty();
                empty.unsubscribe();
                return empty;
            }
        }
        final GroupBySubscriber groupBySubscriber = new GroupBySubscriber(subscriber, this.keySelector, this.valueSelector, this.bufferSize, this.delayError, call, concurrentLinkedQueue);
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorGroupByEvicting.1
            @Override // p042rx.functions.Action0
            public void call() {
                groupBySubscriber.cancel();
            }
        }));
        subscriber.setProducer(groupBySubscriber.producer);
        return groupBySubscriber;
    }

    /* renamed from: rx.internal.operators.OperatorGroupByEvicting$GroupByProducer */
    /* loaded from: classes6.dex */
    public static final class GroupByProducer implements Producer {
        final GroupBySubscriber<?, ?, ?> parent;

        public GroupByProducer(GroupBySubscriber<?, ?, ?> groupBySubscriber) {
            this.parent = groupBySubscriber;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            this.parent.requestMore(j);
        }
    }

    /* renamed from: rx.internal.operators.OperatorGroupByEvicting$GroupBySubscriber */
    /* loaded from: classes6.dex */
    public static final class GroupBySubscriber<T, K, V> extends Subscriber<T> {
        static final Object NULL_KEY = new Object();
        final Subscriber<? super GroupedObservable<K, V>> actual;
        final int bufferSize;
        final AtomicBoolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final Queue<GroupedUnicast<K, V>> evictedGroups;
        final AtomicInteger groupCount;
        final Map<K, GroupedUnicast<K, V>> groups;
        final Func1<? super T, ? extends K> keySelector;
        final GroupByProducer producer;
        final Queue<GroupedUnicast<K, V>> queue = new ConcurrentLinkedQueue();
        final AtomicLong requested;

        /* renamed from: s */
        final ProducerArbiter f2563s;
        final Func1<? super T, ? extends V> valueSelector;
        final AtomicInteger wip;

        public GroupBySubscriber(Subscriber<? super GroupedObservable<K, V>> subscriber, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int r4, boolean z, Map<K, GroupedUnicast<K, V>> map, Queue<GroupedUnicast<K, V>> queue) {
            this.actual = subscriber;
            this.keySelector = func1;
            this.valueSelector = func12;
            this.bufferSize = r4;
            this.delayError = z;
            ProducerArbiter producerArbiter = new ProducerArbiter();
            this.f2563s = producerArbiter;
            producerArbiter.request(r4);
            this.producer = new GroupByProducer(this);
            this.cancelled = new AtomicBoolean();
            this.requested = new AtomicLong();
            this.groupCount = new AtomicInteger(1);
            this.wip = new AtomicInteger();
            this.groups = map;
            this.evictedGroups = queue;
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.f2563s.setProducer(producer);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // p042rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            Queue<?> queue = this.queue;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
            try {
                K call = this.keySelector.call(t);
                Object obj = call != null ? call : NULL_KEY;
                GroupedUnicast groupedUnicast = this.groups.get(obj);
                if (groupedUnicast == null) {
                    if (this.cancelled.get()) {
                        return;
                    }
                    groupedUnicast = GroupedUnicast.createWith(call, this.bufferSize, this, this.delayError);
                    this.groups.put(obj, groupedUnicast);
                    this.groupCount.getAndIncrement();
                    queue.offer(groupedUnicast);
                    drain();
                }
                try {
                    groupedUnicast.onNext(this.valueSelector.call(t));
                    if (this.evictedGroups == null) {
                        return;
                    }
                    while (true) {
                        GroupedUnicast<K, V> poll = this.evictedGroups.poll();
                        if (poll == null) {
                            return;
                        }
                        poll.onComplete();
                    }
                } catch (Throwable th) {
                    unsubscribe();
                    errorAll(subscriber, queue, th);
                }
            } catch (Throwable th2) {
                unsubscribe();
                errorAll(subscriber, queue, th2);
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaHooks.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            this.groupCount.decrementAndGet();
            drain();
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            if (this.done) {
                return;
            }
            for (GroupedUnicast<K, V> groupedUnicast : this.groups.values()) {
                groupedUnicast.onComplete();
            }
            this.groups.clear();
            Queue<GroupedUnicast<K, V>> queue = this.evictedGroups;
            if (queue != null) {
                queue.clear();
            }
            this.done = true;
            this.groupCount.decrementAndGet();
            drain();
        }

        public void requestMore(long j) {
            if (j < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            }
            BackpressureUtils.getAndAddRequest(this.requested, j);
            drain();
        }

        public void cancel() {
            if (this.cancelled.compareAndSet(false, true) && this.groupCount.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) NULL_KEY;
            }
            if (this.groups.remove(k) == null || this.groupCount.decrementAndGet() != 0) {
                return;
            }
            unsubscribe();
        }

        void drain() {
            if (this.wip.getAndIncrement() != 0) {
                return;
            }
            Queue<GroupedUnicast<K, V>> queue = this.queue;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
            int r3 = 1;
            while (!checkTerminated(this.done, queue.isEmpty(), subscriber, queue)) {
                long j = this.requested.get();
                boolean z = j == Long.MAX_VALUE;
                long j2 = 0;
                while (j != 0) {
                    boolean z2 = this.done;
                    GroupedUnicast<K, V> poll = queue.poll();
                    boolean z3 = poll == null;
                    if (checkTerminated(z2, z3, subscriber, queue)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    subscriber.onNext(poll);
                    j--;
                    j2--;
                }
                if (j2 != 0) {
                    if (!z) {
                        this.requested.addAndGet(j2);
                    }
                    this.f2563s.request(-j2);
                }
                r3 = this.wip.addAndGet(-r3);
                if (r3 == 0) {
                    return;
                }
            }
        }

        void errorAll(Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue, Throwable th) {
            queue.clear();
            ArrayList<GroupedUnicast> arrayList = new ArrayList(this.groups.values());
            this.groups.clear();
            Queue<GroupedUnicast<K, V>> queue2 = this.evictedGroups;
            if (queue2 != null) {
                queue2.clear();
            }
            for (GroupedUnicast groupedUnicast : arrayList) {
                groupedUnicast.onError(th);
            }
            subscriber.onError(th);
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue) {
            if (z) {
                Throwable th = this.error;
                if (th != null) {
                    errorAll(subscriber, queue, th);
                    return true;
                } else if (z2) {
                    this.actual.onCompleted();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorGroupByEvicting$EvictionAction */
    /* loaded from: classes6.dex */
    public static class EvictionAction<K, V> implements Action1<GroupedUnicast<K, V>> {
        final Queue<GroupedUnicast<K, V>> evictedGroups;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((GroupedUnicast) ((GroupedUnicast) obj));
        }

        EvictionAction(Queue<GroupedUnicast<K, V>> queue) {
            this.evictedGroups = queue;
        }

        public void call(GroupedUnicast<K, V> groupedUnicast) {
            this.evictedGroups.offer(groupedUnicast);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorGroupByEvicting$GroupedUnicast */
    /* loaded from: classes6.dex */
    public static final class GroupedUnicast<K, T> extends GroupedObservable<K, T> {
        final State<T, K> state;

        public static <T, K> GroupedUnicast<K, T> createWith(K k, int r2, GroupBySubscriber<?, K, T> groupBySubscriber, boolean z) {
            return new GroupedUnicast<>(k, new State(r2, groupBySubscriber, k, z));
        }

        protected GroupedUnicast(K k, State<T, K> state) {
            super(k, state);
            this.state = state;
        }

        public void onNext(T t) {
            this.state.onNext(t);
        }

        public void onError(Throwable th) {
            this.state.onError(th);
        }

        public void onComplete() {
            this.state.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorGroupByEvicting$State */
    /* loaded from: classes6.dex */
    public static final class State<T, K> extends AtomicInteger implements Producer, Subscription, Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final GroupBySubscriber<?, K, T> parent;
        final Queue<Object> queue = new ConcurrentLinkedQueue();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final AtomicReference<Subscriber<? super T>> actual = new AtomicReference<>();
        final AtomicBoolean once = new AtomicBoolean();
        final AtomicLong requested = new AtomicLong();

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public State(int r1, GroupBySubscriber<?, K, T> groupBySubscriber, K k, boolean z) {
            this.parent = groupBySubscriber;
            this.key = k;
            this.delayError = z;
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
        public boolean isUnsubscribed() {
            return this.cancelled.get();
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.parent.cancel(this.key);
            }
        }

        public void call(Subscriber<? super T> subscriber) {
            if (this.once.compareAndSet(false, true)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                this.actual.lazySet(subscriber);
                drain();
                return;
            }
            subscriber.onError(new IllegalStateException("Only one Subscriber allowed!"));
        }

        public void onNext(T t) {
            if (t == null) {
                this.error = new NullPointerException();
                this.done = true;
            } else {
                this.queue.offer(NotificationLite.next(t));
            }
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Queue<Object> queue = this.queue;
            boolean z = this.delayError;
            Subscriber<? super T> subscriber = this.actual.get();
            int r5 = 1;
            while (true) {
                if (subscriber != null) {
                    if (checkTerminated(this.done, queue.isEmpty(), subscriber, z)) {
                        return;
                    }
                    long j = this.requested.get();
                    boolean z2 = j == Long.MAX_VALUE;
                    long j2 = 0;
                    while (j != 0) {
                        boolean z3 = this.done;
                        Object poll = queue.poll();
                        boolean z4 = poll == null;
                        if (checkTerminated(z3, z4, subscriber, z)) {
                            return;
                        }
                        if (z4) {
                            break;
                        }
                        subscriber.onNext((Object) NotificationLite.getValue(poll));
                        j--;
                        j2--;
                    }
                    if (j2 != 0) {
                        if (!z2) {
                            this.requested.addAndGet(j2);
                        }
                        this.parent.f2563s.request(-j2);
                    }
                }
                r5 = addAndGet(-r5);
                if (r5 == 0) {
                    return;
                }
                if (subscriber == null) {
                    subscriber = this.actual.get();
                }
            }
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = this.error;
                if (th2 != null) {
                    this.queue.clear();
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
    }
}
