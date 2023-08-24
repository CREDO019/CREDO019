package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

/* loaded from: classes5.dex */
public class KeyRecRepContent extends ASN1Object {
    private ASN1Sequence caCerts;
    private ASN1Sequence keyPairHist;
    private CMPCertificate newSigCert;
    private PKIStatusInfo status;

    private KeyRecRepContent(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.status = PKIStatusInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo == 0) {
                this.newSigCert = CMPCertificate.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo == 1) {
                this.caCerts = ASN1Sequence.getInstance(aSN1TaggedObject.getObject());
            } else if (tagNo != 2) {
                throw new IllegalArgumentException("unknown tag number: " + aSN1TaggedObject.getTagNo());
            } else {
                this.keyPairHist = ASN1Sequence.getInstance(aSN1TaggedObject.getObject());
            }
        }
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, int r4, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, r4, aSN1Encodable));
        }
    }

    public static KeyRecRepContent getInstance(Object obj) {
        if (obj instanceof KeyRecRepContent) {
            return (KeyRecRepContent) obj;
        }
        if (obj != null) {
            return new KeyRecRepContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CMPCertificate[] getCaCerts() {
        ASN1Sequence aSN1Sequence = this.caCerts;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        CMPCertificate[] cMPCertificateArr = new CMPCertificate[size];
        for (int r2 = 0; r2 != size; r2++) {
            cMPCertificateArr[r2] = CMPCertificate.getInstance(this.caCerts.getObjectAt(r2));
        }
        return cMPCertificateArr;
    }

    public CertifiedKeyPair[] getKeyPairHist() {
        ASN1Sequence aSN1Sequence = this.keyPairHist;
        if (aSN1Sequence == null) {
            return null;
        }
        int size = aSN1Sequence.size();
        CertifiedKeyPair[] certifiedKeyPairArr = new CertifiedKeyPair[size];
        for (int r2 = 0; r2 != size; r2++) {
            certifiedKeyPairArr[r2] = CertifiedKeyPair.getInstance(this.keyPairHist.getObjectAt(r2));
        }
        return certifiedKeyPairArr;
    }

    public CMPCertificate getNewSigCert() {
        return this.newSigCert;
    }

    public PKIStatusInfo getStatus() {
        return this.status;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(this.status);
        addOptional(aSN1EncodableVector, 0, this.newSigCert);
        addOptional(aSN1EncodableVector, 1, this.caCerts);
        addOptional(aSN1EncodableVector, 2, this.keyPairHist);
        return new DERSequence(aSN1EncodableVector);
    }
}
