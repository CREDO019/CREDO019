package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.util.IPAddress;

/* loaded from: classes5.dex */
public class GeneralName extends ASN1Object implements ASN1Choice {
    public static final int dNSName = 2;
    public static final int directoryName = 4;
    public static final int ediPartyName = 5;
    public static final int iPAddress = 7;
    public static final int otherName = 0;
    public static final int registeredID = 8;
    public static final int rfc822Name = 1;
    public static final int uniformResourceIdentifier = 6;
    public static final int x400Address = 3;
    private ASN1Encodable obj;
    private int tag;

    public GeneralName(int r3, String str) {
        ASN1Encodable dERIA5String;
        this.tag = r3;
        if (r3 == 1 || r3 == 2 || r3 == 6) {
            dERIA5String = new DERIA5String(str);
        } else if (r3 == 8) {
            dERIA5String = new ASN1ObjectIdentifier(str);
        } else if (r3 != 4) {
            if (r3 != 7) {
                throw new IllegalArgumentException("can't process String for tag: " + r3);
            }
            byte[] generalNameEncoding = toGeneralNameEncoding(str);
            if (generalNameEncoding == null) {
                throw new IllegalArgumentException("IP Address is invalid");
            }
            this.obj = new DEROctetString(generalNameEncoding);
            return;
        } else {
            dERIA5String = new X500Name(str);
        }
        this.obj = dERIA5String;
    }

    public GeneralName(int r1, ASN1Encodable aSN1Encodable) {
        this.obj = aSN1Encodable;
        this.tag = r1;
    }

    public GeneralName(X500Name x500Name) {
        this.obj = x500Name;
        this.tag = 4;
    }

    public GeneralName(X509Name x509Name) {
        this.obj = X500Name.getInstance(x509Name);
        this.tag = 4;
    }

    private void copyInts(int[] r5, byte[] bArr, int r7) {
        for (int r0 = 0; r0 != r5.length; r0++) {
            int r1 = r0 * 2;
            bArr[r1 + r7] = (byte) (r5[r0] >> 8);
            bArr[r1 + 1 + r7] = (byte) r5[r0];
        }
    }

    public static GeneralName getInstance(Object obj) {
        if (obj == null || (obj instanceof GeneralName)) {
            return (GeneralName) obj;
        }
        if (!(obj instanceof ASN1TaggedObject)) {
            if (obj instanceof byte[]) {
                try {
                    return getInstance(ASN1Primitive.fromByteArray((byte[]) obj));
                } catch (IOException unused) {
                    throw new IllegalArgumentException("unable to parse encoded general name");
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
        int tagNo = aSN1TaggedObject.getTagNo();
        switch (tagNo) {
            case 0:
            case 3:
            case 5:
                return new GeneralName(tagNo, ASN1Sequence.getInstance(aSN1TaggedObject, false));
            case 1:
            case 2:
            case 6:
                return new GeneralName(tagNo, ASN1IA5String.getInstance(aSN1TaggedObject, false));
            case 4:
                return new GeneralName(tagNo, X500Name.getInstance(aSN1TaggedObject, true));
            case 7:
                return new GeneralName(tagNo, ASN1OctetString.getInstance(aSN1TaggedObject, false));
            case 8:
                return new GeneralName(tagNo, ASN1ObjectIdentifier.getInstance(aSN1TaggedObject, false));
            default:
                throw new IllegalArgumentException("unknown tag: " + tagNo);
        }
    }

    public static GeneralName getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1TaggedObject.getInstance(aSN1TaggedObject, true));
    }

    private void parseIPv4(String str, byte[] bArr, int r6) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "./");
        int r4 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            bArr[r4 + r6] = (byte) Integer.parseInt(stringTokenizer.nextToken());
            r4++;
        }
    }

    private void parseIPv4Mask(String str, byte[] bArr, int r8) {
        int parseInt = Integer.parseInt(str);
        for (int r0 = 0; r0 != parseInt; r0++) {
            int r1 = (r0 / 8) + r8;
            bArr[r1] = (byte) (bArr[r1] | (1 << (7 - (r0 % 8))));
        }
    }

    private int[] parseIPv6(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ParameterizedMessage.ERROR_MSG_SEPARATOR, true);
        int[] r4 = new int[8];
        if (str.charAt(0) == ':' && str.charAt(1) == ':') {
            stringTokenizer.nextToken();
        }
        int r12 = -1;
        int r2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(ParameterizedMessage.ERROR_MSG_SEPARATOR)) {
                r4[r2] = 0;
                int r10 = r2;
                r2++;
                r12 = r10;
            } else if (nextToken.indexOf(46) < 0) {
                int r7 = r2 + 1;
                r4[r2] = Integer.parseInt(nextToken, 16);
                if (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                }
                r2 = r7;
            } else {
                StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, ".");
                int r6 = r2 + 1;
                r4[r2] = (Integer.parseInt(stringTokenizer2.nextToken()) << 8) | Integer.parseInt(stringTokenizer2.nextToken());
                r2 = r6 + 1;
                r4[r6] = Integer.parseInt(stringTokenizer2.nextToken()) | (Integer.parseInt(stringTokenizer2.nextToken()) << 8);
            }
        }
        if (r2 != 8) {
            int r22 = r2 - r12;
            int r0 = 8 - r22;
            System.arraycopy(r4, r12, r4, r0, r22);
            while (r12 != r0) {
                r4[r12] = 0;
                r12++;
            }
        }
        return r4;
    }

    private int[] parseMask(String str) {
        int[] r0 = new int[8];
        int parseInt = Integer.parseInt(str);
        for (int r1 = 0; r1 != parseInt; r1++) {
            int r2 = r1 / 16;
            r0[r2] = r0[r2] | (1 << (15 - (r1 % 16)));
        }
        return r0;
    }

    private byte[] toGeneralNameEncoding(String str) {
        if (IPAddress.isValidIPv6WithNetmask(str) || IPAddress.isValidIPv6(str)) {
            int indexOf = str.indexOf(47);
            if (indexOf < 0) {
                byte[] bArr = new byte[16];
                copyInts(parseIPv6(str), bArr, 0);
                return bArr;
            }
            byte[] bArr2 = new byte[32];
            copyInts(parseIPv6(str.substring(0, indexOf)), bArr2, 0);
            String substring = str.substring(indexOf + 1);
            copyInts(substring.indexOf(58) > 0 ? parseIPv6(substring) : parseMask(substring), bArr2, 16);
            return bArr2;
        } else if (IPAddress.isValidIPv4WithNetmask(str) || IPAddress.isValidIPv4(str)) {
            int indexOf2 = str.indexOf(47);
            if (indexOf2 < 0) {
                byte[] bArr3 = new byte[4];
                parseIPv4(str, bArr3, 0);
                return bArr3;
            }
            byte[] bArr4 = new byte[8];
            parseIPv4(str.substring(0, indexOf2), bArr4, 0);
            String substring2 = str.substring(indexOf2 + 1);
            if (substring2.indexOf(46) > 0) {
                parseIPv4(substring2, bArr4, 4);
            } else {
                parseIPv4Mask(substring2, bArr4, 4);
            }
            return bArr4;
        } else {
            return null;
        }
    }

    public ASN1Encodable getName() {
        return this.obj;
    }

    public int getTagNo() {
        return this.tag;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        int r0 = this.tag;
        return new DERTaggedObject(r0 == 4, r0, this.obj);
    }

    public String toString() {
        String string;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.tag);
        stringBuffer.append(": ");
        int r1 = this.tag;
        if (r1 != 1 && r1 != 2) {
            if (r1 == 4) {
                string = X500Name.getInstance(this.obj).toString();
            } else if (r1 != 6) {
                string = this.obj.toString();
            }
            stringBuffer.append(string);
            return stringBuffer.toString();
        }
        string = ASN1IA5String.getInstance(this.obj).getString();
        stringBuffer.append(string);
        return stringBuffer.toString();
    }
}
