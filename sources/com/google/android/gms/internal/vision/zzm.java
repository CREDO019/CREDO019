package com.google.android.gms.internal.vision;

import com.google.android.gms.vision.C2148L;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzm {
    private static final Object sLock = new Object();
    private static final HashMap<String, Integer> zzbx = new HashMap<>();

    public static boolean zza(String str, String str2) {
        synchronized (sLock) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(str2);
            String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            HashMap<String, Integer> hashMap = zzbx;
            int intValue = hashMap.containsKey(concat) ? hashMap.get(concat).intValue() : 0;
            if ((intValue & 1) != 0) {
                return true;
            }
            try {
                System.loadLibrary(str2);
                hashMap.put(concat, Integer.valueOf(intValue | 1));
                return true;
            } catch (UnsatisfiedLinkError e) {
                if ((intValue & 4) == 0) {
                    C2148L.m1079e(e, "System.loadLibrary failed: %s", str2);
                    zzbx.put(concat, Integer.valueOf(intValue | 4));
                }
                return false;
            }
        }
    }
}
