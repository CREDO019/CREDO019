package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgti extends zzgoj implements zzgpy {
    private zzgti() {
        super(zzgtj.zzc());
    }

    public final zzgti zza(String str) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzgtj.zzd((zzgtj) this.zza, str);
        return this;
    }

    public final zzgti zzb(long j) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzgtj.zze((zzgtj) this.zza, j);
        return this;
    }

    public final zzgti zzc(boolean z) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzgtj.zzf((zzgtj) this.zza, z);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgti(zzgrz zzgrzVar) {
        super(zzgtj.zzc());
    }
}
