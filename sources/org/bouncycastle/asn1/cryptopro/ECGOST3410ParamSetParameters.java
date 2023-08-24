package org.bouncycastle.asn1.cryptopro;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes5.dex */
public class ECGOST3410ParamSetParameters extends ASN1Object {

    /* renamed from: a */
    ASN1Integer f1592a;

    /* renamed from: b */
    ASN1Integer f1593b;

    /* renamed from: p */
    ASN1Integer f1594p;

    /* renamed from: q */
    ASN1Integer f1595q;

    /* renamed from: x */
    ASN1Integer f1596x;

    /* renamed from: y */
    ASN1Integer f1597y;

    public ECGOST3410ParamSetParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int r6, BigInteger bigInteger5) {
        this.f1592a = new ASN1Integer(bigInteger);
        this.f1593b = new ASN1Integer(bigInteger2);
        this.f1594p = new ASN1Integer(bigInteger3);
        this.f1595q = new ASN1Integer(bigInteger4);
        this.f1596x = new ASN1Integer(r6);
        this.f1597y = new ASN1Integer(bigInteger5);
    }

    public ECGOST3410ParamSetParameters(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f1592a = (ASN1Integer) objects.nextElement();
        this.f1593b = (ASN1Integer) objects.nextElement();
        this.f1594p = (ASN1Integer) objects.nextElement();
        this.f1595q = (ASN1Integer) objects.nextElement();
        this.f1596x = (ASN1Integer) objects.nextElement();
        this.f1597y = (ASN1Integer) objects.nextElement();
    }

    public static ECGOST3410ParamSetParameters getInstance(Object obj) {
        if (obj == null || (obj instanceof ECGOST3410ParamSetParameters)) {
            return (ECGOST3410ParamSetParameters) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new ECGOST3410ParamSetParameters((ASN1Sequence) obj);
        }
        throw new IllegalArgumentException("Invalid GOST3410Parameter: " + obj.getClass().getName());
    }

    public static ECGOST3410ParamSetParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getA() {
        return this.f1592a.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f1594p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f1595q.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.f1592a);
        aSN1EncodableVector.add(this.f1593b);
        aSN1EncodableVector.add(this.f1594p);
        aSN1EncodableVector.add(this.f1595q);
        aSN1EncodableVector.add(this.f1596x);
        aSN1EncodableVector.add(this.f1597y);
        return new DERSequence(aSN1EncodableVector);
    }
}
