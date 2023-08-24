package com.google.android.play.core.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* renamed from: com.google.android.play.core.internal.cd */
/* loaded from: classes3.dex */
final class C2546cd extends WeakReference<Throwable> {

    /* renamed from: a */
    private final int f849a;

    public C2546cd(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
        super(th, referenceQueue);
        this.f849a = System.identityHashCode(th);
    }

    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == getClass()) {
            if (this == obj) {
                return true;
            }
            C2546cd c2546cd = (C2546cd) obj;
            if (this.f849a == c2546cd.f849a && get() == c2546cd.get()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f849a;
    }
}
