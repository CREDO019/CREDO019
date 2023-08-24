package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzekf implements zzegs {
    private final Context zza;
    private final Executor zzb;
    private final zzduf zzc;

    public zzekf(Context context, Executor executor, zzduf zzdufVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzdufVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, final zzegn zzegnVar) throws zzfds, zzeka {
        zzdub zze = this.zzc.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzduc(new zzdmn() { // from class: com.google.android.gms.internal.ads.zzeke
            @Override // com.google.android.gms.internal.ads.zzdmn
            public final void zza(boolean z, Context context, zzddl zzddlVar) {
                zzegn zzegnVar2 = zzegn.this;
                try {
                    ((zzfei) zzegnVar2.zzb).zzu(z);
                    ((zzfei) zzegnVar2.zzb).zzx(context);
                } catch (zzfds e) {
                    throw new zzdmm(e.getCause());
                }
            }
        }));
        zze.zzd().zzj(new zzcuo((zzfei) zzegnVar.zzb), this.zzb);
        ((zzeig) zzegnVar.zzc).zzc(zze.zzm());
        return zze.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        try {
            zzfdn zzfdnVar = zzfdeVar.zza.zza;
            if (zzfdnVar.zzo.zza == 3) {
                ((zzfei) zzegnVar.zzb).zzq(this.zza, zzfdnVar.zzd, zzfcsVar.zzw.toString(), (zzbvl) zzegnVar.zzc);
            } else {
                ((zzfei) zzegnVar.zzb).zzp(this.zza, zzfdnVar.zzd, zzfcsVar.zzw.toString(), (zzbvl) zzegnVar.zzc);
            }
        } catch (Exception e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Fail to load ad from adapter ".concat(String.valueOf(zzegnVar.zza)), e);
        }
    }
}
