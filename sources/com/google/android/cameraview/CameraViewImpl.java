package com.google.android.cameraview;

import android.graphics.SurfaceTexture;
import android.media.CamcorderProfile;
import android.view.View;
import java.util.Set;
import java.util.SortedSet;

/* loaded from: classes2.dex */
abstract class CameraViewImpl {
    protected final Callback mCallback;
    protected final PreviewImpl mPreview;

    /* loaded from: classes2.dex */
    interface Callback {
        void onCameraClosed();

        void onCameraOpened();

        void onFramePreview(byte[] bArr, int r2, int r3, int r4);

        void onMountError();

        void onPictureTaken(byte[] bArr);

        void onVideoRecorded(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract AspectRatio getAspectRatio();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getAutoFocus();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract SortedSet<Size> getAvailablePictureSizes(AspectRatio aspectRatio);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getCameraId();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getFacing();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getFlash();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getFocusDepth();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Size getPictureSize();

    public abstract Size getPreviewSize();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean getScanning();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Set<AspectRatio> getSupportedAspectRatios();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getWhiteBalance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract float getZoom();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isCameraOpened();

    public abstract void pausePreview();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean record(String str, int r2, int r3, boolean z, CamcorderProfile camcorderProfile);

    public abstract void resumePreview();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean setAspectRatio(AspectRatio aspectRatio);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setAutoFocus(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setDisplayOrientation(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFacing(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFlash(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setFocusDepth(float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setPictureSize(Size size);

    public abstract void setPreviewTexture(SurfaceTexture surfaceTexture);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setScanning(boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setWhiteBalance(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setZoom(float f);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean start();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stop();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void stopRecording();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void takePicture();

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraViewImpl(Callback callback, PreviewImpl previewImpl) {
        this.mCallback = callback;
        this.mPreview = previewImpl;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getView() {
        return this.mPreview.getView();
    }
}
