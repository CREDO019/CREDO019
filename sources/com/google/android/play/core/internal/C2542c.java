package com.google.android.play.core.internal;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/* renamed from: com.google.android.play.core.internal.c */
/* loaded from: classes3.dex */
final class C2542c implements InterfaceC2515b {

    /* renamed from: a */
    private final FileChannel f843a;

    /* renamed from: b */
    private final long f844b;

    /* renamed from: c */
    private final long f845c;

    public C2542c(FileChannel fileChannel, long j, long j2) {
        this.f843a = fileChannel;
        this.f844b = j;
        this.f845c = j2;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2515b
    /* renamed from: a */
    public final long mo722a() {
        return this.f845c;
    }

    @Override // com.google.android.play.core.internal.InterfaceC2515b
    /* renamed from: a */
    public final void mo721a(MessageDigest[] messageDigestArr, long j, int r12) throws IOException {
        MappedByteBuffer map = this.f843a.map(FileChannel.MapMode.READ_ONLY, this.f844b + j, r12);
        map.load();
        for (MessageDigest messageDigest : messageDigestArr) {
            map.position(0);
            messageDigest.update(map);
        }
    }
}
