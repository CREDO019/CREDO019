package com.google.android.play.core.assetpacks;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.google.android.play.core.assetpacks.bd */
/* loaded from: classes3.dex */
final class C2384bd extends InputStream {

    /* renamed from: a */
    private final InputStream f478a;

    /* renamed from: b */
    private long f479b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2384bd(InputStream inputStream, long j) {
        this.f478a = inputStream;
        this.f479b = j;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        this.f478a.close();
        this.f479b = 0L;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        long j = this.f479b;
        if (j <= 0) {
            return -1;
        }
        this.f479b = j - 1;
        return this.f478a.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int r8, int r9) throws IOException {
        long j = this.f479b;
        if (j <= 0) {
            return -1;
        }
        int read = this.f478a.read(bArr, r8, (int) Math.min(r9, j));
        if (read != -1) {
            this.f479b -= read;
        }
        return read;
    }
}
