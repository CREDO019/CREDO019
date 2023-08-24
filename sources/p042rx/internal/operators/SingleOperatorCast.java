package p042rx.internal.operators;

import p042rx.functions.Func1;

/* renamed from: rx.internal.operators.SingleOperatorCast */
/* loaded from: classes6.dex */
public class SingleOperatorCast<T, R> implements Func1<T, R> {
    final Class<R> castClass;

    public SingleOperatorCast(Class<R> cls) {
        this.castClass = cls;
    }

    @Override // p042rx.functions.Func1
    public R call(T t) {
        return this.castClass.cast(t);
    }
}
