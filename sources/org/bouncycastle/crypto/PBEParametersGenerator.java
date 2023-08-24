package org.bouncycastle.crypto;

import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public abstract class PBEParametersGenerator {
    protected int iterationCount;
    protected byte[] password;
    protected byte[] salt;

    public static byte[] PKCS12PasswordToBytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(cArr.length + 1) * 2];
        for (int r0 = 0; r0 != cArr.length; r0++) {
            int r2 = r0 * 2;
            bArr[r2] = (byte) (cArr[r0] >>> '\b');
            bArr[r2 + 1] = (byte) cArr[r0];
        }
        return bArr;
    }

    public static byte[] PKCS5PasswordToBytes(char[] cArr) {
        if (cArr != null) {
            int length = cArr.length;
            byte[] bArr = new byte[length];
            for (int r0 = 0; r0 != length; r0++) {
                bArr[r0] = (byte) cArr[r0];
            }
            return bArr;
        }
        return new byte[0];
    }

    public static byte[] PKCS5PasswordToUTF8Bytes(char[] cArr) {
        return cArr != null ? Strings.toUTF8ByteArray(cArr) : new byte[0];
    }

    public abstract CipherParameters generateDerivedMacParameters(int r1);

    public abstract CipherParameters generateDerivedParameters(int r1);

    public abstract CipherParameters generateDerivedParameters(int r1, int r2);

    public int getIterationCount() {
        return this.iterationCount;
    }

    public byte[] getPassword() {
        return this.password;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public void init(byte[] bArr, byte[] bArr2, int r3) {
        this.password = bArr;
        this.salt = bArr2;
        this.iterationCount = r3;
    }
}
