package androidx.biometric;

import android.content.Context;
import android.util.Log;

/* loaded from: classes.dex */
class ErrorUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isKnownError(int r0) {
        switch (r0) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return true;
            case 6:
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLockoutError(int r1) {
        return r1 == 7 || r1 == 9;
    }

    private ErrorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getFingerprintErrorString(Context context, int r3) {
        if (context == null) {
            return "";
        }
        if (r3 == 1) {
            return context.getString(C0214R.string.fingerprint_error_hw_not_available);
        }
        if (r3 != 7) {
            switch (r3) {
                case 9:
                    break;
                case 10:
                    return context.getString(C0214R.string.fingerprint_error_user_canceled);
                case 11:
                    return context.getString(C0214R.string.fingerprint_error_no_fingerprints);
                case 12:
                    return context.getString(C0214R.string.fingerprint_error_hw_not_present);
                default:
                    Log.e("BiometricUtils", "Unknown error code: " + r3);
                    return context.getString(C0214R.string.default_error_msg);
            }
        }
        return context.getString(C0214R.string.fingerprint_error_lockout);
    }
}
