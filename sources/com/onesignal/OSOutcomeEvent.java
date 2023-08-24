package com.onesignal;

import com.onesignal.influence.domain.OSInfluenceType;
import com.onesignal.outcomes.domain.OSOutcomeEventParams;
import com.onesignal.outcomes.domain.OSOutcomeSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSOutcomeEvent {
    private static final String NOTIFICATION_IDS = "notification_ids";
    private static final String OUTCOME_ID = "id";
    private static final String SESSION = "session";
    private static final String TIMESTAMP = "timestamp";
    private static final String WEIGHT = "weight";
    private String name;
    private JSONArray notificationIds;
    private OSInfluenceType session;
    private long timestamp;
    private Float weight;

    public OSOutcomeEvent(OSInfluenceType oSInfluenceType, JSONArray jSONArray, String str, long j, float f) {
        this.session = oSInfluenceType;
        this.notificationIds = jSONArray;
        this.name = str;
        this.timestamp = j;
        this.weight = Float.valueOf(f);
    }

    public static OSOutcomeEvent fromOutcomeEventParamsV2toOutcomeEventV1(OSOutcomeEventParams oSOutcomeEventParams) {
        JSONArray jSONArray;
        OSInfluenceType oSInfluenceType = OSInfluenceType.UNATTRIBUTED;
        if (oSOutcomeEventParams.getOutcomeSource() != null) {
            OSOutcomeSource outcomeSource = oSOutcomeEventParams.getOutcomeSource();
            if (outcomeSource.getDirectBody() != null && outcomeSource.getDirectBody().getNotificationIds() != null && outcomeSource.getDirectBody().getNotificationIds().length() > 0) {
                oSInfluenceType = OSInfluenceType.DIRECT;
                jSONArray = outcomeSource.getDirectBody().getNotificationIds();
            } else if (outcomeSource.getIndirectBody() != null && outcomeSource.getIndirectBody().getNotificationIds() != null && outcomeSource.getIndirectBody().getNotificationIds().length() > 0) {
                oSInfluenceType = OSInfluenceType.INDIRECT;
                jSONArray = outcomeSource.getIndirectBody().getNotificationIds();
            }
            return new OSOutcomeEvent(oSInfluenceType, jSONArray, oSOutcomeEventParams.getOutcomeId(), oSOutcomeEventParams.getTimestamp(), oSOutcomeEventParams.getWeight());
        }
        jSONArray = null;
        return new OSOutcomeEvent(oSInfluenceType, jSONArray, oSOutcomeEventParams.getOutcomeId(), oSOutcomeEventParams.getTimestamp(), oSOutcomeEventParams.getWeight());
    }

    public OSInfluenceType getSession() {
        return this.session;
    }

    public JSONArray getNotificationIds() {
        return this.notificationIds;
    }

    public String getName() {
        return this.name;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public float getWeight() {
        return this.weight.floatValue();
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("session", this.session);
        jSONObject.put("notification_ids", this.notificationIds);
        jSONObject.put("id", this.name);
        jSONObject.put("timestamp", this.timestamp);
        jSONObject.put("weight", this.weight);
        return jSONObject;
    }

    public JSONObject toJSONObjectForMeasure() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = this.notificationIds;
        if (jSONArray != null && jSONArray.length() > 0) {
            jSONObject.put("notification_ids", this.notificationIds);
        }
        jSONObject.put("id", this.name);
        if (this.weight.floatValue() > 0.0f) {
            jSONObject.put("weight", this.weight);
        }
        long j = this.timestamp;
        if (j > 0) {
            jSONObject.put("timestamp", j);
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OSOutcomeEvent oSOutcomeEvent = (OSOutcomeEvent) obj;
        return this.session.equals(oSOutcomeEvent.session) && this.notificationIds.equals(oSOutcomeEvent.notificationIds) && this.name.equals(oSOutcomeEvent.name) && this.timestamp == oSOutcomeEvent.timestamp && this.weight.equals(oSOutcomeEvent.weight);
    }

    public int hashCode() {
        int r4 = 1;
        Object[] objArr = {this.session, this.notificationIds, this.name, Long.valueOf(this.timestamp), this.weight};
        for (int r2 = 0; r2 < 5; r2++) {
            Object obj = objArr[r2];
            r4 = (r4 * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return r4;
    }

    public String toString() {
        return "OutcomeEvent{session=" + this.session + ", notificationIds=" + this.notificationIds + ", name='" + this.name + "', timestamp=" + this.timestamp + ", weight=" + this.weight + '}';
    }
}
