package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfad {
    private final zzfey zza;
    private final zzdch zzb;
    private final Executor zzc;
    private zzfac zzd;

    public zzfad(zzfey zzfeyVar, zzdch zzdchVar, Executor executor) {
        this.zza = zzfeyVar;
        this.zzb = zzdchVar;
        this.zzc = executor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public final zzffi zze() {
        zzfdn zzg = this.zzb.zzg();
        return this.zza.zzc(zzg.zzd, zzg.zzf, zzg.zzj);
    }

    public final zzfyx zzc() {
        zzfyx zzf;
        zzfac zzfacVar = this.zzd;
        if (zzfacVar == null) {
            if (!((Boolean) zzbku.zza.zze()).booleanValue()) {
                zzfac zzfacVar2 = new zzfac(null, zze(), null);
                this.zzd = zzfacVar2;
                zzf = zzfyo.zzi(zzfacVar2);
            } else {
                zzf = zzfyo.zzf(zzfyo.zzm(zzfyf.zzv(this.zzb.zzb().zze(this.zza.zza())), new zzfaa(this), this.zzc), zzecu.class, new zzezz(this), this.zzc);
            }
            return zzfyo.zzm(zzf, new zzfru() { // from class: com.google.android.gms.internal.ads.zzezy
                @Override // com.google.android.gms.internal.ads.zzfru
                public final Object apply(Object obj) {
                    return (zzfac) obj;
                }
            }, this.zzc);
        }
        return zzfyo.zzi(zzfacVar);
    }
}
