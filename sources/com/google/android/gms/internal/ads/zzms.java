package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzms {
    final /* synthetic */ zzmt zza;
    private final String zzb;
    private int zzc;
    private long zzd;
    private zzsg zze;
    private boolean zzf;
    private boolean zzg;

    public zzms(zzmt zzmtVar, String str, int r3, zzsg zzsgVar) {
        this.zza = zzmtVar;
        this.zzb = str;
        this.zzc = r3;
        this.zzd = zzsgVar == null ? -1L : zzsgVar.zzd;
        if (zzsgVar == null || !zzsgVar.zzb()) {
            return;
        }
        this.zze = zzsgVar;
    }

    public final void zzg(int r6, zzsg zzsgVar) {
        if (this.zzd == -1 && r6 == this.zzc && zzsgVar != null) {
            this.zzd = zzsgVar.zzd;
        }
    }

    public final boolean zzj(int r8, zzsg zzsgVar) {
        if (zzsgVar == null) {
            return r8 == this.zzc;
        }
        zzsg zzsgVar2 = this.zze;
        return zzsgVar2 == null ? !zzsgVar.zzb() && zzsgVar.zzd == this.zzd : zzsgVar.zzd == zzsgVar2.zzd && zzsgVar.zzb == zzsgVar2.zzb && zzsgVar.zzc == zzsgVar2.zzc;
    }

    public final boolean zzk(zzkn zzknVar) {
        long j = this.zzd;
        if (j == -1) {
            return false;
        }
        zzsg zzsgVar = zzknVar.zzd;
        if (zzsgVar == null) {
            return this.zzc != zzknVar.zzc;
        } else if (zzsgVar.zzd > j) {
            return true;
        } else {
            if (this.zze == null) {
                return false;
            }
            int zza = zzknVar.zzb.zza(zzsgVar.zza);
            int zza2 = zzknVar.zzb.zza(this.zze.zza);
            zzsg zzsgVar2 = zzknVar.zzd;
            if (zzsgVar2.zzd < this.zze.zzd || zza < zza2) {
                return false;
            }
            if (zza > zza2) {
                return true;
            }
            if (zzsgVar2.zzb()) {
                zzsg zzsgVar3 = zzknVar.zzd;
                int r0 = zzsgVar3.zzb;
                int r11 = zzsgVar3.zzc;
                zzsg zzsgVar4 = this.zze;
                int r3 = zzsgVar4.zzb;
                return r0 > r3 || (r0 == r3 && r11 > zzsgVar4.zzc);
            }
            int r112 = zzknVar.zzd.zze;
            return r112 == -1 || r112 > this.zze.zzb;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r0 < r8.zzc()) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzl(com.google.android.gms.internal.ads.zzcn r7, com.google.android.gms.internal.ads.zzcn r8) {
        /*
            r6 = this;
            int r0 = r6.zzc
            int r1 = r7.zzc()
            r2 = 0
            r3 = -1
            if (r0 < r1) goto L13
            int r7 = r8.zzc()
            if (r0 >= r7) goto L11
            goto L4a
        L11:
            r0 = -1
            goto L4a
        L13:
            com.google.android.gms.internal.ads.zzmt r1 = r6.zza
            com.google.android.gms.internal.ads.zzcm r1 = com.google.android.gms.internal.ads.zzmt.zzb(r1)
            r4 = 0
            r7.zze(r0, r1, r4)
            com.google.android.gms.internal.ads.zzmt r0 = r6.zza
            com.google.android.gms.internal.ads.zzcm r0 = com.google.android.gms.internal.ads.zzmt.zzb(r0)
            int r0 = r0.zzo
        L26:
            com.google.android.gms.internal.ads.zzmt r1 = r6.zza
            com.google.android.gms.internal.ads.zzcm r1 = com.google.android.gms.internal.ads.zzmt.zzb(r1)
            int r1 = r1.zzp
            if (r0 > r1) goto L11
            java.lang.Object r1 = r7.zzf(r0)
            int r1 = r8.zza(r1)
            if (r1 == r3) goto L47
            com.google.android.gms.internal.ads.zzmt r7 = r6.zza
            com.google.android.gms.internal.ads.zzck r7 = com.google.android.gms.internal.ads.zzmt.zza(r7)
            com.google.android.gms.internal.ads.zzck r7 = r8.zzd(r1, r7, r2)
            int r0 = r7.zzd
            goto L4a
        L47:
            int r0 = r0 + 1
            goto L26
        L4a:
            r6.zzc = r0
            if (r0 != r3) goto L4f
            return r2
        L4f:
            com.google.android.gms.internal.ads.zzsg r7 = r6.zze
            r0 = 1
            if (r7 != 0) goto L55
            return r0
        L55:
            java.lang.Object r7 = r7.zza
            int r7 = r8.zza(r7)
            if (r7 == r3) goto L5e
            return r0
        L5e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzms.zzl(com.google.android.gms.internal.ads.zzcn, com.google.android.gms.internal.ads.zzcn):boolean");
    }
}
