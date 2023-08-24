package expo.modules.p019av;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import com.facebook.jni.HybridData;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.JavaScriptContextProvider;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponseListener;
import expo.modules.p019av.ViewUtils;
import expo.modules.p019av.player.PlayerData;
import expo.modules.p019av.video.VideoView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* renamed from: expo.modules.av.AVManager */
/* loaded from: classes4.dex */
public class AVManager implements LifecycleEventListener, AudioManager.OnAudioFocusChangeListener, MediaRecorder.OnInfoListener, AVManagerInterface, InternalModule {
    private static final String AUDIO_MODE_INTERRUPTION_MODE_KEY = "interruptionModeAndroid";
    private static final String AUDIO_MODE_PLAY_THROUGH_EARPIECE = "playThroughEarpieceAndroid";
    private static final String AUDIO_MODE_SHOULD_DUCK_KEY = "shouldDuckAndroid";
    private static final String AUDIO_MODE_STAYS_ACTIVE_IN_BACKGROUND = "staysActiveInBackground";
    private static final String RECORDING_INPUT_NAME_KEY = "name";
    private static final String RECORDING_INPUT_TYPE_KEY = "type";
    private static final String RECORDING_INPUT_UID_KEY = "uid";
    private static final String RECORDING_OPTIONS_KEY = "android";
    private static final String RECORDING_OPTION_AUDIO_ENCODER_KEY = "audioEncoder";
    private static final String RECORDING_OPTION_BIT_RATE_KEY = "bitRate";
    private static final String RECORDING_OPTION_EXTENSION_KEY = "extension";
    private static final String RECORDING_OPTION_IS_METERING_ENABLED_KEY = "isMeteringEnabled";
    private static final String RECORDING_OPTION_MAX_FILE_SIZE_KEY = "maxFileSize";
    private static final String RECORDING_OPTION_NUMBER_OF_CHANNELS_KEY = "numberOfChannels";
    private static final String RECORDING_OPTION_OUTPUT_FORMAT_KEY = "outputFormat";
    private static final String RECORDING_OPTION_SAMPLE_RATE_KEY = "sampleRate";
    private final AudioManager mAudioManager;
    private final Context mContext;
    private final HybridData mHybridData;
    private boolean mIsRegistered;
    private ModuleRegistry mModuleRegistry;
    private final BroadcastReceiver mNoisyAudioStreamReceiver;
    private boolean mShouldRouteThroughEarpiece = false;
    private boolean mEnabled = true;
    private boolean mAcquiredAudioFocus = false;
    private boolean mAppIsPaused = false;
    private AudioInterruptionMode mAudioInterruptionMode = AudioInterruptionMode.DUCK_OTHERS;
    private boolean mShouldDuckAudio = true;
    private boolean mIsDuckingAudio = false;
    private boolean mStaysActiveInBackground = false;
    private int mSoundMapKeyCount = 0;
    private final Map<Integer, PlayerData> mSoundMap = new HashMap();
    private final Set<VideoView> mVideoViewSet = new HashSet();
    private MediaRecorder mAudioRecorder = null;
    private String mAudioRecordingFilePath = null;
    private long mAudioRecorderUptimeOfLastStartResume = 0;
    private long mAudioRecorderDurationAlreadyRecorded = 0;
    private boolean mAudioRecorderIsRecording = false;
    private boolean mAudioRecorderIsPaused = false;
    private boolean mAudioRecorderIsMeteringEnabled = false;

    /* renamed from: expo.modules.av.AVManager$AudioInterruptionMode */
    /* loaded from: classes4.dex */
    private enum AudioInterruptionMode {
        DO_NOT_MIX,
        DUCK_OTHERS
    }

    private native HybridData initHybrid();

    private native void installJSIBindings(long j, CallInvokerHolderImpl callInvokerHolderImpl);

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    static {
        System.loadLibrary("expo-av");
    }

    public AVManager(Context context) {
        this.mIsRegistered = false;
        this.mContext = context;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: expo.modules.av.AVManager.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                    AVManager.this.abandonAudioFocus();
                }
            }
        };
        this.mNoisyAudioStreamReceiver = broadcastReceiver;
        context.registerReceiver(broadcastReceiver, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
        this.mIsRegistered = true;
        this.mHybridData = initHybrid();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        this.mHybridData.resetNative();
    }

    private PlayerData getMediaPlayerById(int r2) {
        return this.mSoundMap.get(Integer.valueOf(r2));
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public ModuleRegistry getModuleRegistry() {
        return this.mModuleRegistry;
    }

    private UIManager getUIManager() {
        return (UIManager) this.mModuleRegistry.getModule(UIManager.class);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        if (this.mModuleRegistry != null) {
            getUIManager().unregisterLifecycleEventListener(this);
        }
        this.mModuleRegistry = moduleRegistry;
        if (moduleRegistry != null) {
            UIManager uIManager = getUIManager();
            uIManager.registerLifecycleEventListener(this);
            uIManager.runOnClientCodeQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AVManager.this.m1581lambda$onCreate$0$expomodulesavAVManager();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onCreate$0$expo-modules-av-AVManager  reason: not valid java name */
    public /* synthetic */ void m1581lambda$onCreate$0$expomodulesavAVManager() {
        JavaScriptContextProvider javaScriptContextProvider = (JavaScriptContextProvider) this.mModuleRegistry.getModule(JavaScriptContextProvider.class);
        long javaScriptContextRef = javaScriptContextProvider.getJavaScriptContextRef();
        if (javaScriptContextRef != 0) {
            installJSIBindings(javaScriptContextRef, javaScriptContextProvider.getJSCallInvokerHolder());
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public Context getContext() {
        return this.mContext;
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class> getExportedInterfaces() {
        return Collections.singletonList(AVManagerInterface.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, Bundle bundle) {
        EventEmitter eventEmitter;
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        if (moduleRegistry == null || (eventEmitter = (EventEmitter) moduleRegistry.getModule(EventEmitter.class)) == null) {
            return;
        }
        eventEmitter.emit(str, bundle);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        if (this.mAppIsPaused) {
            this.mAppIsPaused = false;
            if (this.mStaysActiveInBackground) {
                return;
            }
            for (AudioEventHandler audioEventHandler : getAllRegisteredAudioEventHandlers()) {
                audioEventHandler.onResume();
            }
            if (this.mShouldRouteThroughEarpiece) {
                updatePlaySoundThroughEarpiece(true);
            }
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
        if (this.mAppIsPaused) {
            return;
        }
        this.mAppIsPaused = true;
        if (this.mStaysActiveInBackground) {
            return;
        }
        for (AudioEventHandler audioEventHandler : getAllRegisteredAudioEventHandlers()) {
            audioEventHandler.onPause();
        }
        abandonAudioFocus();
        if (this.mShouldRouteThroughEarpiece) {
            updatePlaySoundThroughEarpiece(false);
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
        if (this.mIsRegistered) {
            this.mContext.unregisterReceiver(this.mNoisyAudioStreamReceiver);
            this.mIsRegistered = false;
        }
        Iterator<PlayerData> it = this.mSoundMap.values().iterator();
        while (it.hasNext()) {
            PlayerData next = it.next();
            it.remove();
            if (next != null) {
                next.release();
            }
        }
        for (VideoView videoView : this.mVideoViewSet) {
            videoView.unloadPlayerAndMediaController();
        }
        removeAudioRecorder();
        abandonAudioFocus();
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void registerVideoViewForAudioLifecycle(VideoView videoView) {
        this.mVideoViewSet.add(videoView);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void unregisterVideoViewForAudioLifecycle(VideoView videoView) {
        this.mVideoViewSet.remove(videoView);
    }

    private Set<AudioEventHandler> getAllRegisteredAudioEventHandlers() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.mVideoViewSet);
        hashSet.addAll(this.mSoundMap.values());
        return hashSet;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int r4) {
        if (r4 != -3) {
            if (r4 != -2 && r4 != -1) {
                if (r4 != 1) {
                    return;
                }
                this.mIsDuckingAudio = false;
                this.mAcquiredAudioFocus = true;
                for (AudioEventHandler audioEventHandler : getAllRegisteredAudioEventHandlers()) {
                    audioEventHandler.handleAudioFocusGained();
                }
                return;
            }
        } else if (this.mShouldDuckAudio) {
            this.mIsDuckingAudio = true;
            this.mAcquiredAudioFocus = true;
            updateDuckStatusForAllPlayersPlaying();
            return;
        }
        this.mIsDuckingAudio = false;
        this.mAcquiredAudioFocus = false;
        for (AudioEventHandler audioEventHandler2 : getAllRegisteredAudioEventHandlers()) {
            audioEventHandler2.handleAudioFocusInterruptionBegan();
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void acquireAudioFocus() throws AudioFocusNotAcquiredException {
        if (!this.mEnabled) {
            throw new AudioFocusNotAcquiredException("Expo Audio is disabled, so audio focus could not be acquired.");
        }
        if (this.mAppIsPaused && !this.mStaysActiveInBackground) {
            throw new AudioFocusNotAcquiredException("This experience is currently in the background, so audio focus could not be acquired.");
        }
        if (this.mAcquiredAudioFocus) {
            return;
        }
        boolean z = this.mAudioManager.requestAudioFocus(this, 3, this.mAudioInterruptionMode == AudioInterruptionMode.DO_NOT_MIX ? 1 : 3) == 1;
        this.mAcquiredAudioFocus = z;
        if (!z) {
            throw new AudioFocusNotAcquiredException("Audio focus could not be acquired from the OS at this time.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void abandonAudioFocus() {
        for (AudioEventHandler audioEventHandler : getAllRegisteredAudioEventHandlers()) {
            if (audioEventHandler.requiresAudioFocus()) {
                audioEventHandler.pauseImmediately();
            }
        }
        this.mAcquiredAudioFocus = false;
        this.mAudioManager.abandonAudioFocus(this);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void abandonAudioFocusIfUnused() {
        for (AudioEventHandler audioEventHandler : getAllRegisteredAudioEventHandlers()) {
            if (audioEventHandler.requiresAudioFocus()) {
                return;
            }
        }
        abandonAudioFocus();
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public float getVolumeForDuckAndFocus(boolean z, float f) {
        if (!this.mAcquiredAudioFocus || z) {
            return 0.0f;
        }
        return this.mIsDuckingAudio ? f / 2.0f : f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDuckStatusForAllPlayersPlaying() {
        for (AudioEventHandler audioEventHandler : getAllRegisteredAudioEventHandlers()) {
            audioEventHandler.updateVolumeMuteAndDuck();
        }
    }

    private void updatePlaySoundThroughEarpiece(boolean z) {
        this.mAudioManager.setMode(z ? 3 : 0);
        this.mAudioManager.setSpeakerphoneOn(!z);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void setAudioIsEnabled(Boolean bool) {
        this.mEnabled = bool.booleanValue();
        if (bool.booleanValue()) {
            return;
        }
        getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AVManager.this.abandonAudioFocus();
            }
        });
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void setAudioMode(ReadableArguments readableArguments) {
        boolean z = readableArguments.getBoolean(AUDIO_MODE_SHOULD_DUCK_KEY);
        this.mShouldDuckAudio = z;
        if (!z) {
            this.mIsDuckingAudio = false;
            getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    AVManager.this.updateDuckStatusForAllPlayersPlaying();
                }
            });
        }
        if (readableArguments.containsKey(AUDIO_MODE_PLAY_THROUGH_EARPIECE)) {
            boolean z2 = readableArguments.getBoolean(AUDIO_MODE_PLAY_THROUGH_EARPIECE);
            this.mShouldRouteThroughEarpiece = z2;
            updatePlaySoundThroughEarpiece(z2);
        }
        if (readableArguments.getInt(AUDIO_MODE_INTERRUPTION_MODE_KEY) == 1) {
            this.mAudioInterruptionMode = AudioInterruptionMode.DO_NOT_MIX;
        } else {
            this.mAudioInterruptionMode = AudioInterruptionMode.DUCK_OTHERS;
        }
        this.mStaysActiveInBackground = readableArguments.getBoolean(AUDIO_MODE_STAYS_ACTIVE_IN_BACKGROUND);
    }

    private PlayerData tryGetSoundForKey(Integer num, Promise promise) {
        PlayerData playerData = this.mSoundMap.get(num);
        if (playerData == null && promise != null) {
            promise.reject("E_AUDIO_NOPLAYER", "Player does not exist.");
        }
        return playerData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeSoundForKey(Integer num) {
        PlayerData remove = this.mSoundMap.remove(num);
        if (remove != null) {
            remove.release();
            abandonAudioFocusIfUnused();
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void loadForSound(final ReadableArguments readableArguments, final ReadableArguments readableArguments2, final Promise promise) {
        getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AVManager.this.m1580lambda$loadForSound$1$expomodulesavAVManager(readableArguments, readableArguments2, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$loadForSound$1$expo-modules-av-AVManager  reason: not valid java name */
    public /* synthetic */ void m1580lambda$loadForSound$1$expomodulesavAVManager(ReadableArguments readableArguments, ReadableArguments readableArguments2, final Promise promise) {
        final int r0 = this.mSoundMapKeyCount;
        this.mSoundMapKeyCount = r0 + 1;
        PlayerData createUnloadedPlayerData = PlayerData.createUnloadedPlayerData(this, this.mContext, readableArguments, readableArguments2.toBundle());
        createUnloadedPlayerData.setErrorListener(new PlayerData.ErrorListener() { // from class: expo.modules.av.AVManager.2
            @Override // expo.modules.p019av.player.PlayerData.ErrorListener
            public void onError(String str) {
                AVManager.this.removeSoundForKey(Integer.valueOf(r0));
            }
        });
        this.mSoundMap.put(Integer.valueOf(r0), createUnloadedPlayerData);
        createUnloadedPlayerData.load(readableArguments2.toBundle(), new PlayerData.LoadCompletionListener() { // from class: expo.modules.av.AVManager.3
            @Override // expo.modules.p019av.player.PlayerData.LoadCompletionListener
            public void onLoadSuccess(Bundle bundle) {
                promise.resolve(Arrays.asList(Integer.valueOf(r0), bundle));
            }

            @Override // expo.modules.p019av.player.PlayerData.LoadCompletionListener
            public void onLoadError(String str) {
                AVManager.this.mSoundMap.remove(Integer.valueOf(r0));
                promise.reject("E_LOAD_ERROR", str, null);
            }
        });
        createUnloadedPlayerData.setStatusUpdateListener(new PlayerData.StatusUpdateListener() { // from class: expo.modules.av.AVManager.4
            @Override // expo.modules.p019av.player.PlayerData.StatusUpdateListener
            public void onStatusUpdate(Bundle bundle) {
                Bundle bundle2 = new Bundle();
                bundle2.putInt("key", r0);
                bundle2.putBundle("status", bundle);
                AVManager.this.sendEvent("didUpdatePlaybackStatus", bundle2);
            }
        });
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void unloadForSound(final Integer num, final Promise promise) {
        getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AVManager.this.m1584lambda$unloadForSound$2$expomodulesavAVManager(num, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$unloadForSound$2$expo-modules-av-AVManager  reason: not valid java name */
    public /* synthetic */ void m1584lambda$unloadForSound$2$expomodulesavAVManager(Integer num, Promise promise) {
        if (tryGetSoundForKey(num, promise) != null) {
            removeSoundForKey(num);
            promise.resolve(PlayerData.getUnloadedStatus());
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void setStatusForSound(final Integer num, final ReadableArguments readableArguments, final Promise promise) {
        getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                AVManager.this.m1583lambda$setStatusForSound$3$expomodulesavAVManager(num, promise, readableArguments);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setStatusForSound$3$expo-modules-av-AVManager  reason: not valid java name */
    public /* synthetic */ void m1583lambda$setStatusForSound$3$expomodulesavAVManager(Integer num, Promise promise, ReadableArguments readableArguments) {
        PlayerData tryGetSoundForKey = tryGetSoundForKey(num, promise);
        if (tryGetSoundForKey != null) {
            tryGetSoundForKey.setStatus(readableArguments.toBundle(), promise);
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void replaySound(final Integer num, final ReadableArguments readableArguments, final Promise promise) {
        getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                AVManager.this.m1582lambda$replaySound$4$expomodulesavAVManager(num, promise, readableArguments);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$replaySound$4$expo-modules-av-AVManager  reason: not valid java name */
    public /* synthetic */ void m1582lambda$replaySound$4$expomodulesavAVManager(Integer num, Promise promise, ReadableArguments readableArguments) {
        PlayerData tryGetSoundForKey = tryGetSoundForKey(num, promise);
        if (tryGetSoundForKey != null) {
            tryGetSoundForKey.setStatus(readableArguments.toBundle(), promise);
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void getStatusForSound(final Integer num, final Promise promise) {
        getUIManager().runOnUiQueueThread(new Runnable() { // from class: expo.modules.av.AVManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AVManager.this.m1579lambda$getStatusForSound$5$expomodulesavAVManager(num, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getStatusForSound$5$expo-modules-av-AVManager  reason: not valid java name */
    public /* synthetic */ void m1579lambda$getStatusForSound$5$expomodulesavAVManager(Integer num, Promise promise) {
        PlayerData tryGetSoundForKey = tryGetSoundForKey(num, promise);
        if (tryGetSoundForKey != null) {
            promise.resolve(tryGetSoundForKey.getStatus());
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void loadForVideo(Integer num, final ReadableArguments readableArguments, final ReadableArguments readableArguments2, final Promise promise) {
        ViewUtils.tryRunWithVideoView(this.mModuleRegistry, num.intValue(), new ViewUtils.VideoViewCallback() { // from class: expo.modules.av.AVManager.5
            @Override // expo.modules.p019av.ViewUtils.VideoViewCallback
            public void runWithVideoView(VideoView videoView) {
                videoView.setSource(readableArguments, readableArguments2, promise);
            }
        }, promise);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void unloadForVideo(Integer num, final Promise promise) {
        ViewUtils.tryRunWithVideoView(this.mModuleRegistry, num.intValue(), new ViewUtils.VideoViewCallback() { // from class: expo.modules.av.AVManager.6
            @Override // expo.modules.p019av.ViewUtils.VideoViewCallback
            public void runWithVideoView(VideoView videoView) {
                videoView.setSource(null, null, promise);
            }
        }, promise);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void setStatusForVideo(Integer num, final ReadableArguments readableArguments, final Promise promise) {
        ViewUtils.tryRunWithVideoView(this.mModuleRegistry, num.intValue(), new ViewUtils.VideoViewCallback() { // from class: expo.modules.av.AVManager.7
            @Override // expo.modules.p019av.ViewUtils.VideoViewCallback
            public void runWithVideoView(VideoView videoView) {
                videoView.setStatus(readableArguments, promise);
            }
        }, promise);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void replayVideo(Integer num, final ReadableArguments readableArguments, final Promise promise) {
        ViewUtils.tryRunWithVideoView(this.mModuleRegistry, num.intValue(), new ViewUtils.VideoViewCallback() { // from class: expo.modules.av.AVManager.8
            @Override // expo.modules.p019av.ViewUtils.VideoViewCallback
            public void runWithVideoView(VideoView videoView) {
                videoView.setStatus(readableArguments, promise);
            }
        }, promise);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void getStatusForVideo(Integer num, final Promise promise) {
        ViewUtils.tryRunWithVideoView(this.mModuleRegistry, num.intValue(), new ViewUtils.VideoViewCallback() { // from class: expo.modules.av.AVManager.9
            @Override // expo.modules.p019av.ViewUtils.VideoViewCallback
            public void runWithVideoView(VideoView videoView) {
                promise.resolve(videoView.getStatus());
            }
        }, promise);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public boolean hasAudioPermission() {
        return ((Permissions) this.mModuleRegistry.getModule(Permissions.class)).hasGrantedPermissions("android.permission.RECORD_AUDIO");
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void requestAudioPermission(PermissionsResponseListener permissionsResponseListener) {
        ((Permissions) this.mModuleRegistry.getModule(Permissions.class)).askForPermissions(permissionsResponseListener, "android.permission.RECORD_AUDIO");
    }

    private boolean isMissingAudioRecordingPermissions() {
        return !hasAudioPermission();
    }

    private boolean checkAudioRecorderExistsOrReject(Promise promise) {
        if (this.mAudioRecorder == null && promise != null) {
            promise.reject("E_AUDIO_NORECORDER", "Recorder does not exist.");
        }
        return this.mAudioRecorder != null;
    }

    private long getAudioRecorderDurationMillis() {
        if (this.mAudioRecorder == null) {
            return 0L;
        }
        long j = this.mAudioRecorderDurationAlreadyRecorded;
        return (!this.mAudioRecorderIsRecording || this.mAudioRecorderUptimeOfLastStartResume <= 0) ? j : j + (SystemClock.uptimeMillis() - this.mAudioRecorderUptimeOfLastStartResume);
    }

    private int getAudioRecorderLevels() {
        int maxAmplitude;
        MediaRecorder mediaRecorder = this.mAudioRecorder;
        if (mediaRecorder == null || !this.mAudioRecorderIsMeteringEnabled || (maxAmplitude = mediaRecorder.getMaxAmplitude()) == 0) {
            return -160;
        }
        return (int) (Math.log(maxAmplitude / 32767.0d) * 20.0d);
    }

    private Bundle getAudioRecorderStatus() {
        Bundle bundle = new Bundle();
        if (this.mAudioRecorder != null) {
            bundle.putBoolean("canRecord", true);
            bundle.putBoolean("isRecording", this.mAudioRecorderIsRecording);
            bundle.putInt("durationMillis", (int) getAudioRecorderDurationMillis());
            if (this.mAudioRecorderIsMeteringEnabled) {
                bundle.putInt("metering", getAudioRecorderLevels());
            }
        }
        return bundle;
    }

    private void removeAudioRecorder() {
        MediaRecorder mediaRecorder = this.mAudioRecorder;
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
            } catch (RuntimeException unused) {
            }
            this.mAudioRecorder.release();
            this.mAudioRecorder = null;
        }
        this.mAudioRecordingFilePath = null;
        this.mAudioRecorderIsRecording = false;
        this.mAudioRecorderIsPaused = false;
        this.mAudioRecorderDurationAlreadyRecorded = 0L;
        this.mAudioRecorderUptimeOfLastStartResume = 0L;
    }

    @Override // android.media.MediaRecorder.OnInfoListener
    public void onInfo(MediaRecorder mediaRecorder, int r2, int r3) {
        EventEmitter eventEmitter;
        if (r2 != 801) {
            return;
        }
        removeAudioRecorder();
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        if (moduleRegistry == null || (eventEmitter = (EventEmitter) moduleRegistry.getModule(EventEmitter.class)) == null) {
            return;
        }
        eventEmitter.emit("Expo.Recording.recorderUnloaded", new Bundle());
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void prepareAudioRecorder(ReadableArguments readableArguments, Promise promise) {
        if (isMissingAudioRecordingPermissions()) {
            promise.reject("E_MISSING_PERMISSION", "Missing audio recording permissions.");
            return;
        }
        this.mAudioRecorderIsMeteringEnabled = readableArguments.getBoolean(RECORDING_OPTION_IS_METERING_ENABLED_KEY);
        removeAudioRecorder();
        ReadableArguments arguments = readableArguments.getArguments("android");
        String str = "recording-" + UUID.randomUUID().toString() + arguments.getString(RECORDING_OPTION_EXTENSION_KEY);
        try {
            File file = new File(this.mContext.getCacheDir() + File.separator + "Audio");
            ensureDirExists(file);
            this.mAudioRecordingFilePath = file + File.separator + str;
        } catch (IOException unused) {
        }
        MediaRecorder mediaRecorder = new MediaRecorder();
        this.mAudioRecorder = mediaRecorder;
        mediaRecorder.setAudioSource(0);
        this.mAudioRecorder.setOutputFormat(arguments.getInt(RECORDING_OPTION_OUTPUT_FORMAT_KEY));
        this.mAudioRecorder.setAudioEncoder(arguments.getInt(RECORDING_OPTION_AUDIO_ENCODER_KEY));
        if (arguments.containsKey(RECORDING_OPTION_SAMPLE_RATE_KEY)) {
            this.mAudioRecorder.setAudioSamplingRate(arguments.getInt(RECORDING_OPTION_SAMPLE_RATE_KEY));
        }
        if (arguments.containsKey(RECORDING_OPTION_NUMBER_OF_CHANNELS_KEY)) {
            this.mAudioRecorder.setAudioChannels(arguments.getInt(RECORDING_OPTION_NUMBER_OF_CHANNELS_KEY));
        }
        if (arguments.containsKey(RECORDING_OPTION_BIT_RATE_KEY)) {
            this.mAudioRecorder.setAudioEncodingBitRate(arguments.getInt(RECORDING_OPTION_BIT_RATE_KEY));
        }
        this.mAudioRecorder.setOutputFile(this.mAudioRecordingFilePath);
        if (arguments.containsKey(RECORDING_OPTION_MAX_FILE_SIZE_KEY)) {
            this.mAudioRecorder.setMaxFileSize(arguments.getInt(RECORDING_OPTION_MAX_FILE_SIZE_KEY));
            this.mAudioRecorder.setOnInfoListener(this);
        }
        try {
            this.mAudioRecorder.prepare();
            Bundle bundle = new Bundle();
            bundle.putString("uri", Uri.fromFile(new File(this.mAudioRecordingFilePath)).toString());
            bundle.putBundle("status", getAudioRecorderStatus());
            promise.resolve(bundle);
        } catch (Exception e) {
            promise.reject("E_AUDIO_RECORDERNOTCREATED", "Prepare encountered an error: recorder not prepared", e);
            removeAudioRecorder();
        }
    }

    private AudioDeviceInfo getDeviceInfoFromUid(String str) {
        AudioDeviceInfo[] devices;
        if (Build.VERSION.SDK_INT < 23) {
            return null;
        }
        int intValue = Integer.valueOf(str).intValue();
        for (AudioDeviceInfo audioDeviceInfo : this.mAudioManager.getDevices(1)) {
            if (audioDeviceInfo.getId() == intValue) {
                return audioDeviceInfo;
            }
        }
        return null;
    }

    private Bundle getMapFromDeviceInfo(AudioDeviceInfo audioDeviceInfo) {
        Bundle bundle = new Bundle();
        if (Build.VERSION.SDK_INT < 23) {
            return bundle;
        }
        int type = audioDeviceInfo.getType();
        String valueOf = String.valueOf(type);
        if (type == 15) {
            valueOf = "MicrophoneBuiltIn";
        } else if (type == 7) {
            valueOf = "BluetoothSCO";
        } else if (type == 8) {
            valueOf = "BluetoothA2DP";
        } else if (type == 18) {
            valueOf = "Telephony";
        } else if (type == 3) {
            valueOf = "MicrophoneWired";
        }
        bundle.putString("name", audioDeviceInfo.getProductName().toString());
        bundle.putString("type", valueOf);
        bundle.putString(RECORDING_INPUT_UID_KEY, String.valueOf(audioDeviceInfo.getId()));
        return bundle;
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void getCurrentInput(Promise promise) {
        if (Build.VERSION.SDK_INT < 28) {
            promise.reject("E_AUDIO_VERSIONINCOMPATIBLE", "Getting current audio input is not supported on devices running Android version lower than Android 9.0");
            return;
        }
        try {
            this.mAudioRecorder.getRoutedDevice();
        } catch (Exception unused) {
        }
        AudioDeviceInfo preferredDevice = this.mAudioRecorder.getPreferredDevice();
        if (preferredDevice == null) {
            AudioDeviceInfo[] devices = this.mAudioManager.getDevices(1);
            int r2 = 0;
            while (true) {
                if (r2 >= devices.length) {
                    break;
                }
                AudioDeviceInfo audioDeviceInfo = devices[r2];
                if (audioDeviceInfo.getType() == 15) {
                    this.mAudioRecorder.setPreferredDevice(audioDeviceInfo);
                    preferredDevice = audioDeviceInfo;
                    break;
                }
                r2++;
            }
        }
        if (preferredDevice != null) {
            promise.resolve(getMapFromDeviceInfo(preferredDevice));
        } else {
            promise.reject("E_AUDIO_DEVICENOTFOUND", "Cannot get current input, AudioDeviceInfo not found.");
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void getAvailableInputs(Promise promise) {
        AudioDeviceInfo[] devices;
        if (Build.VERSION.SDK_INT < 23) {
            promise.reject("E_AUDIO_VERSIONINCOMPATIBLE", "Getting available inputs is not supported on devices running Android version lower than Android 6.0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (AudioDeviceInfo audioDeviceInfo : this.mAudioManager.getDevices(1)) {
            int type = audioDeviceInfo.getType();
            if (type == 15 || type == 7 || type == 3) {
                arrayList.add(getMapFromDeviceInfo(audioDeviceInfo));
            }
        }
        promise.resolve(arrayList);
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void setInput(String str, Promise promise) {
        boolean z;
        AudioDeviceInfo deviceInfoFromUid = getDeviceInfoFromUid(str);
        if (Build.VERSION.SDK_INT >= 28) {
            if (deviceInfoFromUid != null && deviceInfoFromUid.getType() == 7) {
                this.mAudioManager.startBluetoothSco();
            } else {
                this.mAudioManager.stopBluetoothSco();
            }
            z = this.mAudioRecorder.setPreferredDevice(deviceInfoFromUid);
        } else {
            promise.reject("E_AUDIO_VERSIONINCOMPATIBLE", "Setting input is not supported on devices running Android version lower than Android 9.0");
            z = false;
        }
        if (z) {
            promise.resolve(Boolean.valueOf(z));
        } else {
            promise.reject("E_AUDIO_SETINPUTFAIL", "Could not set preferred device input.");
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void startAudioRecording(Promise promise) {
        if (isMissingAudioRecordingPermissions()) {
            promise.reject("E_MISSING_PERMISSION", "Missing audio recording permissions.");
        } else if (checkAudioRecorderExistsOrReject(promise)) {
            try {
                if (this.mAudioRecorderIsPaused && Build.VERSION.SDK_INT >= 24) {
                    this.mAudioRecorder.resume();
                } else {
                    this.mAudioRecorder.start();
                }
                this.mAudioRecorderUptimeOfLastStartResume = SystemClock.uptimeMillis();
                this.mAudioRecorderIsRecording = true;
                this.mAudioRecorderIsPaused = false;
                promise.resolve(getAudioRecorderStatus());
            } catch (IllegalStateException e) {
                promise.reject("E_AUDIO_RECORDING", "Start encountered an error: recording not started", e);
            }
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void pauseAudioRecording(Promise promise) {
        if (checkAudioRecorderExistsOrReject(promise)) {
            if (Build.VERSION.SDK_INT < 24) {
                promise.reject("E_AUDIO_VERSIONINCOMPATIBLE", "Pausing an audio recording is unsupported on Android devices running SDK < 24.");
                return;
            }
            try {
                this.mAudioRecorder.pause();
                this.mAudioRecorderDurationAlreadyRecorded = getAudioRecorderDurationMillis();
                this.mAudioRecorderIsRecording = false;
                this.mAudioRecorderIsPaused = true;
                promise.resolve(getAudioRecorderStatus());
            } catch (IllegalStateException e) {
                promise.reject("E_AUDIO_RECORDINGPAUSE", "Pause encountered an error: recording not paused", e);
            }
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void stopAudioRecording(Promise promise) {
        if (checkAudioRecorderExistsOrReject(promise)) {
            try {
                this.mAudioRecorder.stop();
                this.mAudioRecorderDurationAlreadyRecorded = getAudioRecorderDurationMillis();
                this.mAudioRecorderIsRecording = false;
                this.mAudioRecorderIsPaused = false;
                promise.resolve(getAudioRecorderStatus());
            } catch (RuntimeException e) {
                this.mAudioRecorderIsPaused = false;
                if (!this.mAudioRecorderIsRecording) {
                    promise.reject("E_AUDIO_RECORDINGSTOP", "Stop encountered an error: recording not started", e);
                    return;
                }
                this.mAudioRecorderIsRecording = false;
                promise.reject("E_AUDIO_NODATA", "Stop encountered an error: no valid audio data has been received", e);
            }
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void getAudioRecordingStatus(Promise promise) {
        if (checkAudioRecorderExistsOrReject(promise)) {
            promise.resolve(getAudioRecorderStatus());
        }
    }

    @Override // expo.modules.p019av.AVManagerInterface
    public void unloadAudioRecorder(Promise promise) {
        if (checkAudioRecorderExistsOrReject(promise)) {
            removeAudioRecorder();
            promise.resolve(null);
        }
    }

    private static File ensureDirExists(File file) throws IOException {
        if (file.isDirectory() || file.mkdirs()) {
            return file;
        }
        throw new IOException("Couldn't create directory '" + file + "'");
    }
}
