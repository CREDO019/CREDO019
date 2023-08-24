package org.bouncycastle.asn1.cmc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes5.dex */
public class PKIData extends ASN1Object {
    private final TaggedContentInfo[] cmsSequence;
    private final TaggedAttribute[] controlSequence;
    private final OtherMsg[] otherMsgSequence;
    private final TaggedRequest[] reqSequence;

    private PKIData(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 4) {
            throw new IllegalArgumentException("Sequence not 4 elements.");
        }
        int r0 = 0;
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
        this.controlSequence = new TaggedAttribute[aSN1Sequence2.size()];
        int r2 = 0;
        while (true) {
            TaggedAttribute[] taggedAttributeArr = this.controlSequence;
            if (r2 >= taggedAttributeArr.length) {
                break;
            }
            taggedAttributeArr[r2] = TaggedAttribute.getInstance(aSN1Sequence2.getObjectAt(r2));
            r2++;
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence.getObjectAt(1);
        this.reqSequence = new TaggedRequest[aSN1Sequence3.size()];
        int r22 = 0;
        while (true) {
            TaggedRequest[] taggedRequestArr = this.reqSequence;
            if (r22 >= taggedRequestArr.length) {
                break;
            }
            taggedRequestArr[r22] = TaggedRequest.getInstance(aSN1Sequence3.getObjectAt(r22));
            r22++;
        }
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence) aSN1Sequence.getObjectAt(2);
        this.cmsSequence = new TaggedContentInfo[aSN1Sequence4.size()];
        int r23 = 0;
        while (true) {
            TaggedContentInfo[] taggedContentInfoArr = this.cmsSequence;
            if (r23 >= taggedContentInfoArr.length) {
                break;
            }
            taggedContentInfoArr[r23] = TaggedContentInfo.getInstance(aSN1Sequence4.getObjectAt(r23));
            r23++;
        }
        ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence.getObjectAt(3);
        this.otherMsgSequence = new OtherMsg[aSN1Sequence5.size()];
        while (true) {
            OtherMsg[] otherMsgArr = this.otherMsgSequence;
            if (r0 >= otherMsgArr.length) {
                return;
            }
            otherMsgArr[r0] = OtherMsg.getInstance(aSN1Sequence5.getObjectAt(r0));
            r0++;
        }
    }

    public PKIData(TaggedAttribute[] taggedAttributeArr, TaggedRequest[] taggedRequestArr, TaggedContentInfo[] taggedContentInfoArr, OtherMsg[] otherMsgArr) {
        this.controlSequence = copy(taggedAttributeArr);
        this.reqSequence = copy(taggedRequestArr);
        this.cmsSequence = copy(taggedContentInfoArr);
        this.otherMsgSequence = copy(otherMsgArr);
    }

    private OtherMsg[] copy(OtherMsg[] otherMsgArr) {
        int length = otherMsgArr.length;
        OtherMsg[] otherMsgArr2 = new OtherMsg[length];
        System.arraycopy(otherMsgArr, 0, otherMsgArr2, 0, length);
        return otherMsgArr2;
    }

    private TaggedAttribute[] copy(TaggedAttribute[] taggedAttributeArr) {
        int length = taggedAttributeArr.length;
        TaggedAttribute[] taggedAttributeArr2 = new TaggedAttribute[length];
        System.arraycopy(taggedAttributeArr, 0, taggedAttributeArr2, 0, length);
        return taggedAttributeArr2;
    }

    private TaggedContentInfo[] copy(TaggedContentInfo[] taggedContentInfoArr) {
        int length = taggedContentInfoArr.length;
        TaggedContentInfo[] taggedContentInfoArr2 = new TaggedContentInfo[length];
        System.arraycopy(taggedContentInfoArr, 0, taggedContentInfoArr2, 0, length);
        return taggedContentInfoArr2;
    }

    private TaggedRequest[] copy(TaggedRequest[] taggedRequestArr) {
        int length = taggedRequestArr.length;
        TaggedRequest[] taggedRequestArr2 = new TaggedRequest[length];
        System.arraycopy(taggedRequestArr, 0, taggedRequestArr2, 0, length);
        return taggedRequestArr2;
    }

    public static PKIData getInstance(Object obj) {
        if (obj instanceof PKIData) {
            return (PKIData) obj;
        }
        if (obj != null) {
            return new PKIData(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public TaggedContentInfo[] getCmsSequence() {
        return copy(this.cmsSequence);
    }

    public TaggedAttribute[] getControlSequence() {
        return copy(this.controlSequence);
    }

    public OtherMsg[] getOtherMsgSequence() {
        return copy(this.otherMsgSequence);
    }

    public TaggedRequest[] getReqSequence() {
        return copy(this.reqSequence);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(new ASN1Encodable[]{new DERSequence(this.controlSequence), new DERSequence(this.reqSequence), new DERSequence(this.cmsSequence), new DERSequence(this.otherMsgSequence)});
    }
}
