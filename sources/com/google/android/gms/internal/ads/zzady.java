package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayDeque;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzady implements zzaea {
    private final byte[] zza = new byte[8];
    private final ArrayDeque zzb = new ArrayDeque();
    private final zzaeh zzc = new zzaeh();
    private zzadz zzd;
    private int zze;
    private int zzf;
    private long zzg;

    private final long zzd(zzzg zzzgVar, int r8) throws IOException {
        ((zzyv) zzzgVar).zzn(this.zza, 0, r8, false);
        long j = 0;
        for (int r1 = 0; r1 < r8; r1++) {
            j = (j << 8) | (this.zza[r1] & 255);
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzaea
    public final void zza(zzadz zzadzVar) {
        this.zzd = zzadzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaea
    public final void zzb() {
        this.zze = 0;
        this.zzb.clear();
        this.zzc.zze();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0087, code lost:
        if (r0 == 1) goto L29;
     */
    @Override // com.google.android.gms.internal.ads.zzaea
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzc(com.google.android.gms.internal.ads.zzzg r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzady.zzc(com.google.android.gms.internal.ads.zzzg):boolean");
    }
}
