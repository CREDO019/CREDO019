package org.bouncycastle.oer.its;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class SequenceOfOctetString extends ASN1Object {
    private byte[][] octetStrings;

    private SequenceOfOctetString(ASN1Sequence aSN1Sequence) {
        this.octetStrings = toByteArrays(aSN1Sequence);
    }

    public static SequenceOfOctetString getInstance(Object obj) {
        if (obj instanceof SequenceOfOctetString) {
            return (SequenceOfOctetString) obj;
        }
        if (obj != null) {
            return new SequenceOfOctetString(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    static byte[][] toByteArrays(ASN1Sequence aSN1Sequence) {
        byte[][] bArr = new byte[aSN1Sequence.size()];
        for (int r1 = 0; r1 != aSN1Sequence.size(); r1++) {
            bArr[r1] = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(r1)).getOctets();
        }
        return bArr;
    }

    public int size() {
        return this.octetStrings.length;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int r1 = 0; r1 != this.octetStrings.length; r1++) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(this.octetStrings[r1])));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
