package org.bouncycastle.math.raw;

/* loaded from: classes5.dex */
public abstract class Nat384 {
    public static void mul(int[] r13, int[] r14, int[] r15) {
        Nat192.mul(r13, r14, r15);
        Nat192.mul(r13, 6, r14, 6, r15, 12);
        int addToEachOther = Nat192.addToEachOther(r15, 6, r15, 12);
        int addTo = addToEachOther + Nat192.addTo(r15, 18, r15, 12, Nat192.addTo(r15, 0, r15, 6, 0) + addToEachOther);
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        boolean z = Nat192.diff(r13, 6, r13, 0, create, 0) != Nat192.diff(r14, 6, r14, 0, create2, 0);
        int[] createExt = Nat192.createExt();
        Nat192.mul(create, create2, createExt);
        Nat.addWordAt(24, addTo + (z ? Nat.addTo(12, createExt, 0, r15, 6) : Nat.subFrom(12, createExt, 0, r15, 6)), r15, 18);
    }

    public static void square(int[] r12, int[] r13) {
        Nat192.square(r12, r13);
        Nat192.square(r12, 6, r13, 12);
        int addToEachOther = Nat192.addToEachOther(r13, 6, r13, 12);
        int addTo = addToEachOther + Nat192.addTo(r13, 18, r13, 12, Nat192.addTo(r13, 0, r13, 6, 0) + addToEachOther);
        int[] create = Nat192.create();
        Nat192.diff(r12, 6, r12, 0, create, 0);
        int[] createExt = Nat192.createExt();
        Nat192.square(create, createExt);
        Nat.addWordAt(24, addTo + Nat.subFrom(12, createExt, 0, r13, 6), r13, 18);
    }
}
