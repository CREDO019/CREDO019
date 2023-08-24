package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class ConstructedOctetStream extends InputStream {
    private InputStream _currentStream;
    private boolean _first = true;
    private final ASN1StreamParser _parser;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstructedOctetStream(ASN1StreamParser aSN1StreamParser) {
        this._parser = aSN1StreamParser;
    }

    private ASN1OctetStringParser getNextParser() throws IOException {
        ASN1Encodable readObject = this._parser.readObject();
        if (readObject == null) {
            return null;
        }
        if (readObject instanceof ASN1OctetStringParser) {
            return (ASN1OctetStringParser) readObject;
        }
        throw new IOException("unknown object encountered: " + readObject.getClass());
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        ASN1OctetStringParser nextParser;
        if (this._currentStream == null) {
            if (!this._first || (nextParser = getNextParser()) == null) {
                return -1;
            }
            this._first = false;
            this._currentStream = nextParser.getOctetStream();
        }
        while (true) {
            int read = this._currentStream.read();
            if (read >= 0) {
                return read;
            }
            ASN1OctetStringParser nextParser2 = getNextParser();
            if (nextParser2 == null) {
                this._currentStream = null;
                return -1;
            }
            this._currentStream = nextParser2.getOctetStream();
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int r7, int r8) throws IOException {
        ASN1OctetStringParser nextParser;
        int r1 = 0;
        if (this._currentStream == null) {
            if (!this._first || (nextParser = getNextParser()) == null) {
                return -1;
            }
            this._first = false;
            this._currentStream = nextParser.getOctetStream();
        }
        while (true) {
            int read = this._currentStream.read(bArr, r7 + r1, r8 - r1);
            if (read >= 0) {
                r1 += read;
                if (r1 == r8) {
                    return r1;
                }
            } else {
                ASN1OctetStringParser nextParser2 = getNextParser();
                if (nextParser2 == null) {
                    this._currentStream = null;
                    if (r1 < 1) {
                        return -1;
                    }
                    return r1;
                }
                this._currentStream = nextParser2.getOctetStream();
            }
        }
    }
}
