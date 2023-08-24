package expo.modules.p019av.player;

import android.content.Context;
import android.media.audiofx.Visualizer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.UiThreadUtil;
import com.google.firebase.messaging.ServiceStarter;
import expo.modules.core.Promise;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.interfaces.permissions.PermissionsStatus;
import expo.modules.p019av.AVManagerInterface;
import expo.modules.p019av.AudioEventHandler;
import expo.modules.p019av.AudioFocusNotAcquiredException;
import expo.modules.p019av.progress.AndroidLooperTimeMachine;
import expo.modules.p019av.progress.ProgressLooper;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;

/* renamed from: expo.modules.av.player.PlayerData */
/* loaded from: classes4.dex */
public abstract class PlayerData implements AudioEventHandler {
    static final String STATUS_ANDROID_IMPLEMENTATION_KEY_PATH = "androidImplementation";
    static final String STATUS_DID_JUST_FINISH_KEY_PATH = "didJustFinish";
    static final String STATUS_DURATION_MILLIS_KEY_PATH = "durationMillis";
    static final String STATUS_HEADERS_KEY_PATH = "headers";
    static final String STATUS_IS_BUFFERING_KEY_PATH = "isBuffering";
    static final String STATUS_IS_LOADED_KEY_PATH = "isLoaded";
    static final String STATUS_IS_LOOPING_KEY_PATH = "isLooping";
    static final String STATUS_IS_MUTED_KEY_PATH = "isMuted";
    public static final String STATUS_IS_PLAYING_KEY_PATH = "isPlaying";
    static final String STATUS_OVERRIDING_EXTENSION_KEY_PATH = "overridingExtension";
    static final String STATUS_PLAYABLE_DURATION_MILLIS_KEY_PATH = "playableDurationMillis";
    static final String STATUS_POSITION_MILLIS_KEY_PATH = "positionMillis";
    static final String STATUS_PROGRESS_UPDATE_INTERVAL_MILLIS_KEY_PATH = "progressUpdateIntervalMillis";
    static final String STATUS_RATE_KEY_PATH = "rate";
    static final String STATUS_SHOULD_CORRECT_PITCH_KEY_PATH = "shouldCorrectPitch";
    static final String STATUS_SHOULD_PLAY_KEY_PATH = "shouldPlay";
    public static final String STATUS_URI_KEY_PATH = "uri";
    static final String STATUS_VOLUME_KEY_PATH = "volume";
    static final String STATUS_VOLUME_PAN_KEY_PATH = "audioPan";
    final AVManagerInterface mAVModule;
    final Map<String, Object> mRequestHeaders;
    private final WeakReference<UIManager> mUiManager;
    final Uri mUri;
    private ProgressLooper mProgressUpdater = new ProgressLooper(new AndroidLooperTimeMachine());
    private Visualizer mVisualizer = null;
    private FullscreenPresenter mFullscreenPresenter = null;
    private StatusUpdateListener mStatusUpdateListener = null;
    ErrorListener mErrorListener = null;
    VideoSizeUpdateListener mVideoSizeUpdateListener = null;
    private int mProgressUpdateIntervalMillis = ServiceStarter.ERROR_UNKNOWN;
    boolean mShouldPlay = false;
    float mRate = 1.0f;
    boolean mShouldCorrectPitch = false;
    float mVolume = 1.0f;
    float mPan = 0.0f;
    boolean mIsMuted = false;
    private final HybridData mHybridData = initHybrid();

    /* renamed from: expo.modules.av.player.PlayerData$ErrorListener */
    /* loaded from: classes4.dex */
    public interface ErrorListener {
        void onError(String str);
    }

    /* renamed from: expo.modules.av.player.PlayerData$FullscreenPresenter */
    /* loaded from: classes4.dex */
    public interface FullscreenPresenter {
        boolean isBeingPresentedFullscreen();

        void setFullscreenMode(boolean z);
    }

    /* renamed from: expo.modules.av.player.PlayerData$LoadCompletionListener */
    /* loaded from: classes4.dex */
    public interface LoadCompletionListener {
        void onLoadError(String str);

        void onLoadSuccess(Bundle bundle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: expo.modules.av.player.PlayerData$SetStatusCompletionListener */
    /* loaded from: classes4.dex */
    public interface SetStatusCompletionListener {
        void onSetStatusComplete();

        void onSetStatusError(String str);
    }

    /* renamed from: expo.modules.av.player.PlayerData$StatusUpdateListener */
    /* loaded from: classes4.dex */
    public interface StatusUpdateListener {
        void onStatusUpdate(Bundle bundle);
    }

    /* renamed from: expo.modules.av.player.PlayerData$VideoSizeUpdateListener */
    /* loaded from: classes4.dex */
    public interface VideoSizeUpdateListener {
        void onVideoSizeUpdate(Pair<Integer, Integer> pair);
    }

    private native HybridData initHybrid();

    abstract void applyNewStatus(Integer num, Boolean bool) throws AudioFocusNotAcquiredException, IllegalStateException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getAudioSessionId();

    protected double getCurrentPositionSeconds() {
        return 0.0d;
    }

    abstract void getExtraStatusFields(Bundle bundle);

    abstract String getImplementationName();

    public abstract Pair<Integer, Integer> getVideoWidthHeight();

    abstract boolean isLoaded();

    public abstract void load(Bundle bundle, LoadCompletionListener loadCompletionListener);

    abstract void playPlayerWithRateAndMuteIfNecessary() throws AudioFocusNotAcquiredException;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: sampleBufferCallback */
    public native void m1592lambda$emitSampleBufferEvent$2$expomodulesavplayerPlayerData(byte[] bArr, double d);

    abstract boolean shouldContinueUpdatingProgress();

    public abstract void tryUpdateVideoSurface(Surface surface);

    public static Bundle getUnloadedStatus() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(STATUS_IS_LOADED_KEY_PATH, false);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlayerData(AVManagerInterface aVManagerInterface, Uri uri, Map<String, Object> map) {
        this.mRequestHeaders = map;
        this.mAVModule = aVManagerInterface;
        this.mUri = uri;
        this.mUiManager = new WeakReference<>((UIManager) aVManagerInterface.getModuleRegistry().getModule(UIManager.class));
    }

    protected void finalize() throws Throwable {
        super.finalize();
        Visualizer visualizer = this.mVisualizer;
        if (visualizer != null) {
            visualizer.release();
            this.mVisualizer = null;
        }
        this.mHybridData.resetNative();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: setEnableSampleBufferCallback */
    public void m227xeaa018ff(final boolean z) {
        if (!UiThreadUtil.isOnUiThread()) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: expo.modules.av.player.PlayerData$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerData.this.m227xeaa018ff(z);
                }
            });
        } else if (z) {
            try {
                if (!this.mAVModule.hasAudioPermission()) {
                    this.mAVModule.requestAudioPermission(new PermissionsResponseListener() { // from class: expo.modules.av.player.PlayerData$$ExternalSyntheticLambda0
                        @Override // expo.modules.interfaces.permissions.PermissionsResponseListener
                        public final void onResult(Map map) {
                            PlayerData.this.m226x5178d8c0(map);
                        }
                    });
                    return;
                }
                int audioSessionId = getAudioSessionId();
                Log.i("PlayerData", "Initializing Visualizer for Audio Session #" + audioSessionId + "...");
                Visualizer visualizer = new Visualizer(audioSessionId);
                this.mVisualizer = visualizer;
                visualizer.setEnabled(false);
                this.mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
                int min = Math.min(Visualizer.getMaxCaptureRate(), 10000);
                this.mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() { // from class: expo.modules.av.player.PlayerData.1
                    @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
                    public void onFftDataCapture(Visualizer visualizer2, byte[] bArr, int r3) {
                    }

                    @Override // android.media.audiofx.Visualizer.OnDataCaptureListener
                    public void onWaveFormDataCapture(Visualizer visualizer2, byte[] bArr, int r5) {
                        if (PlayerData.this.mShouldPlay) {
                            PlayerData playerData = PlayerData.this;
                            playerData.emitSampleBufferEvent(bArr, playerData.getCurrentPositionSeconds());
                        }
                    }
                }, min, true, false);
                this.mVisualizer.setEnabled(true);
                Log.i("PlayerData", "Visualizer initialized with a capture rate of " + min);
            } catch (Throwable th) {
                Log.e("PlayerData", "Failed to initialize Visualizer! " + th.getLocalizedMessage());
                th.printStackTrace();
            }
        } else {
            Visualizer visualizer2 = this.mVisualizer;
            if (visualizer2 != null) {
                visualizer2.setEnabled(false);
                this.mVisualizer.release();
            }
            this.mVisualizer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setEnableSampleBufferCallback$1$expo-modules-av-player-PlayerData */
    public /* synthetic */ void m226x5178d8c0(Map map) {
        PermissionsResponse permissionsResponse = (PermissionsResponse) map.get("android.permission.RECORD_AUDIO");
        if (permissionsResponse == null) {
            return;
        }
        if (permissionsResponse.getStatus() == PermissionsStatus.GRANTED) {
            m227xeaa018ff(true);
        } else if (permissionsResponse.getCanAskAgain()) {
        } else {
            Log.e("PlayerData", "Cannot initialize Sample Data Callback (Visualizer) when RECORD_AUDIO permission is not granted!");
        }
    }

    public static PlayerData createUnloadedPlayerData(AVManagerInterface aVManagerInterface, Context context, ReadableArguments readableArguments, Bundle bundle) {
        String string = readableArguments.getString("uri");
        Map map = readableArguments.containsKey(STATUS_HEADERS_KEY_PATH) ? readableArguments.getMap(STATUS_HEADERS_KEY_PATH) : null;
        String string2 = readableArguments.containsKey(STATUS_OVERRIDING_EXTENSION_KEY_PATH) ? readableArguments.getString(STATUS_OVERRIDING_EXTENSION_KEY_PATH) : null;
        Uri parse = Uri.parse(string);
        if (bundle.containsKey(STATUS_ANDROID_IMPLEMENTATION_KEY_PATH) && bundle.getString(STATUS_ANDROID_IMPLEMENTATION_KEY_PATH).equals("MediaPlayer")) {
            return new MediaPlayerData(aVManagerInterface, context, parse, map);
        }
        return new SimpleExoPlayerData(aVManagerInterface, context, parse, string2, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitSampleBufferEvent(final byte[] bArr, final double d) {
        UIManager uIManager = this.mUiManager.get();
        if (uIManager != null) {
            uIManager.runOnClientCodeQueueThread(new Runnable() { // from class: expo.modules.av.player.PlayerData$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    PlayerData.this.m1592lambda$emitSampleBufferEvent$2$expomodulesavplayerPlayerData(bArr, d);
                }
            });
        }
    }

    public void release() {
        Visualizer visualizer = this.mVisualizer;
        if (visualizer != null) {
            visualizer.setDataCaptureListener(null, 0, false, false);
            this.mVisualizer.setEnabled(false);
            this.mVisualizer.release();
            this.mVisualizer = null;
        }
    }

    private void callStatusUpdateListenerWithStatus(Bundle bundle) {
        StatusUpdateListener statusUpdateListener = this.mStatusUpdateListener;
        if (statusUpdateListener != null) {
            statusUpdateListener.onStatusUpdate(bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void callStatusUpdateListenerWithDidJustFinish() {
        Bundle status = getStatus();
        status.putBoolean(STATUS_DID_JUST_FINISH_KEY_PATH, true);
        callStatusUpdateListenerWithStatus(status);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void callStatusUpdateListener() {
        callStatusUpdateListenerWithStatus(getStatus());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void stopUpdatingProgressIfNecessary() {
        this.mProgressUpdater.stopLooping();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void beginUpdatingProgressIfNecessary() {
        if (!shouldContinueUpdatingProgress() || this.mProgressUpdater.isLooping() || this.mStatusUpdateListener == null) {
            return;
        }
        this.mProgressUpdater.loop(this.mProgressUpdateIntervalMillis, new Functions() { // from class: expo.modules.av.player.PlayerData$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Functions
            public final Object invoke() {
                return PlayerData.this.m228xe8d7d53e();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$beginUpdatingProgressIfNecessary$3$expo-modules-av-player-PlayerData */
    public /* synthetic */ Unit m228xe8d7d53e() {
        callStatusUpdateListener();
        return null;
    }

    public final void setStatusUpdateListener(StatusUpdateListener statusUpdateListener) {
        StatusUpdateListener statusUpdateListener2 = this.mStatusUpdateListener;
        this.mStatusUpdateListener = statusUpdateListener;
        if (statusUpdateListener != null) {
            beginUpdatingProgressIfNecessary();
            if (statusUpdateListener2 == null) {
                callStatusUpdateListener();
                return;
            }
            return;
        }
        stopUpdatingProgressIfNecessary();
    }

    public final void setErrorListener(ErrorListener errorListener) {
        this.mErrorListener = errorListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean shouldPlayerPlay() {
        return this.mShouldPlay && ((double) this.mRate) > 0.0d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setStatusWithListener(Bundle bundle, SetStatusCompletionListener setStatusCompletionListener) {
        if (bundle.containsKey(STATUS_PROGRESS_UPDATE_INTERVAL_MILLIS_KEY_PATH) && this.mProgressUpdateIntervalMillis != ((int) bundle.getDouble(STATUS_PROGRESS_UPDATE_INTERVAL_MILLIS_KEY_PATH))) {
            this.mProgressUpdateIntervalMillis = (int) bundle.getDouble(STATUS_PROGRESS_UPDATE_INTERVAL_MILLIS_KEY_PATH);
            if (this.mProgressUpdater.isLooping()) {
                stopUpdatingProgressIfNecessary();
                beginUpdatingProgressIfNecessary();
            }
        }
        Integer valueOf = bundle.containsKey(STATUS_POSITION_MILLIS_KEY_PATH) ? Integer.valueOf((int) bundle.getDouble(STATUS_POSITION_MILLIS_KEY_PATH)) : null;
        if (bundle.containsKey(STATUS_SHOULD_PLAY_KEY_PATH)) {
            this.mShouldPlay = bundle.getBoolean(STATUS_SHOULD_PLAY_KEY_PATH);
        }
        if (bundle.containsKey(STATUS_RATE_KEY_PATH)) {
            this.mRate = (float) bundle.getDouble(STATUS_RATE_KEY_PATH);
        }
        if (bundle.containsKey(STATUS_SHOULD_CORRECT_PITCH_KEY_PATH)) {
            this.mShouldCorrectPitch = bundle.getBoolean(STATUS_SHOULD_CORRECT_PITCH_KEY_PATH);
        }
        if (bundle.containsKey(STATUS_VOLUME_KEY_PATH)) {
            this.mVolume = (float) bundle.getDouble(STATUS_VOLUME_KEY_PATH);
        }
        if (bundle.containsKey(STATUS_VOLUME_PAN_KEY_PATH)) {
            this.mPan = (float) bundle.getDouble(STATUS_VOLUME_PAN_KEY_PATH);
        }
        if (bundle.containsKey(STATUS_IS_MUTED_KEY_PATH)) {
            this.mIsMuted = bundle.getBoolean(STATUS_IS_MUTED_KEY_PATH);
        }
        try {
            applyNewStatus(valueOf, bundle.containsKey(STATUS_IS_LOOPING_KEY_PATH) ? Boolean.valueOf(bundle.getBoolean(STATUS_IS_LOOPING_KEY_PATH)) : null);
            this.mAVModule.abandonAudioFocusIfUnused();
            setStatusCompletionListener.onSetStatusComplete();
        } catch (Throwable th) {
            this.mAVModule.abandonAudioFocusIfUnused();
            setStatusCompletionListener.onSetStatusError(th.toString());
        }
    }

    public final void setStatus(Bundle bundle, final Promise promise) {
        if (bundle == null) {
            if (promise != null) {
                promise.reject("E_AV_SETSTATUS", "Cannot set null status.");
                return;
            }
            return;
        }
        try {
            setStatusWithListener(bundle, new SetStatusCompletionListener() { // from class: expo.modules.av.player.PlayerData.2
                @Override // expo.modules.p019av.player.PlayerData.SetStatusCompletionListener
                public void onSetStatusComplete() {
                    Promise promise2 = promise;
                    if (promise2 == null) {
                        PlayerData.this.callStatusUpdateListener();
                    } else {
                        promise2.resolve(PlayerData.this.getStatus());
                    }
                }

                @Override // expo.modules.p019av.player.PlayerData.SetStatusCompletionListener
                public void onSetStatusError(String str) {
                    Promise promise2 = promise;
                    if (promise2 == null) {
                        PlayerData.this.callStatusUpdateListener();
                    } else {
                        promise2.reject("E_AV_SETSTATUS", str);
                    }
                }
            });
        } catch (Throwable th) {
            if (promise != null) {
                promise.reject("E_AV_SETSTATUS", "Encountered an error while setting status!", th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getClippedIntegerForValue(Integer num, Integer num2, Integer num3) {
        if (num2 != null && num.intValue() < num2.intValue()) {
            num = num2;
        } else if (num3 != null && num.intValue() > num3.intValue()) {
            num = num3;
        }
        return num.intValue();
    }

    public final synchronized Bundle getStatus() {
        if (!isLoaded()) {
            Bundle unloadedStatus = getUnloadedStatus();
            unloadedStatus.putString(STATUS_ANDROID_IMPLEMENTATION_KEY_PATH, getImplementationName());
            return unloadedStatus;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(STATUS_IS_LOADED_KEY_PATH, true);
        bundle.putString(STATUS_ANDROID_IMPLEMENTATION_KEY_PATH, getImplementationName());
        bundle.putString("uri", this.mUri.getPath());
        bundle.putInt(STATUS_PROGRESS_UPDATE_INTERVAL_MILLIS_KEY_PATH, this.mProgressUpdateIntervalMillis);
        bundle.putBoolean(STATUS_SHOULD_PLAY_KEY_PATH, this.mShouldPlay);
        bundle.putDouble(STATUS_RATE_KEY_PATH, this.mRate);
        bundle.putBoolean(STATUS_SHOULD_CORRECT_PITCH_KEY_PATH, this.mShouldCorrectPitch);
        bundle.putDouble(STATUS_VOLUME_KEY_PATH, this.mVolume);
        bundle.putDouble(STATUS_VOLUME_PAN_KEY_PATH, this.mPan);
        bundle.putBoolean(STATUS_IS_MUTED_KEY_PATH, this.mIsMuted);
        bundle.putBoolean(STATUS_DID_JUST_FINISH_KEY_PATH, false);
        getExtraStatusFields(bundle);
        return bundle;
    }

    public final void setVideoSizeUpdateListener(VideoSizeUpdateListener videoSizeUpdateListener) {
        this.mVideoSizeUpdateListener = videoSizeUpdateListener;
    }

    public final void setFullscreenPresenter(FullscreenPresenter fullscreenPresenter) {
        this.mFullscreenPresenter = fullscreenPresenter;
    }

    public boolean isPresentedFullscreen() {
        return this.mFullscreenPresenter.isBeingPresentedFullscreen();
    }

    public void toggleFullscreen() {
        this.mFullscreenPresenter.setFullscreenMode(!isPresentedFullscreen());
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public final void handleAudioFocusInterruptionBegan() {
        if (this.mIsMuted) {
            return;
        }
        pauseImmediately();
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public final void handleAudioFocusGained() {
        try {
            playPlayerWithRateAndMuteIfNecessary();
        } catch (AudioFocusNotAcquiredException unused) {
        }
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public final void onPause() {
        pauseImmediately();
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public final void onResume() {
        try {
            playPlayerWithRateAndMuteIfNecessary();
        } catch (AudioFocusNotAcquiredException unused) {
        }
    }
}
