package com.amplitude.analytics.connector;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdentityStore.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006HÆ\u0003J9\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u001f\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, m183d2 = {"Lcom/amplitude/analytics/connector/Identity;", "", "userId", "", "deviceId", "userProperties", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getDeviceId", "()Ljava/lang/String;", "getUserId", "getUserProperties", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "analytics-connector_release"}, m182k = 1, m181mv = {1, 5, 1}, m179xi = 48)
/* loaded from: classes.dex */
public final class Identity {
    private final String deviceId;
    private final String userId;
    private final Map<String, Object> userProperties;

    public Identity() {
        this(null, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Identity copy$default(Identity identity, String str, String str2, Map map, int r4, Object obj) {
        if ((r4 & 1) != 0) {
            str = identity.userId;
        }
        if ((r4 & 2) != 0) {
            str2 = identity.deviceId;
        }
        if ((r4 & 4) != 0) {
            map = identity.userProperties;
        }
        return identity.copy(str, str2, map);
    }

    public final String component1() {
        return this.userId;
    }

    public final String component2() {
        return this.deviceId;
    }

    public final Map<String, Object> component3() {
        return this.userProperties;
    }

    public final Identity copy(String str, String str2, Map<String, ? extends Object> userProperties) {
        Intrinsics.checkNotNullParameter(userProperties, "userProperties");
        return new Identity(str, str2, userProperties);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Identity) {
            Identity identity = (Identity) obj;
            return Intrinsics.areEqual(this.userId, identity.userId) && Intrinsics.areEqual(this.deviceId, identity.deviceId) && Intrinsics.areEqual(this.userProperties, identity.userProperties);
        }
        return false;
    }

    public int hashCode() {
        String str = this.userId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.deviceId;
        return ((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.userProperties.hashCode();
    }

    public String toString() {
        return "Identity(userId=" + ((Object) this.userId) + ", deviceId=" + ((Object) this.deviceId) + ", userProperties=" + this.userProperties + ')';
    }

    public Identity(String str, String str2, Map<String, ? extends Object> userProperties) {
        Intrinsics.checkNotNullParameter(userProperties, "userProperties");
        this.userId = str;
        this.deviceId = str2;
        this.userProperties = userProperties;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getDeviceId() {
        return this.deviceId;
    }

    public /* synthetic */ Identity(String str, String str2, Map map, int r5, DefaultConstructorMarker defaultConstructorMarker) {
        this((r5 & 1) != 0 ? null : str, (r5 & 2) != 0 ? null : str2, (r5 & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, Object> getUserProperties() {
        return this.userProperties;
    }
}