package expo.modules.p019av.player;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import expo.modules.p019av.AVManagerInterface;
import expo.modules.p019av.AudioFocusNotAcquiredException;
import expo.modules.p019av.player.PlayerData;
import expo.modules.p019av.player.datasource.DataSourceFactoryProvider;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: expo.modules.av.player.SimpleExoPlayerData */
/* loaded from: classes4.dex */
public class SimpleExoPlayerData extends PlayerData implements Player.Listener, MediaSourceEventListener {
    private static final String IMPLEMENTATION_NAME = "SimpleExoPlayer";
    private static final String TAG = "SimpleExoPlayerData";
    private boolean mFirstFrameRendered;
    private boolean mIsLoading;
    private boolean mIsLooping;
    private Integer mLastPlaybackState;
    private PlayerData.LoadCompletionListener mLoadCompletionListener;
    private final String mOverridingExtension;
    private final Context mReactContext;
    private SimpleExoPlayer mSimpleExoPlayer;
    private Pair<Integer, Integer> mVideoWidthHeight;

    @Override // expo.modules.p019av.player.PlayerData
    protected double getCurrentPositionSeconds() {
        return -1.0d;
    }

    @Override // expo.modules.p019av.player.PlayerData
    String getImplementationName() {
        return IMPLEMENTATION_NAME;
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
        Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onAudioSessionIdChanged(int r1) {
        Player.Listener.CC.$default$onAudioSessionIdChanged(this, r1);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
        Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onCues(CueGroup cueGroup) {
        Player.Listener.CC.$default$onCues(this, cueGroup);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onCues(List list) {
        Player.Listener.CC.$default$onCues(this, list);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
        Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onDeviceVolumeChanged(int r1, boolean z) {
        Player.Listener.CC.$default$onDeviceVolumeChanged(this, r1, z);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onDownstreamFormatChanged(int r1, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onEvents(Player player, Player.Events events) {
        Player.Listener.CC.$default$onEvents(this, player, events);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onIsLoadingChanged(boolean z) {
        Player.Listener.CC.$default$onIsLoadingChanged(this, z);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onIsPlayingChanged(boolean z) {
        Player.Listener.CC.$default$onIsPlayingChanged(this, z);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCanceled(int r1, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadCompleted(int r1, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadStarted(int r1, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
        Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int r2) {
        Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, r2);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
        Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onMetadata(Metadata metadata) {
        Player.Listener.CC.$default$onMetadata(this, metadata);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int r2) {
        Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, r2);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onPlaybackStateChanged(int r1) {
        Player.Listener.CC.$default$onPlaybackStateChanged(this, r1);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onPlaybackSuppressionReasonChanged(int r1) {
        Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, r1);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
        Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
        Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int r3) {
        Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, r3);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onRepeatModeChanged(int r1) {
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onSeekBackIncrementChanged(long j) {
        Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
        Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onSeekProcessed() {
        Player.Listener.CC.$default$onSeekProcessed(this);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onShuffleModeEnabledChanged(boolean z) {
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
        Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onSurfaceSizeChanged(int r1, int r2) {
        Player.Listener.CC.$default$onSurfaceSizeChanged(this, r1, r2);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onTimelineChanged(Timeline timeline, int r2) {
        Player.Listener.CC.$default$onTimelineChanged(this, timeline, r2);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
        Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onTracksChanged(Tracks tracks) {
        Player.Listener.CC.$default$onTracksChanged(this, tracks);
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onUpstreamDiscarded(int r1, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData) {
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public /* synthetic */ void onVolumeChanged(float f) {
        Player.Listener.CC.$default$onVolumeChanged(this, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleExoPlayerData(AVManagerInterface aVManagerInterface, Context context, Uri uri, String str, Map<String, Object> map) {
        super(aVManagerInterface, uri, map);
        this.mSimpleExoPlayer = null;
        this.mLoadCompletionListener = null;
        this.mFirstFrameRendered = false;
        this.mVideoWidthHeight = null;
        this.mLastPlaybackState = null;
        this.mIsLooping = false;
        this.mIsLoading = true;
        this.mReactContext = context;
        this.mOverridingExtension = str;
    }

    @Override // expo.modules.p019av.player.PlayerData
    public void load(Bundle bundle, PlayerData.LoadCompletionListener loadCompletionListener) {
        this.mLoadCompletionListener = loadCompletionListener;
        Context context = this.mAVModule.getContext();
        DefaultBandwidthMeter build = new DefaultBandwidthMeter.Builder(context).build();
        SimpleExoPlayer build2 = new SimpleExoPlayer.Builder(context).setTrackSelector(new DefaultTrackSelector(context, new AdaptiveTrackSelection.Factory())).setBandwidthMeter(build).build();
        this.mSimpleExoPlayer = build2;
        build2.addListener(this);
        try {
            this.mSimpleExoPlayer.prepare(buildMediaSource(this.mUri, this.mOverridingExtension, ((DataSourceFactoryProvider) this.mAVModule.getModuleRegistry().getModule(DataSourceFactoryProvider.class)).createFactory(this.mReactContext, this.mAVModule.getModuleRegistry(), Util.getUserAgent(context, "yourApplicationName"), this.mRequestHeaders, build.getTransferListener())));
            setStatus(bundle, null);
        } catch (IllegalStateException e) {
            onFatalError(e);
        }
    }

    @Override // expo.modules.p019av.player.PlayerData
    public synchronized void release() {
        super.release();
        stopUpdatingProgressIfNecessary();
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            this.mSimpleExoPlayer = null;
        }
    }

    @Override // expo.modules.p019av.player.PlayerData
    boolean shouldContinueUpdatingProgress() {
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        return simpleExoPlayer != null && simpleExoPlayer.getPlayWhenReady();
    }

    @Override // expo.modules.p019av.player.PlayerData
    void playPlayerWithRateAndMuteIfNecessary() throws AudioFocusNotAcquiredException {
        if (this.mSimpleExoPlayer == null || !shouldPlayerPlay()) {
            return;
        }
        if (!this.mIsMuted) {
            this.mAVModule.acquireAudioFocus();
        }
        updateVolumeMuteAndDuck();
        this.mSimpleExoPlayer.setPlaybackParameters(new PlaybackParameters(this.mRate, this.mShouldCorrectPitch ? 1.0f : this.mRate));
        this.mSimpleExoPlayer.setPlayWhenReady(this.mShouldPlay);
    }

    @Override // expo.modules.p019av.player.PlayerData
    void applyNewStatus(Integer num, Boolean bool) throws AudioFocusNotAcquiredException, IllegalStateException {
        if (this.mSimpleExoPlayer == null) {
            throw new IllegalStateException("mSimpleExoPlayer is null!");
        }
        if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            this.mIsLooping = booleanValue;
            if (booleanValue) {
                this.mSimpleExoPlayer.setRepeatMode(2);
            } else {
                this.mSimpleExoPlayer.setRepeatMode(0);
            }
        }
        if (!shouldPlayerPlay()) {
            this.mSimpleExoPlayer.setPlayWhenReady(false);
            stopUpdatingProgressIfNecessary();
        }
        updateVolumeMuteAndDuck();
        if (num != null) {
            this.mSimpleExoPlayer.seekTo(num.intValue());
        }
        playPlayerWithRateAndMuteIfNecessary();
    }

    @Override // expo.modules.p019av.player.PlayerData
    boolean isLoaded() {
        return this.mSimpleExoPlayer != null;
    }

    @Override // expo.modules.p019av.player.PlayerData
    void getExtraStatusFields(Bundle bundle) {
        int duration = (int) this.mSimpleExoPlayer.getDuration();
        bundle.putInt("durationMillis", duration);
        boolean z = false;
        bundle.putInt("positionMillis", getClippedIntegerForValue(Integer.valueOf((int) this.mSimpleExoPlayer.getCurrentPosition()), 0, Integer.valueOf(duration)));
        bundle.putInt("playableDurationMillis", getClippedIntegerForValue(Integer.valueOf((int) this.mSimpleExoPlayer.getBufferedPosition()), 0, Integer.valueOf(duration)));
        bundle.putBoolean(PlayerData.STATUS_IS_PLAYING_KEY_PATH, this.mSimpleExoPlayer.getPlayWhenReady() && this.mSimpleExoPlayer.getPlaybackState() == 3);
        bundle.putBoolean("isBuffering", (this.mIsLoading || this.mSimpleExoPlayer.getPlaybackState() == 2) ? true : true);
        bundle.putBoolean("isLooping", this.mIsLooping);
    }

    @Override // expo.modules.p019av.player.PlayerData
    public Pair<Integer, Integer> getVideoWidthHeight() {
        Pair<Integer, Integer> pair = this.mVideoWidthHeight;
        return pair != null ? pair : new Pair<>(0, 0);
    }

    @Override // expo.modules.p019av.player.PlayerData
    public void tryUpdateVideoSurface(Surface surface) {
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVideoSurface(surface);
        }
    }

    @Override // expo.modules.p019av.player.PlayerData
    public int getAudioSessionId() {
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getAudioSessionId();
        }
        return 0;
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void pauseImmediately() {
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);
        }
        stopUpdatingProgressIfNecessary();
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public boolean requiresAudioFocus() {
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        return simpleExoPlayer != null && (simpleExoPlayer.getPlayWhenReady() || shouldPlayerPlay()) && !this.mIsMuted;
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void updateVolumeMuteAndDuck() {
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setVolume(this.mAVModule.getVolumeForDuckAndFocus(this.mIsMuted, this.mVolume));
        }
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onLoadingChanged(boolean z) {
        this.mIsLoading = z;
        callStatusUpdateListener();
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onPlayerStateChanged(boolean z, int r5) {
        PlayerData.LoadCompletionListener loadCompletionListener;
        if (r5 == 3 && (loadCompletionListener = this.mLoadCompletionListener) != null) {
            this.mLoadCompletionListener = null;
            loadCompletionListener.onLoadSuccess(getStatus());
        }
        Integer num = this.mLastPlaybackState;
        if (num != null && r5 != num.intValue() && r5 == 4) {
            callStatusUpdateListenerWithDidJustFinish();
            stopUpdatingProgressIfNecessary();
        } else {
            callStatusUpdateListener();
            if (z && r5 == 3) {
                beginUpdatingProgressIfNecessary();
            }
        }
        this.mLastPlaybackState = Integer.valueOf(r5);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onPlayerError(PlaybackException playbackException) {
        onFatalError(playbackException.getCause());
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onPositionDiscontinuity(int r1) {
        if (r1 == 0) {
            callStatusUpdateListenerWithDidJustFinish();
        }
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onVideoSizeChanged(VideoSize videoSize) {
        this.mVideoWidthHeight = new Pair<>(Integer.valueOf(videoSize.width), Integer.valueOf(videoSize.height));
        if (!this.mFirstFrameRendered || this.mVideoSizeUpdateListener == null) {
            return;
        }
        this.mVideoSizeUpdateListener.onVideoSizeUpdate(this.mVideoWidthHeight);
    }

    @Override // com.google.android.exoplayer2.Player.Listener
    public void onRenderedFirstFrame() {
        if (!this.mFirstFrameRendered && this.mVideoWidthHeight != null && this.mVideoSizeUpdateListener != null) {
            this.mVideoSizeUpdateListener.onVideoSizeUpdate(this.mVideoWidthHeight);
        }
        this.mFirstFrameRendered = true;
    }

    @Override // com.google.android.exoplayer2.source.MediaSourceEventListener
    public void onLoadError(int r1, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        PlayerData.LoadCompletionListener loadCompletionListener = this.mLoadCompletionListener;
        if (loadCompletionListener != null) {
            this.mLoadCompletionListener = null;
            loadCompletionListener.onLoadError(iOException.toString());
        }
    }

    private void onFatalError(Throwable th) {
        PlayerData.LoadCompletionListener loadCompletionListener = this.mLoadCompletionListener;
        if (loadCompletionListener != null) {
            this.mLoadCompletionListener = null;
            loadCompletionListener.onLoadError(th.toString());
        } else if (this.mErrorListener != null) {
            PlayerData.ErrorListener errorListener = this.mErrorListener;
            errorListener.onError("Player error: " + th.getMessage());
        }
        release();
    }

    private MediaSource buildMediaSource(Uri uri, String str, DataSource.Factory factory) {
        String str2;
        try {
            if (uri.getScheme() == null) {
                DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(this.mReactContext.getResources().getIdentifier(uri.toString(), "raw", this.mReactContext.getPackageName())));
                RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(this.mReactContext);
                rawResourceDataSource.open(dataSpec);
                uri = rawResourceDataSource.getUri();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error reading raw resource from ExoPlayer", e);
        }
        if (TextUtils.isEmpty(str)) {
            str2 = String.valueOf(uri);
        } else {
            str2 = "." + str;
        }
        int inferContentType = Util.inferContentType(str2);
        if (inferContentType != 0) {
            if (inferContentType != 1) {
                if (inferContentType != 2) {
                    if (inferContentType == 4) {
                        return new ProgressiveMediaSource.Factory(factory).createMediaSource(MediaItem.fromUri(uri));
                    }
                    throw new IllegalStateException("Content of this type is unsupported at the moment. Unsupported type: " + inferContentType);
                }
                return new HlsMediaSource.Factory(factory).createMediaSource(MediaItem.fromUri(uri));
            }
            return new SsMediaSource.Factory(new DefaultSsChunkSource.Factory(factory), factory).createMediaSource(MediaItem.fromUri(uri));
        }
        return new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(factory), factory).createMediaSource(MediaItem.fromUri(uri));
    }
}
