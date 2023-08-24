package com.onesignal;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.onesignal.OneSignalDbContract;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSNotificationDataController extends OSBackgroundManager {
    private static final long NOTIFICATION_CACHE_DATA_LIFETIME = 604800;
    private static final String OS_NOTIFICATIONS_THREAD = "OS_NOTIFICATIONS_THREAD";
    private final OneSignalDbHelper dbHelper;
    private final OSLogger logger;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface InvalidOrDuplicateNotificationCallback {
        void onResult(boolean z);
    }

    public OSNotificationDataController(OneSignalDbHelper oneSignalDbHelper, OSLogger oSLogger) {
        this.dbHelper = oneSignalDbHelper;
        this.logger = oSLogger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanOldCachedData() {
        cleanNotificationCache();
    }

    private void cleanNotificationCache() {
        runRunnableOnThread(new BackgroundRunnable() { // from class: com.onesignal.OSNotificationDataController.1
            @Override // com.onesignal.BackgroundRunnable, java.lang.Runnable
            public void run() {
                super.run();
                OSNotificationDataController.this.dbHelper.delete(OneSignalDbContract.NotificationTable.TABLE_NAME, "created_time < ?", new String[]{String.valueOf((OneSignal.getTime().getCurrentTimeMillis() / 1000) - OSNotificationDataController.NOTIFICATION_CACHE_DATA_LIFETIME)});
            }
        }, OS_NOTIFICATIONS_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearOneSignalNotifications(final WeakReference<Context> weakReference) {
        runRunnableOnThread(new BackgroundRunnable() { // from class: com.onesignal.OSNotificationDataController.2
            @Override // com.onesignal.BackgroundRunnable, java.lang.Runnable
            public void run() {
                super.run();
                Context context = (Context) weakReference.get();
                if (context == null) {
                    return;
                }
                NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
                Cursor query = OSNotificationDataController.this.dbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID}, "dismissed = 0 AND opened = 0", null, null, null, null);
                if (query.moveToFirst()) {
                    do {
                        notificationManager.cancel(query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
                    } while (query.moveToNext());
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("dismissed", (Integer) 1);
                    OSNotificationDataController.this.dbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, "opened = 0", null);
                    BadgeCountUpdater.updateCount(0, context);
                    query.close();
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("dismissed", (Integer) 1);
                OSNotificationDataController.this.dbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues2, "opened = 0", null);
                BadgeCountUpdater.updateCount(0, context);
                query.close();
            }
        }, OS_NOTIFICATIONS_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeGroupedNotifications(final String str, final WeakReference<Context> weakReference) {
        runRunnableOnThread(new BackgroundRunnable() { // from class: com.onesignal.OSNotificationDataController.3
            @Override // com.onesignal.BackgroundRunnable, java.lang.Runnable
            public void run() {
                super.run();
                Context context = (Context) weakReference.get();
                if (context == null) {
                    return;
                }
                NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
                String[] strArr = {OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID};
                String[] strArr2 = {str};
                Cursor query = OSNotificationDataController.this.dbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, strArr, "group_id = ? AND dismissed = 0 AND opened = 0", strArr2, null, null, null);
                while (query.moveToNext()) {
                    int r4 = query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID));
                    if (r4 != -1) {
                        notificationManager.cancel(r4);
                    }
                }
                query.close();
                ContentValues contentValues = new ContentValues();
                contentValues.put("dismissed", (Integer) 1);
                OSNotificationDataController.this.dbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, "group_id = ? AND opened = 0 AND dismissed = 0", strArr2);
                BadgeCountUpdater.update(OSNotificationDataController.this.dbHelper, context);
            }
        }, OS_NOTIFICATIONS_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeNotification(final int r2, final WeakReference<Context> weakReference) {
        runRunnableOnThread(new BackgroundRunnable() { // from class: com.onesignal.OSNotificationDataController.4
            @Override // com.onesignal.BackgroundRunnable, java.lang.Runnable
            public void run() {
                super.run();
                Context context = (Context) weakReference.get();
                if (context == null) {
                    return;
                }
                String str = "android_notification_id = " + r2 + " AND opened = 0 AND dismissed = 0";
                ContentValues contentValues = new ContentValues();
                contentValues.put("dismissed", (Integer) 1);
                if (OSNotificationDataController.this.dbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, contentValues, str, null) > 0) {
                    NotificationSummaryManager.updatePossibleDependentSummaryOnDismiss(context, OSNotificationDataController.this.dbHelper, r2);
                }
                BadgeCountUpdater.update(OSNotificationDataController.this.dbHelper, context);
                OneSignalNotificationManager.getNotificationManager(context).cancel(r2);
            }
        }, OS_NOTIFICATIONS_THREAD);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notValidOrDuplicated(JSONObject jSONObject, InvalidOrDuplicateNotificationCallback invalidOrDuplicateNotificationCallback) {
        String oSNotificationIdFromJson = OSNotificationFormatHelper.getOSNotificationIdFromJson(jSONObject);
        if (oSNotificationIdFromJson == null) {
            this.logger.debug("Notification notValidOrDuplicated with id null");
            invalidOrDuplicateNotificationCallback.onResult(true);
            return;
        }
        isDuplicateNotification(oSNotificationIdFromJson, invalidOrDuplicateNotificationCallback);
    }

    private void isDuplicateNotification(final String str, final InvalidOrDuplicateNotificationCallback invalidOrDuplicateNotificationCallback) {
        if (str == null || "".equals(str)) {
            invalidOrDuplicateNotificationCallback.onResult(false);
        } else if (!OSNotificationWorkManager.addNotificationIdProcessed(str)) {
            this.logger.debug("Notification notValidOrDuplicated with id duplicated");
            invalidOrDuplicateNotificationCallback.onResult(true);
        } else {
            runRunnableOnThread(new BackgroundRunnable() { // from class: com.onesignal.OSNotificationDataController.5
                @Override // com.onesignal.BackgroundRunnable, java.lang.Runnable
                public void run() {
                    super.run();
                    boolean z = true;
                    Cursor query = OSNotificationDataController.this.dbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{"notification_id"}, "notification_id = ?", new String[]{str}, null, null, null);
                    boolean moveToFirst = query.moveToFirst();
                    query.close();
                    if (moveToFirst) {
                        OSLogger oSLogger = OSNotificationDataController.this.logger;
                        oSLogger.debug("Notification notValidOrDuplicated with id duplicated, duplicate FCM message received, skip processing of " + str);
                    } else {
                        z = false;
                    }
                    invalidOrDuplicateNotificationCallback.onResult(z);
                }
            }, OS_NOTIFICATIONS_THREAD);
        }
    }
}
