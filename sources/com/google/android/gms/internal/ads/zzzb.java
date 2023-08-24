package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzb {
    public final String zza;

    private zzzb(int r1, int r2, String str) {
        this.zza = str;
    }

    public static zzzb zza(zzed zzedVar) {
        String str;
        zzedVar.zzG(2);
        int zzk = zzedVar.zzk();
        int r1 = zzk >> 1;
        int zzk2 = (zzedVar.zzk() >> 3) | ((zzk & 1) << 5);
        if (r1 == 4 || r1 == 5 || r1 == 7) {
            str = "dvhe";
        } else if (r1 == 8) {
            str = "hev1";
        } else if (r1 != 9) {
            return null;
        } else {
            str = "avc3";
        }
        String str2 = zzk2 < 10 ? ".0" : ".";
        return new zzzb(r1, zzk2, str + ".0" + r1 + str2 + zzk2);
    }
}
