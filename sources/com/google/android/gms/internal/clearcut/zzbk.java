package com.google.android.gms.internal.clearcut;

/* loaded from: classes2.dex */
public abstract class zzbk {
    private static volatile boolean zzft = true;
    private int zzfq;
    private int zzfr;
    private boolean zzfs;

    private zzbk() {
        this.zzfq = 100;
        this.zzfr = Integer.MAX_VALUE;
        this.zzfs = false;
    }

    public static long zza(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbk zza(byte[] bArr, int r7, int r8, boolean z) {
        zzbm zzbmVar = new zzbm(bArr, 0, r8, false);
        try {
            zzbmVar.zzl(r8);
            return zzbmVar;
        } catch (zzco e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzm(int r1) {
        return (-(r1 & 1)) ^ (r1 >>> 1);
    }

    public abstract int zzaf();

    public abstract int zzl(int r1) throws zzco;
}
