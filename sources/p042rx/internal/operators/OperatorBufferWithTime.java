package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p042rx.Observable;
import p042rx.Scheduler;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Action0;
import p042rx.observers.SerializedSubscriber;

/* renamed from: rx.internal.operators.OperatorBufferWithTime */
/* loaded from: classes6.dex */
public final class OperatorBufferWithTime<T> implements Observable.Operator<List<T>, T> {
    final int count;
    final Scheduler scheduler;
    final long timeshift;
    final long timespan;
    final TimeUnit unit;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorBufferWithTime(long j, long j2, TimeUnit timeUnit, int r6, Scheduler scheduler) {
        this.timespan = j;
        this.timeshift = j2;
        this.unit = timeUnit;
        this.count = r6;
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        if (this.timespan == this.timeshift) {
            ExactSubscriber exactSubscriber = new ExactSubscriber(serializedSubscriber, createWorker);
            exactSubscriber.add(createWorker);
            subscriber.add(exactSubscriber);
            exactSubscriber.scheduleExact();
            return exactSubscriber;
        }
        InexactSubscriber inexactSubscriber = new InexactSubscriber(serializedSubscriber, createWorker);
        inexactSubscriber.add(createWorker);
        subscriber.add(inexactSubscriber);
        inexactSubscriber.startNewChunk();
        inexactSubscriber.scheduleChunk();
        return inexactSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorBufferWithTime$InexactSubscriber */
    /* loaded from: classes6.dex */
    public final class InexactSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks = new LinkedList();
        boolean done;
        final Scheduler.Worker inner;

        public InexactSubscriber(Subscriber<? super List<T>> subscriber, Scheduler.Worker worker) {
            this.child = subscriber;
            this.inner = worker;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                Iterator<List<T>> it = this.chunks.iterator();
                LinkedList<List> linkedList = null;
                while (it.hasNext()) {
                    List<T> next = it.next();
                    next.add(t);
                    if (next.size() == OperatorBufferWithTime.this.count) {
                        it.remove();
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                        }
                        linkedList.add(next);
                    }
                }
                if (linkedList != null) {
                    for (List list : linkedList) {
                        this.child.onNext(list);
                    }
                }
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunks.clear();
                this.child.onError(th);
                unsubscribe();
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    LinkedList<List> linkedList = new LinkedList(this.chunks);
                    this.chunks.clear();
                    for (List list : linkedList) {
                        this.child.onNext(list);
                    }
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.child);
            }
        }

        void scheduleChunk() {
            this.inner.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorBufferWithTime.InexactSubscriber.1
                @Override // p042rx.functions.Action0
                public void call() {
                    InexactSubscriber.this.startNewChunk();
                }
            }, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.timeshift, OperatorBufferWithTime.this.unit);
        }

        void startNewChunk() {
            final ArrayList arrayList = new ArrayList();
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunks.add(arrayList);
                this.inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorBufferWithTime.InexactSubscriber.2
                    @Override // p042rx.functions.Action0
                    public void call() {
                        InexactSubscriber.this.emitChunk(arrayList);
                    }
                }, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
            }
        }

        void emitChunk(List<T> list) {
            boolean z;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                Iterator<List<T>> it = this.chunks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == list) {
                        it.remove();
                        z = true;
                        break;
                    }
                }
                if (z) {
                    try {
                        this.child.onNext(list);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, this);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorBufferWithTime$ExactSubscriber */
    /* loaded from: classes6.dex */
    public final class ExactSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        List<T> chunk = new ArrayList();
        boolean done;
        final Scheduler.Worker inner;

        public ExactSubscriber(Subscriber<? super List<T>> subscriber, Scheduler.Worker worker) {
            this.child = subscriber;
            this.inner = worker;
        }

        @Override // p042rx.Observer
        public void onNext(T t) {
            List<T> list;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunk.add(t);
                if (this.chunk.size() == OperatorBufferWithTime.this.count) {
                    list = this.chunk;
                    this.chunk = new ArrayList();
                } else {
                    list = null;
                }
                if (list != null) {
                    this.child.onNext(list);
                }
            }
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunk = null;
                this.child.onError(th);
                unsubscribe();
            }
        }

        @Override // p042rx.Observer
        public void onCompleted() {
            try {
                this.inner.unsubscribe();
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    List<T> list = this.chunk;
                    this.chunk = null;
                    this.child.onNext(list);
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.child);
            }
        }

        void scheduleExact() {
            this.inner.schedulePeriodically(new Action0() { // from class: rx.internal.operators.OperatorBufferWithTime.ExactSubscriber.1
                @Override // p042rx.functions.Action0
                public void call() {
                    ExactSubscriber.this.emit();
                }
            }, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.timespan, OperatorBufferWithTime.this.unit);
        }

        void emit() {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                List<T> list = this.chunk;
                this.chunk = new ArrayList();
                try {
                    this.child.onNext(list);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }
    }
}
