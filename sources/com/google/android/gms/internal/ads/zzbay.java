package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbay {
    private final zzbax zza;
    private final boolean zzb;
    private final long zzc;
    private final long zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private boolean zzh;
    private long zzi;
    private long zzj;
    private long zzk;

    public zzbay() {
        this(-1.0d);
    }

    private final boolean zzd(long j, long j2) {
        return Math.abs((j2 - this.zzi) - (j - this.zzj)) > 20000000;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zza(long r12, long r14) {
        /*
            r11 = this;
            r0 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r12
            boolean r2 = r11.zzh
            if (r2 == 0) goto L42
            long r2 = r11.zze
            int r4 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r4 == 0) goto L19
            long r2 = r11.zzk
            r4 = 1
            long r2 = r2 + r4
            r11.zzk = r2
            long r2 = r11.zzg
            r11.zzf = r2
        L19:
            long r2 = r11.zzk
            r4 = 6
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L3a
            long r4 = r11.zzj
            long r4 = r0 - r4
            long r4 = r4 / r2
            long r2 = r11.zzf
            long r2 = r2 + r4
            boolean r4 = r11.zzd(r2, r14)
            if (r4 == 0) goto L33
            r11.zzh = r6
            goto L42
        L33:
            long r4 = r11.zzi
            long r4 = r4 + r2
            long r6 = r11.zzj
            long r4 = r4 - r6
            goto L44
        L3a:
            boolean r2 = r11.zzd(r0, r14)
            if (r2 == 0) goto L42
            r11.zzh = r6
        L42:
            r4 = r14
            r2 = r0
        L44:
            boolean r6 = r11.zzh
            r7 = 0
            if (r6 != 0) goto L53
            r11.zzj = r0
            r11.zzi = r14
            r11.zzk = r7
            r14 = 1
            r11.zzh = r14
        L53:
            r11.zze = r12
            r11.zzg = r2
            com.google.android.gms.internal.ads.zzbax r12 = r11.zza
            if (r12 == 0) goto L86
            long r12 = r12.zza
            int r14 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r14 != 0) goto L62
            goto L86
        L62:
            com.google.android.gms.internal.ads.zzbax r12 = r11.zza
            long r12 = r12.zza
            long r14 = r11.zzc
            long r0 = r4 - r12
            long r0 = r0 / r14
            long r0 = r0 * r14
            long r12 = r12 + r0
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 > 0) goto L75
            long r14 = r12 - r14
            goto L79
        L75:
            long r14 = r14 + r12
            r9 = r12
            r12 = r14
            r14 = r9
        L79:
            long r0 = r12 - r4
            long r4 = r4 - r14
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 >= 0) goto L81
            goto L82
        L81:
            r12 = r14
        L82:
            long r14 = r11.zzd
            long r12 = r12 - r14
            return r12
        L86:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbay.zza(long, long):long");
    }

    public final void zzb() {
        if (this.zzb) {
            this.zza.zzc();
        }
    }

    public final void zzc() {
        this.zzh = false;
        if (this.zzb) {
            this.zza.zzb();
        }
    }

    private zzbay(double d) {
        long j;
        boolean z = d != -1.0d;
        this.zzb = z;
        if (z) {
            this.zza = zzbax.zza();
            long j2 = (long) (1.0E9d / d);
            this.zzc = j2;
            j = (j2 * 80) / 100;
        } else {
            this.zza = null;
            j = -1;
            this.zzc = -1L;
        }
        this.zzd = j;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public zzbay(android.content.Context r3) {
        /*
            r2 = this;
            java.lang.String r0 = "window"
            java.lang.Object r3 = r3.getSystemService(r0)
            android.view.WindowManager r3 = (android.view.WindowManager) r3
            android.view.Display r0 = r3.getDefaultDisplay()
            if (r0 == 0) goto L18
            android.view.Display r3 = r3.getDefaultDisplay()
            float r3 = r3.getRefreshRate()
            double r0 = (double) r3
            goto L1a
        L18:
            r0 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L1a:
            r2.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbay.<init>(android.content.Context):void");
    }
}
