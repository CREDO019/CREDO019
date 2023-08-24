package com.onesignal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSSubscriptionChangedInternalObserver {
    public void changed(OSSubscriptionState oSSubscriptionState) {
        fireChangesToPublicObserver(oSSubscriptionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireChangesToPublicObserver(OSSubscriptionState oSSubscriptionState) {
        if (OneSignal.getSubscriptionStateChangesObserver().notifyChange(new OSSubscriptionStateChanges(OneSignal.lastSubscriptionState, (OSSubscriptionState) oSSubscriptionState.clone()))) {
            OneSignal.lastSubscriptionState = (OSSubscriptionState) oSSubscriptionState.clone();
            OneSignal.lastSubscriptionState.persistAsFrom();
        }
    }
}
