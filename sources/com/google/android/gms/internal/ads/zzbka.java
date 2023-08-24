package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public class zzbka {
    private final String zza;
    private final Object zzb;
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzbka(String str, Object obj, int r3) {
        this.zza = str;
        this.zzb = obj;
        this.zzc = r3;
    }

    public static zzbka zza(String str, double d) {
        return new zzbka(str, Double.valueOf(d), 3);
    }

    public static zzbka zzb(String str, long j) {
        return new zzbka(str, Long.valueOf(j), 2);
    }

    public static zzbka zzc(String str, String str2) {
        return new zzbka(str, str2, 4);
    }

    public static zzbka zzd(String str, boolean z) {
        return new zzbka(str, Boolean.valueOf(z), 1);
    }

    public final Object zze() {
        zzbld zza = zzblf.zza();
        if (zza == null) {
            if (zzblf.zzb() != null) {
                zzblf.zzb().zza();
            }
            return this.zzb;
        }
        int r1 = this.zzc - 1;
        if (r1 != 0) {
            if (r1 != 1) {
                if (r1 != 2) {
                    return zza.zzd(this.zza, (String) this.zzb);
                }
                return zza.zzb(this.zza, ((Double) this.zzb).doubleValue());
            }
            return zza.zzc(this.zza, ((Long) this.zzb).longValue());
        }
        return zza.zza(this.zza, ((Boolean) this.zzb).booleanValue());
    }
}
