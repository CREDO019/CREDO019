package expo.modules.p019av.video;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Window;
import android.widget.FrameLayout;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.services.KeepAwakeManager;
import expo.modules.kotlin.AppContext;
import expo.modules.p019av.player.PlayerData;
import java.lang.ref.WeakReference;

/* renamed from: expo.modules.av.video.FullscreenVideoPlayer */
/* loaded from: classes4.dex */
public class FullscreenVideoPlayer extends Dialog {
    private WeakReference<AppContext> mAppContext;
    private final FrameLayout mContainerView;
    private Handler mKeepScreenOnHandler;
    private Runnable mKeepScreenOnUpdater;
    private FrameLayout mParent;
    private WeakReference<FullscreenVideoPlayerPresentationChangeListener> mUpdateListener;
    private final VideoView mVideoView;

    /* renamed from: expo.modules.av.video.FullscreenVideoPlayer$KeepScreenOnUpdater */
    /* loaded from: classes4.dex */
    private static class KeepScreenOnUpdater implements Runnable {
        private static final long UPDATE_KEEP_SCREEN_ON_FLAG_MS = 200;
        private final WeakReference<FullscreenVideoPlayer> mFullscreenPlayer;

        KeepScreenOnUpdater(FullscreenVideoPlayer fullscreenVideoPlayer) {
            this.mFullscreenPlayer = new WeakReference<>(fullscreenVideoPlayer);
        }

        @Override // java.lang.Runnable
        public void run() {
            FullscreenVideoPlayer fullscreenVideoPlayer = this.mFullscreenPlayer.get();
            if (fullscreenVideoPlayer != null) {
                Window window = fullscreenVideoPlayer.getWindow();
                if (window != null) {
                    boolean z = true;
                    boolean z2 = fullscreenVideoPlayer.mVideoView.getStatus().containsKey(PlayerData.STATUS_IS_PLAYING_KEY_PATH) && fullscreenVideoPlayer.mVideoView.getStatus().getBoolean(PlayerData.STATUS_IS_PLAYING_KEY_PATH);
                    AppContext appContext = (AppContext) fullscreenVideoPlayer.mAppContext.get();
                    ModuleRegistry legacyModuleRegistry = appContext != null ? appContext.getLegacyModuleRegistry() : null;
                    if (legacyModuleRegistry != null) {
                        KeepAwakeManager keepAwakeManager = (KeepAwakeManager) legacyModuleRegistry.getModule(KeepAwakeManager.class);
                        z = (keepAwakeManager == null || !keepAwakeManager.isActivated()) ? false : false;
                        if (z2 || z) {
                            window.addFlags(128);
                        } else {
                            window.addFlags(128);
                        }
                    }
                }
                fullscreenVideoPlayer.mKeepScreenOnHandler.postDelayed(this, UPDATE_KEEP_SCREEN_ON_FLAG_MS);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FullscreenVideoPlayer(Context context, VideoView videoView, AppContext appContext) {
        super(context, 16973834);
        this.mAppContext = new WeakReference<>(appContext);
        setCancelable(false);
        this.mVideoView = videoView;
        FrameLayout frameLayout = new FrameLayout(context);
        this.mContainerView = frameLayout;
        setContentView(frameLayout, generateDefaultLayoutParams());
        this.mKeepScreenOnUpdater = new KeepScreenOnUpdater(this);
        this.mKeepScreenOnHandler = new Handler();
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        super.onBackPressed();
        if (isShowing()) {
            dismiss();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        FullscreenVideoPlayerPresentationChangeListener fullscreenVideoPlayerPresentationChangeListener = this.mUpdateListener.get();
        if (fullscreenVideoPlayerPresentationChangeListener != null) {
            fullscreenVideoPlayerPresentationChangeListener.onFullscreenPlayerWillPresent();
        }
        super.show();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        this.mVideoView.setOverridingUseNativeControls(null);
        FullscreenVideoPlayerPresentationChangeListener fullscreenVideoPlayerPresentationChangeListener = this.mUpdateListener.get();
        if (fullscreenVideoPlayerPresentationChangeListener != null) {
            fullscreenVideoPlayerPresentationChangeListener.onFullscreenPlayerWillDismiss();
        }
        super.dismiss();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        FrameLayout frameLayout = (FrameLayout) this.mVideoView.getParent();
        this.mParent = frameLayout;
        frameLayout.removeView(this.mVideoView);
        this.mContainerView.addView(this.mVideoView, generateDefaultLayoutParams());
        super.onStart();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FullscreenVideoPlayerPresentationChangeListener fullscreenVideoPlayerPresentationChangeListener = this.mUpdateListener.get();
        if (fullscreenVideoPlayerPresentationChangeListener != null) {
            fullscreenVideoPlayerPresentationChangeListener.onFullscreenPlayerDidPresent();
        }
        this.mVideoView.setOverridingUseNativeControls(true);
        this.mKeepScreenOnHandler.post(this.mKeepScreenOnUpdater);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUpdateListener(FullscreenVideoPlayerPresentationChangeListener fullscreenVideoPlayerPresentationChangeListener) {
        this.mUpdateListener = new WeakReference<>(fullscreenVideoPlayerPresentationChangeListener);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        this.mKeepScreenOnHandler.removeCallbacks(this.mKeepScreenOnUpdater);
        this.mContainerView.removeView(this.mVideoView);
        this.mParent.addView(this.mVideoView, generateDefaultLayoutParams());
        this.mParent.requestLayout();
        this.mParent = null;
        super.onStop();
        FullscreenVideoPlayerPresentationChangeListener fullscreenVideoPlayerPresentationChangeListener = this.mUpdateListener.get();
        if (fullscreenVideoPlayerPresentationChangeListener != null) {
            fullscreenVideoPlayerPresentationChangeListener.onFullscreenPlayerDidDismiss();
        }
    }

    private FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return new FrameLayout.LayoutParams(-1, -1);
    }
}
