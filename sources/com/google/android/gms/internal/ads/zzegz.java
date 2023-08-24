package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzegz implements zzdmn {
    private final zzcgt zza;
    private final zzfyx zzb;
    private final zzfcs zzc;
    private final zzcmn zzd;
    private final zzfdn zze;
    private final zzbpt zzf;
    private final boolean zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzegz(zzcgt zzcgtVar, zzfyx zzfyxVar, zzfcs zzfcsVar, zzcmn zzcmnVar, zzfdn zzfdnVar, boolean z, zzbpt zzbptVar) {
        this.zza = zzcgtVar;
        this.zzb = zzfyxVar;
        this.zzc = zzfcsVar;
        this.zzd = zzcmnVar;
        this.zze = zzfdnVar;
        this.zzg = z;
        this.zzf = zzbptVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdmn
    public final void zza(boolean z, Context context, zzddl zzddlVar) {
        int r9;
        zzcwl zzcwlVar = (zzcwl) zzfyo.zzq(this.zzb);
        this.zzd.zzap(true);
        boolean zze = this.zzg ? this.zzf.zze(true) : true;
        boolean z2 = this.zzg;
        com.google.android.gms.ads.internal.zzj zzjVar = new com.google.android.gms.ads.internal.zzj(zze, true, z2 ? this.zzf.zzd() : false, z2 ? this.zzf.zza() : 0.0f, -1, z, this.zzc.zzP, false);
        if (zzddlVar != null) {
            zzddlVar.zzf();
        }
        com.google.android.gms.ads.internal.zzt.zzj();
        zzdmc zzg = zzcwlVar.zzg();
        zzcmn zzcmnVar = this.zzd;
        int r1 = this.zzc.zzR;
        if (r1 == -1) {
            com.google.android.gms.ads.internal.client.zzw zzwVar = this.zze.zzj;
            if (zzwVar != null) {
                int r12 = zzwVar.zza;
                if (r12 == 1) {
                    r9 = 7;
                } else if (r12 == 2) {
                    r9 = 6;
                }
                zzcgt zzcgtVar = this.zza;
                zzfcs zzfcsVar = this.zzc;
                String str = zzfcsVar.zzC;
                zzfcx zzfcxVar = zzfcsVar.zzt;
                com.google.android.gms.ads.internal.overlay.zzm.zza(context, new AdOverlayInfoParcel((com.google.android.gms.ads.internal.client.zza) null, zzg, (com.google.android.gms.ads.internal.overlay.zzz) null, zzcmnVar, r9, zzcgtVar, str, zzjVar, zzfcxVar.zzb, zzfcxVar.zza, this.zze.zzf, zzddlVar), true);
            }
            com.google.android.gms.ads.internal.util.zze.zze("Error setting app open orientation; no targeting orientation available.");
            r1 = this.zzc.zzR;
        }
        r9 = r1;
        zzcgt zzcgtVar2 = this.zza;
        zzfcs zzfcsVar2 = this.zzc;
        String str2 = zzfcsVar2.zzC;
        zzfcx zzfcxVar2 = zzfcsVar2.zzt;
        com.google.android.gms.ads.internal.overlay.zzm.zza(context, new AdOverlayInfoParcel((com.google.android.gms.ads.internal.client.zza) null, zzg, (com.google.android.gms.ads.internal.overlay.zzz) null, zzcmnVar, r9, zzcgtVar2, str2, zzjVar, zzfcxVar2.zzb, zzfcxVar2.zza, this.zze.zzf, zzddlVar), true);
    }
}
