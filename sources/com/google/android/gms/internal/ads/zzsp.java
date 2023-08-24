package com.google.android.gms.internal.ads;

import android.os.Handler;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzsp {
    public final int zza;
    public final zzsg zzb;
    private final CopyOnWriteArrayList zzc;

    public zzsp() {
        this(new CopyOnWriteArrayList(), 0, null, 0L);
    }

    private zzsp(CopyOnWriteArrayList copyOnWriteArrayList, int r2, zzsg zzsgVar, long j) {
        this.zzc = copyOnWriteArrayList;
        this.zza = r2;
        this.zzb = zzsgVar;
    }

    private static final long zzn(long j) {
        long zzz = zzel.zzz(j);
        return zzz == C1856C.TIME_UNSET ? C1856C.TIME_UNSET : zzz;
    }

    public final zzsp zza(int r7, zzsg zzsgVar, long j) {
        return new zzsp(this.zzc, r7, zzsgVar, 0L);
    }

    public final void zzc(final zzsc zzscVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzso zzsoVar = (zzso) it.next();
            final zzsq zzsqVar = zzsoVar.zzb;
            zzel.zzY(zzsoVar.zza, new Runnable() { // from class: com.google.android.gms.internal.ads.zzsj
                @Override // java.lang.Runnable
                public final void run() {
                    zzsp zzspVar = zzsp.this;
                    zzsqVar.zzaf(zzspVar.zza, zzspVar.zzb, zzscVar);
                }
            });
        }
    }

    public final void zzd(int r12, zzaf zzafVar, int r14, Object obj, long j) {
        zzc(new zzsc(1, r12, zzafVar, 0, null, zzn(j), C1856C.TIME_UNSET));
    }

    public final void zze(final zzrx zzrxVar, final zzsc zzscVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzso zzsoVar = (zzso) it.next();
            final zzsq zzsqVar = zzsoVar.zzb;
            zzel.zzY(zzsoVar.zza, new Runnable() { // from class: com.google.android.gms.internal.ads.zzsk
                @Override // java.lang.Runnable
                public final void run() {
                    zzsp zzspVar = zzsp.this;
                    zzsqVar.zzag(zzspVar.zza, zzspVar.zzb, zzrxVar, zzscVar);
                }
            });
        }
    }

    public final void zzf(zzrx zzrxVar, int r13, int r14, zzaf zzafVar, int r16, Object obj, long j, long j2) {
        zze(zzrxVar, new zzsc(1, -1, null, 0, null, zzn(j), zzn(j2)));
    }

    public final void zzg(final zzrx zzrxVar, final zzsc zzscVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzso zzsoVar = (zzso) it.next();
            final zzsq zzsqVar = zzsoVar.zzb;
            zzel.zzY(zzsoVar.zza, new Runnable() { // from class: com.google.android.gms.internal.ads.zzsn
                @Override // java.lang.Runnable
                public final void run() {
                    zzsp zzspVar = zzsp.this;
                    zzsqVar.zzah(zzspVar.zza, zzspVar.zzb, zzrxVar, zzscVar);
                }
            });
        }
    }

    public final void zzh(zzrx zzrxVar, int r13, int r14, zzaf zzafVar, int r16, Object obj, long j, long j2) {
        zzg(zzrxVar, new zzsc(1, -1, null, 0, null, zzn(j), zzn(j2)));
    }

    public final void zzi(final zzrx zzrxVar, final zzsc zzscVar, final IOException iOException, final boolean z) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzso zzsoVar = (zzso) it.next();
            final zzsq zzsqVar = zzsoVar.zzb;
            zzel.zzY(zzsoVar.zza, new Runnable() { // from class: com.google.android.gms.internal.ads.zzsl
                @Override // java.lang.Runnable
                public final void run() {
                    zzsp zzspVar = zzsp.this;
                    zzsqVar.zzai(zzspVar.zza, zzspVar.zzb, zzrxVar, zzscVar, iOException, z);
                }
            });
        }
    }

    public final void zzj(zzrx zzrxVar, int r13, int r14, zzaf zzafVar, int r16, Object obj, long j, long j2, IOException iOException, boolean z) {
        zzi(zzrxVar, new zzsc(1, -1, null, 0, null, zzn(j), zzn(j2)), iOException, z);
    }

    public final void zzk(final zzrx zzrxVar, final zzsc zzscVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzso zzsoVar = (zzso) it.next();
            final zzsq zzsqVar = zzsoVar.zzb;
            zzel.zzY(zzsoVar.zza, new Runnable() { // from class: com.google.android.gms.internal.ads.zzsm
                @Override // java.lang.Runnable
                public final void run() {
                    zzsp zzspVar = zzsp.this;
                    zzsqVar.zzaj(zzspVar.zza, zzspVar.zzb, zzrxVar, zzscVar);
                }
            });
        }
    }

    public final void zzl(zzrx zzrxVar, int r13, int r14, zzaf zzafVar, int r16, Object obj, long j, long j2) {
        zzk(zzrxVar, new zzsc(1, -1, null, 0, null, zzn(j), zzn(j2)));
    }

    public final void zzm(zzsq zzsqVar) {
        Iterator it = this.zzc.iterator();
        while (it.hasNext()) {
            zzso zzsoVar = (zzso) it.next();
            if (zzsoVar.zzb == zzsqVar) {
                this.zzc.remove(zzsoVar);
            }
        }
    }

    public final void zzb(Handler handler, zzsq zzsqVar) {
        Objects.requireNonNull(zzsqVar);
        this.zzc.add(new zzso(handler, zzsqVar));
    }
}
