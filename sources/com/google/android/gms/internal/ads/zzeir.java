package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeir implements zzegs {
    private final Context zza;
    private final zzdmf zzb;
    private final zzcgt zzc;
    private final Executor zzd;

    public zzeir(Context context, zzcgt zzcgtVar, zzdmf zzdmfVar, Executor executor) {
        this.zza = context;
        this.zzc = zzcgtVar;
        this.zzb = zzdmfVar;
        this.zzd = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, final zzegn zzegnVar) throws zzfds, zzeka {
        zzdlf zze = this.zzb.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzdli(new zzdmn() { // from class: com.google.android.gms.internal.ads.zzeiq
            @Override // com.google.android.gms.internal.ads.zzdmn
            public final void zza(boolean z, Context context, zzddl zzddlVar) {
                zzeir.this.zzc(zzegnVar, z, context, zzddlVar);
            }
        }, null));
        zze.zzd().zzj(new zzcuo((zzfei) zzegnVar.zzb), this.zzd);
        ((zzeig) zzegnVar.zzc).zzc(zze.zzi());
        return zze.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        ((zzfei) zzegnVar.zzb).zzn(this.zza, zzfdeVar.zza.zza.zzd, zzfcsVar.zzw.toString(), com.google.android.gms.ads.internal.util.zzbu.zzl(zzfcsVar.zzt), (zzbvl) zzegnVar.zzc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(zzegn zzegnVar, boolean z, Context context, zzddl zzddlVar) throws zzdmm {
        try {
            ((zzfei) zzegnVar.zzb).zzu(z);
            if (this.zzc.zzc < ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaB)).intValue()) {
                ((zzfei) zzegnVar.zzb).zzv();
            } else {
                ((zzfei) zzegnVar.zzb).zzw(context);
            }
        } catch (zzfds e) {
            com.google.android.gms.ads.internal.util.zze.zzi("Cannot show interstitial.");
            throw new zzdmm(e.getCause());
        }
    }
}
