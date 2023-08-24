package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzehv implements zzegs {
    private final Context zza;
    private final zzcxx zzb;
    private View zzc;
    private zzbvo zzd;

    public zzehv(Context context, zzcxx zzcxxVar) {
        this.zza = context;
        this.zzb = zzcxxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, final zzfcs zzfcsVar, final zzegn zzegnVar) throws zzfds, zzeka {
        final View view;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() && zzfcsVar.zzai) {
            try {
                view = (View) ObjectWrapper.unwrap(this.zzd.zze());
                boolean zzf = this.zzd.zzf();
                if (view == null) {
                    throw new zzfds(new Exception("BannerRtbAdapterWrapper interscrollerView should not be null"));
                }
                if (zzf) {
                    try {
                        view = (View) zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzehr
                            @Override // com.google.android.gms.internal.ads.zzfxv
                            public final zzfyx zza(Object obj) {
                                return zzehv.this.zzc(view, zzfcsVar, obj);
                            }
                        }, zzcha.zze).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new zzfds(e);
                    }
                }
            } catch (RemoteException e2) {
                throw new zzfds(e2);
            }
        } else {
            view = this.zzc;
        }
        zzcxb zza = this.zzb.zza(new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza), new zzcxh(view, null, new zzcza() { // from class: com.google.android.gms.internal.ads.zzehs
            @Override // com.google.android.gms.internal.ads.zzcza
            public final com.google.android.gms.ads.internal.client.zzdk zza() {
                try {
                    return ((zzbwy) zzegn.this.zzb).zze();
                } catch (RemoteException e3) {
                    throw new zzfds(e3);
                }
            }
        }, (zzfct) zzfcsVar.zzv.get(0)));
        zza.zzg().zza(view);
        ((zzeig) zzegnVar.zzc).zzc(zza.zzh());
        return zza.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        try {
            ((zzbwy) zzegnVar.zzb).zzp(zzfcsVar.zzaa);
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() || !zzfcsVar.zzai) {
                ((zzbwy) zzegnVar.zzb).zzi(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzehu(this, zzegnVar, null), (zzbvl) zzegnVar.zzc, zzfdeVar.zza.zza.zze);
            } else {
                ((zzbwy) zzegnVar.zzb).zzj(zzfcsVar.zzV, zzfcsVar.zzw.toString(), zzfdeVar.zza.zza.zzd, ObjectWrapper.wrap(this.zza), new zzehu(this, zzegnVar, null), (zzbvl) zzegnVar.zzc, zzfdeVar.zza.zza.zze);
            }
        } catch (RemoteException e) {
            throw new zzfds(e);
        }
    }

    public final /* synthetic */ zzfyx zzc(View view, zzfcs zzfcsVar, Object obj) throws Exception {
        return zzfyo.zzi(zzcyo.zza(this.zza, view, zzfcsVar));
    }
}
