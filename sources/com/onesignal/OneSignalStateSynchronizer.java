package com.onesignal;

import com.onesignal.LocationController;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import com.onesignal.UserStateSynchronizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OneSignalStateSynchronizer {
    private static final Object LOCK = new Object();
    private static HashMap<UserStateSynchronizerType, UserStateSynchronizer> userStateSynchronizers = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface OSDeviceInfoCompletionHandler {
        void onFailure(OSDeviceInfoError oSDeviceInfoError);

        void onSuccess(String str);
    }

    OneSignalStateSynchronizer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public enum UserStateSynchronizerType {
        PUSH,
        EMAIL,
        SMS;

        public boolean isPush() {
            return equals(PUSH);
        }

        public boolean isEmail() {
            return equals(EMAIL);
        }

        public boolean isSMS() {
            return equals(SMS);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class OSDeviceInfoError {
        public int errorCode;
        public String message;

        /* JADX INFO: Access modifiers changed from: package-private */
        public OSDeviceInfoError(int r1, String str) {
            this.errorCode = r1;
            this.message = str;
        }

        public int getCode() {
            return this.errorCode;
        }

        public String getMessage() {
            return this.message;
        }
    }

    static UserStatePushSynchronizer getPushStateSynchronizer() {
        if (!userStateSynchronizers.containsKey(UserStateSynchronizerType.PUSH) || userStateSynchronizers.get(UserStateSynchronizerType.PUSH) == null) {
            synchronized (LOCK) {
                if (userStateSynchronizers.get(UserStateSynchronizerType.PUSH) == null) {
                    userStateSynchronizers.put(UserStateSynchronizerType.PUSH, new UserStatePushSynchronizer());
                }
            }
        }
        return (UserStatePushSynchronizer) userStateSynchronizers.get(UserStateSynchronizerType.PUSH);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UserStateEmailSynchronizer getEmailStateSynchronizer() {
        if (!userStateSynchronizers.containsKey(UserStateSynchronizerType.EMAIL) || userStateSynchronizers.get(UserStateSynchronizerType.EMAIL) == null) {
            synchronized (LOCK) {
                if (userStateSynchronizers.get(UserStateSynchronizerType.EMAIL) == null) {
                    userStateSynchronizers.put(UserStateSynchronizerType.EMAIL, new UserStateEmailSynchronizer());
                }
            }
        }
        return (UserStateEmailSynchronizer) userStateSynchronizers.get(UserStateSynchronizerType.EMAIL);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UserStateSMSSynchronizer getSMSStateSynchronizer() {
        if (!userStateSynchronizers.containsKey(UserStateSynchronizerType.SMS) || userStateSynchronizers.get(UserStateSynchronizerType.SMS) == null) {
            synchronized (LOCK) {
                if (userStateSynchronizers.get(UserStateSynchronizerType.SMS) == null) {
                    userStateSynchronizers.put(UserStateSynchronizerType.SMS, new UserStateSMSSynchronizer());
                }
            }
        }
        return (UserStateSMSSynchronizer) userStateSynchronizers.get(UserStateSynchronizerType.SMS);
    }

    static List<UserStateSynchronizer> getUserStateSynchronizers() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getPushStateSynchronizer());
        if (OneSignal.hasEmailId()) {
            arrayList.add(getEmailStateSynchronizer());
        }
        if (OneSignal.hasSMSlId()) {
            arrayList.add(getSMSStateSynchronizer());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean persist() {
        boolean persist = getPushStateSynchronizer().persist();
        boolean persist2 = getEmailStateSynchronizer().persist();
        boolean persist3 = getSMSStateSynchronizer().persist();
        if (persist2) {
            persist2 = getEmailStateSynchronizer().getRegistrationId() != null;
        }
        if (persist3) {
            persist3 = getSMSStateSynchronizer().getRegistrationId() != null;
        }
        return persist || persist2 || persist3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearLocation() {
        getPushStateSynchronizer().clearLocation();
        getEmailStateSynchronizer().clearLocation();
        getSMSStateSynchronizer().clearLocation();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initUserState() {
        getPushStateSynchronizer().initUserState();
        getEmailStateSynchronizer().initUserState();
        getSMSStateSynchronizer().initUserState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void syncUserState(boolean z) {
        getPushStateSynchronizer().syncUserState(z);
        getEmailStateSynchronizer().syncUserState(z);
        getSMSStateSynchronizer().syncUserState(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendTags(JSONObject jSONObject, OneSignal.ChangeTagsUpdateHandler changeTagsUpdateHandler) {
        try {
            JSONObject put = new JSONObject().put(UserState.TAGS, jSONObject);
            getPushStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
            getEmailStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
            getSMSStateSynchronizer().sendTags(put, changeTagsUpdateHandler);
        } catch (JSONException e) {
            if (changeTagsUpdateHandler != null) {
                changeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "Encountered an error attempting to serialize your tags into JSON: " + e.getMessage() + "\n" + e.getStackTrace()));
            }
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSMSNumber(String str, String str2) {
        getPushStateSynchronizer().setSMSNumber(str, str2);
        getSMSStateSynchronizer().setChannelId(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setEmail(String str, String str2) {
        getPushStateSynchronizer().setEmail(str, str2);
        getEmailStateSynchronizer().setChannelId(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setSubscription(boolean z) {
        getPushStateSynchronizer().setSubscription(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getUserSubscribePreference() {
        return getPushStateSynchronizer().getUserSubscribePreference();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getLanguage() {
        return getPushStateSynchronizer().getLanguage();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setPermission(boolean z) {
        getPushStateSynchronizer().setPermission(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateLocation(LocationController.LocationPoint locationPoint) {
        getPushStateSynchronizer().updateLocation(locationPoint);
        getEmailStateSynchronizer().updateLocation(locationPoint);
        getSMSStateSynchronizer().updateLocation(locationPoint);
    }

    static boolean getSubscribed() {
        return getPushStateSynchronizer().getSubscribed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getRegistrationId() {
        return getPushStateSynchronizer().getRegistrationId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UserStateSynchronizer.GetTagsResult getTags(boolean z) {
        return getPushStateSynchronizer().getTags(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resetCurrentState() {
        getPushStateSynchronizer().resetCurrentState();
        getEmailStateSynchronizer().resetCurrentState();
        getSMSStateSynchronizer().resetCurrentState();
        getPushStateSynchronizer().saveChannelId(null);
        getEmailStateSynchronizer().saveChannelId(null);
        getSMSStateSynchronizer().saveChannelId(null);
        OneSignal.setLastSessionTime(-3660L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateDeviceInfo(JSONObject jSONObject, OSDeviceInfoCompletionHandler oSDeviceInfoCompletionHandler) {
        getPushStateSynchronizer().updateDeviceInfo(jSONObject, oSDeviceInfoCompletionHandler);
        getEmailStateSynchronizer().updateDeviceInfo(jSONObject, oSDeviceInfoCompletionHandler);
        getSMSStateSynchronizer().updateDeviceInfo(jSONObject, oSDeviceInfoCompletionHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updatePushState(JSONObject jSONObject) {
        getPushStateSynchronizer().updateState(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void refreshSecondaryChannelState() {
        getEmailStateSynchronizer().refresh();
        getSMSStateSynchronizer().refresh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setNewSession() {
        getPushStateSynchronizer().setNewSession();
        getEmailStateSynchronizer().setNewSession();
        getSMSStateSynchronizer().setNewSession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean getSyncAsNewSession() {
        return getPushStateSynchronizer().getSyncAsNewSession() || getEmailStateSynchronizer().getSyncAsNewSession() || getSMSStateSynchronizer().getSyncAsNewSession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setNewSessionForEmail() {
        getEmailStateSynchronizer().setNewSession();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logoutEmail() {
        getPushStateSynchronizer().logoutEmail();
        getEmailStateSynchronizer().logoutChannel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logoutSMS() {
        getSMSStateSynchronizer().logoutChannel();
        getPushStateSynchronizer().logoutSMS();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setExternalUserId(String str, String str2, final OneSignal.OSExternalUserIdUpdateCompletionHandler oSExternalUserIdUpdateCompletionHandler) throws JSONException {
        final JSONObject jSONObject = new JSONObject();
        OneSignal.OSInternalExternalUserIdUpdateCompletionHandler oSInternalExternalUserIdUpdateCompletionHandler = new OneSignal.OSInternalExternalUserIdUpdateCompletionHandler() { // from class: com.onesignal.OneSignalStateSynchronizer.1
            @Override // com.onesignal.OneSignal.OSInternalExternalUserIdUpdateCompletionHandler
            public void onComplete(String str3, boolean z) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
                OneSignal.onesignalLog(log_level, "Completed request to update external user id for channel: " + str3 + " and success: " + z);
                try {
                    jSONObject.put(str3, new JSONObject().put("success", z));
                } catch (JSONException e) {
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.onesignalLog(log_level2, "Error while adding the success status of external id for channel: " + str3);
                    e.printStackTrace();
                }
                for (UserStateSynchronizer userStateSynchronizer : OneSignalStateSynchronizer.userStateSynchronizers.values()) {
                    if (userStateSynchronizer.hasQueuedHandlers()) {
                        OneSignal.LOG_LEVEL log_level3 = OneSignal.LOG_LEVEL.VERBOSE;
                        OneSignal.onesignalLog(log_level3, "External user id handlers are still being processed for channel: " + userStateSynchronizer.getChannelString() + " , wait until finished before proceeding");
                        return;
                    }
                }
                OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.OneSignalStateSynchronizer.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (oSExternalUserIdUpdateCompletionHandler != null) {
                            oSExternalUserIdUpdateCompletionHandler.onSuccess(jSONObject);
                        }
                    }
                });
            }
        };
        for (UserStateSynchronizer userStateSynchronizer : getUserStateSynchronizers()) {
            userStateSynchronizer.setExternalUserId(str, str2, oSInternalExternalUserIdUpdateCompletionHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sendPurchases(JSONObject jSONObject, OneSignalRestClient.ResponseHandler responseHandler) {
        for (UserStateSynchronizer userStateSynchronizer : getUserStateSynchronizers()) {
            userStateSynchronizer.sendPurchases(jSONObject, responseHandler);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void readyToUpdate(boolean z) {
        getPushStateSynchronizer().readyToUpdate(z);
        getEmailStateSynchronizer().readyToUpdate(z);
        getSMSStateSynchronizer().readyToUpdate(z);
    }
}
