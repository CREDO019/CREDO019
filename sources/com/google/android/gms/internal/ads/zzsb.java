package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzsb extends zzrp {
    private final zzsi zza;
    private final boolean zzb;
    private final zzcm zzc;
    private final zzck zzd;
    private zzrz zze;
    private zzry zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;

    public zzsb(zzsi zzsiVar, boolean z) {
        boolean z2;
        this.zza = zzsiVar;
        if (z) {
            zzsiVar.zzu();
            z2 = true;
        } else {
            z2 = false;
        }
        this.zzb = z2;
        this.zzc = new zzcm();
        this.zzd = new zzck();
        zzsiVar.zzG();
        this.zze = zzrz.zzq(zzsiVar.zzz());
    }

    private final Object zzE(Object obj) {
        Object obj2;
        Object obj3;
        obj2 = this.zze.zzf;
        if (obj2 == null || !obj.equals(zzrz.zzd)) {
            return obj;
        }
        obj3 = this.zze.zzf;
        return obj3;
    }

    @RequiresNonNull({"unpreparedMaskingMediaPeriod"})
    private final void zzF(long j) {
        zzry zzryVar = this.zzf;
        int zza = this.zze.zza(zzryVar.zza.zza);
        if (zza == -1) {
            return;
        }
        zzrz zzrzVar = this.zze;
        zzck zzckVar = this.zzd;
        zzrzVar.zzd(zza, zzckVar, false);
        long j2 = zzckVar.zze;
        if (j2 != C1856C.TIME_UNSET && j >= j2) {
            j = Math.max(0L, j2 - 1);
        }
        zzryVar.zzs(j);
    }

    public final zzcn zzA() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final void zzB(zzse zzseVar) {
        ((zzry) zzseVar).zzt();
        if (zzseVar == this.zzf) {
            this.zzf = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    /* renamed from: zzC */
    public final zzry zzD(zzsg zzsgVar, zzwf zzwfVar, long j) {
        zzry zzryVar = new zzry(zzsgVar, zzwfVar, j, null);
        zzryVar.zzu(this.zza);
        if (this.zzh) {
            zzryVar.zzr(zzsgVar.zzc(zzE(zzsgVar.zza)));
        } else {
            this.zzf = zzryVar;
            if (!this.zzg) {
                this.zzg = true;
                zzy(null, this.zza);
            }
        }
        return zzryVar;
    }

    @Override // com.google.android.gms.internal.ads.zzrp, com.google.android.gms.internal.ads.zzrh
    public final void zzn(zzfx zzfxVar) {
        super.zzn(zzfxVar);
        if (this.zzb) {
            return;
        }
        this.zzg = true;
        zzy(null, this.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzrp, com.google.android.gms.internal.ads.zzrh
    public final void zzq() {
        this.zzh = false;
        this.zzg = false;
        super.zzq();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzrp
    public final /* bridge */ /* synthetic */ zzsg zzv(Object obj, zzsg zzsgVar) {
        Object obj2;
        Object obj3;
        Void r2 = (Void) obj;
        Object obj4 = zzsgVar.zza;
        obj2 = this.zze.zzf;
        if (obj2 != null) {
            obj3 = this.zze.zzf;
            if (obj3.equals(obj4)) {
                obj4 = zzrz.zzd;
            }
        }
        return zzsgVar.zzc(obj4);
    }

    @Override // com.google.android.gms.internal.ads.zzrp, com.google.android.gms.internal.ads.zzsi
    public final void zzw() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008e  */
    @Override // com.google.android.gms.internal.ads.zzrp
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ void zzx(java.lang.Object r13, com.google.android.gms.internal.ads.zzsi r14, com.google.android.gms.internal.ads.zzcn r15) {
        /*
            r12 = this;
            java.lang.Void r13 = (java.lang.Void) r13
            boolean r13 = r12.zzh
            r14 = 0
            if (r13 == 0) goto L1c
            com.google.android.gms.internal.ads.zzrz r13 = r12.zze
            com.google.android.gms.internal.ads.zzrz r13 = r13.zzp(r15)
            r12.zze = r13
            com.google.android.gms.internal.ads.zzry r13 = r12.zzf
            if (r13 == 0) goto L9d
            long r0 = r13.zzn()
            r12.zzF(r0)
            goto L9d
        L1c:
            boolean r13 = r15.zzo()
            if (r13 == 0) goto L38
            boolean r13 = r12.zzi
            if (r13 == 0) goto L2d
            com.google.android.gms.internal.ads.zzrz r13 = r12.zze
            com.google.android.gms.internal.ads.zzrz r13 = r13.zzp(r15)
            goto L35
        L2d:
            java.lang.Object r13 = com.google.android.gms.internal.ads.zzcm.zza
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzrz.zzd
            com.google.android.gms.internal.ads.zzrz r13 = com.google.android.gms.internal.ads.zzrz.zzr(r15, r13, r0)
        L35:
            r12.zze = r13
            goto L9d
        L38:
            com.google.android.gms.internal.ads.zzcm r13 = r12.zzc
            r0 = 0
            r1 = 0
            r15.zze(r0, r13, r1)
            com.google.android.gms.internal.ads.zzcm r13 = r12.zzc
            java.lang.Object r13 = r13.zzc
            com.google.android.gms.internal.ads.zzry r3 = r12.zzf
            if (r3 == 0) goto L64
            long r4 = r3.zzq()
            com.google.android.gms.internal.ads.zzrz r6 = r12.zze
            com.google.android.gms.internal.ads.zzsg r3 = r3.zza
            java.lang.Object r3 = r3.zza
            com.google.android.gms.internal.ads.zzck r7 = r12.zzd
            r6.zzn(r3, r7)
            com.google.android.gms.internal.ads.zzrz r3 = r12.zze
            com.google.android.gms.internal.ads.zzcm r6 = r12.zzc
            r3.zze(r0, r6, r1)
            int r0 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r0 == 0) goto L64
            r10 = r4
            goto L65
        L64:
            r10 = r1
        L65:
            com.google.android.gms.internal.ads.zzcm r7 = r12.zzc
            com.google.android.gms.internal.ads.zzck r8 = r12.zzd
            r9 = 0
            r6 = r15
            android.util.Pair r0 = r6.zzl(r7, r8, r9, r10)
            java.lang.Object r1 = r0.first
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r2 = r0.longValue()
            boolean r0 = r12.zzi
            if (r0 == 0) goto L84
            com.google.android.gms.internal.ads.zzrz r13 = r12.zze
            com.google.android.gms.internal.ads.zzrz r13 = r13.zzp(r15)
            goto L88
        L84:
            com.google.android.gms.internal.ads.zzrz r13 = com.google.android.gms.internal.ads.zzrz.zzr(r15, r13, r1)
        L88:
            r12.zze = r13
            com.google.android.gms.internal.ads.zzry r13 = r12.zzf
            if (r13 == 0) goto L9d
            r12.zzF(r2)
            com.google.android.gms.internal.ads.zzsg r13 = r13.zza
            java.lang.Object r14 = r13.zza
            java.lang.Object r14 = r12.zzE(r14)
            com.google.android.gms.internal.ads.zzsg r14 = r13.zzc(r14)
        L9d:
            r13 = 1
            r12.zzi = r13
            r12.zzh = r13
            com.google.android.gms.internal.ads.zzrz r13 = r12.zze
            r12.zzo(r13)
            if (r14 == 0) goto Lb1
            com.google.android.gms.internal.ads.zzry r13 = r12.zzf
            java.util.Objects.requireNonNull(r13)
            r13.zzr(r14)
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzsb.zzx(java.lang.Object, com.google.android.gms.internal.ads.zzsi, com.google.android.gms.internal.ads.zzcn):void");
    }

    @Override // com.google.android.gms.internal.ads.zzsi
    public final zzbg zzz() {
        return this.zza.zzz();
    }
}
