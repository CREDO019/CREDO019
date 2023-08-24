package org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public abstract class DERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    /* JADX INFO: Access modifiers changed from: protected */
    public DERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    public DERGenerator(OutputStream outputStream, int r2, boolean z) {
        super(outputStream);
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = r2;
    }

    private void writeLength(OutputStream outputStream, int r5) throws IOException {
        if (r5 <= 127) {
            outputStream.write((byte) r5);
            return;
        }
        int r1 = r5;
        int r2 = 1;
        while (true) {
            r1 >>>= 8;
            if (r1 == 0) {
                break;
            }
            r2++;
        }
        outputStream.write((byte) (r2 | 128));
        for (int r22 = (r2 - 1) * 8; r22 >= 0; r22 -= 8) {
            outputStream.write((byte) (r5 >> r22));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeDEREncoded(int r4, byte[] bArr) throws IOException {
        if (!this._tagged) {
            writeDEREncoded(this._out, r4, bArr);
            return;
        }
        int r0 = this._tagNo;
        int r1 = r0 | 128;
        if (this._isExplicit) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            writeDEREncoded(byteArrayOutputStream, r4, bArr);
            writeDEREncoded(this._out, r0 | 32 | 128, byteArrayOutputStream.toByteArray());
        } else if ((r4 & 32) != 0) {
            writeDEREncoded(this._out, r1 | 32, bArr);
        } else {
            writeDEREncoded(this._out, r1, bArr);
        }
    }

    void writeDEREncoded(OutputStream outputStream, int r2, byte[] bArr) throws IOException {
        outputStream.write(r2);
        writeLength(outputStream, bArr.length);
        outputStream.write(bArr);
    }
}
