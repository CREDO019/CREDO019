package p042rx.internal.operators;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import p042rx.Single;
import p042rx.SingleSubscriber;
import p042rx.Subscription;
import p042rx.exceptions.Exceptions;
import p042rx.functions.FuncN;
import p042rx.plugins.RxJavaHooks;
import p042rx.subscriptions.CompositeSubscription;

/* renamed from: rx.internal.operators.SingleOperatorZip */
/* loaded from: classes6.dex */
public final class SingleOperatorZip {
    private SingleOperatorZip() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, R> Single<R> zip(final Single<? extends T>[] singleArr, final FuncN<? extends R> funcN) {
        return Single.create(new Single.OnSubscribe<R>() { // from class: rx.internal.operators.SingleOperatorZip.1
            @Override // p042rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object obj) {
                call((SingleSubscriber) ((SingleSubscriber) obj));
            }

            public void call(final SingleSubscriber<? super R> singleSubscriber) {
                if (singleArr.length == 0) {
                    singleSubscriber.onError(new NoSuchElementException("Can't zip 0 Singles."));
                    return;
                }
                final AtomicInteger atomicInteger = new AtomicInteger(singleArr.length);
                final AtomicBoolean atomicBoolean = new AtomicBoolean();
                final Object[] objArr = new Object[singleArr.length];
                CompositeSubscription compositeSubscription = new CompositeSubscription();
                singleSubscriber.add(compositeSubscription);
                for (int r11 = 0; r11 < singleArr.length && !compositeSubscription.isUnsubscribed() && !atomicBoolean.get(); r11++) {
                    final int r5 = r11;
                    Subscription subscription = new SingleSubscriber<T>() { // from class: rx.internal.operators.SingleOperatorZip.1.1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // p042rx.SingleSubscriber
                        public void onSuccess(T t) {
                            objArr[r5] = t;
                            if (atomicInteger.decrementAndGet() == 0) {
                                try {
                                    singleSubscriber.onSuccess(funcN.call(objArr));
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    onError(th);
                                }
                            }
                        }

                        @Override // p042rx.SingleSubscriber
                        public void onError(Throwable th) {
                            if (atomicBoolean.compareAndSet(false, true)) {
                                singleSubscriber.onError(th);
                            } else {
                                RxJavaHooks.onError(th);
                            }
                        }
                    };
                    compositeSubscription.add(subscription);
                    if (compositeSubscription.isUnsubscribed() || atomicBoolean.get()) {
                        return;
                    }
                    singleArr[r11].subscribe((SingleSubscriber) subscription);
                }
            }
        });
    }
}
