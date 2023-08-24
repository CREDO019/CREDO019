package org.bouncycastle.pqc.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GMSSUtils {
    GMSSUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Vector[] clone(Vector[] vectorArr) {
        if (vectorArr == null) {
            return null;
        }
        Vector[] vectorArr2 = new Vector[vectorArr.length];
        for (int r1 = 0; r1 != vectorArr.length; r1++) {
            vectorArr2[r1] = new Vector();
            Enumeration elements = vectorArr[r1].elements();
            while (elements.hasMoreElements()) {
                vectorArr2[r1].addElement(elements.nextElement());
            }
        }
        return vectorArr2;
    }

    static GMSSLeaf[] clone(GMSSLeaf[] gMSSLeafArr) {
        if (gMSSLeafArr == null) {
            return null;
        }
        GMSSLeaf[] gMSSLeafArr2 = new GMSSLeaf[gMSSLeafArr.length];
        System.arraycopy(gMSSLeafArr, 0, gMSSLeafArr2, 0, gMSSLeafArr.length);
        return gMSSLeafArr2;
    }

    static GMSSRootCalc[] clone(GMSSRootCalc[] gMSSRootCalcArr) {
        if (gMSSRootCalcArr == null) {
            return null;
        }
        GMSSRootCalc[] gMSSRootCalcArr2 = new GMSSRootCalc[gMSSRootCalcArr.length];
        System.arraycopy(gMSSRootCalcArr, 0, gMSSRootCalcArr2, 0, gMSSRootCalcArr.length);
        return gMSSRootCalcArr2;
    }

    static GMSSRootSig[] clone(GMSSRootSig[] gMSSRootSigArr) {
        if (gMSSRootSigArr == null) {
            return null;
        }
        GMSSRootSig[] gMSSRootSigArr2 = new GMSSRootSig[gMSSRootSigArr.length];
        System.arraycopy(gMSSRootSigArr, 0, gMSSRootSigArr2, 0, gMSSRootSigArr.length);
        return gMSSRootSigArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Treehash[] clone(Treehash[] treehashArr) {
        if (treehashArr == null) {
            return null;
        }
        Treehash[] treehashArr2 = new Treehash[treehashArr.length];
        System.arraycopy(treehashArr, 0, treehashArr2, 0, treehashArr.length);
        return treehashArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[][] clone(byte[][] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[][] bArr2 = new byte[bArr.length];
        for (int r1 = 0; r1 != bArr.length; r1++) {
            bArr2[r1] = Arrays.clone(bArr[r1]);
        }
        return bArr2;
    }

    static Vector[][] clone(Vector[][] vectorArr) {
        if (vectorArr == null) {
            return null;
        }
        Vector[][] vectorArr2 = new Vector[vectorArr.length];
        for (int r1 = 0; r1 != vectorArr.length; r1++) {
            vectorArr2[r1] = clone(vectorArr[r1]);
        }
        return vectorArr2;
    }

    static Treehash[][] clone(Treehash[][] treehashArr) {
        if (treehashArr == null) {
            return null;
        }
        Treehash[][] treehashArr2 = new Treehash[treehashArr.length];
        for (int r1 = 0; r1 != treehashArr.length; r1++) {
            treehashArr2[r1] = clone(treehashArr[r1]);
        }
        return treehashArr2;
    }

    static byte[][][] clone(byte[][][] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[][][] bArr2 = new byte[bArr.length][];
        for (int r1 = 0; r1 != bArr.length; r1++) {
            bArr2[r1] = clone(bArr[r1]);
        }
        return bArr2;
    }
}
