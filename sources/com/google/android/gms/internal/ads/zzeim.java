package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeim implements zzdmn {
    private final Context zza;
    private final zzcgt zzb;
    private final zzfyx zzc;
    private final zzfcs zzd;
    private final zzcmn zze;
    private final zzfdn zzf;
    private final zzbpt zzg;
    private final boolean zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeim(Context context, zzcgt zzcgtVar, zzfyx zzfyxVar, zzfcs zzfcsVar, zzcmn zzcmnVar, zzfdn zzfdnVar, boolean z, zzbpt zzbptVar) {
        this.zza = context;
        this.zzb = zzcgtVar;
        this.zzc = zzfyxVar;
        this.zzd = zzfcsVar;
        this.zze = zzcmnVar;
        this.zzf = zzfdnVar;
        this.zzg = zzbptVar;
        this.zzh = z;
    }

    @Override // com.google.android.gms.internal.ads.zzdmn
    public final void zza(boolean z, Context context, zzddl zzddlVar) {
        zzdlf zzdlfVar = (zzdlf) zzfyo.zzq(this.zzc);
        this.zze.zzap(true);
        boolean zze = this.zzh ? this.zzg.zze(false) : false;
        com.google.android.gms.ads.internal.zzt.zzq();
        boolean zzE = com.google.android.gms.ads.internal.util.zzs.zzE(this.zza);
        boolean z2 = this.zzh;
        com.google.android.gms.ads.internal.zzj zzjVar = new com.google.android.gms.ads.internal.zzj(zze, zzE, z2 ? this.zzg.zzd() : false, z2 ? this.zzg.zza() : 0.0f, -1, z, this.zzd.zzP, false);
        if (zzddlVar != null) {
            zzddlVar.zzf();
        }
        com.google.android.gms.ads.internal.zzt.zzj();
        zzdmc zzj = zzdlfVar.zzj();
        zzcmn zzcmnVar = this.zze;
        zzfcs zzfcsVar = this.zzd;
        int r9 = zzfcsVar.zzR;
        zzcgt zzcgtVar = this.zzb;
        String str = zzfcsVar.zzC;
        zzfcx zzfcxVar = zzfcsVar.zzt;
        com.google.android.gms.ads.internal.overlay.zzm.zza(context, new AdOverlayInfoParcel((com.google.android.gms.ads.internal.client.zza) null, zzj, (com.google.android.gms.ads.internal.overlay.zzz) null, zzcmnVar, r9, zzcgtVar, str, zzjVar, zzfcxVar.zzb, zzfcxVar.zza, this.zzf.zzf, zzddlVar), true);
    }
}
