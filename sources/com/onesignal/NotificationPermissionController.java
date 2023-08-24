package com.onesignal;

import android.app.Activity;
import android.os.Build;
import com.onesignal.AlertDialogPrepromptForAndroidSettings;
import com.onesignal.OneSignal;
import com.onesignal.PermissionsActivity;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationPermissionController.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\b\u0010\u0011\u001a\u00020\u0007H\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\u0006\u0010\u0013\u001a\u00020\u000fJ\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0007H\u0016J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\nJ\b\u0010\u0018\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, m183d2 = {"Lcom/onesignal/NotificationPermissionController;", "Lcom/onesignal/PermissionsActivity$PermissionCallback;", "()V", "ANDROID_PERMISSION_STRING", "", "PERMISSION_TYPE", "awaitingForReturnFromSystemSettings", "", "callbacks", "", "Lcom/onesignal/OneSignal$PromptForPushNotificationPermissionResponseHandler;", "supportsNativePrompt", "getSupportsNativePrompt", "()Z", "fireCallBacks", "", "accepted", "notificationsEnabled", "onAccept", "onAppForegrounded", "onReject", "fallbackToSettings", "prompt", "callback", "showFallbackAlertDialog", "onesignal_release"}, m182k = 1, m181mv = {1, 4, 2})
/* loaded from: classes3.dex */
public final class NotificationPermissionController implements PermissionsActivity.PermissionCallback {
    private static final String ANDROID_PERMISSION_STRING = "android.permission.POST_NOTIFICATIONS";
    public static final NotificationPermissionController INSTANCE;
    private static final String PERMISSION_TYPE = "NOTIFICATION";
    private static boolean awaitingForReturnFromSystemSettings;
    private static final Set<OneSignal.PromptForPushNotificationPermissionResponseHandler> callbacks;
    private static final boolean supportsNativePrompt;

    static {
        NotificationPermissionController notificationPermissionController = new NotificationPermissionController();
        INSTANCE = notificationPermissionController;
        callbacks = new HashSet();
        PermissionsActivity.registerAsCallback(PERMISSION_TYPE, notificationPermissionController);
        supportsNativePrompt = Build.VERSION.SDK_INT > 32 && OSUtils.getTargetSdkVersion(OneSignal.appContext) > 32;
    }

    private NotificationPermissionController() {
    }

    public final boolean getSupportsNativePrompt() {
        return supportsNativePrompt;
    }

    public final void prompt(boolean z, OneSignal.PromptForPushNotificationPermissionResponseHandler promptForPushNotificationPermissionResponseHandler) {
        if (promptForPushNotificationPermissionResponseHandler != null) {
            callbacks.add(promptForPushNotificationPermissionResponseHandler);
        }
        if (notificationsEnabled()) {
            fireCallBacks(true);
        } else if (supportsNativePrompt) {
            PermissionsActivity.startPrompt(z, PERMISSION_TYPE, ANDROID_PERMISSION_STRING, getClass());
        } else if (z) {
            showFallbackAlertDialog();
        } else {
            fireCallBacks(false);
        }
    }

    @Override // com.onesignal.PermissionsActivity.PermissionCallback
    public void onAccept() {
        OneSignal.refreshNotificationPermissionState();
        fireCallBacks(true);
    }

    @Override // com.onesignal.PermissionsActivity.PermissionCallback
    public void onReject(boolean z) {
        if (z ? showFallbackAlertDialog() : false) {
            return;
        }
        fireCallBacks(false);
    }

    private final boolean showFallbackAlertDialog() {
        final Activity currentActivity = OneSignal.getCurrentActivity();
        if (currentActivity != null) {
            Intrinsics.checkNotNullExpressionValue(currentActivity, "OneSignal.getCurrentActivity() ?: return false");
            AlertDialogPrepromptForAndroidSettings alertDialogPrepromptForAndroidSettings = AlertDialogPrepromptForAndroidSettings.INSTANCE;
            String string = currentActivity.getString(C3644R.string.notification_permission_name_for_title);
            Intrinsics.checkNotNullExpressionValue(string, "activity.getString(R.str…ermission_name_for_title)");
            String string2 = currentActivity.getString(C3644R.string.notification_permission_settings_message);
            Intrinsics.checkNotNullExpressionValue(string2, "activity.getString(R.str…mission_settings_message)");
            alertDialogPrepromptForAndroidSettings.show(currentActivity, string, string2, new AlertDialogPrepromptForAndroidSettings.Callback() { // from class: com.onesignal.NotificationPermissionController$showFallbackAlertDialog$1
                @Override // com.onesignal.AlertDialogPrepromptForAndroidSettings.Callback
                public void onAccept() {
                    NavigateToAndroidSettingsForNotifications.INSTANCE.show(currentActivity);
                    NotificationPermissionController notificationPermissionController = NotificationPermissionController.INSTANCE;
                    NotificationPermissionController.awaitingForReturnFromSystemSettings = true;
                }

                @Override // com.onesignal.AlertDialogPrepromptForAndroidSettings.Callback
                public void onDecline() {
                    NotificationPermissionController.INSTANCE.fireCallBacks(false);
                }
            });
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fireCallBacks(boolean z) {
        for (OneSignal.PromptForPushNotificationPermissionResponseHandler promptForPushNotificationPermissionResponseHandler : callbacks) {
            promptForPushNotificationPermissionResponseHandler.response(z);
        }
        callbacks.clear();
    }

    public final void onAppForegrounded() {
        if (awaitingForReturnFromSystemSettings) {
            awaitingForReturnFromSystemSettings = false;
            fireCallBacks(notificationsEnabled());
        }
    }

    private final boolean notificationsEnabled() {
        return OSUtils.areNotificationsEnabled(OneSignal.appContext);
    }
}
