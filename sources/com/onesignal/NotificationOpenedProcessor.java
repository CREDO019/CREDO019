package com.onesignal;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import org.bouncycastle.i18n.ErrorBundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class NotificationOpenedProcessor {
    private static final String TAG = "com.onesignal.NotificationOpenedProcessor";

    NotificationOpenedProcessor() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processFromContext(Context context, Intent intent) {
        if (isOneSignalIntent(intent)) {
            if (context != null) {
                OneSignal.initWithContext(context.getApplicationContext());
            }
            handleDismissFromActionButtonPress(context, intent);
            processIntent(context, intent);
        }
    }

    private static boolean isOneSignalIntent(Intent intent) {
        return intent.hasExtra(GenerateNotification.BUNDLE_KEY_ONESIGNAL_DATA) || intent.hasExtra(ErrorBundle.SUMMARY_ENTRY) || intent.hasExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID);
    }

    private static void handleDismissFromActionButtonPress(Context context, Intent intent) {
        if (intent.getBooleanExtra("action_button", false)) {
            NotificationManagerCompat.from(context).cancel(intent.getIntExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, 0));
            if (Build.VERSION.SDK_INT < 31) {
                context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            }
        }
    }

    static void processIntent(Context context, Intent intent) {
        OSNotificationIntentExtras oSNotificationIntentExtras;
        String stringExtra;
        OneSignalDbHelper oneSignalDbHelper = OneSignalDbHelper.getInstance(context);
        String stringExtra2 = intent.getStringExtra(ErrorBundle.SUMMARY_ENTRY);
        boolean booleanExtra = intent.getBooleanExtra("dismissed", false);
        if (booleanExtra) {
            oSNotificationIntentExtras = null;
        } else {
            oSNotificationIntentExtras = processToOpenIntent(context, intent, oneSignalDbHelper, stringExtra2);
            if (oSNotificationIntentExtras == null) {
                return;
            }
        }
        markNotificationsConsumed(context, intent, oneSignalDbHelper, booleanExtra);
        if (stringExtra2 == null && (stringExtra = intent.getStringExtra("grp")) != null) {
            NotificationSummaryManager.updateSummaryNotificationAfterChildRemoved(context, oneSignalDbHelper, stringExtra, booleanExtra);
        }
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.onesignalLog(log_level, "processIntent from context: " + context + " and intent: " + intent);
        if (intent.getExtras() != null) {
            OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.onesignalLog(log_level2, "processIntent intent extras: " + intent.getExtras().toString());
        }
        if (booleanExtra) {
            return;
        }
        if (!(context instanceof Activity)) {
            OneSignal.LOG_LEVEL log_level3 = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.onesignalLog(log_level3, "NotificationOpenedProcessor processIntent from an non Activity context: " + context);
            return;
        }
        OneSignal.handleNotificationOpen((Activity) context, oSNotificationIntentExtras.getDataArray(), OSNotificationFormatHelper.getOSNotificationIdFromJson(oSNotificationIntentExtras.getJsonData()));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.onesignal.OSNotificationIntentExtras processToOpenIntent(android.content.Context r7, android.content.Intent r8, com.onesignal.OneSignalDbHelper r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "androidNotificationId"
            java.lang.String r1 = "onesignalData"
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L51
            java.lang.String r4 = r8.getStringExtra(r1)     // Catch: org.json.JSONException -> L51
            r3.<init>(r4)     // Catch: org.json.JSONException -> L51
            boolean r4 = r7 instanceof android.app.Activity     // Catch: org.json.JSONException -> L4f
            if (r4 != 0) goto L29
            com.onesignal.OneSignal$LOG_LEVEL r4 = com.onesignal.OneSignal.LOG_LEVEL.ERROR     // Catch: org.json.JSONException -> L4f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: org.json.JSONException -> L4f
            r5.<init>()     // Catch: org.json.JSONException -> L4f
            java.lang.String r6 = "NotificationOpenedProcessor processIntent from an non Activity context: "
            r5.append(r6)     // Catch: org.json.JSONException -> L4f
            r5.append(r7)     // Catch: org.json.JSONException -> L4f
            java.lang.String r7 = r5.toString()     // Catch: org.json.JSONException -> L4f
            com.onesignal.OneSignal.onesignalLog(r4, r7)     // Catch: org.json.JSONException -> L4f
            goto L32
        L29:
            android.app.Activity r7 = (android.app.Activity) r7     // Catch: org.json.JSONException -> L4f
            boolean r7 = com.onesignal.OSInAppMessagePreviewHandler.notificationOpened(r7, r3)     // Catch: org.json.JSONException -> L4f
            if (r7 == 0) goto L32
            return r2
        L32:
            r7 = 0
            int r7 = r8.getIntExtra(r0, r7)     // Catch: org.json.JSONException -> L4f
            r3.put(r0, r7)     // Catch: org.json.JSONException -> L4f
            java.lang.String r7 = r3.toString()     // Catch: org.json.JSONException -> L4f
            r8.putExtra(r1, r7)     // Catch: org.json.JSONException -> L4f
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: org.json.JSONException -> L4f
            java.lang.String r8 = r8.getStringExtra(r1)     // Catch: org.json.JSONException -> L4f
            r7.<init>(r8)     // Catch: org.json.JSONException -> L4f
            org.json.JSONArray r2 = com.onesignal.NotificationBundleProcessor.newJsonArray(r7)     // Catch: org.json.JSONException -> L4f
            goto L56
        L4f:
            r7 = move-exception
            goto L53
        L51:
            r7 = move-exception
            r3 = r2
        L53:
            r7.printStackTrace()
        L56:
            if (r10 == 0) goto L5b
            addChildNotifications(r2, r10, r9)
        L5b:
            com.onesignal.OSNotificationIntentExtras r7 = new com.onesignal.OSNotificationIntentExtras
            r7.<init>(r2, r3)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationOpenedProcessor.processToOpenIntent(android.content.Context, android.content.Intent, com.onesignal.OneSignalDbHelper, java.lang.String):com.onesignal.OSNotificationIntentExtras");
    }

    private static void addChildNotifications(JSONArray jSONArray, String str, OneSignalDbHelper oneSignalDbHelper) {
        Cursor query = oneSignalDbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, new String[]{OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA}, "group_id = ? AND dismissed = 0 AND opened = 0 AND is_summary = 0", new String[]{str}, null, null, null);
        if (query.getCount() > 1) {
            query.moveToFirst();
            do {
                try {
                    jSONArray.put(new JSONObject(query.getString(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA))));
                } catch (JSONException unused) {
                    OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
                    OneSignal.Log(log_level, "Could not parse JSON of sub notification in group: " + str);
                }
            } while (query.moveToNext());
            query.close();
        }
        query.close();
    }

    private static void markNotificationsConsumed(Context context, Intent intent, OneSignalDbHelper oneSignalDbHelper, boolean z) {
        String str;
        String stringExtra = intent.getStringExtra(ErrorBundle.SUMMARY_ENTRY);
        String[] strArr = null;
        if (stringExtra != null) {
            boolean equals = stringExtra.equals(OneSignalNotificationManager.getGrouplessSummaryKey());
            if (equals) {
                str = "group_id IS NULL";
            } else {
                strArr = new String[]{stringExtra};
                str = "group_id = ?";
            }
            if (!z && !OneSignal.getClearGroupSummaryClick()) {
                String valueOf = String.valueOf(OneSignalNotificationManager.getMostRecentNotifIdFromGroup(oneSignalDbHelper, stringExtra, equals));
                str = str + " AND android_notification_id = ?";
                strArr = equals ? new String[]{valueOf} : new String[]{stringExtra, valueOf};
            }
        } else {
            str = "android_notification_id = " + intent.getIntExtra(GenerateNotification.BUNDLE_KEY_ANDROID_NOTIFICATION_ID, 0);
        }
        clearStatusBarNotifications(context, oneSignalDbHelper, stringExtra);
        oneSignalDbHelper.update(OneSignalDbContract.NotificationTable.TABLE_NAME, newContentValuesWithConsumed(intent), str, strArr);
        BadgeCountUpdater.update(oneSignalDbHelper, context);
    }

    private static void clearStatusBarNotifications(Context context, OneSignalDbHelper oneSignalDbHelper, String str) {
        if (str != null) {
            NotificationSummaryManager.clearNotificationOnSummaryClick(context, oneSignalDbHelper, str);
        } else if (Build.VERSION.SDK_INT < 23 || OneSignalNotificationManager.getGrouplessNotifsCount(context).intValue() >= 1) {
        } else {
            OneSignalNotificationManager.getNotificationManager(context).cancel(OneSignalNotificationManager.getGrouplessSummaryId());
        }
    }

    private static ContentValues newContentValuesWithConsumed(Intent intent) {
        ContentValues contentValues = new ContentValues();
        if (intent.getBooleanExtra("dismissed", false)) {
            contentValues.put("dismissed", (Integer) 1);
        } else {
            contentValues.put("opened", (Integer) 1);
        }
        return contentValues;
    }
}
