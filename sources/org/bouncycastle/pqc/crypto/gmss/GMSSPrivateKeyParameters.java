package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class GMSSPrivateKeyParameters extends GMSSKeyParameters {

    /* renamed from: K */
    private int[] f2409K;
    private byte[][][] currentAuthPaths;
    private Vector[][] currentRetain;
    private byte[][] currentRootSig;
    private byte[][] currentSeeds;
    private Vector[] currentStack;
    private Treehash[][] currentTreehash;
    private GMSSDigestProvider digestProvider;
    private GMSSParameters gmssPS;
    private GMSSRandom gmssRandom;
    private int[] heightOfTrees;
    private int[] index;
    private byte[][][] keep;
    private int mdLength;
    private Digest messDigestTrees;
    private int[] minTreehash;
    private byte[][][] nextAuthPaths;
    private GMSSLeaf[] nextNextLeaf;
    private GMSSRootCalc[] nextNextRoot;
    private byte[][] nextNextSeeds;
    private Vector[][] nextRetain;
    private byte[][] nextRoot;
    private GMSSRootSig[] nextRootSig;
    private Vector[] nextStack;
    private Treehash[][] nextTreehash;
    private int numLayer;
    private int[] numLeafs;
    private int[] otsIndex;
    private GMSSLeaf[] upperLeaf;
    private GMSSLeaf[] upperTreehashLeaf;
    private boolean used;

    private GMSSPrivateKeyParameters(GMSSPrivateKeyParameters gMSSPrivateKeyParameters) {
        super(true, gMSSPrivateKeyParameters.getParameters());
        this.used = false;
        this.index = Arrays.clone(gMSSPrivateKeyParameters.index);
        this.currentSeeds = Arrays.clone(gMSSPrivateKeyParameters.currentSeeds);
        this.nextNextSeeds = Arrays.clone(gMSSPrivateKeyParameters.nextNextSeeds);
        this.currentAuthPaths = Arrays.clone(gMSSPrivateKeyParameters.currentAuthPaths);
        this.nextAuthPaths = Arrays.clone(gMSSPrivateKeyParameters.nextAuthPaths);
        this.currentTreehash = gMSSPrivateKeyParameters.currentTreehash;
        this.nextTreehash = gMSSPrivateKeyParameters.nextTreehash;
        this.currentStack = gMSSPrivateKeyParameters.currentStack;
        this.nextStack = gMSSPrivateKeyParameters.nextStack;
        this.currentRetain = gMSSPrivateKeyParameters.currentRetain;
        this.nextRetain = gMSSPrivateKeyParameters.nextRetain;
        this.keep = Arrays.clone(gMSSPrivateKeyParameters.keep);
        this.nextNextLeaf = gMSSPrivateKeyParameters.nextNextLeaf;
        this.upperLeaf = gMSSPrivateKeyParameters.upperLeaf;
        this.upperTreehashLeaf = gMSSPrivateKeyParameters.upperTreehashLeaf;
        this.minTreehash = gMSSPrivateKeyParameters.minTreehash;
        this.gmssPS = gMSSPrivateKeyParameters.gmssPS;
        this.nextRoot = Arrays.clone(gMSSPrivateKeyParameters.nextRoot);
        this.nextNextRoot = gMSSPrivateKeyParameters.nextNextRoot;
        this.currentRootSig = gMSSPrivateKeyParameters.currentRootSig;
        this.nextRootSig = gMSSPrivateKeyParameters.nextRootSig;
        this.digestProvider = gMSSPrivateKeyParameters.digestProvider;
        this.heightOfTrees = gMSSPrivateKeyParameters.heightOfTrees;
        this.otsIndex = gMSSPrivateKeyParameters.otsIndex;
        this.f2409K = gMSSPrivateKeyParameters.f2409K;
        this.numLayer = gMSSPrivateKeyParameters.numLayer;
        this.messDigestTrees = gMSSPrivateKeyParameters.messDigestTrees;
        this.mdLength = gMSSPrivateKeyParameters.mdLength;
        this.gmssRandom = gMSSPrivateKeyParameters.gmssRandom;
        this.numLeafs = gMSSPrivateKeyParameters.numLeafs;
    }

    public GMSSPrivateKeyParameters(int[] r17, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, byte[][][] bArr5, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] r32, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        super(true, gMSSParameters);
        this.used = false;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTrees = digest;
        this.mdLength = digest.getDigestSize();
        this.gmssPS = gMSSParameters;
        this.otsIndex = gMSSParameters.getWinternitzParameter();
        this.f2409K = gMSSParameters.getK();
        this.heightOfTrees = gMSSParameters.getHeightOfTrees();
        int numOfLayers = this.gmssPS.getNumOfLayers();
        this.numLayer = numOfLayers;
        if (r17 == null) {
            this.index = new int[numOfLayers];
            for (int r1 = 0; r1 < this.numLayer; r1++) {
                this.index[r1] = 0;
            }
        } else {
            this.index = r17;
        }
        this.currentSeeds = bArr;
        this.nextNextSeeds = bArr2;
        this.currentAuthPaths = Arrays.clone(bArr3);
        this.nextAuthPaths = bArr4;
        int r12 = 2;
        if (bArr5 == null) {
            this.keep = new byte[this.numLayer][];
            int r3 = 0;
            while (r3 < this.numLayer) {
                this.keep[r3] = (byte[][]) Array.newInstance(byte.class, (int) Math.floor(this.heightOfTrees[r3] / r12), this.mdLength);
                r3++;
                r12 = 2;
            }
        } else {
            this.keep = bArr5;
        }
        if (vectorArr == null) {
            this.currentStack = new Vector[this.numLayer];
            for (int r13 = 0; r13 < this.numLayer; r13++) {
                this.currentStack[r13] = new Vector();
            }
        } else {
            this.currentStack = vectorArr;
        }
        if (vectorArr2 == null) {
            this.nextStack = new Vector[this.numLayer - 1];
            int r14 = 0;
            for (int r2 = 1; r14 < this.numLayer - r2; r2 = 1) {
                this.nextStack[r14] = new Vector();
                r14++;
            }
        } else {
            this.nextStack = vectorArr2;
        }
        this.currentTreehash = treehashArr;
        this.nextTreehash = treehashArr2;
        this.currentRetain = vectorArr3;
        this.nextRetain = vectorArr4;
        this.nextRoot = bArr6;
        this.digestProvider = gMSSDigestProvider;
        if (gMSSRootCalcArr == null) {
            this.nextNextRoot = new GMSSRootCalc[this.numLayer - 1];
            int r22 = 0;
            for (int r33 = 1; r22 < this.numLayer - r33; r33 = 1) {
                int r11 = r22 + 1;
                this.nextNextRoot[r22] = new GMSSRootCalc(this.heightOfTrees[r11], this.f2409K[r11], this.digestProvider);
                r22 = r11;
            }
        } else {
            this.nextNextRoot = gMSSRootCalcArr;
        }
        this.currentRootSig = bArr7;
        this.numLeafs = new int[this.numLayer];
        for (int r23 = 0; r23 < this.numLayer; r23++) {
            this.numLeafs[r23] = 1 << this.heightOfTrees[r23];
        }
        this.gmssRandom = new GMSSRandom(this.messDigestTrees);
        int r24 = this.numLayer;
        if (r24 <= 1) {
            this.nextNextLeaf = new GMSSLeaf[0];
        } else if (gMSSLeafArr == null) {
            this.nextNextLeaf = new GMSSLeaf[r24 - 2];
            int r25 = 0;
            while (r25 < this.numLayer - 2) {
                int r122 = r25 + 1;
                this.nextNextLeaf[r25] = new GMSSLeaf(gMSSDigestProvider.get(), this.otsIndex[r122], this.numLeafs[r25 + 2], this.nextNextSeeds[r25]);
                r25 = r122;
            }
        } else {
            this.nextNextLeaf = gMSSLeafArr;
        }
        if (gMSSLeafArr2 == null) {
            this.upperLeaf = new GMSSLeaf[this.numLayer - 1];
            int r26 = 0;
            for (int r34 = 1; r26 < this.numLayer - r34; r34 = 1) {
                int r112 = r26 + 1;
                this.upperLeaf[r26] = new GMSSLeaf(gMSSDigestProvider.get(), this.otsIndex[r26], this.numLeafs[r112], this.currentSeeds[r26]);
                r26 = r112;
            }
        } else {
            this.upperLeaf = gMSSLeafArr2;
        }
        if (gMSSLeafArr3 == null) {
            this.upperTreehashLeaf = new GMSSLeaf[this.numLayer - 1];
            int r27 = 0;
            for (int r35 = 1; r27 < this.numLayer - r35; r35 = 1) {
                int r8 = r27 + 1;
                this.upperTreehashLeaf[r27] = new GMSSLeaf(gMSSDigestProvider.get(), this.otsIndex[r27], this.numLeafs[r8]);
                r27 = r8;
            }
        } else {
            this.upperTreehashLeaf = gMSSLeafArr3;
        }
        if (r32 == null) {
            this.minTreehash = new int[this.numLayer - 1];
            int r28 = 0;
            for (int r36 = 1; r28 < this.numLayer - r36; r36 = 1) {
                this.minTreehash[r28] = -1;
                r28++;
            }
        } else {
            this.minTreehash = r32;
        }
        int r29 = this.mdLength;
        byte[] bArr8 = new byte[r29];
        byte[] bArr9 = new byte[r29];
        if (gMSSRootSigArr != null) {
            this.nextRootSig = gMSSRootSigArr;
            return;
        }
        this.nextRootSig = new GMSSRootSig[this.numLayer - 1];
        int r210 = 0;
        while (r210 < this.numLayer - 1) {
            System.arraycopy(bArr[r210], 0, bArr8, 0, this.mdLength);
            this.gmssRandom.nextSeed(bArr8);
            byte[] nextSeed = this.gmssRandom.nextSeed(bArr8);
            int r123 = r210 + 1;
            this.nextRootSig[r210] = new GMSSRootSig(gMSSDigestProvider.get(), this.otsIndex[r210], this.heightOfTrees[r123]);
            this.nextRootSig[r210].initSign(nextSeed, bArr6[r210]);
            r210 = r123;
        }
    }

    public GMSSPrivateKeyParameters(byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, byte[][] bArr5, byte[][] bArr6, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        this(null, bArr, bArr2, bArr3, bArr4, null, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr4, null, null, null, null, bArr5, null, bArr6, null, gMSSParameters, gMSSDigestProvider);
    }

    private void computeAuthPaths(int r14) {
        int r5;
        int r12;
        byte[] bArr;
        int r0 = this.index[r14];
        int r1 = this.heightOfTrees[r14];
        int r2 = this.f2409K[r14];
        int r4 = 0;
        while (true) {
            r5 = r1 - r2;
            if (r4 >= r5) {
                break;
            }
            this.currentTreehash[r14][r4].updateNextSeed(this.gmssRandom);
            r4++;
        }
        int heightOfPhi = heightOfPhi(r0);
        byte[] bArr2 = new byte[this.mdLength];
        byte[] nextSeed = this.gmssRandom.nextSeed(this.currentSeeds[r14]);
        int r6 = (r0 >>> (heightOfPhi + 1)) & 1;
        int r8 = this.mdLength;
        byte[] bArr3 = new byte[r8];
        int r13 = r1 - 1;
        if (heightOfPhi < r13 && r6 == 0) {
            System.arraycopy(this.currentAuthPaths[r14][heightOfPhi], 0, bArr3, 0, r8);
        }
        int r82 = this.mdLength;
        byte[] bArr4 = new byte[r82];
        if (heightOfPhi == 0) {
            if (r14 == this.numLayer - 1) {
                bArr = new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[r14]).getPublicKey();
            } else {
                byte[] bArr5 = new byte[r82];
                System.arraycopy(this.currentSeeds[r14], 0, bArr5, 0, r82);
                this.gmssRandom.nextSeed(bArr5);
                byte[] leaf = this.upperLeaf[r14].getLeaf();
                this.upperLeaf[r14].initLeafCalc(bArr5);
                bArr = leaf;
            }
            System.arraycopy(bArr, 0, this.currentAuthPaths[r14][0], 0, this.mdLength);
        } else {
            int r42 = r82 << 1;
            byte[] bArr6 = new byte[r42];
            System.arraycopy(this.currentAuthPaths[r14][heightOfPhi - 1], 0, bArr6, 0, r82);
            byte[] bArr7 = this.keep[r14][(int) Math.floor(r12 / 2)];
            int r11 = this.mdLength;
            System.arraycopy(bArr7, 0, bArr6, r11, r11);
            this.messDigestTrees.update(bArr6, 0, r42);
            this.currentAuthPaths[r14][heightOfPhi] = new byte[this.messDigestTrees.getDigestSize()];
            this.messDigestTrees.doFinal(this.currentAuthPaths[r14][heightOfPhi], 0);
            for (int r43 = 0; r43 < heightOfPhi; r43++) {
                if (r43 < r5) {
                    if (this.currentTreehash[r14][r43].wasFinished()) {
                        System.arraycopy(this.currentTreehash[r14][r43].getFirstNode(), 0, this.currentAuthPaths[r14][r43], 0, this.mdLength);
                        this.currentTreehash[r14][r43].destroy();
                    } else {
                        System.err.println("Treehash (" + r14 + "," + r43 + ") not finished when needed in AuthPathComputation");
                    }
                }
                if (r43 < r13 && r43 >= r5) {
                    int r10 = r43 - r5;
                    if (this.currentRetain[r14][r10].size() > 0) {
                        System.arraycopy(this.currentRetain[r14][r10].lastElement(), 0, this.currentAuthPaths[r14][r43], 0, this.mdLength);
                        Vector[][] vectorArr = this.currentRetain;
                        vectorArr[r14][r10].removeElementAt(vectorArr[r14][r10].size() - 1);
                    }
                }
                if (r43 < r5 && ((1 << r43) * 3) + r0 < this.numLeafs[r14]) {
                    this.currentTreehash[r14][r43].initialize();
                }
            }
        }
        if (heightOfPhi < r13 && r6 == 0) {
            System.arraycopy(bArr3, 0, this.keep[r14][(int) Math.floor(heightOfPhi / 2)], 0, this.mdLength);
        }
        if (r14 != this.numLayer - 1) {
            this.minTreehash[r14] = getMinTreehashIndex(r14);
            return;
        }
        for (int r7 = 1; r7 <= r5 / 2; r7++) {
            int minTreehashIndex = getMinTreehashIndex(r14);
            if (minTreehashIndex >= 0) {
                try {
                    byte[] bArr8 = new byte[this.mdLength];
                    System.arraycopy(this.currentTreehash[r14][minTreehashIndex].getSeedActive(), 0, bArr8, 0, this.mdLength);
                    this.currentTreehash[r14][minTreehashIndex].update(this.gmssRandom, new WinternitzOTSignature(this.gmssRandom.nextSeed(bArr8), this.digestProvider.get(), this.otsIndex[r14]).getPublicKey());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    private int getMinTreehashIndex(int r6) {
        int r2 = -1;
        for (int r1 = 0; r1 < this.heightOfTrees[r6] - this.f2409K[r6]; r1++) {
            if (this.currentTreehash[r6][r1].wasInitialized() && !this.currentTreehash[r6][r1].wasFinished() && (r2 == -1 || this.currentTreehash[r6][r1].getLowestNodeHeight() < this.currentTreehash[r6][r2].getLowestNodeHeight())) {
                r2 = r1;
            }
        }
        return r2;
    }

    private int heightOfPhi(int r5) {
        if (r5 == 0) {
            return -1;
        }
        int r0 = 0;
        int r2 = 1;
        while (r5 % r2 == 0) {
            r2 *= 2;
            r0++;
        }
        return r0 - 1;
    }

    private void nextKey(int r5) {
        int r0 = this.numLayer;
        if (r5 == r0 - 1) {
            int[] r1 = this.index;
            r1[r5] = r1[r5] + 1;
        }
        if (this.index[r5] != this.numLeafs[r5]) {
            updateKey(r5);
        } else if (r0 != 1) {
            nextTree(r5);
            this.index[r5] = 0;
        }
    }

    private void nextTree(int r8) {
        if (r8 > 0) {
            int[] r0 = this.index;
            int r1 = r8 - 1;
            r0[r1] = r0[r1] + 1;
            int r02 = r8;
            boolean z = true;
            do {
                r02--;
                if (this.index[r02] < this.numLeafs[r02]) {
                    z = false;
                }
                if (!z) {
                    break;
                }
            } while (r02 > 0);
            if (z) {
                return;
            }
            this.gmssRandom.nextSeed(this.currentSeeds[r8]);
            this.nextRootSig[r1].updateSign();
            if (r8 > 1) {
                GMSSLeaf[] gMSSLeafArr = this.nextNextLeaf;
                int r2 = r1 - 1;
                gMSSLeafArr[r2] = gMSSLeafArr[r2].nextLeaf();
            }
            GMSSLeaf[] gMSSLeafArr2 = this.upperLeaf;
            gMSSLeafArr2[r1] = gMSSLeafArr2[r1].nextLeaf();
            if (this.minTreehash[r1] >= 0) {
                GMSSLeaf[] gMSSLeafArr3 = this.upperTreehashLeaf;
                gMSSLeafArr3[r1] = gMSSLeafArr3[r1].nextLeaf();
                try {
                    this.currentTreehash[r1][this.minTreehash[r1]].update(this.gmssRandom, this.upperTreehashLeaf[r1].getLeaf());
                    this.currentTreehash[r1][this.minTreehash[r1]].wasFinished();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            updateNextNextAuthRoot(r8);
            this.currentRootSig[r1] = this.nextRootSig[r1].getSig();
            for (int r03 = 0; r03 < this.heightOfTrees[r8] - this.f2409K[r8]; r03++) {
                Treehash[] treehashArr = this.currentTreehash[r8];
                Treehash[][] treehashArr2 = this.nextTreehash;
                treehashArr[r03] = treehashArr2[r1][r03];
                treehashArr2[r1][r03] = this.nextNextRoot[r1].getTreehash()[r03];
            }
            for (int r04 = 0; r04 < this.heightOfTrees[r8]; r04++) {
                System.arraycopy(this.nextAuthPaths[r1][r04], 0, this.currentAuthPaths[r8][r04], 0, this.mdLength);
                System.arraycopy(this.nextNextRoot[r1].getAuthPath()[r04], 0, this.nextAuthPaths[r1][r04], 0, this.mdLength);
            }
            for (int r05 = 0; r05 < this.f2409K[r8] - 1; r05++) {
                Vector[] vectorArr = this.currentRetain[r8];
                Vector[][] vectorArr2 = this.nextRetain;
                vectorArr[r05] = vectorArr2[r1][r05];
                vectorArr2[r1][r05] = this.nextNextRoot[r1].getRetain()[r05];
            }
            Vector[] vectorArr3 = this.currentStack;
            Vector[] vectorArr4 = this.nextStack;
            vectorArr3[r8] = vectorArr4[r1];
            vectorArr4[r1] = this.nextNextRoot[r1].getStack();
            this.nextRoot[r1] = this.nextNextRoot[r1].getRoot();
            int r82 = this.mdLength;
            byte[] bArr = new byte[r82];
            byte[] bArr2 = new byte[r82];
            System.arraycopy(this.currentSeeds[r1], 0, bArr2, 0, r82);
            this.gmssRandom.nextSeed(bArr2);
            this.gmssRandom.nextSeed(bArr2);
            this.nextRootSig[r1].initSign(this.gmssRandom.nextSeed(bArr2), this.nextRoot[r1]);
            nextKey(r1);
        }
    }

    private void updateKey(int r9) {
        computeAuthPaths(r9);
        if (r9 > 0) {
            if (r9 > 1) {
                GMSSLeaf[] gMSSLeafArr = this.nextNextLeaf;
                int r2 = (r9 - 1) - 1;
                gMSSLeafArr[r2] = gMSSLeafArr[r2].nextLeaf();
            }
            GMSSLeaf[] gMSSLeafArr2 = this.upperLeaf;
            int r22 = r9 - 1;
            gMSSLeafArr2[r22] = gMSSLeafArr2[r22].nextLeaf();
            int floor = (int) Math.floor((getNumLeafs(r9) * 2) / (this.heightOfTrees[r22] - this.f2409K[r22]));
            int[] r3 = this.index;
            if (r3[r9] % floor == 1) {
                if (r3[r9] > 1 && this.minTreehash[r22] >= 0) {
                    try {
                        this.currentTreehash[r22][this.minTreehash[r22]].update(this.gmssRandom, this.upperTreehashLeaf[r22].getLeaf());
                        this.currentTreehash[r22][this.minTreehash[r22]].wasFinished();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                this.minTreehash[r22] = getMinTreehashIndex(r22);
                int[] r32 = this.minTreehash;
                if (r32[r22] >= 0) {
                    this.upperTreehashLeaf[r22] = new GMSSLeaf(this.digestProvider.get(), this.otsIndex[r22], floor, this.currentTreehash[r22][r32[r22]].getSeedActive());
                    GMSSLeaf[] gMSSLeafArr3 = this.upperTreehashLeaf;
                    gMSSLeafArr3[r22] = gMSSLeafArr3[r22].nextLeaf();
                }
            } else if (this.minTreehash[r22] >= 0) {
                GMSSLeaf[] gMSSLeafArr4 = this.upperTreehashLeaf;
                gMSSLeafArr4[r22] = gMSSLeafArr4[r22].nextLeaf();
            }
            this.nextRootSig[r22].updateSign();
            if (this.index[r9] == 1) {
                this.nextNextRoot[r22].initialize(new Vector());
            }
            updateNextNextAuthRoot(r9);
        }
    }

    private void updateNextNextAuthRoot(int r6) {
        byte[] bArr = new byte[this.mdLength];
        int r2 = r6 - 1;
        byte[] nextSeed = this.gmssRandom.nextSeed(this.nextNextSeeds[r2]);
        if (r6 == this.numLayer - 1) {
            this.nextNextRoot[r2].update(this.nextNextSeeds[r2], new WinternitzOTSignature(nextSeed, this.digestProvider.get(), this.otsIndex[r6]).getPublicKey());
            return;
        }
        this.nextNextRoot[r2].update(this.nextNextSeeds[r2], this.nextNextLeaf[r2].getLeaf());
        this.nextNextLeaf[r2].initLeafCalc(this.nextNextSeeds[r2]);
    }

    public byte[][][] getCurrentAuthPaths() {
        return Arrays.clone(this.currentAuthPaths);
    }

    public byte[][] getCurrentSeeds() {
        return Arrays.clone(this.currentSeeds);
    }

    public int getIndex(int r2) {
        return this.index[r2];
    }

    public int[] getIndex() {
        return this.index;
    }

    public GMSSDigestProvider getName() {
        return this.digestProvider;
    }

    public int getNumLeafs(int r2) {
        return this.numLeafs[r2];
    }

    public byte[] getSubtreeRootSig(int r2) {
        return this.currentRootSig[r2];
    }

    public boolean isUsed() {
        return this.used;
    }

    public void markUsed() {
        this.used = true;
    }

    public GMSSPrivateKeyParameters nextKey() {
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = new GMSSPrivateKeyParameters(this);
        gMSSPrivateKeyParameters.nextKey(this.gmssPS.getNumOfLayers() - 1);
        return gMSSPrivateKeyParameters;
    }
}
