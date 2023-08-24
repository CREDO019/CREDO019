package org.bouncycastle.crypto.params;

/* loaded from: classes5.dex */
public class GOST3410ValidationParameters {

    /* renamed from: c */
    private int f2135c;

    /* renamed from: cL */
    private long f2136cL;

    /* renamed from: x0 */
    private int f2137x0;
    private long x0L;

    public GOST3410ValidationParameters(int r1, int r2) {
        this.f2137x0 = r1;
        this.f2135c = r2;
    }

    public GOST3410ValidationParameters(long j, long j2) {
        this.x0L = j;
        this.f2136cL = j2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410ValidationParameters) {
            GOST3410ValidationParameters gOST3410ValidationParameters = (GOST3410ValidationParameters) obj;
            return gOST3410ValidationParameters.f2135c == this.f2135c && gOST3410ValidationParameters.f2137x0 == this.f2137x0 && gOST3410ValidationParameters.f2136cL == this.f2136cL && gOST3410ValidationParameters.x0L == this.x0L;
        }
        return false;
    }

    public int getC() {
        return this.f2135c;
    }

    public long getCL() {
        return this.f2136cL;
    }

    public int getX0() {
        return this.f2137x0;
    }

    public long getX0L() {
        return this.x0L;
    }

    public int hashCode() {
        int r0 = this.f2137x0 ^ this.f2135c;
        long j = this.x0L;
        long j2 = this.f2136cL;
        return (((r0 ^ ((int) j)) ^ ((int) (j >> 32))) ^ ((int) j2)) ^ ((int) (j2 >> 32));
    }
}
