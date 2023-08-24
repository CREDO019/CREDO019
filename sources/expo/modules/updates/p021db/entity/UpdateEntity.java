package expo.modules.updates.p021db.entity;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.p021db.enums.UpdateStatus;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UpdateEntity.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tR\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000b\"\u0004\b \u0010\rR\"\u0010!\u001a\u0004\u0018\u00010\"8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010(\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b)\u0010*R \u0010+\u001a\u0004\u0018\u00010,8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010*\"\u0004\b2\u00103R\u001e\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010*\"\u0004\b5\u00103R\u001a\u00106\u001a\u000207X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010<\u001a\u00020\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\u0011\"\u0004\b>\u0010\u0013¨\u0006?"}, m183d2 = {"Lexpo/modules/updates/db/entity/UpdateEntity;", "", "id", "Ljava/util/UUID;", "commitTime", "Ljava/util/Date;", UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, "", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "(Ljava/util/UUID;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;)V", "getCommitTime", "()Ljava/util/Date;", "setCommitTime", "(Ljava/util/Date;)V", "failedLaunchCount", "", "getFailedLaunchCount", "()I", "setFailedLaunchCount", "(I)V", "getId", "()Ljava/util/UUID;", "setId", "(Ljava/util/UUID;)V", "keep", "", "getKeep", "()Z", "setKeep", "(Z)V", "lastAccessed", "getLastAccessed", "setLastAccessed", "launchAssetId", "", "getLaunchAssetId", "()Ljava/lang/Long;", "setLaunchAssetId", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "loggingId", "getLoggingId", "()Ljava/lang/String;", "manifest", "Lorg/json/JSONObject;", "getManifest", "()Lorg/json/JSONObject;", "setManifest", "(Lorg/json/JSONObject;)V", "getRuntimeVersion", "setRuntimeVersion", "(Ljava/lang/String;)V", "getScopeKey", "setScopeKey", "status", "Lexpo/modules/updates/db/enums/UpdateStatus;", "getStatus", "()Lexpo/modules/updates/db/enums/UpdateStatus;", "setStatus", "(Lexpo/modules/updates/db/enums/UpdateStatus;)V", "successfulLaunchCount", "getSuccessfulLaunchCount", "setSuccessfulLaunchCount", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.entity.UpdateEntity */
/* loaded from: classes5.dex */
public final class UpdateEntity {
    private Date commitTime;
    private int failedLaunchCount;

    /* renamed from: id */
    private UUID f1473id;
    private boolean keep;
    private Date lastAccessed;
    private Long launchAssetId;
    private JSONObject manifest;
    private String runtimeVersion;
    private String scopeKey;
    private UpdateStatus status;
    private int successfulLaunchCount;

    public UpdateEntity(UUID id, Date commitTime, String runtimeVersion, String scopeKey) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(commitTime, "commitTime");
        Intrinsics.checkNotNullParameter(runtimeVersion, "runtimeVersion");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        this.f1473id = id;
        this.commitTime = commitTime;
        this.runtimeVersion = runtimeVersion;
        this.scopeKey = scopeKey;
        this.status = UpdateStatus.PENDING;
        this.lastAccessed = new Date();
    }

    public final UUID getId() {
        return this.f1473id;
    }

    public final void setId(UUID r2) {
        Intrinsics.checkNotNullParameter(r2, "<set-?>");
        this.f1473id = r2;
    }

    public final Date getCommitTime() {
        return this.commitTime;
    }

    public final void setCommitTime(Date date) {
        Intrinsics.checkNotNullParameter(date, "<set-?>");
        this.commitTime = date;
    }

    public final String getRuntimeVersion() {
        return this.runtimeVersion;
    }

    public final void setRuntimeVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.runtimeVersion = str;
    }

    public final String getScopeKey() {
        return this.scopeKey;
    }

    public final void setScopeKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.scopeKey = str;
    }

    public final Long getLaunchAssetId() {
        return this.launchAssetId;
    }

    public final void setLaunchAssetId(Long l) {
        this.launchAssetId = l;
    }

    public final JSONObject getManifest() {
        return this.manifest;
    }

    public final void setManifest(JSONObject jSONObject) {
        this.manifest = jSONObject;
    }

    public final UpdateStatus getStatus() {
        return this.status;
    }

    public final void setStatus(UpdateStatus updateStatus) {
        Intrinsics.checkNotNullParameter(updateStatus, "<set-?>");
        this.status = updateStatus;
    }

    public final boolean getKeep() {
        return this.keep;
    }

    public final void setKeep(boolean z) {
        this.keep = z;
    }

    public final String getLoggingId() {
        String str = this.f1473id.toString();
        Intrinsics.checkNotNullExpressionValue(str, "id.toString()");
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    public final Date getLastAccessed() {
        return this.lastAccessed;
    }

    public final void setLastAccessed(Date date) {
        Intrinsics.checkNotNullParameter(date, "<set-?>");
        this.lastAccessed = date;
    }

    public final int getSuccessfulLaunchCount() {
        return this.successfulLaunchCount;
    }

    public final void setSuccessfulLaunchCount(int r1) {
        this.successfulLaunchCount = r1;
    }

    public final int getFailedLaunchCount() {
        return this.failedLaunchCount;
    }

    public final void setFailedLaunchCount(int r1) {
        this.failedLaunchCount = r1;
    }
}
