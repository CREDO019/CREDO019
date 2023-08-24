package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;

/* loaded from: classes3.dex */
public class GMSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.3";

    /* renamed from: K */
    private int[] f2404K;
    private byte[][] currentRootSigs;
    private byte[][] currentSeeds;
    private GMSSDigestProvider digestProvider;
    private GMSSParameters gmssPS;
    private GMSSKeyGenerationParameters gmssParams;
    private GMSSRandom gmssRandom;
    private int[] heightOfTrees;
    private boolean initialized = false;
    private int mdLength;
    private Digest messDigestTree;
    private byte[][] nextNextSeeds;
    private int numLayer;
    private int[] otsIndex;

    public GMSSKeyPairGenerator(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTree = digest;
        this.mdLength = digest.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTree);
    }

    private AsymmetricCipherKeyPair genKeyPair() {
        int r5;
        int r15;
        if (!this.initialized) {
            initializeDefault();
        }
        int r2 = this.numLayer;
        byte[][][] bArr = new byte[r2][];
        byte[][][] bArr2 = new byte[r2 - 1][];
        Treehash[][] treehashArr = new Treehash[r2];
        Treehash[][] treehashArr2 = new Treehash[r2 - 1];
        Vector[] vectorArr = new Vector[r2];
        Vector[] vectorArr2 = new Vector[r2 - 1];
        Vector[][] vectorArr3 = new Vector[r2];
        int r3 = 1;
        Vector[][] vectorArr4 = new Vector[r2 - 1];
        int r4 = 0;
        while (true) {
            r5 = this.numLayer;
            if (r4 >= r5) {
                break;
            }
            Vector[][] vectorArr5 = vectorArr4;
            bArr[r4] = (byte[][]) Array.newInstance(byte.class, this.heightOfTrees[r4], this.mdLength);
            int[] r52 = this.heightOfTrees;
            treehashArr[r4] = new Treehash[r52[r4] - this.f2404K[r4]];
            if (r4 > 0) {
                int r13 = r4 - 1;
                bArr2[r13] = (byte[][]) Array.newInstance(byte.class, r52[r4], this.mdLength);
                treehashArr2[r13] = new Treehash[this.heightOfTrees[r4] - this.f2404K[r4]];
            }
            vectorArr[r4] = new Vector();
            if (r4 > 0) {
                vectorArr2[r4 - 1] = new Vector();
            }
            r4++;
            vectorArr4 = vectorArr5;
        }
        Vector[][] vectorArr6 = vectorArr4;
        byte[][] bArr3 = (byte[][]) Array.newInstance(byte.class, r5, this.mdLength);
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, this.numLayer - 1, this.mdLength);
        byte[][] bArr5 = (byte[][]) Array.newInstance(byte.class, this.numLayer, this.mdLength);
        int r132 = 0;
        while (true) {
            r15 = this.numLayer;
            if (r132 >= r15) {
                break;
            }
            System.arraycopy(this.currentSeeds[r132], 0, bArr5[r132], 0, this.mdLength);
            r132++;
            r3 = 1;
        }
        int[] r9 = new int[2];
        r9[r3] = this.mdLength;
        r9[0] = r15 - r3;
        this.currentRootSigs = (byte[][]) Array.newInstance(byte.class, r9);
        int r1 = this.numLayer - r3;
        while (r1 >= 0) {
            GMSSRootCalc generateCurrentAuthpathAndRoot = r1 == this.numLayer - r3 ? generateCurrentAuthpathAndRoot(null, vectorArr[r1], bArr5[r1], r1) : generateCurrentAuthpathAndRoot(bArr3[r1 + 1], vectorArr[r1], bArr5[r1], r1);
            for (int r133 = 0; r133 < this.heightOfTrees[r1]; r133++) {
                System.arraycopy(generateCurrentAuthpathAndRoot.getAuthPath()[r133], 0, bArr[r1][r133], 0, this.mdLength);
            }
            vectorArr3[r1] = generateCurrentAuthpathAndRoot.getRetain();
            treehashArr[r1] = generateCurrentAuthpathAndRoot.getTreehash();
            System.arraycopy(generateCurrentAuthpathAndRoot.getRoot(), 0, bArr3[r1], 0, this.mdLength);
            r1--;
            r3 = 1;
        }
        int r12 = this.numLayer - 2;
        while (r12 >= 0) {
            int r92 = r12 + 1;
            GMSSRootCalc generateNextAuthpathAndRoot = generateNextAuthpathAndRoot(vectorArr2[r12], bArr5[r92], r92);
            int r134 = 0;
            while (r134 < this.heightOfTrees[r92]) {
                System.arraycopy(generateNextAuthpathAndRoot.getAuthPath()[r134], 0, bArr2[r12][r134], 0, this.mdLength);
                r134++;
                vectorArr3 = vectorArr3;
            }
            vectorArr6[r12] = generateNextAuthpathAndRoot.getRetain();
            treehashArr2[r12] = generateNextAuthpathAndRoot.getTreehash();
            System.arraycopy(generateNextAuthpathAndRoot.getRoot(), 0, bArr4[r12], 0, this.mdLength);
            System.arraycopy(bArr5[r92], 0, this.nextNextSeeds[r12], 0, this.mdLength);
            r12--;
            vectorArr3 = vectorArr3;
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new GMSSPublicKeyParameters(bArr3[0], this.gmssPS), (AsymmetricKeyParameter) new GMSSPrivateKeyParameters(this.currentSeeds, this.nextNextSeeds, bArr, bArr2, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr6, bArr4, this.currentRootSigs, this.gmssPS, this.digestProvider));
    }

    private GMSSRootCalc generateCurrentAuthpathAndRoot(byte[] bArr, Vector vector, byte[] bArr2, int r11) {
        byte[] Verify;
        int r0 = this.mdLength;
        byte[] bArr3 = new byte[r0];
        byte[] bArr4 = new byte[r0];
        byte[] nextSeed = this.gmssRandom.nextSeed(bArr2);
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[r11], this.f2404K[r11], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        if (r11 == this.numLayer - 1) {
            Verify = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[r11]).getPublicKey();
        } else {
            this.currentRootSigs[r11] = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[r11]).getSignature(bArr);
            Verify = new WinternitzOTSVerify(this.digestProvider.get(), this.otsIndex[r11]).Verify(bArr, this.currentRootSigs[r11]);
        }
        gMSSRootCalc.update(Verify);
        int r8 = 3;
        int r9 = 0;
        int r02 = 1;
        while (true) {
            int[] r3 = this.heightOfTrees;
            if (r02 >= (1 << r3[r11])) {
                break;
            }
            if (r02 == r8 && r9 < r3[r11] - this.f2404K[r11]) {
                gMSSRootCalc.initializeTreehashSeed(bArr2, r9);
                r8 *= 2;
                r9++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr2), this.digestProvider.get(), this.otsIndex[r11]).getPublicKey());
            r02++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private GMSSRootCalc generateNextAuthpathAndRoot(Vector vector, byte[] bArr, int r10) {
        byte[] bArr2 = new byte[this.numLayer];
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[r10], this.f2404K[r10], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        int r8 = 0;
        int r1 = 0;
        int r2 = 3;
        while (true) {
            int[] r3 = this.heightOfTrees;
            if (r8 >= (1 << r3[r10])) {
                break;
            }
            if (r8 == r2 && r1 < r3[r10] - this.f2404K[r10]) {
                gMSSRootCalc.initializeTreehashSeed(bArr, r1);
                r2 *= 2;
                r1++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr), this.digestProvider.get(), this.otsIndex[r10]).getPublicKey());
            r8++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("N�chster Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private void initializeDefault() {
        initialize(new GMSSKeyGenerationParameters(null, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2})));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(int r6, SecureRandom secureRandom) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters;
        if (r6 <= 10) {
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(1, new int[]{10}, new int[]{3}, new int[]{2}));
        } else {
            gMSSKeyGenerationParameters = r6 <= 20 ? new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2})) : new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2}));
        }
        initialize(gMSSKeyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters = (GMSSKeyGenerationParameters) keyGenerationParameters;
        this.gmssParams = gMSSKeyGenerationParameters;
        GMSSParameters gMSSParameters = new GMSSParameters(gMSSKeyGenerationParameters.getParameters().getNumOfLayers(), this.gmssParams.getParameters().getHeightOfTrees(), this.gmssParams.getParameters().getWinternitzParameter(), this.gmssParams.getParameters().getK());
        this.gmssPS = gMSSParameters;
        this.numLayer = gMSSParameters.getNumOfLayers();
        this.heightOfTrees = this.gmssPS.getHeightOfTrees();
        this.otsIndex = this.gmssPS.getWinternitzParameter();
        this.f2404K = this.gmssPS.getK();
        this.currentSeeds = (byte[][]) Array.newInstance(byte.class, this.numLayer, this.mdLength);
        this.nextNextSeeds = (byte[][]) Array.newInstance(byte.class, this.numLayer - 1, this.mdLength);
        SecureRandom random = keyGenerationParameters.getRandom();
        for (int r2 = 0; r2 < this.numLayer; r2++) {
            random.nextBytes(this.currentSeeds[r2]);
            this.gmssRandom.nextSeed(this.currentSeeds[r2]);
        }
        this.initialized = true;
    }
}
