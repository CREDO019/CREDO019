package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdms implements zzdcy, zzdjv {
    private final zzcea zza;
    private final Context zzb;
    private final zzces zzc;
    private final View zzd;
    private String zze;
    private final zzbev zzf;

    public zzdms(zzcea zzceaVar, Context context, zzces zzcesVar, View view, zzbev zzbevVar) {
        this.zza = zzceaVar;
        this.zzb = context;
        this.zzc = zzcesVar;
        this.zzd = view;
        this.zzf = zzbevVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
    }

    @Override // com.google.android.gms.internal.ads.zzdjv
    public final void zzf() {
    }

    @Override // com.google.android.gms.internal.ads.zzdjv
    public final void zzg() {
        if (this.zzf == zzbev.APP_OPEN) {
            return;
        }
        String zzd = this.zzc.zzd(this.zzb);
        this.zze = zzd;
        this.zze = String.valueOf(zzd).concat(this.zzf == zzbev.REWARD_BASED_VIDEO_AD ? "/Rewarded" : "/Interstitial");
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
        this.zza.zzb(false);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
        View view = this.zzd;
        if (view != null && this.zze != null) {
            this.zzc.zzs(view.getContext(), this.zze);
        }
        this.zza.zzb(true);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    @ParametersAreNonnullByDefault
    public final void zzp(zzcbq zzcbqVar, String str, String str2) {
        if (this.zzc.zzu(this.zzb)) {
            try {
                zzces zzcesVar = this.zzc;
                Context context = this.zzb;
                zzcesVar.zzo(context, zzcesVar.zza(context), this.zza.zza(), zzcbqVar.zzc(), zzcbqVar.zzb());
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Remote Exception to get reward item.", e);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
    }
}
