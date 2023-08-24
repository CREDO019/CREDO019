package com.onesignal;

import org.json.JSONException;

/* loaded from: classes3.dex */
class UserStatePush extends UserState {
    /* JADX INFO: Access modifiers changed from: package-private */
    public UserStatePush(String str, boolean z) {
        super(str, z);
    }

    @Override // com.onesignal.UserState
    UserState newInstance(String str) {
        return new UserStatePush(str, false);
    }

    @Override // com.onesignal.UserState
    protected void addDependFields() {
        try {
            putOnSyncValues("notification_types", Integer.valueOf(getNotificationTypes()));
        } catch (JSONException unused) {
        }
    }

    private int getNotificationTypes() {
        int optInt = getDependValues().optInt("subscribableStatus", 1);
        if (optInt < -2) {
            return optInt;
        }
        if (getDependValues().optBoolean("androidPermission", true)) {
            return !getDependValues().optBoolean("userSubscribePref", true) ? -2 : 1;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.onesignal.UserState
    public boolean isSubscribed() {
        return getNotificationTypes() > 0;
    }
}
