package com.onesignal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import com.onesignal.OneSignal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class NotificationChannelManager {
    private static final String CHANNEL_PREFIX = "OS_";
    private static final String DEFAULT_CHANNEL_ID = "fcm_fallback_notification_channel";
    private static final String RESTORE_CHANNEL_ID = "restored_OS_notifications";
    private static final Pattern hexPattern = Pattern.compile("^([A-Fa-f0-9]{8})$");

    private static int priorityToImportance(int r2) {
        if (r2 > 9) {
            return 5;
        }
        if (r2 > 7) {
            return 4;
        }
        if (r2 > 5) {
            return 3;
        }
        if (r2 > 3) {
            return 2;
        }
        return r2 > 1 ? 1 : 0;
    }

    NotificationChannelManager() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String createNotificationChannel(OSNotificationGenerationJob oSNotificationGenerationJob) {
        if (Build.VERSION.SDK_INT < 26) {
            return "fcm_fallback_notification_channel";
        }
        Context context = oSNotificationGenerationJob.getContext();
        JSONObject jsonPayload = oSNotificationGenerationJob.getJsonPayload();
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        if (oSNotificationGenerationJob.isRestoring()) {
            return createRestoreChannel(notificationManager);
        }
        if (jsonPayload.has("oth_chnl")) {
            String optString = jsonPayload.optString("oth_chnl");
            if (notificationManager.getNotificationChannel(optString) != null) {
                return optString;
            }
        }
        if (!jsonPayload.has("chnl")) {
            return createDefaultChannel(notificationManager);
        }
        try {
            return createChannel(context, notificationManager, jsonPayload);
        } catch (JSONException e) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", e);
            return "fcm_fallback_notification_channel";
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(27:1|(1:3)(1:64)|4|(1:6)|7|(2:9|(21:11|12|(1:14)|15|(4:17|(1:19)|20|21)|25|(1:27)(1:62)|28|(1:32)|33|(1:35)(1:61)|36|(2:38|(1:40)(2:41|(1:45)))|46|(1:48)(1:60)|49|(1:51)|52|53|54|55))|63|12|(0)|15|(0)|25|(0)(0)|28|(2:30|32)|33|(0)(0)|36|(0)|46|(0)(0)|49|(0)|52|53|54|55) */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0158, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0159, code lost:
        r7.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String createChannel(android.content.Context r7, android.app.NotificationManager r8, org.json.JSONObject r9) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.NotificationChannelManager.createChannel(android.content.Context, android.app.NotificationManager, org.json.JSONObject):java.lang.String");
    }

    private static String createDefaultChannel(NotificationManager notificationManager) {
        NotificationChannel notificationChannel = new NotificationChannel("fcm_fallback_notification_channel", "Miscellaneous", 3);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);
        return "fcm_fallback_notification_channel";
    }

    private static String createRestoreChannel(NotificationManager notificationManager) {
        notificationManager.createNotificationChannel(new NotificationChannel(RESTORE_CHANNEL_ID, "Restored", 2));
        return RESTORE_CHANNEL_ID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void processChannelList(Context context, JSONArray jSONArray) {
        if (Build.VERSION.SDK_INT < 26 || jSONArray == null || jSONArray.length() == 0) {
            return;
        }
        NotificationManager notificationManager = OneSignalNotificationManager.getNotificationManager(context);
        HashSet hashSet = new HashSet();
        int length = jSONArray.length();
        for (int r3 = 0; r3 < length; r3++) {
            try {
                hashSet.add(createChannel(context, notificationManager, jSONArray.getJSONObject(r3)));
            } catch (JSONException e) {
                OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not create notification channel due to JSON payload error!", e);
            }
        }
        if (hashSet.isEmpty()) {
            return;
        }
        List<NotificationChannel> arrayList = new ArrayList<>();
        try {
            arrayList = notificationManager.getNotificationChannels();
        } catch (NullPointerException e2) {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.ERROR;
            OneSignal.onesignalLog(log_level, "Error when trying to delete notification channel: " + e2.getMessage());
        }
        for (NotificationChannel notificationChannel : arrayList) {
            String id = notificationChannel.getId();
            if (id.startsWith(CHANNEL_PREFIX) && !hashSet.contains(id)) {
                notificationManager.deleteNotificationChannel(id);
            }
        }
    }
}
