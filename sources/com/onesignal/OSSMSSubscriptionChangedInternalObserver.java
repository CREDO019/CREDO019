package com.onesignal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSSMSSubscriptionChangedInternalObserver {
    void changed(OSSMSSubscriptionState oSSMSSubscriptionState) {
        fireChangesToPublicObserver(oSSMSSubscriptionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireChangesToPublicObserver(OSSMSSubscriptionState oSSMSSubscriptionState) {
        if (OneSignal.getSMSSubscriptionStateChangesObserver().notifyChange(new OSSMSSubscriptionStateChanges(OneSignal.lastSMSSubscriptionState, (OSSMSSubscriptionState) oSSMSSubscriptionState.clone()))) {
            OneSignal.lastSMSSubscriptionState = (OSSMSSubscriptionState) oSSMSSubscriptionState.clone();
            OneSignal.lastSMSSubscriptionState.persistAsFrom();
        }
    }
}
