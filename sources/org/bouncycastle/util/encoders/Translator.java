package org.bouncycastle.util.encoders;

/* loaded from: classes4.dex */
public interface Translator {
    int decode(byte[] bArr, int r2, int r3, byte[] bArr2, int r5);

    int encode(byte[] bArr, int r2, int r3, byte[] bArr2, int r5);

    int getDecodedBlockSize();

    int getEncodedBlockSize();
}
