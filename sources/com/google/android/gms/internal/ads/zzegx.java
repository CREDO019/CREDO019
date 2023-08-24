package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzegx implements zzegk {
    private final zzcwo zza;
    private final Context zzb;
    private final zzduw zzc;
    private final zzfdn zzd;
    private final Executor zze;
    private final zzcgt zzf;
    private final zzbpt zzg;
    private final boolean zzh = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhy)).booleanValue();

    public zzegx(zzcwo zzcwoVar, Context context, Executor executor, zzduw zzduwVar, zzfdn zzfdnVar, zzcgt zzcgtVar, zzbpt zzbptVar) {
        this.zzb = context;
        this.zza = zzcwoVar;
        this.zze = executor;
        this.zzc = zzduwVar;
        this.zzd = zzfdnVar;
        this.zzf = zzcgtVar;
        this.zzg = zzbptVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        final zzdva zzdvaVar = new zzdva();
        zzfyx zzn = zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzegv
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzegx.this.zzc(zzfcsVar, zzfdeVar, zzdvaVar, obj);
            }
        }, this.zze);
        zzn.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzegw
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
        final zzcmn zza = this.zzc.zza(this.zzd.zze, zzfcsVar, zzfdeVar.zzb.zzb);
        zza.zzab(zzfcsVar.zzX);
        zzdvaVar.zza(this.zzb, (View) zza);
        zzchf zzchfVar = new zzchf();
        final zzcwl zza2 = this.zza.zza(new zzczr(zzfdeVar, zzfcsVar, null), new zzdli(new zzegz(this.zzf, zzchfVar, zzfcsVar, zza, this.zzd, this.zzh, this.zzg), zza), new zzcwm(zzfcsVar.zzab));
        zza2.zzj().zzi(zza, false, this.zzh ? this.zzg : null);
        zzchfVar.zzd(zza2);
        zza2.zzc().zzj(new zzdds() { // from class: com.google.android.gms.internal.ads.zzegt
            @Override // com.google.android.gms.internal.ads.zzdds
            public final void zzl() {
                zzcmn zzcmnVar = zzcmn.this;
                if (zzcmnVar.zzP() != null) {
                    zzcmnVar.zzP().zzp();
                }
            }
        }, zzcha.zzf);
        zza2.zzj();
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return zzfyo.zzm(zzduv.zzj(zza, zzfcxVar.zzb, zzfcxVar.zza), new zzfru() { // from class: com.google.android.gms.internal.ads.zzegu
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj2) {
                zzcmn zzcmnVar = zza;
                zzfcs zzfcsVar2 = zzfcsVar;
                zzcwl zzcwlVar = zza2;
                if (zzfcsVar2.zzN) {
                    zzcmnVar.zzag();
                }
                zzcmnVar.zzaa();
                zzcmnVar.onPause();
                return zzcwlVar.zza();
            }
        }, this.zze);
    }
}
