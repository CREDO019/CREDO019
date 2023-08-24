package com.google.android.gms.internal.vision;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdx extends WeakReference<Throwable> {
    private final int zzmi;

    public zzdx(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        Objects.requireNonNull(th, "The referent cannot be null");
        this.zzmi = System.identityHashCode(th);
    }

    public final int hashCode() {
        return this.zzmi;
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            zzdx zzdxVar = (zzdx) obj;
            if (this.zzmi == zzdxVar.zzmi && get() == zzdxVar.get()) {
                return true;
            }
        }
        return false;
    }
}
