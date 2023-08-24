package com.google.android.gms.internal.ads;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzawd {
    public final zzawm zza = new zzawm();
    public final zzave zzb;
    public zzawk zzc;
    public zzawa zzd;
    public int zze;
    public int zzf;
    public int zzg;
    public zzavd zzh;
    public zzawl zzi;

    public zzawd(zzave zzaveVar) {
        this.zzb = zzaveVar;
    }

    public final void zzb() {
        zzawm zzawmVar = this.zza;
        zzawmVar.zzd = 0;
        zzawmVar.zzr = 0L;
        zzawmVar.zzl = false;
        zzawmVar.zzq = false;
        zzawmVar.zzn = null;
        this.zze = 0;
        this.zzg = 0;
        this.zzf = 0;
        this.zzh = null;
        this.zzi = null;
    }

    public final void zza(zzawk zzawkVar, zzawa zzawaVar) {
        Objects.requireNonNull(zzawkVar);
        this.zzc = zzawkVar;
        Objects.requireNonNull(zzawaVar);
        this.zzd = zzawaVar;
        this.zzb.zza(zzawkVar.zzf);
        zzb();
    }
}
