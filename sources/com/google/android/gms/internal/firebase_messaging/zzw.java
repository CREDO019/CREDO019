package com.google.android.gms.internal.firebase_messaging;

import java.io.OutputStream;

/* compiled from: com.google.firebase:firebase-messaging@@22.0.0 */
/* loaded from: classes.dex */
final class zzw extends OutputStream {
    private long zza = 0;

    @Override // java.io.OutputStream
    public final void write(int r5) {
        this.zza++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.zza += bArr.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zza() {
        return this.zza;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int r4, int r5) {
        int length;
        int r42;
        if (r4 < 0 || r4 > (length = bArr.length) || r5 < 0 || (r42 = r4 + r5) > length || r42 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.zza += r5;
    }
}
