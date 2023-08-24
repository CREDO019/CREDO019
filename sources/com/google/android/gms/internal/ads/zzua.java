package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzua extends zzcn {
    private static final Object zzc = new Object();
    private static final zzbg zzd;
    private final long zze;
    private final long zzf;
    private final boolean zzg;
    private final zzbg zzh;
    private final zzaw zzi;

    static {
        zzaj zzajVar = new zzaj();
        zzajVar.zza("SinglePeriodTimeline");
        zzajVar.zzb(Uri.EMPTY);
        zzd = zzajVar.zzc();
    }

    public zzua(long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z, boolean z2, boolean z3, Object obj, zzbg zzbgVar, zzaw zzawVar) {
        this.zze = j4;
        this.zzf = j5;
        this.zzg = z;
        this.zzh = zzbgVar;
        this.zzi = zzawVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zza(Object obj) {
        return zzc.equals(obj) ? 0 : -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzb() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final int zzc() {
        return 1;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzck zzd(int r11, zzck zzckVar, boolean z) {
        zzdd.zza(r11, 0, 1);
        zzckVar.zzk(null, z ? zzc : null, 0, this.zze, 0L, zzd.zza, false);
        return zzckVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final zzcm zze(int r23, zzcm zzcmVar, long j) {
        zzdd.zza(r23, 0, 1);
        zzcmVar.zza(zzcm.zza, this.zzh, null, C1856C.TIME_UNSET, C1856C.TIME_UNSET, C1856C.TIME_UNSET, this.zzg, false, this.zzi, 0L, this.zzf, 0, 0, 0L);
        return zzcmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcn
    public final Object zzf(int r3) {
        zzdd.zza(r3, 0, 1);
        return zzc;
    }
}
