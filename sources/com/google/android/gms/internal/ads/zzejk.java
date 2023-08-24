package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejk implements zzegs {
    private final Context zza;
    private final zzdnb zzb;
    private zzbvu zzc;
    private final zzcgt zzd;

    public zzejk(Context context, zzdnb zzdnbVar, zzcgt zzcgtVar) {
        this.zza = context;
        this.zzb = zzdnbVar;
        this.zzd = zzcgtVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds, zzeka {
        if (!zzfdeVar.zza.zza.zzg.contains(Integer.toString(6))) {
            throw new zzeka(2, "Unified must be used for RTB.");
        }
        zzdoo zzs = zzdoo.zzs(this.zzc);
        if (!zzfdeVar.zza.zza.zzg.contains(Integer.toString(zzs.zzc()))) {
            throw new zzeka(1, "No corresponding native ad listener");
        }
        zzdoq zze = this.zzb.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzdpa(zzs), new zzdqo(null, null, this.zzc, null));
        ((zzeig) zzegnVar.zzc).zzc(zze.zzh());
        return zze.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        try {
            ((zzbwy) zzegnVar.zzb).zzp(zzfcsVar.zzaa);
            if (this.zzd.zzc < ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbt)).intValue()) {
                ((zzbwy) zzegnVar.zzb).zzl(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzejj(this, zzegnVar, null), (zzbvl) zzegnVar.zzc);
            } else {
                ((zzbwy) zzegnVar.zzb).zzm(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzejj(this, zzegnVar, null), (zzbvl) zzegnVar.zzc, zzfdeVar.zza.zza.zzi);
            }
        } catch (RemoteException e) {
            throw new zzfds(e);
        }
    }
}
