package com.reactnativecommunity.netinfo;

import android.content.Context;
import androidx.core.content.ContextCompat;

/* loaded from: classes3.dex */
public class NetInfoUtils {
    public static void reverseByteArray(byte[] bArr) {
        for (int r0 = 0; r0 < bArr.length / 2; r0++) {
            byte b = bArr[r0];
            bArr[r0] = bArr[(bArr.length - r0) - 1];
            bArr[(bArr.length - r0) - 1] = b;
        }
    }

    public static boolean isAccessWifiStatePermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_WIFI_STATE") == 0;
    }
}
