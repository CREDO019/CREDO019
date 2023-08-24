package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzehl implements zzegk {
    private final zzcxx zza;
    private final Context zzb;
    private final zzduw zzc;
    private final zzfdn zzd;
    private final Executor zze;
    private final zzfru zzf;

    public zzehl(zzcxx zzcxxVar, Context context, Executor executor, zzduw zzduwVar, zzfdn zzfdnVar, zzfru zzfruVar) {
        this.zzb = context;
        this.zza = zzcxxVar;
        this.zze = executor;
        this.zzc = zzduwVar;
        this.zzd = zzfdnVar;
        this.zzf = zzfruVar;
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        return zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzehf
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzehl.this.zzc(zzfdeVar, zzfcsVar, obj);
            }
        }, this.zze);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return (zzfcxVar == null || zzfcxVar.zza == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(zzfde zzfdeVar, zzfcs zzfcsVar, Object obj) throws Exception {
        View zzduzVar;
        com.google.android.gms.ads.internal.client.zzq zza = zzfdr.zza(this.zzb, zzfcsVar.zzv);
        final zzcmn zza2 = this.zzc.zza(zza, zzfcsVar, zzfdeVar.zzb.zzb);
        zza2.zzab(zzfcsVar.zzX);
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgF)).booleanValue() || !zzfcsVar.zzai) {
            zzduzVar = new zzduz(this.zzb, (View) zza2, (com.google.android.gms.ads.internal.util.zzas) this.zzf.apply(zzfcsVar));
        } else {
            zzduzVar = zzcyo.zza(this.zzb, (View) zza2, zzfcsVar);
        }
        final zzcxb zza3 = this.zza.zza(new zzczr(zzfdeVar, zzfcsVar, null), new zzcxh(zzduzVar, zza2, new zzcza() { // from class: com.google.android.gms.internal.ads.zzehg
            @Override // com.google.android.gms.internal.ads.zzcza
            public final com.google.android.gms.ads.internal.client.zzdk zza() {
                return zzcmn.this.zzs();
            }
        }, zzfdr.zzc(zza)));
        zza3.zzj().zzi(zza2, false, null);
        zza3.zzc().zzj(new zzdds() { // from class: com.google.android.gms.internal.ads.zzehh
            @Override // com.google.android.gms.internal.ads.zzdds
            public final void zzl() {
                zzcmn zzcmnVar = zzcmn.this;
                if (zzcmnVar.zzP() != null) {
                    zzcmnVar.zzP().zzp();
                }
            }
        }, zzcha.zzf);
        zza3.zzj();
        zzfcx zzfcxVar = zzfcsVar.zzt;
        zzfyx zzj = zzduv.zzj(zza2, zzfcxVar.zzb, zzfcxVar.zza);
        if (zzfcsVar.zzN) {
            zzj.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzehi
                @Override // java.lang.Runnable
                public final void run() {
                    zzcmn.this.zzag();
                }
            }, this.zze);
        }
        zzj.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzehj
            @Override // java.lang.Runnable
            public final void run() {
                zzehl.this.zzd(zza2);
            }
        }, this.zze);
        return zzfyo.zzm(zzj, new zzfru() { // from class: com.google.android.gms.internal.ads.zzehk
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj2) {
                return zzcxb.this.zza();
            }
        }, zzcha.zzf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(zzcmn zzcmnVar) {
        zzcmnVar.zzaa();
        zzcnj zzs = zzcmnVar.zzs();
        com.google.android.gms.ads.internal.client.zzff zzffVar = this.zzd.zza;
        if (zzffVar == null || zzs == null) {
            return;
        }
        zzs.zzs(zzffVar);
    }
}
