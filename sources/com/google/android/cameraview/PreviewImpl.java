package com.google.android.cameraview;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;

/* loaded from: classes2.dex */
abstract class PreviewImpl {
    private Callback mCallback;
    private int mHeight;
    private int mWidth;

    /* loaded from: classes2.dex */
    interface Callback {
        void onSurfaceChanged();

        void onSurfaceDestroyed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Class getOutputClass();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Surface getSurface();

    /* JADX INFO: Access modifiers changed from: package-private */
    public SurfaceHolder getSurfaceHolder() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getSurfaceTexture() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract View getView();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isReady();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBufferSize(int r1, int r2) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setDisplayOrientation(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dispatchSurfaceChanged() {
        this.mCallback.onSurfaceChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dispatchSurfaceDestroyed() {
        this.mCallback.onSurfaceDestroyed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSize(int r1, int r2) {
        this.mWidth = r1;
        this.mHeight = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getWidth() {
        return this.mWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getHeight() {
        return this.mHeight;
    }
}
