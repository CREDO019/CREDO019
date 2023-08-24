package expo.modules.updates.loader;

import android.content.Context;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.entity.AssetEntity;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EmbeddedLoader.kt */
@Metadata(m184d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB)\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB1\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ2\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J(\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0017H\u0014J\u001b\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001aH\u0002¢\u0006\u0002\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\u0013H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m183d2 = {"Lexpo/modules/updates/loader/EmbeddedLoader;", "Lexpo/modules/updates/loader/Loader;", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "updatesDirectory", "Ljava/io/File;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Ljava/io/File;)V", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Ljava/io/File;Lexpo/modules/updates/loader/LoaderFiles;)V", "pixelDensity", "", "loadAsset", "", "assetEntity", "Lexpo/modules/updates/db/entity/AssetEntity;", "callback", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "loadManifest", "Lexpo/modules/updates/loader/FileDownloader$ManifestDownloadCallback;", "pickClosestScale", "scales", "", "([Ljava/lang/Float;)F", "shouldSkipAsset", "", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class EmbeddedLoader extends Loader {
    public static final String BARE_BUNDLE_FILENAME = "index.android.bundle";
    public static final String BUNDLE_FILENAME = "app.bundle";
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "EmbeddedLoader";
    private final UpdatesConfiguration configuration;
    private final Context context;
    private final LoaderFiles loaderFiles;
    private final float pixelDensity;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EmbeddedLoader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, File file, LoaderFiles loaderFiles) {
        super(context, configuration, database, file, loaderFiles);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(loaderFiles, "loaderFiles");
        this.context = context;
        this.configuration = configuration;
        this.loaderFiles = loaderFiles;
        this.pixelDensity = context.getResources().getDisplayMetrics().density;
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public EmbeddedLoader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, File file) {
        this(context, configuration, database, file, new LoaderFiles());
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
    }

    @Override // expo.modules.updates.loader.Loader
    protected void loadManifest(Context context, UpdatesDatabase database, UpdatesConfiguration configuration, FileDownloader.ManifestDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        UpdateManifest readEmbeddedManifest = this.loaderFiles.readEmbeddedManifest(this.context, this.configuration);
        if (readEmbeddedManifest != null) {
            callback.onSuccess(readEmbeddedManifest);
        } else {
            callback.onFailure("Embedded manifest is null", new Exception("Embedded manifest is null"));
        }
    }

    @Override // expo.modules.updates.loader.Loader
    protected void loadAsset(Context context, AssetEntity assetEntity, File file, UpdatesConfiguration configuration, FileDownloader.AssetDownloadCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(callback, "callback");
        String createFilenameForAsset = UpdatesUtils.INSTANCE.createFilenameForAsset(assetEntity);
        File file2 = new File(file, createFilenameForAsset);
        if (this.loaderFiles.fileExists(file2)) {
            assetEntity.setRelativePath(createFilenameForAsset);
            callback.onSuccess(assetEntity, false);
            return;
        }
        try {
            assetEntity.setHash(this.loaderFiles.copyAssetAndGetHash(assetEntity, file2, context));
            assetEntity.setDownloadTime(new Date());
            assetEntity.setRelativePath(createFilenameForAsset);
            callback.onSuccess(assetEntity, true);
        } catch (FileNotFoundException unused) {
            String embeddedAssetFilename = assetEntity.getEmbeddedAssetFilename() != null ? assetEntity.getEmbeddedAssetFilename() : assetEntity.getResourcesFilename();
            throw new AssertionError("APK bundle must contain the expected embedded asset " + embeddedAssetFilename);
        } catch (Exception e) {
            callback.onFailure(e, assetEntity);
        }
    }

    @Override // expo.modules.updates.loader.Loader
    protected boolean shouldSkipAsset(AssetEntity assetEntity) {
        Intrinsics.checkNotNullParameter(assetEntity, "assetEntity");
        if (assetEntity.getScales() == null || assetEntity.getScale() == null) {
            return false;
        }
        Float[] scales = assetEntity.getScales();
        Intrinsics.checkNotNull(scales);
        return !Intrinsics.areEqual(pickClosestScale(scales), assetEntity.getScale());
    }

    private final float pickClosestScale(Float[] fArr) {
        int length = fArr.length;
        float f = 0.0f;
        int r3 = 0;
        float f2 = Float.MAX_VALUE;
        while (r3 < length) {
            float floatValue = fArr[r3].floatValue();
            r3++;
            if (floatValue >= this.pixelDensity && floatValue < f2) {
                f2 = floatValue;
            }
            if (floatValue > f) {
                f = floatValue;
            }
        }
        return f2 < Float.MAX_VALUE ? f2 : f;
    }

    /* compiled from: EmbeddedLoader.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, m183d2 = {"Lexpo/modules/updates/loader/EmbeddedLoader$Companion;", "", "()V", "BARE_BUNDLE_FILENAME", "", "BUNDLE_FILENAME", "TAG", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
