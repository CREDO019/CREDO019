package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzehp implements zzegs {
    private final Context zza;
    private final zzcxx zzb;
    private final Executor zzc;

    public zzehp(Context context, zzcxx zzcxxVar, Executor executor) {
        this.zza = context;
        this.zzb = zzcxxVar;
        this.zzc = executor;
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final /* bridge */ /* synthetic */ Object zza(zzfde zzfdeVar, final zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds, zzeka {
        final View zza;
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() || !zzfcsVar.zzai) {
            zza = ((zzfei) zzegnVar.zzb).zza();
        } else {
            zzbvo zzc = ((zzfei) zzegnVar.zzb).zzc();
            if (zzc == null) {
                com.google.android.gms.ads.internal.util.zze.zzg("getInterscrollerAd should not be null after loadInterscrollerAd loaded ad.");
                throw new zzfds(new Exception("getInterscrollerAd should not be null after loadInterscrollerAd loaded ad."));
            }
            try {
                zza = (View) ObjectWrapper.unwrap(zzc.zze());
                boolean zzf = zzc.zzf();
                if (zza == null) {
                    throw new zzfds(new Exception("BannerAdapterWrapper interscrollerView should not be null"));
                }
                if (zzf) {
                    try {
                        zza = (View) zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeho
                            @Override // com.google.android.gms.internal.ads.zzfxv
                            public final zzfyx zza(Object obj) {
                                return zzehp.this.zzc(zza, zzfcsVar, obj);
                            }
                        }, zzcha.zze).get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new zzfds(e);
                    }
                }
            } catch (RemoteException e2) {
                throw new zzfds(e2);
            }
        }
        zzcxx zzcxxVar = this.zzb;
        zzczr zzczrVar = new zzczr(zzfdeVar, zzfcsVar, zzegnVar.zza);
        final zzfei zzfeiVar = (zzfei) zzegnVar.zzb;
        zzcxb zza2 = zzcxxVar.zza(zzczrVar, new zzcxh(zza, null, new zzcza() { // from class: com.google.android.gms.internal.ads.zzehn
            @Override // com.google.android.gms.internal.ads.zzcza
            public final com.google.android.gms.ads.internal.client.zzdk zza() {
                return zzfei.this.zzb();
            }
        }, (zzfct) zzfcsVar.zzv.get(0)));
        zza2.zzg().zza(zza);
        zza2.zzd().zzj(new zzcuo((zzfei) zzegnVar.zzb), this.zzc);
        ((zzeig) zzegnVar.zzc).zzc(zza2.zzi());
        return zza2.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzegs
    public final void zzb(zzfde zzfdeVar, zzfcs zzfcsVar, zzegn zzegnVar) throws zzfds {
        com.google.android.gms.ads.internal.client.zzq zza;
        com.google.android.gms.ads.internal.client.zzq zzqVar = zzfdeVar.zza.zza.zze;
        if (zzqVar.zzn) {
            zza = new com.google.android.gms.ads.internal.client.zzq(this.zza, com.google.android.gms.ads.zzb.zzd(zzqVar.zze, zzqVar.zzb));
        } else {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() || !zzfcsVar.zzai) {
                zza = zzfdr.zza(this.zza, zzfcsVar.zzv);
            } else {
                zza = new com.google.android.gms.ads.internal.client.zzq(this.zza, com.google.android.gms.ads.zzb.zze(zzqVar.zze, zzqVar.zzb));
            }
        }
        com.google.android.gms.ads.internal.client.zzq zzqVar2 = zza;
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() || !zzfcsVar.zzai) {
            ((zzfei) zzegnVar.zzb).zzl(this.zza, zzqVar2, zzfdeVar.zza.zza.zzd, zzfcsVar.zzw.toString(), com.google.android.gms.ads.internal.util.zzbu.zzl(zzfcsVar.zzt), (zzbvl) zzegnVar.zzc);
        } else {
            ((zzfei) zzegnVar.zzb).zzm(this.zza, zzqVar2, zzfdeVar.zza.zza.zzd, zzfcsVar.zzw.toString(), com.google.android.gms.ads.internal.util.zzbu.zzl(zzfcsVar.zzt), (zzbvl) zzegnVar.zzc);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(View view, zzfcs zzfcsVar, Object obj) throws Exception {
        return zzfyo.zzi(zzcyo.zza(this.zza, view, zzfcsVar));
    }
}
