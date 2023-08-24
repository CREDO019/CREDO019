package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.p041io.Streams;

/* loaded from: classes5.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final boolean lazyEvaluate;
    private final int limit;
    private final byte[][] tmpBuffers;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1InputStream(InputStream inputStream, int r3) {
        this(inputStream, r3, false);
    }

    public ASN1InputStream(InputStream inputStream, int r3, boolean z) {
        this(inputStream, r3, z, new byte[11]);
    }

    private ASN1InputStream(InputStream inputStream, int r2, boolean z, byte[][] bArr) {
        super(inputStream);
        this.limit = r2;
        this.lazyEvaluate = z;
        this.tmpBuffers = bArr;
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.findLimit(inputStream), z);
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive createPrimitiveDERObject(int r1, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        switch (r1) {
            case 1:
                return ASN1Boolean.createPrimitive(getBuffer(definiteLengthInputStream, bArr));
            case 2:
                return ASN1Integer.createPrimitive(definiteLengthInputStream.toByteArray());
            case 3:
                return ASN1BitString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 4:
                return ASN1OctetString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 5:
                return ASN1Null.createPrimitive(definiteLengthInputStream.toByteArray());
            case 6:
                return ASN1ObjectIdentifier.createPrimitive(getBuffer(definiteLengthInputStream, bArr), true);
            case 7:
                return ASN1ObjectDescriptor.createPrimitive(definiteLengthInputStream.toByteArray());
            case 8:
            case 9:
            case 11:
            case 14:
            case 15:
            case 16:
            case 17:
            case 29:
            default:
                throw new IOException("unknown tag " + r1 + " encountered");
            case 10:
                return ASN1Enumerated.createPrimitive(getBuffer(definiteLengthInputStream, bArr), true);
            case 12:
                return ASN1UTF8String.createPrimitive(definiteLengthInputStream.toByteArray());
            case 13:
                return ASN1RelativeOID.createPrimitive(definiteLengthInputStream.toByteArray(), false);
            case 18:
                return ASN1NumericString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 19:
                return ASN1PrintableString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 20:
                return ASN1T61String.createPrimitive(definiteLengthInputStream.toByteArray());
            case 21:
                return ASN1VideotexString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 22:
                return ASN1IA5String.createPrimitive(definiteLengthInputStream.toByteArray());
            case 23:
                return ASN1UTCTime.createPrimitive(definiteLengthInputStream.toByteArray());
            case 24:
                return ASN1GeneralizedTime.createPrimitive(definiteLengthInputStream.toByteArray());
            case 25:
                return ASN1GraphicString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 26:
                return ASN1VisibleString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 27:
                return ASN1GeneralString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 28:
                return ASN1UniversalString.createPrimitive(definiteLengthInputStream.toByteArray());
            case 30:
                return ASN1BMPString.createPrimitive(getBMPCharBuffer(definiteLengthInputStream));
        }
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int r9;
        int remaining = definiteLengthInputStream.getRemaining();
        if ((remaining & 1) == 0) {
            int r1 = remaining / 2;
            char[] cArr = new char[r1];
            byte[] bArr = new byte[8];
            int r5 = 0;
            int r6 = 0;
            while (remaining >= 8) {
                if (Streams.readFully(definiteLengthInputStream, bArr, 0, 8) != 8) {
                    throw new EOFException("EOF encountered in middle of BMPString");
                }
                cArr[r6] = (char) ((bArr[0] << 8) | (bArr[1] & 255));
                cArr[r6 + 1] = (char) ((bArr[2] << 8) | (bArr[3] & 255));
                cArr[r6 + 2] = (char) ((bArr[4] << 8) | (bArr[5] & 255));
                cArr[r6 + 3] = (char) ((bArr[6] << 8) | (bArr[7] & 255));
                r6 += 4;
                remaining -= 8;
            }
            if (remaining > 0) {
                if (Streams.readFully(definiteLengthInputStream, bArr, 0, remaining) != remaining) {
                    throw new EOFException("EOF encountered in middle of BMPString");
                }
                while (true) {
                    int r7 = r5 + 1;
                    int r8 = r7 + 1;
                    r9 = r6 + 1;
                    cArr[r6] = (char) ((bArr[r5] << 8) | (bArr[r7] & 255));
                    if (r8 >= remaining) {
                        break;
                    }
                    r5 = r8;
                    r6 = r9;
                }
                r6 = r9;
            }
            if (definiteLengthInputStream.getRemaining() == 0 && r1 == r6) {
                return cArr;
            }
            throw new IllegalStateException();
        }
        throw new IOException("malformed BMPString encoding encountered");
    }

    private static byte[] getBuffer(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) throws IOException {
        int remaining = definiteLengthInputStream.getRemaining();
        if (remaining >= bArr.length) {
            return definiteLengthInputStream.toByteArray();
        }
        byte[] bArr2 = bArr[remaining];
        if (bArr2 == null) {
            bArr2 = new byte[remaining];
            bArr[remaining] = bArr2;
        }
        definiteLengthInputStream.readAllIntoByteArray(bArr2);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readLength(InputStream inputStream, int r6, boolean z) throws IOException {
        int read = inputStream.read();
        if ((read >>> 7) == 0) {
            return read;
        }
        if (128 == read) {
            return -1;
        }
        if (read >= 0) {
            if (255 != read) {
                int r0 = read & 127;
                int r1 = 0;
                int r2 = 0;
                do {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found reading length");
                    }
                    if ((r1 >>> 23) != 0) {
                        throw new IOException("long form definite-length more than 31 bits");
                    }
                    r1 = (r1 << 8) + read2;
                    r2++;
                } while (r2 < r0);
                if (r1 < r6 || z) {
                    return r1;
                }
                throw new IOException("corrupted stream - out of bounds length found: " + r1 + " >= " + r6);
            }
            throw new IOException("invalid long form definite-length 0xFF");
        }
        throw new EOFException("EOF found when length expected");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int readTagNumber(InputStream inputStream, int r4) throws IOException {
        int r42 = r4 & 31;
        if (r42 == 31) {
            int r43 = 0;
            int read = inputStream.read();
            if (read < 31) {
                if (read < 0) {
                    throw new EOFException("EOF found inside tag value.");
                }
                throw new IOException("corrupted stream - high tag number < 31 found");
            } else if ((read & 127) != 0) {
                while ((read & 128) != 0) {
                    if ((r43 >>> 24) != 0) {
                        throw new IOException("Tag number more than 31 bits");
                    }
                    r43 = (r43 | (read & 127)) << 7;
                    read = inputStream.read();
                    if (read < 0) {
                        throw new EOFException("EOF found inside tag value.");
                    }
                }
                return r43 | (read & 127);
            } else {
                throw new IOException("corrupted stream - invalid high tag number found");
            }
        }
        return r42;
    }

    ASN1BitString buildConstructedBitString(ASN1EncodableVector aSN1EncodableVector) throws IOException {
        int size = aSN1EncodableVector.size();
        ASN1BitString[] aSN1BitStringArr = new ASN1BitString[size];
        for (int r2 = 0; r2 != size; r2++) {
            ASN1Encodable aSN1Encodable = aSN1EncodableVector.get(r2);
            if (!(aSN1Encodable instanceof ASN1BitString)) {
                throw new ASN1Exception("unknown object encountered in constructed BIT STRING: " + aSN1Encodable.getClass());
            }
            aSN1BitStringArr[r2] = (ASN1BitString) aSN1Encodable;
        }
        return new BERBitString(aSN1BitStringArr);
    }

    ASN1OctetString buildConstructedOctetString(ASN1EncodableVector aSN1EncodableVector) throws IOException {
        int size = aSN1EncodableVector.size();
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
        for (int r2 = 0; r2 != size; r2++) {
            ASN1Encodable aSN1Encodable = aSN1EncodableVector.get(r2);
            if (!(aSN1Encodable instanceof ASN1OctetString)) {
                throw new ASN1Exception("unknown object encountered in constructed OCTET STRING: " + aSN1Encodable.getClass());
            }
            aSN1OctetStringArr[r2] = (ASN1OctetString) aSN1Encodable;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }

    protected ASN1Primitive buildObject(int r3, int r4, int r5) throws IOException {
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, r5, this.limit);
        if ((r3 & 224) == 0) {
            return createPrimitiveDERObject(r4, definiteLengthInputStream, this.tmpBuffers);
        }
        int r52 = r3 & 192;
        if (r52 != 0) {
            return readTaggedObjectDL(r52, r4, (r3 & 32) != 0, definiteLengthInputStream);
        } else if (r4 != 3) {
            if (r4 != 4) {
                if (r4 != 8) {
                    if (r4 == 16) {
                        return definiteLengthInputStream.getRemaining() < 1 ? DLFactory.EMPTY_SEQUENCE : this.lazyEvaluate ? new LazyEncodedSequence(definiteLengthInputStream.toByteArray()) : DLFactory.createSequence(readVector(definiteLengthInputStream));
                    } else if (r4 == 17) {
                        return DLFactory.createSet(readVector(definiteLengthInputStream));
                    } else {
                        throw new IOException("unknown tag " + r4 + " encountered");
                    }
                }
                return DLFactory.createSequence(readVector(definiteLengthInputStream)).toASN1External();
            }
            return buildConstructedOctetString(readVector(definiteLengthInputStream));
        } else {
            return buildConstructedBitString(readVector(definiteLengthInputStream));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getLimit() {
        return this.limit;
    }

    protected void readFully(byte[] bArr) throws IOException {
        if (Streams.readFully(this, bArr, 0, bArr.length) != bArr.length) {
            throw new EOFException("EOF encountered in middle of object");
        }
    }

    protected int readLength() throws IOException {
        return readLength(this, this.limit, false);
    }

    public ASN1Primitive readObject() throws IOException {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int readTagNumber = readTagNumber(this, read);
        int readLength = readLength();
        if (readLength >= 0) {
            try {
                return buildObject(read, readTagNumber, readLength);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        } else if ((read & 32) != 0) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit, this.tmpBuffers);
            int r0 = read & 192;
            if (r0 != 0) {
                return aSN1StreamParser.loadTaggedIL(r0, readTagNumber);
            }
            if (readTagNumber != 3) {
                if (readTagNumber != 4) {
                    if (readTagNumber != 8) {
                        if (readTagNumber != 16) {
                            if (readTagNumber == 17) {
                                return BERSetParser.parse(aSN1StreamParser);
                            }
                            throw new IOException("unknown BER object encountered");
                        }
                        return BERSequenceParser.parse(aSN1StreamParser);
                    }
                    return DERExternalParser.parse(aSN1StreamParser);
                }
                return BEROctetStringParser.parse(aSN1StreamParser);
            }
            return BERBitStringParser.parse(aSN1StreamParser);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }

    ASN1Primitive readTaggedObjectDL(int r1, int r2, boolean z, DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        return !z ? ASN1TaggedObject.createPrimitive(r1, r2, definiteLengthInputStream.toByteArray()) : ASN1TaggedObject.createConstructedDL(r1, r2, readVector(definiteLengthInputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1EncodableVector readVector() throws IOException {
        ASN1Primitive readObject = readObject();
        if (readObject == null) {
            return new ASN1EncodableVector(0);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        do {
            aSN1EncodableVector.add(readObject);
            readObject = readObject();
        } while (readObject != null);
        return aSN1EncodableVector;
    }

    ASN1EncodableVector readVector(DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        int remaining = definiteLengthInputStream.getRemaining();
        return remaining < 1 ? new ASN1EncodableVector(0) : new ASN1InputStream(definiteLengthInputStream, remaining, this.lazyEvaluate, this.tmpBuffers).readVector();
    }
}
