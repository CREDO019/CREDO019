package com.google.android.play.core.internal;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: com.google.android.play.core.internal.a */
/* loaded from: classes3.dex */
final class C2488a implements InterfaceC2515b {

    /* renamed from: a */
    private final ByteBuffer f803a;

    public C2488a(ByteBuffer byteBuffer) {
        this.f803a = byteBuffer.slice();
    }

    @Override // com.google.android.play.core.internal.InterfaceC2515b
    /* renamed from: a */
    public final long mo722a() {
        return this.f803a.capacity();
    }

    @Override // com.google.android.play.core.internal.InterfaceC2515b
    /* renamed from: a */
    public final void mo721a(MessageDigest[] messageDigestArr, long j, int r6) throws IOException {
        ByteBuffer slice;
        synchronized (this.f803a) {
            int r5 = (int) j;
            this.f803a.position(r5);
            this.f803a.limit(r5 + r6);
            slice = this.f803a.slice();
        }
        for (MessageDigest messageDigest : messageDigestArr) {
            slice.position(0);
            messageDigest.update(slice);
        }
    }
}
