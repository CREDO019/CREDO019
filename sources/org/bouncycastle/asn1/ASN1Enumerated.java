package org.bouncycastle.asn1;

import java.io.IOException;
import java.math.BigInteger;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class ASN1Enumerated extends ASN1Primitive {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1Enumerated.class, 10) { // from class: org.bouncycastle.asn1.ASN1Enumerated.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1Enumerated.createPrimitive(dEROctetString.getOctets(), false);
        }
    };
    private static final ASN1Enumerated[] cache = new ASN1Enumerated[12];
    private final byte[] contents;
    private final int start;

    public ASN1Enumerated(int r3) {
        if (r3 < 0) {
            throw new IllegalArgumentException("enumerated must be non-negative");
        }
        this.contents = BigInteger.valueOf(r3).toByteArray();
        this.start = 0;
    }

    public ASN1Enumerated(BigInteger bigInteger) {
        if (bigInteger.signum() < 0) {
            throw new IllegalArgumentException("enumerated must be non-negative");
        }
        this.contents = bigInteger.toByteArray();
        this.start = 0;
    }

    public ASN1Enumerated(byte[] bArr) {
        this(bArr, true);
    }

    ASN1Enumerated(byte[] bArr, boolean z) {
        if (ASN1Integer.isMalformed(bArr)) {
            throw new IllegalArgumentException("malformed enumerated");
        }
        if ((bArr[0] & 128) != 0) {
            throw new IllegalArgumentException("enumerated must be non-negative");
        }
        this.contents = z ? Arrays.clone(bArr) : bArr;
        this.start = ASN1Integer.signBytesToSkip(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Enumerated createPrimitive(byte[] bArr, boolean z) {
        if (bArr.length > 1) {
            return new ASN1Enumerated(bArr, z);
        }
        if (bArr.length != 0) {
            int r0 = bArr[0] & 255;
            ASN1Enumerated[] aSN1EnumeratedArr = cache;
            if (r0 >= aSN1EnumeratedArr.length) {
                return new ASN1Enumerated(bArr, z);
            }
            ASN1Enumerated aSN1Enumerated = aSN1EnumeratedArr[r0];
            if (aSN1Enumerated == null) {
                ASN1Enumerated aSN1Enumerated2 = new ASN1Enumerated(bArr, z);
                aSN1EnumeratedArr[r0] = aSN1Enumerated2;
                return aSN1Enumerated2;
            }
            return aSN1Enumerated;
        }
        throw new IllegalArgumentException("ENUMERATED has zero length");
    }

    public static ASN1Enumerated getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Enumerated)) {
            return (ASN1Enumerated) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1Enumerated) TYPE.fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static ASN1Enumerated getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1Enumerated) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1Enumerated) {
            return Arrays.areEqual(this.contents, ((ASN1Enumerated) aSN1Primitive).contents);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 10, this.contents);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean encodeConstructed() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    public BigInteger getValue() {
        return new BigInteger(this.contents);
    }

    public boolean hasValue(int r5) {
        byte[] bArr = this.contents;
        int length = bArr.length;
        int r2 = this.start;
        return length - r2 <= 4 && ASN1Integer.intValue(bArr, r2, -1) == r5;
    }

    public boolean hasValue(BigInteger bigInteger) {
        return bigInteger != null && ASN1Integer.intValue(this.contents, this.start, -1) == bigInteger.intValue() && getValue().equals(bigInteger);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.contents);
    }

    public int intValueExact() {
        byte[] bArr = this.contents;
        int length = bArr.length;
        int r2 = this.start;
        if (length - r2 <= 4) {
            return ASN1Integer.intValue(bArr, r2, -1);
        }
        throw new ArithmeticException("ASN.1 Enumerated out of int range");
    }
}
