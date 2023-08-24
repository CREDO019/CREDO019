package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgny {
    private final Object zza;
    private final int zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgny(Object obj, int r2) {
        this.zza = obj;
        this.zzb = r2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgny) {
            zzgny zzgnyVar = (zzgny) obj;
            return this.zza == zzgnyVar.zza && this.zzb == zzgnyVar.zzb;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
