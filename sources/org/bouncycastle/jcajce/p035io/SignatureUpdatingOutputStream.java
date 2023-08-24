package org.bouncycastle.jcajce.p035io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.Signature;
import java.security.SignatureException;

/* renamed from: org.bouncycastle.jcajce.io.SignatureUpdatingOutputStream */
/* loaded from: classes5.dex */
class SignatureUpdatingOutputStream extends OutputStream {
    private Signature sig;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SignatureUpdatingOutputStream(Signature signature) {
        this.sig = signature;
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        try {
            this.sig.update((byte) r2);
        } catch (SignatureException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        try {
            this.sig.update(bArr);
        } catch (SignatureException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        try {
            this.sig.update(bArr, r3, r4);
        } catch (SignatureException e) {
            throw new IOException(e.getMessage());
        }
    }
}
