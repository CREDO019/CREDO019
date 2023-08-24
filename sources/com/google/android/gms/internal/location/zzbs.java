package com.google.android.gms.internal.location;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes.dex */
public final class zzbs {
    public static String zza(@CheckForNull String str, @CheckForNull Object... objArr) {
        int length;
        int length2;
        int indexOf;
        String sb;
        int r0 = 0;
        int r1 = 0;
        while (true) {
            length = objArr.length;
            if (r1 >= length) {
                break;
            }
            Object obj = objArr[r1];
            if (obj == null) {
                sb = "null";
            } else {
                try {
                    sb = obj.toString();
                } catch (Exception e) {
                    String name = obj.getClass().getName();
                    String hexString = Integer.toHexString(System.identityHashCode(obj));
                    StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
                    sb2.append(name);
                    sb2.append('@');
                    sb2.append(hexString);
                    String sb3 = sb2.toString();
                    Logger.getLogger("com.google.common.base.Strings").logp(Level.WARNING, "com.google.common.base.Strings", "lenientToString", sb3.length() != 0 ? "Exception during lenientFormat for ".concat(sb3) : new String("Exception during lenientFormat for "), (Throwable) e);
                    String name2 = e.getClass().getName();
                    StringBuilder sb4 = new StringBuilder(sb3.length() + 9 + String.valueOf(name2).length());
                    sb4.append("<");
                    sb4.append(sb3);
                    sb4.append(" threw ");
                    sb4.append(name2);
                    sb4.append(">");
                    sb = sb4.toString();
                }
            }
            objArr[r1] = sb;
            r1++;
        }
        StringBuilder sb5 = new StringBuilder(str.length() + (length * 16));
        int r2 = 0;
        while (true) {
            length2 = objArr.length;
            if (r0 >= length2 || (indexOf = str.indexOf("%s", r2)) == -1) {
                break;
            }
            sb5.append((CharSequence) str, r2, indexOf);
            sb5.append(objArr[r0]);
            r2 = indexOf + 2;
            r0++;
        }
        sb5.append((CharSequence) str, r2, str.length());
        if (r0 < length2) {
            sb5.append(" [");
            sb5.append(objArr[r0]);
            for (int r11 = r0 + 1; r11 < objArr.length; r11++) {
                sb5.append(", ");
                sb5.append(objArr[r11]);
            }
            sb5.append(']');
        }
        return sb5.toString();
    }
}
