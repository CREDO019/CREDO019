package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class CramerShoupCiphertext {

    /* renamed from: e */
    BigInteger f1911e;

    /* renamed from: u1 */
    BigInteger f1912u1;

    /* renamed from: u2 */
    BigInteger f1913u2;

    /* renamed from: v */
    BigInteger f1914v;

    public CramerShoupCiphertext() {
    }

    public CramerShoupCiphertext(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f1912u1 = bigInteger;
        this.f1913u2 = bigInteger2;
        this.f1911e = bigInteger3;
        this.f1914v = bigInteger4;
    }

    public CramerShoupCiphertext(byte[] bArr) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, 0) + 4;
        this.f1912u1 = new BigInteger(Arrays.copyOfRange(bArr, 4, bigEndianToInt));
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, bigEndianToInt);
        int r0 = bigEndianToInt + 4;
        int r2 = bigEndianToInt2 + r0;
        this.f1913u2 = new BigInteger(Arrays.copyOfRange(bArr, r0, r2));
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, r2);
        int r22 = r2 + 4;
        int r02 = bigEndianToInt3 + r22;
        this.f1911e = new BigInteger(Arrays.copyOfRange(bArr, r22, r02));
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, r02);
        int r03 = r02 + 4;
        this.f1914v = new BigInteger(Arrays.copyOfRange(bArr, r03, bigEndianToInt4 + r03));
    }

    public BigInteger getE() {
        return this.f1911e;
    }

    public BigInteger getU1() {
        return this.f1912u1;
    }

    public BigInteger getU2() {
        return this.f1913u2;
    }

    public BigInteger getV() {
        return this.f1914v;
    }

    public void setE(BigInteger bigInteger) {
        this.f1911e = bigInteger;
    }

    public void setU1(BigInteger bigInteger) {
        this.f1912u1 = bigInteger;
    }

    public void setU2(BigInteger bigInteger) {
        this.f1913u2 = bigInteger;
    }

    public void setV(BigInteger bigInteger) {
        this.f1914v = bigInteger;
    }

    public byte[] toByteArray() {
        byte[] byteArray = this.f1912u1.toByteArray();
        int length = byteArray.length;
        byte[] byteArray2 = this.f1913u2.toByteArray();
        int length2 = byteArray2.length;
        byte[] byteArray3 = this.f1911e.toByteArray();
        int length3 = byteArray3.length;
        byte[] byteArray4 = this.f1914v.toByteArray();
        int length4 = byteArray4.length;
        byte[] bArr = new byte[length + length2 + length3 + length4 + 16];
        Pack.intToBigEndian(length, bArr, 0);
        System.arraycopy(byteArray, 0, bArr, 4, length);
        int r1 = length + 4;
        Pack.intToBigEndian(length2, bArr, r1);
        int r12 = r1 + 4;
        System.arraycopy(byteArray2, 0, bArr, r12, length2);
        int r13 = r12 + length2;
        Pack.intToBigEndian(length3, bArr, r13);
        int r14 = r13 + 4;
        System.arraycopy(byteArray3, 0, bArr, r14, length3);
        int r15 = r14 + length3;
        Pack.intToBigEndian(length4, bArr, r15);
        System.arraycopy(byteArray4, 0, bArr, r15 + 4, length4);
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("u1: " + this.f1912u1.toString());
        stringBuffer.append("\nu2: " + this.f1913u2.toString());
        stringBuffer.append("\ne: " + this.f1911e.toString());
        stringBuffer.append("\nv: " + this.f1914v.toString());
        return stringBuffer.toString();
    }
}
