package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.exoplayer2.C1856C;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
public class GoogleApiAvailabilityLight {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    static final String TRACKING_SOURCE_DIALOG = "d";
    static final String TRACKING_SOURCE_NOTIFICATION = "n";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailabilityLight zza = new GoogleApiAvailabilityLight();

    public static GoogleApiAvailabilityLight getInstance() {
        return zza;
    }

    public void cancelAvailabilityErrorNotifications(Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }

    public int getApkVersion(Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    public int getClientVersion(Context context) {
        return GooglePlayServicesUtilLight.getClientVersion(context);
    }

    @Deprecated
    public Intent getErrorResolutionIntent(int r2) {
        return getErrorResolutionIntent(null, r2, null);
    }

    public PendingIntent getErrorResolutionPendingIntent(Context context, int r3, int r4) {
        return getErrorResolutionPendingIntent(context, r3, r4, null);
    }

    public String getErrorString(int r1) {
        return GooglePlayServicesUtilLight.getErrorString(r1);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return isGooglePlayServicesAvailable(context, GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    public boolean isPlayServicesPossiblyUpdating(Context context, int r2) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, r2);
    }

    public boolean isPlayStorePossiblyUpdating(Context context, int r2) {
        return GooglePlayServicesUtilLight.isPlayStorePossiblyUpdating(context, r2);
    }

    public boolean isUninstalledAppPossiblyUpdating(Context context, String str) {
        return GooglePlayServicesUtilLight.zza(context, str);
    }

    public boolean isUserResolvableError(int r1) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(r1);
    }

    public void verifyGooglePlayServicesIsAvailable(Context context, int r2) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, r2);
    }

    public Intent getErrorResolutionIntent(Context context, int r5, String str) {
        if (r5 != 1 && r5 != 2) {
            if (r5 != 3) {
                return null;
            }
            return com.google.android.gms.common.internal.zzt.zzc("com.google.android.gms");
        } else if (context == null || !DeviceProperties.isWearableWithoutPlayStore(context)) {
            StringBuilder sb = new StringBuilder();
            sb.append("gcore_");
            sb.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
            sb.append("-");
            if (!TextUtils.isEmpty(str)) {
                sb.append(str);
            }
            sb.append("-");
            if (context != null) {
                sb.append(context.getPackageName());
            }
            sb.append("-");
            if (context != null) {
                try {
                    sb.append(Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionCode);
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            return com.google.android.gms.common.internal.zzt.zzb("com.google.android.gms", sb.toString());
        } else {
            return com.google.android.gms.common.internal.zzt.zza();
        }
    }

    public PendingIntent getErrorResolutionPendingIntent(Context context, int r3, int r4, String str) {
        Intent errorResolutionIntent = getErrorResolutionIntent(context, r3, str);
        if (errorResolutionIntent == null) {
            return null;
        }
        return PendingIntent.getActivity(context, r4, errorResolutionIntent, com.google.android.gms.internal.common.zzd.zza | C1856C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    public int isGooglePlayServicesAvailable(Context context, int r2) {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, r2);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, isGooglePlayServicesAvailable)) {
            return 18;
        }
        return isGooglePlayServicesAvailable;
    }
}
