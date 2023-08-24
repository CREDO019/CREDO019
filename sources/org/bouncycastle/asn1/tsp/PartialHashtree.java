package org.bouncycastle.asn1.tsp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class PartialHashtree extends ASN1Object {
    private final ASN1Sequence values;

    private PartialHashtree(ASN1Sequence aSN1Sequence) {
        for (int r0 = 0; r0 != aSN1Sequence.size(); r0++) {
            if (!(aSN1Sequence.getObjectAt(r0) instanceof DEROctetString)) {
                throw new IllegalArgumentException("unknown object in constructor: " + aSN1Sequence.getObjectAt(r0).getClass().getName());
            }
        }
        this.values = aSN1Sequence;
    }

    public PartialHashtree(byte[] bArr) {
        this(new byte[][]{bArr});
    }

    public PartialHashtree(byte[][] bArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(bArr.length);
        for (int r1 = 0; r1 != bArr.length; r1++) {
            aSN1EncodableVector.add(new DEROctetString(Arrays.clone(bArr[r1])));
        }
        this.values = new DERSequence(aSN1EncodableVector);
    }

    public static PartialHashtree getInstance(Object obj) {
        if (obj instanceof PartialHashtree) {
            return (PartialHashtree) obj;
        }
        if (obj != null) {
            return new PartialHashtree(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public boolean containsHash(byte[] bArr) {
        Enumeration objects = this.values.getObjects();
        while (objects.hasMoreElements()) {
            if (Arrays.constantTimeAreEqual(bArr, ASN1OctetString.getInstance(objects.nextElement()).getOctets())) {
                return true;
            }
        }
        return false;
    }

    public int getValueCount() {
        return this.values.size();
    }

    public byte[][] getValues() {
        int size = this.values.size();
        byte[][] bArr = new byte[size];
        for (int r2 = 0; r2 != size; r2++) {
            bArr[r2] = Arrays.clone(ASN1OctetString.getInstance(this.values.getObjectAt(r2)).getOctets());
        }
        return bArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.values;
    }
}
