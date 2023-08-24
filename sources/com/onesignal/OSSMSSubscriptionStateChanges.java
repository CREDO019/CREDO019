package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSSMSSubscriptionStateChanges {
    private OSSMSSubscriptionState from;

    /* renamed from: to */
    private OSSMSSubscriptionState f1303to;

    public OSSMSSubscriptionStateChanges(OSSMSSubscriptionState oSSMSSubscriptionState, OSSMSSubscriptionState oSSMSSubscriptionState2) {
        this.from = oSSMSSubscriptionState;
        this.f1303to = oSSMSSubscriptionState2;
    }

    public OSSMSSubscriptionState getTo() {
        return this.f1303to;
    }

    public OSSMSSubscriptionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f1303to.toJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
