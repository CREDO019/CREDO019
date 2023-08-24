package org.bouncycastle.pqc.crypto.sphincsplus;

/* loaded from: classes3.dex */
class SIG {

    /* renamed from: r */
    private final byte[] f2501r;
    private final SIG_FORS[] sig_fors;
    private final SIG_XMSS[] sig_ht;

    public SIG(int r8, int r9, int r10, int r11, int r12, int r13, byte[] bArr) {
        byte[] bArr2 = new byte[r8];
        this.f2501r = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, r8);
        this.sig_fors = new SIG_FORS[r9];
        int r2 = r8;
        for (int r0 = 0; r0 != r9; r0++) {
            byte[] bArr3 = new byte[r8];
            System.arraycopy(bArr, r2, bArr3, 0, r8);
            r2 += r8;
            byte[][] bArr4 = new byte[r10];
            for (int r5 = 0; r5 != r10; r5++) {
                bArr4[r5] = new byte[r8];
                System.arraycopy(bArr, r2, bArr4[r5], 0, r8);
                r2 += r8;
            }
            this.sig_fors[r0] = new SIG_FORS(bArr3, bArr4);
        }
        this.sig_ht = new SIG_XMSS[r11];
        for (int r92 = 0; r92 != r11; r92++) {
            int r102 = r13 * r8;
            byte[] bArr5 = new byte[r102];
            System.arraycopy(bArr, r2, bArr5, 0, r102);
            r2 += r102;
            byte[][] bArr6 = new byte[r12];
            for (int r3 = 0; r3 != r12; r3++) {
                bArr6[r3] = new byte[r8];
                System.arraycopy(bArr, r2, bArr6[r3], 0, r8);
                r2 += r8;
            }
            this.sig_ht[r92] = new SIG_XMSS(bArr5, bArr6);
        }
        if (r2 != bArr.length) {
            throw new IllegalArgumentException("signature wrong length");
        }
    }

    public byte[] getR() {
        return this.f2501r;
    }

    public SIG_FORS[] getSIG_FORS() {
        return this.sig_fors;
    }

    public SIG_XMSS[] getSIG_HT() {
        return this.sig_ht;
    }
}
