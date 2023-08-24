package com.google.android.play.core.internal;

import java.util.Objects;

/* renamed from: com.google.android.play.core.internal.cf */
/* loaded from: classes3.dex */
final class C2548cf extends AbstractC2545cc {

    /* renamed from: a */
    private final C2547ce f852a = new C2547ce();

    @Override // com.google.android.play.core.internal.AbstractC2545cc
    /* renamed from: a */
    public final void mo715a(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
        this.f852a.m716a(th).add(th2);
    }
}
