package p042rx.observers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import p042rx.Notification;
import p042rx.Observer;
import p042rx.Subscriber;
import p042rx.exceptions.CompositeException;

/* renamed from: rx.observers.TestSubscriber */
/* loaded from: classes6.dex */
public class TestSubscriber<T> extends Subscriber<T> {
    private static final Observer<Object> INERT = new Observer<Object>() { // from class: rx.observers.TestSubscriber.1
        @Override // p042rx.Observer
        public void onCompleted() {
        }

        @Override // p042rx.Observer
        public void onError(Throwable th) {
        }

        @Override // p042rx.Observer
        public void onNext(Object obj) {
        }
    };
    private int completions;
    private final Observer<T> delegate;
    private final List<Throwable> errors;
    private volatile Thread lastSeenThread;
    private final CountDownLatch latch;
    private volatile int valueCount;
    private final List<T> values;

    public TestSubscriber(long j) {
        this(INERT, j);
    }

    public TestSubscriber(Observer<T> observer, long j) {
        this.latch = new CountDownLatch(1);
        Objects.requireNonNull(observer);
        this.delegate = observer;
        if (j >= 0) {
            request(j);
        }
        this.values = new ArrayList();
        this.errors = new ArrayList();
    }

    public TestSubscriber(Subscriber<T> subscriber) {
        this(subscriber, -1L);
    }

    public TestSubscriber(Observer<T> observer) {
        this(observer, -1L);
    }

    public TestSubscriber() {
        this(-1L);
    }

    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<>();
    }

    public static <T> TestSubscriber<T> create(long j) {
        return new TestSubscriber<>(j);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer, long j) {
        return new TestSubscriber<>(observer, j);
    }

    public static <T> TestSubscriber<T> create(Subscriber<T> subscriber) {
        return new TestSubscriber<>((Subscriber) subscriber);
    }

    public static <T> TestSubscriber<T> create(Observer<T> observer) {
        return new TestSubscriber<>(observer);
    }

    @Override // p042rx.Observer
    public void onCompleted() {
        try {
            this.completions++;
            this.lastSeenThread = Thread.currentThread();
            this.delegate.onCompleted();
        } finally {
            this.latch.countDown();
        }
    }

    @Deprecated
    public List<Notification<T>> getOnCompletedEvents() {
        int r0 = this.completions;
        ArrayList arrayList = new ArrayList(r0 != 0 ? r0 : 1);
        for (int r2 = 0; r2 < r0; r2++) {
            arrayList.add(Notification.createOnCompleted());
        }
        return arrayList;
    }

    public final int getCompletions() {
        return this.completions;
    }

    @Override // p042rx.Observer
    public void onError(Throwable th) {
        try {
            this.lastSeenThread = Thread.currentThread();
            this.errors.add(th);
            this.delegate.onError(th);
        } finally {
            this.latch.countDown();
        }
    }

    public List<Throwable> getOnErrorEvents() {
        return this.errors;
    }

    @Override // p042rx.Observer
    public void onNext(T t) {
        this.lastSeenThread = Thread.currentThread();
        this.values.add(t);
        this.valueCount = this.values.size();
        this.delegate.onNext(t);
    }

    public final int getValueCount() {
        return this.valueCount;
    }

    public void requestMore(long j) {
        request(j);
    }

    public List<T> getOnNextEvents() {
        return this.values;
    }

    public void assertReceivedOnNext(List<T> list) {
        if (this.values.size() != list.size()) {
            assertionError("Number of items does not match. Provided: " + list.size() + "  Actual: " + this.values.size() + ".\nProvided values: " + list + "\nActual values: " + this.values + "\n");
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            assertItem(list.get(r0), r0);
        }
    }

    private void assertItem(T t, int r5) {
        T t2 = this.values.get(r5);
        if (t == null) {
            if (t2 != null) {
                assertionError("Value at index: " + r5 + " expected: [null] but was: [" + t2 + "]\n");
            }
        } else if (t.equals(t2)) {
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Value at index: ");
            sb.append(r5);
            sb.append(" expected: [");
            sb.append(t);
            sb.append("] (");
            sb.append(t.getClass().getSimpleName());
            sb.append(") but was: [");
            sb.append(t2);
            sb.append("] (");
            sb.append(t2 != null ? t2.getClass().getSimpleName() : "null");
            sb.append(")\n");
            assertionError(sb.toString());
        }
    }

    public final boolean awaitValueCount(int r4, long j, TimeUnit timeUnit) {
        while (j != 0 && this.valueCount < r4) {
            try {
                timeUnit.sleep(1L);
                j--;
            } catch (InterruptedException e) {
                throw new IllegalStateException("Interrupted", e);
            }
        }
        return this.valueCount >= r4;
    }

    public void assertTerminalEvent() {
        if (this.errors.size() > 1) {
            assertionError("Too many onError events: " + this.errors.size());
        }
        if (this.completions > 1) {
            assertionError("Too many onCompleted events: " + this.completions);
        }
        if (this.completions == 1 && this.errors.size() == 1) {
            assertionError("Received both an onError and onCompleted. Should be one or the other.");
        }
        if (this.completions == 0 && this.errors.isEmpty()) {
            assertionError("No terminal events received.");
        }
    }

    public void assertUnsubscribed() {
        if (isUnsubscribed()) {
            return;
        }
        assertionError("Not unsubscribed.");
    }

    public void assertNoErrors() {
        if (getOnErrorEvents().isEmpty()) {
            return;
        }
        assertionError("Unexpected onError events");
    }

    public void awaitTerminalEvent() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted", e);
        }
    }

    public void awaitTerminalEvent(long j, TimeUnit timeUnit) {
        try {
            this.latch.await(j, timeUnit);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted", e);
        }
    }

    public void awaitTerminalEventAndUnsubscribeOnTimeout(long j, TimeUnit timeUnit) {
        try {
            if (this.latch.await(j, timeUnit)) {
                return;
            }
            unsubscribe();
        } catch (InterruptedException unused) {
            unsubscribe();
        }
    }

    public Thread getLastSeenThread() {
        return this.lastSeenThread;
    }

    public void assertCompleted() {
        int r0 = this.completions;
        if (r0 == 0) {
            assertionError("Not completed!");
        } else if (r0 > 1) {
            assertionError("Completed multiple times: " + r0);
        }
    }

    public void assertNotCompleted() {
        int r0 = this.completions;
        if (r0 == 1) {
            assertionError("Completed!");
        } else if (r0 > 1) {
            assertionError("Completed multiple times: " + r0);
        }
    }

    public void assertError(Class<? extends Throwable> cls) {
        List<Throwable> list = this.errors;
        if (list.isEmpty()) {
            assertionError("No errors");
        } else if (list.size() > 1) {
            AssertionError assertionError = new AssertionError("Multiple errors: " + list.size());
            assertionError.initCause(new CompositeException(list));
            throw assertionError;
        } else if (cls.isInstance(list.get(0))) {
        } else {
            AssertionError assertionError2 = new AssertionError("Exceptions differ; expected: " + cls + ", actual: " + list.get(0));
            assertionError2.initCause(list.get(0));
            throw assertionError2;
        }
    }

    public void assertError(Throwable th) {
        List<Throwable> list = this.errors;
        if (list.isEmpty()) {
            assertionError("No errors");
        } else if (list.size() > 1) {
            assertionError("Multiple errors");
        } else if (th.equals(list.get(0))) {
        } else {
            assertionError("Exceptions differ; expected: " + th + ", actual: " + list.get(0));
        }
    }

    public void assertNoTerminalEvent() {
        List<Throwable> list = this.errors;
        int r1 = this.completions;
        if (!list.isEmpty() || r1 > 0) {
            if (list.isEmpty()) {
                assertionError("Found " + list.size() + " errors and " + r1 + " completion events instead of none");
            } else if (list.size() == 1) {
                assertionError("Found " + list.size() + " errors and " + r1 + " completion events instead of none");
            } else {
                assertionError("Found " + list.size() + " errors and " + r1 + " completion events instead of none");
            }
        }
    }

    public void assertNoValues() {
        int size = this.values.size();
        if (size != 0) {
            assertionError("No onNext events expected yet some received: " + size);
        }
    }

    public void assertValueCount(int r4) {
        int size = this.values.size();
        if (size != r4) {
            assertionError("Number of onNext events differ; expected: " + r4 + ", actual: " + size);
        }
    }

    public void assertValues(T... tArr) {
        assertReceivedOnNext(Arrays.asList(tArr));
    }

    public void assertValue(T t) {
        assertReceivedOnNext(Collections.singletonList(t));
    }

    final void assertionError(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 32);
        sb.append(str);
        sb.append(" (");
        int r6 = this.completions;
        sb.append(r6);
        sb.append(" completion");
        if (r6 != 1) {
            sb.append('s');
        }
        sb.append(')');
        if (!this.errors.isEmpty()) {
            int size = this.errors.size();
            sb.append(" (+");
            sb.append(size);
            sb.append(" error");
            if (size != 1) {
                sb.append('s');
            }
            sb.append(')');
        }
        AssertionError assertionError = new AssertionError(sb.toString());
        if (!this.errors.isEmpty()) {
            if (this.errors.size() == 1) {
                assertionError.initCause(this.errors.get(0));
            } else {
                assertionError.initCause(new CompositeException(this.errors));
            }
        }
        throw assertionError;
    }

    public final void assertValuesAndClear(T t, T... tArr) {
        assertValueCount(tArr.length + 1);
        assertItem(t, 0);
        int r3 = 0;
        while (r3 < tArr.length) {
            T t2 = tArr[r3];
            r3++;
            assertItem(t2, r3);
        }
        this.values.clear();
        this.valueCount = 0;
    }
}
