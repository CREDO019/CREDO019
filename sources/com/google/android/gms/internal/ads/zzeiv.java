package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeiv implements zzegs {
    private final Context zza;
    private final zzdmf zzb;

    public zzeiv(Context context, zzdmf zzdmfVar) {
        this.zza = context;
        this.zzb = zzdmfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds, zzeka {
        zzeip zzeipVar = new zzeip(zzfcsVar, (zzbwy) zzegnVar.zzb, false);
        zzdlf zze = this.zzb.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzdli(zzeipVar, null));
        zzeipVar.zzb(zze.zzc());
        ((zzeig) zzegnVar.zzc).zzc(zze.zzh());
        return zze.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        try {
            ((zzbwy) zzegnVar.zzb).zzp(zzfcsVar.zzaa);
            ((zzbwy) zzegnVar.zzb).zzk(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzeiu(this, zzegnVar, null), (zzbvl) zzegnVar.zzc);
        } catch (RemoteException e) {
            throw new zzfds(e);
        }
    }
}
