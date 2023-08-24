package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes5.dex */
public class DLBitString extends ASN1BitString {
    public DLBitString(byte b, int r2) {
        super(b, r2);
    }

    public DLBitString(int r2) {
        super(getBytes(r2), getPadBits(r2));
    }

    public DLBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded(ASN1Encoding.DER), 0);
    }

    public DLBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DLBitString(byte[] bArr, int r2) {
        super(bArr, r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLBitString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void encode(ASN1OutputStream aSN1OutputStream, boolean z, byte b, byte[] bArr, int r11, int r12) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 3, b, bArr, r11, r12);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void encode(ASN1OutputStream aSN1OutputStream, boolean z, byte[] bArr, int r9, int r10) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 3, bArr, r9, r10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodedLength(boolean z, int r1) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, r1);
    }

    static DLBitString fromOctetString(ASN1OctetString aSN1OctetString) {
        return new DLBitString(aSN1OctetString.getOctets(), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 3, this.contents);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1BitString, org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return this;
    }
}
