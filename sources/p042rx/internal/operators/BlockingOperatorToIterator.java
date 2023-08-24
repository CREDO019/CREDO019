package p042rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import p042rx.Notification;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.internal.util.RxRingBuffer;

/* renamed from: rx.internal.operators.BlockingOperatorToIterator */
/* loaded from: classes6.dex */
public final class BlockingOperatorToIterator {
    private BlockingOperatorToIterator() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterator<T> toIterator(Observable<? extends T> observable) {
        SubscriberIterator subscriberIterator = new SubscriberIterator();
        observable.materialize().subscribe((Subscriber<? super Notification<? extends T>>) subscriberIterator);
        return subscriberIterator;
    }

    /* renamed from: rx.internal.operators.BlockingOperatorToIterator$SubscriberIterator */
    /* loaded from: classes6.dex */
    public static final class SubscriberIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T> {
        static final int LIMIT = (RxRingBuffer.SIZE * 3) / 4;
        private Notification<? extends T> buf;
        private final BlockingQueue<Notification<? extends T>> notifications = new LinkedBlockingQueue();
        private int received;

        @Override // p042rx.Observer
        public void onCompleted() {
        }

        @Override // p042rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void onStart() {
            request(RxRingBuffer.SIZE);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.notifications.offer(Notification.createOnError(th));
        }

        public void onNext(Notification<? extends T> notification) {
            this.notifications.offer(notification);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.buf == null) {
                this.buf = take();
                int r0 = this.received + 1;
                this.received = r0;
                if (r0 >= LIMIT) {
                    request(r0);
                    this.received = 0;
                }
            }
            if (this.buf.isOnError()) {
                throw Exceptions.propagate(this.buf.getThrowable());
            }
            return !this.buf.isOnCompleted();
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T value = this.buf.getValue();
                this.buf = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        private Notification<? extends T> take() {
            try {
                Notification<? extends T> poll = this.notifications.poll();
                return poll != null ? poll : this.notifications.take();
            } catch (InterruptedException e) {
                unsubscribe();
                throw Exceptions.propagate(e);
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator");
        }
    }
}
