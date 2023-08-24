package org.bouncycastle.asn1;

import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public abstract class ASN1BitString extends ASN1Primitive implements ASN1String, ASN1BitStringParser {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1BitString.class, 3) { // from class: org.bouncycastle.asn1.ASN1BitString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive fromImplicitConstructed(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.toASN1BitString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1BitString.createPrimitive(dEROctetString.getOctets());
        }
    };
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    final byte[] contents;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString(byte b, int r4) {
        if (r4 > 7 || r4 < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.contents = new byte[]{(byte) r4, b};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString(byte[] bArr, int r3) {
        Objects.requireNonNull(bArr, "'data' cannot be null");
        if (bArr.length == 0 && r3 != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        }
        if (r3 > 7 || r3 < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.contents = Arrays.prepend(bArr, (byte) r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BitString(byte[] bArr, boolean z) {
        if (z) {
            Objects.requireNonNull(bArr, "'contents' cannot be null");
            if (bArr.length < 1) {
                throw new IllegalArgumentException("'contents' cannot be empty");
            }
            int r4 = bArr[0] & 255;
            if (r4 > 0) {
                if (bArr.length < 2) {
                    throw new IllegalArgumentException("zero length data with non-zero pad bits");
                }
                if (r4 > 7) {
                    throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
                }
            }
        }
        this.contents = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1BitString createPrimitive(byte[] bArr) {
        int length = bArr.length;
        if (length >= 1) {
            int r3 = bArr[0] & 255;
            if (r3 > 0) {
                if (r3 > 7 || length < 2) {
                    throw new IllegalArgumentException("invalid pad bits detected");
                }
                byte b = bArr[length - 1];
                if (b != ((byte) ((255 << r3) & b))) {
                    return new DLBitString(bArr, false);
                }
            }
            return new DERBitString(bArr, false);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] getBytes(int r5) {
        if (r5 == 0) {
            return new byte[0];
        }
        int r1 = 4;
        for (int r2 = 3; r2 >= 1 && ((255 << (r2 * 8)) & r5) == 0; r2--) {
            r1--;
        }
        byte[] bArr = new byte[r1];
        for (int r0 = 0; r0 < r1; r0++) {
            bArr[r0] = (byte) ((r5 >> (r0 * 8)) & 255);
        }
        return bArr;
    }

    public static ASN1BitString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1BitString)) {
            return (ASN1BitString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1BitString) {
                return (ASN1BitString) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1BitString) TYPE.fromByteArray((byte[]) obj);
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct BIT STRING from byte[]: " + e.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1BitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1BitString) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int getPadBits(int r3) {
        int r32;
        int r0 = 3;
        while (true) {
            if (r0 < 0) {
                r32 = 0;
                break;
            } else if (r0 != 0) {
                int r2 = r3 >> (r0 * 8);
                if (r2 != 0) {
                    r32 = r2 & 255;
                    break;
                }
                r0--;
            } else if (r3 != 0) {
                r32 = r3 & 255;
                break;
            } else {
                r0--;
            }
        }
        if (r32 == 0) {
            return 0;
        }
        int r1 = 1;
        while (true) {
            r32 <<= 1;
            if ((r32 & 255) == 0) {
                return 8 - r1;
            }
            r1++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1BitString) {
            byte[] bArr = this.contents;
            byte[] bArr2 = ((ASN1BitString) aSN1Primitive).contents;
            int length = bArr.length;
            if (bArr2.length != length) {
                return false;
            }
            if (length == 1) {
                return true;
            }
            int r2 = length - 1;
            for (int r4 = 0; r4 < r2; r4++) {
                if (bArr[r4] != bArr2[r4]) {
                    return false;
                }
            }
            int r42 = 255 << (bArr[0] & 255);
            return ((byte) (bArr[r2] & r42)) == ((byte) (bArr2[r2] & r42));
        }
        return false;
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public InputStream getBitStream() throws IOException {
        byte[] bArr = this.contents;
        return new ByteArrayInputStream(bArr, 1, bArr.length - 1);
    }

    public byte[] getBytes() {
        byte[] bArr = this.contents;
        if (bArr.length == 1) {
            return ASN1OctetString.EMPTY_OCTETS;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 1, bArr.length);
        int length = copyOfRange.length - 1;
        copyOfRange[length] = (byte) (((byte) (255 << (bArr[0] & 255))) & copyOfRange[length]);
        return copyOfRange;
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public InputStream getOctetStream() throws IOException {
        int r0 = this.contents[0] & 255;
        if (r0 == 0) {
            return getBitStream();
        }
        throw new IOException("expected octet-aligned bitstring, but found padBits: " + r0);
    }

    public byte[] getOctets() {
        byte[] bArr = this.contents;
        if (bArr[0] == 0) {
            return Arrays.copyOfRange(bArr, 1, bArr.length);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public int getPadBits() {
        return this.contents[0] & 255;
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public String getString() {
        try {
            byte[] encoded = getEncoded();
            StringBuffer stringBuffer = new StringBuffer((encoded.length * 2) + 1);
            stringBuffer.append('#');
            for (int r2 = 0; r2 != encoded.length; r2++) {
                byte b = encoded[r2];
                char[] cArr = table;
                stringBuffer.append(cArr[(b >>> 4) & 15]);
                stringBuffer.append(cArr[b & Ascii.f1128SI]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        byte[] bArr = this.contents;
        if (bArr.length < 2) {
            return 1;
        }
        int length = bArr.length - 1;
        return (Arrays.hashCode(bArr, 0, length) * 257) ^ ((byte) (bArr[length] & (255 << (bArr[0] & 255))));
    }

    public int intValue() {
        int min = Math.min(5, this.contents.length - 1);
        int r5 = 0;
        for (int r4 = 1; r4 < min; r4++) {
            r5 |= (255 & this.contents[r4]) << ((r4 - 1) * 8);
        }
        if (1 > min || min >= 5) {
            return r5;
        }
        byte[] bArr = this.contents;
        return r5 | ((((byte) (bArr[min] & (255 << (bArr[0] & 255)))) & 255) << ((min - 1) * 8));
    }

    public ASN1BitStringParser parser() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERBitString(this.contents, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return new DLBitString(this.contents, false);
    }

    public String toString() {
        return getString();
    }
}
