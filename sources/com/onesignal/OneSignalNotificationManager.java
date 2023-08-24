package com.onesignal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.onesignal.OneSignalDbContract;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class OneSignalNotificationManager {
    private static final int GROUPLESS_SUMMARY_ID = -718463522;
    private static final String GROUPLESS_SUMMARY_KEY = "os_group_undefined";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getGrouplessSummaryId() {
        return GROUPLESS_SUMMARY_ID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getGrouplessSummaryKey() {
        return GROUPLESS_SUMMARY_KEY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Integer getGrouplessNotifsCount(Context context) {
        StatusBarNotification[] activeNotifications;
        int r2 = 0;
        for (StatusBarNotification statusBarNotification : getActiveNotifications(context)) {
            if (!NotificationCompat.isGroupSummary(statusBarNotification.getNotification()) && GROUPLESS_SUMMARY_KEY.equals(statusBarNotification.getNotification().getGroup())) {
                r2++;
            }
        }
        return Integer.valueOf(r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StatusBarNotification[] getActiveNotifications(Context context) {
        StatusBarNotification[] statusBarNotificationArr = new StatusBarNotification[0];
        try {
            return getNotificationManager(context).getActiveNotifications();
        } catch (Throwable unused) {
            return statusBarNotificationArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<StatusBarNotification> getActiveGrouplessNotifications(Context context) {
        StatusBarNotification[] activeNotifications;
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        for (StatusBarNotification statusBarNotification : getActiveNotifications(context)) {
            Notification notification = statusBarNotification.getNotification();
            boolean isGroupSummary = NotificationLimitManager.isGroupSummary(statusBarNotification);
            boolean z = notification.getGroup() == null || notification.getGroup().equals(getGrouplessSummaryKey());
            if (!isGroupSummary && z) {
                arrayList.add(statusBarNotification);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void assignGrouplessNotifications(Context context, ArrayList<StatusBarNotification> arrayList) {
        Iterator<StatusBarNotification> it = arrayList.iterator();
        while (it.hasNext()) {
            StatusBarNotification next = it.next();
            NotificationManagerCompat.from(context).notify(next.getId(), Notification.Builder.recoverBuilder(context, next.getNotification()).setGroup(GROUPLESS_SUMMARY_KEY).setOnlyAlertOnce(true).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Integer getMostRecentNotifIdFromGroup(OneSignalDbHelper oneSignalDbHelper, String str, boolean z) {
        String str2 = z ? "group_id IS NULL" : "group_id = ?";
        Cursor query = oneSignalDbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, null, str2 + " AND dismissed = 0 AND opened = 0 AND is_summary = 0", z ? null : new String[]{str}, null, null, "created_time DESC", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        if (!query.moveToFirst()) {
            query.close();
            return null;
        }
        Integer valueOf = Integer.valueOf(query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
        query.close();
        return valueOf;
    }

    public static boolean areNotificationsEnabled(Context context, String str) {
        NotificationChannel notificationChannel;
        if (NotificationManagerCompat.from(context).areNotificationsEnabled()) {
            return Build.VERSION.SDK_INT < 26 || (notificationChannel = getNotificationManager(context).getNotificationChannel(str)) == null || notificationChannel.getImportance() != 0;
        }
        return false;
    }
}
