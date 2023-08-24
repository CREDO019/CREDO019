package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzapv implements zzfoe {
    private final zzfmh zza;
    private final zzfmy zzb;
    private final zzaqi zzc;
    private final zzapu zzd;
    private final zzapf zze;
    private final zzaqk zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzapv(zzfmh zzfmhVar, zzfmy zzfmyVar, zzaqi zzaqiVar, zzapu zzapuVar, zzapf zzapfVar, zzaqk zzaqkVar) {
        this.zza = zzfmhVar;
        this.zzb = zzfmyVar;
        this.zzc = zzaqiVar;
        this.zzd = zzapuVar;
        this.zze = zzapfVar;
        this.zzf = zzaqkVar;
    }

    private final Map zze() {
        HashMap hashMap = new HashMap();
        zzamx zzb = this.zzb.zzb();
        hashMap.put("v", this.zza.zzb());
        hashMap.put("gms", Boolean.valueOf(this.zza.zzc()));
        hashMap.put("int", zzb.zzh());
        hashMap.put("up", Boolean.valueOf(this.zzd.zza()));
        hashMap.put("t", new Throwable());
        return hashMap;
    }

    @Override // com.google.android.gms.internal.ads.zzfoe
    public final Map zza() {
        Map zze = zze();
        zze.put("lts", Long.valueOf(this.zzc.zza()));
        return zze;
    }

    @Override // com.google.android.gms.internal.ads.zzfoe
    public final Map zzb() {
        Map zze = zze();
        zzamx zza = this.zzb.zza();
        zze.put("gai", Boolean.valueOf(this.zza.zzd()));
        zze.put("did", zza.zzg());
        zze.put("dst", Integer.valueOf(zza.zzak() - 1));
        zze.put("doo", Boolean.valueOf(zza.zzah()));
        zzapf zzapfVar = this.zze;
        if (zzapfVar != null) {
            zze.put("nt", Long.valueOf(zzapfVar.zza()));
        }
        zzaqk zzaqkVar = this.zzf;
        if (zzaqkVar != null) {
            zze.put("vs", Long.valueOf(zzaqkVar.zzc()));
            zze.put("vf", Long.valueOf(this.zzf.zzb()));
        }
        return zze;
    }

    @Override // com.google.android.gms.internal.ads.zzfoe
    public final Map zzc() {
        return zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(View view) {
        this.zzc.zzd(view);
    }
}
