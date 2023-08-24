package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import com.google.android.gms.common.GoogleApiAvailability;

/* loaded from: classes3.dex */
class GooglePlayServicesUpgradePrompt {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    GooglePlayServicesUpgradePrompt() {
    }

    private static boolean isGooglePlayStoreInstalled() {
        try {
            PackageManager packageManager = OneSignal.appContext.getPackageManager();
            return !((String) packageManager.getPackageInfo("com.google.android.gms", 128).applicationInfo.loadLabel(packageManager)).equals("Market");
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void showUpdateGPSDialog() {
        if (OSUtils.isAndroidDeviceType() && isGooglePlayStoreInstalled() && !OneSignal.getDisableGMSMissingPrompt() && !OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_GT_DO_NOT_SHOW_MISSING_GPS, false)) {
            OSUtils.runOnMainUIThread(new Runnable() { // from class: com.onesignal.GooglePlayServicesUpgradePrompt.1
                @Override // java.lang.Runnable
                public void run() {
                    final Activity currentActivity = OneSignal.getCurrentActivity();
                    if (currentActivity == null) {
                        return;
                    }
                    String resourceString = OSUtils.getResourceString(currentActivity, "onesignal_gms_missing_alert_text", "To receive push notifications please press 'Update' to enable 'Google Play services'.");
                    String resourceString2 = OSUtils.getResourceString(currentActivity, "onesignal_gms_missing_alert_button_update", "Update");
                    String resourceString3 = OSUtils.getResourceString(currentActivity, "onesignal_gms_missing_alert_button_skip", "Skip");
                    new AlertDialog.Builder(currentActivity).setMessage(resourceString).setPositiveButton(resourceString2, new DialogInterface.OnClickListener() { // from class: com.onesignal.GooglePlayServicesUpgradePrompt.1.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int r2) {
                            GooglePlayServicesUpgradePrompt.OpenPlayStoreToApp(currentActivity);
                        }
                    }).setNegativeButton(resourceString3, new DialogInterface.OnClickListener() { // from class: com.onesignal.GooglePlayServicesUpgradePrompt.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int r3) {
                            OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, OneSignalPrefs.PREFS_GT_DO_NOT_SHOW_MISSING_GPS, true);
                        }
                    }).setNeutralButton(OSUtils.getResourceString(currentActivity, "onesignal_gms_missing_alert_button_close", "Close"), (DialogInterface.OnClickListener) null).create().show();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void OpenPlayStoreToApp(Activity activity) {
        try {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            PendingIntent errorResolutionPendingIntent = googleApiAvailability.getErrorResolutionPendingIntent(activity, googleApiAvailability.isGooglePlayServicesAvailable(OneSignal.appContext), PLAY_SERVICES_RESOLUTION_REQUEST);
            if (errorResolutionPendingIntent != null) {
                errorResolutionPendingIntent.send();
            }
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }
}
