package org.bouncycastle.oer.its;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;

/* loaded from: classes5.dex */
public class SubjectPermissions extends ASN1Object implements ASN1Choice {
    public static final int all = 1;
    public static final int explicit = 0;
    public static final int extension = 3;
    private final int choice;
    private final ASN1Encodable value;

    /* loaded from: classes5.dex */
    public static class Builder {
        int choice;
        ASN1Encodable value;

        public Builder all() {
            this.choice = 1;
            this.value = DERNull.INSTANCE;
            return this;
        }

        public Builder choice(int r1) {
            this.choice = r1;
            return this;
        }

        public SubjectPermissions createSubjectPermissions() {
            return new SubjectPermissions(this.choice, this.value);
        }

        public Builder explicit(SequenceOfPsidSspRange sequenceOfPsidSspRange) {
            this.choice = 0;
            this.value = sequenceOfPsidSspRange;
            return this;
        }

        public Builder extension(ASN1Encodable aSN1Encodable) {
            this.choice = 3;
            if (aSN1Encodable instanceof ASN1OctetString) {
                this.value = aSN1Encodable;
            } else {
                try {
                    this.value = new DEROctetString(aSN1Encodable.toASN1Primitive().getEncoded());
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            return this;
        }

        public Builder value(ASN1Encodable aSN1Encodable) {
            this.value = aSN1Encodable;
            return this;
        }
    }

    SubjectPermissions(int r1, ASN1Encodable aSN1Encodable) {
        this.value = aSN1Encodable;
        this.choice = r1;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SubjectPermissions getInstance(Object obj) {
        if (obj instanceof SubjectPermissions) {
            return (SubjectPermissions) obj;
        }
        ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(obj);
        int tagNo = aSN1TaggedObject.getTagNo();
        if (tagNo != 0) {
            if (tagNo != 1) {
                if (tagNo != 3) {
                    return null;
                }
                try {
                    return new SubjectPermissions(3, new DEROctetString(aSN1TaggedObject.getObject().getEncoded()));
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
            return new SubjectPermissions(1, DERNull.INSTANCE);
        }
        return new SubjectPermissions(0, SequenceOfPsidSspRange.getInstance(aSN1TaggedObject.getObject()));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, this.value);
    }
}
