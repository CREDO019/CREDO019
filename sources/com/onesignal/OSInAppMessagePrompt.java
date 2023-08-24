package com.onesignal;

import com.onesignal.OneSignal;

/* loaded from: classes3.dex */
public abstract class OSInAppMessagePrompt {
    private boolean prompted = false;

    abstract String getPromptKey();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void handlePrompt(OneSignal.OSPromptActionCompletionCallback oSPromptActionCompletionCallback);

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPrompted() {
        return this.prompted;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPrompted(boolean z) {
        this.prompted = z;
    }

    public String toString() {
        return "OSInAppMessagePrompt{key=" + getPromptKey() + " prompted=" + this.prompted + '}';
    }
}
