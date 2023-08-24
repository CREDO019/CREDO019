package p042rx.observers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import p042rx.Observer;
import p042rx.Producer;
import p042rx.Subscription;
import p042rx.functions.Action0;

/* renamed from: rx.observers.AssertableSubscriber */
/* loaded from: classes6.dex */
public interface AssertableSubscriber<T> extends Observer<T>, Subscription {
    AssertableSubscriber<T> assertCompleted();

    AssertableSubscriber<T> assertError(Class<? extends Throwable> cls);

    AssertableSubscriber<T> assertError(Throwable th);

    AssertableSubscriber<T> assertFailure(Class<? extends Throwable> cls, T... tArr);

    AssertableSubscriber<T> assertFailureAndMessage(Class<? extends Throwable> cls, String str, T... tArr);

    AssertableSubscriber<T> assertNoErrors();

    AssertableSubscriber<T> assertNoTerminalEvent();

    AssertableSubscriber<T> assertNoValues();

    AssertableSubscriber<T> assertNotCompleted();

    AssertableSubscriber<T> assertReceivedOnNext(List<T> list);

    AssertableSubscriber<T> assertResult(T... tArr);

    AssertableSubscriber<T> assertTerminalEvent();

    AssertableSubscriber<T> assertUnsubscribed();

    AssertableSubscriber<T> assertValue(T t);

    AssertableSubscriber<T> assertValueCount(int r1);

    AssertableSubscriber<T> assertValues(T... tArr);

    AssertableSubscriber<T> assertValuesAndClear(T t, T... tArr);

    AssertableSubscriber<T> awaitTerminalEvent();

    AssertableSubscriber<T> awaitTerminalEvent(long j, TimeUnit timeUnit);

    AssertableSubscriber<T> awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit);

    AssertableSubscriber<T> awaitValueCount(int r1, long j, TimeUnit timeUnit);

    int getCompletions();

    Thread getLastSeenThread();

    List<Throwable> getOnErrorEvents();

    List<T> getOnNextEvents();

    int getValueCount();

    @Override // p042rx.Subscription
    boolean isUnsubscribed();

    void onStart();

    AssertableSubscriber<T> perform(Action0 action0);

    AssertableSubscriber<T> requestMore(long j);

    void setProducer(Producer producer);

    @Override // p042rx.Subscription
    void unsubscribe();
}
