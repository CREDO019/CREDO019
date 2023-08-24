package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSSubscriptionStateChanges {
    private OSSubscriptionState from;

    /* renamed from: to */
    private OSSubscriptionState f1304to;

    public OSSubscriptionStateChanges(OSSubscriptionState oSSubscriptionState, OSSubscriptionState oSSubscriptionState2) {
        this.from = oSSubscriptionState;
        this.f1304to = oSSubscriptionState2;
    }

    public OSSubscriptionState getTo() {
        return this.f1304to;
    }

    public OSSubscriptionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f1304to.toJSONObject());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
