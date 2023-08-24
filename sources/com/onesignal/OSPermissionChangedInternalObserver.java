package com.onesignal;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSPermissionChangedInternalObserver {
    void changed(OSPermissionState oSPermissionState) {
        handleInternalChanges(oSPermissionState);
        fireChangesToPublicObserver(oSPermissionState);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void handleInternalChanges(OSPermissionState oSPermissionState) {
        if (!oSPermissionState.areNotificationsEnabled()) {
            BadgeCountUpdater.updateCount(0, OneSignal.appContext);
        }
        OneSignalStateSynchronizer.setPermission(OneSignal.areNotificationsEnabledForSubscribedState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireChangesToPublicObserver(OSPermissionState oSPermissionState) {
        if (OneSignal.getPermissionStateChangesObserver().notifyChange(new OSPermissionStateChanges(OneSignal.lastPermissionState, (OSPermissionState) oSPermissionState.clone()))) {
            OneSignal.lastPermissionState = (OSPermissionState) oSPermissionState.clone();
            OneSignal.lastPermissionState.persistAsFrom();
        }
    }
}
