package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public abstract class BERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    /* JADX INFO: Access modifiers changed from: protected */
    public BERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BERGenerator(OutputStream outputStream, int r2, boolean z) {
        super(outputStream);
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = r2;
    }

    private void writeHdr(int r2) throws IOException {
        this._out.write(r2);
        this._out.write(128);
    }

    @Override // org.bouncycastle.asn1.ASN1Generator
    public OutputStream getRawOutputStream() {
        return this._out;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeBEREnd() throws IOException {
        this._out.write(0);
        this._out.write(0);
        if (this._tagged && this._isExplicit) {
            this._out.write(0);
            this._out.write(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeBERHeader(int r3) throws IOException {
        if (this._tagged) {
            int r0 = this._tagNo | 128;
            if (this._isExplicit) {
                writeHdr(r0 | 32);
            } else if ((r3 & 32) == 0) {
                writeHdr(r0);
                return;
            } else {
                r3 = r0 | 32;
            }
        }
        writeHdr(r3);
    }
}
