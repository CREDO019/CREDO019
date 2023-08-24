package com.google.android.gms.internal.consent_sdk;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.0.0 */
/* loaded from: classes.dex */
public final class zzcf<T> implements zzch<T> {
    private zzcl<T> zza;

    public static <T> void zza(zzcl<T> zzclVar, zzcl<T> zzclVar2) {
        zzcf zzcfVar = (zzcf) zzclVar;
        if (zzcfVar.zza == null) {
            zzcfVar.zza = zzclVar2;
            return;
        }
        throw new IllegalStateException();
    }

    @Override // com.google.android.gms.internal.consent_sdk.zzcl
    public final T zzb() {
        zzcl<T> zzclVar = this.zza;
        if (zzclVar == null) {
            throw new IllegalStateException();
        }
        return zzclVar.zzb();
    }
}
