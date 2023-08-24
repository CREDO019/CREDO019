package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzti implements zzse, zzzi, zzwl, zzwq, zztu {
    private static final Map zzb;
    private static final zzaf zzc;
    private boolean zzA;
    private boolean zzC;
    private boolean zzD;
    private int zzE;
    private long zzG;
    private boolean zzI;
    private int zzJ;
    private boolean zzK;
    private boolean zzL;
    private final zzwj zzM;
    private final zzwf zzN;
    private final Uri zzd;
    private final zzev zze;
    private final zzpo zzf;
    private final zzsp zzg;
    private final zzpi zzh;
    private final zzte zzi;
    private final long zzj;
    private final zzsz zzl;
    private zzsd zzq;
    private zzacj zzr;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private zzth zzx;
    private zzaai zzy;
    private final zzwt zzk = new zzwt("ProgressiveMediaPeriod");
    private final zzdg zzm = new zzdg(zzde.zza);
    private final Runnable zzn = new Runnable() { // from class: com.google.android.gms.internal.ads.zzta
        @Override // java.lang.Runnable
        public final void run() {
            zzti.this.zzS();
        }
    };
    private final Runnable zzo = new Runnable() { // from class: com.google.android.gms.internal.ads.zztb
        @Override // java.lang.Runnable
        public final void run() {
            zzti.this.zzC();
        }
    };
    private final Handler zzp = zzel.zzD(null);
    private zztg[] zzt = new zztg[0];
    private zztv[] zzs = new zztv[0];
    private long zzH = C1856C.TIME_UNSET;
    private long zzF = -1;
    private long zzz = C1856C.TIME_UNSET;
    private int zzB = 1;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_NAME, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        zzb = Collections.unmodifiableMap(hashMap);
        zzad zzadVar = new zzad();
        zzadVar.zzH("icy");
        zzadVar.zzS(MimeTypes.APPLICATION_ICY);
        zzc = zzadVar.zzY();
    }

    public zzti(Uri uri, zzev zzevVar, zzsz zzszVar, zzpo zzpoVar, zzpi zzpiVar, zzwj zzwjVar, zzsp zzspVar, zzte zzteVar, zzwf zzwfVar, String str, int r11, byte[] bArr) {
        this.zzd = uri;
        this.zze = zzevVar;
        this.zzf = zzpoVar;
        this.zzh = zzpiVar;
        this.zzM = zzwjVar;
        this.zzg = zzspVar;
        this.zzi = zzteVar;
        this.zzN = zzwfVar;
        this.zzj = r11;
        this.zzl = zzszVar;
    }

    private final int zzN() {
        int r3 = 0;
        for (zztv zztvVar : this.zzs) {
            r3 += zztvVar.zzc();
        }
        return r3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zzO() {
        long j = Long.MIN_VALUE;
        for (zztv zztvVar : this.zzs) {
            j = Math.max(j, zztvVar.zzg());
        }
        return j;
    }

    private final zzaam zzP(zztg zztgVar) {
        int length = this.zzs.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (zztgVar.equals(this.zzt[r1])) {
                return this.zzs[r1];
            }
        }
        zzwf zzwfVar = this.zzN;
        zzpo zzpoVar = this.zzf;
        zzpi zzpiVar = this.zzh;
        Objects.requireNonNull(zzpoVar);
        zztv zztvVar = new zztv(zzwfVar, zzpoVar, zzpiVar, null);
        zztvVar.zzu(this);
        int r12 = length + 1;
        zztg[] zztgVarArr = (zztg[]) Arrays.copyOf(this.zzt, r12);
        zztgVarArr[length] = zztgVar;
        this.zzt = (zztg[]) zzel.zzac(zztgVarArr);
        zztv[] zztvVarArr = (zztv[]) Arrays.copyOf(this.zzs, r12);
        zztvVarArr[length] = zztvVar;
        this.zzs = (zztv[]) zzel.zzac(zztvVarArr);
        return zztvVar;
    }

    @EnsuresNonNull({"trackState", "seekMap"})
    private final void zzQ() {
        zzdd.zzf(this.zzv);
        Objects.requireNonNull(this.zzx);
        Objects.requireNonNull(this.zzy);
    }

    private final void zzR(zztd zztdVar) {
        if (this.zzF == -1) {
            this.zzF = zztd.zzb(zztdVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzS() {
        zzbq zzc2;
        int r7;
        if (this.zzL || this.zzv || !this.zzu || this.zzy == null) {
            return;
        }
        for (zztv zztvVar : this.zzs) {
            if (zztvVar.zzh() == null) {
                return;
            }
        }
        this.zzm.zzc();
        int length = this.zzs.length;
        zzcp[] zzcpVarArr = new zzcp[length];
        boolean[] zArr = new boolean[length];
        for (int r4 = 0; r4 < length; r4++) {
            zzaf zzh = this.zzs[r4].zzh();
            Objects.requireNonNull(zzh);
            String str = zzh.zzm;
            boolean zzg = zzbt.zzg(str);
            boolean z = zzg || zzbt.zzh(str);
            zArr[r4] = z;
            this.zzw = z | this.zzw;
            zzacj zzacjVar = this.zzr;
            if (zzacjVar != null) {
                if (zzg || this.zzt[r4].zzb) {
                    zzbq zzbqVar = zzh.zzk;
                    if (zzbqVar == null) {
                        zzc2 = new zzbq(zzacjVar);
                    } else {
                        zzc2 = zzbqVar.zzc(zzacjVar);
                    }
                    zzad zzb2 = zzh.zzb();
                    zzb2.zzM(zzc2);
                    zzh = zzb2.zzY();
                }
                if (zzg && zzh.zzg == -1 && zzh.zzh == -1 && (r7 = zzacjVar.zza) != -1) {
                    zzad zzb3 = zzh.zzb();
                    zzb3.zzv(r7);
                    zzh = zzb3.zzY();
                }
            }
            zzcpVarArr[r4] = new zzcp(Integer.toString(r4), zzh.zzc(this.zzf.zza(zzh)));
        }
        this.zzx = new zzth(new zzue(zzcpVarArr), zArr);
        this.zzv = true;
        zzsd zzsdVar = this.zzq;
        Objects.requireNonNull(zzsdVar);
        zzsdVar.zzi(this);
    }

    private final void zzT(int r11) {
        zzQ();
        zzth zzthVar = this.zzx;
        boolean[] zArr = zzthVar.zzd;
        if (zArr[r11]) {
            return;
        }
        zzaf zzb2 = zzthVar.zza.zzb(r11).zzb(0);
        this.zzg.zzd(zzbt.zzb(zzb2.zzm), zzb2, 0, null, this.zzG);
        zArr[r11] = true;
    }

    private final void zzU(int r5) {
        zzQ();
        boolean[] zArr = this.zzx.zzb;
        if (this.zzI && zArr[r5] && !this.zzs[r5].zzx(false)) {
            this.zzH = 0L;
            this.zzI = false;
            this.zzD = true;
            this.zzG = 0L;
            this.zzJ = 0;
            for (zztv zztvVar : this.zzs) {
                zztvVar.zzp(false);
            }
            zzsd zzsdVar = this.zzq;
            Objects.requireNonNull(zzsdVar);
            zzsdVar.zzg(this);
        }
    }

    private final void zzV() {
        zztd zztdVar = new zztd(this, this.zzd, this.zze, this.zzl, this, this.zzm);
        if (this.zzv) {
            zzdd.zzf(zzW());
            long j = this.zzz;
            if (j == C1856C.TIME_UNSET || this.zzH <= j) {
                zzaai zzaaiVar = this.zzy;
                Objects.requireNonNull(zzaaiVar);
                zztd.zzg(zztdVar, zzaaiVar.zzg(this.zzH).zza.zzc, this.zzH);
                for (zztv zztvVar : this.zzs) {
                    zztvVar.zzt(this.zzH);
                }
                this.zzH = C1856C.TIME_UNSET;
            } else {
                this.zzK = true;
                this.zzH = C1856C.TIME_UNSET;
                return;
            }
        }
        this.zzJ = zzN();
        long zza = this.zzk.zza(zztdVar, this, zzwj.zza(this.zzB));
        zzfa zze = zztd.zze(zztdVar);
        this.zzg.zzl(new zzrx(zztd.zzc(zztdVar), zze, zze.zza, Collections.emptyMap(), zza, 0L, 0L), 1, -1, null, 0, null, zztd.zzd(zztdVar), this.zzz);
    }

    private final boolean zzW() {
        return this.zzH != C1856C.TIME_UNSET;
    }

    private final boolean zzX() {
        return this.zzD || zzW();
    }

    @Override // com.google.android.gms.internal.ads.zzzi
    public final void zzB() {
        this.zzu = true;
        this.zzp.post(this.zzn);
    }

    final void zzE() throws IOException {
        this.zzk.zzi(zzwj.zza(this.zzB));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzF(int r2) throws IOException {
        this.zzs[r2].zzm();
        zzE();
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final /* bridge */ /* synthetic */ void zzG(zzwp zzwpVar, long j, long j2, boolean z) {
        zztd zztdVar = (zztd) zzwpVar;
        zzfw zzf = zztd.zzf(zztdVar);
        zzrx zzrxVar = new zzrx(zztd.zzc(zztdVar), zztd.zze(zztdVar), zzf.zzh(), zzf.zzi(), j, j2, zzf.zzg());
        zztd.zzc(zztdVar);
        this.zzg.zzf(zzrxVar, 1, -1, null, 0, null, zztd.zzd(zztdVar), this.zzz);
        if (z) {
            return;
        }
        zzR(zztdVar);
        for (zztv zztvVar : this.zzs) {
            zztvVar.zzp(false);
        }
        if (this.zzE > 0) {
            zzsd zzsdVar = this.zzq;
            Objects.requireNonNull(zzsdVar);
            zzsdVar.zzg(this);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzwl
    public final /* bridge */ /* synthetic */ void zzH(zzwp zzwpVar, long j, long j2) {
        zzaai zzaaiVar;
        if (this.zzz == C1856C.TIME_UNSET && (zzaaiVar = this.zzy) != null) {
            boolean zzh = zzaaiVar.zzh();
            long zzO = zzO();
            long j3 = zzO == Long.MIN_VALUE ? 0L : zzO + WorkRequest.MIN_BACKOFF_MILLIS;
            this.zzz = j3;
            this.zzi.zza(j3, zzh, this.zzA);
        }
        zztd zztdVar = (zztd) zzwpVar;
        zzfw zzf = zztd.zzf(zztdVar);
        zzrx zzrxVar = new zzrx(zztd.zzc(zztdVar), zztd.zze(zztdVar), zzf.zzh(), zzf.zzi(), j, j2, zzf.zzg());
        zztd.zzc(zztdVar);
        this.zzg.zzh(zzrxVar, 1, -1, null, 0, null, zztd.zzd(zztdVar), this.zzz);
        zzR(zztdVar);
        this.zzK = true;
        zzsd zzsdVar = this.zzq;
        Objects.requireNonNull(zzsdVar);
        zzsdVar.zzg(this);
    }

    @Override // com.google.android.gms.internal.ads.zzwq
    public final void zzI() {
        for (zztv zztvVar : this.zzs) {
            zztvVar.zzo();
        }
        this.zzl.zze();
    }

    @Override // com.google.android.gms.internal.ads.zztu
    public final void zzJ(zzaf zzafVar) {
        this.zzp.post(this.zzn);
    }

    public final void zzK() {
        if (this.zzv) {
            for (zztv zztvVar : this.zzs) {
                zztvVar.zzn();
            }
        }
        this.zzk.zzj(this);
        this.zzp.removeCallbacksAndMessages(null);
        this.zzq = null;
        this.zzL = true;
    }

    @Override // com.google.android.gms.internal.ads.zzzi
    public final void zzL(final zzaai zzaaiVar) {
        this.zzp.post(new Runnable() { // from class: com.google.android.gms.internal.ads.zztc
            @Override // java.lang.Runnable
            public final void run() {
                zzti.this.zzD(zzaaiVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzM(int r2) {
        return !zzX() && this.zzs[r2].zzx(this.zzK);
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zza(long j, zzkb zzkbVar) {
        long j2;
        zzQ();
        if (this.zzy.zzh()) {
            zzaag zzg = this.zzy.zzg(j);
            long j3 = zzg.zza.zzb;
            long j4 = zzg.zzb.zzb;
            long j5 = zzkbVar.zzf;
            if (j5 != 0) {
                j2 = j5;
            } else if (zzkbVar.zzg == 0) {
                return j;
            } else {
                j2 = 0;
            }
            long zzx = zzel.zzx(j, j2, Long.MIN_VALUE);
            long zzq = zzel.zzq(j, zzkbVar.zzg, Long.MAX_VALUE);
            boolean z = true;
            boolean z2 = zzx <= j3 && j3 <= zzq;
            z = (zzx > j4 || j4 > zzq) ? false : false;
            if (z2 && z) {
                if (Math.abs(j3 - j) > Math.abs(j4 - j)) {
                    return j4;
                }
            } else if (!z2) {
                return z ? j4 : zzx;
            }
            return j3;
        }
        return 0L;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzb() {
        long j;
        zzQ();
        boolean[] zArr = this.zzx.zzb;
        if (this.zzK) {
            return Long.MIN_VALUE;
        }
        if (zzW()) {
            return this.zzH;
        }
        if (this.zzw) {
            int length = this.zzs.length;
            j = Long.MAX_VALUE;
            for (int r6 = 0; r6 < length; r6++) {
                if (zArr[r6] && !this.zzs[r6].zzw()) {
                    j = Math.min(j, this.zzs[r6].zzg());
                }
            }
        } else {
            j = Long.MAX_VALUE;
        }
        if (j == Long.MAX_VALUE) {
            j = zzO();
        }
        return j == Long.MIN_VALUE ? this.zzG : j;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final long zzc() {
        if (this.zzE == 0) {
            return Long.MIN_VALUE;
        }
        return zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zzd() {
        if (this.zzD) {
            if (this.zzK || zzN() > this.zzJ) {
                this.zzD = false;
                return this.zzG;
            }
            return C1856C.TIME_UNSET;
        }
        return C1856C.TIME_UNSET;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final long zze(long j) {
        int r3;
        zzQ();
        boolean[] zArr = this.zzx.zzb;
        if (true != this.zzy.zzh()) {
            j = 0;
        }
        this.zzD = false;
        this.zzG = j;
        if (zzW()) {
            this.zzH = j;
            return j;
        }
        if (this.zzB != 7) {
            int length = this.zzs.length;
            while (r3 < length) {
                r3 = (this.zzs[r3].zzy(j, false) || (!zArr[r3] && this.zzw)) ? r3 + 1 : 0;
            }
            return j;
        }
        this.zzI = false;
        this.zzH = j;
        this.zzK = false;
        zzwt zzwtVar = this.zzk;
        if (zzwtVar.zzl()) {
            for (zztv zztvVar : this.zzs) {
                zztvVar.zzj();
            }
            this.zzk.zzg();
        } else {
            zzwtVar.zzh();
            for (zztv zztvVar2 : this.zzs) {
                zztvVar2.zzp(false);
            }
        }
        return j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003c, code lost:
        if (r2 == 0) goto L77;
     */
    @Override // com.google.android.gms.internal.ads.zzse
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long zzf(com.google.android.gms.internal.ads.zzvq[] r8, boolean[] r9, com.google.android.gms.internal.ads.zztw[] r10, boolean[] r11, long r12) {
        /*
            Method dump skipped, instructions count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzti.zzf(com.google.android.gms.internal.ads.zzvq[], boolean[], com.google.android.gms.internal.ads.zztw[], boolean[], long):long");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzg(int r4, zzje zzjeVar, zzgg zzggVar, int r7) {
        if (zzX()) {
            return -3;
        }
        zzT(r4);
        int zzd = this.zzs[r4].zzd(zzjeVar, zzggVar, r7, this.zzK);
        if (zzd == -3) {
            zzU(r4);
        }
        return zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final zzue zzh() {
        zzQ();
        return this.zzx.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzi(int r4, long j) {
        if (zzX()) {
            return 0;
        }
        zzT(r4);
        zztv zztvVar = this.zzs[r4];
        int zzb2 = zztvVar.zzb(j, this.zzK);
        zztvVar.zzv(zzb2);
        if (zzb2 == 0) {
            zzU(r4);
            return 0;
        }
        return zzb2;
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzj(long j, boolean z) {
        zzQ();
        if (zzW()) {
            return;
        }
        boolean[] zArr = this.zzx.zzc;
        int length = this.zzs.length;
        for (int r2 = 0; r2 < length; r2++) {
            this.zzs[r2].zzi(j, false, zArr[r2]);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzk() throws IOException {
        zzE();
        if (this.zzK && !this.zzv) {
            throw zzbu.zza("Loading finished before preparation is complete.", null);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzse
    public final void zzl(zzsd zzsdVar, long j) {
        this.zzq = zzsdVar;
        this.zzm.zze();
        zzV();
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final void zzm(long j) {
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzo(long j) {
        if (this.zzK || this.zzk.zzk() || this.zzI) {
            return false;
        }
        if (this.zzv && this.zzE == 0) {
            return false;
        }
        boolean zze = this.zzm.zze();
        if (this.zzk.zzl()) {
            return zze;
        }
        zzV();
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzse, com.google.android.gms.internal.ads.zzty
    public final boolean zzp() {
        return this.zzk.zzl() && this.zzm.zzd();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0103  */
    @Override // com.google.android.gms.internal.ads.zzwl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ com.google.android.gms.internal.ads.zzwn zzt(com.google.android.gms.internal.ads.zzwp r27, long r28, long r30, java.io.IOException r32, int r33) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzti.zzt(com.google.android.gms.internal.ads.zzwp, long, long, java.io.IOException, int):com.google.android.gms.internal.ads.zzwn");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzaam zzu() {
        return zzP(new zztg(0, true));
    }

    @Override // com.google.android.gms.internal.ads.zzzi
    public final zzaam zzv(int r2, int r3) {
        return zzP(new zztg(r2, false));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzC() {
        if (this.zzL) {
            return;
        }
        zzsd zzsdVar = this.zzq;
        Objects.requireNonNull(zzsdVar);
        zzsdVar.zzg(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzD(zzaai zzaaiVar) {
        this.zzy = this.zzr == null ? zzaaiVar : new zzaah(C1856C.TIME_UNSET, 0L);
        this.zzz = zzaaiVar.zze();
        boolean z = false;
        if (this.zzF == -1 && zzaaiVar.zze() == C1856C.TIME_UNSET) {
            z = true;
        }
        this.zzA = z;
        this.zzB = true == z ? 7 : 1;
        this.zzi.zza(this.zzz, zzaaiVar.zzh(), this.zzA);
        if (this.zzv) {
            return;
        }
        zzS();
    }
}
