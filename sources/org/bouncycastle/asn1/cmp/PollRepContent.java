package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes5.dex */
public class PollRepContent extends ASN1Object {
    private ASN1Integer[] certReqId;
    private ASN1Integer[] checkAfter;
    private PKIFreeText[] reason;

    public PollRepContent(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2) {
        this(aSN1Integer, aSN1Integer2, null);
    }

    public PollRepContent(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, PKIFreeText pKIFreeText) {
        this.certReqId = r1;
        this.checkAfter = r2;
        this.reason = r0;
        ASN1Integer[] aSN1IntegerArr = {aSN1Integer};
        ASN1Integer[] aSN1IntegerArr2 = {aSN1Integer2};
        PKIFreeText[] pKIFreeTextArr = {pKIFreeText};
    }

    private PollRepContent(ASN1Sequence aSN1Sequence) {
        this.certReqId = new ASN1Integer[aSN1Sequence.size()];
        this.checkAfter = new ASN1Integer[aSN1Sequence.size()];
        this.reason = new PKIFreeText[aSN1Sequence.size()];
        for (int r1 = 0; r1 != aSN1Sequence.size(); r1++) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(r1));
            this.certReqId[r1] = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(0));
            this.checkAfter[r1] = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(1));
            if (aSN1Sequence2.size() > 2) {
                this.reason[r1] = PKIFreeText.getInstance(aSN1Sequence2.getObjectAt(2));
            }
        }
    }

    public static PollRepContent getInstance(Object obj) {
        if (obj instanceof PollRepContent) {
            return (PollRepContent) obj;
        }
        if (obj != null) {
            return new PollRepContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getCertReqId(int r2) {
        return this.certReqId[r2];
    }

    public ASN1Integer getCheckAfter(int r2) {
        return this.checkAfter[r2];
    }

    public PKIFreeText getReason(int r2) {
        return this.reason[r2];
    }

    public int size() {
        return this.certReqId.length;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(this.certReqId.length);
        for (int r1 = 0; r1 != this.certReqId.length; r1++) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(3);
            aSN1EncodableVector2.add(this.certReqId[r1]);
            aSN1EncodableVector2.add(this.checkAfter[r1]);
            PKIFreeText[] pKIFreeTextArr = this.reason;
            if (pKIFreeTextArr[r1] != null) {
                aSN1EncodableVector2.add(pKIFreeTextArr[r1]);
            }
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
