package com.onesignal;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class NotificationLimitManager {
    private static final int MAX_NUMBER_OF_NOTIFICATIONS_INT = 49;
    static final String MAX_NUMBER_OF_NOTIFICATIONS_STR = Integer.toString(49);

    private static int getMaxNumberOfNotificationsInt() {
        return 49;
    }

    NotificationLimitManager() {
    }

    private static String getMaxNumberOfNotificationsString() {
        return MAX_NUMBER_OF_NOTIFICATIONS_STR;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearOldestOverLimit(Context context, int r3) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                clearOldestOverLimitStandard(context, r3);
            } else {
                clearOldestOverLimitFallback(context, r3);
            }
        } catch (Throwable unused) {
            clearOldestOverLimitFallback(context, r3);
        }
    }

    static void clearOldestOverLimitStandard(Context context, int r7) throws Throwable {
        StatusBarNotification[] activeNotifications = OneSignalNotificationManager.getActiveNotifications(context);
        int length = (activeNotifications.length - getMaxNumberOfNotificationsInt()) + r7;
        if (length < 1) {
            return;
        }
        TreeMap treeMap = new TreeMap();
        for (StatusBarNotification statusBarNotification : activeNotifications) {
            if (!isGroupSummary(statusBarNotification)) {
                treeMap.put(Long.valueOf(statusBarNotification.getNotification().when), Integer.valueOf(statusBarNotification.getId()));
            }
        }
        for (Map.Entry entry : treeMap.entrySet()) {
            OneSignal.removeNotification(((Integer) entry.getValue()).intValue());
            length--;
            if (length <= 0) {
                return;
            }
        }
    }

    static void clearOldestOverLimitFallback(Context context, int r12) {
        int count;
        boolean isClosed;
        OneSignalDbHelper oneSignalDbHelper = OneSignalDbHelper.getInstance(context);
        Cursor cursor = null;
        try {
            cursor = oneSignalDbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID}, OneSignalDbHelper.recentUninteractedWithNotificationsWhere().toString(), null, null, null, "_id", getMaxNumberOfNotificationsString() + r12);
            count = (cursor.getCount() - getMaxNumberOfNotificationsInt()) + r12;
        } catch (Throwable th) {
            try {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error clearing oldest notifications over limit! ", th);
                if (cursor == null || cursor.isClosed()) {
                    return;
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
        if (count < 1) {
            if (cursor != null) {
                if (isClosed) {
                    return;
                }
                return;
            }
            return;
        }
        while (cursor.moveToNext()) {
            OneSignal.removeNotification(cursor.getInt(cursor.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
            count--;
            if (count <= 0) {
                break;
            }
        }
        if (cursor == null || cursor.isClosed()) {
            return;
        }
        cursor.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isGroupSummary(StatusBarNotification statusBarNotification) {
        return (statusBarNotification.getNotification().flags & 512) != 0;
    }
}
