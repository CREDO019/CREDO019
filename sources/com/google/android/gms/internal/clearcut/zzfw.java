package com.google.android.gms.internal.clearcut;

/* loaded from: classes2.dex */
public final class zzfw implements Cloneable {
    private static final zzfx zzrl = new zzfx();
    private int mSize;
    private boolean zzrm;
    private int[] zzrn;
    private zzfx[] zzro;

    zzfw() {
        this(10);
    }

    private zzfw(int r5) {
        this.zzrm = false;
        int r52 = r5 << 2;
        int r2 = 4;
        while (true) {
            if (r2 >= 32) {
                break;
            }
            int r3 = (1 << r2) - 12;
            if (r52 <= r3) {
                r52 = r3;
                break;
            }
            r2++;
        }
        int r53 = r52 / 4;
        this.zzrn = new int[r53];
        this.zzro = new zzfx[r53];
        this.mSize = 0;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int r0 = this.mSize;
        zzfw zzfwVar = new zzfw(r0);
        System.arraycopy(this.zzrn, 0, zzfwVar.zzrn, 0, r0);
        for (int r4 = 0; r4 < r0; r4++) {
            zzfx[] zzfxVarArr = this.zzro;
            if (zzfxVarArr[r4] != null) {
                zzfwVar.zzro[r4] = (zzfx) zzfxVarArr[r4].clone();
            }
        }
        zzfwVar.mSize = r0;
        return zzfwVar;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfw) {
            zzfw zzfwVar = (zzfw) obj;
            int r1 = this.mSize;
            if (r1 != zzfwVar.mSize) {
                return false;
            }
            int[] r3 = this.zzrn;
            int[] r4 = zzfwVar.zzrn;
            int r5 = 0;
            while (true) {
                if (r5 >= r1) {
                    z = true;
                    break;
                } else if (r3[r5] != r4[r5]) {
                    z = false;
                    break;
                } else {
                    r5++;
                }
            }
            if (z) {
                zzfx[] zzfxVarArr = this.zzro;
                zzfx[] zzfxVarArr2 = zzfwVar.zzro;
                int r32 = this.mSize;
                int r42 = 0;
                while (true) {
                    if (r42 >= r32) {
                        z2 = true;
                        break;
                    } else if (!zzfxVarArr[r42].equals(zzfxVarArr2[r42])) {
                        z2 = false;
                        break;
                    } else {
                        r42++;
                    }
                }
                if (z2) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int r0 = 17;
        for (int r1 = 0; r1 < this.mSize; r1++) {
            r0 = (((r0 * 31) + this.zzrn[r1]) * 31) + this.zzro[r1].hashCode();
        }
        return r0;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int size() {
        return this.mSize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzfx zzaq(int r2) {
        return this.zzro[r2];
    }
}
