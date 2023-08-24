package expo.modules.p019av.player;

import android.os.Bundle;
import android.widget.MediaController;

/* renamed from: expo.modules.av.player.PlayerDataControl */
/* loaded from: classes4.dex */
public class PlayerDataControl implements MediaController.MediaPlayerControl {
    private final PlayerData mPlayerData;

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return true;
    }

    public PlayerDataControl(PlayerData playerData) {
        this.mPlayerData = playerData;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("shouldPlay", true);
        this.mPlayerData.setStatus(bundle, null);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("shouldPlay", false);
        this.mPlayerData.setStatus(bundle, null);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        Bundle status = this.mPlayerData.getStatus();
        if (status.getBoolean("isLoaded") && status.containsKey("durationMillis")) {
            return status.getInt("durationMillis");
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        Bundle status = this.mPlayerData.getStatus();
        if (status.getBoolean("isLoaded")) {
            return status.getInt("positionMillis");
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int r4) {
        Bundle bundle = new Bundle();
        bundle.putDouble("positionMillis", r4);
        this.mPlayerData.setStatus(bundle, null);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        Bundle status = this.mPlayerData.getStatus();
        return status.getBoolean("isLoaded") && status.getBoolean(PlayerData.STATUS_IS_PLAYING_KEY_PATH);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        Bundle status = this.mPlayerData.getStatus();
        if (status.getBoolean("isLoaded") && status.containsKey("durationMillis") && status.containsKey("playableDurationMillis")) {
            return (int) ((status.getInt("playableDurationMillis") / status.getInt("durationMillis")) * 100.0d);
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return this.mPlayerData.getAudioSessionId();
    }

    public boolean isFullscreen() {
        return this.mPlayerData.isPresentedFullscreen();
    }

    public void toggleFullscreen() {
        this.mPlayerData.toggleFullscreen();
    }
}
