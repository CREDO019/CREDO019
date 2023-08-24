package com.onesignal;

import com.onesignal.OneSignal;
import com.onesignal.OneSignalRestClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OneSignalRemoteParams {
    public static final int DEFAULT_INDIRECT_ATTRIBUTION_WINDOW = 1440;
    public static final int DEFAULT_NOTIFICATION_LIMIT = 10;
    private static final String DIRECT_PARAM = "direct";
    private static final String DISABLE_GMS_MISSING_PROMPT = "disable_gms_missing_prompt";
    private static final String ENABLED_PARAM = "enabled";
    private static final String FCM_API_KEY = "api_key";
    private static final String FCM_APP_ID = "app_id";
    private static final String FCM_PARENT_PARAM = "fcm";
    private static final String FCM_PROJECT_ID = "project_id";
    private static final String IAM_ATTRIBUTION_PARAM = "in_app_message_attribution";
    private static final int INCREASE_BETWEEN_RETRIES = 10000;
    private static final String INDIRECT_PARAM = "indirect";
    private static final String LOCATION_SHARED = "location_shared";
    private static final int MAX_WAIT_BETWEEN_RETRIES = 90000;
    private static final int MIN_WAIT_BETWEEN_RETRIES = 30000;
    private static final String NOTIFICATION_ATTRIBUTION_PARAM = "notification_attribution";
    private static final String OUTCOMES_V2_SERVICE_PARAM = "v2_enabled";
    private static final String OUTCOME_PARAM = "outcomes";
    private static final String REQUIRES_USER_PRIVACY_CONSENT = "requires_user_privacy_consent";
    private static final String UNATTRIBUTED_PARAM = "unattributed";
    private static final String UNSUBSCRIBE_ON_NOTIFICATION_DISABLE = "unsubscribe_on_notifications_disabled";
    private static int androidParamsRetries;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface Callback {
        void complete(Params params);
    }

    static /* synthetic */ int access$008() {
        int r0 = androidParamsRetries;
        androidParamsRetries = r0 + 1;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class FCMParams {
        String apiKey;
        String appId;
        String projectId;

        FCMParams() {
        }
    }

    /* loaded from: classes3.dex */
    public static class InfluenceParams {
        int indirectNotificationAttributionWindow = OneSignalRemoteParams.DEFAULT_INDIRECT_ATTRIBUTION_WINDOW;
        int notificationLimit = 10;
        int indirectIAMAttributionWindow = OneSignalRemoteParams.DEFAULT_INDIRECT_ATTRIBUTION_WINDOW;
        int iamLimit = 10;
        boolean directEnabled = false;
        boolean indirectEnabled = false;
        boolean unattributedEnabled = false;
        boolean outcomesV2ServiceEnabled = false;

        public int getIndirectNotificationAttributionWindow() {
            return this.indirectNotificationAttributionWindow;
        }

        public int getNotificationLimit() {
            return this.notificationLimit;
        }

        public int getIndirectIAMAttributionWindow() {
            return this.indirectIAMAttributionWindow;
        }

        public int getIamLimit() {
            return this.iamLimit;
        }

        public boolean isDirectEnabled() {
            return this.directEnabled;
        }

        public boolean isIndirectEnabled() {
            return this.indirectEnabled;
        }

        public boolean isUnattributedEnabled() {
            return this.unattributedEnabled;
        }

        public String toString() {
            return "InfluenceParams{indirectNotificationAttributionWindow=" + this.indirectNotificationAttributionWindow + ", notificationLimit=" + this.notificationLimit + ", indirectIAMAttributionWindow=" + this.indirectIAMAttributionWindow + ", iamLimit=" + this.iamLimit + ", directEnabled=" + this.directEnabled + ", indirectEnabled=" + this.indirectEnabled + ", unattributedEnabled=" + this.unattributedEnabled + '}';
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class Params {
        boolean clearGroupOnSummaryClick;
        Boolean disableGMSMissingPrompt;
        boolean enterprise;
        FCMParams fcmParams;
        boolean firebaseAnalytics;
        String googleProjectNumber;
        InfluenceParams influenceParams;
        Boolean locationShared;
        JSONArray notificationChannels;
        boolean receiveReceiptEnabled;
        Boolean requiresUserPrivacyConsent;
        boolean restoreTTLFilter;
        Boolean unsubscribeWhenNotificationsDisabled;
        boolean useEmailAuth;
        boolean useSMSAuth;
        boolean useUserIdAuth;

        Params() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void makeAndroidParamsRequest(final String str, final String str2, final Callback callback) {
        OneSignalRestClient.ResponseHandler responseHandler = new OneSignalRestClient.ResponseHandler() { // from class: com.onesignal.OneSignalRemoteParams.1
            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onFailure(int r1, String str3, Throwable th) {
                if (r1 == 403) {
                    OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "403 error getting OneSignal params, omitting further retries!");
                } else {
                    new Thread(new Runnable() { // from class: com.onesignal.OneSignalRemoteParams.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            int r0 = (OneSignalRemoteParams.androidParamsRetries * 10000) + 30000;
                            if (r0 > OneSignalRemoteParams.MAX_WAIT_BETWEEN_RETRIES) {
                                r0 = OneSignalRemoteParams.MAX_WAIT_BETWEEN_RETRIES;
                            }
                            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
                            OneSignal.Log(log_level, "Failed to get Android parameters, trying again in " + (r0 / 1000) + " seconds.");
                            try {
                                Thread.sleep(r0);
                                OneSignalRemoteParams.access$008();
                                OneSignalRemoteParams.makeAndroidParamsRequest(str, str2, callback);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, "OS_PARAMS_REQUEST").start();
                }
            }

            @Override // com.onesignal.OneSignalRestClient.ResponseHandler
            void onSuccess(String str3) {
                OneSignalRemoteParams.processJson(str3, callback);
            }
        };
        String str3 = "apps/" + str + "/android_params.js";
        if (str2 != null) {
            str3 = str3 + "?player_id=" + str2;
        }
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
        OneSignalRestClient.get(str3, responseHandler, "CACHE_KEY_REMOTE_PARAMS");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processJson(String str, Callback callback) {
        try {
            callback.complete(new Params(new JSONObject(str)) { // from class: com.onesignal.OneSignalRemoteParams.2
                final /* synthetic */ JSONObject val$responseJson;

                {
                    this.val$responseJson = r6;
                    this.enterprise = r6.optBoolean("enterp", false);
                    this.useEmailAuth = r6.optBoolean("require_email_auth", false);
                    this.useUserIdAuth = r6.optBoolean("require_user_id_auth", false);
                    this.notificationChannels = r6.optJSONArray("chnl_lst");
                    this.firebaseAnalytics = r6.optBoolean("fba", false);
                    this.restoreTTLFilter = r6.optBoolean("restore_ttl_filter", true);
                    this.googleProjectNumber = r6.optString("android_sender_id", null);
                    this.clearGroupOnSummaryClick = r6.optBoolean("clear_group_on_summary_click", true);
                    this.receiveReceiptEnabled = r6.optBoolean("receive_receipts_enable", false);
                    this.disableGMSMissingPrompt = !r6.has(OneSignalRemoteParams.DISABLE_GMS_MISSING_PROMPT) ? null : Boolean.valueOf(r6.optBoolean(OneSignalRemoteParams.DISABLE_GMS_MISSING_PROMPT, false));
                    this.unsubscribeWhenNotificationsDisabled = !r6.has(OneSignalRemoteParams.UNSUBSCRIBE_ON_NOTIFICATION_DISABLE) ? null : Boolean.valueOf(r6.optBoolean(OneSignalRemoteParams.UNSUBSCRIBE_ON_NOTIFICATION_DISABLE, true));
                    this.locationShared = !r6.has(OneSignalRemoteParams.LOCATION_SHARED) ? null : Boolean.valueOf(r6.optBoolean(OneSignalRemoteParams.LOCATION_SHARED, true));
                    this.requiresUserPrivacyConsent = !r6.has(OneSignalRemoteParams.REQUIRES_USER_PRIVACY_CONSENT) ? null : Boolean.valueOf(r6.optBoolean(OneSignalRemoteParams.REQUIRES_USER_PRIVACY_CONSENT, false));
                    this.influenceParams = new InfluenceParams();
                    if (r6.has(OneSignalRemoteParams.OUTCOME_PARAM)) {
                        OneSignalRemoteParams.processOutcomeJson(r6.optJSONObject(OneSignalRemoteParams.OUTCOME_PARAM), this.influenceParams);
                    }
                    this.fcmParams = new FCMParams();
                    if (r6.has("fcm")) {
                        JSONObject optJSONObject = r6.optJSONObject("fcm");
                        this.fcmParams.apiKey = optJSONObject.optString(OneSignalRemoteParams.FCM_API_KEY, null);
                        this.fcmParams.appId = optJSONObject.optString("app_id", null);
                        this.fcmParams.projectId = optJSONObject.optString(OneSignalRemoteParams.FCM_PROJECT_ID, null);
                    }
                }
            });
        } catch (NullPointerException | JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.FATAL, "Error parsing android_params!: ", e);
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.FATAL;
            OneSignal.Log(log_level, "Response that errored from android_params!: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void processOutcomeJson(JSONObject jSONObject, InfluenceParams influenceParams) {
        if (jSONObject.has(OUTCOMES_V2_SERVICE_PARAM)) {
            influenceParams.outcomesV2ServiceEnabled = jSONObject.optBoolean(OUTCOMES_V2_SERVICE_PARAM);
        }
        if (jSONObject.has("direct")) {
            influenceParams.directEnabled = jSONObject.optJSONObject("direct").optBoolean("enabled");
        }
        if (jSONObject.has("indirect")) {
            JSONObject optJSONObject = jSONObject.optJSONObject("indirect");
            influenceParams.indirectEnabled = optJSONObject.optBoolean("enabled");
            if (optJSONObject.has(NOTIFICATION_ATTRIBUTION_PARAM)) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(NOTIFICATION_ATTRIBUTION_PARAM);
                influenceParams.indirectNotificationAttributionWindow = optJSONObject2.optInt("minutes_since_displayed", DEFAULT_INDIRECT_ATTRIBUTION_WINDOW);
                influenceParams.notificationLimit = optJSONObject2.optInt("limit", 10);
            }
            if (optJSONObject.has(IAM_ATTRIBUTION_PARAM)) {
                JSONObject optJSONObject3 = optJSONObject.optJSONObject(IAM_ATTRIBUTION_PARAM);
                influenceParams.indirectIAMAttributionWindow = optJSONObject3.optInt("minutes_since_displayed", DEFAULT_INDIRECT_ATTRIBUTION_WINDOW);
                influenceParams.iamLimit = optJSONObject3.optInt("limit", 10);
            }
        }
        if (jSONObject.has(UNATTRIBUTED_PARAM)) {
            influenceParams.unattributedEnabled = jSONObject.optJSONObject(UNATTRIBUTED_PARAM).optBoolean("enabled");
        }
    }
}
