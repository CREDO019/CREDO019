package expo.modules.updates.logging;

import com.onesignal.OneSignalDbContract;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UpdatesLogEntry.kt */
@Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010\u0017\u001a\u00020\u0005J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bHÆ\u0003J[\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bHÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0019\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006'"}, m183d2 = {"Lexpo/modules/updates/logging/UpdatesLogEntry;", "", "timestamp", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "", "code", "level", "updateId", "assetId", "stacktrace", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAssetId", "()Ljava/lang/String;", "getCode", "getLevel", "getMessage", "getStacktrace", "()Ljava/util/List;", "getTimestamp", "()J", "getUpdateId", "asString", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesLogEntry {
    public static final Companion Companion = new Companion(null);
    private final String assetId;
    private final String code;
    private final String level;
    private final String message;
    private final List<String> stacktrace;
    private final long timestamp;
    private final String updateId;

    public final long component1() {
        return this.timestamp;
    }

    public final String component2() {
        return this.message;
    }

    public final String component3() {
        return this.code;
    }

    public final String component4() {
        return this.level;
    }

    public final String component5() {
        return this.updateId;
    }

    public final String component6() {
        return this.assetId;
    }

    public final List<String> component7() {
        return this.stacktrace;
    }

    public final UpdatesLogEntry copy(long j, String message, String code, String level, String str, String str2, List<String> list) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(level, "level");
        return new UpdatesLogEntry(j, message, code, level, str, str2, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UpdatesLogEntry) {
            UpdatesLogEntry updatesLogEntry = (UpdatesLogEntry) obj;
            return this.timestamp == updatesLogEntry.timestamp && Intrinsics.areEqual(this.message, updatesLogEntry.message) && Intrinsics.areEqual(this.code, updatesLogEntry.code) && Intrinsics.areEqual(this.level, updatesLogEntry.level) && Intrinsics.areEqual(this.updateId, updatesLogEntry.updateId) && Intrinsics.areEqual(this.assetId, updatesLogEntry.assetId) && Intrinsics.areEqual(this.stacktrace, updatesLogEntry.stacktrace);
        }
        return false;
    }

    public int hashCode() {
        int m205m = ((((((UpdatesLogEntry$$ExternalSyntheticBackport0.m205m(this.timestamp) * 31) + this.message.hashCode()) * 31) + this.code.hashCode()) * 31) + this.level.hashCode()) * 31;
        String str = this.updateId;
        int hashCode = (m205m + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.assetId;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<String> list = this.stacktrace;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        long j = this.timestamp;
        String str = this.message;
        String str2 = this.code;
        String str3 = this.level;
        String str4 = this.updateId;
        String str5 = this.assetId;
        List<String> list = this.stacktrace;
        return "UpdatesLogEntry(timestamp=" + j + ", message=" + str + ", code=" + str2 + ", level=" + str3 + ", updateId=" + str4 + ", assetId=" + str5 + ", stacktrace=" + list + ")";
    }

    public UpdatesLogEntry(long j, String message, String code, String level, String str, String str2, List<String> list) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(level, "level");
        this.timestamp = j;
        this.message = message;
        this.code = code;
        this.level = level;
        this.updateId = str;
        this.assetId = str2;
        this.stacktrace = list;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getCode() {
        return this.code;
    }

    public final String getLevel() {
        return this.level;
    }

    public final String getUpdateId() {
        return this.updateId;
    }

    public final String getAssetId() {
        return this.assetId;
    }

    public final List<String> getStacktrace() {
        return this.stacktrace;
    }

    public final String asString() {
        JSONObject jSONObject = new JSONObject(MapsKt.mapOf(TuplesKt.m176to("timestamp", Long.valueOf(this.timestamp)), TuplesKt.m176to(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, this.message), TuplesKt.m176to("code", this.code), TuplesKt.m176to("level", this.level)));
        if (getUpdateId() != null) {
            jSONObject.put("updateId", getUpdateId());
        }
        if (getAssetId() != null) {
            jSONObject.put("assetId", getAssetId());
        }
        if (getStacktrace() != null && (!getStacktrace().isEmpty())) {
            jSONObject.put("stacktrace", new JSONArray((Collection) getStacktrace()));
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "JSONObject(\n      mapOf(…\n      }\n    }.toString()");
        return jSONObject2;
    }

    /* compiled from: UpdatesLogEntry.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/logging/UpdatesLogEntry$Companion;", "", "()V", "create", "Lexpo/modules/updates/logging/UpdatesLogEntry;", "json", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UpdatesLogEntry create(String json) {
            Long l;
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            JSONArray jSONArray;
            ArrayList arrayList;
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                JSONObject jSONObject = new JSONObject(json);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Long.class);
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                    String string = jSONObject.getString("timestamp");
                    if (string == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) string;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    l = (Long) Double.valueOf(jSONObject.getDouble("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    l = (Long) Integer.valueOf(jSONObject.getInt("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    l = Long.valueOf(jSONObject.getLong("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    l = (Long) Boolean.valueOf(jSONObject.getBoolean("timestamp"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("timestamp");
                    if (jSONArray2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) jSONArray2;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("timestamp");
                    if (jSONObject2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) jSONObject2;
                } else {
                    Object obj = jSONObject.get("timestamp");
                    if (obj == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
                    }
                    l = (Long) obj;
                }
                long longValue = l.longValue();
                KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                    str = jSONObject.getString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
                    if (str == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str = (String) Double.valueOf(jSONObject.getDouble(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str = (String) Integer.valueOf(jSONObject.getInt(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str = (String) Long.valueOf(jSONObject.getLong(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(jSONObject.getBoolean(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray3 = jSONObject.getJSONArray(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
                    if (jSONArray3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str = (String) jSONArray3;
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = jSONObject.getJSONObject(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
                    if (jSONObject3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str = (String) jSONObject3;
                } else {
                    Object obj2 = jSONObject.get(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
                    if (obj2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str = (String) obj2;
                }
                String str8 = str;
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                    str2 = jSONObject.getString("code");
                    if (str2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str2 = (String) Double.valueOf(jSONObject.getDouble("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str2 = (String) Integer.valueOf(jSONObject.getInt("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str2 = (String) Long.valueOf(jSONObject.getLong("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str2 = (String) Boolean.valueOf(jSONObject.getBoolean("code"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray4 = jSONObject.getJSONArray("code");
                    if (jSONArray4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str2 = (String) jSONArray4;
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject4 = jSONObject.getJSONObject("code");
                    if (jSONObject4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str2 = (String) jSONObject4;
                } else {
                    Object obj3 = jSONObject.get("code");
                    if (obj3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str2 = (String) obj3;
                }
                KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(String.class))) {
                    str3 = jSONObject.getString("level");
                    if (str3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str3 = (String) Double.valueOf(jSONObject.getDouble("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str3 = (String) Integer.valueOf(jSONObject.getInt("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str3 = (String) Long.valueOf(jSONObject.getLong("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str3 = (String) Boolean.valueOf(jSONObject.getBoolean("level"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray5 = jSONObject.getJSONArray("level");
                    if (jSONArray5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str3 = (String) jSONArray5;
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject5 = jSONObject.getJSONObject("level");
                    if (jSONObject5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str3 = (String) jSONObject5;
                } else {
                    Object obj4 = jSONObject.get("level");
                    if (obj4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    str3 = (String) obj4;
                }
                String str9 = str3;
                if (jSONObject.has("updateId")) {
                    KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(String.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(String.class))) {
                        str4 = jSONObject.getString("updateId");
                        if (str4 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        str4 = (String) Double.valueOf(jSONObject.getDouble("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        str4 = (String) Integer.valueOf(jSONObject.getInt("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        str4 = (String) Long.valueOf(jSONObject.getLong("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        str4 = (String) Boolean.valueOf(jSONObject.getBoolean("updateId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        JSONArray jSONArray6 = jSONObject.getJSONArray("updateId");
                        if (jSONArray6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str4 = (String) jSONArray6;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        JSONObject jSONObject6 = jSONObject.getJSONObject("updateId");
                        if (jSONObject6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str4 = (String) jSONObject6;
                    } else {
                        Object obj5 = jSONObject.get("updateId");
                        if (obj5 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str4 = (String) obj5;
                    }
                    str5 = str4;
                } else {
                    str5 = null;
                }
                if (jSONObject.has("assetId")) {
                    KClass orCreateKotlinClass6 = Reflection.getOrCreateKotlinClass(String.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(String.class))) {
                        str6 = jSONObject.getString("assetId");
                        if (str6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        str6 = (String) Double.valueOf(jSONObject.getDouble("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        str6 = (String) Integer.valueOf(jSONObject.getInt("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        str6 = (String) Long.valueOf(jSONObject.getLong("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        str6 = (String) Boolean.valueOf(jSONObject.getBoolean("assetId"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        JSONArray jSONArray7 = jSONObject.getJSONArray("assetId");
                        if (jSONArray7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str6 = (String) jSONArray7;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass6, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        JSONObject jSONObject7 = jSONObject.getJSONObject("assetId");
                        if (jSONObject7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str6 = (String) jSONObject7;
                    } else {
                        Object obj6 = jSONObject.get("assetId");
                        if (obj6 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                        }
                        str6 = (String) obj6;
                    }
                    str7 = str6;
                } else {
                    str7 = null;
                }
                if (jSONObject.has("stacktrace")) {
                    KClass orCreateKotlinClass7 = Reflection.getOrCreateKotlinClass(JSONArray.class);
                    if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(String.class))) {
                        String string2 = jSONObject.getString("stacktrace");
                        if (string2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                        jSONArray = (JSONArray) string2;
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                        jSONArray = (JSONArray) Double.valueOf(jSONObject.getDouble("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                        jSONArray = (JSONArray) Integer.valueOf(jSONObject.getInt("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                        jSONArray = (JSONArray) Long.valueOf(jSONObject.getLong("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                        jSONArray = (JSONArray) Boolean.valueOf(jSONObject.getBoolean("stacktrace"));
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                        jSONArray = jSONObject.getJSONArray("stacktrace");
                        if (jSONArray == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                    } else if (Intrinsics.areEqual(orCreateKotlinClass7, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                        JSONObject jSONObject8 = jSONObject.getJSONObject("stacktrace");
                        if (jSONObject8 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                        jSONArray = (JSONArray) jSONObject8;
                    } else {
                        Object obj7 = jSONObject.get("stacktrace");
                        if (obj7 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                        }
                        jSONArray = (JSONArray) obj7;
                    }
                } else {
                    jSONArray = null;
                }
                if (jSONArray == null) {
                    arrayList = null;
                } else {
                    int length = jSONArray.length();
                    ArrayList arrayList2 = new ArrayList(length);
                    for (int r10 = 0; r10 < length; r10++) {
                        arrayList2.add(jSONArray.getString(r10));
                    }
                    arrayList = arrayList2;
                }
                return new UpdatesLogEntry(longValue, str8, str2, str9, str5, str7, arrayList);
            } catch (JSONException unused) {
                return null;
            }
        }
    }
}
