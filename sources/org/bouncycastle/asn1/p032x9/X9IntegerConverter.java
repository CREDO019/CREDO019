package org.bouncycastle.asn1.p032x9;

import java.math.BigInteger;
import org.bouncycastle.math.p039ec.ECCurve;
import org.bouncycastle.math.p039ec.ECFieldElement;

/* renamed from: org.bouncycastle.asn1.x9.X9IntegerConverter */
/* loaded from: classes5.dex */
public class X9IntegerConverter {
    public int getByteLength(ECCurve eCCurve) {
        return (eCCurve.getFieldSize() + 7) / 8;
    }

    public int getByteLength(ECFieldElement eCFieldElement) {
        return (eCFieldElement.getFieldSize() + 7) / 8;
    }

    public byte[] integerToBytes(BigInteger bigInteger, int r5) {
        byte[] byteArray = bigInteger.toByteArray();
        if (r5 < byteArray.length) {
            byte[] bArr = new byte[r5];
            System.arraycopy(byteArray, byteArray.length - r5, bArr, 0, r5);
            return bArr;
        } else if (r5 > byteArray.length) {
            byte[] bArr2 = new byte[r5];
            System.arraycopy(byteArray, 0, bArr2, r5 - byteArray.length, byteArray.length);
            return bArr2;
        } else {
            return byteArray;
        }
    }
}
