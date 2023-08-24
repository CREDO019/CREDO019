package p042rx.internal.util;

import p042rx.functions.Func1;

/* renamed from: rx.internal.util.UtilityFunctions */
/* loaded from: classes6.dex */
public final class UtilityFunctions {

    /* renamed from: rx.internal.util.UtilityFunctions$Identity */
    /* loaded from: classes6.dex */
    enum Identity implements Func1<Object, Object> {
        INSTANCE;

        @Override // p042rx.functions.Func1
        public Object call(Object obj) {
            return obj;
        }
    }

    private UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return Identity.INSTANCE;
    }

    /* renamed from: rx.internal.util.UtilityFunctions$AlwaysTrue */
    /* loaded from: classes6.dex */
    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // p042rx.functions.Func1
        public Boolean call(Object obj) {
            return true;
        }
    }

    /* renamed from: rx.internal.util.UtilityFunctions$AlwaysFalse */
    /* loaded from: classes6.dex */
    enum AlwaysFalse implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // p042rx.functions.Func1
        public Boolean call(Object obj) {
            return false;
        }
    }
}
