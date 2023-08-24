package expo.modules.updates;

import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.p021db.DatabaseHolder;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: UpdatesInterface.kt */
@Metadata(m184d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010'\u001a\u00020\u0017H&J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H&J\b\u0010,\u001a\u00020)H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u0012\u0010\u001a\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0011R \u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0012\u0010#\u001a\u00020$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006-"}, m183d2 = {"Lexpo/modules/updates/UpdatesInterface;", "", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getConfiguration", "()Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "getDatabaseHolder", "()Lexpo/modules/updates/db/DatabaseHolder;", "directory", "Ljava/io/File;", "getDirectory", "()Ljava/io/File;", "embeddedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getEmbeddedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "getFileDownloader", "()Lexpo/modules/updates/loader/FileDownloader;", "isEmbeddedLaunch", "", "()Z", "isEmergencyLaunch", "isUsingEmbeddedAssets", "launchedUpdate", "getLaunchedUpdate", "localAssetFiles", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "", "getLocalAssetFiles", "()Ljava/util/Map;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "getSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "canRelaunch", "relaunchReactApplication", "", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "resetSelectionPolicy", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public interface UpdatesInterface {
    boolean canRelaunch();

    UpdatesConfiguration getConfiguration();

    DatabaseHolder getDatabaseHolder();

    File getDirectory();

    UpdateEntity getEmbeddedUpdate();

    FileDownloader getFileDownloader();

    UpdateEntity getLaunchedUpdate();

    Map<AssetEntity, String> getLocalAssetFiles();

    SelectionPolicy getSelectionPolicy();

    boolean isEmbeddedLaunch();

    boolean isEmergencyLaunch();

    boolean isUsingEmbeddedAssets();

    void relaunchReactApplication(Launcher.LauncherCallback launcherCallback);

    void resetSelectionPolicy();
}
