package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.collection.ArrayMap;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdmo implements zzdem, com.google.android.gms.ads.internal.overlay.zzo, zzdds {
    IObjectWrapper zza;
    private final Context zzb;
    private final zzcmn zzc;
    private final zzfcs zzd;
    private final zzcgt zze;
    private final zzbev zzf;

    public zzdmo(Context context, zzcmn zzcmnVar, zzfcs zzfcsVar, zzcgt zzcgtVar, zzbev zzbevVar) {
        this.zzb = context;
        this.zzc = zzcmnVar;
        this.zzd = zzfcsVar;
        this.zze = zzcgtVar;
        this.zzf = zzbevVar;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzb() {
        if (this.zza == null || this.zzc == null) {
            return;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzei)).booleanValue()) {
            return;
        }
        this.zzc.zzd("onSdkImpression", new ArrayMap());
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbC() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbK() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzbr() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zze() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzo
    public final void zzf(int r1) {
        this.zza = null;
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        if (this.zza == null || this.zzc == null) {
            return;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzei)).booleanValue()) {
            this.zzc.zzd("onSdkImpression", new ArrayMap());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        zzbyw zzbywVar;
        zzbyw zzbywVar2;
        zzbyv zzbyvVar;
        zzbev zzbevVar;
        if ((this.zzf == zzbev.REWARD_BASED_VIDEO_AD || (zzbevVar = this.zzf) == zzbev.INTERSTITIAL || zzbevVar == zzbev.APP_OPEN) && this.zzd.zzU && this.zzc != null && com.google.android.gms.ads.internal.zzt.zzh().zze(this.zzb)) {
            zzcgt zzcgtVar = this.zze;
            String str = zzcgtVar.zzb + "." + zzcgtVar.zzc;
            String zza = this.zzd.zzW.zza();
            if (this.zzd.zzW.zzb() == 1) {
                zzbyvVar = zzbyv.VIDEO;
                zzbywVar2 = zzbyw.DEFINED_BY_JAVASCRIPT;
            } else {
                if (this.zzd.zzZ == 2) {
                    zzbywVar = zzbyw.UNSPECIFIED;
                } else {
                    zzbywVar = zzbyw.BEGIN_TO_RENDER;
                }
                zzbywVar2 = zzbywVar;
                zzbyvVar = zzbyv.HTML_DISPLAY;
            }
            IObjectWrapper zza2 = com.google.android.gms.ads.internal.zzt.zzh().zza(str, this.zzc.zzI(), "", "javascript", zza, zzbywVar2, zzbyvVar, this.zzd.zzan);
            this.zza = zza2;
            if (zza2 != null) {
                com.google.android.gms.ads.internal.zzt.zzh().zzc(this.zza, (View) this.zzc);
                this.zzc.zzar(this.zza);
                com.google.android.gms.ads.internal.zzt.zzh().zzd(this.zza);
                this.zzc.zzd("onSdkLoaded", new ArrayMap());
            }
        }
    }
}
