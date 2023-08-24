package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class GeneralNames extends ASN1Object {
    private final GeneralName[] names;

    private GeneralNames(ASN1Sequence aSN1Sequence) {
        this.names = new GeneralName[aSN1Sequence.size()];
        for (int r0 = 0; r0 != aSN1Sequence.size(); r0++) {
            this.names[r0] = GeneralName.getInstance(aSN1Sequence.getObjectAt(r0));
        }
    }

    public GeneralNames(GeneralName generalName) {
        this.names = new GeneralName[]{generalName};
    }

    public GeneralNames(GeneralName[] generalNameArr) {
        this.names = copy(generalNameArr);
    }

    private static GeneralName[] copy(GeneralName[] generalNameArr) {
        GeneralName[] generalNameArr2 = new GeneralName[generalNameArr.length];
        System.arraycopy(generalNameArr, 0, generalNameArr2, 0, generalNameArr.length);
        return generalNameArr2;
    }

    public static GeneralNames fromExtensions(Extensions extensions, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, aSN1ObjectIdentifier));
    }

    public static GeneralNames getInstance(Object obj) {
        if (obj instanceof GeneralNames) {
            return (GeneralNames) obj;
        }
        if (obj != null) {
            return new GeneralNames(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static GeneralNames getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return new GeneralNames(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public GeneralName[] getNames() {
        return copy(this.names);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.names);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("GeneralNames:");
        stringBuffer.append(lineSeparator);
        for (int r2 = 0; r2 != this.names.length; r2++) {
            stringBuffer.append("    ");
            stringBuffer.append(this.names[r2]);
            stringBuffer.append(lineSeparator);
        }
        return stringBuffer.toString();
    }
}
