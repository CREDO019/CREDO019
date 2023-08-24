package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zziq implements zzjl {
    private final Object zza;
    private zzcn zzb;

    public zziq(Object obj, zzcn zzcnVar) {
        this.zza = obj;
        this.zzb = zzcnVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzc(zziq zziqVar, zzcn zzcnVar) {
        zziqVar.zzb = zzcnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final zzcn zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzjl
    public final Object zzb() {
        return this.zza;
    }
}
