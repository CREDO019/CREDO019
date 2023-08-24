package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.Zuc256CoreEngine;

/* loaded from: classes5.dex */
public final class Zuc256Mac implements Mac {
    private static final int TOPBIT = 128;
    private int theByteIndex;
    private final InternalZuc256Engine theEngine;
    private final int[] theKeyStream;
    private final int[] theMac;
    private final int theMacLength;
    private Zuc256CoreEngine theState;
    private int theWordIndex;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class InternalZuc256Engine extends Zuc256CoreEngine {
        public InternalZuc256Engine(int r1) {
            super(r1);
        }

        int createKeyStreamWord() {
            return super.makeKeyStreamWord();
        }
    }

    public Zuc256Mac(int r2) {
        this.theEngine = new InternalZuc256Engine(r2);
        this.theMacLength = r2;
        int r22 = r2 / 32;
        this.theMac = new int[r22];
        this.theKeyStream = new int[r22 + 1];
    }

    private int getKeyStreamWord(int r5, int r6) {
        int[] r0 = this.theKeyStream;
        int r1 = this.theWordIndex;
        int r2 = r0[(r1 + r5) % r0.length];
        if (r6 == 0) {
            return r2;
        }
        int r52 = r0[((r1 + r5) + 1) % r0.length];
        return (r52 >>> (32 - r6)) | (r2 << r6);
    }

    private void initKeyStream() {
        int r0 = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.theMac;
            if (r1 >= r2.length) {
                break;
            }
            r2[r1] = this.theEngine.createKeyStreamWord();
            r1++;
        }
        while (true) {
            int[] r12 = this.theKeyStream;
            if (r0 >= r12.length - 1) {
                this.theWordIndex = r12.length - 1;
                this.theByteIndex = 3;
                return;
            }
            r12[r0] = this.theEngine.createKeyStreamWord();
            r0++;
        }
    }

    private void shift4Final() {
        int r0 = (this.theByteIndex + 1) % 4;
        this.theByteIndex = r0;
        if (r0 == 0) {
            this.theWordIndex = (this.theWordIndex + 1) % this.theKeyStream.length;
        }
    }

    private void shift4NextByte() {
        int r0 = (this.theByteIndex + 1) % 4;
        this.theByteIndex = r0;
        if (r0 == 0) {
            this.theKeyStream[this.theWordIndex] = this.theEngine.createKeyStreamWord();
            this.theWordIndex = (this.theWordIndex + 1) % this.theKeyStream.length;
        }
    }

    private void updateMac(int r5) {
        int r0 = 0;
        while (true) {
            int[] r1 = this.theMac;
            if (r0 >= r1.length) {
                return;
            }
            r1[r0] = r1[r0] ^ getKeyStreamWord(r0, r5);
            r0++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r5) {
        shift4Final();
        updateMac(this.theByteIndex * 8);
        int r0 = 0;
        while (true) {
            int[] r1 = this.theMac;
            if (r0 >= r1.length) {
                reset();
                return getMacSize();
            }
            Zuc256CoreEngine.encode32be(r1[r0], bArr, (r0 * 4) + r5);
            r0++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "Zuc256Mac-" + this.theMacLength;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.theMacLength / 8;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.theEngine.init(true, cipherParameters);
        this.theState = (Zuc256CoreEngine) this.theEngine.copy();
        initKeyStream();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        Zuc256CoreEngine zuc256CoreEngine = this.theState;
        if (zuc256CoreEngine != null) {
            this.theEngine.reset(zuc256CoreEngine);
        }
        initKeyStream();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) {
        shift4NextByte();
        int r0 = this.theByteIndex * 8;
        int r1 = 128;
        int r2 = 0;
        while (r1 > 0) {
            if ((b & r1) != 0) {
                updateMac(r0 + r2);
            }
            r1 >>= 1;
            r2++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r4, int r5) {
        for (int r0 = 0; r0 < r5; r0++) {
            update(bArr[r4 + r0]);
        }
    }
}
