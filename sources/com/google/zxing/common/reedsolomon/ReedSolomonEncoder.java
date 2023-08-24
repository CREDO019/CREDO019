package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators;
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF genericGF) {
        this.field = genericGF;
        ArrayList arrayList = new ArrayList();
        this.cachedGenerators = arrayList;
        arrayList.add(new GenericGFPoly(genericGF, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int r9) {
        if (r9 >= this.cachedGenerators.size()) {
            List<GenericGFPoly> list = this.cachedGenerators;
            GenericGFPoly genericGFPoly = list.get(list.size() - 1);
            for (int size = this.cachedGenerators.size(); size <= r9; size++) {
                GenericGF genericGF = this.field;
                genericGFPoly = genericGFPoly.multiply(new GenericGFPoly(genericGF, new int[]{1, genericGF.exp((size - 1) + genericGF.getGeneratorBase())}));
                this.cachedGenerators.add(genericGFPoly);
            }
        }
        return this.cachedGenerators.get(r9);
    }

    public void encode(int[] r7, int r8) {
        if (r8 == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = r7.length - r8;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        GenericGFPoly buildGenerator = buildGenerator(r8);
        int[] r2 = new int[length];
        System.arraycopy(r7, 0, r2, 0, length);
        int[] coefficients = new GenericGFPoly(this.field, r2).multiplyByMonomial(r8, 1).divide(buildGenerator)[1].getCoefficients();
        int length2 = r8 - coefficients.length;
        for (int r22 = 0; r22 < length2; r22++) {
            r7[length + r22] = 0;
        }
        System.arraycopy(coefficients, 0, r7, length + length2, coefficients.length);
    }
}
