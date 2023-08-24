package expo.modules.p019av;

/* renamed from: expo.modules.av.AudioEventHandler */
/* loaded from: classes4.dex */
public interface AudioEventHandler {
    void handleAudioFocusGained();

    void handleAudioFocusInterruptionBegan();

    void onPause();

    void onResume();

    void pauseImmediately();

    boolean requiresAudioFocus();

    void updateVolumeMuteAndDuck();
}
