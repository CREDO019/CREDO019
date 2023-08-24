package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaaz implements zzzf {
    private int zzc;
    private zzaba zze;
    private long zzh;
    private zzabc zzi;
    private int zzm;
    private boolean zzn;
    private final zzed zza = new zzed(12);
    private final zzaay zzb = new zzaay(null);
    private zzzi zzd = new zzzd();
    private zzabc[] zzg = new zzabc[0];
    private long zzk = -1;
    private long zzl = -1;
    private int zzj = -1;
    private long zzf = C1856C.TIME_UNSET;

    private final zzabc zzf(int r6) {
        zzabc[] zzabcVarArr;
        for (zzabc zzabcVar : this.zzg) {
            if (zzabcVar.zzg(r6)) {
                return zzabcVar;
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzc = 0;
        this.zzd = zzziVar;
        this.zzh = -1L;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        int r4;
        this.zzh = -1L;
        this.zzi = null;
        for (zzabc zzabcVar : this.zzg) {
            zzabcVar.zzf(j);
        }
        if (j != 0) {
            r4 = 6;
        } else if (this.zzg.length == 0) {
            this.zzc = 0;
            return;
        } else {
            r4 = 3;
        }
        this.zzc = r4;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        ((zzyv) zzzgVar).zzm(this.zza.zzH(), 0, 12, false);
        this.zza.zzF(0);
        if (this.zza.zzg() != 1179011410) {
            return false;
        }
        this.zza.zzG(4);
        return this.zza.zzg() == 541677121;
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0033 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0034  */
    @Override // com.google.android.gms.internal.ads.zzzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(com.google.android.gms.internal.ads.zzzg r23, com.google.android.gms.internal.ads.zzaaf r24) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 961
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaaz.zza(com.google.android.gms.internal.ads.zzzg, com.google.android.gms.internal.ads.zzaaf):int");
    }
}
