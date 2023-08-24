package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbuo {
    private final zzbtr zza;
    private zzfyx zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbuo(zzbtr zzbtrVar) {
        this.zza = zzbtrVar;
    }

    private final void zzd() {
        if (this.zzb == null) {
            final zzchf zzchfVar = new zzchf();
            this.zzb = zzchfVar;
            this.zza.zzb(null).zzi(new zzchj() { // from class: com.google.android.gms.internal.ads.zzbum
                @Override // com.google.android.gms.internal.ads.zzchj
                public final void zza(Object obj) {
                    zzchf.this.zzd((zzbts) obj);
                }
            }, new zzchh() { // from class: com.google.android.gms.internal.ads.zzbun
                @Override // com.google.android.gms.internal.ads.zzchh
                public final void zza() {
                    zzchf.this.zze(new zzbtu("Cannot get Javascript Engine"));
                }
            });
        }
    }

    public final zzbur zza(String str, zzbty zzbtyVar, zzbtx zzbtxVar) {
        zzd();
        return new zzbur(this.zzb, "google.afma.activeView.handleUpdate", zzbtyVar, zzbtxVar);
    }

    public final void zzb(final String str, final zzbpq zzbpqVar) {
        zzd();
        this.zzb = zzfyo.zzn(this.zzb, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzbuk
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                zzbts zzbtsVar = (zzbts) obj;
                zzbtsVar.zzq(str, zzbpqVar);
                return zzfyo.zzi(zzbtsVar);
            }
        }, zzcha.zzf);
    }

    public final void zzc(final String str, final zzbpq zzbpqVar) {
        this.zzb = zzfyo.zzm(this.zzb, new zzfru() { // from class: com.google.android.gms.internal.ads.zzbul
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                zzbts zzbtsVar = (zzbts) obj;
                zzbtsVar.zzr(str, zzbpqVar);
                return zzbtsVar;
            }
        }, zzcha.zzf);
    }
}
