package com.onesignal;

import com.onesignal.OneSignal;

/* loaded from: classes3.dex */
public abstract class OSInAppMessageLifecycleHandler {
    public void onWillDisplayInAppMessage(OSInAppMessage oSInAppMessage) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, "OSInAppMessageLifecycleHandler: IAM Will Display: " + oSInAppMessage.getMessageId());
    }

    public void onDidDisplayInAppMessage(OSInAppMessage oSInAppMessage) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, "OSInAppMessageLifecycleHandler: IAM Did Display: " + oSInAppMessage.getMessageId());
    }

    public void onWillDismissInAppMessage(OSInAppMessage oSInAppMessage) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, "OSInAppMessageLifecycleHandler: IAM Will Dismiss: " + oSInAppMessage.getMessageId());
    }

    public void onDidDismissInAppMessage(OSInAppMessage oSInAppMessage) {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.VERBOSE;
        OneSignal.Log(log_level, "OSInAppMessageLifecycleHandler: IAM Did Dismiss: " + oSInAppMessage.getMessageId());
    }
}
