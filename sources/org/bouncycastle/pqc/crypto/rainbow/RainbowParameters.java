package org.bouncycastle.pqc.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;

/* loaded from: classes3.dex */
public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;

    /* renamed from: vi */
    private int[] f2493vi;

    public RainbowParameters() {
        int[] r0 = {6, 12, 17, 22, 33};
        this.DEFAULT_VI = r0;
        this.f2493vi = r0;
    }

    public RainbowParameters(int[] r2) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.f2493vi = r2;
        checkParams();
    }

    private void checkParams() {
        int[] r2;
        int r3;
        int[] r0 = this.f2493vi;
        if (r0 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (r0.length <= 1) {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int r02 = 0;
        do {
            r2 = this.f2493vi;
            if (r02 >= r2.length - 1) {
                return;
            }
            r3 = r2[r02];
            r02++;
        } while (r3 < r2[r02]);
        throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
    }

    public int getDocLength() {
        int[] r0 = this.f2493vi;
        return r0[r0.length - 1] - r0[0];
    }

    public int getNumOfLayers() {
        return this.f2493vi.length - 1;
    }

    public int[] getVi() {
        return this.f2493vi;
    }
}
