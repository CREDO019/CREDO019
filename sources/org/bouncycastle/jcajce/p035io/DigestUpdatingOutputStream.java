package org.bouncycastle.jcajce.p035io;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;

/* renamed from: org.bouncycastle.jcajce.io.DigestUpdatingOutputStream */
/* loaded from: classes5.dex */
class DigestUpdatingOutputStream extends OutputStream {
    private MessageDigest digest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DigestUpdatingOutputStream(MessageDigest messageDigest) {
        this.digest = messageDigest;
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        this.digest.update((byte) r2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.digest.update(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        this.digest.update(bArr, r3, r4);
    }
}
