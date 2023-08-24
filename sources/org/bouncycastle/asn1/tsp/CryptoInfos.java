package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.Attribute;

/* loaded from: classes5.dex */
public class CryptoInfos extends ASN1Object {
    private ASN1Sequence attributes;

    private CryptoInfos(ASN1Sequence aSN1Sequence) {
        this.attributes = aSN1Sequence;
    }

    public CryptoInfos(Attribute[] attributeArr) {
        this.attributes = new DERSequence(attributeArr);
    }

    public static CryptoInfos getInstance(Object obj) {
        if (obj instanceof CryptoInfos) {
            return (CryptoInfos) obj;
        }
        if (obj != null) {
            return new CryptoInfos(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CryptoInfos getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Attribute[] getAttributes() {
        int size = this.attributes.size();
        Attribute[] attributeArr = new Attribute[size];
        for (int r2 = 0; r2 != size; r2++) {
            attributeArr[r2] = Attribute.getInstance(this.attributes.getObjectAt(r2));
        }
        return attributeArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.attributes;
    }
}
