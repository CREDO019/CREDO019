package org.bouncycastle.pqc.crypto.qtesla;

/* loaded from: classes3.dex */
final class IntSlicer {
    private int base;
    private final int[] values;

    IntSlicer(int[] r1, int r2) {
        this.values = r1;
        this.base = r2;
    }

    /* renamed from: at */
    final int m15at(int r3) {
        return this.values[this.base + r3];
    }

    /* renamed from: at */
    final int m14at(int r3, int r4) {
        this.values[this.base + r3] = r4;
        return r4;
    }

    /* renamed from: at */
    final int m13at(int r3, long j) {
        int[] r0 = this.values;
        int r1 = this.base + r3;
        int r32 = (int) j;
        r0[r1] = r32;
        return r32;
    }

    final IntSlicer copy() {
        return new IntSlicer(this.values, this.base);
    }

    final IntSlicer from(int r4) {
        return new IntSlicer(this.values, this.base + r4);
    }

    final void incBase(int r2) {
        this.base += r2;
    }
}
