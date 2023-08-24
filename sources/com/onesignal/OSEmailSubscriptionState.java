package com.onesignal;

import androidx.autofill.HintConstants;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class OSEmailSubscriptionState implements Cloneable {
    private static final String CHANGED_KEY = "changed";
    private String emailAddress;
    private String emailUserId;
    private OSObservable<Object, OSEmailSubscriptionState> observable = new OSObservable<>(CHANGED_KEY, false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSEmailSubscriptionState(boolean z) {
        if (z) {
            this.emailUserId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_ONESIGNAL_EMAIL_ID_LAST, null);
            this.emailAddress = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST, null);
            return;
        }
        this.emailUserId = OneSignal.getEmailId();
        this.emailAddress = OneSignalStateSynchronizer.getEmailStateSynchronizer().getRegistrationId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearEmailAndId() {
        boolean z = (this.emailUserId == null && this.emailAddress == null) ? false : true;
        this.emailUserId = null;
        this.emailAddress = null;
        if (z) {
            this.observable.notifyChange(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEmailUserId(String str) {
        boolean z = true;
        if (str != null ? str.equals(this.emailUserId) : this.emailUserId == null) {
            z = false;
        }
        this.emailUserId = str;
        if (z) {
            this.observable.notifyChange(this);
        }
    }

    public String getEmailUserId() {
        return this.emailUserId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEmailAddress(String str) {
        boolean z = !str.equals(this.emailAddress);
        this.emailAddress = str;
        if (z) {
            this.observable.notifyChange(this);
        }
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public boolean isSubscribed() {
        return (this.emailUserId == null || this.emailAddress == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void persistAsFrom() {
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_ONESIGNAL_EMAIL_ID_LAST, this.emailUserId);
        OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_ONESIGNAL_EMAIL_ADDRESS_LAST, this.emailAddress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean compare(OSEmailSubscriptionState oSEmailSubscriptionState) {
        String str = this.emailUserId;
        if (str == null) {
            str = "";
        }
        String str2 = oSEmailSubscriptionState.emailUserId;
        if (str2 == null) {
            str2 = "";
        }
        if (str.equals(str2)) {
            String str3 = this.emailAddress;
            if (str3 == null) {
                str3 = "";
            }
            String str4 = oSEmailSubscriptionState.emailAddress;
            if (str3.equals(str4 != null ? str4 : "")) {
                return false;
            }
        }
        return true;
    }

    public OSObservable<Object, OSEmailSubscriptionState> getObservable() {
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
            String str = this.emailUserId;
            if (str != null) {
                jSONObject.put("emailUserId", str);
            } else {
                jSONObject.put("emailUserId", JSONObject.NULL);
            }
            String str2 = this.emailAddress;
            if (str2 != null) {
                jSONObject.put(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS, str2);
            } else {
                jSONObject.put(HintConstants.AUTOFILL_HINT_EMAIL_ADDRESS, JSONObject.NULL);
            }
            jSONObject.put("isSubscribed", isSubscribed());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
