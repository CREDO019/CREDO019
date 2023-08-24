package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Objects;

/* renamed from: com.google.android.play.core.assetpacks.dc */
/* loaded from: classes3.dex */
public final class C2437dc extends InputStream {

    /* renamed from: a */
    private final Enumeration<File> f669a;

    /* renamed from: b */
    private InputStream f670b;

    public C2437dc(Enumeration<File> enumeration) throws IOException {
        this.f669a = enumeration;
        m898a();
    }

    /* renamed from: a */
    final void m898a() throws IOException {
        InputStream inputStream = this.f670b;
        if (inputStream != null) {
            inputStream.close();
        }
        this.f670b = this.f669a.hasMoreElements() ? new FileInputStream(this.f669a.nextElement()) : null;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        super.close();
        InputStream inputStream = this.f670b;
        if (inputStream != null) {
            inputStream.close();
            this.f670b = null;
        }
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        while (true) {
            InputStream inputStream = this.f670b;
            if (inputStream == null) {
                return -1;
            }
            int read = inputStream.read();
            if (read != -1) {
                return read;
            }
            m898a();
        }
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int r4, int r5) throws IOException {
        if (this.f670b == null) {
            return -1;
        }
        Objects.requireNonNull(bArr);
        if (r4 < 0 || r5 < 0 || r5 > bArr.length - r4) {
            throw new IndexOutOfBoundsException();
        }
        if (r5 != 0) {
            do {
                int read = this.f670b.read(bArr, r4, r5);
                if (read > 0) {
                    return read;
                }
                m898a();
            } while (this.f670b != null);
            return -1;
        }
        return 0;
    }
}
