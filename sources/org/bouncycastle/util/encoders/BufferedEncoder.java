package org.bouncycastle.util.encoders;

/* loaded from: classes4.dex */
public class BufferedEncoder {
    protected byte[] buf;
    protected int bufOff;
    protected Translator translator;

    public BufferedEncoder(Translator translator, int r2) {
        this.translator = translator;
        if (r2 % translator.getEncodedBlockSize() != 0) {
            throw new IllegalArgumentException("buffer size not multiple of input block size");
        }
        this.buf = new byte[r2];
        this.bufOff = 0;
    }

    public int processByte(byte b, byte[] bArr, int r10) {
        byte[] bArr2 = this.buf;
        int r0 = this.bufOff;
        int r2 = r0 + 1;
        this.bufOff = r2;
        bArr2[r0] = b;
        if (r2 == bArr2.length) {
            int encode = this.translator.encode(bArr2, 0, bArr2.length, bArr, r10);
            this.bufOff = 0;
            return encode;
        }
        return 0;
    }

    public int processBytes(byte[] bArr, int r12, int r13, byte[] bArr2, int r15) {
        if (r13 >= 0) {
            byte[] bArr3 = this.buf;
            int length = bArr3.length;
            int r2 = this.bufOff;
            int r1 = length - r2;
            int r3 = 0;
            if (r13 > r1) {
                System.arraycopy(bArr, r12, bArr3, r2, r1);
                Translator translator = this.translator;
                byte[] bArr4 = this.buf;
                int encode = translator.encode(bArr4, 0, bArr4.length, bArr2, r15) + 0;
                this.bufOff = 0;
                int r132 = r13 - r1;
                int r122 = r12 + r1;
                int r7 = r15 + encode;
                int length2 = r132 - (r132 % this.buf.length);
                r3 = encode + this.translator.encode(bArr, r122, length2, bArr2, r7);
                r13 = r132 - length2;
                r12 = r122 + length2;
            }
            if (r13 != 0) {
                System.arraycopy(bArr, r12, this.buf, this.bufOff, r13);
                this.bufOff += r13;
            }
            return r3;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
