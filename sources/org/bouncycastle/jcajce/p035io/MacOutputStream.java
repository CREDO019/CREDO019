package org.bouncycastle.jcajce.p035io;

import java.io.IOException;
import java.io.OutputStream;
import javax.crypto.Mac;

/* renamed from: org.bouncycastle.jcajce.io.MacOutputStream */
/* loaded from: classes5.dex */
public final class MacOutputStream extends OutputStream {
    private Mac mac;

    public MacOutputStream(Mac mac) {
        this.mac = mac;
    }

    public byte[] getMac() {
        return this.mac.doFinal();
    }

    @Override // java.io.OutputStream
    public void write(int r2) throws IOException {
        this.mac.update((byte) r2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int r3, int r4) throws IOException {
        this.mac.update(bArr, r3, r4);
    }
}
