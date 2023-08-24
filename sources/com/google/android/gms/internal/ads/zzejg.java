package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejg implements zzegs {
    private final Context zza;
    private final zzdnb zzb;
    private final Executor zzc;

    public zzejg(Context context, zzdnb zzdnbVar, Executor executor) {
        this.zza = context;
        this.zzb = zzdnbVar;
        this.zzc = executor;
    }

    private static final boolean zzc(zzfde zzfdeVar, int r1) {
        return zzfdeVar.zza.zza.zzg.contains(Integer.toString(r1));
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds, zzeka {
        zzdoo zzac;
        zzbvq zzB = ((zzfei) zzegnVar.zzb).zzB();
        zzbvr zzC = ((zzfei) zzegnVar.zzb).zzC();
        zzbvu zzd = ((zzfei) zzegnVar.zzb).zzd();
        if (zzd == null || !zzc(zzfdeVar, 6)) {
            if (zzB == null || !zzc(zzfdeVar, 6)) {
                if (zzB == null || !zzc(zzfdeVar, 2)) {
                    if (zzC == null || !zzc(zzfdeVar, 6)) {
                        if (zzC == null || !zzc(zzfdeVar, 1)) {
                            throw new zzeka(1, "No native ad mappers");
                        }
                        zzac = zzdoo.zzac(zzC);
                    } else {
                        zzac = zzdoo.zzae(zzC);
                    }
                } else {
                    zzac = zzdoo.zzab(zzB);
                }
            } else {
                zzac = zzdoo.zzad(zzB);
            }
        } else {
            zzac = zzdoo.zzs(zzd);
        }
        if (!zzfdeVar.zza.zza.zzg.contains(Integer.toString(zzac.zzc()))) {
            throw new zzeka(1, "No corresponding native ad listener");
        }
        zzdoq zze = this.zzb.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzdpa(zzac), new zzdqo(zzC, zzB, zzd, null));
        ((zzeig) zzegnVar.zzc).zzc(zze.zzi());
        zze.zzd().zzj(new zzcuo((zzfei) zzegnVar.zzb), this.zzc);
        return zze.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        zzfdn zzfdnVar = zzfdeVar.zza.zza;
        ((zzfei) zzegnVar.zzb).zzo(this.zza, zzfdeVar.zza.zza.zzd, zzfcsVar.zzw.toString(), com.google.android.gms.ads.internal.util.zzbu.zzl(zzfcsVar.zzt), (zzbvl) zzegnVar.zzc, zzfdnVar.zzi, zzfdnVar.zzg);
    }
}
