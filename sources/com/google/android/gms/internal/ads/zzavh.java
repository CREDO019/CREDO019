package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Stack;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzavh {
    private final byte[] zza = new byte[8];
    private final Stack zzb = new Stack();
    private final zzavp zzc = new zzavp();
    private int zzd;
    private int zze;
    private long zzf;
    private zzavk zzg;

    private final long zzd(zzauu zzauuVar, int r8) throws IOException, InterruptedException {
        zzauuVar.zzh(this.zza, 0, r8, false);
        long j = 0;
        for (int r1 = 0; r1 < r8; r1++) {
            j = (j << 8) | (this.zza[r1] & 255);
        }
        return j;
    }

    public final void zza() {
        this.zzd = 0;
        this.zzb.clear();
        this.zzc.zzd();
    }

    public final void zzb(zzavk zzavkVar) {
        this.zzg = zzavkVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
        if (r0 == 1) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzc(com.google.android.gms.internal.ads.zzauu r13) throws java.io.IOException, java.lang.InterruptedException {
        /*
            Method dump skipped, instructions count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzavh.zzc(com.google.android.gms.internal.ads.zzauu):boolean");
    }
}
