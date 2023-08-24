package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseArray;
import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxu implements zzaxy, zzauw, zzazs, zzayi {
    private long zzB;
    private int zzD;
    private boolean zzE;
    private boolean zzF;
    private final zzazl zzG;
    private final Uri zza;
    private final zzazi zzb;
    private final int zzc;
    private final Handler zzd;
    private final zzaxv zze;
    private final zzaxz zzf;
    private final long zzg;
    private final zzaxs zzi;
    private zzaxx zzo;
    private zzavc zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private int zzu;
    private zzayp zzv;
    private long zzw;
    private boolean[] zzx;
    private boolean[] zzy;
    private boolean zzz;
    private final zzazw zzh = new zzazw("Loader:ExtractorMediaPeriod");
    private final zzbaa zzj = new zzbaa();
    private final Runnable zzk = new zzaxn(this);
    private final Runnable zzl = new zzaxo(this);
    private final Handler zzm = new Handler();
    private long zzC = C1856C.TIME_UNSET;
    private final SparseArray zzn = new SparseArray();
    private long zzA = -1;

    public zzaxu(Uri uri, zzazi zzaziVar, zzauv[] zzauvVarArr, int r4, Handler handler, zzaxv zzaxvVar, zzaxz zzaxzVar, zzazl zzazlVar, String str, int r10, byte[] bArr) {
        this.zza = uri;
        this.zzb = zzaziVar;
        this.zzc = r4;
        this.zzd = handler;
        this.zze = zzaxvVar;
        this.zzf = zzaxzVar;
        this.zzG = zzazlVar;
        this.zzg = r10;
        this.zzi = new zzaxs(zzauvVarArr, this);
    }

    private final int zzC() {
        int size = this.zzn.size();
        int r2 = 0;
        for (int r1 = 0; r1 < size; r1++) {
            r2 += ((zzayj) this.zzn.valueAt(r1)).zze();
        }
        return r2;
    }

    private final long zzD() {
        int size = this.zzn.size();
        long j = Long.MIN_VALUE;
        for (int r3 = 0; r3 < size; r3++) {
            j = Math.max(j, ((zzayj) this.zzn.valueAt(r3)).zzg());
        }
        return j;
    }

    private final void zzE(zzaxr zzaxrVar) {
        long j;
        if (this.zzA == -1) {
            j = zzaxrVar.zzj;
            this.zzA = j;
        }
    }

    private final void zzF() {
        zzavc zzavcVar;
        zzaxr zzaxrVar = new zzaxr(this, this.zza, this.zzb, this.zzi, this.zzj);
        if (this.zzr) {
            zzazy.zze(zzG());
            long j = this.zzw;
            if (j == C1856C.TIME_UNSET || this.zzC < j) {
                zzaxrVar.zzd(this.zzp.zzb(this.zzC), this.zzC);
                this.zzC = C1856C.TIME_UNSET;
            } else {
                this.zzE = true;
                this.zzC = C1856C.TIME_UNSET;
                return;
            }
        }
        this.zzD = zzC();
        int r0 = this.zzc;
        if (r0 == -1) {
            r0 = (this.zzr && this.zzA == -1 && ((zzavcVar = this.zzp) == null || zzavcVar.zza() == C1856C.TIME_UNSET)) ? 6 : 3;
        }
        this.zzh.zza(zzaxrVar, this, r0);
    }

    private final boolean zzG() {
        return this.zzC != C1856C.TIME_UNSET;
    }

    public static /* bridge */ /* synthetic */ void zzp(zzaxu zzaxuVar) {
        if (zzaxuVar.zzF || zzaxuVar.zzr || zzaxuVar.zzp == null || !zzaxuVar.zzq) {
            return;
        }
        int size = zzaxuVar.zzn.size();
        for (int r2 = 0; r2 < size; r2++) {
            if (((zzayj) zzaxuVar.zzn.valueAt(r2)).zzh() == null) {
                return;
            }
        }
        zzaxuVar.zzj.zzb();
        zzayo[] zzayoVarArr = new zzayo[size];
        zzaxuVar.zzy = new boolean[size];
        zzaxuVar.zzx = new boolean[size];
        zzaxuVar.zzw = zzaxuVar.zzp.zza();
        int r3 = 0;
        while (true) {
            boolean z = true;
            if (r3 < size) {
                zzass zzh = ((zzayj) zzaxuVar.zzn.valueAt(r3)).zzh();
                zzayoVarArr[r3] = new zzayo(zzh);
                String str = zzh.zzf;
                if (!zzbad.zzb(str) && !zzbad.zza(str)) {
                    z = false;
                }
                zzaxuVar.zzy[r3] = z;
                zzaxuVar.zzz = z | zzaxuVar.zzz;
                r3++;
            } else {
                zzaxuVar.zzv = new zzayp(zzayoVarArr);
                zzaxuVar.zzr = true;
                zzaxuVar.zzf.zzg(new zzayn(zzaxuVar.zzw, zzaxuVar.zzp.zzc()), null);
                zzaxuVar.zzo.zzf(zzaxuVar);
                return;
            }
        }
    }

    public final boolean zzA(int r4) {
        return this.zzE || (!zzG() && ((zzayj) this.zzn.valueAt(r4)).zzm());
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x00bc, code lost:
        if (r1 != false) goto L55;
     */
    @Override // com.google.android.gms.internal.ads.zzaxy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zzB(com.google.android.gms.internal.ads.zzayt[] r7, boolean[] r8, com.google.android.gms.internal.ads.zzayk[] r9, boolean[] r10, long r11) {
        /*
            Method dump skipped, instructions count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaxu.zzB(com.google.android.gms.internal.ads.zzayt[], boolean[], com.google.android.gms.internal.ads.zzayk[], boolean[], long):long");
    }

    @Override // com.google.android.gms.internal.ads.zzaxy, com.google.android.gms.internal.ads.zzaym
    public final long zza() {
        if (this.zzu == 0) {
            return Long.MIN_VALUE;
        }
        return zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzauw
    public final void zzb() {
        this.zzq = true;
        this.zzm.post(this.zzk);
    }

    @Override // com.google.android.gms.internal.ads.zzauw
    public final zzave zzbi(int r3, int r4) {
        zzayj zzayjVar = (zzayj) this.zzn.get(r3);
        if (zzayjVar == null) {
            zzayj zzayjVar2 = new zzayj(this.zzG, null);
            zzayjVar2.zzk(this);
            this.zzn.put(r3, zzayjVar2);
            return zzayjVar2;
        }
        return zzayjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy, com.google.android.gms.internal.ads.zzaym
    public final boolean zzbj(long j) {
        if (this.zzE) {
            return false;
        }
        if (this.zzr && this.zzu == 0) {
            return false;
        }
        boolean zzc = this.zzj.zzc();
        if (this.zzh.zzi()) {
            return zzc;
        }
        zzF();
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzauw
    public final void zzc(zzavc zzavcVar) {
        this.zzp = zzavcVar;
        this.zzm.post(this.zzk);
    }

    @Override // com.google.android.gms.internal.ads.zzazs
    public final /* bridge */ /* synthetic */ int zzd(zzazu zzazuVar, long j, long j2, IOException iOException) {
        zzavc zzavcVar;
        zzaxr zzaxrVar = (zzaxr) zzazuVar;
        zzE(zzaxrVar);
        Handler handler = this.zzd;
        if (handler != null) {
            handler.post(new zzaxq(this, iOException));
        }
        if (iOException instanceof zzayq) {
            return 3;
        }
        int zzC = zzC();
        int r9 = this.zzD;
        if (this.zzA == -1 && ((zzavcVar = this.zzp) == null || zzavcVar.zza() == C1856C.TIME_UNSET)) {
            this.zzB = 0L;
            this.zzt = this.zzr;
            int size = this.zzn.size();
            for (int r1 = 0; r1 < size; r1++) {
                ((zzayj) this.zzn.valueAt(r1)).zzj(!this.zzr || this.zzx[r1]);
            }
            zzaxrVar.zzd(0L, 0L);
        }
        this.zzD = zzC();
        return zzC <= r9 ? 0 : 1;
    }

    public final int zze(int r8, zzast zzastVar, zzaun zzaunVar, boolean z) {
        if (this.zzt || zzG()) {
            return -3;
        }
        return ((zzayj) this.zzn.valueAt(r8)).zzf(zzastVar, zzaunVar, z, this.zzE, this.zzB);
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzg() {
        long zzD;
        if (this.zzE) {
            return Long.MIN_VALUE;
        }
        if (zzG()) {
            return this.zzC;
        }
        if (this.zzz) {
            int size = this.zzn.size();
            zzD = Long.MAX_VALUE;
            for (int r5 = 0; r5 < size; r5++) {
                if (this.zzy[r5]) {
                    zzD = Math.min(zzD, ((zzayj) this.zzn.valueAt(r5)).zzg());
                }
            }
        } else {
            zzD = zzD();
        }
        return zzD == Long.MIN_VALUE ? this.zzB : zzD;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzh() {
        if (this.zzt) {
            this.zzt = false;
            return this.zzB;
        }
        return C1856C.TIME_UNSET;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final long zzi(long j) {
        if (true != this.zzp.zzc()) {
            j = 0;
        }
        this.zzB = j;
        int size = this.zzn.size();
        boolean zzG = true ^ zzG();
        int r3 = 0;
        while (true) {
            if (!zzG) {
                this.zzC = j;
                this.zzE = false;
                zzazw zzazwVar = this.zzh;
                if (zzazwVar.zzi()) {
                    zzazwVar.zzf();
                } else {
                    for (int r1 = 0; r1 < size; r1++) {
                        ((zzayj) this.zzn.valueAt(r1)).zzj(this.zzx[r1]);
                    }
                }
            } else if (r3 >= size) {
                break;
            } else {
                if (this.zzx[r3]) {
                    zzG = ((zzayj) this.zzn.valueAt(r3)).zzn(j, false);
                }
                r3++;
            }
        }
        this.zzt = false;
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final zzayp zzn() {
        return this.zzv;
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final void zzq(long j) {
    }

    public final void zzr() throws IOException {
        this.zzh.zzg(Integer.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final void zzs() throws IOException {
        this.zzh.zzg(Integer.MIN_VALUE);
    }

    @Override // com.google.android.gms.internal.ads.zzazs
    public final /* bridge */ /* synthetic */ void zzt(zzazu zzazuVar, long j, long j2, boolean z) {
        zzE((zzaxr) zzazuVar);
        if (z || this.zzu <= 0) {
            return;
        }
        int size = this.zzn.size();
        for (int r2 = 0; r2 < size; r2++) {
            ((zzayj) this.zzn.valueAt(r2)).zzj(this.zzx[r2]);
        }
        this.zzo.zze(this);
    }

    @Override // com.google.android.gms.internal.ads.zzazs
    public final /* bridge */ /* synthetic */ void zzu(zzazu zzazuVar, long j, long j2) {
        zzE((zzaxr) zzazuVar);
        this.zzE = true;
        if (this.zzw == C1856C.TIME_UNSET) {
            long zzD = zzD();
            long j3 = zzD == Long.MIN_VALUE ? 0L : zzD + WorkRequest.MIN_BACKOFF_MILLIS;
            this.zzw = j3;
            this.zzf.zzg(new zzayn(j3, this.zzp.zzc()), null);
        }
        this.zzo.zze(this);
    }

    @Override // com.google.android.gms.internal.ads.zzayi
    public final void zzv(zzass zzassVar) {
        this.zzm.post(this.zzk);
    }

    @Override // com.google.android.gms.internal.ads.zzaxy
    public final void zzw(zzaxx zzaxxVar, long j) {
        this.zzo = zzaxxVar;
        this.zzj.zzc();
        zzF();
    }

    public final void zzx() {
        this.zzh.zzh(new zzaxp(this, this.zzi));
        this.zzm.removeCallbacksAndMessages(null);
        this.zzF = true;
    }

    public final void zzy(int r4, long j) {
        zzayj zzayjVar = (zzayj) this.zzn.valueAt(r4);
        if (!this.zzE || j <= zzayjVar.zzg()) {
            zzayjVar.zzn(j, true);
        } else {
            zzayjVar.zzl();
        }
    }
}
