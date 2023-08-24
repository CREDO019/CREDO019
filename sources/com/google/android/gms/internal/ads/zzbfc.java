package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbfc extends zzgoj implements zzgpy {
    private zzbfc() {
        super(zzbfd.zzc());
    }

    public final zzbfc zza(boolean z) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbfd.zze((zzbfd) this.zza, z);
        return this;
    }

    public final zzbfc zzb(int r2) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbfd.zzf((zzbfd) this.zza, r2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbfc(zzbes zzbesVar) {
        super(zzbfd.zzc());
    }
}
