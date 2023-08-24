package org.bouncycastle.math.p039ec;

/* renamed from: org.bouncycastle.math.ec.FixedPointPreCompInfo */
/* loaded from: classes5.dex */
public class FixedPointPreCompInfo implements PreCompInfo {
    protected ECPoint offset = null;
    protected ECLookupTable lookupTable = null;
    protected int width = -1;

    public ECLookupTable getLookupTable() {
        return this.lookupTable;
    }

    public ECPoint getOffset() {
        return this.offset;
    }

    public int getWidth() {
        return this.width;
    }

    public void setLookupTable(ECLookupTable eCLookupTable) {
        this.lookupTable = eCLookupTable;
    }

    public void setOffset(ECPoint eCPoint) {
        this.offset = eCPoint;
    }

    public void setWidth(int r1) {
        this.width = r1;
    }
}
