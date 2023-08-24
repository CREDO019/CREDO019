package com.jakewharton.rxrelay;

import p042rx.functions.Action1;

/* loaded from: classes3.dex */
final class SerializedAction1<T> implements Action1<T> {
    private static final int MAX_DRAIN_ITERATION = 1024;
    private final Action1<? super T> actual;
    private boolean emitting;
    private FastList<T> queue;

    /* loaded from: classes3.dex */
    private static final class FastList<T> {
        T[] array;
        int size;

        FastList() {
        }

        void add(T t) {
            int r0 = this.size;
            T[] tArr = this.array;
            if (tArr == null) {
                tArr = (T[]) new Object[16];
                this.array = tArr;
            } else if (r0 == tArr.length) {
                T[] tArr2 = (T[]) new Object[(r0 >> 2) + r0];
                System.arraycopy(tArr, 0, tArr2, 0, r0);
                this.array = tArr2;
                tArr = tArr2;
            }
            tArr[r0] = t;
            this.size = r0 + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerializedAction1(Action1<? super T> action1) {
        this.actual = action1;
    }

    @Override // p042rx.functions.Action1
    public void call(T t) {
        FastList<T> fastList;
        T[] tArr;
        synchronized (this) {
            if (this.emitting) {
                FastList<T> fastList2 = this.queue;
                if (fastList2 == null) {
                    fastList2 = new FastList<>();
                    this.queue = fastList2;
                }
                fastList2.add(t);
                return;
            }
            this.emitting = true;
            this.actual.call(t);
            while (true) {
                for (int r0 = 0; r0 < 1024; r0++) {
                    synchronized (this) {
                        fastList = this.queue;
                        if (fastList == null) {
                            this.emitting = false;
                            return;
                        }
                        this.queue = null;
                    }
                    for (T t2 : fastList.array) {
                        if (t2 == null) {
                            break;
                        }
                        this.actual.call(t2);
                    }
                }
            }
        }
    }
}
