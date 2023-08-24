package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.CommonStatusCodes;

/* compiled from: com.google.android.gms:play-services-auth@@19.2.0 */
/* loaded from: classes2.dex */
public final class GoogleSignInStatusCodes extends CommonStatusCodes {
    public static final int SIGN_IN_CANCELLED = 12501;
    public static final int SIGN_IN_CURRENTLY_IN_PROGRESS = 12502;
    public static final int SIGN_IN_FAILED = 12500;

    private GoogleSignInStatusCodes() {
    }

    public static String getStatusCodeString(int r0) {
        switch (r0) {
            case SIGN_IN_FAILED /* 12500 */:
                return "A non-recoverable sign in failure occurred";
            case SIGN_IN_CANCELLED /* 12501 */:
                return "Sign in action cancelled";
            case SIGN_IN_CURRENTLY_IN_PROGRESS /* 12502 */:
                return "Sign-in in progress";
            default:
                return CommonStatusCodes.getStatusCodeString(r0);
        }
    }
}
