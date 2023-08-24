package com.onesignal;

import androidx.core.p005os.EnvironmentCompat;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class OSTrigger {
    public OSTriggerKind kind;
    public OSTriggerOperator operatorType;
    public String property;
    String triggerId;
    public Object value;

    /* loaded from: classes3.dex */
    public enum OSTriggerOperator {
        GREATER_THAN("greater"),
        LESS_THAN("less"),
        EQUAL_TO("equal"),
        NOT_EQUAL_TO("not_equal"),
        LESS_THAN_OR_EQUAL_TO("less_or_equal"),
        GREATER_THAN_OR_EQUAL_TO("greater_or_equal"),
        EXISTS("exists"),
        NOT_EXISTS("not_exists"),
        CONTAINS("in");
        
        private String text;

        OSTriggerOperator(String str) {
            this.text = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.text;
        }

        public static OSTriggerOperator fromString(String str) {
            OSTriggerOperator[] values;
            for (OSTriggerOperator oSTriggerOperator : values()) {
                if (oSTriggerOperator.text.equalsIgnoreCase(str)) {
                    return oSTriggerOperator;
                }
            }
            return EQUAL_TO;
        }

        public boolean checksEquality() {
            return this == EQUAL_TO || this == NOT_EQUAL_TO;
        }
    }

    /* loaded from: classes3.dex */
    public enum OSTriggerKind {
        TIME_SINCE_LAST_IN_APP("min_time_since"),
        SESSION_TIME("session_time"),
        CUSTOM(OSNotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM),
        UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
        
        private String value;

        OSTriggerKind(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }

        public static OSTriggerKind fromString(String str) {
            OSTriggerKind[] values;
            for (OSTriggerKind oSTriggerKind : values()) {
                if (oSTriggerKind.value.equalsIgnoreCase(str)) {
                    return oSTriggerKind;
                }
            }
            return UNKNOWN;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSTrigger(JSONObject jSONObject) throws JSONException {
        this.triggerId = jSONObject.getString("id");
        this.kind = OSTriggerKind.fromString(jSONObject.getString("kind"));
        this.property = jSONObject.optString("property", null);
        this.operatorType = OSTriggerOperator.fromString(jSONObject.getString("operator"));
        this.value = jSONObject.opt("value");
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.triggerId);
            jSONObject.put("kind", this.kind);
            jSONObject.put("property", this.property);
            jSONObject.put("operator", this.operatorType.toString());
            jSONObject.put("value", this.value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return "OSTrigger{triggerId='" + this.triggerId + "', kind=" + this.kind + ", property='" + this.property + "', operatorType=" + this.operatorType + ", value=" + this.value + '}';
    }
}
