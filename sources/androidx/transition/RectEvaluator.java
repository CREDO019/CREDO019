package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* loaded from: classes.dex */
class RectEvaluator implements TypeEvaluator<Rect> {
    private Rect mRect;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectEvaluator() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RectEvaluator(Rect rect) {
        this.mRect = rect;
    }

    @Override // android.animation.TypeEvaluator
    public Rect evaluate(float f, Rect rect, Rect rect2) {
        int r0 = rect.left + ((int) ((rect2.left - rect.left) * f));
        int r1 = rect.top + ((int) ((rect2.top - rect.top) * f));
        int r2 = rect.right + ((int) ((rect2.right - rect.right) * f));
        int r3 = rect.bottom + ((int) ((rect2.bottom - rect.bottom) * f));
        Rect rect3 = this.mRect;
        if (rect3 == null) {
            return new Rect(r0, r1, r2, r3);
        }
        rect3.set(r0, r1, r2, r3);
        return this.mRect;
    }
}
