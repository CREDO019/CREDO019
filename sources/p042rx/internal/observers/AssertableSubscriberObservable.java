package p042rx.internal.observers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import p042rx.Producer;
import p042rx.Subscriber;
import p042rx.functions.Action0;
import p042rx.observers.AssertableSubscriber;
import p042rx.observers.TestSubscriber;

/* renamed from: rx.internal.observers.AssertableSubscriberObservable */
/* loaded from: classes4.dex */
public class AssertableSubscriberObservable<T> extends Subscriber<T> implements AssertableSubscriber<T> {

    /* renamed from: ts */
    private final TestSubscriber<T> f2549ts;

    public AssertableSubscriberObservable(TestSubscriber<T> testSubscriber) {
        this.f2549ts = testSubscriber;
    }

    public static <T> AssertableSubscriberObservable<T> create(long j) {
        TestSubscriber testSubscriber = new TestSubscriber(j);
        AssertableSubscriberObservable<T> assertableSubscriberObservable = new AssertableSubscriberObservable<>(testSubscriber);
        assertableSubscriberObservable.add(testSubscriber);
        return assertableSubscriberObservable;
    }

    @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
    public void onStart() {
        this.f2549ts.onStart();
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        this.f2549ts.onCompleted();
    }

    @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
    public void setProducer(Producer producer) {
        this.f2549ts.setProducer(producer);
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final int getCompletions() {
        return this.f2549ts.getCompletions();
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        this.f2549ts.onError(th);
    }

    @Override // p042rx.observers.AssertableSubscriber
    public List<Throwable> getOnErrorEvents() {
        return this.f2549ts.getOnErrorEvents();
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.f2549ts.onNext(t);
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final int getValueCount() {
        return this.f2549ts.getValueCount();
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> requestMore(long j) {
        this.f2549ts.requestMore(j);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public List<T> getOnNextEvents() {
        return this.f2549ts.getOnNextEvents();
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertReceivedOnNext(List<T> list) {
        this.f2549ts.assertReceivedOnNext(list);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> awaitValueCount(int r2, long j, TimeUnit timeUnit) {
        if (this.f2549ts.awaitValueCount(r2, j, timeUnit)) {
            return this;
        }
        throw new AssertionError("Did not receive enough values in time. Expected: " + r2 + ", Actual: " + this.f2549ts.getValueCount());
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertTerminalEvent() {
        this.f2549ts.assertTerminalEvent();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertUnsubscribed() {
        this.f2549ts.assertUnsubscribed();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoErrors() {
        this.f2549ts.assertNoErrors();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEvent() {
        this.f2549ts.awaitTerminalEvent();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEvent(long j, TimeUnit timeUnit) {
        this.f2549ts.awaitTerminalEvent(j, timeUnit);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        this.f2549ts.awaitTerminalEventAndUnsubscribeOnTimeout(j, timeUnit);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public Thread getLastSeenThread() {
        return this.f2549ts.getLastSeenThread();
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertCompleted() {
        this.f2549ts.assertCompleted();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNotCompleted() {
        this.f2549ts.assertNotCompleted();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertError(Class<? extends Throwable> cls) {
        this.f2549ts.assertError(cls);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertError(Throwable th) {
        this.f2549ts.assertError(th);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoTerminalEvent() {
        this.f2549ts.assertNoTerminalEvent();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertNoValues() {
        this.f2549ts.assertNoValues();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValueCount(int r2) {
        this.f2549ts.assertValueCount(r2);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValues(T... tArr) {
        this.f2549ts.assertValues(tArr);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public AssertableSubscriber<T> assertValue(T t) {
        this.f2549ts.assertValue(t);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertValuesAndClear(T t, T... tArr) {
        this.f2549ts.assertValuesAndClear(t, tArr);
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> perform(Action0 action0) {
        action0.call();
        return this;
    }

    public String toString() {
        return this.f2549ts.toString();
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertResult(T... tArr) {
        this.f2549ts.assertValues(tArr);
        this.f2549ts.assertNoErrors();
        this.f2549ts.assertCompleted();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertFailure(Class<? extends Throwable> cls, T... tArr) {
        this.f2549ts.assertValues(tArr);
        this.f2549ts.assertError(cls);
        this.f2549ts.assertNotCompleted();
        return this;
    }

    @Override // p042rx.observers.AssertableSubscriber
    public final AssertableSubscriber<T> assertFailureAndMessage(Class<? extends Throwable> cls, String str, T... tArr) {
        this.f2549ts.assertValues(tArr);
        this.f2549ts.assertError(cls);
        this.f2549ts.assertNotCompleted();
        String message = this.f2549ts.getOnErrorEvents().get(0).getMessage();
        if (message == str || (str != null && str.equals(message))) {
            return this;
        }
        throw new AssertionError("Error message differs. Expected: '" + str + "', Received: '" + message + "'");
    }
}
