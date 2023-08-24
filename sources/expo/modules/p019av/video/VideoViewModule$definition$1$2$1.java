package expo.modules.p019av.video;

import expo.modules.kotlin.Promise;
import expo.modules.p019av.ViewUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: VideoViewModule.kt */
@Metadata(m184d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m183d2 = {"expo/modules/av/video/VideoViewModule$definition$1$2$1", "Lexpo/modules/av/ViewUtils$VideoViewCallback;", "runWithVideoView", "", "videoView", "Lexpo/modules/av/video/VideoView;", "expo-av_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.av.video.VideoViewModule$definition$1$2$1 */
/* loaded from: classes4.dex */
public final class VideoViewModule$definition$1$2$1 implements ViewUtils.VideoViewCallback {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ boolean $shouldBeFullscreen;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VideoViewModule$definition$1$2$1(boolean z, Promise promise) {
        this.$shouldBeFullscreen = z;
        this.$promise = promise;
    }

    @Override // expo.modules.p019av.ViewUtils.VideoViewCallback
    public void runWithVideoView(final VideoView videoView) {
        Intrinsics.checkNotNullParameter(videoView, "videoView");
        final Promise promise = this.$promise;
        FullscreenVideoPlayerPresentationChangeProgressListener fullscreenVideoPlayerPresentationChangeProgressListener = new FullscreenVideoPlayerPresentationChangeProgressListener() { // from class: expo.modules.av.video.VideoViewModule$definition$1$2$1$runWithVideoView$listener$1
            @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeProgressListener, expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeListener
            public void onFullscreenPlayerDidDismiss() {
                Promise.this.resolve(videoView.getStatus());
            }

            @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeProgressListener, expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeListener
            public void onFullscreenPlayerDidPresent() {
                Promise.this.resolve(videoView.getStatus());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeProgressListener
            public void onFullscreenPlayerPresentationTriedToInterrupt() {
                Promise.this.reject("E_FULLSCREEN_VIDEO_PLAYER", "This presentation change tries to interrupt an older request. Await the old request and then try again.", null);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeProgressListener
            public void onFullscreenPlayerPresentationInterrupted() {
                Promise.this.reject("E_FULLSCREEN_VIDEO_PLAYER", "This presentation change has been interrupted by a newer change request.", null);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // expo.modules.p019av.video.FullscreenVideoPlayerPresentationChangeProgressListener
            public void onFullscreenPlayerPresentationError(String str) {
                StringBuilder sb = new StringBuilder();
                sb.append("This presentation change has been interrupted by an error.");
                if (str != null) {
                    sb.append(" ");
                    sb.append(str);
                }
                Promise.this.reject("E_FULLSCREEN_VIDEO_PLAYER", sb.toString(), null);
            }
        };
        if (this.$shouldBeFullscreen) {
            videoView.ensureFullscreenPlayerIsPresented(fullscreenVideoPlayerPresentationChangeProgressListener);
        } else {
            videoView.ensureFullscreenPlayerIsDismissed(fullscreenVideoPlayerPresentationChangeProgressListener);
        }
    }
}
