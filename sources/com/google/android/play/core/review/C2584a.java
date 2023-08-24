package com.google.android.play.core.review;

import android.app.PendingIntent;
import java.util.Objects;

/* renamed from: com.google.android.play.core.review.a */
/* loaded from: classes3.dex */
final class C2584a extends ReviewInfo {

    /* renamed from: a */
    private final PendingIntent f883a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2584a(PendingIntent pendingIntent) {
        Objects.requireNonNull(pendingIntent, "Null pendingIntent");
        this.f883a = pendingIntent;
    }

    @Override // com.google.android.play.core.review.ReviewInfo
    /* renamed from: a */
    final PendingIntent mo623a() {
        return this.f883a;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReviewInfo) {
            return this.f883a.equals(((ReviewInfo) obj).mo623a());
        }
        return false;
    }

    public final int hashCode() {
        return this.f883a.hashCode() ^ 1000003;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f883a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 26);
        sb.append("ReviewInfo{pendingIntent=");
        sb.append(valueOf);
        sb.append("}");
        return sb.toString();
    }
}
