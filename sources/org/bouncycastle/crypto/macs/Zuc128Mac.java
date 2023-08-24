package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.engines.Zuc128CoreEngine;

/* loaded from: classes5.dex */
public final class Zuc128Mac implements Mac {
    private static final int TOPBIT = 128;
    private int theByteIndex;
    private final InternalZuc128Engine theEngine = new InternalZuc128Engine();
    private final int[] theKeyStream = new int[2];
    private int theMac;
    private Zuc128CoreEngine theState;
    private int theWordIndex;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public static class InternalZuc128Engine extends Zuc128CoreEngine {
        private InternalZuc128Engine() {
        }

        int createKeyStreamWord() {
            return super.makeKeyStreamWord();
        }
    }

    private int getFinalWord() {
        if (this.theByteIndex != 0) {
            return this.theEngine.createKeyStreamWord();
        }
        int[] r1 = this.theKeyStream;
        int length = (this.theWordIndex + 1) % r1.length;
        this.theWordIndex = length;
        return r1[length];
    }

    private int getKeyStreamWord(int r5) {
        int[] r0 = this.theKeyStream;
        int r1 = this.theWordIndex;
        int r2 = r0[r1];
        if (r5 == 0) {
            return r2;
        }
        int r02 = r0[(r1 + 1) % r0.length];
        return (r02 >>> (32 - r5)) | (r2 << r5);
    }

    private void initKeyStream() {
        int r0 = 0;
        this.theMac = 0;
        while (true) {
            int[] r1 = this.theKeyStream;
            if (r0 >= r1.length - 1) {
                this.theWordIndex = r1.length - 1;
                this.theByteIndex = 3;
                return;
            }
            r1[r0] = this.theEngine.createKeyStreamWord();
            r0++;
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

    private void updateMac(int r2) {
        this.theMac = getKeyStreamWord(r2) ^ this.theMac;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r4) {
        shift4NextByte();
        int keyStreamWord = this.theMac ^ getKeyStreamWord(this.theByteIndex * 8);
        this.theMac = keyStreamWord;
        int finalWord = keyStreamWord ^ getFinalWord();
        this.theMac = finalWord;
        Zuc128CoreEngine.encode32be(finalWord, bArr, r4);
        reset();
        return getMacSize();
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "Zuc128Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 4;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) {
        this.theEngine.init(true, cipherParameters);
        this.theState = (Zuc128CoreEngine) this.theEngine.copy();
        initKeyStream();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theState;
        if (zuc128CoreEngine != null) {
            this.theEngine.reset(zuc128CoreEngine);
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
