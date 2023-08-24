package com.onesignal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import com.onesignal.shortcutbadger.ShortcutBadger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class BadgeCountUpdater {
    private static int badgesEnabled = -1;

    BadgeCountUpdater() {
    }

    private static boolean areBadgeSettingsEnabled(Context context) {
        int r0 = badgesEnabled;
        if (r0 != -1) {
            return r0 == 1;
        }
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                badgesEnabled = "DISABLE".equals(bundle.getString("com.onesignal.BadgeCount")) ? 0 : 1;
            } else {
                badgesEnabled = 1;
            }
        } catch (PackageManager.NameNotFoundException e) {
            badgesEnabled = 0;
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Error reading meta-data tag 'com.onesignal.BadgeCount'. Disabling badge setting.", e);
        }
        return badgesEnabled == 1;
    }

    private static boolean areBadgesEnabled(Context context) {
        return areBadgeSettingsEnabled(context) && OSUtils.areNotificationsEnabled(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void update(OneSignalDb oneSignalDb, Context context) {
        if (areBadgesEnabled(context)) {
            if (Build.VERSION.SDK_INT >= 23) {
                updateStandard(context);
            } else {
                updateFallback(oneSignalDb, context);
            }
        }
    }

    private static void updateStandard(Context context) {
        int r3 = 0;
        for (StatusBarNotification statusBarNotification : OneSignalNotificationManager.getActiveNotifications(context)) {
            if (!NotificationLimitManager.isGroupSummary(statusBarNotification)) {
                r3++;
            }
        }
        updateCount(r3, context);
    }

    private static void updateFallback(OneSignalDb oneSignalDb, Context context) {
        Cursor query = oneSignalDb.query(OneSignalDbContract.NotificationTable.TABLE_NAME, null, OneSignalDbHelper.recentUninteractedWithNotificationsWhere().toString(), null, null, null, null, NotificationLimitManager.MAX_NUMBER_OF_NOTIFICATIONS_STR);
        int count = query.getCount();
        query.close();
        updateCount(count, context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateCount(int r1, Context context) {
        if (areBadgeSettingsEnabled(context)) {
            try {
                ShortcutBadger.applyCountOrThrow(context, r1);
            } catch (ShortcutBadgeException unused) {
            }
        }
    }
}
