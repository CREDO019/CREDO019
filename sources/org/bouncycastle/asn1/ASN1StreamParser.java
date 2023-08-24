package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;
    private final byte[][] tmpBuffers;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int r3) {
        this(inputStream, r3, new byte[11]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1StreamParser(InputStream inputStream, int r2, byte[][] bArr) {
        this._in = inputStream;
        this._limit = r2;
        this.tmpBuffers = bArr;
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    private void set00Check(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }

    ASN1Encodable implParseObject(int r9) throws IOException {
        set00Check(false);
        int readTagNumber = ASN1InputStream.readTagNumber(this._in, r9);
        int readLength = ASN1InputStream.readLength(this._in, this._limit, readTagNumber == 3 || readTagNumber == 4 || readTagNumber == 16 || readTagNumber == 17 || readTagNumber == 8);
        if (readLength < 0) {
            if ((r9 & 32) != 0) {
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this._in, this._limit), this._limit, this.tmpBuffers);
                int r92 = r9 & 192;
                return r92 != 0 ? 64 == r92 ? new BERApplicationSpecificParser(readTagNumber, aSN1StreamParser) : new BERTaggedObjectParser(r92, readTagNumber, aSN1StreamParser) : aSN1StreamParser.parseImplicitConstructedIL(readTagNumber);
            }
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, readLength, this._limit);
        if ((r9 & 224) == 0) {
            return parseImplicitPrimitive(readTagNumber, definiteLengthInputStream);
        }
        ASN1StreamParser aSN1StreamParser2 = new ASN1StreamParser(definiteLengthInputStream, definiteLengthInputStream.getLimit(), this.tmpBuffers);
        int r5 = r9 & 192;
        if (r5 != 0) {
            boolean z = (r9 & 32) != 0;
            return 64 == r5 ? (DLApplicationSpecific) aSN1StreamParser2.loadTaggedDL(r5, readTagNumber, z) : new DLTaggedObjectParser(r5, readTagNumber, z, aSN1StreamParser2);
        }
        return aSN1StreamParser2.parseImplicitConstructedDL(readTagNumber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive loadTaggedDL(int r1, int r2, boolean z) throws IOException {
        return !z ? ASN1TaggedObject.createPrimitive(r1, r2, ((DefiniteLengthInputStream) this._in).toByteArray()) : ASN1TaggedObject.createConstructedDL(r1, r2, readVector());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive loadTaggedIL(int r2, int r3) throws IOException {
        return ASN1TaggedObject.createConstructedIL(r2, r3, readVector());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitConstructedDL(int r4) throws IOException {
        if (r4 != 3) {
            if (r4 != 4) {
                if (r4 != 8) {
                    if (r4 != 16) {
                        if (r4 == 17) {
                            return new DLSetParser(this);
                        }
                        throw new ASN1Exception("unknown DL object encountered: 0x" + Integer.toHexString(r4));
                    }
                    return new DLSequenceParser(this);
                }
                return new DERExternalParser(this);
            }
            return new BEROctetStringParser(this);
        }
        return new BERBitStringParser(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitConstructedIL(int r4) throws IOException {
        if (r4 != 3) {
            if (r4 != 4) {
                if (r4 != 8) {
                    if (r4 != 16) {
                        if (r4 == 17) {
                            return new BERSetParser(this);
                        }
                        throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(r4));
                    }
                    return new BERSequenceParser(this);
                }
                return new DERExternalParser(this);
            }
            return new BEROctetStringParser(this);
        }
        return new BERBitStringParser(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable parseImplicitPrimitive(int r2) throws IOException {
        return parseImplicitPrimitive(r2, (DefiniteLengthInputStream) this._in);
    }

    ASN1Encodable parseImplicitPrimitive(int r2, DefiniteLengthInputStream definiteLengthInputStream) throws IOException {
        if (r2 != 3) {
            if (r2 != 4) {
                if (r2 != 8) {
                    if (r2 != 16) {
                        if (r2 != 17) {
                            try {
                                return ASN1InputStream.createPrimitiveDERObject(r2, definiteLengthInputStream, this.tmpBuffers);
                            } catch (IllegalArgumentException e) {
                                throw new ASN1Exception("corrupted stream detected", e);
                            }
                        }
                        throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
                    }
                    throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
                }
                throw new ASN1Exception("externals must use constructed encoding (see X.690 8.18)");
            }
            return new DEROctetStringParser(definiteLengthInputStream);
        }
        return new DLBitStringParser(definiteLengthInputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable parseObject(int r4) throws IOException {
        if (r4 < 0 || r4 > 30) {
            throw new IllegalArgumentException("invalid universal tag number: " + r4);
        }
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        if ((read & (-33)) == r4) {
            return implParseObject(read);
        }
        throw new IOException("unexpected identifier encountered: " + read);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1TaggedObjectParser parseTaggedObject() throws IOException {
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        if ((read & 192) != 0) {
            return (ASN1TaggedObjectParser) implParseObject(read);
        }
        throw new ASN1Exception("no tagged object found");
    }

    public ASN1Encodable readObject() throws IOException {
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        return implParseObject(read);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1EncodableVector readVector() throws IOException {
        int read = this._in.read();
        if (read < 0) {
            return new ASN1EncodableVector(0);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        do {
            ASN1Encodable implParseObject = implParseObject(read);
            aSN1EncodableVector.add(implParseObject instanceof InMemoryRepresentable ? ((InMemoryRepresentable) implParseObject).getLoadedObject() : implParseObject.toASN1Primitive());
            read = this._in.read();
        } while (read >= 0);
        return aSN1EncodableVector;
    }
}
