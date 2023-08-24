package com.facebook.react.views.drawer;

import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.events.NativeGestureUtil;

/* loaded from: classes.dex */
class ReactDrawerLayout extends DrawerLayout {
    public static final int DEFAULT_DRAWER_WIDTH = -1;
    private int mDrawerPosition;
    private int mDrawerWidth;

    public ReactDrawerLayout(ReactContext reactContext) {
        super(reactContext);
        this.mDrawerPosition = GravityCompat.START;
        this.mDrawerWidth = -1;
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                NativeGestureUtil.notifyNativeGestureStarted(this, motionEvent);
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            FLog.m1287w(ReactConstants.TAG, "Error intercepting touch event.", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void openDrawer() {
        openDrawer(this.mDrawerPosition);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void closeDrawer() {
        closeDrawer(this.mDrawerPosition);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDrawerPosition(int r1) {
        this.mDrawerPosition = r1;
        setDrawerProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDrawerWidth(int r1) {
        this.mDrawerWidth = r1;
        setDrawerProperties();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setDrawerProperties() {
        if (getChildCount() == 2) {
            View childAt = getChildAt(1);
            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.gravity = this.mDrawerPosition;
            layoutParams.width = this.mDrawerWidth;
            childAt.setLayoutParams(layoutParams);
            childAt.setClickable(true);
        }
    }
}
