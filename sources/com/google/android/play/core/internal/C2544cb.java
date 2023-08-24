package com.google.android.play.core.internal;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.android.play.core.internal.cb */
/* loaded from: classes3.dex */
public final class C2544cb extends AbstractC2543ca {

    /* renamed from: a */
    private final AbstractC2543ca f846a;

    /* renamed from: b */
    private final long f847b;

    /* renamed from: c */
    private final long f848c;

    public C2544cb(AbstractC2543ca abstractC2543ca, long j, long j2) {
        this.f846a = abstractC2543ca;
        long m718a = m718a(j);
        this.f847b = m718a;
        this.f848c = m718a(m718a + j2);
    }

    /* renamed from: a */
    private final long m718a(long j) {
        if (j < 0) {
            return 0L;
        }
        return j > this.f846a.mo719a() ? this.f846a.mo719a() : j;
    }

    @Override // com.google.android.play.core.internal.AbstractC2543ca
    /* renamed from: a */
    public final long mo719a() {
        return this.f848c - this.f847b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.AbstractC2543ca
    /* renamed from: a */
    public final InputStream mo717a(long j, long j2) throws IOException {
        long m718a = m718a(this.f847b);
        return this.f846a.mo717a(m718a, m718a(j2 + m718a) - m718a);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }
}
