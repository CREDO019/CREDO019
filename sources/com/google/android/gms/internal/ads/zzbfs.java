package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbfs extends zzgoj implements zzgpy {
    private zzbfs() {
        super(zzbft.zza());
    }

    public final zzbfs zza(String str) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbft.zzd((zzbft) this.zza, str);
        return this;
    }

    public final zzbfs zzb(zzbhl zzbhlVar) {
        if (this.zzb) {
            zzap();
            this.zzb = false;
        }
        zzbft.zze((zzbft) this.zza, zzbhlVar);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbfs(zzbes zzbesVar) {
        super(zzbft.zza());
    }
}
