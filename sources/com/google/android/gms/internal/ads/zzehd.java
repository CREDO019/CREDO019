package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzehd implements zzegk {
    private final zzcwd zza;
    private final Context zzb;
    private final zzduw zzc;
    private final Executor zzd;

    public zzehd(zzcwd zzcwdVar, Context context, Executor executor, zzduw zzduwVar) {
        this.zzb = context;
        this.zza = zzcwdVar;
        this.zzd = executor;
        this.zzc = zzduwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        return zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeha
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzehd.this.zzc(zzfdeVar, zzfcsVar, obj);
            }
        }, this.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return (zzfcxVar == null || zzfcxVar.zza == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(zzfde zzfdeVar, zzfcs zzfcsVar, Object obj) throws Exception {
        com.google.android.gms.ads.internal.client.zzq zza = zzfdr.zza(this.zzb, zzfcsVar.zzv);
        final zzcmn zza2 = this.zzc.zza(zza, zzfcsVar, zzfdeVar.zzb.zzb);
        final zzcvv zza3 = this.zza.zza(new zzczr(zzfdeVar, zzfcsVar, null), new zzcvw((View) zza2, zza2, zzfdr.zzc(zza), zzfcsVar.zzab, zzfcsVar.zzaf, zzfcsVar.zzP));
        zza3.zzg().zzi(zza2, false, null);
        zza3.zzc().zzj(new zzdds() { // from class: com.google.android.gms.internal.ads.zzehb
            @Override // com.google.android.gms.internal.ads.zzdds
            public final void zzl() {
                zzcmn zzcmnVar = zzcmn.this;
                if (zzcmnVar.zzP() != null) {
                    zzcmnVar.zzP().zzp();
                }
            }
        }, zzcha.zzf);
        zza3.zzg();
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return zzfyo.zzm(zzduv.zzj(zza2, zzfcxVar.zzb, zzfcxVar.zza), new zzfru() { // from class: com.google.android.gms.internal.ads.zzehc
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj2) {
                return zzcvv.this.zza();
            }
        }, zzcha.zzf);
    }
}
