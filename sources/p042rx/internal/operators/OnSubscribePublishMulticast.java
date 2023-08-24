package p042rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.MissingBackpressureException;
import p042rx.internal.util.atomic.SpscAtomicArrayQueue;
import p042rx.internal.util.unsafe.SpscArrayQueue;
import p042rx.internal.util.unsafe.UnsafeAccess;

/* renamed from: rx.internal.operators.OnSubscribePublishMulticast */
/* loaded from: classes6.dex */
public final class OnSubscribePublishMulticast<T> extends AtomicInteger implements Observable.OnSubscribe<T>, Observer<T>, Subscription {
    static final PublishProducer<?>[] EMPTY = new PublishProducer[0];
    static final PublishProducer<?>[] TERMINATED = new PublishProducer[0];
    private static final long serialVersionUID = -3741892510772238743L;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final ParentSubscriber<T> parent;
    final int prefetch;
    volatile Producer producer;
    final Queue<T> queue;
    volatile PublishProducer<T>[] subscribers;

    @Override // p042rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OnSubscribePublishMulticast(int r3, boolean z) {
        if (r3 <= 0) {
            throw new IllegalArgumentException("prefetch > 0 required but it was " + r3);
        }
        this.prefetch = r3;
        this.delayError = z;
        if (UnsafeAccess.isUnsafeAvailable()) {
            this.queue = new SpscArrayQueue(r3);
        } else {
            this.queue = new SpscAtomicArrayQueue(r3);
        }
        this.subscribers = EMPTY;
        this.parent = new ParentSubscriber<>(this);
    }

    public void call(Subscriber<? super T> subscriber) {
        PublishProducer<T> publishProducer = new PublishProducer<>(subscriber, this);
        subscriber.add(publishProducer);
        subscriber.setProducer(publishProducer);
        if (add(publishProducer)) {
            if (publishProducer.isUnsubscribed()) {
                remove(publishProducer);
                return;
            } else {
                drain();
                return;
            }
        }
        Throwable th = this.error;
        if (th != null) {
            subscriber.onError(th);
        } else {
            subscriber.onCompleted();
        }
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        if (!this.queue.offer(t)) {
            this.parent.unsubscribe();
            this.error = new MissingBackpressureException("Queue full?!");
            this.done = true;
        }
        drain();
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.done = true;
        drain();
    }

    void setProducer(Producer producer) {
        this.producer = producer;
        producer.request(this.prefetch);
    }

    void drain() {
        int r10;
        if (getAndIncrement() != 0) {
            return;
        }
        Queue<T> queue = this.queue;
        int r2 = 0;
        do {
            long j = Long.MAX_VALUE;
            PublishProducer<T>[] publishProducerArr = this.subscribers;
            int length = publishProducerArr.length;
            for (PublishProducer<T> publishProducer : publishProducerArr) {
                j = Math.min(j, publishProducer.get());
            }
            if (length != 0) {
                long j2 = 0;
                while (true) {
                    r10 = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (r10 == 0) {
                        break;
                    }
                    boolean z = this.done;
                    T poll = queue.poll();
                    boolean z2 = poll == null;
                    if (checkTerminated(z, z2)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    for (PublishProducer<T> publishProducer2 : publishProducerArr) {
                        publishProducer2.actual.onNext(poll);
                    }
                    j2++;
                }
                if (r10 == 0 && checkTerminated(this.done, queue.isEmpty())) {
                    return;
                }
                if (j2 != 0) {
                    Producer producer = this.producer;
                    if (producer != null) {
                        producer.request(j2);
                    }
                    for (PublishProducer<T> publishProducer3 : publishProducerArr) {
                        BackpressureUtils.produced(publishProducer3, j2);
                    }
                }
            }
            r2 = addAndGet(-r2);
        } while (r2 != 0);
    }

    boolean checkTerminated(boolean z, boolean z2) {
        int r0 = 0;
        if (z) {
            if (!this.delayError) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    PublishProducer<T>[] terminate = terminate();
                    int length = terminate.length;
                    while (r0 < length) {
                        terminate[r0].actual.onError(th);
                        r0++;
                    }
                    return true;
                } else if (z2) {
                    PublishProducer<T>[] terminate2 = terminate();
                    int length2 = terminate2.length;
                    while (r0 < length2) {
                        terminate2[r0].actual.onCompleted();
                        r0++;
                    }
                    return true;
                }
            } else if (z2) {
                PublishProducer<T>[] terminate3 = terminate();
                Throwable th2 = this.error;
                if (th2 != null) {
                    int length3 = terminate3.length;
                    while (r0 < length3) {
                        terminate3[r0].actual.onError(th2);
                        r0++;
                    }
                } else {
                    int length4 = terminate3.length;
                    while (r0 < length4) {
                        terminate3[r0].actual.onCompleted();
                        r0++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    PublishProducer<T>[] terminate() {
        PublishProducer<T>[] publishProducerArr = this.subscribers;
        PublishProducer<?>[] publishProducerArr2 = TERMINATED;
        if (publishProducerArr != publishProducerArr2) {
            synchronized (this) {
                publishProducerArr = this.subscribers;
                if (publishProducerArr != publishProducerArr2) {
                    this.subscribers = publishProducerArr2;
                }
            }
        }
        return publishProducerArr;
    }

    boolean add(PublishProducer<T> publishProducer) {
        PublishProducer<T>[] publishProducerArr = this.subscribers;
        PublishProducer<?>[] publishProducerArr2 = TERMINATED;
        if (publishProducerArr == publishProducerArr2) {
            return false;
        }
        synchronized (this) {
            PublishProducer<T>[] publishProducerArr3 = this.subscribers;
            if (publishProducerArr3 == publishProducerArr2) {
                return false;
            }
            int length = publishProducerArr3.length;
            PublishProducer<T>[] publishProducerArr4 = new PublishProducer[length + 1];
            System.arraycopy(publishProducerArr3, 0, publishProducerArr4, 0, length);
            publishProducerArr4[length] = publishProducer;
            this.subscribers = publishProducerArr4;
            return true;
        }
    }

    void remove(PublishProducer<T> publishProducer) {
        PublishProducer<?>[] publishProducerArr;
        PublishProducer[] publishProducerArr2;
        PublishProducer<T>[] publishProducerArr3 = this.subscribers;
        PublishProducer<?>[] publishProducerArr4 = TERMINATED;
        if (publishProducerArr3 == publishProducerArr4 || publishProducerArr3 == (publishProducerArr = EMPTY)) {
            return;
        }
        synchronized (this) {
            PublishProducer<T>[] publishProducerArr5 = this.subscribers;
            if (publishProducerArr5 != publishProducerArr4 && publishProducerArr5 != publishProducerArr) {
                int r1 = -1;
                int length = publishProducerArr5.length;
                int r4 = 0;
                while (true) {
                    if (r4 >= length) {
                        break;
                    } else if (publishProducerArr5[r4] == publishProducer) {
                        r1 = r4;
                        break;
                    } else {
                        r4++;
                    }
                }
                if (r1 < 0) {
                    return;
                }
                if (length == 1) {
                    publishProducerArr2 = EMPTY;
                } else {
                    PublishProducer<T>[] publishProducerArr6 = new PublishProducer[length - 1];
                    System.arraycopy(publishProducerArr5, 0, publishProducerArr6, 0, r1);
                    System.arraycopy(publishProducerArr5, r1 + 1, publishProducerArr6, r1, (length - r1) - 1);
                    publishProducerArr2 = publishProducerArr6;
                }
                this.subscribers = publishProducerArr2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribePublishMulticast$ParentSubscriber */
    /* loaded from: classes6.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        final OnSubscribePublishMulticast<T> state;

        public ParentSubscriber(OnSubscribePublishMulticast<T> onSubscribePublishMulticast) {
            this.state = onSubscribePublishMulticast;
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

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.state.setProducer(producer);
        }
    }

    public Subscriber<T> subscriber() {
        return this.parent;
    }

    @Override // p042rx.Subscription
    public void unsubscribe() {
        this.parent.unsubscribe();
    }

    @Override // p042rx.Subscription
    public boolean isUnsubscribed() {
        return this.parent.isUnsubscribed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OnSubscribePublishMulticast$PublishProducer */
    /* loaded from: classes6.dex */
    public static final class PublishProducer<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = 960704844171597367L;
        final Subscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final OnSubscribePublishMulticast<T> parent;

        public PublishProducer(Subscriber<? super T> subscriber, OnSubscribePublishMulticast<T> onSubscribePublishMulticast) {
            this.actual = subscriber;
            this.parent = onSubscribePublishMulticast;
        }

        @Override // p042rx.Producer
        public void request(long j) {
            int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (r2 < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + j);
            } else if (r2 != 0) {
                BackpressureUtils.getAndAddRequest(this, j);
                this.parent.drain();
            }
        }

        @Override // p042rx.Subscription
        public boolean isUnsubscribed() {
            return this.once.get();
        }

        @Override // p042rx.Subscription
        public void unsubscribe() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }
    }
}
