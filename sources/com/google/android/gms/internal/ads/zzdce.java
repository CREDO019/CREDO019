package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdce {
    private final zzfhp zza;
    private final zzcgt zzb;
    private final ApplicationInfo zzc;
    private final String zzd;
    private final List zze;
    private final PackageInfo zzf;
    private final zzgul zzg;
    private final String zzh;
    private final zzeuq zzi;

    public zzdce(zzfhp zzfhpVar, zzcgt zzcgtVar, ApplicationInfo applicationInfo, String str, List list, PackageInfo packageInfo, zzgul zzgulVar, com.google.android.gms.ads.internal.util.zzg zzgVar, String str2, zzeuq zzeuqVar) {
        this.zza = zzfhpVar;
        this.zzb = zzcgtVar;
        this.zzc = applicationInfo;
        this.zzd = str;
        this.zze = list;
        this.zzf = packageInfo;
        this.zzg = zzgulVar;
        this.zzh = str2;
        this.zzi = zzeuqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzcba zza(zzfyx zzfyxVar) throws Exception {
        return new zzcba((Bundle) zzfyxVar.get(), this.zzb, this.zzc, this.zzd, this.zze, this.zzf, (String) ((zzfyx) this.zzg.zzb()).get(), this.zzh, null, null);
    }

    public final zzfyx zzb() {
        zzfhp zzfhpVar = this.zza;
        return zzfgz.zzc(this.zzi.zza(new Bundle()), zzfhj.SIGNALS, zzfhpVar).zza();
    }

    public final zzfyx zzc() {
        final zzfyx zzb = zzb();
        return this.zza.zza(zzfhj.REQUEST_PARCEL, zzb, (zzfyx) this.zzg.zzb()).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzdcd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzdce.this.zza(zzb);
            }
        }).zza();
    }
}
