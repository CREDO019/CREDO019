package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import p042rx.Observable;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.Subscription;
import p042rx.exceptions.CompositeException;
import p042rx.functions.Action0;
import p042rx.internal.util.RxRingBuffer;
import p042rx.internal.util.atomic.SpscLinkedArrayQueue;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.SerialSubscription;
import p042rx.subscriptions.Subscriptions;

/* renamed from: rx.internal.operators.OperatorSwitch */
/* loaded from: classes6.dex */
public final class OperatorSwitch<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayError;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorSwitch$Holder */
    /* loaded from: classes6.dex */
    public static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(false);

        Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorSwitch$HolderDelayError */
    /* loaded from: classes6.dex */
    public static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(true);

        HolderDelayError() {
        }
    }

    public static <T> OperatorSwitch<T> instance(boolean z) {
        if (z) {
            return (OperatorSwitch<T>) HolderDelayError.INSTANCE;
        }
        return (OperatorSwitch<T>) Holder.INSTANCE;
    }

    OperatorSwitch(boolean z) {
        this.delayError = z;
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        SwitchSubscriber switchSubscriber = new SwitchSubscriber(subscriber, this.delayError);
        subscriber.add(switchSubscriber);
        switchSubscriber.init();
        return switchSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorSwitch$SwitchSubscriber */
    /* loaded from: classes6.dex */
    public static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        Producer producer;
        long requested;
        final SerialSubscription serial = new SerialSubscription();
        final AtomicLong index = new AtomicLong();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);

        @Override // p042rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        SwitchSubscriber(Subscriber<? super T> subscriber, boolean z) {
            this.child = subscriber;
            this.delayError = z;
        }

        void init() {
            this.child.add(this.serial);
            this.child.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.1
                @Override // p042rx.functions.Action0
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.2
                @Override // p042rx.Producer
                public void request(long j) {
                    int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                    if (r2 > 0) {
                        SwitchSubscriber.this.childRequested(j);
                    } else if (r2 >= 0) {
                    } else {
                        throw new IllegalArgumentException("n >= 0 expected but it was " + j);
                    }
                }
            });
        }

        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        public void onNext(Observable<? extends T> observable) {
            InnerSubscriber innerSubscriber;
            long incrementAndGet = this.index.incrementAndGet();
            Subscription subscription = this.serial.get();
            if (subscription != null) {
                subscription.unsubscribe();
            }
            synchronized (this) {
                innerSubscriber = new InnerSubscriber(incrementAndGet, this);
                this.innerActive = true;
                this.producer = null;
            }
            this.serial.set(innerSubscriber);
            observable.unsafeSubscribe(innerSubscriber);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            boolean updateError;
            synchronized (this) {
                updateError = updateError(th);
            }
            if (updateError) {
                this.mainDone = true;
                drain();
                return;
            }
            pluginError(th);
        }

        boolean updateError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == TERMINAL_ERROR) {
                return false;
            }
            if (th2 == null) {
                this.error = th;
            } else if (th2 instanceof CompositeException) {
                ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                arrayList.add(th);
                this.error = new CompositeException(arrayList);
            } else {
                this.error = new CompositeException(th2, th);
            }
            return true;
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.mainDone = true;
            drain();
        }

        void emit(T t, InnerSubscriber<T> innerSubscriber) {
            synchronized (this) {
                if (this.index.get() != ((InnerSubscriber) innerSubscriber).f2567id) {
                    return;
                }
                this.queue.offer(innerSubscriber, NotificationLite.next(t));
                drain();
            }
        }

        void error(Throwable th, long j) {
            boolean z;
            synchronized (this) {
                if (this.index.get() == j) {
                    z = updateError(th);
                    this.innerActive = false;
                    this.producer = null;
                } else {
                    z = true;
                }
            }
            if (z) {
                drain();
            } else {
                pluginError(th);
            }
        }

        void complete(long j) {
            synchronized (this) {
                if (this.index.get() != j) {
                    return;
                }
                this.innerActive = false;
                this.producer = null;
                drain();
            }
        }

        void pluginError(Throwable th) {
            RxJavaHooks.onError(th);
        }

        void innerProducer(Producer producer, long j) {
            synchronized (this) {
                if (this.index.get() != j) {
                    return;
                }
                long j2 = this.requested;
                this.producer = producer;
                producer.request(j2);
            }
        }

        void childRequested(long j) {
            Producer producer;
            synchronized (this) {
                producer = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, j);
            }
            if (producer != null) {
                producer.request(j);
            }
            drain();
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0074, code lost:
            if (r18 != 0) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007a, code lost:
            if (r11.isUnsubscribed() == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x007c, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
            if (checkTerminated(r20.mainDone, r0, r14, r9, r11, r9.isEmpty()) == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x008f, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0090, code lost:
            monitor-enter(r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0091, code lost:
            r0 = r20.requested;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
            if (r0 == Long.MAX_VALUE) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
            r0 = r0 - r16;
            r20.requested = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00a0, code lost:
            r12 = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00a4, code lost:
            if (r20.missed != false) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00a6, code lost:
            r20.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
            monitor-exit(r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00a9, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00aa, code lost:
            r20.missed = false;
            r15 = r20.mainDone;
            r0 = r20.innerActive;
            r14 = r20.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00b2, code lost:
            if (r14 == null) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00b4, code lost:
            r1 = p042rx.internal.operators.OperatorSwitch.SwitchSubscriber.TERMINAL_ERROR;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b6, code lost:
            if (r14 == r1) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00ba, code lost:
            if (r20.delayError != false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00bc, code lost:
            r20.error = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00be, code lost:
            monitor-exit(r20);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 199
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: p042rx.internal.operators.OperatorSwitch.SwitchSubscriber.drain():void");
        }

        protected boolean checkTerminated(boolean z, boolean z2, Throwable th, SpscLinkedArrayQueue<Object> spscLinkedArrayQueue, Subscriber<? super T> subscriber, boolean z3) {
            if (this.delayError) {
                if (z && !z2 && z3) {
                    if (th != null) {
                        subscriber.onError(th);
                    } else {
                        subscriber.onCompleted();
                    }
                    return true;
                }
                return false;
            } else if (th != null) {
                spscLinkedArrayQueue.clear();
                subscriber.onError(th);
                return true;
            } else if (z && !z2 && z3) {
                subscriber.onCompleted();
                return true;
            } else {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorSwitch$InnerSubscriber */
    /* loaded from: classes6.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {

        /* renamed from: id */
        private final long f2567id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long j, SwitchSubscriber<T> switchSubscriber) {
            this.f2567id = j;
            this.parent = switchSubscriber;
        }

        @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.parent.innerProducer(producer, this.f2567id);
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            this.parent.emit(t, this);
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            this.parent.error(th, this.f2567id);
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            this.parent.complete(this.f2567id);
        }
    }
}
