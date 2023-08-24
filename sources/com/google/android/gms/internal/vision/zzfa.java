package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzfa {
    private static final Class<?> zzrm = zzv("libcore.io.Memory");
    private static final boolean zzrn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzdr() {
        return (zzrm == null || zzrn) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> zzds() {
        return zzrm;
    }

    private static <T> Class<T> zzv(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        zzrn = zzv("org.robolectric.Robolectric") != null;
    }
}
