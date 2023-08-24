package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class ParSet extends ASN1Object {

    /* renamed from: h */
    private int[] f2397h;

    /* renamed from: k */
    private int[] f2398k;

    /* renamed from: t */
    private int f2399t;

    /* renamed from: w */
    private int[] f2400w;

    public ParSet(int r1, int[] r2, int[] r3, int[] r4) {
        this.f2399t = r1;
        this.f2397h = r2;
        this.f2400w = r3;
        this.f2398k = r4;
    }

    private ParSet(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("sie of seqOfParams = " + aSN1Sequence.size());
        }
        this.f2399t = checkBigIntegerInIntRangeAndPositive(aSN1Sequence.getObjectAt(0));
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        if (aSN1Sequence2.size() != this.f2399t || aSN1Sequence3.size() != this.f2399t || aSN1Sequence4.size() != this.f2399t) {
            throw new IllegalArgumentException("invalid size of sequences");
        }
        this.f2397h = new int[aSN1Sequence2.size()];
        this.f2400w = new int[aSN1Sequence3.size()];
        this.f2398k = new int[aSN1Sequence4.size()];
        for (int r0 = 0; r0 < this.f2399t; r0++) {
            this.f2397h[r0] = checkBigIntegerInIntRangeAndPositive(aSN1Sequence2.getObjectAt(r0));
            this.f2400w[r0] = checkBigIntegerInIntRangeAndPositive(aSN1Sequence3.getObjectAt(r0));
            this.f2398k[r0] = checkBigIntegerInIntRangeAndPositive(aSN1Sequence4.getObjectAt(r0));
        }
    }

    private static int checkBigIntegerInIntRangeAndPositive(ASN1Encodable aSN1Encodable) {
        int intValueExact = ((ASN1Integer) aSN1Encodable).intValueExact();
        if (intValueExact > 0) {
            return intValueExact;
        }
        throw new IllegalArgumentException("BigInteger not in Range: " + intValueExact);
    }

    public static ParSet getInstance(Object obj) {
        if (obj instanceof ParSet) {
            return (ParSet) obj;
        }
        if (obj != null) {
            return new ParSet(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public int[] getH() {
        return Arrays.clone(this.f2397h);
    }

    public int[] getK() {
        return Arrays.clone(this.f2398k);
    }

    public int getT() {
        return this.f2399t;
    }

    public int[] getW() {
        return Arrays.clone(this.f2400w);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        for (int r3 = 0; r3 < this.f2397h.length; r3++) {
            aSN1EncodableVector.add(new ASN1Integer(this.f2397h[r3]));
            aSN1EncodableVector2.add(new ASN1Integer(this.f2400w[r3]));
            aSN1EncodableVector3.add(new ASN1Integer(this.f2398k[r3]));
        }
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        aSN1EncodableVector4.add(new ASN1Integer(this.f2399t));
        aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector));
        aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector4.add(new DERSequence(aSN1EncodableVector3));
        return new DERSequence(aSN1EncodableVector4);
    }
}
