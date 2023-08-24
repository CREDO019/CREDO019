package com.google.android.gms.internal.firebase_messaging;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* compiled from: com.google.firebase:firebase-messaging@@22.0.0 */
/* loaded from: classes.dex */
final class zzk extends FilterInputStream {
    private long zza;
    private long zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(InputStream inputStream, long j) {
        super(inputStream);
        this.zzb = -1L;
        Objects.requireNonNull(inputStream);
        this.zza = 1048577L;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() throws IOException {
        return (int) Math.min(this.in.available(), this.zza);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void mark(int r3) {
        this.in.mark(r3);
        this.zzb = this.zza;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        if (this.zza == 0) {
            return -1;
        }
        int read = this.in.read();
        if (read != -1) {
            this.zza--;
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final synchronized void reset() throws IOException {
        if (this.in.markSupported()) {
            if (this.zzb == -1) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.zza = this.zzb;
        } else {
            throw new IOException("Mark not supported");
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j) throws IOException {
        long skip = this.in.skip(Math.min(j, this.zza));
        this.zza -= skip;
        return skip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int r8, int r9) throws IOException {
        long j = this.zza;
        if (j == 0) {
            return -1;
        }
        int read = this.in.read(bArr, r8, (int) Math.min(r9, j));
        if (read != -1) {
            this.zza -= read;
        }
        return read;
    }
}
