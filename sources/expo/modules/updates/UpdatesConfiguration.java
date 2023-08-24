package expo.modules.updates;

import android.net.Uri;
import expo.modules.updates.codesigning.CodeSigningConfiguration;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: UpdatesConfiguration.kt */
@Metadata(m184d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0016\u0018\u0000 82\u00020\u0001:\u000278B'\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007B£\u0001\b\u0002\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\t\u0012\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\u0006\u0010\u0019\u001a\u00020\t\u0012\u0006\u0010\u001a\u001a\u00020\t¢\u0006\u0002\u0010\u001bR\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u001d\u0010\"\u001a\u0004\u0018\u00010#8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u0011\u0010\u0019\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u001f\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u001fR\u0011\u0010-\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b-\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0010\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u0010!R\u001d\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010*R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b2\u0010!R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b3\u0010!R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b4\u0010!R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u00069"}, m183d2 = {"Lexpo/modules/updates/UpdatesConfiguration;", "", "context", "Landroid/content/Context;", "overrideMap", "", "", "(Landroid/content/Context;Ljava/util/Map;)V", "isEnabled", "", UpdatesConfiguration.UPDATES_CONFIGURATION_EXPECTS_EXPO_SIGNED_MANIFEST, UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY, "Landroid/net/Uri;", UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_KEY, "", UpdatesConfiguration.UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY, "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", UpdatesConfiguration.UPDATES_CONFIGURATION_HAS_EMBEDDED_UPDATE_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_METADATA, UpdatesConfiguration.f1470xa2fbbc06, UpdatesConfiguration.UPDATES_CONFIGURATION_CODE_SIGNING_ALLOW_UNSIGNED_MANIFESTS, "(ZZLjava/lang/String;Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;ZLjava/util/Map;Ljava/lang/String;Ljava/util/Map;ZZ)V", "getCheckOnLaunch", "()Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getCodeSigningAllowUnsignedManifests", "()Z", "getCodeSigningCertificate", "()Ljava/lang/String;", "codeSigningConfiguration", "Lexpo/modules/updates/codesigning/CodeSigningConfiguration;", "getCodeSigningConfiguration", "()Lexpo/modules/updates/codesigning/CodeSigningConfiguration;", "codeSigningConfiguration$delegate", "Lkotlin/Lazy;", "getCodeSigningIncludeManifestResponseCertificateChain", "getCodeSigningMetadata", "()Ljava/util/Map;", "getExpectsSignedManifest", "getHasEmbeddedUpdate", "isMissingRuntimeVersion", "getLaunchWaitMs", "()I", "getReleaseChannel", "getRequestHeaders", "getRuntimeVersion", "getScopeKey", "getSdkVersion", "getUpdateUrl", "()Landroid/net/Uri;", "CheckAutomaticallyConfiguration", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesConfiguration {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdatesConfiguration";
    public static final String UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY = "checkOnLaunch";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_ALLOW_UNSIGNED_MANIFESTS = "codeSigningAllowUnsignedManifests";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE = "codeSigningCertificate";

    /* renamed from: UPDATES_CONFIGURATION_CODE_SIGNING_INCLUDE_MANIFEST_RESPONSE_CERTIFICATE_CHAIN */
    public static final String f1470xa2fbbc06 = "codeSigningIncludeManifestResponseCertificateChain";
    public static final String UPDATES_CONFIGURATION_CODE_SIGNING_METADATA = "codeSigningMetadata";
    public static final String UPDATES_CONFIGURATION_ENABLED_KEY = "enabled";
    public static final String UPDATES_CONFIGURATION_EXPECTS_EXPO_SIGNED_MANIFEST = "expectsSignedManifest";
    public static final String UPDATES_CONFIGURATION_HAS_EMBEDDED_UPDATE_KEY = "hasEmbeddedUpdate";
    private static final int UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_DEFAULT_VALUE = 0;
    public static final String UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_KEY = "launchWaitMs";
    private static final String UPDATES_CONFIGURATION_RELEASE_CHANNEL_DEFAULT_VALUE = "default";
    public static final String UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY = "releaseChannel";
    public static final String UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY = "requestHeaders";
    public static final String UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY = "runtimeVersion";
    public static final String UPDATES_CONFIGURATION_SCOPE_KEY_KEY = "scopeKey";
    public static final String UPDATES_CONFIGURATION_SDK_VERSION_KEY = "sdkVersion";
    public static final String UPDATES_CONFIGURATION_UPDATE_URL_KEY = "updateUrl";
    private final CheckAutomaticallyConfiguration checkOnLaunch;
    private final boolean codeSigningAllowUnsignedManifests;
    private final String codeSigningCertificate;
    private final Lazy codeSigningConfiguration$delegate;
    private final boolean codeSigningIncludeManifestResponseCertificateChain;
    private final Map<String, String> codeSigningMetadata;
    private final boolean expectsSignedManifest;
    private final boolean hasEmbeddedUpdate;
    private final boolean isEnabled;
    private final int launchWaitMs;
    private final String releaseChannel;
    private final Map<String, String> requestHeaders;
    private final String runtimeVersion;
    private final String scopeKey;
    private final String sdkVersion;
    private final Uri updateUrl;

    /* compiled from: UpdatesConfiguration.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "", "(Ljava/lang/String;I)V", "NEVER", "ERROR_RECOVERY_ONLY", "WIFI_ONLY", "ALWAYS", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public enum CheckAutomaticallyConfiguration {
        NEVER,
        ERROR_RECOVERY_ONLY,
        WIFI_ONLY,
        ALWAYS
    }

    private UpdatesConfiguration(boolean z, boolean z2, String str, Uri uri, String str2, String str3, String str4, int r8, CheckAutomaticallyConfiguration checkAutomaticallyConfiguration, boolean z3, Map<String, String> map, String str5, Map<String, String> map2, boolean z4, boolean z5) {
        this.isEnabled = z;
        this.expectsSignedManifest = z2;
        this.scopeKey = str;
        this.updateUrl = uri;
        this.sdkVersion = str2;
        this.runtimeVersion = str3;
        this.releaseChannel = str4;
        this.launchWaitMs = r8;
        this.checkOnLaunch = checkAutomaticallyConfiguration;
        this.hasEmbeddedUpdate = z3;
        this.requestHeaders = map;
        this.codeSigningCertificate = str5;
        this.codeSigningMetadata = map2;
        this.codeSigningIncludeManifestResponseCertificateChain = z4;
        this.codeSigningAllowUnsignedManifests = z5;
        this.codeSigningConfiguration$delegate = LazyKt.lazy(new Functions<CodeSigningConfiguration>() { // from class: expo.modules.updates.UpdatesConfiguration$codeSigningConfiguration$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final CodeSigningConfiguration invoke() {
                String codeSigningCertificate = UpdatesConfiguration.this.getCodeSigningCertificate();
                if (codeSigningCertificate == null) {
                    return null;
                }
                UpdatesConfiguration updatesConfiguration = UpdatesConfiguration.this;
                return new CodeSigningConfiguration(codeSigningCertificate, updatesConfiguration.getCodeSigningMetadata(), updatesConfiguration.getCodeSigningIncludeManifestResponseCertificateChain(), updatesConfiguration.getCodeSigningAllowUnsignedManifests());
            }
        });
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final boolean getExpectsSignedManifest() {
        return this.expectsSignedManifest;
    }

    public final String getScopeKey() {
        return this.scopeKey;
    }

    public final Uri getUpdateUrl() {
        return this.updateUrl;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public final String getRuntimeVersion() {
        return this.runtimeVersion;
    }

    public final String getReleaseChannel() {
        return this.releaseChannel;
    }

    public final int getLaunchWaitMs() {
        return this.launchWaitMs;
    }

    public final CheckAutomaticallyConfiguration getCheckOnLaunch() {
        return this.checkOnLaunch;
    }

    public final boolean getHasEmbeddedUpdate() {
        return this.hasEmbeddedUpdate;
    }

    public final Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public final String getCodeSigningCertificate() {
        return this.codeSigningCertificate;
    }

    public final Map<String, String> getCodeSigningMetadata() {
        return this.codeSigningMetadata;
    }

    public final boolean getCodeSigningIncludeManifestResponseCertificateChain() {
        return this.codeSigningIncludeManifestResponseCertificateChain;
    }

    public final boolean getCodeSigningAllowUnsignedManifests() {
        return this.codeSigningAllowUnsignedManifests;
    }

    public final boolean isMissingRuntimeVersion() {
        String str = this.runtimeVersion;
        if (str != null) {
            if (!(str.length() == 0)) {
                return false;
            }
        }
        String str2 = this.sdkVersion;
        if (str2 != null) {
            if (!(str2.length() == 0)) {
                return false;
            }
        }
        return true;
    }

    public final CodeSigningConfiguration getCodeSigningConfiguration() {
        return (CodeSigningConfiguration) this.codeSigningConfiguration$delegate.getValue();
    }

    /* compiled from: UpdatesConfiguration.kt */
    @Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/updates/UpdatesConfiguration$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "UPDATES_CONFIGURATION_CHECK_ON_LAUNCH_KEY", "UPDATES_CONFIGURATION_CODE_SIGNING_ALLOW_UNSIGNED_MANIFESTS", "UPDATES_CONFIGURATION_CODE_SIGNING_CERTIFICATE", "UPDATES_CONFIGURATION_CODE_SIGNING_INCLUDE_MANIFEST_RESPONSE_CERTIFICATE_CHAIN", "UPDATES_CONFIGURATION_CODE_SIGNING_METADATA", "UPDATES_CONFIGURATION_ENABLED_KEY", "UPDATES_CONFIGURATION_EXPECTS_EXPO_SIGNED_MANIFEST", "UPDATES_CONFIGURATION_HAS_EMBEDDED_UPDATE_KEY", "UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_DEFAULT_VALUE", "", "UPDATES_CONFIGURATION_LAUNCH_WAIT_MS_KEY", "UPDATES_CONFIGURATION_RELEASE_CHANNEL_DEFAULT_VALUE", "UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY", "UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY", "UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY", "UPDATES_CONFIGURATION_SCOPE_KEY_KEY", "UPDATES_CONFIGURATION_SDK_VERSION_KEY", "UPDATES_CONFIGURATION_UPDATE_URL_KEY", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0089, code lost:
        if (r5 == null) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:379:0x0761, code lost:
        if (r2 == null) goto L184;
     */
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x05d5  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x06d6  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x06f3  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x076b  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0770  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0788  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x07fe  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0806  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0808  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x081e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0894  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public UpdatesConfiguration(android.content.Context r25, java.util.Map<java.lang.String, ? extends java.lang.Object> r26) {
        /*
            Method dump skipped, instructions count: 2893
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.UpdatesConfiguration.<init>(android.content.Context, java.util.Map):void");
    }
}
