package com.google.android.gms.internal.ads;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdii {
    private final Set zza = new HashSet();
    private final Set zzb = new HashSet();
    private final Set zzc = new HashSet();
    private final Set zzd = new HashSet();
    private final Set zze = new HashSet();
    private final Set zzf = new HashSet();
    private final Set zzg = new HashSet();
    private final Set zzh = new HashSet();
    private final Set zzi = new HashSet();
    private final Set zzj = new HashSet();
    private final Set zzk = new HashSet();
    private final Set zzl = new HashSet();
    private final Set zzm = new HashSet();
    private final Set zzn = new HashSet();
    private zzfae zzo;

    public final zzdii zza(com.google.android.gms.ads.internal.client.zza zzaVar, Executor executor) {
        this.zzc.add(new zzdke(zzaVar, executor));
        return this;
    }

    public final zzdii zzb(zzddb zzddbVar, Executor executor) {
        this.zzi.add(new zzdke(zzddbVar, executor));
        return this;
    }

    public final zzdii zzc(zzddo zzddoVar, Executor executor) {
        this.zzl.add(new zzdke(zzddoVar, executor));
        return this;
    }

    public final zzdii zzd(zzdds zzddsVar, Executor executor) {
        this.zzf.add(new zzdke(zzddsVar, executor));
        return this;
    }

    public final zzdii zze(zzdcy zzdcyVar, Executor executor) {
        this.zze.add(new zzdke(zzdcyVar, executor));
        return this;
    }

    public final zzdii zzf(zzdem zzdemVar, Executor executor) {
        this.zzh.add(new zzdke(zzdemVar, executor));
        return this;
    }

    public final zzdii zzg(zzdex zzdexVar, Executor executor) {
        this.zzg.add(new zzdke(zzdexVar, executor));
        return this;
    }

    public final zzdii zzh(com.google.android.gms.ads.internal.overlay.zzo zzoVar, Executor executor) {
        this.zzn.add(new zzdke(zzoVar, executor));
        return this;
    }

    public final zzdii zzi(zzdfj zzdfjVar, Executor executor) {
        this.zzm.add(new zzdke(zzdfjVar, executor));
        return this;
    }

    public final zzdii zzj(zzdft zzdftVar, Executor executor) {
        this.zzb.add(new zzdke(zzdftVar, executor));
        return this;
    }

    public final zzdii zzk(zzasb zzasbVar, Executor executor) {
        this.zzk.add(new zzdke(zzasbVar, executor));
        return this;
    }

    public final zzdii zzl(zzdkl zzdklVar, Executor executor) {
        this.zzd.add(new zzdke(zzdklVar, executor));
        return this;
    }

    public final zzdii zzm(zzfae zzfaeVar) {
        this.zzo = zzfaeVar;
        return this;
    }

    public final zzdik zzn() {
        return new zzdik(this, null);
    }
}
