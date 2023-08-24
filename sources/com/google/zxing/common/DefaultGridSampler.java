package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* loaded from: classes3.dex */
public final class DefaultGridSampler extends GridSampler {
    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int r7, int r8, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        return sampleGrid(bitMatrix, r7, r8, PerspectiveTransform.quadrilateralToQuadrilateral(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    @Override // com.google.zxing.common.GridSampler
    public BitMatrix sampleGrid(BitMatrix bitMatrix, int r10, int r11, PerspectiveTransform perspectiveTransform) throws NotFoundException {
        if (r10 <= 0 || r11 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        BitMatrix bitMatrix2 = new BitMatrix(r10, r11);
        int r102 = r10 * 2;
        float[] fArr = new float[r102];
        for (int r3 = 0; r3 < r11; r3++) {
            float f = r3 + 0.5f;
            for (int r6 = 0; r6 < r102; r6 += 2) {
                fArr[r6] = (r6 / 2) + 0.5f;
                fArr[r6 + 1] = f;
            }
            perspectiveTransform.transformPoints(fArr);
            checkAndNudgePoints(bitMatrix, fArr);
            for (int r4 = 0; r4 < r102; r4 += 2) {
                try {
                    if (bitMatrix.get((int) fArr[r4], (int) fArr[r4 + 1])) {
                        bitMatrix2.set(r4 / 2, r3);
                    }
                } catch (ArrayIndexOutOfBoundsException unused) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bitMatrix2;
    }
}
