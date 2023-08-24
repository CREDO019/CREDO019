package expo.modules.updates.codesigning;

import expo.modules.updates.UpdatesConfiguration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CertificateChain.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/updates/codesigning/ExpoProjectInformation;", "", "projectId", "", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "(Ljava/lang/String;Ljava/lang/String;)V", "getProjectId", "()Ljava/lang/String;", "getScopeKey", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ExpoProjectInformation {
    private final String projectId;
    private final String scopeKey;

    public static /* synthetic */ ExpoProjectInformation copy$default(ExpoProjectInformation expoProjectInformation, String str, String str2, int r3, Object obj) {
        if ((r3 & 1) != 0) {
            str = expoProjectInformation.projectId;
        }
        if ((r3 & 2) != 0) {
            str2 = expoProjectInformation.scopeKey;
        }
        return expoProjectInformation.copy(str, str2);
    }

    public final String component1() {
        return this.projectId;
    }

    public final String component2() {
        return this.scopeKey;
    }

    public final ExpoProjectInformation copy(String projectId, String scopeKey) {
        Intrinsics.checkNotNullParameter(projectId, "projectId");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        return new ExpoProjectInformation(projectId, scopeKey);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ExpoProjectInformation) {
            ExpoProjectInformation expoProjectInformation = (ExpoProjectInformation) obj;
            return Intrinsics.areEqual(this.projectId, expoProjectInformation.projectId) && Intrinsics.areEqual(this.scopeKey, expoProjectInformation.scopeKey);
        }
        return false;
    }

    public int hashCode() {
        return (this.projectId.hashCode() * 31) + this.scopeKey.hashCode();
    }

    public String toString() {
        String str = this.projectId;
        String str2 = this.scopeKey;
        return "ExpoProjectInformation(projectId=" + str + ", scopeKey=" + str2 + ")";
    }

    public ExpoProjectInformation(String projectId, String scopeKey) {
        Intrinsics.checkNotNullParameter(projectId, "projectId");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        this.projectId = projectId;
        this.scopeKey = scopeKey;
    }

    public final String getProjectId() {
        return this.projectId;
    }

    public final String getScopeKey() {
        return this.scopeKey;
    }
}
