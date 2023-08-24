package com.google.android.gms.internal.clearcut;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbm extends zzbk {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzfu;
    private int zzfv;
    private int zzfw;
    private int zzfx;

    private zzbm(byte[] bArr, int r3, int r4, boolean z) {
        super();
        this.zzfx = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = r4 + r3;
        this.pos = r3;
        this.zzfw = r3;
        this.zzfu = z;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbk
    public final int zzaf() {
        return this.pos - this.zzfw;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbk
    public final int zzl(int r4) throws zzco {
        if (r4 >= 0) {
            int zzaf = r4 + zzaf();
            int r0 = this.zzfx;
            if (zzaf <= r0) {
                this.zzfx = zzaf;
                int r1 = this.limit + this.zzfv;
                this.limit = r1;
                int r2 = r1 - this.zzfw;
                if (r2 > zzaf) {
                    int r22 = r2 - zzaf;
                    this.zzfv = r22;
                    this.limit = r1 - r22;
                } else {
                    this.zzfv = 0;
                }
                return r0;
            }
            throw zzco.zzbl();
        }
        throw new zzco("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }
}
