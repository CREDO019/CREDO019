package expo.modules.updates.manifest;

import expo.modules.manifests.core.BareManifest;
import expo.modules.manifests.core.LegacyManifest;
import expo.modules.manifests.core.NewManifest;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.manifest.BareUpdateManifest;
import expo.modules.updates.manifest.LegacyUpdateManifest;
import expo.modules.updates.manifest.NewUpdateManifest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ManifestFactory.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ*\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/updates/manifest/ManifestFactory;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getEmbeddedManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "manifestJson", "Lorg/json/JSONObject;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getManifest", "manifestHeaderData", "Lexpo/modules/updates/manifest/ManifestHeaderData;", "extensions", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ManifestFactory {
    public static final ManifestFactory INSTANCE = new ManifestFactory();
    private static final String TAG = "ManifestFactory";

    private ManifestFactory() {
    }

    public final UpdateManifest getManifest(JSONObject manifestJson, ManifestHeaderData manifestHeaderData, JSONObject jSONObject, UpdatesConfiguration updatesConfiguration) throws Exception {
        Intrinsics.checkNotNullParameter(manifestJson, "manifestJson");
        Intrinsics.checkNotNullParameter(manifestHeaderData, "manifestHeaderData");
        String protocolVersion = manifestHeaderData.getProtocolVersion();
        if (protocolVersion == null) {
            LegacyUpdateManifest.Companion companion = LegacyUpdateManifest.Companion;
            LegacyManifest legacyManifest = new LegacyManifest(manifestJson);
            Intrinsics.checkNotNull(updatesConfiguration);
            return companion.fromLegacyManifest(legacyManifest, updatesConfiguration);
        }
        Integer valueOf = Integer.valueOf(protocolVersion);
        if (valueOf != null && valueOf.intValue() == 0) {
            NewUpdateManifest.Companion companion2 = NewUpdateManifest.Companion;
            NewManifest newManifest = new NewManifest(manifestJson);
            Intrinsics.checkNotNull(updatesConfiguration);
            return companion2.fromNewManifest(newManifest, manifestHeaderData, jSONObject, updatesConfiguration);
        }
        throw new Exception("Unsupported expo-protocol-version: " + protocolVersion);
    }

    public final UpdateManifest getEmbeddedManifest(JSONObject manifestJson, UpdatesConfiguration updatesConfiguration) throws JSONException {
        Intrinsics.checkNotNullParameter(manifestJson, "manifestJson");
        if (manifestJson.has("releaseId")) {
            LegacyUpdateManifest.Companion companion = LegacyUpdateManifest.Companion;
            LegacyManifest legacyManifest = new LegacyManifest(manifestJson);
            Intrinsics.checkNotNull(updatesConfiguration);
            return companion.fromLegacyManifest(legacyManifest, updatesConfiguration);
        }
        BareUpdateManifest.Companion companion2 = BareUpdateManifest.Companion;
        BareManifest bareManifest = new BareManifest(manifestJson);
        Intrinsics.checkNotNull(updatesConfiguration);
        return companion2.fromBareManifest(bareManifest, updatesConfiguration);
    }
}
