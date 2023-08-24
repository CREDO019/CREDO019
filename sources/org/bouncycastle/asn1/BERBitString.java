package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class BERBitString extends ASN1BitString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1BitString[] elements;
    private final int segmentLimit;

    public BERBitString(byte b, int r2) {
        super(b, r2);
        this.elements = null;
        this.segmentLimit = 1000;
    }

    public BERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        this(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public BERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public BERBitString(byte[] bArr, int r3) {
        this(bArr, r3, 1000);
    }

    public BERBitString(byte[] bArr, int r2, int r3) {
        super(bArr, r2);
        this.elements = null;
        this.segmentLimit = r3;
    }

    BERBitString(byte[] bArr, boolean z) {
        super(bArr, z);
        this.elements = null;
        this.segmentLimit = 1000;
    }

    public BERBitString(ASN1BitString[] aSN1BitStringArr) {
        this(aSN1BitStringArr, 1000);
    }

    public BERBitString(ASN1BitString[] aSN1BitStringArr, int r4) {
        super(flattenBitStrings(aSN1BitStringArr), false);
        this.elements = aSN1BitStringArr;
        this.segmentLimit = r4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] flattenBitStrings(ASN1BitString[] aSN1BitStringArr) {
        int length = aSN1BitStringArr.length;
        if (length != 0) {
            if (length != 1) {
                int r3 = length - 1;
                int r5 = 0;
                for (int r4 = 0; r4 < r3; r4++) {
                    byte[] bArr = aSN1BitStringArr[r4].contents;
                    if (bArr[0] != 0) {
                        throw new IllegalArgumentException("only the last nested bitstring can have padding");
                    }
                    r5 += bArr.length - 1;
                }
                byte[] bArr2 = aSN1BitStringArr[r3].contents;
                byte b = bArr2[0];
                byte[] bArr3 = new byte[r5 + bArr2.length];
                bArr3[0] = b;
                int r42 = 1;
                for (ASN1BitString aSN1BitString : aSN1BitStringArr) {
                    byte[] bArr4 = aSN1BitString.contents;
                    int length2 = bArr4.length - 1;
                    System.arraycopy(bArr4, 1, bArr3, r42, length2);
                    r42 += length2;
                }
                return bArr3;
            }
            return aSN1BitStringArr[0].contents;
        }
        return new byte[]{0};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        if (!encodeConstructed()) {
            DLBitString.encode(aSN1OutputStream, z, this.contents, 0, this.contents.length);
            return;
        }
        aSN1OutputStream.writeIdentifier(z, 35);
        aSN1OutputStream.write(128);
        ASN1BitString[] aSN1BitStringArr = this.elements;
        if (aSN1BitStringArr != null) {
            aSN1OutputStream.writePrimitives(aSN1BitStringArr);
        } else if (this.contents.length >= 2) {
            byte b = this.contents[0];
            int length = this.contents.length;
            int r0 = length - 1;
            int r2 = this.segmentLimit - 1;
            while (r0 > r2) {
                DLBitString.encode(aSN1OutputStream, true, (byte) 0, this.contents, length - r0, r2);
                r0 -= r2;
            }
            DLBitString.encode(aSN1OutputStream, true, b, this.contents, length - r0, r0);
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean encodeConstructed() {
        return this.elements != null || this.contents.length > this.segmentLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) throws IOException {
        if (!encodeConstructed()) {
            return DLBitString.encodedLength(z, this.contents.length);
        }
        int r5 = z ? 4 : 3;
        if (this.elements == null) {
            if (this.contents.length < 2) {
                return r5;
            }
            int r2 = this.segmentLimit;
            int length = (this.contents.length - 2) / (r2 - 1);
            return r5 + (DLBitString.encodedLength(true, r2) * length) + DLBitString.encodedLength(true, this.contents.length - (length * (this.segmentLimit - 1)));
        }
        int r0 = 0;
        while (true) {
            ASN1BitString[] aSN1BitStringArr = this.elements;
            if (r0 >= aSN1BitStringArr.length) {
                return r5;
            }
            r5 += aSN1BitStringArr[r0].encodedLength(true);
            r0++;
        }
    }
}
