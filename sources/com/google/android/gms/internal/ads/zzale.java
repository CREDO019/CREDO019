package com.google.android.gms.internal.ads;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzale extends ByteArrayOutputStream {
    private final zzakr zza;

    public zzale(zzakr zzakrVar, int r3) {
        this.zza = zzakrVar;
        this.buf = zzakrVar.zzb(Math.max(r3, 256));
    }

    private final void zza(int r4) {
        if (this.count + r4 <= this.buf.length) {
            return;
        }
        zzakr zzakrVar = this.zza;
        int r1 = this.count + r4;
        byte[] zzb = zzakrVar.zzb(r1 + r1);
        System.arraycopy(this.buf, 0, zzb, 0, this.count);
        this.zza.zza(this.buf);
        this.buf = zzb;
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.zza.zza(this.buf);
        this.buf = null;
        super.close();
    }

    public final void finalize() {
        this.zza.zza(this.buf);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(int r2) {
        zza(1);
        super.write(r2);
    }

    @Override // java.io.ByteArrayOutputStream, java.io.OutputStream
    public final synchronized void write(byte[] bArr, int r2, int r3) {
        zza(r3);
        super.write(bArr, r2, r3);
    }
}
