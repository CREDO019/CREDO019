package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSPermissionStateChanges {
    private OSPermissionState from;

    /* renamed from: to */
    private OSPermissionState f1302to;

    public OSPermissionStateChanges(OSPermissionState oSPermissionState, OSPermissionState oSPermissionState2) {
        this.from = oSPermissionState;
        this.f1302to = oSPermissionState2;
    }

    public OSPermissionState getTo() {
        return this.f1302to;
    }

    public OSPermissionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f1302to.toJSONObject());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
