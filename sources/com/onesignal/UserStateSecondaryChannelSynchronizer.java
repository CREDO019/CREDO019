package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalStateSynchronizer;
import com.onesignal.UserStateSynchronizer;
import com.onesignal.outcomes.OSOutcomeConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class UserStateSecondaryChannelSynchronizer extends UserStateSynchronizer {
    abstract void fireUpdateFailure();

    abstract void fireUpdateSuccess(JSONObject jSONObject);

    protected abstract String getAuthHashKey();

    protected abstract String getChannelKey();

    protected abstract int getDeviceType();

    @Override // com.onesignal.UserStateSynchronizer
    String getExternalId(boolean z) {
        return null;
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected abstract String getId();

    @Override // com.onesignal.UserStateSynchronizer
    boolean getSubscribed() {
        return false;
    }

    @Override // com.onesignal.UserStateSynchronizer
    UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        return null;
    }

    @Override // com.onesignal.UserStateSynchronizer
    public boolean getUserSubscribePreference() {
        return false;
    }

    @Override // com.onesignal.UserStateSynchronizer
    abstract void logoutChannel();

    @Override // com.onesignal.UserStateSynchronizer
    protected abstract UserState newUserState(String str, boolean z);

    @Override // com.onesignal.UserStateSynchronizer
    public void setPermission(boolean z) {
    }

    @Override // com.onesignal.UserStateSynchronizer
    void setSubscription(boolean z) {
    }

    @Override // com.onesignal.UserStateSynchronizer
    abstract void updateIdDependents(String str);

    @Override // com.onesignal.UserStateSynchronizer
    void updateState(JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStateSecondaryChannelSynchronizer(OneSignalStateSynchronizer.UserStateSynchronizerType userStateSynchronizerType) {
        super(userStateSynchronizerType);
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected OneSignal.LOG_LEVEL getLogLevel() {
        return OneSignal.LOG_LEVEL.INFO;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refresh() {
        scheduleSyncToServer();
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void scheduleSyncToServer() {
        if ((getId() == null && getRegistrationId() == null) || OneSignal.getUserId() == null) {
            return;
        }
        getNetworkHandlerThread(0).runNewJobDelayed();
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void addOnSessionOrCreateExtras(JSONObject jSONObject) {
        try {
            jSONObject.put(OSOutcomeConstants.DEVICE_TYPE, getDeviceType());
            jSONObject.putOpt("device_player_id", OneSignal.getUserId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void fireEventsForUpdateFailure(JSONObject jSONObject) {
        if (jSONObject.has("identifier")) {
            fireUpdateFailure();
        }
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void onSuccessfulSync(JSONObject jSONObject) {
        if (jSONObject.has("identifier")) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(getChannelKey(), jSONObject.get("identifier"));
                if (jSONObject.has(getAuthHashKey())) {
                    jSONObject2.put(getAuthHashKey(), jSONObject.get(getAuthHashKey()));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            fireUpdateSuccess(jSONObject2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setChannelId(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            com.onesignal.UserState r0 = r6.getUserStateForModification()
            com.onesignal.ImmutableJSONObject r1 = r0.getSyncValues()
            java.lang.String r2 = "identifier"
            java.lang.String r3 = r1.optString(r2)
            boolean r3 = r7.equals(r3)
            java.lang.String r4 = ""
            if (r3 == 0) goto L2b
            java.lang.String r3 = r6.getAuthHashKey()
            java.lang.String r3 = r1.optString(r3)
            if (r8 != 0) goto L22
            r5 = r4
            goto L23
        L22:
            r5 = r8
        L23:
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L2b
            r3 = 1
            goto L2c
        L2b:
            r3 = 0
        L2c:
            if (r3 == 0) goto L4a
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r6.getChannelKey()     // Catch: org.json.JSONException -> L42
            r0.put(r1, r7)     // Catch: org.json.JSONException -> L42
            java.lang.String r7 = r6.getAuthHashKey()     // Catch: org.json.JSONException -> L42
            r0.put(r7, r8)     // Catch: org.json.JSONException -> L42
            goto L46
        L42:
            r7 = move-exception
            r7.printStackTrace()
        L46:
            r6.fireUpdateSuccess(r0)
            return
        L4a:
            r3 = 0
            java.lang.String r1 = r1.optString(r2, r3)
            if (r1 != 0) goto L54
            r6.setNewSession()
        L54:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L7f
            r5.<init>()     // Catch: org.json.JSONException -> L7f
            r5.put(r2, r7)     // Catch: org.json.JSONException -> L7f
            if (r8 == 0) goto L65
            java.lang.String r2 = r6.getAuthHashKey()     // Catch: org.json.JSONException -> L7f
            r5.put(r2, r8)     // Catch: org.json.JSONException -> L7f
        L65:
            if (r8 != 0) goto L78
            if (r1 == 0) goto L78
            boolean r7 = r1.equals(r7)     // Catch: org.json.JSONException -> L7f
            if (r7 != 0) goto L78
            r6.saveChannelId(r4)     // Catch: org.json.JSONException -> L7f
            r6.resetCurrentState()     // Catch: org.json.JSONException -> L7f
            r6.setNewSession()     // Catch: org.json.JSONException -> L7f
        L78:
            r0.generateJsonDiffFromIntoSyncValued(r5, r3)     // Catch: org.json.JSONException -> L7f
            r6.scheduleSyncToServer()     // Catch: org.json.JSONException -> L7f
            goto L83
        L7f:
            r7 = move-exception
            r7.printStackTrace()
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.UserStateSecondaryChannelSynchronizer.setChannelId(java.lang.String, java.lang.String):void");
    }
}
