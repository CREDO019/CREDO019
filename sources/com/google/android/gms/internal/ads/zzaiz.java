package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaiz implements zzaja {
    private final ByteBuffer zza;

    public zzaiz(ByteBuffer byteBuffer) {
        this.zza = byteBuffer.slice();
    }

    @Override // com.google.android.gms.internal.ads.zzaja
    public final long zza() {
        return this.zza.capacity();
    }

    @Override // com.google.android.gms.internal.ads.zzaja
    public final void zzb(MessageDigest[] messageDigestArr, long j, int r6) throws IOException {
        ByteBuffer slice;
        synchronized (this.zza) {
            int r5 = (int) j;
            this.zza.position(r5);
            this.zza.limit(r5 + r6);
            slice = this.zza.slice();
        }
        for (MessageDigest messageDigest : messageDigestArr) {
            slice.position(0);
            messageDigest.update(slice);
        }
    }
}
