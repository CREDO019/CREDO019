package com.google.android.gms.internal.vision;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzcv {
    public static String zzk(@NullableDecl String str) {
        return zzcp.zzk(str);
    }

    public static String zza(@NullableDecl String str, @NullableDecl Object... objArr) {
        int indexOf;
        String valueOf = String.valueOf(str);
        int r0 = 0;
        for (int r1 = 0; r1 < objArr.length; r1++) {
            objArr[r1] = zzc(objArr[r1]);
        }
        StringBuilder sb = new StringBuilder(valueOf.length() + (objArr.length * 16));
        int r2 = 0;
        while (r0 < objArr.length && (indexOf = valueOf.indexOf("%s", r2)) != -1) {
            sb.append((CharSequence) valueOf, r2, indexOf);
            sb.append(objArr[r0]);
            r2 = indexOf + 2;
            r0++;
        }
        sb.append((CharSequence) valueOf, r2, valueOf.length());
        if (r0 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[r0]);
            for (int r6 = r0 + 1; r6 < objArr.length; r6++) {
                sb.append(", ");
                sb.append(objArr[r6]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    private static String zzc(@NullableDecl Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception e) {
            String name = obj.getClass().getName();
            String hexString = Integer.toHexString(System.identityHashCode(obj));
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
            sb.append(name);
            sb.append('@');
            sb.append(hexString);
            String sb2 = sb.toString();
            Logger logger = Logger.getLogger("com.google.common.base.Strings");
            Level level = Level.WARNING;
            String valueOf = String.valueOf(sb2);
            logger.logp(level, "com.google.common.base.Strings", "lenientToString", valueOf.length() != 0 ? "Exception during lenientFormat for ".concat(valueOf) : new String("Exception during lenientFormat for "), (Throwable) e);
            String name2 = e.getClass().getName();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 9 + String.valueOf(name2).length());
            sb3.append("<");
            sb3.append(sb2);
            sb3.append(" threw ");
            sb3.append(name2);
            sb3.append(">");
            return sb3.toString();
        }
    }
}
