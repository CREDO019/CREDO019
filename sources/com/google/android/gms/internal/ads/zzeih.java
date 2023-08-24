package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeih extends zzcca implements zzdei {
    private zzccb zza;
    private zzdeh zzb;
    private zzdkw zzc;

    @Override // com.google.android.gms.internal.ads.zzdei
    public final synchronized void zza(zzdeh zzdehVar) {
        this.zzb = zzdehVar;
    }

    public final synchronized void zzc(zzccb zzccbVar) {
        this.zza = zzccbVar;
    }

    public final synchronized void zzd(zzdkw zzdkwVar) {
        this.zzc = zzdkwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zze(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            ((zzela) zzccbVar).zzb.onAdClicked();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzf(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            zzccbVar.zzf(iObjectWrapper);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzg(IObjectWrapper iObjectWrapper, int r2) throws RemoteException {
        zzdeh zzdehVar = this.zzb;
        if (zzdehVar != null) {
            zzdehVar.zza(r2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzh(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            ((zzela) zzccbVar).zzc.zzb();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzdeh zzdehVar = this.zzb;
        if (zzdehVar != null) {
            zzdehVar.zzd();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            ((zzela) zzccbVar).zza.zzb();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzk(IObjectWrapper iObjectWrapper, int r2) throws RemoteException {
        zzdkw zzdkwVar = this.zzc;
        if (zzdkwVar != null) {
            com.google.android.gms.ads.internal.util.zze.zzj("Fail to initialize adapter ".concat(String.valueOf(((zzekz) zzdkwVar).zzc.zza)));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzl(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzdkw zzdkwVar = this.zzc;
        if (zzdkwVar != null) {
            Executor zzc = zzelb.zzc(((zzekz) zzdkwVar).zzd);
            final zzfde zzfdeVar = ((zzekz) zzdkwVar).zza;
            final zzfcs zzfcsVar = ((zzekz) zzdkwVar).zzb;
            final zzegn zzegnVar = ((zzekz) zzdkwVar).zzc;
            final zzekz zzekzVar = (zzekz) zzdkwVar;
            zzc.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeky
                @Override // java.lang.Runnable
                public final void run() {
                    zzekz zzekzVar2 = zzekz.this;
                    zzfde zzfdeVar2 = zzfdeVar;
                    zzfcs zzfcsVar2 = zzfcsVar;
                    zzegn zzegnVar2 = zzegnVar;
                    zzelb zzelbVar = zzekzVar2.zzd;
                    zzelb.zze(zzfdeVar2, zzfcsVar2, zzegnVar2);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzm(IObjectWrapper iObjectWrapper, zzccc zzcccVar) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            ((zzela) zzccbVar).zzd.zza(zzcccVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzn(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            ((zzela) zzccbVar).zzc.zze();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzccb
    public final synchronized void zzo(IObjectWrapper iObjectWrapper) throws RemoteException {
        zzccb zzccbVar = this.zza;
        if (zzccbVar != null) {
            ((zzela) zzccbVar).zzd.zzc();
        }
    }
}
