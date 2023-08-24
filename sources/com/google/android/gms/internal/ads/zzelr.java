package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzelr implements zzegk {
    private final Context zza;
    private final zzcxx zzb;
    private final zzbjt zzc;
    private final zzfyy zzd;
    private final zzfhp zze;

    public zzelr(Context context, zzcxx zzcxxVar, zzfhp zzfhpVar, zzfyy zzfyyVar, zzbjt zzbjtVar) {
        this.zza = context;
        this.zzb = zzcxxVar;
        this.zze = zzfhpVar;
        this.zzd = zzfyyVar;
        this.zzc = zzbjtVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzcxb zza = this.zzb.zza(new zzczr(zzfdeVar, zzfcsVar, null), new zzelp(this, new View(this.zza), null, new zzcza() { // from class: com.google.android.gms.internal.ads.zzeln
            @Override // com.google.android.gms.internal.ads.zzcza
            public final com.google.android.gms.ads.internal.client.zzdk zza() {
                return null;
            }
        }, (zzfct) zzfcsVar.zzv.get(0)));
        zzelq zzk = zza.zzk();
        zzfcx zzfcxVar = zzfcsVar.zzt;
        final zzbjo zzbjoVar = new zzbjo(zzk, zzfcxVar.zzb, zzfcxVar.zza);
        zzfhp zzfhpVar = this.zze;
        return zzfgz.zzd(new zzfgt() { // from class: com.google.android.gms.internal.ads.zzelo
            @Override // com.google.android.gms.internal.ads.zzfgt
            public final void zza() {
                zzelr.this.zzc(zzbjoVar);
            }
        }, this.zzd, zzfhj.CUSTOM_RENDER_SYN, zzfhpVar).zzb(zzfhj.CUSTOM_RENDER_ACK).zzd(zzfyo.zzi(zza.zza())).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfcx zzfcxVar;
        return (this.zzc == null || (zzfcxVar = zzfcsVar.zzt) == null || zzfcxVar.zza == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzbjo zzbjoVar) throws Exception {
        this.zzc.zze(zzbjoVar);
    }
}
