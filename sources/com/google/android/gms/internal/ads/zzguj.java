package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzguj {
    public static zzguj zzb(Class cls) {
        if (System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik")) {
            return new zzgue(cls.getSimpleName());
        }
        return new zzgug(cls.getSimpleName());
    }

    public abstract void zza(String str);
}
