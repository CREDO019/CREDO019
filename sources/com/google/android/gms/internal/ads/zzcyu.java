package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcyu {
    private final zzdfn zza;
    private final zzdht zzb;

    public zzcyu(zzdfn zzdfnVar, zzdht zzdhtVar) {
        this.zza = zzdfnVar;
        this.zzb = zzdhtVar;
    }

    public final zzdfn zza() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzdht zzb() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzdke zzc() {
        zzdht zzdhtVar = this.zzb;
        return zzdhtVar != null ? new zzdke(zzdhtVar, zzcha.zzf) : new zzdke(new zzcyt(this), zzcha.zzf);
    }
}
