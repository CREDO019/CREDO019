package org.bouncycastle.asn1.x500;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;

/* loaded from: classes5.dex */
public class RDN extends ASN1Object {
    private ASN1Set values;

    public RDN(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        aSN1EncodableVector.add(aSN1Encodable);
        this.values = new DERSet(new DERSequence(aSN1EncodableVector));
    }

    private RDN(ASN1Set aSN1Set) {
        this.values = aSN1Set;
    }

    public RDN(AttributeTypeAndValue attributeTypeAndValue) {
        this.values = new DERSet(attributeTypeAndValue);
    }

    public RDN(AttributeTypeAndValue[] attributeTypeAndValueArr) {
        this.values = new DERSet(attributeTypeAndValueArr);
    }

    public static RDN getInstance(Object obj) {
        if (obj instanceof RDN) {
            return (RDN) obj;
        }
        if (obj != null) {
            return new RDN(ASN1Set.getInstance(obj));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int collectAttributeTypes(ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr, int r6) {
        int size = this.values.size();
        for (int r1 = 0; r1 < size; r1++) {
            aSN1ObjectIdentifierArr[r6 + r1] = AttributeTypeAndValue.getInstance(this.values.getObjectAt(r1)).getType();
        }
        return size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsAttributeType(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        int size = this.values.size();
        for (int r2 = 0; r2 < size; r2++) {
            if (AttributeTypeAndValue.getInstance(this.values.getObjectAt(r2)).getType().equals((ASN1Primitive) aSN1ObjectIdentifier)) {
                return true;
            }
        }
        return false;
    }

    public AttributeTypeAndValue getFirst() {
        if (this.values.size() == 0) {
            return null;
        }
        return AttributeTypeAndValue.getInstance(this.values.getObjectAt(0));
    }

    public AttributeTypeAndValue[] getTypesAndValues() {
        int size = this.values.size();
        AttributeTypeAndValue[] attributeTypeAndValueArr = new AttributeTypeAndValue[size];
        for (int r2 = 0; r2 != size; r2++) {
            attributeTypeAndValueArr[r2] = AttributeTypeAndValue.getInstance(this.values.getObjectAt(r2));
        }
        return attributeTypeAndValueArr;
    }

    public boolean isMultiValued() {
        return this.values.size() > 1;
    }

    public int size() {
        return this.values.size();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.values;
    }
}
