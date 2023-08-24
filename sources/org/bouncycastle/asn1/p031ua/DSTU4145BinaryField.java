package org.bouncycastle.asn1.p031ua;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* renamed from: org.bouncycastle.asn1.ua.DSTU4145BinaryField */
/* loaded from: classes5.dex */
public class DSTU4145BinaryField extends ASN1Object {

    /* renamed from: j */
    private int f1623j;

    /* renamed from: k */
    private int f1624k;

    /* renamed from: l */
    private int f1625l;

    /* renamed from: m */
    private int f1626m;

    public DSTU4145BinaryField(int r2, int r3) {
        this(r2, r3, 0, 0);
    }

    public DSTU4145BinaryField(int r1, int r2, int r3, int r4) {
        this.f1626m = r1;
        this.f1624k = r2;
        this.f1623j = r3;
        this.f1625l = r4;
    }

    private DSTU4145BinaryField(ASN1Sequence aSN1Sequence) {
        this.f1626m = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).intPositiveValueExact();
        if (aSN1Sequence.getObjectAt(1) instanceof ASN1Integer) {
            this.f1624k = ((ASN1Integer) aSN1Sequence.getObjectAt(1)).intPositiveValueExact();
        } else if (!(aSN1Sequence.getObjectAt(1) instanceof ASN1Sequence)) {
            throw new IllegalArgumentException("object parse error");
        } else {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
            this.f1624k = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0)).intPositiveValueExact();
            this.f1623j = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(1)).intPositiveValueExact();
            this.f1625l = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(2)).intPositiveValueExact();
        }
    }

    public static DSTU4145BinaryField getInstance(Object obj) {
        if (obj instanceof DSTU4145BinaryField) {
            return (DSTU4145BinaryField) obj;
        }
        if (obj != null) {
            return new DSTU4145BinaryField(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int getK1() {
        return this.f1624k;
    }

    public int getK2() {
        return this.f1623j;
    }

    public int getK3() {
        return this.f1625l;
    }

    public int getM() {
        return this.f1626m;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(new ASN1Integer(this.f1626m));
        if (this.f1623j == 0) {
            aSN1EncodableVector.add(new ASN1Integer(this.f1624k));
        } else {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(3);
            aSN1EncodableVector2.add(new ASN1Integer(this.f1624k));
            aSN1EncodableVector2.add(new ASN1Integer(this.f1623j));
            aSN1EncodableVector2.add(new ASN1Integer(this.f1625l));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
