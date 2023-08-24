package com.google.android.gms.internal.vision;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzck<T> extends zzcn<T> {
    static final zzck<Object> zzlf = new zzck<>();

    private zzck() {
    }

    public final boolean equals(@NullableDecl Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    @Override // com.google.android.gms.internal.vision.zzcn
    public final boolean isPresent() {
        return false;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.android.gms.internal.vision.zzcn
    public final T get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }
}
