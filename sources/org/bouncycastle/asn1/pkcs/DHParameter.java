package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes5.dex */
public class DHParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f1618g;

    /* renamed from: l */
    ASN1Integer f1619l;

    /* renamed from: p */
    ASN1Integer f1620p;

    public DHParameter(BigInteger bigInteger, BigInteger bigInteger2, int r4) {
        this.f1620p = new ASN1Integer(bigInteger);
        this.f1618g = new ASN1Integer(bigInteger2);
        this.f1619l = r4 != 0 ? new ASN1Integer(r4) : null;
    }

    private DHParameter(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.f1620p = ASN1Integer.getInstance(objects.nextElement());
        this.f1618g = ASN1Integer.getInstance(objects.nextElement());
        this.f1619l = objects.hasMoreElements() ? (ASN1Integer) objects.nextElement() : null;
    }

    public static DHParameter getInstance(Object obj) {
        if (obj instanceof DHParameter) {
            return (DHParameter) obj;
        }
        if (obj != null) {
            return new DHParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public BigInteger getG() {
        return this.f1618g.getPositiveValue();
    }

    public BigInteger getL() {
        ASN1Integer aSN1Integer = this.f1619l;
        if (aSN1Integer == null) {
            return null;
        }
        return aSN1Integer.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f1620p.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f1620p);
        aSN1EncodableVector.add(this.f1618g);
        if (getL() != null) {
            aSN1EncodableVector.add(this.f1619l);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
