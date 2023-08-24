package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeeh implements zzdjr {
    private final String zzc;
    private final zzfhz zzd;
    private boolean zza = false;
    private boolean zzb = false;
    private final com.google.android.gms.ads.internal.util.zzg zze = com.google.android.gms.ads.internal.zzt.zzp().zzh();

    public zzeeh(String str, zzfhz zzfhzVar) {
        this.zzc = str;
        this.zzd = zzfhzVar;
    }

    private final zzfhy zzg(String str) {
        String str2 = this.zze.zzP() ? "" : this.zzc;
        zzfhy zzb = zzfhy.zzb(str);
        zzb.zza("tms", Long.toString(com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime(), 10));
        zzb.zza("tid", str2);
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzdjr
    public final void zza(String str) {
        zzfhz zzfhzVar = this.zzd;
        zzfhy zzg = zzg("aaia");
        zzg.zza("aair", "MalformedJson");
        zzfhzVar.zzb(zzg);
    }

    @Override // com.google.android.gms.internal.ads.zzdjr
    public final void zzb(String str, String str2) {
        zzfhz zzfhzVar = this.zzd;
        zzfhy zzg = zzg("adapter_init_finished");
        zzg.zza("ancn", str);
        zzg.zza("rqe", str2);
        zzfhzVar.zzb(zzg);
    }

    @Override // com.google.android.gms.internal.ads.zzdjr
    public final void zzc(String str) {
        zzfhz zzfhzVar = this.zzd;
        zzfhy zzg = zzg("adapter_init_started");
        zzg.zza("ancn", str);
        zzfhzVar.zzb(zzg);
    }

    @Override // com.google.android.gms.internal.ads.zzdjr
    public final void zzd(String str) {
        zzfhz zzfhzVar = this.zzd;
        zzfhy zzg = zzg("adapter_init_finished");
        zzg.zza("ancn", str);
        zzfhzVar.zzb(zzg);
    }

    @Override // com.google.android.gms.internal.ads.zzdjr
    public final synchronized void zze() {
        if (this.zzb) {
            return;
        }
        this.zzd.zzb(zzg("init_finished"));
        this.zzb = true;
    }

    @Override // com.google.android.gms.internal.ads.zzdjr
    public final synchronized void zzf() {
        if (this.zza) {
            return;
        }
        this.zzd.zzb(zzg("init_started"));
        this.zza = true;
    }
}
