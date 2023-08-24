package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgup implements zzgur {
    private zzgve zza;

    public static void zza(zzgve zzgveVar, zzgve zzgveVar2) {
        zzgup zzgupVar = (zzgup) zzgveVar;
        if (zzgupVar.zza != null) {
            throw new IllegalStateException();
        }
        zzgupVar.zza = zzgveVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final Object zzb() {
        zzgve zzgveVar = this.zza;
        if (zzgveVar == null) {
            throw new IllegalStateException();
        }
        return zzgveVar.zzb();
    }
}
