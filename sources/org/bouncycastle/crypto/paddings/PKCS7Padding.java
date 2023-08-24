package org.bouncycastle.crypto.paddings;

import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;

/* loaded from: classes5.dex */
public class PKCS7Padding implements BlockCipherPadding {
    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int addPadding(byte[] bArr, int r4) {
        byte length = (byte) (bArr.length - r4);
        while (r4 < bArr.length) {
            bArr[r4] = length;
            r4++;
        }
        return length;
    }

    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public String getPaddingName() {
        return "PKCS7";
    }

    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public void init(SecureRandom secureRandom) throws IllegalArgumentException {
    }

    @Override // org.bouncycastle.crypto.paddings.BlockCipherPadding
    public int padCount(byte[] bArr) throws InvalidCipherTextException {
        int r0 = bArr[bArr.length - 1] & 255;
        byte b = (byte) r0;
        boolean z = (r0 > bArr.length) | (r0 == 0);
        for (int r5 = 0; r5 < bArr.length; r5++) {
            z |= (bArr.length - r5 <= r0) & (bArr[r5] != b);
        }
        if (z) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        return r0;
    }
}
