package org.bouncycastle.jcajce.p035io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.bouncycastle.crypto.p034io.InvalidCipherTextIOException;

/* renamed from: org.bouncycastle.jcajce.io.CipherInputStream */
/* loaded from: classes5.dex */
public class CipherInputStream extends FilterInputStream {
    private byte[] buf;
    private int bufOff;
    private final Cipher cipher;
    private boolean finalized;
    private final byte[] inputBuffer;
    private int maxBuf;

    public CipherInputStream(InputStream inputStream, Cipher cipher) {
        super(inputStream);
        this.inputBuffer = new byte[512];
        this.finalized = false;
        this.cipher = cipher;
    }

    private byte[] finaliseCipher() throws InvalidCipherTextIOException {
        try {
            if (this.finalized) {
                return null;
            }
            this.finalized = true;
            return this.cipher.doFinal();
        } catch (GeneralSecurityException e) {
            throw new InvalidCipherTextIOException("Error finalising cipher", e);
        }
    }

    private int nextChunk() throws IOException {
        if (this.finalized) {
            return -1;
        }
        this.bufOff = 0;
        this.maxBuf = 0;
        while (true) {
            int r2 = this.maxBuf;
            if (r2 != 0) {
                return r2;
            }
            int read = this.in.read(this.inputBuffer);
            if (read == -1) {
                byte[] finaliseCipher = finaliseCipher();
                this.buf = finaliseCipher;
                if (finaliseCipher == null || finaliseCipher.length == 0) {
                    return -1;
                }
                int length = finaliseCipher.length;
                this.maxBuf = length;
                return length;
            }
            byte[] update = this.cipher.update(this.inputBuffer, 0, read);
            this.buf = update;
            if (update != null) {
                this.maxBuf = update.length;
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.maxBuf - this.bufOff;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.in.close();
            this.bufOff = 0;
            this.maxBuf = 0;
        } finally {
            if (!this.finalized) {
                finaliseCipher();
            }
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int r1) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.bufOff < this.maxBuf || nextChunk() >= 0) {
            byte[] bArr = this.buf;
            int r1 = this.bufOff;
            this.bufOff = r1 + 1;
            return bArr[r1] & 255;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int r4, int r5) throws IOException {
        if (this.bufOff < this.maxBuf || nextChunk() >= 0) {
            int min = Math.min(r5, available());
            System.arraycopy(this.buf, this.bufOff, bArr, r4, min);
            this.bufOff += min;
            return min;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        int min = (int) Math.min(j, available());
        this.bufOff += min;
        return min;
    }
}
