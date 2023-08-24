package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfuh extends zzfwd implements Serializable {
    final Comparator zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfuh(Comparator comparator) {
        Objects.requireNonNull(comparator);
        this.zza = comparator;
    }

    @Override // com.google.android.gms.internal.ads.zzfwd, java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return this.zza.compare(obj, obj2);
    }

    @Override // java.util.Comparator
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzfuh) {
            return this.zza.equals(((zzfuh) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final String toString() {
        return this.zza.toString();
    }
}
