package org.bouncycastle.asn1;

/* loaded from: classes5.dex */
public class DERVideotexString extends ASN1VideotexString {
    public DERVideotexString(byte[] bArr) {
        this(bArr, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DERVideotexString(byte[] bArr, boolean z) {
        super(bArr, z);
    }

    public static DERVideotexString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERVideotexString)) {
            return (DERVideotexString) obj;
        }
        if (obj instanceof ASN1VideotexString) {
            return new DERVideotexString(((ASN1VideotexString) obj).contents, false);
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (DERVideotexString) fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static DERVideotexString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        return (z || (object instanceof DERVideotexString)) ? getInstance((Object) object) : new DERVideotexString(ASN1OctetString.getInstance(object).getOctets());
    }
}
