package com.onesignal;

import com.onesignal.OneSignal;

/* loaded from: classes3.dex */
public class OSInAppMessagePushPrompt extends OSInAppMessagePrompt {
    static final String PUSH_PROMPT_KEY = "push";

    @Override // com.onesignal.OSInAppMessagePrompt
    String getPromptKey() {
        return PUSH_PROMPT_KEY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.OSInAppMessagePrompt
    public void handlePrompt(final OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback) {
        OneSignal.promptForPushNotifications(true, new OneSignal.PromptForPushNotificationPermissionResponseHandler() { // from class: com.onesignal.OSInAppMessagePushPrompt$$ExternalSyntheticLambda0
            @Override // com.onesignal.OneSignal.PromptForPushNotificationPermissionResponseHandler
            public final void response(boolean z) {
                OSInAppMessagePushPrompt.lambda$handlePrompt$0(OneSignal.OSPromptActionCompletionCallback.this, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handlePrompt$0(OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback, boolean z) {
        OneSignal.PromptActionResult promptActionResult;
        if (z) {
            promptActionResult = OneSignal.PromptActionResult.PERMISSION_GRANTED;
        } else {
            promptActionResult = OneSignal.PromptActionResult.PERMISSION_DENIED;
        }
        oSPromptActionCompletionCallback.onCompleted(promptActionResult);
    }
}
