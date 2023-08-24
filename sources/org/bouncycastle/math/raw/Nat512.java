package org.bouncycastle.math.raw;

/* loaded from: classes5.dex */
public abstract class Nat512 {
    public static void mul(int[] r13, int[] r14, int[] r15) {
        Nat256.mul(r13, r14, r15);
        Nat256.mul(r13, 8, r14, 8, r15, 16);
        int addToEachOther = Nat256.addToEachOther(r15, 8, r15, 16);
        int addTo = addToEachOther + Nat256.addTo(r15, 24, r15, 16, Nat256.addTo(r15, 0, r15, 8, 0) + addToEachOther);
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        boolean z = Nat256.diff(r13, 8, r13, 0, create, 0) != Nat256.diff(r14, 8, r14, 0, create2, 0);
        int[] createExt = Nat256.createExt();
        Nat256.mul(create, create2, createExt);
        Nat.addWordAt(32, addTo + (z ? Nat.addTo(16, createExt, 0, r15, 8) : Nat.subFrom(16, createExt, 0, r15, 8)), r15, 24);
    }

    public static void square(int[] r12, int[] r13) {
        Nat256.square(r12, r13);
        Nat256.square(r12, 8, r13, 16);
        int addToEachOther = Nat256.addToEachOther(r13, 8, r13, 16);
        int addTo = addToEachOther + Nat256.addTo(r13, 24, r13, 16, Nat256.addTo(r13, 0, r13, 8, 0) + addToEachOther);
        int[] create = Nat256.create();
        Nat256.diff(r12, 8, r12, 0, create, 0);
        int[] createExt = Nat256.createExt();
        Nat256.square(create, createExt);
        Nat.addWordAt(32, addTo + Nat.subFrom(16, createExt, 0, r13, 8), r13, 24);
    }
}
