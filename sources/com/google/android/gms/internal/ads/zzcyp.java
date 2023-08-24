package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.collection.ArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcyp implements zzdem, zzdds {
    private final Context zza;
    private final zzcmn zzb;
    private final zzfcs zzc;
    private final zzcgt zzd;
    private IObjectWrapper zze;
    private boolean zzf;

    public zzcyp(Context context, zzcmn zzcmnVar, zzfcs zzfcsVar, zzcgt zzcgtVar) {
        this.zza = context;
        this.zzb = zzcmnVar;
        this.zzc = zzfcsVar;
        this.zzd = zzcgtVar;
    }

    private final synchronized void zza() {
        zzbyv zzbyvVar;
        zzbyw zzbywVar;
        if (this.zzc.zzU) {
            if (this.zzb == null) {
                return;
            }
            if (com.google.android.gms.ads.internal.zzt.zzh().zze(this.zza)) {
                zzcgt zzcgtVar = this.zzd;
                String str = zzcgtVar.zzb + "." + zzcgtVar.zzc;
                String zza = this.zzc.zzW.zza();
                if (this.zzc.zzW.zzb() == 1) {
                    zzbyvVar = zzbyv.VIDEO;
                    zzbywVar = zzbyw.DEFINED_BY_JAVASCRIPT;
                } else {
                    zzbyvVar = zzbyv.HTML_DISPLAY;
                    if (this.zzc.zzf == 1) {
                        zzbywVar = zzbyw.ONE_PIXEL;
                    } else {
                        zzbywVar = zzbyw.BEGIN_TO_RENDER;
                    }
                }
                IObjectWrapper zza2 = com.google.android.gms.ads.internal.zzt.zzh().zza(str, this.zzb.zzI(), "", "javascript", zza, zzbywVar, zzbyvVar, this.zzc.zzan);
                this.zze = zza2;
                zzcmn zzcmnVar = this.zzb;
                if (zza2 != null) {
                    com.google.android.gms.ads.internal.zzt.zzh().zzc(this.zze, (View) zzcmnVar);
                    this.zzb.zzar(this.zze);
                    com.google.android.gms.ads.internal.zzt.zzh().zzd(this.zze);
                    this.zzf = true;
                    this.zzb.zzd("onSdkLoaded", new ArrayMap());
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final synchronized void zzl() {
        zzcmn zzcmnVar;
        if (!this.zzf) {
            zza();
        }
        if (!this.zzc.zzU || this.zze == null || (zzcmnVar = this.zzb) == null) {
            return;
        }
        zzcmnVar.zzd("onSdkImpression", new ArrayMap());
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final synchronized void zzn() {
        if (this.zzf) {
            return;
        }
        zza();
    }
}
