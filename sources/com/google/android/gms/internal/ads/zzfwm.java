package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfwm extends zzfwd implements Serializable {
    final zzfwd zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfwm(zzfwd zzfwdVar) {
        this.zza = zzfwdVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfwd, java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.zza.compare(obj2, obj);
    }

    @Override // java.util.Comparator
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfwm) {
            return this.zza.equals(((zzfwm) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return -this.zza.hashCode();
    }

    public final String toString() {
        zzfwd zzfwdVar = this.zza;
        Objects.toString(zzfwdVar);
        return zzfwdVar.toString().concat(".reverse()");
    }

    @Override // com.google.android.gms.internal.ads.zzfwd
    public final zzfwd zza() {
        return this.zza;
    }
}
