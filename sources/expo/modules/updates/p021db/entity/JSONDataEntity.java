package expo.modules.updates.p021db.entity;

import expo.modules.updates.UpdatesConfiguration;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSONDataEntity.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012¨\u0006\u001b"}, m183d2 = {"Lexpo/modules/updates/db/entity/JSONDataEntity;", "", "key", "", "value", "lastUpdated", "Ljava/util/Date;", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;)V", "id", "", "getId", "()J", "setId", "(J)V", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "getLastUpdated", "()Ljava/util/Date;", "setLastUpdated", "(Ljava/util/Date;)V", "getScopeKey", "setScopeKey", "getValue", "setValue", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.entity.JSONDataEntity */
/* loaded from: classes5.dex */
public final class JSONDataEntity {

    /* renamed from: id */
    private long f1472id;
    private String key;
    private Date lastUpdated;
    private String scopeKey;
    private String value;

    public JSONDataEntity(String key, String value, Date lastUpdated, String scopeKey) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(lastUpdated, "lastUpdated");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        this.key = key;
        this.value = value;
        this.lastUpdated = lastUpdated;
        this.scopeKey = scopeKey;
    }

    public final String getKey() {
        return this.key;
    }

    public final void setKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.key = str;
    }

    public final String getValue() {
        return this.value;
    }

    public final void setValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }

    public final Date getLastUpdated() {
        return this.lastUpdated;
    }

    public final void setLastUpdated(Date date) {
        Intrinsics.checkNotNullParameter(date, "<set-?>");
        this.lastUpdated = date;
    }

    public final String getScopeKey() {
        return this.scopeKey;
    }

    public final void setScopeKey(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.scopeKey = str;
    }

    public final long getId() {
        return this.f1472id;
    }

    public final void setId(long j) {
        this.f1472id = j;
    }
}
