package org.bouncycastle.math.p039ec;

/* renamed from: org.bouncycastle.math.ec.SimpleLookupTable */
/* loaded from: classes5.dex */
public class SimpleLookupTable extends AbstractECLookupTable {
    private final ECPoint[] points;

    public SimpleLookupTable(ECPoint[] eCPointArr, int r2, int r3) {
        this.points = copy(eCPointArr, r2, r3);
    }

    private static ECPoint[] copy(ECPoint[] eCPointArr, int r4, int r5) {
        ECPoint[] eCPointArr2 = new ECPoint[r5];
        for (int r1 = 0; r1 < r5; r1++) {
            eCPointArr2[r1] = eCPointArr[r4 + r1];
        }
        return eCPointArr2;
    }

    @Override // org.bouncycastle.math.p039ec.ECLookupTable
    public int getSize() {
        return this.points.length;
    }

    @Override // org.bouncycastle.math.p039ec.ECLookupTable
    public ECPoint lookup(int r2) {
        throw new UnsupportedOperationException("Constant-time lookup not supported");
    }

    @Override // org.bouncycastle.math.p039ec.AbstractECLookupTable, org.bouncycastle.math.p039ec.ECLookupTable
    public ECPoint lookupVar(int r2) {
        return this.points[r2];
    }
}
