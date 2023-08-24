package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
public class GMSSParameters {

    /* renamed from: K */
    private int[] f2408K;
    private int[] heightOfTrees;
    private int numOfLayers;
    private int[] winternitzParameter;

    public GMSSParameters(int r6) throws IllegalArgumentException {
        if (r6 <= 10) {
            init(1, new int[]{10}, new int[]{3}, new int[]{2});
        } else if (r6 <= 20) {
            init(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2});
        } else {
            init(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2});
        }
    }

    public GMSSParameters(int r1, int[] r2, int[] r3, int[] r4) throws IllegalArgumentException {
        init(r1, r2, r3, r4);
    }

    private void init(int r7, int[] r8, int[] r9, int[] r10) throws IllegalArgumentException {
        String str;
        boolean z;
        this.numOfLayers = r7;
        if (r7 == r9.length && r7 == r8.length && r7 == r10.length) {
            z = true;
            str = "";
        } else {
            str = "Unexpected parameterset format";
            z = false;
        }
        for (int r2 = 0; r2 < this.numOfLayers; r2++) {
            if (r10[r2] < 2 || (r8[r2] - r10[r2]) % 2 != 0) {
                str = "Wrong parameter K (K >= 2 and H-K even required)!";
                z = false;
            }
            if (r8[r2] < 4 || r9[r2] < 2) {
                str = "Wrong parameter H or w (H > 3 and w > 1 required)!";
                z = false;
            }
        }
        if (!z) {
            throw new IllegalArgumentException(str);
        }
        this.heightOfTrees = Arrays.clone(r8);
        this.winternitzParameter = Arrays.clone(r9);
        this.f2408K = Arrays.clone(r10);
    }

    public int[] getHeightOfTrees() {
        return Arrays.clone(this.heightOfTrees);
    }

    public int[] getK() {
        return Arrays.clone(this.f2408K);
    }

    public int getNumOfLayers() {
        return this.numOfLayers;
    }

    public int[] getWinternitzParameter() {
        return Arrays.clone(this.winternitzParameter);
    }
}
