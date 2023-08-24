package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzekv implements zzegk {
    private final Context zza;
    private final zzduw zzb;
    private final zzduf zzc;
    private final zzfdn zzd;
    private final Executor zze;
    private final zzcgt zzf;
    private final zzbpt zzg;
    private final boolean zzh = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhy)).booleanValue();

    public zzekv(Context context, zzcgt zzcgtVar, zzfdn zzfdnVar, Executor executor, zzduf zzdufVar, zzduw zzduwVar, zzbpt zzbptVar) {
        this.zza = context;
        this.zzd = zzfdnVar;
        this.zzc = zzdufVar;
        this.zze = executor;
        this.zzf = zzcgtVar;
        this.zzb = zzduwVar;
        this.zzg = zzbptVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        final zzdva zzdvaVar = new zzdva();
        zzfyx zzn = zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeko
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzekv.this.zzc(zzfcsVar, zzfdeVar, zzdvaVar, obj);
            }
        }, this.zze);
        zzn.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzekp
            @Override // java.lang.Runnable
            public final void run() {
                zzdva.this.zzb();
            }
        }, this.zze);
        return zzn;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return (zzfcxVar == null || zzfcxVar.zza == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(final zzfcs zzfcsVar, zzfde zzfdeVar, zzdva zzdvaVar, Object obj) throws Exception {
        final zzcmn zza = this.zzb.zza(this.zzd.zze, zzfcsVar, zzfdeVar.zzb.zzb);
        zza.zzab(zzfcsVar.zzX);
        zzdvaVar.zza(this.zza, (View) zza);
        zzchf zzchfVar = new zzchf();
        final zzdub zze = this.zzc.zze(new zzczr(zzfdeVar, zzfcsVar, null), new zzduc(new zzeku(this.zza, this.zzb, this.zzd, this.zzf, zzfcsVar, zzchfVar, zza, this.zzg, this.zzh), zza));
        zzchfVar.zzd(zze);
        zzbqg.zzb(zza, zze.zzg());
        zze.zzc().zzj(new zzdds() { // from class: com.google.android.gms.internal.ads.zzekq
            @Override // com.google.android.gms.internal.ads.zzdds
            public final void zzl() {
                zzcmn zzcmnVar = zzcmn.this;
                if (zzcmnVar.zzP() != null) {
                    zzcmnVar.zzP().zzp();
                }
            }
        }, zzcha.zzf);
        zze.zzl().zzi(zza, true, this.zzh ? this.zzg : null);
        zze.zzl();
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return zzfyo.zzm(zzduv.zzj(zza, zzfcxVar.zzb, zzfcxVar.zza), new zzfru() { // from class: com.google.android.gms.internal.ads.zzekr
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj2) {
                zzcmn zzcmnVar = zza;
                zzfcs zzfcsVar2 = zzfcsVar;
                zzdub zzdubVar = zze;
                if (zzfcsVar2.zzN) {
                    zzcmnVar.zzag();
                }
                zzcmnVar.zzaa();
                zzcmnVar.onPause();
                return zzdubVar.zzk();
            }
        }, this.zze);
    }
}
