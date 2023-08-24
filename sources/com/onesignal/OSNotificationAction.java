package com.onesignal;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSNotificationAction {
    private final String actionId;
    private final ActionType type;

    /* loaded from: classes3.dex */
    public enum ActionType {
        Opened,
        ActionTaken
    }

    public OSNotificationAction(ActionType actionType, String str) {
        this.type = actionType;
        this.actionId = str;
    }

    public ActionType getType() {
        return this.type;
    }

    public String getActionId() {
        return this.actionId;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SessionDescription.ATTR_TYPE, this.type.ordinal());
            jSONObject.put(GenerateNotification.BUNDLE_KEY_ACTION_ID, this.actionId);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }
}
