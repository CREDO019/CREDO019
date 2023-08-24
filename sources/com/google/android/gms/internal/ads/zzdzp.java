package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdzp extends zzbro {
    final /* synthetic */ Object zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ zzfir zzd;
    final /* synthetic */ zzchf zze;
    final /* synthetic */ zzdzq zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdzp(zzdzq zzdzqVar, Object obj, String str, long j, zzfir zzfirVar, zzchf zzchfVar) {
        this.zzf = zzdzqVar;
        this.zza = obj;
        this.zzb = str;
        this.zzc = j;
        this.zzd = zzfirVar;
        this.zze = zzchfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbrp
    public final void zze(String str) {
        zzdxx zzdxxVar;
        zzdjp zzdjpVar;
        zzfje zzfjeVar;
        synchronized (this.zza) {
            this.zzf.zzv(this.zzb, false, str, (int) (com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - this.zzc));
            zzdxxVar = this.zzf.zzl;
            zzdxxVar.zzb(this.zzb, "error");
            zzdjpVar = this.zzf.zzo;
            zzdjpVar.zzb(this.zzb, "error");
            zzfjeVar = this.zzf.zzp;
            zzfir zzfirVar = this.zzd;
            zzfirVar.zze(false);
            zzfjeVar.zzb(zzfirVar.zzj());
            this.zze.zzd(false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbrp
    public final void zzf() {
        zzdxx zzdxxVar;
        zzdjp zzdjpVar;
        zzfje zzfjeVar;
        synchronized (this.zza) {
            this.zzf.zzv(this.zzb, true, "", (int) (com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - this.zzc));
            zzdxxVar = this.zzf.zzl;
            zzdxxVar.zzd(this.zzb);
            zzdjpVar = this.zzf.zzo;
            zzdjpVar.zzd(this.zzb);
            zzfjeVar = this.zzf.zzp;
            zzfir zzfirVar = this.zzd;
            zzfirVar.zze(true);
            zzfjeVar.zzb(zzfirVar.zzj());
            this.zze.zzd(true);
        }
    }
}
