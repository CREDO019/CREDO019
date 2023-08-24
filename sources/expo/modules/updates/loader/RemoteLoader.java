package expo.modules.updates.loader;

import android.content.Context;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RemoteLoader.kt */
@Metadata(m184d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB;\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eBC\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J2\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J(\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u001aH\u0014J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0015\u001a\u00020\u0016H\u0014R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/updates/loader/RemoteLoader;", "Lexpo/modules/updates/loader/Loader;", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "updatesDirectory", "Ljava/io/File;", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Lexpo/modules/updates/loader/FileDownloader;Ljava/io/File;Lexpo/modules/updates/db/entity/UpdateEntity;)V", "mFileDownloader", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Lexpo/modules/updates/loader/FileDownloader;Ljava/io/File;Lexpo/modules/updates/db/entity/UpdateEntity;Lexpo/modules/updates/loader/LoaderFiles;)V", "loadAsset", "", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "callback", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "loadManifest", "Lexpo/modules/updates/loader/FileDownloader$ManifestDownloadCallback;", "shouldSkipAsset", "", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class RemoteLoader extends Loader {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "RemoteLoader";
    private final UpdateEntity launchedUpdate;
    private final LoaderFiles loaderFiles;
    private final FileDownloader mFileDownloader;

    @Override // expo.modules.updates.loader.Loader
    protected boolean shouldSkipAsset(AssetEntity assetEntity) {
        Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteLoader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, FileDownloader mFileDownloader, File file, UpdateEntity updateEntity, LoaderFiles loaderFiles) {
        super(context, configuration, database, file, loaderFiles);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(mFileDownloader, "mFileDownloader");
        Intrinsics.checkNotNullParameter(loaderFiles, "loaderFiles");
        this.mFileDownloader = mFileDownloader;
        this.launchedUpdate = updateEntity;
        this.loaderFiles = loaderFiles;
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public RemoteLoader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, FileDownloader fileDownloader, File file, UpdateEntity updateEntity) {
        this(context, configuration, database, fileDownloader, file, updateEntity, new LoaderFiles());
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
    }

    @Override // expo.modules.updates.loader.Loader
    protected void loadManifest(Context context, UpdatesDatabase database, UpdatesConfiguration configuration, FileDownloader.ManifestDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        UpdateManifest readEmbeddedManifest = this.loaderFiles.readEmbeddedManifest(context, configuration);
        this.mFileDownloader.downloadManifest(configuration, FileDownloader.Companion.getExtraHeaders(database, configuration, this.launchedUpdate, readEmbeddedManifest == null ? null : readEmbeddedManifest.getUpdateEntity()), context, callback);
    }

    @Override // expo.modules.updates.loader.Loader
    protected void loadAsset(Context context, AssetEntity assetEntity, File file, UpdatesConfiguration configuration, FileDownloader.AssetDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.mFileDownloader.downloadAsset(assetEntity, file, configuration, context, callback);
    }

    /* compiled from: RemoteLoader.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/loader/RemoteLoader$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
