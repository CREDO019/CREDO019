package com.onesignal;

import androidx.core.app.NotificationCompat;

/* loaded from: classes3.dex */
public class OSMutableNotification extends OSNotification {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OSMutableNotification(OSNotification oSNotification) {
        super(oSNotification);
    }

    public void setExtender(NotificationCompat.Extender extender) {
        setNotificationExtender(extender);
    }

    @Override // com.onesignal.OSNotification
    public void setAndroidNotificationId(int r1) {
        super.setAndroidNotificationId(r1);
    }
}
