package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzekj implements zzegs {
    private final Context zza;
    private final zzduf zzb;

    public zzekj(Context context, zzduf zzdufVar) {
        this.zza = context;
        this.zzb = zzdufVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds, zzeka {
        zzeip zzeipVar = new zzeip(zzfcsVar, (zzbwy) zzegnVar.zzb, true);
        zzdub zze = this.zzb.zze(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzduc(zzeipVar));
        zzeipVar.zzb(zze.zzc());
        ((zzeig) zzegnVar.zzc).zzc(zze.zzn());
        return zze.zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        try {
            ((zzbwy) zzegnVar.zzb).zzp(zzfcsVar.zzaa);
            if (zzfdeVar.zza.zza.zzo.zza == 3) {
                ((zzbwy) zzegnVar.zzb).zzn(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzeki(this, zzegnVar, null), (zzbvl) zzegnVar.zzc);
            } else {
                ((zzbwy) zzegnVar.zzb).zzo(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzeki(this, zzegnVar, null), (zzbvl) zzegnVar.zzc);
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.zze.zzb("Remote exception loading a rewarded RTB ad", e);
        }
    }
}
