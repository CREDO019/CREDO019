package expo.modules.updates.manifest;

import android.content.Context;
import android.util.Log;
import expo.modules.updates.UpdatesConfiguration;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;
import org.apache.commons.p028io.IOUtils;
import org.json.JSONObject;

/* compiled from: EmbeddedManifest.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/updates/manifest/EmbeddedManifest;", "", "()V", "MANIFEST_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "sEmbeddedManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "get", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class EmbeddedManifest {
    private static final String MANIFEST_FILENAME = "app.manifest";
    private static UpdateManifest sEmbeddedManifest;
    public static final EmbeddedManifest INSTANCE = new EmbeddedManifest();
    private static final String TAG = "EmbeddedManifest";

    private EmbeddedManifest() {
    }

    public final UpdateManifest get(Context context, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        if (configuration.getHasEmbeddedUpdate()) {
            if (sEmbeddedManifest == null) {
                try {
                    InputStream open = context.getAssets().open(MANIFEST_FILENAME);
                    JSONObject jSONObject = new JSONObject(IOUtils.toString(open, "UTF-8"));
                    jSONObject.put("isVerified", true);
                    sEmbeddedManifest = ManifestFactory.INSTANCE.getEmbeddedManifest(jSONObject, configuration);
                    Unit unit = Unit.INSTANCE;
                    Closeable.closeFinally(open, null);
                } catch (Exception e) {
                    Log.e(TAG, "Could not read embedded manifest", e);
                    String message = e.getMessage();
                    throw new AssertionError("The embedded manifest is invalid or could not be read. Make sure you have configured expo-updates correctly in android/app/build.gradle. " + message);
                }
            }
            UpdateManifest updateManifest = sEmbeddedManifest;
            Intrinsics.checkNotNull(updateManifest);
            return updateManifest;
        }
        return null;
    }
}
