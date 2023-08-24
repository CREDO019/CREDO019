package org.bouncycastle.jcajce.spec;

import javax.crypto.spec.IvParameterSpec;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class AEADParameterSpec extends IvParameterSpec {
    private final byte[] associatedData;
    private final int macSizeInBits;

    public AEADParameterSpec(byte[] bArr, int r3) {
        this(bArr, r3, null);
    }

    public AEADParameterSpec(byte[] bArr, int r2, byte[] bArr2) {
        super(bArr);
        this.macSizeInBits = r2;
        this.associatedData = Arrays.clone(bArr2);
    }

    public byte[] getAssociatedData() {
        return Arrays.clone(this.associatedData);
    }

    public int getMacSizeInBits() {
        return this.macSizeInBits;
    }

    public byte[] getNonce() {
        return getIV();
    }
}
