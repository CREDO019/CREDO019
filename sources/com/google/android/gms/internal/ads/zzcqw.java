package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcqw {
    private zzcon zza;
    private zzcsj zzb;
    private zzfht zzc;
    private zzcsw zzd;
    private zzfen zze;

    private zzcqw() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcqw(zzcqv zzcqvVar) {
    }

    public final zzcok zza() {
        zzguz.zzc(this.zza, zzcon.class);
        zzguz.zzc(this.zzb, zzcsj.class);
        if (this.zzc == null) {
            this.zzc = new zzfht();
        }
        if (this.zzd == null) {
            this.zzd = new zzcsw();
        }
        if (this.zze == null) {
            this.zze = new zzfen();
        }
        return new zzcpu(this.zza, this.zzb, this.zzc, this.zzd, this.zze, null);
    }

    public final zzcqw zzb(zzcon zzconVar) {
        this.zza = zzconVar;
        return this;
    }

    public final zzcqw zzc(zzcsj zzcsjVar) {
        this.zzb = zzcsjVar;
        return this;
    }
}
