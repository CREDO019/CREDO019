package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzail {
    private final String zza;
    private final int zzb;
    private final int zzc;
    private int zzd;
    private String zze;

    public zzail(int r4, int r5, int r6) {
        String str;
        if (r4 != Integer.MIN_VALUE) {
            str = r4 + "/";
        } else {
            str = "";
        }
        this.zza = str;
        this.zzb = r5;
        this.zzc = r6;
        this.zzd = Integer.MIN_VALUE;
        this.zze = "";
    }

    private final void zzd() {
        if (this.zzd == Integer.MIN_VALUE) {
            throw new IllegalStateException("generateNewId() must be called before retrieving ids.");
        }
    }

    public final int zza() {
        zzd();
        return this.zzd;
    }

    public final String zzb() {
        zzd();
        return this.zze;
    }

    public final void zzc() {
        int r0 = this.zzd;
        int r02 = r0 == Integer.MIN_VALUE ? this.zzb : r0 + this.zzc;
        this.zzd = r02;
        String str = this.zza;
        this.zze = str + r02;
    }
}
