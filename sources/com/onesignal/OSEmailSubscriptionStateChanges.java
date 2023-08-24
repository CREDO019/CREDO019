package com.onesignal;

import com.google.firebase.messaging.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSEmailSubscriptionStateChanges {
    private OSEmailSubscriptionState from;

    /* renamed from: to */
    private OSEmailSubscriptionState f1298to;

    public OSEmailSubscriptionStateChanges(OSEmailSubscriptionState oSEmailSubscriptionState, OSEmailSubscriptionState oSEmailSubscriptionState2) {
        this.from = oSEmailSubscriptionState;
        this.f1298to = oSEmailSubscriptionState2;
    }

    public OSEmailSubscriptionState getTo() {
        return this.f1298to;
    }

    public OSEmailSubscriptionState getFrom() {
        return this.from;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.MessagePayloadKeys.FROM, this.from.toJSONObject());
            jSONObject.put("to", this.f1298to.toJSONObject());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
