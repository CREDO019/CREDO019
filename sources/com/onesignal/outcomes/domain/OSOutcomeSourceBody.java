package com.onesignal.outcomes.domain;

import com.onesignal.outcomes.OSOutcomeConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: OSOutcomeSourceBody.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0010"}, m183d2 = {"Lcom/onesignal/outcomes/domain/OSOutcomeSourceBody;", "", "notificationIds", "Lorg/json/JSONArray;", "inAppMessagesIds", "(Lorg/json/JSONArray;Lorg/json/JSONArray;)V", "getInAppMessagesIds", "()Lorg/json/JSONArray;", "setInAppMessagesIds", "(Lorg/json/JSONArray;)V", "getNotificationIds", "setNotificationIds", "toJSONObject", "Lorg/json/JSONObject;", "toString", "", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class OSOutcomeSourceBody {
    private JSONArray inAppMessagesIds;
    private JSONArray notificationIds;

    public OSOutcomeSourceBody() {
        this(null, null, 3, null);
    }

    public OSOutcomeSourceBody(JSONArray jSONArray) {
        this(jSONArray, null, 2, null);
    }

    public OSOutcomeSourceBody(JSONArray jSONArray, JSONArray jSONArray2) {
        this.notificationIds = jSONArray;
        this.inAppMessagesIds = jSONArray2;
    }

    public /* synthetic */ OSOutcomeSourceBody(JSONArray jSONArray, JSONArray jSONArray2, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this((r3 & 1) != 0 ? new JSONArray() : jSONArray, (r3 & 2) != 0 ? new JSONArray() : jSONArray2);
    }

    public final JSONArray getInAppMessagesIds() {
        return this.inAppMessagesIds;
    }

    public final JSONArray getNotificationIds() {
        return this.notificationIds;
    }

    public final void setInAppMessagesIds(JSONArray jSONArray) {
        this.inAppMessagesIds = jSONArray;
    }

    public final void setNotificationIds(JSONArray jSONArray) {
        this.notificationIds = jSONArray;
    }

    public final JSONObject toJSONObject() throws JSONException {
        JSONObject put = new JSONObject().put("notification_ids", this.notificationIds).put(OSOutcomeConstants.IAM_IDS, this.inAppMessagesIds);
        Intrinsics.checkNotNullExpressionValue(put, "JSONObject()\n           …AM_IDS, inAppMessagesIds)");
        return put;
    }

    public String toString() {
        return "OSOutcomeSourceBody{notificationIds=" + this.notificationIds + ", inAppMessagesIds=" + this.inAppMessagesIds + '}';
    }
}
