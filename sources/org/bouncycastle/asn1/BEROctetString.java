package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.NoSuchElementException;

/* loaded from: classes5.dex */
public class BEROctetString extends ASN1OctetString {
    private static final int DEFAULT_SEGMENT_LIMIT = 1000;
    private final ASN1OctetString[] elements;
    private final int segmentLimit;

    public BEROctetString(byte[] bArr) {
        this(bArr, 1000);
    }

    public BEROctetString(byte[] bArr, int r3) {
        this(bArr, null, r3);
    }

    private BEROctetString(byte[] bArr, ASN1OctetString[] aSN1OctetStringArr, int r3) {
        super(bArr);
        this.elements = aSN1OctetStringArr;
        this.segmentLimit = r3;
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr) {
        this(aSN1OctetStringArr, 1000);
    }

    public BEROctetString(ASN1OctetString[] aSN1OctetStringArr, int r3) {
        this(flattenOctetStrings(aSN1OctetStringArr), aSN1OctetStringArr, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] flattenOctetStrings(ASN1OctetString[] aSN1OctetStringArr) {
        int length = aSN1OctetStringArr.length;
        if (length != 0) {
            if (length != 1) {
                int r3 = 0;
                for (ASN1OctetString aSN1OctetString : aSN1OctetStringArr) {
                    r3 += aSN1OctetString.string.length;
                }
                byte[] bArr = new byte[r3];
                int r4 = 0;
                for (ASN1OctetString aSN1OctetString2 : aSN1OctetStringArr) {
                    byte[] bArr2 = aSN1OctetString2.string;
                    System.arraycopy(bArr2, 0, bArr, r4, bArr2.length);
                    r4 += bArr2.length;
                }
                return bArr;
            }
            return aSN1OctetStringArr[0].string;
        }
        return EMPTY_OCTETS;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        if (!encodeConstructed()) {
            DEROctetString.encode(aSN1OutputStream, z, this.string, 0, this.string.length);
            return;
        }
        aSN1OutputStream.writeIdentifier(z, 36);
        aSN1OutputStream.write(128);
        ASN1OctetString[] aSN1OctetStringArr = this.elements;
        if (aSN1OctetStringArr != null) {
            aSN1OutputStream.writePrimitives(aSN1OctetStringArr);
        } else {
            int r6 = 0;
            while (r6 < this.string.length) {
                int min = Math.min(this.string.length - r6, this.segmentLimit);
                DEROctetString.encode(aSN1OutputStream, true, this.string, r6, min);
                r6 += min;
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean encodeConstructed() {
        return this.elements != null || this.string.length > this.segmentLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) throws IOException {
        if (!encodeConstructed()) {
            return DEROctetString.encodedLength(z, this.string.length);
        }
        int r5 = z ? 4 : 3;
        if (this.elements == null) {
            int length = this.string.length;
            int r2 = this.segmentLimit;
            int r0 = length / r2;
            int encodedLength = r5 + (DEROctetString.encodedLength(true, r2) * r0);
            int length2 = this.string.length - (r0 * this.segmentLimit);
            return length2 > 0 ? encodedLength + DEROctetString.encodedLength(true, length2) : encodedLength;
        }
        int r02 = 0;
        while (true) {
            ASN1OctetString[] aSN1OctetStringArr = this.elements;
            if (r02 >= aSN1OctetStringArr.length) {
                return r5;
            }
            r5 += aSN1OctetStringArr[r02].encodedLength(true);
            r02++;
        }
    }

    public Enumeration getObjects() {
        return this.elements == null ? new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.1
            int pos = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.pos < BEROctetString.this.string.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.pos < BEROctetString.this.string.length) {
                    int min = Math.min(BEROctetString.this.string.length - this.pos, BEROctetString.this.segmentLimit);
                    byte[] bArr = new byte[min];
                    System.arraycopy(BEROctetString.this.string, this.pos, bArr, 0, min);
                    this.pos += min;
                    return new DEROctetString(bArr);
                }
                throw new NoSuchElementException();
            }
        } : new Enumeration() { // from class: org.bouncycastle.asn1.BEROctetString.2
            int counter = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.counter < BEROctetString.this.elements.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                if (this.counter < BEROctetString.this.elements.length) {
                    ASN1OctetString[] aSN1OctetStringArr = BEROctetString.this.elements;
                    int r1 = this.counter;
                    this.counter = r1 + 1;
                    return aSN1OctetStringArr[r1];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
