package org.bouncycastle.asn1.p029bc;

import java.util.Iterator;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Iterable;

/* renamed from: org.bouncycastle.asn1.bc.ObjectDataSequence */
/* loaded from: classes5.dex */
public class ObjectDataSequence extends ASN1Object implements Iterable<ASN1Encodable> {
    private final ASN1Encodable[] dataSequence;

    private ObjectDataSequence(ASN1Sequence aSN1Sequence) {
        this.dataSequence = new ASN1Encodable[aSN1Sequence.size()];
        int r0 = 0;
        while (true) {
            ASN1Encodable[] aSN1EncodableArr = this.dataSequence;
            if (r0 == aSN1EncodableArr.length) {
                return;
            }
            aSN1EncodableArr[r0] = ObjectData.getInstance(aSN1Sequence.getObjectAt(r0));
            r0++;
        }
    }

    public ObjectDataSequence(ObjectData[] objectDataArr) {
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[objectDataArr.length];
        this.dataSequence = aSN1EncodableArr;
        System.arraycopy(objectDataArr, 0, aSN1EncodableArr, 0, objectDataArr.length);
    }

    public static ObjectDataSequence getInstance(Object obj) {
        if (obj instanceof ObjectDataSequence) {
            return (ObjectDataSequence) obj;
        }
        if (obj != null) {
            return new ObjectDataSequence(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.util.Iterable, java.lang.Iterable
    public Iterator<ASN1Encodable> iterator() {
        return new Arrays.Iterator(this.dataSequence);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.dataSequence);
    }
}
