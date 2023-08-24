package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* loaded from: classes3.dex */
public class VisibilityAwareImageButton extends ImageButton {
    private int userSetVisibility;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int r3) {
        super(context, attributeSet, r3);
        this.userSetVisibility = getVisibility();
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int r2) {
        internalSetVisibility(r2, true);
    }

    public final void internalSetVisibility(int r1, boolean z) {
        super.setVisibility(r1);
        if (z) {
            this.userSetVisibility = r1;
        }
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }
}
