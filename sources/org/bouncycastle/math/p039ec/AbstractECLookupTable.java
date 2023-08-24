package org.bouncycastle.math.p039ec;

/* renamed from: org.bouncycastle.math.ec.AbstractECLookupTable */
/* loaded from: classes5.dex */
public abstract class AbstractECLookupTable implements ECLookupTable {
    @Override // org.bouncycastle.math.p039ec.ECLookupTable
    public ECPoint lookupVar(int r1) {
        return lookup(r1);
    }
}
