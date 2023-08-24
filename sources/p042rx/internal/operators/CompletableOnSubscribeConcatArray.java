package p042rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import p042rx.Completable;
import p042rx.CompletableSubscriber;
import p042rx.Subscription;
import p042rx.internal.subscriptions.SequentialSubscription;

/* renamed from: rx.internal.operators.CompletableOnSubscribeConcatArray */
/* loaded from: classes6.dex */
public final class CompletableOnSubscribeConcatArray implements Completable.OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeConcatArray(Completable[] completableArr) {
        this.sources = completableArr;
    }

    @Override // p042rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        ConcatInnerSubscriber concatInnerSubscriber = new ConcatInnerSubscriber(completableSubscriber, this.sources);
        completableSubscriber.onSubscribe(concatInnerSubscriber.f2551sd);
        concatInnerSubscriber.next();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.CompletableOnSubscribeConcatArray$ConcatInnerSubscriber */
    /* loaded from: classes6.dex */
    public static final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableSubscriber actual;
        int index;

        /* renamed from: sd */
        final SequentialSubscription f2551sd = new SequentialSubscription();
        final Completable[] sources;

        public ConcatInnerSubscriber(CompletableSubscriber completableSubscriber, Completable[] completableArr) {
            this.actual = completableSubscriber;
            this.sources = completableArr;
        }

        @Override // p042rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.f2551sd.replace(subscription);
        }

        @Override // p042rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.actual.onError(th);
        }

        @Override // p042rx.CompletableSubscriber
        public void onCompleted() {
            next();
        }

        void next() {
            if (!this.f2551sd.isUnsubscribed() && getAndIncrement() == 0) {
                Completable[] completableArr = this.sources;
                while (!this.f2551sd.isUnsubscribed()) {
                    int r1 = this.index;
                    this.index = r1 + 1;
                    if (r1 == completableArr.length) {
                        this.actual.onCompleted();
                        return;
                    }
                    completableArr[r1].unsafeSubscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
