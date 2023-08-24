package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class NotificationSummaryManager {
    NotificationSummaryManager() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updatePossibleDependentSummaryOnDismiss(Context context, OneSignalDb oneSignalDb, int r11) {
        String[] strArr = {OneSignalDbContract.NotificationTable.COLUMN_NAME_GROUP_ID};
        Cursor query = oneSignalDb.query(OneSignalDbContract.NotificationTable.TABLE_NAME, strArr, "android_notification_id = " + r11, null, null, null, null);
        if (query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_GROUP_ID));
            query.close();
            if (string != null) {
                updateSummaryNotificationAfterChildRemoved(context, oneSignalDb, string, true);
                return;
            }
            return;
        }
        query.close();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateSummaryNotificationAfterChildRemoved(Context context, OneSignalDb oneSignalDb, String str, boolean z) {
        try {
            Cursor internalUpdateSummaryNotificationAfterChildRemoved = internalUpdateSummaryNotificationAfterChildRemoved(context, oneSignalDb, str, z);
            if (internalUpdateSummaryNotificationAfterChildRemoved == null || internalUpdateSummaryNotificationAfterChildRemoved.isClosed()) {
                return;
            }
            internalUpdateSummaryNotificationAfterChildRemoved.close();
        } finally {
        }
    }

    private static Cursor internalUpdateSummaryNotificationAfterChildRemoved(Context context, OneSignalDb oneSignalDb, String str, boolean z) {
        Long valueOf;
        String string;
        Cursor query = oneSignalDb.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.COLUMN_NAME_CREATED_TIME, OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, null, null, "_id DESC");
        int count = query.getCount();
        if (count == 0) {
            query.close();
            Integer summaryNotificationId = getSummaryNotificationId(oneSignalDb, str);
            if (summaryNotificationId == null) {
                return query;
            }
            OneSignalNotificationManager.getNotificationManager(context).cancel(summaryNotificationId.intValue());
            ContentValues contentValues = new ContentValues();
            contentValues.put(z ? "dismissed" : "opened", (Integer) 1);
            oneSignalDb.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, "android_notification_id = " + summaryNotificationId, null);
            return query;
        } else if (count == 1) {
            query.close();
            if (getSummaryNotificationId(oneSignalDb, str) == null) {
                return query;
            }
            restoreSummary(context, str);
            return query;
        } else {
            try {
                query.moveToFirst();
                valueOf = Long.valueOf(query.getLong(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_CREATED_TIME)));
                string = query.getString(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA));
                query.close();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (getSummaryNotificationId(oneSignalDb, str) == null) {
                return query;
            }
            OSNotificationGenerationJob oSNotificationGenerationJob = new OSNotificationGenerationJob(context);
            oSNotificationGenerationJob.setRestoring(true);
            oSNotificationGenerationJob.setShownTimeStamp(valueOf);
            oSNotificationGenerationJob.setJsonPayload(new JSONObject(string));
            GenerateNotification.updateSummaryNotification(oSNotificationGenerationJob);
            return query;
        }
    }

    private static void restoreSummary(Context context, String str) {
        String[] strArr = {str};
        Cursor cursor = null;
        try {
            cursor = OneSignalDbHelper.getInstance(context).query(OneSignalDbContract.NotificationTable.TABLE_NAME, OSNotificationRestoreWorkManager.COLUMNS_FOR_RESTORE, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", strArr, null, null, null);
            OSNotificationRestoreWorkManager.showNotificationsFromCursor(context, cursor, 0);
            if (cursor == null || cursor.isClosed()) {
            }
        } catch (Throwable th) {
            try {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error restoring notification records! ", th);
                if (cursor == null) {
                    return;
                }
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
    }

    static Integer getSummaryNotificationId(OneSignalDb oneSignalDb, String str) {
        Integer num;
        Cursor query;
        Cursor cursor = null;
        try {
            query = oneSignalDb.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 1", new String[]{str}, null, null, null);
        } catch (Throwable th) {
            th = th;
            num = null;
        }
        try {
            if (!query.moveToFirst()) {
                query.close();
                if (query != null && !query.isClosed()) {
                    query.close();
                }
                return null;
            }
            Integer valueOf = Integer.valueOf(query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
            query.close();
            if (query == null || query.isClosed()) {
                return valueOf;
            }
            query.close();
            return valueOf;
        } catch (Throwable th2) {
            th = th2;
            cursor = query;
            num = null;
            try {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error getting android notification id for summary notification group: " + str, th);
                return num;
            } finally {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clearNotificationOnSummaryClick(Context context, OneSignalDbHelper oneSignalDbHelper, String str) {
        Integer summaryNotificationId = getSummaryNotificationId(oneSignalDbHelper, str);
        boolean equals = str.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        Integer mostRecentNotifIdFromGroup = OneSignalNotificationManager.getMostRecentNotifIdFromGroup(oneSignalDbHelper, str, equals);
        if (mostRecentNotifIdFromGroup != null) {
            if (!OneSignal.getClearGroupSummaryClick()) {
                OneSignal.removeNotification(mostRecentNotifIdFromGroup.intValue());
                return;
            }
            if (equals) {
                summaryNotificationId = Integer.valueOf(OneSignalNotificationManager.getGrouplessSummaryId());
            }
            if (summaryNotificationId != null) {
                notificationManager.cancel(summaryNotificationId.intValue());
            }
        }
    }
}
