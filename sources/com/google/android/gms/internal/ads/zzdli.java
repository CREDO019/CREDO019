package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzdli {
    private final zzdmn zza;
    private final zzcmn zzb;

    public zzdli(zzdmn zzdmnVar, zzcmn zzcmnVar) {
        this.zza = zzdmnVar;
        this.zzb = zzcmnVar;
    }

    public static final zzdke zzh(zzfio zzfioVar) {
        return new zzdke(zzfioVar, zzcha.zzf);
    }

    public static final zzdke zzi(zzdms zzdmsVar) {
        return new zzdke(zzdmsVar, zzcha.zzf);
    }

    public final View zza() {
        zzcmn zzcmnVar = this.zzb;
        if (zzcmnVar == null) {
            return null;
        }
        return zzcmnVar.zzI();
    }

    public final View zzb() {
        zzcmn zzcmnVar = this.zzb;
        if (zzcmnVar != null) {
            return zzcmnVar.zzI();
        }
        return null;
    }

    public final zzcmn zzc() {
        return this.zzb;
    }

    public final zzdke zzd(Executor executor) {
        final zzcmn zzcmnVar = this.zzb;
        return new zzdke(new zzdhk() { // from class: com.google.android.gms.internal.ads.zzdlg
            @Override // com.google.android.gms.internal.ads.zzdhk
            public final void zza() {
                zzcmn zzcmnVar2 = zzcmn.this;
                if (zzcmnVar2.zzN() != null) {
                    zzcmnVar2.zzN().zzb();
                }
            }
        }, executor);
    }

    public final zzdmn zze() {
        return this.zza;
    }

    public Set zzf(zzdby zzdbyVar) {
        return Collections.singleton(new zzdke(zzdbyVar, zzcha.zzf));
    }

    public Set zzg(zzdby zzdbyVar) {
        return Collections.singleton(new zzdke(zzdbyVar, zzcha.zzf));
    }
}
