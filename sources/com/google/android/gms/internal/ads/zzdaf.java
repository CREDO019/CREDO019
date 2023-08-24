package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdaf {
    private final zzeca zza;
    private final zzfdn zzb;
    private final zzfhp zzc;
    private final zzcts zzd;
    private final zzekc zze;
    private final zzdic zzf;
    private zzfde zzg;
    private final zzede zzh;
    private final zzdce zzi;
    private final Executor zzj;
    private final zzecr zzk;
    private final zzegp zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdaf(zzeca zzecaVar, zzfdn zzfdnVar, zzfhp zzfhpVar, zzcts zzctsVar, zzekc zzekcVar, zzdic zzdicVar, zzfde zzfdeVar, zzede zzedeVar, zzdce zzdceVar, Executor executor, zzecr zzecrVar, zzegp zzegpVar) {
        this.zza = zzecaVar;
        this.zzb = zzfdnVar;
        this.zzc = zzfhpVar;
        this.zzd = zzctsVar;
        this.zze = zzekcVar;
        this.zzf = zzdicVar;
        this.zzg = zzfdeVar;
        this.zzh = zzedeVar;
        this.zzi = zzdceVar;
        this.zzj = executor;
        this.zzk = zzecrVar;
        this.zzl = zzegpVar;
    }

    public final com.google.android.gms.ads.internal.client.zze zza(Throwable th) {
        return zzfem.zzb(th, this.zzl);
    }

    public final zzdic zzc() {
        return this.zzf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfde zzd(zzfde zzfdeVar) throws Exception {
        this.zzd.zza(zzfdeVar);
        return zzfdeVar;
    }

    public final zzfyx zze(final zzfff zzfffVar) {
        zzfgu zza = this.zzc.zzb(zzfhj.GET_CACHE_KEY, this.zzi.zzc()).zzf(new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdac
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzdaf.this.zzf(zzfffVar, (zzcba) obj);
            }
        }).zza();
        zzfyo.zzr(zza, new zzdad(this), this.zzj);
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzf(zzfff zzfffVar, zzcba zzcbaVar) throws Exception {
        zzcbaVar.zzi = zzfffVar;
        return this.zzh.zza(zzcbaVar);
    }

    public final zzfyx zzg(zzcba zzcbaVar) {
        zzfgu zza = this.zzc.zzb(zzfhj.NOTIFY_CACHE_HIT, this.zzh.zzf(zzcbaVar)).zza();
        zzfyo.zzr(zza, new zzdae(this), this.zzj);
        return zza;
    }

    public final zzfyx zzh(zzfyx zzfyxVar) {
        zzfhg zzf = this.zzc.zzb(zzfhj.RENDERER, zzfyxVar).zze(new zzfgs() { // from class: com.google.android.gms.internal.ads.zzdab
            @Override // com.google.android.gms.internal.ads.zzfgs
            public final Object zza(Object obj) {
                zzfde zzfdeVar = (zzfde) obj;
                zzdaf.this.zzd(zzfdeVar);
                return zzfdeVar;
            }
        }).zzf(this.zze);
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeA)).booleanValue()) {
            zzf = zzf.zzi(((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeB)).intValue(), TimeUnit.SECONDS);
        }
        return zzf.zza();
    }

    public final zzfyx zzi() {
        com.google.android.gms.ads.internal.client.zzl zzlVar = this.zzb.zzd;
        if (zzlVar.zzx != null || zzlVar.zzs != null) {
            zzfhp zzfhpVar = this.zzc;
            return zzfgz.zzc(this.zza.zza(), zzfhj.PRELOADED_LOADER, zzfhpVar).zza();
        }
        return zzj(this.zzi.zzc());
    }

    public final zzfyx zzj(zzfyx zzfyxVar) {
        if (this.zzg != null) {
            zzfhp zzfhpVar = this.zzc;
            return zzfgz.zzc(zzfyo.zzi(this.zzg), zzfhj.SERVER_TRANSACTION, zzfhpVar).zza();
        }
        com.google.android.gms.ads.internal.zzt.zzc().zzj();
        zzfhg zzb = this.zzc.zzb(zzfhj.SERVER_TRANSACTION, zzfyxVar);
        final zzecr zzecrVar = this.zzk;
        return zzb.zzf(new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdaa
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzecr.this.zzb((zzcba) obj);
            }
        }).zza();
    }

    public final void zzk(zzfde zzfdeVar) {
        this.zzg = zzfdeVar;
    }
}
