package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.util.Arrays;

/* loaded from: classes4.dex */
public class RainbowParameterSpec implements AlgorithmParameterSpec {
    private static final int[] DEFAULT_VI = {6, 12, 17, 22, 33};

    /* renamed from: vi */
    private int[] f2524vi;

    public RainbowParameterSpec() {
        this.f2524vi = DEFAULT_VI;
    }

    public RainbowParameterSpec(int[] r1) {
        this.f2524vi = r1;
        checkParams();
    }

    private void checkParams() {
        int[] r2;
        int r3;
        int[] r0 = this.f2524vi;
        if (r0 == null) {
            throw new IllegalArgumentException("no layers defined.");
        }
        if (r0.length <= 1) {
            throw new IllegalArgumentException("Rainbow needs at least 1 layer, such that v1 < v2.");
        }
        int r02 = 0;
        do {
            r2 = this.f2524vi;
            if (r02 >= r2.length - 1) {
                return;
            }
            r3 = r2[r02];
            r02++;
        } while (r3 < r2[r02]);
        throw new IllegalArgumentException("v[i] has to be smaller than v[i+1]");
    }

    public int getDocumentLength() {
        int[] r0 = this.f2524vi;
        return r0[r0.length - 1] - r0[0];
    }

    public int getNumOfLayers() {
        return this.f2524vi.length - 1;
    }

    public int[] getVi() {
        return Arrays.clone(this.f2524vi);
    }
}
