package expo.modules.p019av.video;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import expo.modules.p019av.video.scalablevideoview.ScalableType;
import expo.modules.p019av.video.scalablevideoview.ScaleManager;

/* renamed from: expo.modules.av.video.VideoTextureView */
/* loaded from: classes4.dex */
public class VideoTextureView extends TextureView implements TextureView.SurfaceTextureListener {
    private boolean mIsAttachedToWindow;
    private Surface mSurface;
    private VideoView mVideoView;
    private boolean mVisible;

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int r2, int r3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public VideoTextureView(Context context, VideoView videoView) {
        super(context, null, 0);
        this.mIsAttachedToWindow = false;
        this.mSurface = null;
        this.mVisible = true;
        this.mVideoView = videoView;
        setSurfaceTextureListener(this);
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttachedToWindow;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public void scaleVideoSize(Pair<Integer, Integer> pair, ScalableType scalableType) {
        Matrix scaleMatrix;
        int intValue = ((Integer) pair.first).intValue();
        int intValue2 = ((Integer) pair.second).intValue();
        if (intValue == 0 || intValue2 == 0 || (scaleMatrix = new ScaleManager(new Size(getWidth(), getHeight()), new Size(intValue, intValue2)).getScaleMatrix(scalableType)) == null) {
            return;
        }
        Matrix matrix = new Matrix();
        getTransform(matrix);
        if (scaleMatrix.equals(matrix)) {
            return;
        }
        setTransform(scaleMatrix);
        invalidate();
    }

    public void onResume() {
        if (this.mVisible) {
            onVisibilityChanged(this, 4);
            onVisibilityChanged(this, 0);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int r2, int r3) {
        Surface surface = new Surface(surfaceTexture);
        this.mSurface = surface;
        this.mVideoView.tryUpdateVideoSurface(surface);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mSurface = null;
        this.mVideoView.tryUpdateVideoSurface(null);
        return true;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.TextureView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttachedToWindow = true;
        this.mVideoView.tryUpdateVideoSurface(this.mSurface);
    }

    @Override // android.view.TextureView, android.view.View
    protected void onVisibilityChanged(View view, int r2) {
        super.onVisibilityChanged(view, r2);
        this.mVisible = r2 == 0;
    }
}
