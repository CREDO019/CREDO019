package com.onesignal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.amazon.device.messaging.ADMMessageHandlerBase;
import com.amazon.device.messaging.ADMMessageReceiver;
import com.onesignal.NotificationBundleProcessor;
import com.onesignal.OneSignal;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ADMMessageHandler extends ADMMessageHandlerBase {
    private static final int JOB_ID = 123891;

    /* loaded from: classes3.dex */
    public static class Receiver extends ADMMessageReceiver {
        public Receiver() {
            super(ADMMessageHandler.class);
            boolean z;
            try {
                Class.forName("com.amazon.device.messaging.ADMMessageHandlerJobBase");
                z = true;
            } catch (ClassNotFoundException unused) {
                z = false;
            }
            if (z) {
                registerJobServiceClass(ADMMessageHandlerJob.class, ADMMessageHandler.JOB_ID);
            }
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "ADM latest available: " + z);
        }
    }

    public ADMMessageHandler() {
        super("ADMMessageHandler");
    }

    protected void onMessage(Intent intent) {
        final Context applicationContext = getApplicationContext();
        final Bundle extras = intent.getExtras();
        NotificationBundleProcessor.processBundleFromReceiver(applicationContext, extras, new NotificationBundleProcessor.ProcessBundleReceiverCallback() { // from class: com.onesignal.ADMMessageHandler.1
            @Override // com.onesignal.NotificationBundleProcessor.ProcessBundleReceiverCallback
            public void onBundleProcessed(NotificationBundleProcessor.ProcessedBundleResult processedBundleResult) {
                if (processedBundleResult.processed()) {
                    return;
                }
                JSONObject bundleAsJSONObject = NotificationBundleProcessor.bundleAsJSONObject(extras);
                OSNotification oSNotification = new OSNotification(bundleAsJSONObject);
                OSNotificationGenerationJob oSNotificationGenerationJob = new OSNotificationGenerationJob(applicationContext);
                oSNotificationGenerationJob.setJsonPayload(bundleAsJSONObject);
                oSNotificationGenerationJob.setContext(applicationContext);
                oSNotificationGenerationJob.setNotification(oSNotification);
                NotificationBundleProcessor.processJobForDisplay(oSNotificationGenerationJob, true);
            }
        });
    }

    protected void onRegistered(String str) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
        OneSignal.Log(log_level, "ADM registration ID: " + str);
        PushRegistratorADM.fireCallback(str);
    }

    protected void onRegistrationError(String str) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
        OneSignal.Log(log_level, "ADM:onRegistrationError: " + str);
        if ("INVALID_SENDER".equals(str)) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Please double check that you have a matching package name (NOTE: Case Sensitive), api_key.txt, and the apk was signed with the same Keystore and Alias.");
        }
        PushRegistratorADM.fireCallback(null);
    }

    protected void onUnregistered(String str) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.INFO;
        OneSignal.Log(log_level, "ADM:onUnregistered: " + str);
    }
}
