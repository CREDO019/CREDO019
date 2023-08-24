package p042rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func2;
import p042rx.internal.producers.SingleDelayedProducer;

/* renamed from: rx.internal.operators.OperatorToObservableSortedList */
/* loaded from: classes6.dex */
public final class OperatorToObservableSortedList<T> implements Observable.Operator<List<T>, T> {
    private static final Comparator DEFAULT_SORT_FUNCTION = new DefaultComparableFunction();
    final int initialCapacity;
    final Comparator<? super T> sortFunction;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorToObservableSortedList(int r2) {
        this.sortFunction = DEFAULT_SORT_FUNCTION;
        this.initialCapacity = r2;
    }

    public OperatorToObservableSortedList(final Func2<? super T, ? super T, Integer> func2, int r2) {
        this.initialCapacity = r2;
        this.sortFunction = (Comparator<T>) new Comparator<T>() { // from class: rx.internal.operators.OperatorToObservableSortedList.1
            @Override // java.util.Comparator
            public int compare(T t, T t2) {
                return ((Integer) func2.call(t, t2)).intValue();
            }
        };
    }

    public Subscriber<? super T> call(final Subscriber<? super List<T>> subscriber) {
        final SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorToObservableSortedList.2
            boolean completed;
            List<T> list;

            {
                this.list = new ArrayList(OperatorToObservableSortedList.this.initialCapacity);
            }

            @Override // p042rx.Subscriber, p042rx.observers.AssertableSubscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                if (this.completed) {
                    return;
                }
                this.completed = true;
                List<T> list = this.list;
                this.list = null;
                try {
                    Collections.sort(list, OperatorToObservableSortedList.this.sortFunction);
                    singleDelayedProducer.setValue(list);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onNext(T t) {
                if (this.completed) {
                    return;
                }
                this.list.add(t);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(singleDelayedProducer);
        return subscriber2;
    }

    /* renamed from: rx.internal.operators.OperatorToObservableSortedList$DefaultComparableFunction */
    /* loaded from: classes6.dex */
    static final class DefaultComparableFunction implements Comparator<Object> {
        DefaultComparableFunction() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    }
}
