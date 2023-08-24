package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqe {
    private static final char[] zza = "0123456789abcdef".toCharArray();

    public static long zza(double d, int r2, DisplayMetrics displayMetrics) {
        return Math.round(d / displayMetrics.density);
    }

    public static Activity zzb(View view) {
        View rootView = view.getRootView();
        if (rootView != null) {
            view = rootView;
        }
        Context context = view.getContext();
        for (int r0 = 0; (context instanceof ContextWrapper) && r0 < 10; r0++) {
            if (!(context instanceof Activity)) {
                context = ((ContextWrapper) context).getBaseContext();
            } else {
                return (Activity) context;
            }
        }
        return null;
    }

    public static String zzc(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length + length];
        for (int r1 = 0; r1 < bArr.length; r1++) {
            int r2 = bArr[r1] & 255;
            int r3 = r1 + r1;
            char[] cArr2 = zza;
            cArr[r3] = cArr2[r2 >>> 4];
            cArr[r3 + 1] = cArr2[r2 & 15];
        }
        return new String(cArr);
    }

    public static String zzd(String str) {
        if (str == null || !str.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
            return str;
        }
        UUID fromString = UUID.fromString(str);
        byte[] bArr = new byte[16];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(fromString.getMostSignificantBits());
        wrap.putLong(fromString.getLeastSignificantBits());
        return zzanm.zza(bArr, true);
    }

    public static String zze(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static boolean zzf() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean zzg(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean zzh(DisplayMetrics displayMetrics) {
        return (displayMetrics == null || displayMetrics.density == 0.0f) ? false : true;
    }

    public static byte[] zzi(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("String must be of even-length");
        }
        byte[] bArr = new byte[length / 2];
        for (int r2 = 0; r2 < length; r2 += 2) {
            bArr[r2 / 2] = (byte) ((Character.digit(str.charAt(r2), 16) << 4) + Character.digit(str.charAt(r2 + 1), 16));
        }
        return bArr;
    }
}
