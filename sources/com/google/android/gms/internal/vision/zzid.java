package com.google.android.gms.internal.vision;

import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzid {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(zzic zzicVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzicVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x01f2, code lost:
        if (((java.lang.Boolean) r11).booleanValue() == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01f4, code lost:
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0204, code lost:
        if (((java.lang.Integer) r11).intValue() == 0) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0215, code lost:
        if (((java.lang.Float) r11).floatValue() == 0.0f) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0227, code lost:
        if (((java.lang.Double) r11).doubleValue() == 0.0d) goto L79;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zza(com.google.android.gms.internal.vision.zzic r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instructions count: 707
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzid.zza(com.google.android.gms.internal.vision.zzic, java.lang.StringBuilder, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zza(StringBuilder sb, int r7, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zza(sb, r7, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zza(sb, r7, str, entry);
            }
        } else {
            sb.append('\n');
            int r0 = 0;
            for (int r1 = 0; r1 < r7; r1++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzjf.zzd(zzfh.zzw((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzfh) {
                sb.append(": \"");
                sb.append(zzjf.zzd((zzfh) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzgs) {
                sb.append(" {");
                zza((zzgs) obj, sb, r7 + 2);
                sb.append("\n");
                while (r0 < r7) {
                    sb.append(' ');
                    r0++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int r8 = r7 + 2;
                zza(sb, r8, "key", entry2.getKey());
                zza(sb, r8, "value", entry2.getValue());
                sb.append("\n");
                while (r0 < r7) {
                    sb.append(' ');
                    r0++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    private static final String zzz(String str) {
        StringBuilder sb = new StringBuilder();
        for (int r1 = 0; r1 < str.length(); r1++) {
            char charAt = str.charAt(r1);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
