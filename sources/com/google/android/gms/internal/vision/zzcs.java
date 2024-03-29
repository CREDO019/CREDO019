package com.google.android.gms.internal.vision;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzcs<T> extends zzcn<T> {
    private final T zzlh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcs(T t) {
        this.zzlh = t;
    }

    @Override // com.google.android.gms.internal.vision.zzcn
    public final boolean isPresent() {
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzcn
    public final T get() {
        return this.zzlh;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzcs) {
            return this.zzlh.equals(((zzcs) obj).zzlh);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzlh.hashCode() + 1502476572;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzlh);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(valueOf);
        sb.append(")");
        return sb.toString();
    }
}
