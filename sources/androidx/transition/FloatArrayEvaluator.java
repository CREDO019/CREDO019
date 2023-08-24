package androidx.transition;

import android.animation.TypeEvaluator;

/* loaded from: classes.dex */
class FloatArrayEvaluator implements TypeEvaluator<float[]> {
    private float[] mArray;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatArrayEvaluator(float[] fArr) {
        this.mArray = fArr;
    }

    @Override // android.animation.TypeEvaluator
    public float[] evaluate(float f, float[] fArr, float[] fArr2) {
        float[] fArr3 = this.mArray;
        if (fArr3 == null) {
            fArr3 = new float[fArr.length];
        }
        for (int r1 = 0; r1 < fArr3.length; r1++) {
            float f2 = fArr[r1];
            fArr3[r1] = f2 + ((fArr2[r1] - f2) * f);
        }
        return fArr3;
    }
}
