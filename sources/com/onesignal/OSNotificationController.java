package com.onesignal;

import android.content.Context;
import com.onesignal.OneSignal;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSNotificationController {
    private static final String EXTENSION_SERVICE_META_DATA_TAG_NAME = "com.onesignal.NotificationServiceExtension";
    static final String GOOGLE_SENT_TIME_KEY = "google.sent_time";
    static final String GOOGLE_TTL_KEY = "google.ttl";
    private boolean fromBackgroundLogic;
    private final OSNotificationGenerationJob notificationJob;
    private boolean restoring;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSNotificationController(OSNotificationGenerationJob oSNotificationGenerationJob, boolean z, boolean z2) {
        this.restoring = z;
        this.fromBackgroundLogic = z2;
        this.notificationJob = oSNotificationGenerationJob;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSNotificationController(Context context, OSNotification oSNotification, JSONObject jSONObject, boolean z, boolean z2, Long l) {
        this.restoring = z;
        this.fromBackgroundLogic = z2;
        this.notificationJob = createNotificationJobFromCurrent(context, oSNotification, jSONObject, l);
    }

    private OSNotificationGenerationJob createNotificationJobFromCurrent(Context context, OSNotification oSNotification, JSONObject jSONObject, Long l) {
        OSNotificationGenerationJob oSNotificationGenerationJob = new OSNotificationGenerationJob(context);
        oSNotificationGenerationJob.setJsonPayload(jSONObject);
        oSNotificationGenerationJob.setShownTimeStamp(l);
        oSNotificationGenerationJob.setRestoring(this.restoring);
        oSNotificationGenerationJob.setNotification(oSNotification);
        return oSNotificationGenerationJob;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processNotification(OSNotification oSNotification, OSNotification oSNotification2) {
        if (oSNotification2 != null) {
            boolean isStringNotEmpty = OSUtils.isStringNotEmpty(oSNotification2.getBody());
            boolean isNotificationWithinTTL = isNotificationWithinTTL();
            if (isStringNotEmpty && isNotificationWithinTTL) {
                this.notificationJob.setNotification(oSNotification2);
                NotificationBundleProcessor.processJobForDisplay(this, this.fromBackgroundLogic);
            } else {
                notDisplayNotificationLogic(oSNotification);
            }
            if (this.restoring) {
                OSUtils.sleep(100);
                return;
            }
            return;
        }
        notDisplayNotificationLogic(oSNotification);
    }

    private void notDisplayNotificationLogic(OSNotification oSNotification) {
        this.notificationJob.setNotification(oSNotification);
        if (this.restoring) {
            NotificationBundleProcessor.markNotificationAsDismissed(this.notificationJob);
            return;
        }
        this.notificationJob.setIsNotificationToDisplay(false);
        NotificationBundleProcessor.processNotification(this.notificationJob, true, false);
        OneSignal.handleNotificationReceived(this.notificationJob);
    }

    public boolean isNotificationWithinTTL() {
        if (OneSignal.getRemoteParamController().isRestoreTTLFilterActive()) {
            return this.notificationJob.getNotification().getSentTime() + ((long) this.notificationJob.getNotification().getTtl()) > OneSignal.getTime().getCurrentTimeMillis() / 1000;
        }
        return true;
    }

    public OSNotificationGenerationJob getNotificationJob() {
        return this.notificationJob;
    }

    public OSNotificationReceivedEvent getNotificationReceivedEvent() {
        return new OSNotificationReceivedEvent(this, this.notificationJob.getNotification());
    }

    public boolean isRestoring() {
        return this.restoring;
    }

    public void setRestoring(boolean z) {
        this.restoring = z;
    }

    public boolean isFromBackgroundLogic() {
        return this.fromBackgroundLogic;
    }

    public void setFromBackgroundLogic(boolean z) {
        this.fromBackgroundLogic = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setupNotificationServiceExtension(Context context) {
        String manifestMeta = OSUtils.getManifestMeta(context, EXTENSION_SERVICE_META_DATA_TAG_NAME);
        if (manifestMeta == null) {
            OneSignal.onesignalLog(OneSignal.LOG_LEVEL.VERBOSE, "No class found, not setting up OSRemoteNotificationReceivedHandler");
            return;
        }
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.onesignalLog(log_level, "Found class: " + manifestMeta + ", attempting to call constructor");
        try {
            Object newInstance = Class.forName(manifestMeta).newInstance();
            if ((newInstance instanceof OneSignal.OSRemoteNotificationReceivedHandler) && OneSignal.remoteNotificationReceivedHandler == null) {
                OneSignal.setRemoteNotificationReceivedHandler((OneSignal.OSRemoteNotificationReceivedHandler) newInstance);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    public String toString() {
        return "OSNotificationController{notificationJob=" + this.notificationJob + ", isRestoring=" + this.restoring + ", isBackgroundLogic=" + this.fromBackgroundLogic + '}';
    }
}
