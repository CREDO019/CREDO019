package com.facebook.react.views.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class ReactViewBackgroundManager {
    private ReactViewBackgroundDrawable mReactBackgroundDrawable;
    private View mView;

    public ReactViewBackgroundManager(View view) {
        this.mView = view;
    }

    public void cleanup() {
        ViewCompat.setBackground(this.mView, null);
        this.mView = null;
        this.mReactBackgroundDrawable = null;
    }

    private ReactViewBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mReactBackgroundDrawable == null) {
            this.mReactBackgroundDrawable = new ReactViewBackgroundDrawable(this.mView.getContext());
            Drawable background = this.mView.getBackground();
            ViewCompat.setBackground(this.mView, null);
            if (background == null) {
                ViewCompat.setBackground(this.mView, this.mReactBackgroundDrawable);
            } else {
                ViewCompat.setBackground(this.mView, new LayerDrawable(new Drawable[]{this.mReactBackgroundDrawable, background}));
            }
        }
        return this.mReactBackgroundDrawable;
    }

    public void setBackgroundColor(int r2) {
        if (r2 == 0 && this.mReactBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(r2);
    }

    public void setBorderWidth(int r2, float f) {
        getOrCreateReactViewBackground().setBorderWidth(r2, f);
    }

    public void setBorderColor(int r2, float f, float f2) {
        getOrCreateReactViewBackground().setBorderColor(r2, f, f2);
    }

    public int getBorderColor(int r2) {
        return getOrCreateReactViewBackground().getBorderColor(r2);
    }

    public void setBorderRadius(float f) {
        getOrCreateReactViewBackground().setRadius(f);
    }

    public void setBorderRadius(float f, int r3) {
        getOrCreateReactViewBackground().setRadius(f, r3);
    }

    public void setBorderStyle(String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }
}
