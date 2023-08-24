package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class GMSSRootCalc {
    private byte[][] AuthPath;

    /* renamed from: K */
    private int f2410K;
    private GMSSDigestProvider digestProvider;
    private int heightOfNextSeed;
    private Vector heightOfNodes;
    private int heightOfTree;
    private int[] index;
    private int indexForNextSeed;
    private boolean isFinished;
    private boolean isInitialized;
    private int mdLength;
    private Digest messDigestTree;
    private Vector[] retain;
    private byte[] root;
    private Vector tailStack;
    private Treehash[] treehash;

    public GMSSRootCalc(int r3, int r4, GMSSDigestProvider gMSSDigestProvider) {
        this.heightOfTree = r3;
        this.digestProvider = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.messDigestTree = digest;
        int digestSize = digest.getDigestSize();
        this.mdLength = digestSize;
        this.f2410K = r4;
        this.index = new int[r3];
        int[] r0 = {r3, digestSize};
        this.AuthPath = (byte[][]) Array.newInstance(byte.class, r0);
        this.root = new byte[this.mdLength];
        this.retain = new Vector[this.f2410K - 1];
        for (int r5 = 0; r5 < r4 - 1; r5++) {
            this.retain[r5] = new Vector();
        }
    }

    public byte[][] getAuthPath() {
        return GMSSUtils.clone(this.AuthPath);
    }

    public Vector[] getRetain() {
        return GMSSUtils.clone(this.retain);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.root);
    }

    public Vector getStack() {
        Vector vector = new Vector();
        Enumeration elements = this.tailStack.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement());
        }
        return vector;
    }

    public byte[][] getStatByte() {
        Vector vector = this.tailStack;
        int size = vector == null ? 0 : vector.size();
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, this.heightOfTree + 1 + size, 64);
        bArr[0] = this.root;
        int r4 = 0;
        while (r4 < this.heightOfTree) {
            int r5 = r4 + 1;
            bArr[r5] = this.AuthPath[r4];
            r4 = r5;
        }
        for (int r1 = 0; r1 < size; r1++) {
            bArr[this.heightOfTree + 1 + r1] = (byte[]) this.tailStack.elementAt(r1);
        }
        return bArr;
    }

    public int[] getStatInt() {
        Vector vector = this.tailStack;
        int size = vector == null ? 0 : vector.size();
        int r2 = this.heightOfTree;
        int[] r3 = new int[r2 + 8 + size];
        r3[0] = r2;
        r3[1] = this.mdLength;
        r3[2] = this.f2410K;
        r3[3] = this.indexForNextSeed;
        r3[4] = this.heightOfNextSeed;
        if (this.isFinished) {
            r3[5] = 1;
        } else {
            r3[5] = 0;
        }
        if (this.isInitialized) {
            r3[6] = 1;
        } else {
            r3[6] = 0;
        }
        r3[7] = size;
        for (int r22 = 0; r22 < this.heightOfTree; r22++) {
            r3[r22 + 8] = this.index[r22];
        }
        for (int r1 = 0; r1 < size; r1++) {
            r3[this.heightOfTree + 8 + r1] = ((Integer) this.heightOfNodes.elementAt(r1)).intValue();
        }
        return r3;
    }

    public Treehash[] getTreehash() {
        return GMSSUtils.clone(this.treehash);
    }

    public void initialize(Vector vector) {
        int r2;
        this.treehash = new Treehash[this.heightOfTree - this.f2410K];
        int r1 = 0;
        while (true) {
            r2 = this.heightOfTree;
            if (r1 >= r2 - this.f2410K) {
                break;
            }
            this.treehash[r1] = new Treehash(vector, r1, this.digestProvider.get());
            r1++;
        }
        this.index = new int[r2];
        this.AuthPath = (byte[][]) Array.newInstance(byte.class, r2, this.mdLength);
        this.root = new byte[this.mdLength];
        this.tailStack = new Vector();
        this.heightOfNodes = new Vector();
        this.isInitialized = true;
        this.isFinished = false;
        for (int r6 = 0; r6 < this.heightOfTree; r6++) {
            this.index[r6] = -1;
        }
        this.retain = new Vector[this.f2410K - 1];
        for (int r62 = 0; r62 < this.f2410K - 1; r62++) {
            this.retain[r62] = new Vector();
        }
        this.indexForNextSeed = 3;
        this.heightOfNextSeed = 0;
    }

    public void initializeTreehashSeed(byte[] bArr, int r3) {
        this.treehash[r3].initializeSeed(bArr);
    }

    public String toString() {
        Vector vector = this.tailStack;
        int size = vector == null ? 0 : vector.size();
        String str = "";
        for (int r3 = 0; r3 < this.heightOfTree + 8 + size; r3++) {
            str = str + getStatInt()[r3] + " ";
        }
        for (int r1 = 0; r1 < this.heightOfTree + 1 + size; r1++) {
            str = str + new String(Hex.encode(getStatByte()[r1])) + " ";
        }
        return str + "  " + this.digestProvider.get().getDigestSize();
    }

    public void update(byte[] bArr) {
        if (this.isFinished) {
            System.out.print("Too much updates for Tree!!");
        } else if (!this.isInitialized) {
            System.err.println("GMSSRootCalc not initialized!");
        } else {
            int[] r0 = this.index;
            r0[0] = r0[0] + 1;
            if (r0[0] == 1) {
                System.arraycopy(bArr, 0, this.AuthPath[0], 0, this.mdLength);
            } else if (r0[0] == 3 && this.heightOfTree > this.f2410K) {
                this.treehash[0].setFirstNode(bArr);
            }
            int[] r02 = this.index;
            if ((r02[0] - 3) % 2 == 0 && r02[0] >= 3 && this.heightOfTree == this.f2410K) {
                this.retain[0].insertElementAt(bArr, 0);
            }
            if (this.index[0] == 0) {
                this.tailStack.addElement(bArr);
                this.heightOfNodes.addElement(Integers.valueOf(0));
                return;
            }
            int r03 = this.mdLength;
            byte[] bArr2 = new byte[r03];
            int r5 = r03 << 1;
            byte[] bArr3 = new byte[r5];
            System.arraycopy(bArr, 0, bArr2, 0, r03);
            int r10 = 0;
            while (this.tailStack.size() > 0 && r10 == ((Integer) this.heightOfNodes.lastElement()).intValue()) {
                System.arraycopy(this.tailStack.lastElement(), 0, bArr3, 0, this.mdLength);
                Vector vector = this.tailStack;
                vector.removeElementAt(vector.size() - 1);
                Vector vector2 = this.heightOfNodes;
                vector2.removeElementAt(vector2.size() - 1);
                int r04 = this.mdLength;
                System.arraycopy(bArr2, 0, bArr3, r04, r04);
                this.messDigestTree.update(bArr3, 0, r5);
                bArr2 = new byte[this.messDigestTree.getDigestSize()];
                this.messDigestTree.doFinal(bArr2, 0);
                r10++;
                if (r10 < this.heightOfTree) {
                    int[] r05 = this.index;
                    r05[r10] = r05[r10] + 1;
                    if (r05[r10] == 1) {
                        System.arraycopy(bArr2, 0, this.AuthPath[r10], 0, this.mdLength);
                    }
                    if (r10 >= this.heightOfTree - this.f2410K) {
                        if (r10 == 0) {
                            System.out.println("M���P");
                        }
                        int[] r06 = this.index;
                        if ((r06[r10] - 3) % 2 == 0 && r06[r10] >= 3) {
                            this.retain[r10 - (this.heightOfTree - this.f2410K)].insertElementAt(bArr2, 0);
                        }
                    } else if (this.index[r10] == 3) {
                        this.treehash[r10].setFirstNode(bArr2);
                    }
                }
            }
            this.tailStack.addElement(bArr2);
            this.heightOfNodes.addElement(Integers.valueOf(r10));
            if (r10 == this.heightOfTree) {
                this.isFinished = true;
                this.isInitialized = false;
                this.root = (byte[]) this.tailStack.lastElement();
            }
        }
    }

    public void update(byte[] bArr, byte[] bArr2) {
        int r0 = this.heightOfNextSeed;
        if (r0 < this.heightOfTree - this.f2410K && this.indexForNextSeed - 2 == this.index[0]) {
            initializeTreehashSeed(bArr, r0);
            this.heightOfNextSeed++;
            this.indexForNextSeed *= 2;
        }
        update(bArr2);
    }

    public boolean wasFinished() {
        return this.isFinished;
    }

    public boolean wasInitialized() {
        return this.isInitialized;
    }
}
