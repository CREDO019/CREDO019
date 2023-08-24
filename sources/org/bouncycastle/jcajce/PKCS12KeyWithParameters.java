package org.bouncycastle.jcajce;

import javax.crypto.interfaces.PBEKey;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class PKCS12KeyWithParameters extends PKCS12Key implements PBEKey {
    private final int iterationCount;
    private final byte[] salt;

    public PKCS12KeyWithParameters(char[] cArr, boolean z, byte[] bArr, int r4) {
        super(cArr, z);
        this.salt = Arrays.clone(bArr);
        this.iterationCount = r4;
    }

    public PKCS12KeyWithParameters(char[] cArr, byte[] bArr, int r3) {
        super(cArr);
        this.salt = Arrays.clone(bArr);
        this.iterationCount = r3;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public int getIterationCount() {
        return this.iterationCount;
    }

    @Override // javax.crypto.interfaces.PBEKey
    public byte[] getSalt() {
        return this.salt;
    }
}
