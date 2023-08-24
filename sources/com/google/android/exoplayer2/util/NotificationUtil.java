package com.google.android.exoplayer2.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import com.onesignal.OneSignalDbContract;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* loaded from: classes2.dex */
public final class NotificationUtil {
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface Importance {
    }

    public static void createNotificationChannel(Context context, String str, int r4, int r5, int r6) {
        if (Util.SDK_INT >= 26) {
            NotificationManager notificationManager = (NotificationManager) Assertions.checkNotNull((NotificationManager) context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME));
            NotificationChannel notificationChannel = new NotificationChannel(str, context.getString(r4), r6);
            if (r5 != 0) {
                notificationChannel.setDescription(context.getString(r5));
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public static void setNotification(Context context, int r2, Notification notification) {
        NotificationManager notificationManager = (NotificationManager) Assertions.checkNotNull((NotificationManager) context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME));
        if (notification != null) {
            notificationManager.notify(r2, notification);
        } else {
            notificationManager.cancel(r2);
        }
    }

    private NotificationUtil() {
    }
}
