package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzelb implements zzegs {
    private final Context zza;
    private final Executor zzb;
    private final zzduf zzc;

    public zzelb(Context context, Executor executor, zzduf zzdufVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzdufVar;
    }

    public static /* bridge */ /* synthetic */ Executor zzc(zzelb zzelbVar) {
        return zzelbVar.zzb;
    }

    public static final void zze(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) {
        try {
            ((zzfei) zzegnVar.zzb).zzk(zzfdeVar.zza.zza.zzd, zzfcsVar.zzw.toString());
        } catch (Exception e) {
            com.google.android.gms.ads.internal.util.zze.zzk("Fail to load ad from adapter ".concat(String.valueOf(zzegnVar.zza)), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, final zzegn zzegnVar) throws zzfds, zzeka {
        zzdub zze = this.zzc.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzduc(new zzdmn() { // from class: com.google.android.gms.internal.ads.zzekx
            @Override // com.google.android.gms.internal.ads.zzdmn
            public final void zza(boolean z, Context context, zzddl zzddlVar) {
                zzegn zzegnVar2 = zzegn.this;
                try {
                    ((zzfei) zzegnVar2.zzb).zzu(z);
                    ((zzfei) zzegnVar2.zzb).zzy();
                } catch (zzfds e) {
                    com.google.android.gms.ads.internal.util.zze.zzk("Cannot show rewarded video.", e);
                    throw new zzdmm(e.getCause());
                }
            }
        }));
        zze.zzd().zzj(new zzcuo((zzfei) zzegnVar.zzb), this.zzb);
        zzdef zze2 = zze.zze();
        zzdcw zzb = zze.zzb();
        ((zzeih) zzegnVar.zzc).zzc(new zzela(this, zze.zza(), zzb, zze2, zze.zzg()));
        return zze.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        if (!((zzfei) zzegnVar.zzb).zzA()) {
            ((zzeih) zzegnVar.zzc).zzd(new zzekz(this, zzfdeVar, zzfcsVar, zzegnVar));
            ((zzfei) zzegnVar.zzb).zzh(this.zza, zzfdeVar.zza.zza.zzd, null, (zzccb) zzegnVar.zzc, zzfcsVar.zzw.toString());
            return;
        }
        zze(zzfdeVar, zzfcsVar, zzegnVar);
    }
}
