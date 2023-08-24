package org.bouncycastle.math.p039ec;

/* renamed from: org.bouncycastle.math.ec.ECLookupTable */
/* loaded from: classes5.dex */
public interface ECLookupTable {
    int getSize();

    ECPoint lookup(int r1);

    ECPoint lookupVar(int r1);
}
