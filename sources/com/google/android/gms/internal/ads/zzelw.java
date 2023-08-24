package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzelw implements zzegk {
    private final zzbjt zza;
    private final zzfyy zzb;
    private final zzfhp zzc;
    private final zzemf zzd;

    public zzelw(zzfhp zzfhpVar, zzfyy zzfyyVar, zzbjt zzbjtVar, zzemf zzemfVar, byte[] bArr) {
        this.zzc = zzfhpVar;
        this.zzb = zzfyyVar;
        this.zza = zzbjtVar;
        this.zzd = zzemfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzchf zzchfVar = new zzchf();
        zzemb zzembVar = new zzemb();
        zzembVar.zzd(new zzelv(this, zzchfVar, zzfdeVar, zzfcsVar, zzembVar));
        zzfcx zzfcxVar = zzfcsVar.zzt;
        final zzbjo zzbjoVar = new zzbjo(zzembVar, zzfcxVar.zzb, zzfcxVar.zza);
        zzfhp zzfhpVar = this.zzc;
        return zzfgz.zzd(new zzfgt() { // from class: com.google.android.gms.internal.ads.zzelu
            @Override // com.google.android.gms.internal.ads.zzfgt
            public final void zza() {
                zzelw.this.zzc(zzbjoVar);
            }
        }, this.zzb, zzfhj.CUSTOM_RENDER_SYN, zzfhpVar).zzb(zzfhj.CUSTOM_RENDER_ACK).zzd(zzchfVar).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfcx zzfcxVar;
        return (this.zza == null || (zzfcxVar = zzfcsVar.zzt) == null || zzfcxVar.zza == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzbjo zzbjoVar) throws Exception {
        this.zza.zze(zzbjoVar);
    }
}
