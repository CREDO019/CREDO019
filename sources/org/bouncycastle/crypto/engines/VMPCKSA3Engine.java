package org.bouncycastle.crypto.engines;

/* loaded from: classes5.dex */
public class VMPCKSA3Engine extends VMPCEngine {
    @Override // org.bouncycastle.crypto.engines.VMPCEngine, org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "VMPC-KSA3";
    }

    @Override // org.bouncycastle.crypto.engines.VMPCEngine
    protected void initKey(byte[] bArr, byte[] bArr2) {
        this.f1979s = (byte) 0;
        this.f1977P = new byte[256];
        for (int r2 = 0; r2 < 256; r2++) {
            this.f1977P[r2] = (byte) r2;
        }
        for (int r1 = 0; r1 < 768; r1++) {
            int r5 = r1 & 255;
            this.f1979s = this.f1977P[(this.f1979s + this.f1977P[r5] + bArr[r1 % bArr.length]) & 255];
            byte b = this.f1977P[r5];
            this.f1977P[r5] = this.f1977P[this.f1979s & 255];
            this.f1977P[this.f1979s & 255] = b;
        }
        for (int r12 = 0; r12 < 768; r12++) {
            int r6 = r12 & 255;
            this.f1979s = this.f1977P[(this.f1979s + this.f1977P[r6] + bArr2[r12 % bArr2.length]) & 255];
            byte b2 = this.f1977P[r6];
            this.f1977P[r6] = this.f1977P[this.f1979s & 255];
            this.f1977P[this.f1979s & 255] = b2;
        }
        for (int r10 = 0; r10 < 768; r10++) {
            int r52 = r10 & 255;
            this.f1979s = this.f1977P[(this.f1979s + this.f1977P[r52] + bArr[r10 % bArr.length]) & 255];
            byte b3 = this.f1977P[r52];
            this.f1977P[r52] = this.f1977P[this.f1979s & 255];
            this.f1977P[this.f1979s & 255] = b3;
        }
        this.f1978n = (byte) 0;
    }
}
