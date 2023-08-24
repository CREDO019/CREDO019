package androidx.biometric;

import android.os.Build;
import androidx.biometric.BiometricPrompt;

/* loaded from: classes.dex */
class AuthenticatorUtils {
    private static final int BIOMETRIC_CLASS_MASK = 32767;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDeviceCredentialAllowed(int r1) {
        return (r1 & 32768) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSomeBiometricAllowed(int r0) {
        return (r0 & BIOMETRIC_CLASS_MASK) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isWeakBiometricAllowed(int r1) {
        return (r1 & 255) == 255;
    }

    private AuthenticatorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String convertToString(int r1) {
        return r1 != 15 ? r1 != 255 ? r1 != 32768 ? r1 != 32783 ? r1 != 33023 ? String.valueOf(r1) : "BIOMETRIC_WEAK | DEVICE_CREDENTIAL" : "BIOMETRIC_STRONG | DEVICE_CREDENTIAL" : "DEVICE_CREDENTIAL" : "BIOMETRIC_WEAK" : "BIOMETRIC_STRONG";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getConsolidatedAuthenticators(BiometricPrompt.PromptInfo promptInfo, BiometricPrompt.CryptoObject cryptoObject) {
        if (promptInfo.getAllowedAuthenticators() != 0) {
            return promptInfo.getAllowedAuthenticators();
        }
        int r2 = cryptoObject != null ? 15 : 255;
        return promptInfo.isDeviceCredentialAllowed() ? 32768 | r2 : r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isSupportedCombination(int r3) {
        if (r3 == 15 || r3 == 255) {
            return true;
        }
        return r3 != 32768 ? r3 != 32783 ? r3 == 33023 || r3 == 0 : Build.VERSION.SDK_INT < 28 || Build.VERSION.SDK_INT > 29 : Build.VERSION.SDK_INT >= 30;
    }
}
