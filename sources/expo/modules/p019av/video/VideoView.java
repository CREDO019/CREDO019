package expo.modules.p019av.video;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.Surface;
import android.widget.FrameLayout;
import expo.modules.core.Promise;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.kotlin.AppContext;
import expo.modules.p019av.AVManagerInterface;
import expo.modules.p019av.AudioEventHandler;
import expo.modules.p019av.player.PlayerData;
import expo.modules.p019av.player.PlayerDataControl;
import expo.modules.p019av.video.scalablevideoview.ScalableType;
import kotlin.Unit;

/* renamed from: expo.modules.av.video.VideoView */
/* loaded from: classes4.dex */
public class VideoView extends FrameLayout implements AudioEventHandler, FullscreenVideoPlayerPresentationChangeListener, PlayerData.FullscreenPresenter {
    private final AVManagerInterface mAVModule;
    private FullscreenVideoPlayer mFullscreenPlayer;
    private FullscreenVideoPlayerPresentationChangeProgressListener mFullscreenPlayerPresentationChangeProgressListener;
    private FullscreenVideoPlayerPresentationChangeProgressListener mFullscreenVideoPlayerPresentationOnLoadChangeListener;
    private boolean mIsLoaded;
    private ReadableArguments mLastSource;
    private MediaController mMediaController;
    private final Runnable mMediaControllerUpdater;
    private Boolean mOverridingUseNativeControls;
    private PlayerData mPlayerData;
    private ScalableType mResizeMode;
    private boolean mShouldShowFullscreenPlayerOnLoad;
    private Bundle mStatusToSet;
    private final PlayerData.StatusUpdateListener mStatusUpdateListener;
    private boolean mUseNativeControls;
    private VideoTextureView mVideoTextureView;
    private VideoViewWrapper mVideoViewWrapper;
    private Pair<Integer, Integer> mVideoWidthHeight;

    public VideoView(Context context, VideoViewWrapper videoViewWrapper, AppContext appContext) {
        super(context);
        this.mMediaControllerUpdater = new Runnable() { // from class: expo.modules.av.video.VideoView.1
            @Override // java.lang.Runnable
            public void run() {
                if (VideoView.this.mMediaController != null) {
                    VideoView.this.mMediaController.updateControls();
                }
            }
        };
        this.mStatusUpdateListener = new PlayerData.StatusUpdateListener() { // from class: expo.modules.av.video.VideoView.2
            @Override // expo.modules.p019av.player.PlayerData.StatusUpdateListener
            public void onStatusUpdate(Bundle bundle) {
                VideoView videoView = VideoView.this;
                videoView.post(videoView.mMediaControllerUpdater);
                VideoView.this.mVideoViewWrapper.getOnStatusUpdate().invoke(bundle);
            }
        };
        this.mPlayerData = null;
        this.mResizeMode = ScalableType.LEFT_TOP;
        this.mUseNativeControls = false;
        this.mOverridingUseNativeControls = null;
        this.mMediaController = null;
        this.mVideoWidthHeight = null;
        this.mFullscreenPlayerPresentationChangeProgressListener = null;
        this.mStatusToSet = new Bundle();
        this.mFullscreenPlayer = null;
        this.mVideoTextureView = null;
        this.mIsLoaded = false;
        this.mShouldShowFullscreenPlayerOnLoad = false;
        this.mFullscreenVideoPlayerPresentationOnLoadChangeListener = null;
        this.mVideoViewWrapper = videoViewWrapper;
        AVManagerInterface aVManagerInterface = (AVManagerInterface) appContext.getLegacyModuleRegistry().getModule(AVManagerInterface.class);
        this.mAVModule = aVManagerInterface;
        aVManagerInterface.registerVideoViewForAudioLifecycle(this);
        VideoTextureView videoTextureView = new VideoTextureView(context, this);
        this.mVideoTextureView = videoTextureView;
        addView(videoTextureView, generateDefaultLayoutParams());
        FullscreenVideoPlayer fullscreenVideoPlayer = new FullscreenVideoPlayer(context, this, appContext);
        this.mFullscreenPlayer = fullscreenVideoPlayer;
        fullscreenVideoPlayer.setUpdateListener(this);
        MediaController mediaController = new MediaController(getContext());
        this.mMediaController = mediaController;
        mediaController.setAnchorView(this);
        maybeUpdateMediaControllerForUseNativeControls();
    }

    public void unloadPlayerAndMediaController() {
        ensureFullscreenPlayerIsDismissed();
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.hide();
            this.mMediaController.setEnabled(false);
            this.mMediaController.setAnchorView(null);
            this.mMediaController = null;
        }
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.release();
            this.mPlayerData = null;
        }
        this.mIsLoaded = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDropViewInstance() {
        this.mAVModule.unregisterVideoViewForAudioLifecycle(this);
        unloadPlayerAndMediaController();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callOnError(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("error", str);
        this.mVideoViewWrapper.getOnError().invoke(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callOnReadyForDisplay(Pair<Integer, Integer> pair) {
        if (pair == null || !this.mIsLoaded) {
            return;
        }
        int intValue = ((Integer) pair.first).intValue();
        int intValue2 = ((Integer) pair.second).intValue();
        if (intValue == 0 || intValue2 == 0) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("width", intValue);
        bundle.putInt("height", intValue2);
        bundle.putString("orientation", intValue > intValue2 ? "landscape" : "portrait");
        Bundle bundle2 = new Bundle();
        bundle2.putBundle("naturalSize", bundle);
        bundle2.putBundle("status", this.mPlayerData.getStatus());
        this.mVideoViewWrapper.getOnReadyForDisplay().invoke(bundle2);
    }

    public void maybeUpdateMediaControllerForUseNativeControls() {
        maybeUpdateMediaControllerForUseNativeControls(true);
    }

    public void maybeUpdateMediaControllerForUseNativeControls(boolean z) {
        MediaController mediaController;
        if (this.mPlayerData == null || (mediaController = this.mMediaController) == null) {
            return;
        }
        mediaController.updateControls();
        this.mMediaController.setEnabled(shouldUseNativeControls());
        if (shouldUseNativeControls() && z) {
            this.mMediaController.show();
        } else {
            this.mMediaController.hide();
        }
    }

    public void ensureFullscreenPlayerIsPresented() {
        ensureFullscreenPlayerIsPresented(null);
    }

    public void ensureFullscreenPlayerIsPresented(FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener) {
        if (!this.mIsLoaded) {
            saveFullscreenPlayerStateForOnLoad(true, fullscreenVideoPlayerPresentationChangeProgressListener);
        } else if (this.mFullscreenPlayerPresentationChangeProgressListener != null) {
            if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
                fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerPresentationTriedToInterrupt();
            }
        } else if (isBeingPresentedFullscreen()) {
            if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
                fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerDidPresent();
            }
        } else {
            if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
                this.mFullscreenPlayerPresentationChangeProgressListener = fullscreenVideoPlayerPresentationChangeProgressListener;
            }
            this.mFullscreenPlayer.show();
        }
    }

    public void ensureFullscreenPlayerIsDismissed() {
        ensureFullscreenPlayerIsDismissed(null);
    }

    public void ensureFullscreenPlayerIsDismissed(FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener) {
        if (!this.mIsLoaded) {
            saveFullscreenPlayerStateForOnLoad(false, fullscreenVideoPlayerPresentationChangeProgressListener);
        } else if (this.mFullscreenPlayerPresentationChangeProgressListener != null) {
            if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
                fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerPresentationTriedToInterrupt();
            }
        } else if (isBeingPresentedFullscreen()) {
            if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
                this.mFullscreenPlayerPresentationChangeProgressListener = fullscreenVideoPlayerPresentationChangeProgressListener;
            }
            this.mFullscreenPlayer.dismiss();
        } else if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
            fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerDidDismiss();
        }
    }

    private void saveFullscreenPlayerStateForOnLoad(boolean z, FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener) {
        this.mShouldShowFullscreenPlayerOnLoad = z;
        FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener2 = this.mFullscreenVideoPlayerPresentationOnLoadChangeListener;
        if (fullscreenVideoPlayerPresentationChangeProgressListener2 != null) {
            fullscreenVideoPlayerPresentationChangeProgressListener2.onFullscreenPlayerPresentationInterrupted();
        }
        this.mFullscreenVideoPlayerPresentationOnLoadChangeListener = fullscreenVideoPlayerPresentationChangeProgressListener;
    }

    @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeListener
    public void onFullscreenPlayerWillPresent() {
        callFullscreenCallbackWithUpdate(FullscreenPlayerUpdate.FULLSCREEN_PLAYER_WILL_PRESENT);
        FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener = this.mFullscreenPlayerPresentationChangeProgressListener;
        if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
            fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerWillPresent();
        }
    }

    @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeListener
    public void onFullscreenPlayerDidPresent() {
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.updateControls();
        }
        callFullscreenCallbackWithUpdate(FullscreenPlayerUpdate.FULLSCREEN_PLAYER_DID_PRESENT);
        FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener = this.mFullscreenPlayerPresentationChangeProgressListener;
        if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
            fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerDidPresent();
            this.mFullscreenPlayerPresentationChangeProgressListener = null;
        }
    }

    @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeListener
    public void onFullscreenPlayerWillDismiss() {
        callFullscreenCallbackWithUpdate(FullscreenPlayerUpdate.FULLSCREEN_PLAYER_WILL_DISMISS);
        FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener = this.mFullscreenPlayerPresentationChangeProgressListener;
        if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
            fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerWillDismiss();
        }
    }

    @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeListener
    public void onFullscreenPlayerDidDismiss() {
        MediaController mediaController = this.mMediaController;
        if (mediaController != null) {
            mediaController.updateControls();
        }
        callFullscreenCallbackWithUpdate(FullscreenPlayerUpdate.FULLSCREEN_PLAYER_DID_DISMISS);
        FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener = this.mFullscreenPlayerPresentationChangeProgressListener;
        if (fullscreenVideoPlayerPresentationChangeProgressListener != null) {
            fullscreenVideoPlayerPresentationChangeProgressListener.onFullscreenPlayerDidDismiss();
            this.mFullscreenPlayerPresentationChangeProgressListener = null;
        }
    }

    private void callFullscreenCallbackWithUpdate(FullscreenPlayerUpdate fullscreenPlayerUpdate) {
        Bundle bundle = new Bundle();
        bundle.putInt("fullscreenUpdate", fullscreenPlayerUpdate.getJsValue());
        bundle.putBundle("status", getStatus());
        this.mVideoViewWrapper.getOnFullscreenUpdate().invoke(bundle);
    }

    public void setStatus(ReadableArguments readableArguments, Promise promise) {
        Bundle bundle = readableArguments.toBundle();
        this.mStatusToSet.putAll(bundle);
        if (this.mPlayerData != null) {
            new Bundle().putAll(this.mStatusToSet);
            this.mStatusToSet = new Bundle();
            this.mPlayerData.setStatus(bundle, promise);
        } else if (promise != null) {
            promise.resolve(PlayerData.getUnloadedStatus());
        }
    }

    public Bundle getStatus() {
        PlayerData playerData = this.mPlayerData;
        return playerData == null ? PlayerData.getUnloadedStatus() : playerData.getStatus();
    }

    private boolean shouldUseNativeControls() {
        Boolean bool = this.mOverridingUseNativeControls;
        if (bool != null) {
            return bool.booleanValue();
        }
        return this.mUseNativeControls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOverridingUseNativeControls(Boolean bool) {
        this.mOverridingUseNativeControls = bool;
        maybeUpdateMediaControllerForUseNativeControls();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUseNativeControls(boolean z) {
        this.mUseNativeControls = z;
        maybeUpdateMediaControllerForUseNativeControls();
    }

    private static boolean equalBundles(Bundle bundle, Bundle bundle2) {
        if (bundle.size() == bundle2.size() && bundle.keySet().containsAll(bundle2.keySet())) {
            for (String str : bundle.keySet()) {
                Object obj = bundle.get(str);
                Object obj2 = bundle2.get(str);
                if ((obj instanceof Bundle) && (obj2 instanceof Bundle)) {
                    if (!equalBundles((Bundle) obj, (Bundle) obj2)) {
                        return false;
                    }
                } else if (obj == null) {
                    if (obj2 != null) {
                        return false;
                    }
                } else if (!obj.equals(obj2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void setSource(ReadableArguments readableArguments) {
        ReadableArguments readableArguments2 = this.mLastSource;
        if (readableArguments2 == null || !equalBundles(readableArguments2.toBundle(), readableArguments.toBundle())) {
            this.mLastSource = readableArguments;
            setSource(readableArguments, null, null);
        }
    }

    public void setSource(ReadableArguments readableArguments, ReadableArguments readableArguments2, final Promise promise) {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            this.mStatusToSet.putAll(playerData.getStatus());
            this.mPlayerData.release();
            this.mPlayerData = null;
            this.mIsLoaded = false;
        }
        if (readableArguments2 != null) {
            this.mStatusToSet.putAll(readableArguments2.toBundle());
        }
        if ((readableArguments != null ? readableArguments.getString("uri") : null) == null) {
            if (promise != null) {
                promise.resolve(PlayerData.getUnloadedStatus());
                return;
            }
            return;
        }
        this.mVideoViewWrapper.getOnLoadStart().invoke(Unit.INSTANCE);
        Bundle bundle = new Bundle();
        bundle.putAll(this.mStatusToSet);
        this.mStatusToSet = new Bundle();
        PlayerData createUnloadedPlayerData = PlayerData.createUnloadedPlayerData(this.mAVModule, getContext(), readableArguments, bundle);
        this.mPlayerData = createUnloadedPlayerData;
        createUnloadedPlayerData.setErrorListener(new PlayerData.ErrorListener() { // from class: expo.modules.av.video.VideoView.3
            @Override // expo.modules.p019av.player.PlayerData.ErrorListener
            public void onError(String str) {
                VideoView.this.unloadPlayerAndMediaController();
                VideoView.this.callOnError(str);
            }
        });
        this.mPlayerData.setVideoSizeUpdateListener(new PlayerData.VideoSizeUpdateListener() { // from class: expo.modules.av.video.VideoView.4
            @Override // expo.modules.p019av.player.PlayerData.VideoSizeUpdateListener
            public void onVideoSizeUpdate(Pair<Integer, Integer> pair) {
                VideoView.this.mVideoTextureView.scaleVideoSize(pair, VideoView.this.mResizeMode);
                VideoView.this.mVideoWidthHeight = pair;
                VideoView.this.callOnReadyForDisplay(pair);
            }
        });
        this.mPlayerData.setFullscreenPresenter(this);
        this.mPlayerData.load(bundle, new PlayerData.LoadCompletionListener() { // from class: expo.modules.av.video.VideoView.5
            @Override // expo.modules.p019av.player.PlayerData.LoadCompletionListener
            public void onLoadSuccess(Bundle bundle2) {
                VideoView.this.mIsLoaded = true;
                VideoView.this.mVideoTextureView.scaleVideoSize(VideoView.this.mPlayerData.getVideoWidthHeight(), VideoView.this.mResizeMode);
                if (VideoView.this.mVideoTextureView.isAttachedToWindow()) {
                    VideoView.this.mPlayerData.tryUpdateVideoSurface(VideoView.this.mVideoTextureView.getSurface());
                }
                if (promise != null) {
                    Bundle bundle3 = new Bundle();
                    bundle3.putAll(bundle2);
                    promise.resolve(bundle3);
                }
                VideoView.this.mPlayerData.setStatusUpdateListener(VideoView.this.mStatusUpdateListener);
                if (VideoView.this.mMediaController == null) {
                    VideoView.this.mMediaController = new MediaController(VideoView.this.getContext());
                }
                VideoView.this.mMediaController.setMediaPlayer(new PlayerDataControl(VideoView.this.mPlayerData));
                VideoView.this.mMediaController.setAnchorView(VideoView.this);
                VideoView.this.maybeUpdateMediaControllerForUseNativeControls(false);
                VideoView.this.mVideoViewWrapper.getOnLoad().invoke(bundle2);
                if (VideoView.this.mFullscreenVideoPlayerPresentationOnLoadChangeListener != null) {
                    FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener = VideoView.this.mFullscreenVideoPlayerPresentationOnLoadChangeListener;
                    VideoView.this.mFullscreenVideoPlayerPresentationOnLoadChangeListener = null;
                    if (VideoView.this.mShouldShowFullscreenPlayerOnLoad) {
                        VideoView.this.ensureFullscreenPlayerIsPresented(fullscreenVideoPlayerPresentationChangeProgressListener);
                    } else {
                        VideoView.this.ensureFullscreenPlayerIsDismissed(fullscreenVideoPlayerPresentationChangeProgressListener);
                    }
                }
                VideoView videoView = VideoView.this;
                videoView.callOnReadyForDisplay(videoView.mVideoWidthHeight);
            }

            @Override // expo.modules.p019av.player.PlayerData.LoadCompletionListener
            public void onLoadError(String str) {
                if (VideoView.this.mFullscreenVideoPlayerPresentationOnLoadChangeListener != null) {
                    VideoView.this.mFullscreenVideoPlayerPresentationOnLoadChangeListener.onFullscreenPlayerPresentationError(str);
                    VideoView.this.mFullscreenVideoPlayerPresentationOnLoadChangeListener = null;
                }
                VideoView.this.mShouldShowFullscreenPlayerOnLoad = false;
                VideoView.this.unloadPlayerAndMediaController();
                Promise promise2 = promise;
                if (promise2 != null) {
                    promise2.reject("E_VIDEO_NOTCREATED", str);
                }
                VideoView.this.callOnError(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setResizeMode(ScalableType scalableType) {
        if (this.mResizeMode != scalableType) {
            this.mResizeMode = scalableType;
            PlayerData playerData = this.mPlayerData;
            if (playerData != null) {
                this.mVideoTextureView.scaleVideoSize(playerData.getVideoWidthHeight(), this.mResizeMode);
            }
        }
    }

    private int getReactId() {
        return this.mVideoViewWrapper.getId();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MediaController mediaController;
        if (shouldUseNativeControls() && (mediaController = this.mMediaController) != null) {
            mediaController.show();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        PlayerData playerData;
        super.onLayout(z, r2, r3, r4, r5);
        if (!z || (playerData = this.mPlayerData) == null) {
            return;
        }
        this.mVideoTextureView.scaleVideoSize(playerData.getVideoWidthHeight(), this.mResizeMode);
    }

    public void tryUpdateVideoSurface(Surface surface) {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.tryUpdateVideoSurface(surface);
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void pauseImmediately() {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.pauseImmediately();
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public boolean requiresAudioFocus() {
        PlayerData playerData = this.mPlayerData;
        return playerData != null && playerData.requiresAudioFocus();
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void updateVolumeMuteAndDuck() {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.updateVolumeMuteAndDuck();
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void handleAudioFocusInterruptionBegan() {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.handleAudioFocusInterruptionBegan();
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void handleAudioFocusGained() {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.handleAudioFocusGained();
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void onPause() {
        if (this.mPlayerData != null) {
            ensureFullscreenPlayerIsDismissed();
            this.mPlayerData.onPause();
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void onResume() {
        PlayerData playerData = this.mPlayerData;
        if (playerData != null) {
            playerData.onResume();
        }
        this.mVideoTextureView.onResume();
    }

    @Override // expo.modules.p019av.player.PlayerData.FullscreenPresenter
    public boolean isBeingPresentedFullscreen() {
        return this.mFullscreenPlayer.isShowing();
    }

    @Override // expo.modules.p019av.player.PlayerData.FullscreenPresenter
    public void setFullscreenMode(boolean z) {
        if (z) {
            ensureFullscreenPlayerIsPresented();
        } else {
            ensureFullscreenPlayerIsDismissed();
        }
    }
}
