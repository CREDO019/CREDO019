package p042rx.internal.operators;

import p042rx.Observable;
import p042rx.Subscriber;
import p042rx.exceptions.Exceptions;
import p042rx.functions.Func1;
import p042rx.functions.Func2;

/* renamed from: rx.internal.operators.OperatorSkipWhile */
/* loaded from: classes6.dex */
public final class OperatorSkipWhile<T> implements Observable.Operator<T, T> {
    final Func2<? super T, Integer, Boolean> predicate;

    @Override // p042rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorSkipWhile(Func2<? super T, Integer, Boolean> func2) {
        this.predicate = func2;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorSkipWhile.1
            int index;
            boolean skipping = true;

            @Override // p042rx.Observer
            public void onNext(T t) {
                if (!this.skipping) {
                    subscriber.onNext(t);
                    return;
                }
                try {
                    Func2<? super T, Integer, Boolean> func2 = OperatorSkipWhile.this.predicate;
                    int r1 = this.index;
                    this.index = r1 + 1;
                    if (!func2.call(t, Integer.valueOf(r1)).booleanValue()) {
                        this.skipping = false;
                        subscriber.onNext(t);
                        return;
                    }
                    request(1L);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, t);
                }
            }

            @Override // p042rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }

            @Override // p042rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }
        };
    }

    public static <T> Func2<T, Integer, Boolean> toPredicate2(final Func1<? super T, Boolean> func1) {
        return new Func2<T, Integer, Boolean>() { // from class: rx.internal.operators.OperatorSkipWhile.2
            @Override // p042rx.functions.Func2
            public /* bridge */ /* synthetic */ Boolean call(Object obj, Integer num) {
                return call2((C56752) obj, num);
            }

            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Boolean call2(T t, Integer num) {
                return (Boolean) Func1.this.call(t);
            }
        };
    }
}
