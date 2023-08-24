package p042rx.internal.producers;

import java.util.ArrayList;
import java.util.List;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscriber;

/* renamed from: rx.internal.producers.ProducerObserverArbiter */
/* loaded from: classes6.dex */
public final class ProducerObserverArbiter<T> implements Producer, Observer<T> {
    static final Producer NULL_PRODUCER = new Producer() { // from class: rx.internal.producers.ProducerObserverArbiter.1
        @Override // p042rx.Producer
        public void request(long j) {
        }
    };
    final Subscriber<? super T> child;
    Producer currentProducer;
    boolean emitting;
    volatile boolean hasError;
    Producer missedProducer;
    long missedRequested;
    Object missedTerminal;
    List<T> queue;
    long requested;

    public ProducerObserverArbiter(Subscriber<? super T> subscriber) {
        this.child = subscriber;
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        synchronized (this) {
            if (this.emitting) {
                List list = this.queue;
                if (list == null) {
                    list = new ArrayList(4);
                    this.queue = list;
                }
                list.add(t);
                return;
            }
            this.emitting = true;
            try {
                this.child.onNext(t);
                long j = this.requested;
                if (j != Long.MAX_VALUE) {
                    this.requested = j - 1;
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        boolean z;
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = th;
                z = false;
            } else {
                this.emitting = true;
                z = true;
            }
        }
        if (z) {
            this.child.onError(th);
        } else {
            this.hasError = true;
        }
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = true;
                return;
            }
            this.emitting = true;
            this.child.onCompleted();
        }
    }

    @Override // p042rx.Producer
    public void request(long j) {
        int r2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (r2 < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (r2 == 0) {
            return;
        }
        synchronized (this) {
            if (this.emitting) {
                this.missedRequested += j;
                return;
            }
            this.emitting = true;
            Producer producer = this.currentProducer;
            try {
                long j2 = this.requested + j;
                if (j2 < 0) {
                    j2 = Long.MAX_VALUE;
                }
                this.requested = j2;
                emitLoop();
                if (producer != null) {
                    producer.request(j);
                }
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }

    public void setProducer(Producer producer) {
        synchronized (this) {
            if (this.emitting) {
                if (producer == null) {
                    producer = NULL_PRODUCER;
                }
                this.missedProducer = producer;
                return;
            }
            this.emitting = true;
            this.currentProducer = producer;
            long j = this.requested;
            try {
                emitLoop();
                if (producer == null || j == 0) {
                    return;
                }
                producer.request(j);
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x0009, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void emitLoop() {
        /*
            Method dump skipped, instructions count: 213
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p042rx.internal.producers.ProducerObserverArbiter.emitLoop():void");
    }
}
