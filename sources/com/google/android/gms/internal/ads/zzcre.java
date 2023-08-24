package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcre implements zzdyp {
    private final Long zza;
    private final String zzb;
    private final zzcpu zzc;
    private final zzcri zzd;
    private final zzcre zze = this;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcre(zzcpu zzcpuVar, zzcri zzcriVar, Long l, String str, zzcrd zzcrdVar) {
        this.zzc = zzcpuVar;
        this.zzd = zzcriVar;
        this.zza = l;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.ads.zzdyp
    public final zzdyz zza() {
        Context context;
        zzdys zzc;
        long longValue = this.zza.longValue();
        zzcri zzcriVar = this.zzd;
        context = zzcriVar.zza;
        zzc = zzdyt.zzc(zzcriVar.zzb);
        return zzdza.zza(longValue, context, zzc, this.zzc, this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzdyp
    public final zzdzd zzb() {
        Context context;
        zzdys zzc;
        long longValue = this.zza.longValue();
        zzcri zzcriVar = this.zzd;
        context = zzcriVar.zza;
        zzc = zzdyt.zzc(zzcriVar.zzb);
        return zzdze.zza(longValue, context, zzc, this.zzc, this.zzb);
    }
}
