package com.onesignal;

import com.onesignal.OneSignal;

/* loaded from: classes3.dex */
class OSInAppMessageLocationPrompt extends OSInAppMessagePrompt {
    static final String LOCATION_PROMPT_KEY = "location";

    @Override // com.onesignal.OSInAppMessagePrompt
    String getPromptKey() {
        return "location";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessagePrompt
    public void handlePrompt(OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback) {
        OneSignal.promptLocation(oSPromptActionCompletionCallback, true);
    }
}
