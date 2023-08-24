package com.google.android.cameraview;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.imagepipeline.common.RotationOptions;

/* loaded from: classes2.dex */
class TextureViewPreview extends PreviewImpl {
    private int mDisplayOrientation;
    private final TextureView mTextureView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextureViewPreview(Context context, ViewGroup viewGroup) {
        TextureView textureView = (TextureView) View.inflate(context, C1829R.layout.texture_view, viewGroup).findViewById(C1829R.C1832id.texture_view);
        this.mTextureView = textureView;
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.google.android.cameraview.TextureViewPreview.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int r2, int r3) {
                TextureViewPreview.this.setSize(r2, r3);
                TextureViewPreview.this.configureTransform();
                TextureViewPreview.this.dispatchSurfaceChanged();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int r2, int r3) {
                TextureViewPreview.this.setSize(r2, r3);
                TextureViewPreview.this.configureTransform();
                TextureViewPreview.this.dispatchSurfaceChanged();
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                TextureViewPreview.this.setSize(0, 0);
                TextureViewPreview.this.dispatchSurfaceDestroyed();
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public void setBufferSize(int r2, int r3) {
        this.mTextureView.getSurfaceTexture().setDefaultBufferSize(r2, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public Surface getSurface() {
        return new Surface(this.mTextureView.getSurfaceTexture());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public SurfaceTexture getSurfaceTexture() {
        return this.mTextureView.getSurfaceTexture();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public View getView() {
        return this.mTextureView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public Class getOutputClass() {
        return SurfaceTexture.class;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public void setDisplayOrientation(int r1) {
        this.mDisplayOrientation = r1;
        configureTransform();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.cameraview.PreviewImpl
    public boolean isReady() {
        return this.mTextureView.getSurfaceTexture() != null;
    }

    void configureTransform() {
        Matrix matrix = new Matrix();
        int r1 = this.mDisplayOrientation;
        if (r1 % RotationOptions.ROTATE_180 == 90) {
            float width = getWidth();
            float height = getHeight();
            matrix.setPolyToPoly(new float[]{0.0f, 0.0f, width, 0.0f, 0.0f, height, width, height}, 0, this.mDisplayOrientation == 90 ? new float[]{0.0f, height, 0.0f, 0.0f, width, height, width, 0.0f} : new float[]{width, 0.0f, width, height, 0.0f, 0.0f, 0.0f, height}, 0, 4);
        } else if (r1 == 180) {
            matrix.postRotate(180.0f, getWidth() / 2, getHeight() / 2);
        }
        this.mTextureView.setTransform(matrix);
    }
}
