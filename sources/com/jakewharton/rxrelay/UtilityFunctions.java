package com.jakewharton.rxrelay;

import p042rx.functions.Func1;

/* loaded from: classes3.dex */
final class UtilityFunctions {
    private static final Func1<Object, Object> IDENTITY = new Func1<Object, Object>() { // from class: com.jakewharton.rxrelay.UtilityFunctions.1
        @Override // p042rx.functions.Func1
        public Object call(Object obj) {
            return obj;
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Func1<T, T> identity() {
        return (Func1<T, T>) IDENTITY;
    }

    private UtilityFunctions() {
    }
}
