package com.onesignal;

import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSPermissionState implements Cloneable {
    private static final String ARE_NOTIFICATION_ENABLED_KEY = "areNotificationsEnabled";
    private static final String CHANGED_KEY = "changed";
    private boolean notificationsEnabled;
    private OSObservable<Object, OSPermissionState> observable = new OSObservable<>(CHANGED_KEY, false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSPermissionState(boolean z) {
        if (z) {
            this.notificationsEnabled = OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_ONESIGNAL_ACCEPTED_NOTIFICATION_LAST, false);
        } else {
            refreshAsTo();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void refreshAsTo() {
        setNotificationsEnabled(OSUtils.areNotificationsEnabled(OneSignal.appContext));
    }

    public boolean areNotificationsEnabled() {
        return this.notificationsEnabled;
    }

    private void setNotificationsEnabled(boolean z) {
        boolean z2 = this.notificationsEnabled != z;
        this.notificationsEnabled = z;
        if (z2) {
            this.observable.notifyChange(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void persistAsFrom() {
        OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_ONESIGNAL_ACCEPTED_NOTIFICATION_LAST, this.notificationsEnabled);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean compare(OSPermissionState oSPermissionState) {
        return this.notificationsEnabled != oSPermissionState.notificationsEnabled;
    }

    public OSObservable<Object, OSPermissionState> getObservable() {
        return this.observable;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object clone() {
        try {
            return super.clone();
        } catch (Throwable unused) {
            return null;
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ARE_NOTIFICATION_ENABLED_KEY, this.notificationsEnabled);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
