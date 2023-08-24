package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzkv implements zzcu<zzku> {
    private static zzkv zzagg = new zzkv();
    private final zzcu<zzku> zzagd;

    public static boolean zzjp() {
        return ((zzku) zzagg.get()).zzjp();
    }

    public static boolean zzjq() {
        return ((zzku) zzagg.get()).zzjq();
    }

    private zzkv(zzcu<zzku> zzcuVar) {
        this.zzagd = zzcx.zza(zzcuVar);
    }

    public zzkv() {
        this(zzcx.zzd(new zzkw()));
    }

    @Override // com.google.android.gms.internal.vision.zzcu
    public final /* synthetic */ zzku get() {
        return this.zzagd.get();
    }
}
