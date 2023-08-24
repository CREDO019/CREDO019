package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes5.dex */
public class BEROctetStringGenerator extends BERGenerator {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class BufferedBEROctetStream extends OutputStream {
        private byte[] _buf;
        private DEROutputStream _derOut;
        private int _off = 0;

        BufferedBEROctetStream(byte[] bArr) {
            this._buf = bArr;
            this._derOut = new DEROutputStream(BEROctetStringGenerator.this._out);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            int r0 = this._off;
            if (r0 != 0) {
                DEROctetString.encode(this._derOut, true, this._buf, 0, r0);
            }
            this._derOut.flushInternal();
            BEROctetStringGenerator.this.writeBEREnd();
        }

        @Override // java.io.OutputStream
        public void write(int r5) throws IOException {
            byte[] bArr = this._buf;
            int r1 = this._off;
            int r2 = r1 + 1;
            this._off = r2;
            bArr[r1] = (byte) r5;
            if (r2 == bArr.length) {
                DEROctetString.encode(this._derOut, true, bArr, 0, bArr.length);
                this._off = 0;
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int r8, int r9) throws IOException {
            int r3;
            byte[] bArr2 = this._buf;
            int length = bArr2.length;
            int r2 = this._off;
            int r32 = length - r2;
            if (r9 < r32) {
                System.arraycopy(bArr, r8, bArr2, r2, r9);
                this._off += r9;
                return;
            }
            if (r2 > 0) {
                System.arraycopy(bArr, r8, bArr2, r2, r32);
                r3 = r32 + 0;
                DEROctetString.encode(this._derOut, true, this._buf, 0, length);
            } else {
                r3 = 0;
            }
            while (true) {
                int r0 = r9 - r3;
                if (r0 < length) {
                    System.arraycopy(bArr, r8 + r3, this._buf, 0, r0);
                    this._off = r0;
                    return;
                }
                DEROctetString.encode(this._derOut, true, bArr, r8 + r3, length);
                r3 += length;
            }
        }
    }

    public BEROctetStringGenerator(OutputStream outputStream) throws IOException {
        super(outputStream);
        writeBERHeader(36);
    }

    public BEROctetStringGenerator(OutputStream outputStream, int r2, boolean z) throws IOException {
        super(outputStream, r2, z);
        writeBERHeader(36);
    }

    public OutputStream getOctetOutputStream() {
        return getOctetOutputStream(new byte[1000]);
    }

    public OutputStream getOctetOutputStream(byte[] bArr) {
        return new BufferedBEROctetStream(bArr);
    }
}
