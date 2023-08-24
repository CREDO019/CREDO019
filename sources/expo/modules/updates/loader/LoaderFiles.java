package expo.modules.updates.loader;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.manifest.EmbeddedManifest;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.entity.AssetEntity;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;

/* compiled from: LoaderFiles.kt */
@Metadata(m184d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ%\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\fJ%\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderFiles;", "", "()V", "copyAssetAndGetHash", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "destination", "Ljava/io/File;", "context", "Landroid/content/Context;", "copyContextAssetAndGetHash", "copyContextAssetAndGetHash$expo_updates_release", "copyResourceAndGetHash", "copyResourceAndGetHash$expo_updates_release", "fileExists", "", "readEmbeddedManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public class LoaderFiles {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "LoaderFiles";

    public final boolean fileExists(File destination) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        return destination.exists();
    }

    public final UpdateManifest readEmbeddedManifest(Context context, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return EmbeddedManifest.INSTANCE.get(context, configuration);
    }

    public final byte[] copyAssetAndGetHash(AssetEntity asset, File destination, Context context) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(context, "context");
        if (asset.getEmbeddedAssetFilename() != null) {
            return copyContextAssetAndGetHash$expo_updates_release(asset, destination, context);
        }
        if (asset.getResourcesFilename() != null && asset.getResourcesFolder() != null) {
            return copyResourceAndGetHash$expo_updates_release(asset, destination, context);
        }
        String key = asset.getKey();
        throw new AssertionError("Failed to copy embedded asset " + key + " from APK assets or resources because not enough information was provided.");
    }

    public final byte[] copyContextAssetAndGetHash$expo_updates_release(AssetEntity asset, File destination, Context context) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            AssetManager assets = context.getAssets();
            String embeddedAssetFilename = asset.getEmbeddedAssetFilename();
            Intrinsics.checkNotNull(embeddedAssetFilename);
            InputStream open = assets.open(embeddedAssetFilename);
            InputStream inputStream = open;
            UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(inputStream, "inputStream");
            byte[] verifySHA256AndWriteToFile = updatesUtils.verifySHA256AndWriteToFile(inputStream, destination, null);
            Closeable.closeFinally(open, null);
            return verifySHA256AndWriteToFile;
        } catch (Exception e) {
            String str = TAG;
            String embeddedAssetFilename2 = asset.getEmbeddedAssetFilename();
            Log.e(str, "Failed to copy asset " + embeddedAssetFilename2, e);
            throw e;
        }
    }

    public final byte[] copyResourceAndGetHash$expo_updates_release(AssetEntity asset, File destination, Context context) throws NoSuchAlgorithmException, IOException {
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            InputStream openRawResource = context.getResources().openRawResource(context.getResources().getIdentifier(asset.getResourcesFilename(), asset.getResourcesFolder(), context.getPackageName()));
            InputStream inputStream = openRawResource;
            UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(inputStream, "inputStream");
            byte[] verifySHA256AndWriteToFile = updatesUtils.verifySHA256AndWriteToFile(inputStream, destination, null);
            Closeable.closeFinally(openRawResource, null);
            return verifySHA256AndWriteToFile;
        } catch (Exception e) {
            String str = TAG;
            String embeddedAssetFilename = asset.getEmbeddedAssetFilename();
            Log.e(str, "Failed to copy asset " + embeddedAssetFilename, e);
            throw e;
        }
    }

    /* compiled from: LoaderFiles.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderFiles$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
