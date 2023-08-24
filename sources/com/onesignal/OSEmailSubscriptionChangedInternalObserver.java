package com.onesignal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSEmailSubscriptionChangedInternalObserver {
    void changed(OSEmailSubscriptionState oSEmailSubscriptionState) {
        fireChangesToPublicObserver(oSEmailSubscriptionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireChangesToPublicObserver(OSEmailSubscriptionState oSEmailSubscriptionState) {
        if (OneSignal.getEmailSubscriptionStateChangesObserver().notifyChange(new OSEmailSubscriptionStateChanges(OneSignal.lastEmailSubscriptionState, (OSEmailSubscriptionState) oSEmailSubscriptionState.clone()))) {
            OneSignal.lastEmailSubscriptionState = (OSEmailSubscriptionState) oSEmailSubscriptionState.clone();
            OneSignal.lastEmailSubscriptionState.persistAsFrom();
        }
    }
}
