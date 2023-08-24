package com.onesignal;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.Log;

/* loaded from: classes3.dex */
class AndroidSupportV4Compat {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int r1);
    }

    AndroidSupportV4Compat() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ContextCompat {
        ContextCompat() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int checkSelfPermission(Context context, String str) {
            try {
                return context.checkPermission(str, Process.myPid(), Process.myUid());
            } catch (Throwable unused) {
                Log.e("OneSignal", "checkSelfPermission failed, returning PERMISSION_DENIED");
                return -1;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static int getColor(Context context, int r3) {
            if (Build.VERSION.SDK_INT > 22) {
                return context.getColor(r3);
            }
            return context.getResources().getColor(r3);
        }
    }

    /* loaded from: classes3.dex */
    static class ActivityCompat {
        ActivityCompat() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void requestPermissions(Activity activity, String[] strArr, int r2) {
            ActivityCompatApi23.requestPermissions(activity, strArr, r2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return ActivityCompatApi23.shouldShowRequestPermissionRationale(activity, str);
        }
    }

    /* loaded from: classes3.dex */
    static class ActivityCompatApi23 {
        ActivityCompatApi23() {
        }

        static void requestPermissions(Activity activity, String[] strArr, int r3) {
            if (activity instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator) activity).validateRequestPermissionsRequestCode(r3);
            }
            activity.requestPermissions(strArr, r3);
        }

        static boolean shouldShowRequestPermissionRationale(Activity activity, String str) {
            return androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale(activity, str);
        }
    }
}
