package expo.modules.p019av.video;

import kotlin.Metadata;

/* compiled from: FullscreenPlayerUpdate.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/av/video/FullscreenPlayerUpdate;", "", "jsValue", "", "(Ljava/lang/String;II)V", "getJsValue", "()I", "FULLSCREEN_PLAYER_WILL_PRESENT", "FULLSCREEN_PLAYER_DID_PRESENT", "FULLSCREEN_PLAYER_WILL_DISMISS", "FULLSCREEN_PLAYER_DID_DISMISS", "expo-av_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.av.video.FullscreenPlayerUpdate */
/* loaded from: classes4.dex */
public enum FullscreenPlayerUpdate {
    FULLSCREEN_PLAYER_WILL_PRESENT(0),
    FULLSCREEN_PLAYER_DID_PRESENT(1),
    FULLSCREEN_PLAYER_WILL_DISMISS(2),
    FULLSCREEN_PLAYER_DID_DISMISS(3);
    
    private final int jsValue;

    FullscreenPlayerUpdate(int r3) {
        this.jsValue = r3;
    }

    public final int getJsValue() {
        return this.jsValue;
    }
}
