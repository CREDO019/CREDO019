package expo.modules.p019av.player;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import com.google.common.net.HttpHeaders;
import expo.modules.core.ModuleRegistry;
import expo.modules.p019av.AVManagerInterface;
import expo.modules.p019av.AudioFocusNotAcquiredException;
import expo.modules.p019av.player.PlayerData;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.p028io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: expo.modules.av.player.MediaPlayerData */
/* loaded from: classes4.dex */
public class MediaPlayerData extends PlayerData implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {
    static final String IMPLEMENTATION_NAME = "MediaPlayer";
    private boolean mIsBuffering;
    private MediaPlayer mMediaPlayer;
    private boolean mMediaPlayerHasStartedEver;
    private ModuleRegistry mModuleRegistry;
    private Integer mPlayableDurationMillis;

    @Override // expo.modules.p019av.player.PlayerData
    String getImplementationName() {
        return IMPLEMENTATION_NAME;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaPlayerData(AVManagerInterface aVManagerInterface, Context context, Uri uri, Map<String, Object> map) {
        super(aVManagerInterface, uri, map);
        this.mMediaPlayer = null;
        this.mModuleRegistry = null;
        this.mMediaPlayerHasStartedEver = false;
        this.mPlayableDurationMillis = null;
        this.mIsBuffering = false;
        this.mModuleRegistry = aVManagerInterface.getModuleRegistry();
    }

    @Override // expo.modules.p019av.player.PlayerData
    public void load(final Bundle bundle, final PlayerData.LoadCompletionListener loadCompletionListener) {
        if (this.mMediaPlayer != null) {
            loadCompletionListener.onLoadError("Load encountered an error: MediaPlayerData cannot be loaded twice.");
            return;
        }
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            Uri uri = this.mUri;
            if (uri.getScheme() == null) {
                int identifier = this.mAVModule.getContext().getResources().getIdentifier(uri.toString(), "raw", this.mAVModule.getContext().getPackageName());
                uri = Uri.parse("android.resource://" + this.mAVModule.getContext().getPackageName() + "/" + identifier);
            }
            if (Build.VERSION.SDK_INT >= 26) {
                mediaPlayer.setDataSource(this.mAVModule.getContext(), uri, null, getHttpCookiesList());
            } else {
                HashMap hashMap = new HashMap(1);
                StringBuilder sb = new StringBuilder();
                for (HttpCookie httpCookie : getHttpCookiesList()) {
                    sb.append(httpCookie.getName());
                    sb.append("=");
                    sb.append(httpCookie.getValue());
                    sb.append("; ");
                }
                sb.append(IOUtils.LINE_SEPARATOR_WINDOWS);
                hashMap.put(HttpHeaders.COOKIE, sb.toString());
                if (this.mRequestHeaders != null) {
                    for (Map.Entry<String, Object> entry : this.mRequestHeaders.entrySet()) {
                        if (entry.getValue() instanceof String) {
                            hashMap.put(entry.getKey(), (String) entry.getValue());
                        }
                    }
                }
                mediaPlayer.setDataSource(this.mAVModule.getContext(), uri, hashMap);
            }
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: expo.modules.av.player.MediaPlayerData.1
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer2, int r4, int r5) {
                    PlayerData.LoadCompletionListener loadCompletionListener2 = loadCompletionListener;
                    loadCompletionListener2.onLoadError("Load encountered an error: the OnErrorListener was called with 'what' code " + r4 + " and 'extra' code " + r5 + ".");
                    return true;
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: expo.modules.av.player.MediaPlayerData.2
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    MediaPlayerData.this.mMediaPlayer = mediaPlayer2;
                    MediaPlayerData.this.mMediaPlayer.setOnBufferingUpdateListener(MediaPlayerData.this);
                    MediaPlayerData.this.mMediaPlayer.setOnCompletionListener(MediaPlayerData.this);
                    MediaPlayerData.this.mMediaPlayer.setOnErrorListener(MediaPlayerData.this);
                    MediaPlayerData.this.mMediaPlayer.setOnInfoListener(MediaPlayerData.this);
                    MediaPlayerData.this.setStatusWithListener(bundle, new PlayerData.SetStatusCompletionListener() { // from class: expo.modules.av.player.MediaPlayerData.2.1
                        @Override // expo.modules.p019av.player.PlayerData.SetStatusCompletionListener
                        public void onSetStatusComplete() {
                            loadCompletionListener.onLoadSuccess(MediaPlayerData.this.getStatus());
                        }

                        @Override // expo.modules.p019av.player.PlayerData.SetStatusCompletionListener
                        public void onSetStatusError(String str) {
                            loadCompletionListener.onLoadSuccess(MediaPlayerData.this.getStatus());
                        }
                    });
                }
            });
            try {
                mediaPlayer.prepareAsync();
            } catch (Throwable th) {
                loadCompletionListener.onLoadError("Load encountered an error: an exception was thrown from prepareAsync() with message: " + th.toString());
            }
        } catch (Throwable th2) {
            loadCompletionListener.onLoadError("Load encountered an error: setDataSource() threw an exception was thrown with message: " + th2.toString());
        }
    }

    @Override // expo.modules.p019av.player.PlayerData
    public synchronized void release() {
        super.release();
        stopUpdatingProgressIfNecessary();
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setOnBufferingUpdateListener(null);
            this.mMediaPlayer.setOnCompletionListener(null);
            this.mMediaPlayer.setOnErrorListener(null);
            this.mMediaPlayer.setOnInfoListener(null);
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    @Override // expo.modules.p019av.player.PlayerData
    protected double getCurrentPositionSeconds() {
        return this.mMediaPlayer.getCurrentPosition() / 1000.0d;
    }

    @Override // expo.modules.p019av.player.PlayerData
    boolean shouldContinueUpdatingProgress() {
        return (this.mMediaPlayer == null || this.mIsBuffering) ? false : true;
    }

    private void playMediaPlayerWithRateMAndHigher(float f) {
        PlaybackParams playbackParams = this.mMediaPlayer.getPlaybackParams();
        playbackParams.setPitch(this.mShouldCorrectPitch ? 1.0f : f);
        playbackParams.setSpeed(f);
        playbackParams.setAudioFallbackMode(0);
        this.mMediaPlayer.setPlaybackParams(playbackParams);
        this.mMediaPlayer.start();
    }

    @Override // expo.modules.p019av.player.PlayerData
    void playPlayerWithRateAndMuteIfNecessary() throws AudioFocusNotAcquiredException {
        if (this.mMediaPlayer == null || !shouldPlayerPlay()) {
            return;
        }
        if (!this.mIsMuted) {
            this.mAVModule.acquireAudioFocus();
        }
        updateVolumeMuteAndDuck();
        if (Build.VERSION.SDK_INT < 23) {
            if (!this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.start();
                this.mMediaPlayerHasStartedEver = true;
            }
        } else {
            boolean z = false;
            try {
                PlaybackParams playbackParams = this.mMediaPlayer.getPlaybackParams();
                float speed = playbackParams.getSpeed();
                boolean z2 = playbackParams.getPitch() == 1.0f;
                if (speed == this.mRate) {
                    if (z2 == this.mShouldCorrectPitch) {
                        z = true;
                    }
                }
            } catch (Throwable unused) {
            }
            if (this.mRate != 0.0f && (!this.mMediaPlayer.isPlaying() || !z)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    playMediaPlayerWithRateMAndHigher(this.mRate);
                } else if (Build.VERSION.SDK_INT >= 23) {
                    playMediaPlayerWithRateMAndHigher(2.0f);
                    this.mMediaPlayer.pause();
                    playMediaPlayerWithRateMAndHigher(this.mRate);
                }
                this.mMediaPlayerHasStartedEver = true;
            }
        }
        beginUpdatingProgressIfNecessary();
    }

    @Override // expo.modules.p019av.player.PlayerData
    void applyNewStatus(Integer num, Boolean bool) throws AudioFocusNotAcquiredException, IllegalStateException {
        if (this.mMediaPlayer == null) {
            throw new IllegalStateException("mMediaPlayer is null!");
        }
        if (Build.VERSION.SDK_INT < 23 && this.mRate != 1.0f) {
            Log.w("Expo MediaPlayerData", "Cannot set audio/video playback rate for Android SDK < 23.");
            this.mRate = 1.0f;
        }
        if (bool != null) {
            this.mMediaPlayer.setLooping(bool.booleanValue());
        }
        if (!shouldPlayerPlay()) {
            if (this.mMediaPlayerHasStartedEver) {
                this.mMediaPlayer.pause();
            }
            stopUpdatingProgressIfNecessary();
        }
        updateVolumeMuteAndDuck();
        if (num != null && num.intValue() != this.mMediaPlayer.getCurrentPosition()) {
            this.mMediaPlayer.seekTo(num.intValue());
        }
        playPlayerWithRateAndMuteIfNecessary();
    }

    @Override // expo.modules.p019av.player.PlayerData
    boolean isLoaded() {
        return this.mMediaPlayer != null;
    }

    @Override // expo.modules.p019av.player.PlayerData
    void getExtraStatusFields(Bundle bundle) {
        Integer valueOf = Integer.valueOf(this.mMediaPlayer.getDuration());
        if (valueOf.intValue() < 0) {
            valueOf = null;
        }
        if (valueOf != null) {
            bundle.putInt("durationMillis", valueOf.intValue());
        }
        bundle.putInt("positionMillis", getClippedIntegerForValue(Integer.valueOf(this.mMediaPlayer.getCurrentPosition()), 0, valueOf));
        Integer num = this.mPlayableDurationMillis;
        if (num != null) {
            bundle.putInt("playableDurationMillis", getClippedIntegerForValue(num, 0, valueOf));
        }
        bundle.putBoolean(PlayerData.STATUS_IS_PLAYING_KEY_PATH, this.mMediaPlayer.isPlaying());
        bundle.putBoolean("isBuffering", this.mIsBuffering);
        bundle.putBoolean("isLooping", this.mMediaPlayer.isLooping());
    }

    @Override // expo.modules.p019av.player.PlayerData
    public Pair<Integer, Integer> getVideoWidthHeight() {
        return this.mMediaPlayer == null ? new Pair<>(0, 0) : new Pair<>(Integer.valueOf(this.mMediaPlayer.getVideoWidth()), Integer.valueOf(this.mMediaPlayer.getVideoHeight()));
    }

    @Override // expo.modules.p019av.player.PlayerData
    public void tryUpdateVideoSurface(Surface surface) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.setSurface(surface);
        if (this.mMediaPlayerHasStartedEver || this.mShouldPlay) {
            return;
        }
        this.mMediaPlayer.start();
        this.mMediaPlayer.pause();
        this.mMediaPlayerHasStartedEver = true;
    }

    @Override // expo.modules.p019av.player.PlayerData
    public int getAudioSessionId() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getAudioSessionId();
        }
        return 0;
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void pauseImmediately() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null && this.mMediaPlayerHasStartedEver) {
            mediaPlayer.pause();
        }
        stopUpdatingProgressIfNecessary();
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public boolean requiresAudioFocus() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        return mediaPlayer != null && (mediaPlayer.isPlaying() || shouldPlayerPlay()) && !this.mIsMuted;
    }

    @Override // expo.modules.p019av.AudioEventHandler
    public void updateVolumeMuteAndDuck() {
        float f;
        if (this.mMediaPlayer != null) {
            float volumeForDuckAndFocus = this.mAVModule.getVolumeForDuckAndFocus(this.mIsMuted, this.mVolume);
            if (this.mPan > 0.0f) {
                f = volumeForDuckAndFocus;
                volumeForDuckAndFocus = (1.0f - this.mPan) * volumeForDuckAndFocus;
            } else {
                f = this.mPan < 0.0f ? (this.mPan + 1.0f) * volumeForDuckAndFocus : volumeForDuckAndFocus;
            }
            this.mMediaPlayer.setVolume(volumeForDuckAndFocus, f);
        }
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int r6) {
        if (mediaPlayer.getDuration() >= 0) {
            this.mPlayableDurationMillis = Integer.valueOf((int) (mediaPlayer.getDuration() * (r6 / 100.0d)));
        } else {
            this.mPlayableDurationMillis = null;
        }
        callStatusUpdateListener();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        callStatusUpdateListenerWithDidJustFinish();
        if (mediaPlayer.isLooping()) {
            return;
        }
        this.mAVModule.abandonAudioFocusIfUnused();
        stopUpdatingProgressIfNecessary();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int r4, int r5) {
        release();
        if (this.mErrorListener != null) {
            PlayerData.ErrorListener errorListener = this.mErrorListener;
            errorListener.onError("MediaPlayer failed with 'what' code " + r4 + " and 'extra' code " + r5 + ".");
            return true;
        }
        return true;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, int r4, int r5) {
        if (r4 != 3) {
            if (r4 == 701) {
                this.mIsBuffering = true;
            } else if (r4 == 702) {
                this.mIsBuffering = false;
                beginUpdatingProgressIfNecessary();
            }
        } else if (this.mVideoSizeUpdateListener != null) {
            this.mVideoSizeUpdateListener.onVideoSizeUpdate(new Pair<>(Integer.valueOf(mediaPlayer.getVideoWidth()), Integer.valueOf(mediaPlayer.getVideoHeight())));
        }
        callStatusUpdateListener();
        return true;
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        callStatusUpdateListener();
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int r3, int r4) {
        if (this.mVideoSizeUpdateListener != null) {
            this.mVideoSizeUpdateListener.onVideoSizeUpdate(new Pair<>(Integer.valueOf(r3), Integer.valueOf(r4)));
        }
    }

    private List<HttpCookie> getHttpCookiesList() {
        CookieHandler cookieHandler;
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        if (moduleRegistry != null && (cookieHandler = (CookieHandler) moduleRegistry.getModule(CookieHandler.class)) != null) {
            try {
                List<String> list = cookieHandler.get(URI.create(this.mUri.toString()), null).get(HttpHeaders.COOKIE);
                if (list != null) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : list) {
                        arrayList.addAll(HttpCookie.parse(str));
                    }
                    return arrayList;
                }
                return Collections.emptyList();
            } catch (IOException unused) {
            }
        }
        return Collections.emptyList();
    }
}
