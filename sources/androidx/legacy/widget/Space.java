package androidx.legacy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
/* loaded from: classes.dex */
public class Space extends View {
    @Override // android.view.View
    @Deprecated
    public void draw(Canvas canvas) {
    }

    @Deprecated
    public Space(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        if (getVisibility() == 0) {
            setVisibility(4);
        }
    }

    @Deprecated
    public Space(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Deprecated
    public Space(Context context) {
        this(context, null);
    }

    private static int getDefaultSize2(int r2, int r3) {
        int mode = View.MeasureSpec.getMode(r3);
        int size = View.MeasureSpec.getSize(r3);
        if (mode != Integer.MIN_VALUE) {
            return mode != 1073741824 ? r2 : size;
        }
        return Math.min(r2, size);
    }

    @Override // android.view.View
    @Deprecated
    protected void onMeasure(int r2, int r3) {
        setMeasuredDimension(getDefaultSize2(getSuggestedMinimumWidth(), r2), getDefaultSize2(getSuggestedMinimumHeight(), r3));
    }
}
