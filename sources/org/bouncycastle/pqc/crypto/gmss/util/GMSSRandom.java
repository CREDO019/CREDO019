package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;

/* loaded from: classes3.dex */
public class GMSSRandom {
    private Digest messDigestTree;

    public GMSSRandom(Digest digest) {
        this.messDigestTree = digest;
    }

    private void addByteArrays(byte[] bArr, byte[] bArr2) {
        byte b = 0;
        for (int r0 = 0; r0 < bArr.length; r0++) {
            int r2 = (bArr[r0] & 255) + (bArr2[r0] & 255) + b;
            bArr[r0] = (byte) r2;
            b = (byte) (r2 >> 8);
        }
    }

    private void addOne(byte[] bArr) {
        byte b = 1;
        for (int r1 = 0; r1 < bArr.length; r1++) {
            int r2 = (bArr[r1] & 255) + b;
            bArr[r1] = (byte) r2;
            b = (byte) (r2 >> 8);
        }
    }

    public byte[] nextSeed(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        this.messDigestTree.update(bArr, 0, bArr.length);
        byte[] bArr3 = new byte[this.messDigestTree.getDigestSize()];
        this.messDigestTree.doFinal(bArr3, 0);
        addByteArrays(bArr, bArr3);
        addOne(bArr);
        return bArr3;
    }
}
