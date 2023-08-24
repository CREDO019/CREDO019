package com.onesignal;

import com.amplitude.api.Constants;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.OneSignalStateSynchronizer;
import com.onesignal.UserStateSynchronizer;
import com.onesignal.outcomes.OSOutcomeConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class UserStatePushSynchronizer extends UserStateSynchronizer {
    private static boolean serverSuccess;

    @Override // com.onesignal.UserStateSynchronizer
    protected void addOnSessionOrCreateExtras(JSONObject jSONObject) {
    }

    @Override // com.onesignal.UserStateSynchronizer
    void logoutChannel() {
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void onSuccessfulSync(JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStatePushSynchronizer() {
        super(OneSignalStateSynchronizer.UserStateSynchronizerType.PUSH);
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected UserState newUserState(String str, boolean z) {
        return new UserStatePush(str, z);
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected OneSignal.LOG_LEVEL getLogLevel() {
        return OneSignal.LOG_LEVEL.ERROR;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void saveChannelId(String str) {
        OneSignal.saveUserId(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public boolean getSubscribed() {
        return getToSyncUserState().isSubscribed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        UserStateSynchronizer.GetTagsResult getTagsResult;
        if (z) {
            String userId = OneSignal.getUserId();
            String savedAppId = OneSignal.getSavedAppId();
            OneSignalRestClient.getSync("players/" + userId + "?app_id=" + savedAppId, new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.UserStatePushSynchronizer.1
                @Override // com.onesignal.OneSignalRestClient.ResponseHandler
                void onSuccess(String str) {
                    boolean unused = UserStatePushSynchronizer.serverSuccess = true;
                    try {
                        JSONObject jSONObject = new JSONObject((str == null || str.isEmpty()) ? "{}" : "{}");
                        if (jSONObject.has(UserState.TAGS)) {
                            synchronized (UserStatePushSynchronizer.this.LOCK) {
                                UserStatePushSynchronizer userStatePushSynchronizer = UserStatePushSynchronizer.this;
                                JSONObject generateJsonDiff = userStatePushSynchronizer.generateJsonDiff(userStatePushSynchronizer.getCurrentUserState().getSyncValues().optJSONObject(UserState.TAGS), UserStatePushSynchronizer.this.getToSyncUserState().getSyncValues().optJSONObject(UserState.TAGS), null, null);
                                UserStatePushSynchronizer.this.getCurrentUserState().putOnSyncValues(UserState.TAGS, jSONObject.optJSONObject(UserState.TAGS));
                                UserStatePushSynchronizer.this.getCurrentUserState().persistState();
                                UserStatePushSynchronizer.this.getToSyncUserState().mergeTags(jSONObject, generateJsonDiff);
                                UserStatePushSynchronizer.this.getToSyncUserState().persistState();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, "CACHE_KEY_GET_TAGS");
        }
        synchronized (this.LOCK) {
            getTagsResult = new UserStateSynchronizer.GetTagsResult(serverSuccess, JSONUtils.getJSONObjectWithoutBlankValues(getToSyncUserState().getSyncValues(), UserState.TAGS));
        }
        return getTagsResult;
    }

    @Override // com.onesignal.UserStateSynchronizer
    String getExternalId(boolean z) {
        String optString;
        synchronized (this.LOCK) {
            optString = getToSyncUserState().getSyncValues().optString("external_user_id", null);
        }
        return optString;
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void scheduleSyncToServer() {
        getNetworkHandlerThread(0).runNewJobDelayed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void updateState(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("identifier", jSONObject.optString("identifier", null));
            if (jSONObject.has(OSOutcomeConstants.DEVICE_TYPE)) {
                jSONObject2.put(OSOutcomeConstants.DEVICE_TYPE, jSONObject.optInt(OSOutcomeConstants.DEVICE_TYPE));
            }
            jSONObject2.putOpt("parent_player_id", jSONObject.optString("parent_player_id", null));
            getUserStateForModification().generateJsonDiffFromIntoSyncValued(jSONObject2, null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject.has("subscribableStatus")) {
                jSONObject3.put("subscribableStatus", jSONObject.optInt("subscribableStatus"));
            }
            if (jSONObject.has("androidPermission")) {
                jSONObject3.put("androidPermission", jSONObject.optBoolean("androidPermission"));
            }
            getUserStateForModification().generateJsonDiffFromIntoDependValues(jSONObject3, null);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEmail(String str, String str2) {
        try {
            UserState userStateForModification = getUserStateForModification();
            userStateForModification.putOnDependValues("email_auth_hash", str2);
            userStateForModification.generateJsonDiffFromIntoSyncValued(new JSONObject().put("email", str), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSMSNumber(String str, String str2) {
        try {
            UserState userStateForModification = getUserStateForModification();
            userStateForModification.putOnDependValues("sms_auth_hash", str2);
            userStateForModification.generateJsonDiffFromIntoSyncValued(new JSONObject().put("sms_number", str), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserStateSynchronizer
    public void setSubscription(boolean z) {
        try {
            getUserStateForModification().putOnDependValues("userSubscribePref", Boolean.valueOf(z));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.onesignal.UserStateSynchronizer
    public boolean getUserSubscribePreference() {
        return getToSyncUserState().getDependValues().optBoolean("userSubscribePref", true);
    }

    public String getLanguage() {
        return getToSyncUserState().getDependValues().optString(Constants.AMP_TRACKING_OPTION_LANGUAGE, null);
    }

    @Override // com.onesignal.UserStateSynchronizer
    public void setPermission(boolean z) {
        try {
            getUserStateForModification().putOnDependValues("androidPermission", Boolean.valueOf(z));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected String getId() {
        return OneSignal.getUserId();
    }

    @Override // com.onesignal.UserStateSynchronizer
    void updateIdDependents(String str) {
        OneSignal.updateUserIdDependents(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logoutEmail() {
        try {
            getUserStateForModification().putOnDependValues("logoutEmail", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void logoutSMS() {
        UserState toSyncUserState = getToSyncUserState();
        toSyncUserState.removeFromDependValues("sms_auth_hash");
        toSyncUserState.removeFromSyncValues("sms_number");
        toSyncUserState.persistState();
        UserState currentUserState = getCurrentUserState();
        currentUserState.removeFromDependValues("sms_auth_hash");
        String optString = currentUserState.getSyncValues().optString("sms_number");
        currentUserState.removeFromSyncValues("sms_number");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sms_number", optString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
        OneSignal.Log(log_level, "Device successfully logged out of SMS number: " + jSONObject);
        OneSignal.handleSuccessfulSMSlLogout(jSONObject);
    }

    @Override // com.onesignal.UserStateSynchronizer
    protected void fireEventsForUpdateFailure(JSONObject jSONObject) {
        if (jSONObject.has("email")) {
            OneSignal.fireEmailUpdateFailure();
        }
        if (jSONObject.has("sms_number")) {
            OneSignal.fireSMSUpdateFailure();
        }
    }
}
