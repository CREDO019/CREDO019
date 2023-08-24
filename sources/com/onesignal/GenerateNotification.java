package com.onesignal;

import android.R;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.onesignal.AndroidSupportV4Compat;
import com.onesignal.OSThrowable;
import com.onesignal.OneSignal;
import com.onesignal.OneSignalDbContract;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.i18n.ErrorBundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class GenerateNotification {
    public static final String BUNDLE_KEY_ACTION_ID = "actionId";
    public static final String BUNDLE_KEY_ANDROID_NOTIFICATION_ID = "androidNotificationId";
    public static final String BUNDLE_KEY_ONESIGNAL_DATA = "onesignalData";
    public static final String OS_SHOW_NOTIFICATION_THREAD = "OS_SHOW_NOTIFICATION_THREAD";
    private static Class<?> notificationOpenedClass = NotificationOpenedReceiver.class;
    private static Class<?> notificationDismissedClass = NotificationDismissReceiver.class;
    private static Resources contextResources = null;
    private static Context currentContext = null;
    private static String packageName = null;
    private static Integer groupAlertBehavior = null;

    private static int convertOSToAndroidPriority(int r2) {
        if (r2 > 9) {
            return 2;
        }
        if (r2 > 7) {
            return 1;
        }
        if (r2 > 4) {
            return 0;
        }
        return r2 > 2 ? -1 : -2;
    }

    GenerateNotification() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class OneSignalNotificationBuilder {
        NotificationCompat.Builder compatBuilder;
        boolean hasLargeIcon;

        private OneSignalNotificationBuilder() {
        }
    }

    private static void initGroupAlertBehavior() {
        if (Build.VERSION.SDK_INT >= 24) {
            groupAlertBehavior = 2;
        } else {
            groupAlertBehavior = 1;
        }
    }

    private static void setStatics(Context context) {
        currentContext = context;
        packageName = context.getPackageName();
        contextResources = currentContext.getResources();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean displayNotification(OSNotificationGenerationJob oSNotificationGenerationJob) {
        setStatics(oSNotificationGenerationJob.getContext());
        isRunningOnMainThreadCheck();
        initGroupAlertBehavior();
        return showNotification(oSNotificationGenerationJob);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean displayIAMPreviewNotification(OSNotificationGenerationJob oSNotificationGenerationJob) {
        setStatics(oSNotificationGenerationJob.getContext());
        return showNotification(oSNotificationGenerationJob);
    }

    static void isRunningOnMainThreadCheck() {
        if (OSUtils.isRunningOnMainThread()) {
            throw new OSThrowable.OSMainThreadException("Process for showing a notification should never been done on Main Thread!");
        }
    }

    private static CharSequence getTitle(JSONObject jSONObject) {
        String optString = jSONObject.optString("title", null);
        return optString != null ? optString : currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo());
    }

    private static PendingIntent getNewDismissActionPendingIntent(int r2, Intent intent) {
        return PendingIntent.getBroadcast(currentContext, r2, intent, 201326592);
    }

    private static Intent getNewBaseDismissIntent(int r3) {
        return new Intent(currentContext, notificationDismissedClass).putExtra(BUNDLE_KEY_ANDROID_NOTIFICATION_ID, r3).putExtra("dismissed", true);
    }

    private static OneSignalNotificationBuilder getBaseOneSignalNotificationBuilder(OSNotificationGenerationJob oSNotificationGenerationJob) {
        NotificationCompat.Builder builder;
        JSONObject jsonPayload = oSNotificationGenerationJob.getJsonPayload();
        OneSignalNotificationBuilder oneSignalNotificationBuilder = new OneSignalNotificationBuilder();
        try {
            builder = new NotificationCompat.Builder(currentContext, NotificationChannelManager.createNotificationChannel(oSNotificationGenerationJob));
        } catch (Throwable unused) {
            builder = new NotificationCompat.Builder(currentContext);
        }
        String optString = jsonPayload.optString("alert", null);
        builder.setAutoCancel(true).setSmallIcon(getSmallIconId(jsonPayload)).setStyle(new NotificationCompat.BigTextStyle().bigText(optString)).setContentText(optString).setTicker(optString);
        if (Build.VERSION.SDK_INT < 24 || !jsonPayload.optString("title").equals("")) {
            builder.setContentTitle(getTitle(jsonPayload));
        }
        try {
            BigInteger accentColor = getAccentColor(jsonPayload);
            if (accentColor != null) {
                builder.setColor(accentColor.intValue());
            }
        } catch (Throwable unused2) {
        }
        try {
            builder.setVisibility(jsonPayload.has("vis") ? Integer.parseInt(jsonPayload.optString("vis")) : 1);
        } catch (Throwable unused3) {
        }
        Bitmap largeIcon = getLargeIcon(jsonPayload);
        if (largeIcon != null) {
            oneSignalNotificationBuilder.hasLargeIcon = true;
            builder.setLargeIcon(largeIcon);
        }
        Bitmap bitmap = getBitmap(jsonPayload.optString("bicon", null));
        if (bitmap != null) {
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText(optString));
        }
        if (oSNotificationGenerationJob.getShownTimeStamp() != null) {
            try {
                builder.setWhen(oSNotificationGenerationJob.getShownTimeStamp().longValue() * 1000);
            } catch (Throwable unused4) {
            }
        }
        setAlertnessOptions(jsonPayload, builder);
        oneSignalNotificationBuilder.compatBuilder = builder;
        return oneSignalNotificationBuilder;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void setAlertnessOptions(org.json.JSONObject r6, androidx.core.app.NotificationCompat.Builder r7) {
        /*
            java.lang.String r0 = "pri"
            r1 = 6
            int r0 = r6.optInt(r0, r1)
            int r0 = convertOSToAndroidPriority(r0)
            r7.setPriority(r0)
            r1 = 0
            r2 = 1
            if (r0 >= 0) goto L14
            r0 = 1
            goto L15
        L14:
            r0 = 0
        L15:
            if (r0 == 0) goto L18
            return
        L18:
            java.lang.String r0 = "ledc"
            boolean r3 = r6.has(r0)
            r4 = 4
            if (r3 == 0) goto L40
            java.lang.String r3 = "led"
            int r3 = r6.optInt(r3, r2)
            if (r3 != r2) goto L40
            java.math.BigInteger r3 = new java.math.BigInteger     // Catch: java.lang.Throwable -> L40
            java.lang.String r0 = r6.optString(r0)     // Catch: java.lang.Throwable -> L40
            r5 = 16
            r3.<init>(r0, r5)     // Catch: java.lang.Throwable -> L40
            int r0 = r3.intValue()     // Catch: java.lang.Throwable -> L40
            r3 = 2000(0x7d0, float:2.803E-42)
            r5 = 5000(0x1388, float:7.006E-42)
            r7.setLights(r0, r3, r5)     // Catch: java.lang.Throwable -> L40
            goto L41
        L40:
            r1 = 4
        L41:
            java.lang.String r0 = "vib"
            int r0 = r6.optInt(r0, r2)
            if (r0 != r2) goto L5d
            java.lang.String r0 = "vib_pt"
            boolean r0 = r6.has(r0)
            if (r0 == 0) goto L5b
            long[] r0 = com.onesignal.OSUtils.parseVibrationPattern(r6)
            if (r0 == 0) goto L5d
            r7.setVibrate(r0)
            goto L5d
        L5b:
            r1 = r1 | 2
        L5d:
            boolean r0 = isSoundEnabled(r6)
            if (r0 == 0) goto L78
            android.content.Context r0 = com.onesignal.GenerateNotification.currentContext
            r2 = 0
            java.lang.String r3 = "sound"
            java.lang.String r6 = r6.optString(r3, r2)
            android.net.Uri r6 = com.onesignal.OSUtils.getSoundUri(r0, r6)
            if (r6 == 0) goto L76
            r7.setSound(r6)
            goto L78
        L76:
            r1 = r1 | 1
        L78:
            r7.setDefaults(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.GenerateNotification.setAlertnessOptions(org.json.JSONObject, androidx.core.app.NotificationCompat$Builder):void");
    }

    private static void removeNotifyOptions(NotificationCompat.Builder builder) {
        builder.setOnlyAlertOnce(true).setDefaults(0).setSound(null).setVibrate(null).setTicker(null);
    }

    private static boolean showNotification(OSNotificationGenerationJob oSNotificationGenerationJob) {
        Notification createGenericPendingIntentsForNotif;
        int intValue = oSNotificationGenerationJob.getAndroidId().intValue();
        JSONObject jsonPayload = oSNotificationGenerationJob.getJsonPayload();
        String optString = jsonPayload.optString("grp", null);
        IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications = new IntentGeneratorForAttachingToNotifications(currentContext);
        ArrayList<StatusBarNotification> arrayList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList = OneSignalNotificationManager.getActiveGrouplessNotifications(currentContext);
            if (optString == null && arrayList.size() >= 3) {
                optString = OneSignalNotificationManager.getGrouplessSummaryKey();
                OneSignalNotificationManager.assignGrouplessNotifications(currentContext, arrayList);
            }
        }
        OneSignalNotificationBuilder baseOneSignalNotificationBuilder = getBaseOneSignalNotificationBuilder(oSNotificationGenerationJob);
        NotificationCompat.Builder builder = baseOneSignalNotificationBuilder.compatBuilder;
        addNotificationActionButtons(jsonPayload, intentGeneratorForAttachingToNotifications, builder, intValue, null);
        try {
            addBackgroundImage(jsonPayload, builder);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Could not set background notification image!", th);
        }
        applyNotificationExtender(oSNotificationGenerationJob, builder);
        if (oSNotificationGenerationJob.isRestoring()) {
            removeNotifyOptions(builder);
        }
        NotificationLimitManager.clearOldestOverLimit(currentContext, optString != null ? 2 : 1);
        if (optString != null) {
            createGenericPendingIntentsForGroup(builder, intentGeneratorForAttachingToNotifications, jsonPayload, optString, intValue);
            createGenericPendingIntentsForNotif = createSingleNotificationBeforeSummaryBuilder(oSNotificationGenerationJob, builder);
            if (Build.VERSION.SDK_INT >= 24 && optString.equals(OneSignalNotificationManager.getGrouplessSummaryKey())) {
                createGrouplessSummaryNotification(oSNotificationGenerationJob, intentGeneratorForAttachingToNotifications, arrayList.size() + 1);
            } else {
                createSummaryNotification(oSNotificationGenerationJob, baseOneSignalNotificationBuilder);
            }
        } else {
            createGenericPendingIntentsForNotif = createGenericPendingIntentsForNotif(builder, intentGeneratorForAttachingToNotifications, jsonPayload, intValue);
        }
        if (optString == null || Build.VERSION.SDK_INT > 17) {
            addXiaomiSettings(baseOneSignalNotificationBuilder, createGenericPendingIntentsForNotif);
            NotificationManagerCompat.from(currentContext).notify(intValue, createGenericPendingIntentsForNotif);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return OneSignalNotificationManager.areNotificationsEnabled(currentContext, createGenericPendingIntentsForNotif.getChannelId());
        }
        return true;
    }

    private static Notification createGenericPendingIntentsForNotif(NotificationCompat.Builder builder, IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications, JSONObject jSONObject, int r7) {
        SecureRandom secureRandom = new SecureRandom();
        builder.setContentIntent(intentGeneratorForAttachingToNotifications.getNewActionPendingIntent(secureRandom.nextInt(), intentGeneratorForAttachingToNotifications.getNewBaseIntent(r7).putExtra(BUNDLE_KEY_ONESIGNAL_DATA, jSONObject.toString())));
        builder.setDeleteIntent(getNewDismissActionPendingIntent(secureRandom.nextInt(), getNewBaseDismissIntent(r7)));
        return builder.build();
    }

    private static void createGenericPendingIntentsForGroup(NotificationCompat.Builder builder, IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications, JSONObject jSONObject, String str, int r8) {
        SecureRandom secureRandom = new SecureRandom();
        builder.setContentIntent(intentGeneratorForAttachingToNotifications.getNewActionPendingIntent(secureRandom.nextInt(), intentGeneratorForAttachingToNotifications.getNewBaseIntent(r8).putExtra(BUNDLE_KEY_ONESIGNAL_DATA, jSONObject.toString()).putExtra("grp", str)));
        builder.setDeleteIntent(getNewDismissActionPendingIntent(secureRandom.nextInt(), getNewBaseDismissIntent(r8).putExtra("grp", str)));
        builder.setGroup(str);
        try {
            builder.setGroupAlertBehavior(groupAlertBehavior.intValue());
        } catch (Throwable unused) {
        }
    }

    private static void applyNotificationExtender(OSNotificationGenerationJob oSNotificationGenerationJob, NotificationCompat.Builder builder) {
        if (oSNotificationGenerationJob.hasExtender()) {
            try {
                Field declaredField = NotificationCompat.Builder.class.getDeclaredField("mNotification");
                declaredField.setAccessible(true);
                Notification notification = (Notification) declaredField.get(builder);
                oSNotificationGenerationJob.setOrgFlags(Integer.valueOf(notification.flags));
                oSNotificationGenerationJob.setOrgSound(notification.sound);
                builder.extend(oSNotificationGenerationJob.getNotification().getNotificationExtender());
                Notification notification2 = (Notification) declaredField.get(builder);
                Field declaredField2 = NotificationCompat.Builder.class.getDeclaredField("mContentText");
                declaredField2.setAccessible(true);
                Field declaredField3 = NotificationCompat.Builder.class.getDeclaredField("mContentTitle");
                declaredField3.setAccessible(true);
                oSNotificationGenerationJob.setOverriddenBodyFromExtender((CharSequence) declaredField2.get(builder));
                oSNotificationGenerationJob.setOverriddenTitleFromExtender((CharSequence) declaredField3.get(builder));
                if (oSNotificationGenerationJob.isRestoring()) {
                    return;
                }
                oSNotificationGenerationJob.setOverriddenFlags(Integer.valueOf(notification2.flags));
                oSNotificationGenerationJob.setOverriddenSound(notification2.sound);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private static Notification createSingleNotificationBeforeSummaryBuilder(OSNotificationGenerationJob oSNotificationGenerationJob, NotificationCompat.Builder builder) {
        boolean z = Build.VERSION.SDK_INT > 17 && Build.VERSION.SDK_INT < 24 && !oSNotificationGenerationJob.isRestoring();
        if (z && oSNotificationGenerationJob.getOverriddenSound() != null && !oSNotificationGenerationJob.getOverriddenSound().equals(oSNotificationGenerationJob.getOrgSound())) {
            builder.setSound(null);
        }
        Notification build = builder.build();
        if (z) {
            builder.setSound(oSNotificationGenerationJob.getOverriddenSound());
        }
        return build;
    }

    private static void addXiaomiSettings(OneSignalNotificationBuilder oneSignalNotificationBuilder, Notification notification) {
        if (oneSignalNotificationBuilder.hasLargeIcon) {
            try {
                Object newInstance = Class.forName("android.app.MiuiNotification").newInstance();
                Field declaredField = newInstance.getClass().getDeclaredField("customizedIcon");
                declaredField.setAccessible(true);
                declaredField.set(newInstance, true);
                Field field = notification.getClass().getField("extraNotification");
                field.setAccessible(true);
                field.set(notification, newInstance);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void updateSummaryNotification(OSNotificationGenerationJob oSNotificationGenerationJob) {
        setStatics(oSNotificationGenerationJob.getContext());
        createSummaryNotification(oSNotificationGenerationJob, null);
    }

    private static void createSummaryNotification(OSNotificationGenerationJob oSNotificationGenerationJob, OneSignalNotificationBuilder oneSignalNotificationBuilder) {
        String str;
        JSONObject jSONObject;
        ArrayList<SpannableString> arrayList;
        Integer num;
        Notification build;
        String str2;
        String str3;
        String str4;
        String str5 = OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE;
        String str6 = "title";
        String str7 = OneSignalDbContract.NotificationTable.COLUMN_NAME_IS_SUMMARY;
        boolean isRestoring = oSNotificationGenerationJob.isRestoring();
        JSONObject jsonPayload = oSNotificationGenerationJob.getJsonPayload();
        IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications = new IntentGeneratorForAttachingToNotifications(currentContext);
        Cursor cursor = null;
        String optString = jsonPayload.optString("grp", null);
        SecureRandom secureRandom = new SecureRandom();
        PendingIntent newDismissActionPendingIntent = getNewDismissActionPendingIntent(secureRandom.nextInt(), getNewBaseDismissIntent(0).putExtra(ErrorBundle.SUMMARY_ENTRY, optString));
        OneSignalDbHelper oneSignalDbHelper = OneSignalDbHelper.getInstance(currentContext);
        try {
            String[] strArr = {OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA, OneSignalDbContract.NotificationTable.COLUMN_NAME_IS_SUMMARY, "title", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE};
            try {
                String[] strArr2 = {optString};
                if (isRestoring) {
                    str = "group_id = ? AND dismissed = 0 AND opened = 0";
                } else {
                    try {
                        str = "group_id = ? AND dismissed = 0 AND opened = 0 AND android_notification_id <> " + oSNotificationGenerationJob.getAndroidId();
                    } catch (Throwable th) {
                        th = th;
                        cursor = null;
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                Cursor query = oneSignalDbHelper.query(OneSignalDbContract.NotificationTable.TABLE_NAME, strArr, str, strArr2, null, null, "_id DESC");
                try {
                    if (query.moveToFirst()) {
                        arrayList = new ArrayList();
                        String str8 = null;
                        num = null;
                        while (true) {
                            String str9 = str7;
                            if (query.getInt(query.getColumnIndex(str7)) == 1) {
                                num = Integer.valueOf(query.getInt(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID)));
                                str2 = str5;
                                str3 = str6;
                            } else {
                                String string = query.getString(query.getColumnIndex(str6));
                                String str10 = string == null ? "" : string + " ";
                                str2 = str5;
                                str3 = str6;
                                SpannableString spannableString = new SpannableString(str10 + query.getString(query.getColumnIndex(str5)));
                                if (str10.length() > 0) {
                                    spannableString.setSpan(new StyleSpan(1), 0, str10.length(), 0);
                                }
                                arrayList.add(spannableString);
                                if (str8 == null) {
                                    str8 = query.getString(query.getColumnIndex(OneSignalDbContract.NotificationTable.COLUMN_NAME_FULL_DATA));
                                }
                            }
                            str4 = str8;
                            if (!query.moveToNext()) {
                                break;
                            }
                            str8 = str4;
                            str7 = str9;
                            str5 = str2;
                            str6 = str3;
                        }
                        if (isRestoring != 0 && str4 != null) {
                            try {
                                jSONObject = new JSONObject(str4);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        jSONObject = jsonPayload;
                    } else {
                        jSONObject = jsonPayload;
                        arrayList = null;
                        num = null;
                    }
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    if (num == null) {
                        num = Integer.valueOf(secureRandom.nextInt());
                        createSummaryIdDatabaseEntry(oneSignalDbHelper, optString, num.intValue());
                    }
                    PendingIntent newActionPendingIntent = intentGeneratorForAttachingToNotifications.getNewActionPendingIntent(secureRandom.nextInt(), createBaseSummaryIntent(num.intValue(), intentGeneratorForAttachingToNotifications, jSONObject, optString));
                    if (arrayList != null && ((isRestoring && arrayList.size() > 1) || (!isRestoring && arrayList.size() > 0))) {
                        int size = arrayList.size() + (!isRestoring);
                        String optString2 = jSONObject.optString("grp_msg", null);
                        CharSequence replace = optString2 == null ? size + " new messages" : optString2.replace("$[notif_count]", "" + size);
                        NotificationCompat.Builder builder = getBaseOneSignalNotificationBuilder(oSNotificationGenerationJob).compatBuilder;
                        if (isRestoring != 0) {
                            removeNotifyOptions(builder);
                        } else {
                            if (oSNotificationGenerationJob.getOverriddenSound() != null) {
                                builder.setSound(oSNotificationGenerationJob.getOverriddenSound());
                            }
                            if (oSNotificationGenerationJob.getOverriddenFlags() != null) {
                                builder.setDefaults(oSNotificationGenerationJob.getOverriddenFlags().intValue());
                            }
                        }
                        builder.setContentIntent(newActionPendingIntent).setDeleteIntent(newDismissActionPendingIntent).setContentTitle(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).setContentText(replace).setNumber(size).setSmallIcon(getDefaultSmallIconId()).setLargeIcon(getDefaultLargeIcon()).setOnlyAlertOnce(isRestoring).setAutoCancel(false).setGroup(optString).setGroupSummary(true);
                        try {
                            builder.setGroupAlertBehavior(groupAlertBehavior.intValue());
                        } catch (Throwable unused) {
                        }
                        if (isRestoring == 0) {
                            builder.setTicker(replace);
                        }
                        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                        if (isRestoring == 0) {
                            String charSequence = oSNotificationGenerationJob.getTitle() != null ? oSNotificationGenerationJob.getTitle().toString() : null;
                            String str11 = charSequence != null ? charSequence + " " : "";
                            SpannableString spannableString2 = new SpannableString(str11 + oSNotificationGenerationJob.getBody().toString());
                            if (str11.length() > 0) {
                                spannableString2.setSpan(new StyleSpan(1), 0, str11.length(), 0);
                            }
                            inboxStyle.addLine(spannableString2);
                        }
                        for (SpannableString spannableString3 : arrayList) {
                            inboxStyle.addLine(spannableString3);
                        }
                        inboxStyle.setBigContentTitle(replace);
                        builder.setStyle(inboxStyle);
                        build = builder.build();
                    } else {
                        NotificationCompat.Builder builder2 = oneSignalNotificationBuilder.compatBuilder;
                        builder2.mActions.clear();
                        addNotificationActionButtons(jSONObject, intentGeneratorForAttachingToNotifications, builder2, num.intValue(), optString);
                        builder2.setContentIntent(newActionPendingIntent).setDeleteIntent(newDismissActionPendingIntent).setOnlyAlertOnce(isRestoring).setAutoCancel(false).setGroup(optString).setGroupSummary(true);
                        try {
                            builder2.setGroupAlertBehavior(groupAlertBehavior.intValue());
                        } catch (Throwable unused2) {
                        }
                        build = builder2.build();
                        addXiaomiSettings(oneSignalNotificationBuilder, build);
                    }
                    NotificationManagerCompat.from(currentContext).notify(num.intValue(), build);
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private static void createGrouplessSummaryNotification(OSNotificationGenerationJob oSNotificationGenerationJob, IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications, int r9) {
        JSONObject jsonPayload = oSNotificationGenerationJob.getJsonPayload();
        SecureRandom secureRandom = new SecureRandom();
        String grouplessSummaryKey = OneSignalNotificationManager.getGrouplessSummaryKey();
        String str = r9 + " new messages";
        int grouplessSummaryId = OneSignalNotificationManager.getGrouplessSummaryId();
        PendingIntent newActionPendingIntent = intentGeneratorForAttachingToNotifications.getNewActionPendingIntent(secureRandom.nextInt(), createBaseSummaryIntent(grouplessSummaryId, intentGeneratorForAttachingToNotifications, jsonPayload, grouplessSummaryKey));
        PendingIntent newDismissActionPendingIntent = getNewDismissActionPendingIntent(secureRandom.nextInt(), getNewBaseDismissIntent(0).putExtra(ErrorBundle.SUMMARY_ENTRY, grouplessSummaryKey));
        NotificationCompat.Builder builder = getBaseOneSignalNotificationBuilder(oSNotificationGenerationJob).compatBuilder;
        if (oSNotificationGenerationJob.getOverriddenSound() != null) {
            builder.setSound(oSNotificationGenerationJob.getOverriddenSound());
        }
        if (oSNotificationGenerationJob.getOverriddenFlags() != null) {
            builder.setDefaults(oSNotificationGenerationJob.getOverriddenFlags().intValue());
        }
        builder.setContentIntent(newActionPendingIntent).setDeleteIntent(newDismissActionPendingIntent).setContentTitle(currentContext.getPackageManager().getApplicationLabel(currentContext.getApplicationInfo())).setContentText(str).setNumber(r9).setSmallIcon(getDefaultSmallIconId()).setLargeIcon(getDefaultLargeIcon()).setOnlyAlertOnce(true).setAutoCancel(false).setGroup(grouplessSummaryKey).setGroupSummary(true);
        try {
            builder.setGroupAlertBehavior(groupAlertBehavior.intValue());
        } catch (Throwable unused) {
        }
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(str);
        builder.setStyle(inboxStyle);
        NotificationManagerCompat.from(currentContext).notify(grouplessSummaryId, builder.build());
    }

    private static Intent createBaseSummaryIntent(int r0, IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications, JSONObject jSONObject, String str) {
        return intentGeneratorForAttachingToNotifications.getNewBaseIntent(r0).putExtra(BUNDLE_KEY_ONESIGNAL_DATA, jSONObject.toString()).putExtra(ErrorBundle.SUMMARY_ENTRY, str);
    }

    private static void createSummaryIdDatabaseEntry(OneSignalDbHelper oneSignalDbHelper, String str, int r4) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_ANDROID_NOTIFICATION_ID, Integer.valueOf(r4));
        contentValues.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_GROUP_ID, str);
        contentValues.put(OneSignalDbContract.NotificationTable.COLUMN_NAME_IS_SUMMARY, (Integer) 1);
        oneSignalDbHelper.insertOrThrow(OneSignalDbContract.NotificationTable.TABLE_NAME, null, contentValues);
    }

    private static void addBackgroundImage(JSONObject jSONObject, NotificationCompat.Builder builder) throws Throwable {
        Bitmap bitmap;
        JSONObject jSONObject2;
        String string;
        if (Build.VERSION.SDK_INT < 16 || Build.VERSION.SDK_INT >= 31) {
            OneSignal.Log(OneSignal.LOG_LEVEL.VERBOSE, "Cannot use background images in notifications for device on version: " + Build.VERSION.SDK_INT);
            return;
        }
        String optString = jSONObject.optString("bg_img", null);
        if (optString != null) {
            jSONObject2 = new JSONObject(optString);
            bitmap = getBitmap(jSONObject2.optString("img", null));
        } else {
            bitmap = null;
            jSONObject2 = null;
        }
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("onesignal_bgimage_default_image");
        }
        if (bitmap != null) {
            RemoteViews remoteViews = new RemoteViews(currentContext.getPackageName(), C3644R.layout.onesignal_bgimage_notif_layout);
            remoteViews.setTextViewText(C3644R.C3648id.os_bgimage_notif_title, getTitle(jSONObject));
            remoteViews.setTextViewText(C3644R.C3648id.os_bgimage_notif_body, jSONObject.optString("alert"));
            setTextColor(remoteViews, jSONObject2, C3644R.C3648id.os_bgimage_notif_title, "tc", "onesignal_bgimage_notif_title_color");
            setTextColor(remoteViews, jSONObject2, C3644R.C3648id.os_bgimage_notif_body, "bc", "onesignal_bgimage_notif_body_color");
            if (jSONObject2 != null && jSONObject2.has("img_align")) {
                string = jSONObject2.getString("img_align");
            } else {
                int identifier = contextResources.getIdentifier("onesignal_bgimage_notif_image_align", "string", packageName);
                string = identifier != 0 ? contextResources.getString(identifier) : null;
            }
            if ("right".equals(string)) {
                remoteViews.setViewPadding(C3644R.C3648id.os_bgimage_notif_bgimage_align_layout, -5000, 0, 0, 0);
                remoteViews.setImageViewBitmap(C3644R.C3648id.os_bgimage_notif_bgimage_right_aligned, bitmap);
                remoteViews.setViewVisibility(C3644R.C3648id.os_bgimage_notif_bgimage_right_aligned, 0);
                remoteViews.setViewVisibility(C3644R.C3648id.os_bgimage_notif_bgimage, 2);
            } else {
                remoteViews.setImageViewBitmap(C3644R.C3648id.os_bgimage_notif_bgimage, bitmap);
            }
            builder.setContent(remoteViews);
            builder.setStyle(null);
        }
    }

    private static void setTextColor(RemoteViews remoteViews, JSONObject jSONObject, int r3, String str, String str2) {
        Integer safeGetColorFromHex = safeGetColorFromHex(jSONObject, str);
        if (safeGetColorFromHex != null) {
            remoteViews.setTextColor(r3, safeGetColorFromHex.intValue());
            return;
        }
        int identifier = contextResources.getIdentifier(str2, "color", packageName);
        if (identifier != 0) {
            remoteViews.setTextColor(r3, AndroidSupportV4Compat.ContextCompat.getColor(currentContext, identifier));
        }
    }

    private static Integer safeGetColorFromHex(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has(str)) {
                    return Integer.valueOf(new BigInteger(jSONObject.optString(str), 16).intValue());
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    private static Bitmap getLargeIcon(JSONObject jSONObject) {
        Bitmap bitmap = getBitmap(jSONObject.optString("licon"));
        if (bitmap == null) {
            bitmap = getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default");
        }
        if (bitmap == null) {
            return null;
        }
        return resizeBitmapForLargeIconArea(bitmap);
    }

    private static Bitmap getDefaultLargeIcon() {
        return resizeBitmapForLargeIconArea(getBitmapFromAssetsOrResourceName("ic_onesignal_large_icon_default"));
    }

    private static Bitmap resizeBitmapForLargeIconArea(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        try {
            int dimension = (int) contextResources.getDimension(17104902);
            int dimension2 = (int) contextResources.getDimension(17104901);
            int height = bitmap.getHeight();
            int width = bitmap.getWidth();
            if (width > dimension2 || height > dimension) {
                if (height > width) {
                    dimension2 = (int) (dimension * (width / height));
                } else if (width > height) {
                    dimension = (int) (dimension2 * (height / width));
                }
                return Bitmap.createScaledBitmap(bitmap, dimension2, dimension, true);
            }
            return bitmap;
        } catch (Throwable unused) {
            return bitmap;
        }
    }

    private static Bitmap getBitmapFromAssetsOrResourceName(String str) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(str));
        } catch (Throwable unused) {
            bitmap = null;
        }
        if (bitmap != null) {
            return bitmap;
        }
        try {
            for (String str2 : Arrays.asList(".png", ".webp", ".jpg", ".gif", ".bmp")) {
                try {
                    bitmap = BitmapFactory.decodeStream(currentContext.getAssets().open(str + str2));
                    continue;
                } catch (Throwable unused2) {
                    continue;
                }
                if (bitmap != null) {
                    return bitmap;
                }
            }
            int resourceIcon = getResourceIcon(str);
            if (resourceIcon != 0) {
                return BitmapFactory.decodeResource(contextResources, resourceIcon);
            }
        } catch (Throwable unused3) {
        }
        return null;
    }

    private static Bitmap getBitmapFromURL(String str) {
        try {
            return BitmapFactory.decodeStream(new URL(str).openConnection().getInputStream());
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.WARN, "Could not download image!", th);
            return null;
        }
    }

    private static Bitmap getBitmap(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("http://") || trim.startsWith("https://")) {
            return getBitmapFromURL(trim);
        }
        return getBitmapFromAssetsOrResourceName(str);
    }

    private static int getResourceIcon(String str) {
        if (str == null) {
            return 0;
        }
        String trim = str.trim();
        if (OSUtils.isValidResourceName(trim)) {
            int drawableId = getDrawableId(trim);
            if (drawableId != 0) {
                return drawableId;
            }
            try {
                return R.drawable.class.getField(str).getInt(null);
            } catch (Throwable unused) {
                return 0;
            }
        }
        return 0;
    }

    private static int getSmallIconId(JSONObject jSONObject) {
        int resourceIcon = getResourceIcon(jSONObject.optString("sicon", null));
        return resourceIcon != 0 ? resourceIcon : getDefaultSmallIconId();
    }

    private static int getDefaultSmallIconId() {
        int drawableId = getDrawableId("ic_stat_onesignal_default");
        if (drawableId != 0) {
            return drawableId;
        }
        int drawableId2 = getDrawableId("corona_statusbar_icon_default");
        if (drawableId2 != 0) {
            return drawableId2;
        }
        int drawableId3 = getDrawableId("ic_os_notification_fallback_white_24dp");
        if (drawableId3 != 0) {
            return drawableId3;
        }
        return 17301598;
    }

    private static int getDrawableId(String str) {
        return contextResources.getIdentifier(str, "drawable", packageName);
    }

    private static boolean isSoundEnabled(JSONObject jSONObject) {
        String optString = jSONObject.optString("sound", null);
        return ("null".equals(optString) || "nil".equals(optString)) ? false : true;
    }

    static BigInteger getAccentColor(JSONObject jSONObject) {
        try {
            if (jSONObject.has("bgac")) {
                return new BigInteger(jSONObject.optString("bgac", null), 16);
            }
        } catch (Throwable unused) {
        }
        try {
            String resourceString = OSUtils.getResourceString(OneSignal.appContext, "onesignal_notification_accent_color", null);
            if (resourceString != null) {
                return new BigInteger(resourceString, 16);
            }
        } catch (Throwable unused2) {
        }
        try {
            String manifestMeta = OSUtils.getManifestMeta(OneSignal.appContext, "com.onesignal.NotificationAccentColor.DEFAULT");
            if (manifestMeta != null) {
                return new BigInteger(manifestMeta, 16);
            }
        } catch (Throwable unused3) {
        }
        return null;
    }

    private static void addNotificationActionButtons(JSONObject jSONObject, IntentGeneratorForAttachingToNotifications intentGeneratorForAttachingToNotifications, NotificationCompat.Builder builder, int r13, String str) {
        try {
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString(OSNotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
            if (jSONObject2.has(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY)) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY);
                if (jSONObject3.has("actionButtons")) {
                    JSONArray jSONArray = jSONObject3.getJSONArray("actionButtons");
                    for (int r4 = 0; r4 < jSONArray.length(); r4++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(r4);
                        JSONObject jSONObject4 = new JSONObject(jSONObject.toString());
                        Intent newBaseIntent = intentGeneratorForAttachingToNotifications.getNewBaseIntent(r13);
                        newBaseIntent.setAction("" + r4);
                        newBaseIntent.putExtra("action_button", true);
                        jSONObject4.put(BUNDLE_KEY_ACTION_ID, optJSONObject.optString("id"));
                        newBaseIntent.putExtra(BUNDLE_KEY_ONESIGNAL_DATA, jSONObject4.toString());
                        if (str != null) {
                            newBaseIntent.putExtra(ErrorBundle.SUMMARY_ENTRY, str);
                        } else if (jSONObject.has("grp")) {
                            newBaseIntent.putExtra("grp", jSONObject.optString("grp"));
                        }
                        builder.addAction(optJSONObject.has("icon") ? getResourceIcon(optJSONObject.optString("icon")) : 0, optJSONObject.optString("text"), intentGeneratorForAttachingToNotifications.getNewActionPendingIntent(r13, newBaseIntent));
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private static void addAlertButtons(Context context, JSONObject jSONObject, List<String> list, List<String> list2) {
        try {
            addCustomAlertButtons(jSONObject, list, list2);
        } catch (Throwable th) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Failed to parse JSON for custom buttons for alert dialog.", th);
        }
        if (list.size() == 0 || list.size() < 3) {
            list.add(OSUtils.getResourceString(context, "onesignal_in_app_alert_ok_button_text", "Ok"));
            list2.add("__DEFAULT__");
        }
    }

    private static void addCustomAlertButtons(JSONObject jSONObject, List<String> list, List<String> list2) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.optString(OSNotificationFormatHelper.PAYLOAD_OS_ROOT_CUSTOM));
        if (jSONObject2.has(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY)) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject(NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY);
            if (jSONObject3.has("actionButtons")) {
                JSONArray optJSONArray = jSONObject3.optJSONArray("actionButtons");
                for (int r0 = 0; r0 < optJSONArray.length(); r0++) {
                    JSONObject jSONObject4 = optJSONArray.getJSONObject(r0);
                    list.add(jSONObject4.optString("text"));
                    list2.add(jSONObject4.optString("id"));
                }
            }
        }
    }
}
