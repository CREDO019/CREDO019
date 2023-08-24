package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzasl implements zzasi {
    private final zzasx[] zza;
    private final zzazd zzb;
    private final zzazb zzc;
    private final Handler zzd;
    private final zzasq zze;
    private final CopyOnWriteArraySet zzf;
    private final zzatc zzg;
    private final zzatb zzh;
    private boolean zzi;
    private boolean zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private boolean zzn;
    private zzatd zzo;
    private Object zzp;
    private zzayp zzq;
    private zzazb zzr;
    private zzasw zzs;
    private zzasn zzt;
    private long zzu;

    public zzasl(zzasx[] zzasxVarArr, zzazd zzazdVar, zzcjt zzcjtVar, byte[] bArr) {
        String str = zzban.zze;
        Log.i("ExoPlayerImpl", "Init ExoPlayerLib/2.4.2 [" + str + "]");
        this.zza = zzasxVarArr;
        Objects.requireNonNull(zzazdVar);
        this.zzb = zzazdVar;
        this.zzj = false;
        this.zzk = 1;
        this.zzf = new CopyOnWriteArraySet();
        zzazb zzazbVar = new zzazb(new zzayt[2], null);
        this.zzc = zzazbVar;
        this.zzo = zzatd.zza;
        this.zzg = new zzatc();
        this.zzh = new zzatb();
        this.zzq = zzayp.zza;
        this.zzr = zzazbVar;
        this.zzs = zzasw.zza;
        zzask zzaskVar = new zzask(this, Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper());
        this.zzd = zzaskVar;
        zzasn zzasnVar = new zzasn(0, 0L);
        this.zzt = zzasnVar;
        this.zze = new zzasq(zzasxVarArr, zzazdVar, zzcjtVar, this.zzj, 0, zzaskVar, zzasnVar, this, null);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final int zza() {
        return this.zzk;
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final long zzb() {
        if (this.zzo.zzh() || this.zzl > 0) {
            return this.zzu;
        }
        this.zzo.zzd(this.zzt.zza, this.zzh, false);
        return zzasd.zzb(0L) + zzasd.zzb(this.zzt.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final long zzc() {
        if (this.zzo.zzh() || this.zzl > 0) {
            return this.zzu;
        }
        this.zzo.zzd(this.zzt.zza, this.zzh, false);
        return zzasd.zzb(0L) + zzasd.zzb(this.zzt.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final long zzd() {
        if (this.zzo.zzh()) {
            return C1856C.TIME_UNSET;
        }
        zzatd zzatdVar = this.zzo;
        zzs();
        return zzasd.zzb(zzatdVar.zzg(0, this.zzg, false).zza);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zze(zzasf zzasfVar) {
        this.zzf.add(zzasfVar);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzf(zzash... zzashVarArr) {
        if (this.zze.zzr()) {
            if (this.zze.zzq(zzashVarArr)) {
                return;
            }
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((zzasf) it.next()).zzc(zzase.zzc(new RuntimeException(new TimeoutException("ExoPlayer3 blockingSendMessages timeout"))));
            }
            return;
        }
        this.zze.zza(zzashVarArr);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzg() {
        this.zze.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzh(int r2) {
        this.zze.zzc(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzi() {
        this.zze.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzj(zzaya zzayaVar) {
        if (!this.zzo.zzh() || this.zzp != null) {
            this.zzo = zzatd.zza;
            this.zzp = null;
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((zzasf) it.next()).zzf(this.zzo, this.zzp);
            }
        }
        if (this.zzi) {
            this.zzi = false;
            this.zzq = zzayp.zza;
            this.zzr = this.zzc;
            this.zzb.zzd(null);
            Iterator it2 = this.zzf.iterator();
            while (it2.hasNext()) {
                ((zzasf) it2.next()).zzg(this.zzq, this.zzr);
            }
        }
        this.zzm++;
        this.zze.zzi(zzayaVar, true);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzk() {
        if (this.zze.zzr()) {
            if (!this.zze.zzs()) {
                Iterator it = this.zzf.iterator();
                while (it.hasNext()) {
                    ((zzasf) it.next()).zzc(zzase.zzc(new RuntimeException(new TimeoutException("ExoPlayer3 release timeout"))));
                }
            }
            this.zzd.removeCallbacksAndMessages(null);
            return;
        }
        this.zze.zzj();
        this.zzd.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzl(zzasf zzasfVar) {
        this.zzf.remove(zzasfVar);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzm(long j) {
        zzs();
        if (this.zzo.zzh() || this.zzo.zzc() > 0) {
            this.zzl++;
            if (!this.zzo.zzh()) {
                this.zzo.zzg(0, this.zzg, false);
                long zza = zzasd.zza(j);
                long j2 = this.zzo.zzd(0, this.zzh, false).zzc;
                if (j2 != C1856C.TIME_UNSET) {
                    int r0 = (zza > j2 ? 1 : (zza == j2 ? 0 : -1));
                }
            }
            this.zzu = j;
            this.zze.zzk(this.zzo, 0, zzasd.zza(j));
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((zzasf) it.next()).zze();
            }
            return;
        }
        throw new zzasu(this.zzo, 0, j);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzn(zzash... zzashVarArr) {
        this.zze.zzl(zzashVarArr);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzo(int r2) {
        this.zze.zzm(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzp(int r2) {
        this.zze.zzn(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzq(boolean z) {
        if (this.zzj != z) {
            this.zzj = z;
            this.zze.zzo(z);
            Iterator it = this.zzf.iterator();
            while (it.hasNext()) {
                ((zzasf) it.next()).zzd(z, this.zzk);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final void zzr() {
        this.zze.zzp();
    }

    public final int zzs() {
        if (!this.zzo.zzh() && this.zzl <= 0) {
            this.zzo.zzd(this.zzt.zza, this.zzh, false);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzt(Message message) {
        switch (message.what) {
            case 0:
                this.zzm--;
                return;
            case 1:
                this.zzk = message.arg1;
                Iterator it = this.zzf.iterator();
                while (it.hasNext()) {
                    ((zzasf) it.next()).zzd(this.zzj, this.zzk);
                }
                return;
            case 2:
                this.zzn = message.arg1 != 0;
                Iterator it2 = this.zzf.iterator();
                while (it2.hasNext()) {
                    ((zzasf) it2.next()).zza(this.zzn);
                }
                return;
            case 3:
                if (this.zzm == 0) {
                    zzaze zzazeVar = (zzaze) message.obj;
                    this.zzi = true;
                    this.zzq = zzazeVar.zza;
                    this.zzr = zzazeVar.zzb;
                    this.zzb.zzd(zzazeVar.zzc);
                    Iterator it3 = this.zzf.iterator();
                    while (it3.hasNext()) {
                        ((zzasf) it3.next()).zzg(this.zzq, this.zzr);
                    }
                    return;
                }
                return;
            case 4:
                int r0 = this.zzl - 1;
                this.zzl = r0;
                if (r0 == 0) {
                    this.zzt = (zzasn) message.obj;
                    if (message.arg1 != 0) {
                        Iterator it4 = this.zzf.iterator();
                        while (it4.hasNext()) {
                            ((zzasf) it4.next()).zze();
                        }
                        return;
                    }
                    return;
                }
                return;
            case 5:
                if (this.zzl == 0) {
                    this.zzt = (zzasn) message.obj;
                    Iterator it5 = this.zzf.iterator();
                    while (it5.hasNext()) {
                        ((zzasf) it5.next()).zze();
                    }
                    return;
                }
                return;
            case 6:
                zzasp zzaspVar = (zzasp) message.obj;
                this.zzl -= zzaspVar.zzd;
                if (this.zzm == 0) {
                    this.zzo = zzaspVar.zza;
                    this.zzp = zzaspVar.zzb;
                    this.zzt = zzaspVar.zzc;
                    Iterator it6 = this.zzf.iterator();
                    while (it6.hasNext()) {
                        ((zzasf) it6.next()).zzf(this.zzo, this.zzp);
                    }
                    return;
                }
                return;
            case 7:
                zzasw zzaswVar = (zzasw) message.obj;
                if (this.zzs.equals(zzaswVar)) {
                    return;
                }
                this.zzs = zzaswVar;
                Iterator it7 = this.zzf.iterator();
                while (it7.hasNext()) {
                    ((zzasf) it7.next()).zzb(zzaswVar);
                }
                return;
            case 8:
                zzase zzaseVar = (zzase) message.obj;
                Iterator it8 = this.zzf.iterator();
                while (it8.hasNext()) {
                    ((zzasf) it8.next()).zzc(zzaseVar);
                }
                return;
            default:
                throw new IllegalStateException();
        }
    }
}
