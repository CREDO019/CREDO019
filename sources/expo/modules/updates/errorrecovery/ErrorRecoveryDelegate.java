package expo.modules.updates.errorrecovery;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.launcher.Launcher;
import kotlin.Metadata;

/* compiled from: ErrorRecoveryDelegate.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0013J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\tH&J\b\u0010\u000b\u001a\u00020\tH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH&J\u0014\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H&¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;", "", "getCheckAutomaticallyConfiguration", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getLaunchedUpdateSuccessfulLaunchCount", "", "getRemoteLoadStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "loadRemoteUpdate", "", "markFailedLaunchForLaunchedUpdate", "markSuccessfulLaunchForLaunchedUpdate", "relaunch", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "throwException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "RemoteLoadStatus", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public interface ErrorRecoveryDelegate {

    /* compiled from: ErrorRecoveryDelegate.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "", "(Ljava/lang/String;I)V", "IDLE", "NEW_UPDATE_LOADING", "NEW_UPDATE_LOADED", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public enum RemoteLoadStatus {
        IDLE,
        NEW_UPDATE_LOADING,
        NEW_UPDATE_LOADED
    }

    UpdatesConfiguration.CheckAutomaticallyConfiguration getCheckAutomaticallyConfiguration();

    int getLaunchedUpdateSuccessfulLaunchCount();

    RemoteLoadStatus getRemoteLoadStatus();

    void loadRemoteUpdate();

    void markFailedLaunchForLaunchedUpdate();

    void markSuccessfulLaunchForLaunchedUpdate();

    void relaunch(Launcher.LauncherCallback launcherCallback);

    void throwException(Exception exc);
}
