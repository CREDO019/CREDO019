package expo.modules.updates.manifest;

import android.net.Uri;
import android.util.Log;
import expo.modules.manifests.core.LegacyManifest;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.loader.EmbeddedLoader;
import expo.modules.updates.manifest.LegacyUpdateManifest;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.p021db.enums.UpdateStatus;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LegacyUpdateManifest.kt */
@Metadata(m184d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 -2\u00020\u0001:\u0001-BI\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010R!\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0017\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0017\u001a\u0004\b\u001c\u0010\u001eR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\"\u001a\u0004\u0018\u00010#X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0016\u0010&\u001a\u0004\u0018\u00010#X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010%R\u001b\u0010(\u001a\u00020)8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0017\u001a\u0004\b*\u0010+¨\u0006."}, m183d2 = {"Lexpo/modules/updates/manifest/LegacyUpdateManifest;", "Lexpo/modules/updates/manifest/UpdateManifest;", "manifest", "Lexpo/modules/manifests/core/LegacyManifest;", "mManifestUrl", "Landroid/net/Uri;", "mId", "Ljava/util/UUID;", "mScopeKey", "", "mCommitTime", "Ljava/util/Date;", "mRuntimeVersion", "mBundleUrl", "mAssets", "Lorg/json/JSONArray;", "(Lexpo/modules/manifests/core/LegacyManifest;Landroid/net/Uri;Ljava/util/UUID;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Landroid/net/Uri;Lorg/json/JSONArray;)V", "assetEntityList", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getAssetEntityList", "()Ljava/util/List;", "assetEntityList$delegate", "Lkotlin/Lazy;", "assetsUrlBase", "getAssetsUrlBase", "()Landroid/net/Uri;", "assetsUrlBase$delegate", "isDevelopmentMode", "", "()Z", "isDevelopmentMode$delegate", "getManifest", "()Lexpo/modules/manifests/core/LegacyManifest;", "manifestFilters", "Lorg/json/JSONObject;", "getManifestFilters", "()Lorg/json/JSONObject;", "serverDefinedHeaders", "getServerDefinedHeaders", "updateEntity", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getUpdateEntity", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "updateEntity$delegate", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class LegacyUpdateManifest implements UpdateManifest {
    private static final String EXPO_ASSETS_URL_BASE = "https://classic-assets.eascdn.net/~assets/";
    private final Lazy assetEntityList$delegate;
    private final Lazy assetsUrlBase$delegate;
    private final Lazy isDevelopmentMode$delegate;
    private final JSONArray mAssets;
    private final Uri mBundleUrl;
    private final Date mCommitTime;
    private final UUID mId;
    private final Uri mManifestUrl;
    private final String mRuntimeVersion;
    private final String mScopeKey;
    private final LegacyManifest manifest;
    private final JSONObject manifestFilters;
    private final JSONObject serverDefinedHeaders;
    private final Lazy updateEntity$delegate;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdateManifest";
    private static final String[] EXPO_DOMAINS = {"expo.io", "exp.host", "expo.test"};

    public /* synthetic */ LegacyUpdateManifest(LegacyManifest legacyManifest, Uri uri, UUID r3, String str, Date date, String str2, Uri uri2, JSONArray jSONArray, DefaultConstructorMarker defaultConstructorMarker) {
        this(legacyManifest, uri, r3, str, date, str2, uri2, jSONArray);
    }

    private LegacyUpdateManifest(LegacyManifest legacyManifest, Uri uri, UUID r3, String str, Date date, String str2, Uri uri2, JSONArray jSONArray) {
        this.manifest = legacyManifest;
        this.mManifestUrl = uri;
        this.mId = r3;
        this.mScopeKey = str;
        this.mCommitTime = date;
        this.mRuntimeVersion = str2;
        this.mBundleUrl = uri2;
        this.mAssets = jSONArray;
        this.updateEntity$delegate = LazyKt.lazy(new Functions<UpdateEntity>() { // from class: expo.modules.updates.manifest.LegacyUpdateManifest$updateEntity$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final UpdateEntity invoke() {
                UUID r1;
                Date date2;
                String str3;
                String str4;
                r1 = LegacyUpdateManifest.this.mId;
                date2 = LegacyUpdateManifest.this.mCommitTime;
                str3 = LegacyUpdateManifest.this.mRuntimeVersion;
                str4 = LegacyUpdateManifest.this.mScopeKey;
                UpdateEntity updateEntity = new UpdateEntity(r1, date2, str3, str4);
                LegacyUpdateManifest legacyUpdateManifest = LegacyUpdateManifest.this;
                updateEntity.setManifest(legacyUpdateManifest.getManifest().getRawJson());
                if (legacyUpdateManifest.isDevelopmentMode()) {
                    updateEntity.setStatus(UpdateStatus.DEVELOPMENT);
                }
                return updateEntity;
            }
        });
        this.assetEntityList$delegate = LazyKt.lazy(new Functions<List<AssetEntity>>() { // from class: expo.modules.updates.manifest.LegacyUpdateManifest$assetEntityList$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final List<AssetEntity> invoke() {
                Uri uri3;
                JSONArray jSONArray2;
                JSONArray jSONArray3;
                JSONArray jSONArray4;
                JSONArray jSONArray5;
                String substring;
                String str3;
                Uri assetsUrlBase;
                ArrayList arrayList = new ArrayList();
                AssetEntity assetEntity = new AssetEntity(LegacyUpdateManifest.this.getManifest().getBundleKey(), "js");
                uri3 = LegacyUpdateManifest.this.mBundleUrl;
                assetEntity.setUrl(uri3);
                assetEntity.setLaunchAsset(true);
                assetEntity.setEmbeddedAssetFilename(EmbeddedLoader.BUNDLE_FILENAME);
                arrayList.add(assetEntity);
                jSONArray2 = LegacyUpdateManifest.this.mAssets;
                if (jSONArray2 != null) {
                    jSONArray3 = LegacyUpdateManifest.this.mAssets;
                    if (jSONArray3.length() > 0) {
                        int r1 = 0;
                        jSONArray4 = LegacyUpdateManifest.this.mAssets;
                        int length = jSONArray4.length();
                        while (r1 < length) {
                            int r32 = r1 + 1;
                            try {
                                jSONArray5 = LegacyUpdateManifest.this.mAssets;
                                String bundledAsset = jSONArray5.getString(r1);
                                Intrinsics.checkNotNullExpressionValue(bundledAsset, "bundledAsset");
                                int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) bundledAsset, '.', 0, false, 6, (Object) null);
                                if (lastIndexOf$default > 0) {
                                    substring = bundledAsset.substring(6, lastIndexOf$default);
                                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                                } else {
                                    substring = bundledAsset.substring(6);
                                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                                }
                                if (lastIndexOf$default > 0) {
                                    str3 = bundledAsset.substring(lastIndexOf$default + 1);
                                    Intrinsics.checkNotNullExpressionValue(str3, "this as java.lang.String).substring(startIndex)");
                                } else {
                                    str3 = "";
                                }
                                AssetEntity assetEntity2 = new AssetEntity(substring, str3);
                                assetsUrlBase = LegacyUpdateManifest.this.getAssetsUrlBase();
                                assetEntity2.setUrl(Uri.withAppendedPath(assetsUrlBase, substring));
                                assetEntity2.setEmbeddedAssetFilename(bundledAsset);
                                arrayList.add(assetEntity2);
                            } catch (JSONException e) {
                                Log.e(LegacyUpdateManifest.TAG, "Could not read asset from manifest", e);
                            }
                            r1 = r32;
                        }
                    }
                }
                return arrayList;
            }
        });
        this.assetsUrlBase$delegate = LazyKt.lazy(new Functions<Uri>() { // from class: expo.modules.updates.manifest.LegacyUpdateManifest$assetsUrlBase$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Uri invoke() {
                Uri uri3;
                LegacyUpdateManifest.Companion companion = LegacyUpdateManifest.Companion;
                uri3 = LegacyUpdateManifest.this.mManifestUrl;
                return companion.getAssetsUrlBase$expo_updates_release(uri3, LegacyUpdateManifest.this.getManifest());
            }
        });
        this.isDevelopmentMode$delegate = LazyKt.lazy(new Functions<Boolean>() { // from class: expo.modules.updates.manifest.LegacyUpdateManifest$isDevelopmentMode$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final Boolean invoke() {
                return Boolean.valueOf(LegacyUpdateManifest.this.getManifest().isDevelopmentMode());
            }
        });
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public LegacyManifest getManifest() {
        return this.manifest;
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public JSONObject getServerDefinedHeaders() {
        return this.serverDefinedHeaders;
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public JSONObject getManifestFilters() {
        return this.manifestFilters;
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public UpdateEntity getUpdateEntity() {
        return (UpdateEntity) this.updateEntity$delegate.getValue();
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public List<AssetEntity> getAssetEntityList() {
        return (List) this.assetEntityList$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Uri getAssetsUrlBase() {
        return (Uri) this.assetsUrlBase$delegate.getValue();
    }

    @Override // expo.modules.updates.manifest.UpdateManifest
    public boolean isDevelopmentMode() {
        return ((Boolean) this.isDevelopmentMode$delegate.getValue()).booleanValue();
    }

    /* compiled from: LegacyUpdateManifest.kt */
    @Metadata(m184d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\rH\u0000¢\u0006\u0002\b\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/updates/manifest/LegacyUpdateManifest$Companion;", "", "()V", "EXPO_ASSETS_URL_BASE", "", "EXPO_DOMAINS", "", "[Ljava/lang/String;", "TAG", "kotlin.jvm.PlatformType", "fromLegacyManifest", "Lexpo/modules/updates/manifest/LegacyUpdateManifest;", "manifest", "Lexpo/modules/manifests/core/LegacyManifest;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getAssetsUrlBase", "Landroid/net/Uri;", "manifestUrl", "legacyManifest", "getAssetsUrlBase$expo_updates_release", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LegacyUpdateManifest fromLegacyManifest(LegacyManifest manifest, UpdatesConfiguration configuration) throws JSONException {
            UUID fromString;
            Date date;
            Intrinsics.checkNotNullParameter(manifest, "manifest");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            if (manifest.isUsingDeveloperTool()) {
                fromString = UUID.randomUUID();
                Intrinsics.checkNotNullExpressionValue(fromString, "randomUUID()");
                date = new Date();
            } else {
                fromString = UUID.fromString(manifest.getReleaseId());
                Intrinsics.checkNotNullExpressionValue(fromString, "fromString(manifest.getReleaseId())");
                try {
                    date = UpdatesUtils.INSTANCE.parseDateString(manifest.getCommitTime());
                } catch (ParseException e) {
                    Log.e(LegacyUpdateManifest.TAG, "Could not parse commitTime", e);
                    date = new Date();
                }
            }
            UUID r5 = fromString;
            Date date2 = date;
            String runtimeVersion = manifest.getRuntimeVersion();
            if (runtimeVersion == null && (runtimeVersion = manifest.getSDKVersion()) == null) {
                throw new Exception("sdkVersion should not be null");
            }
            String str = runtimeVersion;
            Uri bundleUrl = Uri.parse(manifest.getBundleURL());
            JSONArray bundledAssets = manifest.getBundledAssets();
            Uri updateUrl = configuration.getUpdateUrl();
            Intrinsics.checkNotNull(updateUrl);
            String scopeKey = configuration.getScopeKey();
            Intrinsics.checkNotNull(scopeKey);
            Intrinsics.checkNotNullExpressionValue(bundleUrl, "bundleUrl");
            return new LegacyUpdateManifest(manifest, updateUrl, r5, scopeKey, date2, str, bundleUrl, bundledAssets, null);
        }

        public final Uri getAssetsUrlBase$expo_updates_release(Uri manifestUrl, LegacyManifest legacyManifest) {
            Uri build;
            Intrinsics.checkNotNullParameter(manifestUrl, "manifestUrl");
            Intrinsics.checkNotNullParameter(legacyManifest, "legacyManifest");
            String host = manifestUrl.getHost();
            if (host != null) {
                String[] strArr = LegacyUpdateManifest.EXPO_DOMAINS;
                int length = strArr.length;
                int r5 = 0;
                while (r5 < length) {
                    String str = strArr[r5];
                    r5++;
                    if (!Intrinsics.areEqual(host, str)) {
                        if (StringsKt.endsWith$default(host, "." + str, false, 2, (Object) null)) {
                        }
                    }
                    Uri parse = Uri.parse(LegacyUpdateManifest.EXPO_ASSETS_URL_BASE);
                    Intrinsics.checkNotNullExpressionValue(parse, "parse(EXPO_ASSETS_URL_BASE)");
                    return parse;
                }
                String assetUrlOverride = legacyManifest.getAssetUrlOverride();
                if (assetUrlOverride == null) {
                    assetUrlOverride = "assets";
                }
                try {
                    build = Uri.parse(new URI(manifestUrl.toString()).resolve(new URI(assetUrlOverride)).toString());
                } catch (Exception e) {
                    Log.e(LegacyUpdateManifest.TAG, "Failed to parse assetUrlOverride, falling back to default asset path", e);
                    build = manifestUrl.buildUpon().appendPath("assets").build();
                }
                Intrinsics.checkNotNullExpressionValue(build, "{\n        for (expoDomai…build()\n        }\n      }");
                return build;
            }
            Uri parse2 = Uri.parse(LegacyUpdateManifest.EXPO_ASSETS_URL_BASE);
            Intrinsics.checkNotNullExpressionValue(parse2, "{\n        Uri.parse(EXPO_ASSETS_URL_BASE)\n      }");
            return parse2;
        }
    }
}
