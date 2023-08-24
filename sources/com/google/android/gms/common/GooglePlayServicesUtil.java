package com.google.android.gms.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.internal.zag;

/* compiled from: com.google.android.gms:play-services-base@@18.1.0 */
/* loaded from: classes2.dex */
public final class GooglePlayServicesUtil extends GooglePlayServicesUtilLight {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static Dialog getErrorDialog(int r1, Activity activity, int r3) {
        return getErrorDialog(r1, activity, r3, null);
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int r0, Context context, int r2) {
        return GooglePlayServicesUtilLight.getErrorPendingIntent(r0, context, r2);
    }

    @Deprecated
    public static String getErrorString(int r0) {
        return GooglePlayServicesUtilLight.getErrorString(r0);
    }

    public static Context getRemoteContext(Context context) {
        return GooglePlayServicesUtilLight.getRemoteContext(context);
    }

    public static Resources getRemoteResource(Context context) {
        return GooglePlayServicesUtilLight.getRemoteResource(context);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int r0) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(r0);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int r1, Activity activity, int r3) {
        return showErrorDialogFragment(r1, activity, r3, null);
    }

    @Deprecated
    public static void showErrorNotification(int r2, Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, r2) || GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, r2)) {
            googleApiAvailability.zaf(context);
        } else {
            googleApiAvailability.showErrorNotification(context, r2);
        }
    }

    @Deprecated
    public static Dialog getErrorDialog(int r2, Activity activity, int r4, DialogInterface.OnCancelListener onCancelListener) {
        if (true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, r2)) {
            r2 = 18;
        }
        return GoogleApiAvailability.getInstance().getErrorDialog(activity, r2, r4, onCancelListener);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context, int r1) {
        return GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, r1);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int r1, Activity activity, int r3, DialogInterface.OnCancelListener onCancelListener) {
        return showErrorDialogFragment(r1, activity, null, r3, onCancelListener);
    }

    public static boolean showErrorDialogFragment(int r4, Activity activity, Fragment fragment, int r7, DialogInterface.OnCancelListener onCancelListener) {
        if (true == GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(activity, r4)) {
            r4 = 18;
        }
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        if (fragment == null) {
            return googleApiAvailability.showErrorDialogFragment(activity, r4, r7, onCancelListener);
        }
        Dialog zaa = googleApiAvailability.zaa(activity, r4, zag.zac(fragment, GoogleApiAvailability.getInstance().getErrorResolutionIntent(activity, r4, "d"), r7), onCancelListener);
        if (zaa == null) {
            return false;
        }
        googleApiAvailability.zad(activity, zaa, GMS_ERROR_DIALOG, onCancelListener);
        return true;
    }
}
