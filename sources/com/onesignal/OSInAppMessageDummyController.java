package com.onesignal;

import com.onesignal.language.LanguageContext;
import java.util.Collection;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSInAppMessageDummyController extends OSInAppMessageController {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void addTriggers(Map<String, Object> map) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void cleanCachedInAppMessages() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void displayPreviewMessage(String str) {
    }

    @Override // com.onesignal.OSInAppMessageController
    OSInAppMessageInternal getCurrentDisplayedInAppMessage() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public Object getTriggerValue(String str) {
        return null;
    }

    @Override // com.onesignal.OSInAppMessageController
    public void initRedisplayData() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void initWithCachedInAppMessages() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public boolean isInAppMessageShowing() {
        return false;
    }

    @Override // com.onesignal.OSInAppMessageController, com.onesignal.OSDynamicTriggerController.OSDynamicTriggerControllerObserver
    public void messageTriggerConditionChanged() {
    }

    @Override // com.onesignal.OSInAppMessageController
    public void messageWasDismissed(OSInAppMessageInternal oSInAppMessageInternal) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void onMessageActionOccurredOnMessage(OSInAppMessageInternal oSInAppMessageInternal, JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void onMessageActionOccurredOnPreview(OSInAppMessageInternal oSInAppMessageInternal, JSONObject jSONObject) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void receivedInAppMessageJson(JSONArray jSONArray) throws JSONException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void removeTriggersForKeys(Collection<String> collection) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessageController
    public void setInAppMessagingEnabled(boolean z) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSInAppMessageDummyController(OneSignalDbHelper oneSignalDbHelper, OSTaskController oSTaskController, OSLogger oSLogger, OSSharedPreferences oSSharedPreferences, LanguageContext languageContext) {
        super(oneSignalDbHelper, oSTaskController, oSLogger, oSSharedPreferences, languageContext);
    }
}
