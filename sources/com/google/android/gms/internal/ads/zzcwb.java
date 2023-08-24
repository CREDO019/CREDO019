package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcwb {
    private final zzfhz zza;
    private final zzdxo zzb;
    private final zzfde zzc;

    public zzcwb(zzdxo zzdxoVar, zzfde zzfdeVar, zzfhz zzfhzVar) {
        this.zza = zzfhzVar;
        this.zzb = zzdxoVar;
        this.zzc = zzfdeVar;
    }

    private static String zzb(int r1) {
        int r12 = r1 - 1;
        return r12 != 0 ? r12 != 1 ? r12 != 2 ? r12 != 3 ? r12 != 4 ? "u" : "ac" : "cb" : "cc" : "bb" : "h";
    }

    public final void zza(long j, int r10) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhn)).booleanValue()) {
            zzfhz zzfhzVar = this.zza;
            zzfhy zzb = zzfhy.zzb("ad_closed");
            zzb.zzg(this.zzc.zzb.zzb);
            zzb.zza("show_time", String.valueOf(j));
            zzb.zza("ad_format", "app_open_ad");
            zzb.zza("acr", zzb(r10));
            zzfhzVar.zzb(zzb);
            return;
        }
        zzdxn zza = this.zzb.zza();
        zza.zze(this.zzc.zzb.zzb);
        zza.zzb("action", "ad_closed");
        zza.zzb("show_time", String.valueOf(j));
        zza.zzb("ad_format", "app_open_ad");
        zza.zzb("acr", zzb(r10));
        zza.zzg();
    }
}
