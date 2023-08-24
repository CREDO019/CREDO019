package com.onesignal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.firebase.messaging.Constants;
import com.onesignal.OSNotificationDataController;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class NotificationBundleProcessor {
    private static final String ANDROID_NOTIFICATION_ID = "android_notif_id";
    static final String DEFAULT_ACTION = "__DEFAULT__";
    static final String IAM_PREVIEW_KEY = "os_in_app_message_preview_id";
    public static final String PUSH_ADDITIONAL_DATA_KEY = "a";
    public static final String PUSH_MINIFIED_BUTTONS_LIST = "o";
    public static final String PUSH_MINIFIED_BUTTON_ICON = "p";
    public static final String PUSH_MINIFIED_BUTTON_ID = "i";
    public static final String PUSH_MINIFIED_BUTTON_TEXT = "n";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface NotificationProcessingCallback {
        void onResult(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface ProcessBundleReceiverCallback {
        void onBundleProcessed(ProcessedBundleResult processedBundleResult);
    }

    NotificationBundleProcessor() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processFromFCMIntentService(final Context context, BundleCompat bundleCompat) {
        OneSignal.initWithContext(context);
        try {
            final String string = bundleCompat.getString("json_payload");
            if (string == null) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.Log(log_level, "json_payload key is nonexistent from mBundle passed to ProcessFromFCMIntentService: " + bundleCompat);
                return;
            }
            final JSONObject jSONObject = new JSONObject(string);
            final boolean z = bundleCompat.getBoolean("is_restoring", false);
            final long longValue = bundleCompat.getLong("timestamp").longValue();
            final int intValue = bundleCompat.containsKey(ANDROID_NOTIFICATION_ID) ? bundleCompat.getInt(ANDROID_NOTIFICATION_ID).intValue() : 0;
            OneSignal.notValidOrDuplicated(context, jSONObject, new OSNotificationDataController.InvalidOrDuplicateNotificationCallback() { // from class: com.onesignal.NotificationBundleProcessor.1
                @Override // com.onesignal.OSNotificationDataController.InvalidOrDuplicateNotificationCallback
                public void onResult(boolean z2) {
                    if (z || !z2) {
                        OSNotificationWorkManager.beginEnqueueingWork(context, OSNotificationFormatHelper.getOSNotificationIdFromJson(jSONObject), intValue, string, longValue, z, false);
                        if (z) {
                            OSUtils.sleep(100);
                        }
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int processJobForDisplay(OSNotificationGenerationJob oSNotificationGenerationJob, boolean z) {
        return processJobForDisplay(new OSNotificationController(oSNotificationGenerationJob, oSNotificationGenerationJob.isRestoring(), true), false, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int processJobForDisplay(OSNotificationController oSNotificationController, boolean z) {
        return processJobForDisplay(oSNotificationController, false, z);
    }

    private static int processJobForDisplay(OSNotificationController oSNotificationController, boolean z, boolean z2) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "Starting processJobForDisplay opened: " + z + " fromBackgroundLogic: " + z2);
        OSNotificationGenerationJob notificationJob = oSNotificationController.getNotificationJob();
        processCollapseKey(notificationJob);
        int intValue = notificationJob.getAndroidId().intValue();
        boolean z3 = false;
        if (shouldDisplayNotification(notificationJob)) {
            notificationJob.setIsNotificationToDisplay(true);
            if (z2 && OneSignal.shouldFireForegroundHandlers(notificationJob)) {
                oSNotificationController.setFromBackgroundLogic(false);
                OneSignal.fireForegroundHandlers(oSNotificationController);
                return intValue;
            }
            z3 = GenerateNotification.displayNotification(notificationJob);
        }
        if (!notificationJob.isRestoring()) {
            processNotification(notificationJob, z, z3);
            OSNotificationWorkManager.removeNotificationIdProcessed(OSNotificationFormatHelper.getOSNotificationIdFromJson(oSNotificationController.getNotificationJob().getJsonPayload()));
            OneSignal.handleNotificationReceived(notificationJob);
        }
        return intValue;
    }

    private static boolean shouldDisplayNotification(OSNotificationGenerationJob oSNotificationGenerationJob) {
        return oSNotificationGenerationJob.hasExtender() || OSUtils.isStringNotEmpty(oSNotificationGenerationJob.getJsonPayload().optString("alert"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processNotification(OSNotificationGenerationJob oSNotificationGenerationJob, boolean z, boolean z2) {
        saveNotification(oSNotificationGenerationJob, z);
        if (!z2) {
            markNotificationAsDismissed(oSNotificationGenerationJob);
            return;
        }
        String apiNotificationId = oSNotificationGenerationJob.getApiNotificationId();
        OSReceiveReceiptController.getInstance().beginEnqueueingWork(oSNotificationGenerationJob.getContext(), apiNotificationId);
        OneSignal.getSessionManager().onNotificationReceived(apiNotificationId);
    }

    private static void saveNotification(OSNotificationGenerationJob oSNotificationGenerationJob, boolean z) {
        OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Saving Notification job: " + oSNotificationGenerationJob.toString());
        Context context = oSNotificationGenerationJob.getContext();
        JSONObject jsonPayload = oSNotificationGenerationJob.getJsonPayload();
        try {
            JSONObject customJSONObject = getCustomJSONObject(oSNotificationGenerationJob.getJsonPayload());
            OneSignalDbHelper oneSignalDbHelper = OneSignalDbHelper.getInstance(oSNotificationGenerationJob.getContext());
            int r9 = 1;
            if (oSNotificationGenerationJob.isNotificationToDisplay()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("dismissed", (Integer) 1);
                oneSignalDbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, "android_notification_id = " + oSNotificationGenerationJob.getAndroidId(), null);
                BadgeCountUpdater.update(oneSignalDbHelper, context);
            }
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("notification_id", customJSONObject.optString("i"));
            if (jsonPayload.has("grp")) {
                contentValues2.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_GROUP_ID, jsonPayload.optString("grp"));
            }
            if (jsonPayload.has(Constants.MessagePayloadKeys.COLLAPSE_KEY) && !"do_not_collapse".equals(jsonPayload.optString(Constants.MessagePayloadKeys.COLLAPSE_KEY))) {
                contentValues2.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_COLLAPSE_ID, jsonPayload.optString(Constants.MessagePayloadKeys.COLLAPSE_KEY));
            }
            if (!z) {
                r9 = 0;
            }
            contentValues2.put("opened", Integer.valueOf(r9));
            if (!z) {
                contentValues2.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, oSNotificationGenerationJob.getAndroidId());
            }
            if (oSNotificationGenerationJob.getTitle() != null) {
                contentValues2.put("title", oSNotificationGenerationJob.getTitle().toString());
            }
            if (oSNotificationGenerationJob.getBody() != null) {
                contentValues2.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, oSNotificationGenerationJob.getBody().toString());
            }
            contentValues2.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_EXPIRE_TIME, Long.valueOf((jsonPayload.optLong(Constants.MessagePayloadKeys.SENT_TIME, OneSignal.getTime().getCurrentTimeMillis()) / 1000) + jsonPayload.optInt(Constants.MessagePayloadKeys.TTL, 259200)));
            contentValues2.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA, jsonPayload.toString());
            oneSignalDbHelper.insertOrThrow(OneSignalDbContract.NotificationTable.TABLE_NAME, null, contentValues2);
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Notification saved values: " + contentValues2.toString());
            if (z) {
                return;
            }
            BadgeCountUpdater.update(oneSignalDbHelper, context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void markNotificationAsDismissed(OSNotificationGenerationJob oSNotificationGenerationJob) {
        if (oSNotificationGenerationJob.isNotificationToDisplay()) {
            OneSignal.Log(OneSignal.LOG_LEVEL.DEBUG, "Marking restored or disabled notifications as dismissed: " + oSNotificationGenerationJob.toString());
            OneSignalDbHelper oneSignalDbHelper = OneSignalDbHelper.getInstance(oSNotificationGenerationJob.getContext());
            ContentValues contentValues = new ContentValues();
            contentValues.put("dismissed", (Integer) 1);
            oneSignalDbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, "android_notification_id = " + oSNotificationGenerationJob.getAndroidId(), null);
            BadgeCountUpdater.update(oneSignalDbHelper, oSNotificationGenerationJob.getContext());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject bundleAsJSONObject(Bundle bundle) {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            try {
                jSONObject.put(str, bundle.get(str));
            } catch (JSONException e) {
                OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                OneSignal.Log(log_level, "bundleAsJSONObject error for key: " + str, e);
            }
        }
        return jSONObject;
    }

    private static void maximizeButtonsFromBundle(Bundle bundle) {
        JSONObject jSONObject;
        String str;
        if (bundle.containsKey(PUSH_MINIFIED_BUTTONS_LIST)) {
            try {
                JSONObject jSONObject2 = new JSONObject(bundle.getString(OSNotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
                if (jSONObject2.has(PUSH_ADDITIONAL_DATA_KEY)) {
                    jSONObject = jSONObject2.getJSONObject(PUSH_ADDITIONAL_DATA_KEY);
                } else {
                    jSONObject = new JSONObject();
                }
                JSONArray jSONArray = new JSONArray(bundle.getString(PUSH_MINIFIED_BUTTONS_LIST));
                bundle.remove(PUSH_MINIFIED_BUTTONS_LIST);
                for (int r5 = 0; r5 < jSONArray.length(); r5++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(r5);
                    String string = jSONObject3.getString(PUSH_MINIFIED_BUTTON_TEXT);
                    jSONObject3.remove(PUSH_MINIFIED_BUTTON_TEXT);
                    if (jSONObject3.has("i")) {
                        str = jSONObject3.getString("i");
                        jSONObject3.remove("i");
                    } else {
                        str = string;
                    }
                    jSONObject3.put("id", str);
                    jSONObject3.put("text", string);
                    if (jSONObject3.has("p")) {
                        jSONObject3.put("icon", jSONObject3.getString("p"));
                        jSONObject3.remove("p");
                    }
                }
                jSONObject.put("actionButtons", jSONArray);
                jSONObject.put(GenerateNotification.BUNDLE_KEY_ACTION_ID, DEFAULT_ACTION);
                if (!jSONObject2.has(PUSH_ADDITIONAL_DATA_KEY)) {
                    jSONObject2.put(PUSH_ADDITIONAL_DATA_KEY, jSONObject);
                }
                bundle.putString(OSNotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM, jSONObject2.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void processCollapseKey(OSNotificationGenerationJob oSNotificationGenerationJob) {
        if (oSNotificationGenerationJob.isRestoring() || !oSNotificationGenerationJob.getJsonPayload().has(Constants.MessagePayloadKeys.COLLAPSE_KEY) || "do_not_collapse".equals(oSNotificationGenerationJob.getJsonPayload().optString(Constants.MessagePayloadKeys.COLLAPSE_KEY))) {
            return;
        }
        Cursor query = OneSignalDbHelper.getInstance(oSNotificationGenerationJob.getContext()).query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID}, "collapse_id = ? AND dismissed = 0 AND opened = 0 ", new String[]{oSNotificationGenerationJob.getJsonPayload().optString(Constants.MessagePayloadKeys.COLLAPSE_KEY)}, null, null, null);
        if (query.moveToFirst()) {
            oSNotificationGenerationJob.getNotification().setAndroidNotificationId(query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
        }
        query.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processBundleFromReceiver(Context context, Bundle bundle, final ProcessBundleReceiverCallback processBundleReceiverCallback) {
        final ProcessedBundleResult processedBundleResult = new ProcessedBundleResult();
        if (!OSNotificationFormatHelper.isOneSignalBundle(bundle)) {
            processBundleReceiverCallback.onBundleProcessed(processedBundleResult);
            return;
        }
        processedBundleResult.setOneSignalPayload(true);
        maximizeButtonsFromBundle(bundle);
        if (OSInAppMessagePreviewHandler.notificationReceived(context, bundle)) {
            processedBundleResult.setInAppPreviewShown(true);
            processBundleReceiverCallback.onBundleProcessed(processedBundleResult);
            return;
        }
        startNotificationProcessing(context, bundle, processedBundleResult, new NotificationProcessingCallback() { // from class: com.onesignal.NotificationBundleProcessor.2
            @Override // com.onesignal.NotificationBundleProcessor.NotificationProcessingCallback
            public void onResult(boolean z) {
                if (!z) {
                    ProcessedBundleResult.this.setDup(true);
                }
                processBundleReceiverCallback.onBundleProcessed(ProcessedBundleResult.this);
            }
        });
    }

    private static void startNotificationProcessing(final Context context, final Bundle bundle, final ProcessedBundleResult processedBundleResult, final NotificationProcessingCallback notificationProcessingCallback) {
        final JSONObject bundleAsJSONObject = bundleAsJSONObject(bundle);
        final long currentTimeMillis = OneSignal.getTime().getCurrentTimeMillis() / 1000;
        final boolean z = bundle.getBoolean("is_restoring", false);
        final boolean z2 = Integer.parseInt(bundle.getString("pri", SessionDescription.SUPPORTED_SDP_VERSION)) > 9;
        OneSignal.notValidOrDuplicated(context, bundleAsJSONObject, new OSNotificationDataController.InvalidOrDuplicateNotificationCallback() { // from class: com.onesignal.NotificationBundleProcessor.3
            @Override // com.onesignal.OSNotificationDataController.InvalidOrDuplicateNotificationCallback
            public void onResult(boolean z3) {
                if (!z && z3) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
                    OneSignal.Log(log_level, "startNotificationProcessing returning, with context: " + context + " and bundle: " + bundle);
                    notificationProcessingCallback.onResult(false);
                    return;
                }
                OSNotificationWorkManager.beginEnqueueingWork(context, OSNotificationFormatHelper.getOSNotificationIdFromJson(bundleAsJSONObject), bundle.containsKey(NotificationBundleProcessor.ANDROID_NOTIFICATION_ID) ? bundle.getInt(NotificationBundleProcessor.ANDROID_NOTIFICATION_ID) : 0, bundleAsJSONObject.toString(), currentTimeMillis, z, z2);
                processedBundleResult.setWorkManagerProcessing(true);
                notificationProcessingCallback.onResult(true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONArray newJsonArray(JSONObject jSONObject) {
        return new JSONArray().put(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject getCustomJSONObject(JSONObject jSONObject) throws JSONException {
        return new JSONObject(jSONObject.optString(OSNotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasRemoteResource(Bundle bundle) {
        return isBuildKeyRemote(bundle, "licon") || isBuildKeyRemote(bundle, "bicon") || bundle.getString("bg_img", null) != null;
    }

    private static boolean isBuildKeyRemote(Bundle bundle, String str) {
        String trim = bundle.getString(str, "").trim();
        return trim.startsWith("http://") || trim.startsWith("https://");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ProcessedBundleResult {
        private boolean inAppPreviewShown;
        private boolean isDup;
        private boolean isOneSignalPayload;
        private boolean isWorkManagerProcessing;

        ProcessedBundleResult() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean processed() {
            return !this.isOneSignalPayload || this.isDup || this.inAppPreviewShown || this.isWorkManagerProcessing;
        }

        void setOneSignalPayload(boolean z) {
            this.isOneSignalPayload = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean isDup() {
            return this.isDup;
        }

        void setDup(boolean z) {
            this.isDup = z;
        }

        public void setInAppPreviewShown(boolean z) {
            this.inAppPreviewShown = z;
        }

        public boolean isWorkManagerProcessing() {
            return this.isWorkManagerProcessing;
        }

        public void setWorkManagerProcessing(boolean z) {
            this.isWorkManagerProcessing = z;
        }
    }
}
