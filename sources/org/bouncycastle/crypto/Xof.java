package org.bouncycastle.crypto;

/* loaded from: classes5.dex */
public interface Xof extends ExtendedDigest {
    int doFinal(byte[] bArr, int r2, int r3);

    int doOutput(byte[] bArr, int r2, int r3);
}
