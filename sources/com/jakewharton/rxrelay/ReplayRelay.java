package com.jakewharton.rxrelay;

import com.jakewharton.rxrelay.RelaySubscriptionManager;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Scheduler;
import p042rx.functions.Action1;
import p042rx.functions.Func1;
import p042rx.schedulers.Timestamped;

/* loaded from: classes3.dex */
public class ReplayRelay<T> extends Relay<T, T> {
    private static final Object[] EMPTY_ARRAY = new Object[0];
    private final RelaySubscriptionManager<T> ssm;
    private final ReplayState<T> state;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public interface EvictionPolicy {
        void evict(NodeList<Object> nodeList);

        boolean test(Object obj, long j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public interface ReplayState<T> {
        boolean isEmpty();

        T latest();

        void next(T t);

        boolean replayObserver(RelaySubscriptionManager.RelayObserver<? super T> relayObserver);

        int size();

        T[] toArray(T[] tArr);
    }

    public static <T> ReplayRelay<T> create() {
        return create(16);
    }

    public static <T> ReplayRelay<T> create(int r2) {
        final UnboundedReplayState unboundedReplayState = new UnboundedReplayState(r2);
        RelaySubscriptionManager relaySubscriptionManager = new RelaySubscriptionManager();
        relaySubscriptionManager.onStart = new Action1<RelaySubscriptionManager.RelayObserver<T>>() { // from class: com.jakewharton.rxrelay.ReplayRelay.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
            }

            public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
                relayObserver.index(Integer.valueOf(UnboundedReplayState.this.replayObserverFromIndex(0, relayObserver).intValue()));
            }
        };
        relaySubscriptionManager.onAdded = new Action1<RelaySubscriptionManager.RelayObserver<T>>() { // from class: com.jakewharton.rxrelay.ReplayRelay.2
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
            }

            public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
                boolean z;
                synchronized (relayObserver) {
                    if (relayObserver.first && !relayObserver.emitting) {
                        relayObserver.first = false;
                        boolean z2 = true;
                        relayObserver.emitting = true;
                        try {
                            UnboundedReplayState unboundedReplayState2 = UnboundedReplayState.this;
                            while (true) {
                                int intValue = ((Integer) relayObserver.index()).intValue();
                                int r4 = unboundedReplayState2.get();
                                if (intValue != r4) {
                                    relayObserver.index(unboundedReplayState2.replayObserverFromIndex(Integer.valueOf(intValue), relayObserver));
                                }
                                try {
                                    synchronized (relayObserver) {
                                        try {
                                            if (r4 == unboundedReplayState2.get()) {
                                                relayObserver.emitting = false;
                                                return;
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            z2 = false;
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                                try {
                                    throw th;
                                } catch (Throwable th3) {
                                    z = z2;
                                    th = th3;
                                    if (!z) {
                                        synchronized (relayObserver) {
                                            relayObserver.emitting = false;
                                        }
                                    }
                                    throw th;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            z = false;
                        }
                    }
                }
            }
        };
        return new ReplayRelay<>(relaySubscriptionManager, relaySubscriptionManager, unboundedReplayState);
    }

    static <T> ReplayRelay<T> createUnbounded() {
        BoundedState boundedState = new BoundedState(new EmptyEvictionPolicy(), UtilityFunctions.identity(), UtilityFunctions.identity());
        return createWithState(boundedState, new DefaultOnAdd(boundedState));
    }

    public static <T> ReplayRelay<T> createWithSize(int r3) {
        BoundedState boundedState = new BoundedState(new SizeEvictionPolicy(r3), UtilityFunctions.identity(), UtilityFunctions.identity());
        return createWithState(boundedState, new DefaultOnAdd(boundedState));
    }

    public static <T> ReplayRelay<T> createWithTime(long j, TimeUnit timeUnit, Scheduler scheduler) {
        BoundedState boundedState = new BoundedState(new TimeEvictionPolicy(timeUnit.toMillis(j), scheduler), new AddTimestamped(scheduler), new RemoveTimestamped());
        return createWithState(boundedState, new TimedOnAdd(boundedState, scheduler));
    }

    public static <T> ReplayRelay<T> createWithTimeAndSize(long j, TimeUnit timeUnit, int r6, Scheduler scheduler) {
        BoundedState boundedState = new BoundedState(new PairEvictionPolicy(new SizeEvictionPolicy(r6), new TimeEvictionPolicy(timeUnit.toMillis(j), scheduler)), new AddTimestamped(scheduler), new RemoveTimestamped());
        return createWithState(boundedState, new TimedOnAdd(boundedState, scheduler));
    }

    private static <T> ReplayRelay<T> createWithState(final BoundedState<T> boundedState, Action1<RelaySubscriptionManager.RelayObserver<T>> action1) {
        RelaySubscriptionManager relaySubscriptionManager = new RelaySubscriptionManager();
        relaySubscriptionManager.onStart = action1;
        relaySubscriptionManager.onAdded = new Action1<RelaySubscriptionManager.RelayObserver<T>>() { // from class: com.jakewharton.rxrelay.ReplayRelay.3
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
            }

            public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
                boolean z;
                synchronized (relayObserver) {
                    if (relayObserver.first && !relayObserver.emitting) {
                        relayObserver.first = false;
                        boolean z2 = true;
                        relayObserver.emitting = true;
                        while (true) {
                            try {
                                NodeList.Node<Object> node = (NodeList.Node) relayObserver.index();
                                NodeList.Node<Object> tail = BoundedState.this.tail();
                                if (node != tail) {
                                    relayObserver.index(BoundedState.this.replayObserverFromIndex(node, relayObserver));
                                }
                                try {
                                    synchronized (relayObserver) {
                                        try {
                                            if (tail == BoundedState.this.tail()) {
                                                relayObserver.emitting = false;
                                                return;
                                            }
                                        } catch (Throwable th) {
                                            th = th;
                                            z2 = false;
                                        }
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                                try {
                                    throw th;
                                } catch (Throwable th3) {
                                    z = z2;
                                    th = th3;
                                    if (!z) {
                                        synchronized (relayObserver) {
                                            relayObserver.emitting = false;
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                z = false;
                            }
                        }
                    }
                }
            }
        };
        return new ReplayRelay<>(relaySubscriptionManager, relaySubscriptionManager, boundedState);
    }

    ReplayRelay(Observable.OnSubscribe<T> onSubscribe, RelaySubscriptionManager<T> relaySubscriptionManager, ReplayState<T> replayState) {
        super(onSubscribe);
        this.ssm = relaySubscriptionManager;
        this.state = replayState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p042rx.functions.Action1
    public void call(T t) {
        RelaySubscriptionManager.RelayObserver<T>[] observers;
        if (this.ssm.active) {
            this.state.next(t);
            for (RelaySubscriptionManager.RelayObserver<? super T> relayObserver : this.ssm.observers()) {
                if (caughtUp(relayObserver)) {
                    relayObserver.onNext(t);
                }
            }
        }
    }

    int subscriberCount() {
        return this.ssm.get().observers.length;
    }

    @Override // com.jakewharton.rxrelay.Relay
    public boolean hasObservers() {
        return this.ssm.observers().length > 0;
    }

    private boolean caughtUp(RelaySubscriptionManager.RelayObserver<? super T> relayObserver) {
        if (relayObserver.caughtUp) {
            return true;
        }
        if (this.state.replayObserver(relayObserver)) {
            relayObserver.caughtUp = true;
            relayObserver.index(null);
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class UnboundedReplayState<T> extends AtomicInteger implements ReplayState<T> {
        private final ArrayList<Object> list;

        UnboundedReplayState(int r2) {
            this.list = new ArrayList<>(r2);
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public void next(T t) {
            this.list.add(NotificationLite.next(t));
            getAndIncrement();
        }

        private void accept(Observer<? super T> observer, int r3) {
            NotificationLite.accept(observer, this.list.get(r3));
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public boolean replayObserver(RelaySubscriptionManager.RelayObserver<? super T> relayObserver) {
            synchronized (relayObserver) {
                relayObserver.first = false;
                if (relayObserver.emitting) {
                    return false;
                }
                Integer num = (Integer) relayObserver.index();
                if (num != null) {
                    relayObserver.index(Integer.valueOf(replayObserverFromIndex(num, relayObserver).intValue()));
                    return true;
                }
                throw new IllegalStateException("failed to find lastEmittedLink for: " + relayObserver);
            }
        }

        Integer replayObserverFromIndex(Integer num, RelaySubscriptionManager.RelayObserver<? super T> relayObserver) {
            int intValue = num.intValue();
            while (intValue < get()) {
                accept(relayObserver, intValue);
                intValue++;
            }
            return Integer.valueOf(intValue);
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public int size() {
            return get();
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public boolean isEmpty() {
            return size() == 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public T[] toArray(T[] tArr) {
            int size = size();
            if (size > 0) {
                if (size > tArr.length) {
                    tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), size));
                }
                for (int r2 = 0; r2 < size; r2++) {
                    tArr[r2] = this.list.get(r2);
                }
                if (tArr.length > size) {
                    tArr[size] = null;
                }
            } else if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public T latest() {
            int r0 = get();
            if (r0 > 0) {
                return (T) NotificationLite.getValue(this.list.get(r0 - 1));
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class BoundedState<T> implements ReplayState<T> {
        final Func1<Object, Object> enterTransform;
        final EvictionPolicy evictionPolicy;
        final Func1<Object, Object> leaveTransform;
        final NodeList<Object> list;
        volatile NodeList.Node<Object> tail;
        volatile boolean terminated;

        BoundedState(EvictionPolicy evictionPolicy, Func1<Object, Object> func1, Func1<Object, Object> func12) {
            NodeList<Object> nodeList = new NodeList<>();
            this.list = nodeList;
            this.tail = nodeList.tail;
            this.evictionPolicy = evictionPolicy;
            this.enterTransform = func1;
            this.leaveTransform = func12;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public void next(T t) {
            if (this.terminated) {
                return;
            }
            this.list.addLast(this.enterTransform.call(NotificationLite.next(t)));
            this.evictionPolicy.evict(this.list);
            this.tail = this.list.tail;
        }

        private void accept(Observer<? super T> observer, NodeList.Node<Object> node) {
            NotificationLite.accept(observer, this.leaveTransform.call(node.value));
        }

        private void acceptTest(Observer<? super T> observer, NodeList.Node<Object> node, long j) {
            Object obj = node.value;
            if (this.evictionPolicy.test(obj, j)) {
                return;
            }
            NotificationLite.accept(observer, this.leaveTransform.call(obj));
        }

        NodeList.Node<Object> head() {
            return this.list.head;
        }

        NodeList.Node<Object> tail() {
            return this.tail;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public boolean replayObserver(RelaySubscriptionManager.RelayObserver<? super T> relayObserver) {
            synchronized (relayObserver) {
                relayObserver.first = false;
                if (relayObserver.emitting) {
                    return false;
                }
                relayObserver.index(replayObserverFromIndex((NodeList.Node) relayObserver.index(), relayObserver));
                return true;
            }
        }

        NodeList.Node<Object> replayObserverFromIndex(NodeList.Node<Object> node, RelaySubscriptionManager.RelayObserver<? super T> relayObserver) {
            while (node != tail()) {
                accept(relayObserver, node.next);
                node = node.next;
            }
            return node;
        }

        NodeList.Node<Object> replayObserverFromIndexTest(NodeList.Node<Object> node, RelaySubscriptionManager.RelayObserver<? super T> relayObserver, long j) {
            while (node != tail()) {
                acceptTest(relayObserver, node.next, j);
                node = node.next;
            }
            return node;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public int size() {
            int r1 = 0;
            for (NodeList.Node node = head().next; node != null; node = node.next) {
                r1++;
            }
            return r1;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public boolean isEmpty() {
            return head().next == null;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (NodeList.Node node = head().next; node != null; node = node.next) {
                arrayList.add(this.leaveTransform.call(node.value));
            }
            return (T[]) arrayList.toArray(tArr);
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.ReplayState
        public T latest() {
            NodeList.Node<Object> node = head().next;
            if (node == null) {
                return null;
            }
            while (node != tail()) {
                node = node.next;
            }
            return (T) NotificationLite.getValue(this.leaveTransform.call(node.value));
        }
    }

    /* loaded from: classes3.dex */
    private static final class SizeEvictionPolicy implements EvictionPolicy {
        private final int maxSize;

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public boolean test(Object obj, long j) {
            return false;
        }

        SizeEvictionPolicy(int r1) {
            this.maxSize = r1;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public void evict(NodeList<Object> nodeList) {
            while (nodeList.size() > this.maxSize) {
                nodeList.removeFirst();
            }
        }
    }

    /* loaded from: classes3.dex */
    private static final class TimeEvictionPolicy implements EvictionPolicy {
        private final long maxAgeMillis;
        private final Scheduler scheduler;

        TimeEvictionPolicy(long j, Scheduler scheduler) {
            this.maxAgeMillis = j;
            this.scheduler = scheduler;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public void evict(NodeList<Object> nodeList) {
            long now = this.scheduler.now();
            while (!nodeList.isEmpty() && test(nodeList.head.next.value, now)) {
                nodeList.removeFirst();
            }
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public boolean test(Object obj, long j) {
            return ((Timestamped) obj).getTimestampMillis() <= j - this.maxAgeMillis;
        }
    }

    /* loaded from: classes3.dex */
    private static final class PairEvictionPolicy implements EvictionPolicy {
        private final EvictionPolicy first;
        private final EvictionPolicy second;

        PairEvictionPolicy(EvictionPolicy evictionPolicy, EvictionPolicy evictionPolicy2) {
            this.first = evictionPolicy;
            this.second = evictionPolicy2;
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public void evict(NodeList<Object> nodeList) {
            this.first.evict(nodeList);
            this.second.evict(nodeList);
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public boolean test(Object obj, long j) {
            return this.first.test(obj, j) || this.second.test(obj, j);
        }
    }

    /* loaded from: classes3.dex */
    private static final class AddTimestamped implements Func1<Object, Object> {
        private final Scheduler scheduler;

        AddTimestamped(Scheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override // p042rx.functions.Func1
        public Object call(Object obj) {
            return new Timestamped(this.scheduler.now(), obj);
        }
    }

    /* loaded from: classes3.dex */
    static final class RemoveTimestamped implements Func1<Object, Object> {
        RemoveTimestamped() {
        }

        @Override // p042rx.functions.Func1
        public Object call(Object obj) {
            return ((Timestamped) obj).getValue();
        }
    }

    /* loaded from: classes3.dex */
    private static final class DefaultOnAdd<T> implements Action1<RelaySubscriptionManager.RelayObserver<T>> {
        private final BoundedState<T> state;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
        }

        DefaultOnAdd(BoundedState<T> boundedState) {
            this.state = boundedState;
        }

        public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
            BoundedState<T> boundedState = this.state;
            relayObserver.index(boundedState.replayObserverFromIndex(boundedState.head(), relayObserver));
        }
    }

    /* loaded from: classes3.dex */
    private static final class TimedOnAdd<T> implements Action1<RelaySubscriptionManager.RelayObserver<T>> {
        private final Scheduler scheduler;
        private final BoundedState<T> state;

        @Override // p042rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((RelaySubscriptionManager.RelayObserver) ((RelaySubscriptionManager.RelayObserver) obj));
        }

        TimedOnAdd(BoundedState<T> boundedState, Scheduler scheduler) {
            this.state = boundedState;
            this.scheduler = scheduler;
        }

        public void call(RelaySubscriptionManager.RelayObserver<T> relayObserver) {
            NodeList.Node<Object> replayObserverFromIndex;
            if (!this.state.terminated) {
                BoundedState<T> boundedState = this.state;
                replayObserverFromIndex = boundedState.replayObserverFromIndexTest(boundedState.head(), relayObserver, this.scheduler.now());
            } else {
                BoundedState<T> boundedState2 = this.state;
                replayObserverFromIndex = boundedState2.replayObserverFromIndex(boundedState2.head(), relayObserver);
            }
            relayObserver.index(replayObserverFromIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class NodeList<T> {
        final Node<T> head;
        int size;
        Node<T> tail;

        NodeList() {
            Node<T> node = new Node<>(null);
            this.head = node;
            this.tail = node;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public static final class Node<T> {
            volatile Node<T> next;
            final T value;

            Node(T t) {
                this.value = t;
            }
        }

        void addLast(T t) {
            Node<T> node = this.tail;
            Node<T> node2 = new Node<>(t);
            node.next = node2;
            this.tail = node2;
            this.size++;
        }

        T removeFirst() {
            if (this.head.next == null) {
                throw new IllegalStateException("Empty!");
            }
            Node<T> node = this.head.next;
            this.head.next = node.next;
            if (this.head.next == null) {
                this.tail = this.head;
            }
            this.size--;
            return node.value;
        }

        boolean isEmpty() {
            return this.size == 0;
        }

        int size() {
            return this.size;
        }

        void clear() {
            this.tail = this.head;
            this.size = 0;
        }
    }

    /* loaded from: classes3.dex */
    private static final class EmptyEvictionPolicy implements EvictionPolicy {
        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public void evict(NodeList<Object> nodeList) {
        }

        @Override // com.jakewharton.rxrelay.ReplayRelay.EvictionPolicy
        public boolean test(Object obj, long j) {
            return true;
        }

        EmptyEvictionPolicy() {
        }
    }

    public int size() {
        return this.state.size();
    }

    public boolean hasAnyValue() {
        return !this.state.isEmpty();
    }

    public boolean hasValue() {
        return hasAnyValue();
    }

    public T[] getValues(T[] tArr) {
        return this.state.toArray(tArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = EMPTY_ARRAY;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    public T getValue() {
        return this.state.latest();
    }
}
