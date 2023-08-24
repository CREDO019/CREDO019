package com.google.android.gms.internal.ads;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfsk extends zzfsb {
    private final Object zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfsk(Object obj) {
        this.zza = obj;
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzfsk) {
            return this.zza.equals(((zzfsk) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        return "Optional.of(" + this.zza + ")";
    }

    @Override // com.google.android.gms.internal.ads.zzfsb
    public final zzfsb zza(zzfru zzfruVar) {
        Object apply = zzfruVar.apply(this.zza);
        zzfsf.zzc(apply, "the Function passed to Optional.transform() must not return null.");
        return new zzfsk(apply);
    }

    @Override // com.google.android.gms.internal.ads.zzfsb
    public final Object zzb(Object obj) {
        return this.zza;
    }
}
