package com.onesignal;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.messaging.Constants;
import com.onesignal.OneSignalDbContract;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class TrackFirebaseAnalytics {
    private static final String EVENT_NOTIFICATION_INFLUENCE_OPEN = "os_notification_influence_open";
    private static final String EVENT_NOTIFICATION_OPENED = "os_notification_opened";
    private static final String EVENT_NOTIFICATION_RECEIVED = "os_notification_received";
    private static Class<?> FirebaseAnalyticsClass;
    private static AtomicLong lastOpenedTime;
    private static OSNotification lastReceivedNotification;
    private static AtomicLong lastReceivedTime;
    private Context appContext;
    private Object mFirebaseAnalyticsInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackFirebaseAnalytics(Context context) {
        this.appContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean CanTrack() {
        try {
            FirebaseAnalyticsClass = Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trackInfluenceOpenEvent() {
        if (lastReceivedTime == null || lastReceivedNotification == null) {
            return;
        }
        long currentTimeMillis = OneSignal.getTime().getCurrentTimeMillis();
        if (currentTimeMillis - lastReceivedTime.get() > 120000) {
            return;
        }
        AtomicLong atomicLong = lastOpenedTime;
        if (atomicLong == null || currentTimeMillis - atomicLong.get() >= 30000) {
            try {
                Object firebaseAnalyticsInstance = getFirebaseAnalyticsInstance(this.appContext);
                Method trackMethod = getTrackMethod(FirebaseAnalyticsClass);
                Bundle bundle = new Bundle();
                bundle.putString("source", "OneSignal");
                bundle.putString(Constants.ScionAnalytics.PARAM_MEDIUM, OneSignalDbContract.NotificationTable.TABLE_NAME);
                bundle.putString("notification_id", lastReceivedNotification.getNotificationId());
                bundle.putString(Constants.ScionAnalytics.PARAM_CAMPAIGN, getCampaignNameFromNotification(lastReceivedNotification));
                trackMethod.invoke(firebaseAnalyticsInstance, EVENT_NOTIFICATION_INFLUENCE_OPEN, bundle);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trackOpenedEvent(OSNotificationOpenedResult oSNotificationOpenedResult) {
        if (lastOpenedTime == null) {
            lastOpenedTime = new AtomicLong();
        }
        lastOpenedTime.set(OneSignal.getTime().getCurrentTimeMillis());
        try {
            Object firebaseAnalyticsInstance = getFirebaseAnalyticsInstance(this.appContext);
            Method trackMethod = getTrackMethod(FirebaseAnalyticsClass);
            Bundle bundle = new Bundle();
            bundle.putString("source", "OneSignal");
            bundle.putString(Constants.ScionAnalytics.PARAM_MEDIUM, OneSignalDbContract.NotificationTable.TABLE_NAME);
            bundle.putString("notification_id", oSNotificationOpenedResult.getNotification().getNotificationId());
            bundle.putString(Constants.ScionAnalytics.PARAM_CAMPAIGN, getCampaignNameFromNotification(oSNotificationOpenedResult.getNotification()));
            trackMethod.invoke(firebaseAnalyticsInstance, EVENT_NOTIFICATION_OPENED, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void trackReceivedEvent(OSNotificationOpenedResult oSNotificationOpenedResult) {
        try {
            Object firebaseAnalyticsInstance = getFirebaseAnalyticsInstance(this.appContext);
            Method trackMethod = getTrackMethod(FirebaseAnalyticsClass);
            Bundle bundle = new Bundle();
            bundle.putString("source", "OneSignal");
            bundle.putString(Constants.ScionAnalytics.PARAM_MEDIUM, OneSignalDbContract.NotificationTable.TABLE_NAME);
            bundle.putString("notification_id", oSNotificationOpenedResult.getNotification().getNotificationId());
            bundle.putString(Constants.ScionAnalytics.PARAM_CAMPAIGN, getCampaignNameFromNotification(oSNotificationOpenedResult.getNotification()));
            trackMethod.invoke(firebaseAnalyticsInstance, EVENT_NOTIFICATION_RECEIVED, bundle);
            if (lastReceivedTime == null) {
                lastReceivedTime = new AtomicLong();
            }
            lastReceivedTime.set(OneSignal.getTime().getCurrentTimeMillis());
            lastReceivedNotification = oSNotificationOpenedResult.getNotification();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private String getCampaignNameFromNotification(OSNotification oSNotification) {
        if (oSNotification.getTemplateName().isEmpty() || oSNotification.getTemplateId().isEmpty()) {
            return oSNotification.getTitle() != null ? oSNotification.getTitle().substring(0, Math.min(10, oSNotification.getTitle().length())) : "";
        }
        return oSNotification.getTemplateName() + " - " + oSNotification.getTemplateId();
    }

    private Object getFirebaseAnalyticsInstance(Context context) {
        if (this.mFirebaseAnalyticsInstance == null) {
            try {
                this.mFirebaseAnalyticsInstance = getInstanceMethod(FirebaseAnalyticsClass).invoke(null, context);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
        return this.mFirebaseAnalyticsInstance;
    }

    private static Method getTrackMethod(Class cls) {
        try {
            return cls.getMethod("logEvent", String.class, Bundle.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Method getInstanceMethod(Class cls) {
        try {
            return cls.getMethod("getInstance", Context.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }
}
