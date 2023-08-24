package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.PlaybackException;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zztv implements zzaam {
    private boolean zzA;
    private boolean zzB;
    private zzpp zzC;
    private final zztp zza;
    private final zzpo zzd;
    private final zzpi zze;
    private zztu zzf;
    private zzaf zzg;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private boolean zzv;
    private zzaf zzy;
    private zzaf zzz;
    private final zztr zzb = new zztr();
    private int zzh = 1000;
    private int[] zzi = new int[1000];
    private long[] zzj = new long[1000];
    private long[] zzm = new long[1000];
    private int[] zzl = new int[1000];
    private int[] zzk = new int[1000];
    private zzaal[] zzn = new zzaal[1000];
    private final zzuc zzc = new zzuc(new zzdh() { // from class: com.google.android.gms.internal.ads.zztq
    });
    private long zzs = Long.MIN_VALUE;
    private long zzt = Long.MIN_VALUE;
    private long zzu = Long.MIN_VALUE;
    private boolean zzx = true;
    private boolean zzw = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public zztv(zzwf zzwfVar, zzpo zzpoVar, zzpi zzpiVar, byte[] bArr) {
        this.zzd = zzpoVar;
        this.zze = zzpiVar;
        this.zza = new zztp(zzwfVar, null);
    }

    private final int zzA(int r2) {
        int r0 = this.zzq + r2;
        int r22 = this.zzh;
        return r0 < r22 ? r0 : r0 - r22;
    }

    private final synchronized int zzB(zzje zzjeVar, zzgg zzggVar, boolean z, boolean z2, zztr zztrVar) {
        zzggVar.zzc = false;
        if (!zzJ()) {
            if (!z2 && !this.zzv) {
                zzaf zzafVar = this.zzz;
                if (zzafVar == null || (!z && zzafVar == this.zzg)) {
                    return -3;
                }
                zzG(zzafVar, zzjeVar);
                return -5;
            }
            zzggVar.zzc(4);
            return -4;
        }
        zzaf zzafVar2 = ((zztt) this.zzc.zza(this.zzp + this.zzr)).zza;
        if (!z && zzafVar2 == this.zzg) {
            int zzA = zzA(this.zzr);
            if (!zzK(zzA)) {
                zzggVar.zzc = true;
                return -3;
            }
            zzggVar.zzc(this.zzl[zzA]);
            long j = this.zzm[zzA];
            zzggVar.zzd = j;
            if (j < this.zzs) {
                zzggVar.zza(Integer.MIN_VALUE);
            }
            zztrVar.zza = this.zzk[zzA];
            zztrVar.zzb = this.zzj[zzA];
            zztrVar.zzc = this.zzn[zzA];
            return -4;
        }
        zzG(zzafVar2, zzjeVar);
        return -5;
    }

    private final synchronized long zzC(long j, boolean z, boolean z2) {
        int r13;
        int r12 = this.zzo;
        if (r12 != 0) {
            long[] jArr = this.zzm;
            int r4 = this.zzq;
            if (j >= jArr[r4]) {
                if (z2 && (r13 = this.zzr) != r12) {
                    r12 = r13 + 1;
                }
                int zzz = zzz(r4, r12, j, false);
                if (zzz == -1) {
                    return -1L;
                }
                return zzE(zzz);
            }
        }
        return -1L;
    }

    private final synchronized long zzD() {
        int r0 = this.zzo;
        if (r0 == 0) {
            return -1L;
        }
        return zzE(r0);
    }

    private final synchronized void zzF(long j, int r11, long j2, int r14, zzaal zzaalVar) {
        int r0 = this.zzo;
        if (r0 > 0) {
            int zzA = zzA(r0 - 1);
            zzdd.zzd(this.zzj[zzA] + ((long) this.zzk[zzA]) <= j2);
        }
        this.zzv = (536870912 & r11) != 0;
        this.zzu = Math.max(this.zzu, j);
        int zzA2 = zzA(this.zzo);
        this.zzm[zzA2] = j;
        this.zzj[zzA2] = j2;
        this.zzk[zzA2] = r14;
        this.zzl[zzA2] = r11;
        this.zzn[zzA2] = zzaalVar;
        this.zzi[zzA2] = 0;
        if (this.zzc.zzf() || !((zztt) this.zzc.zzb()).zza.equals(this.zzz)) {
            zzpn zzpnVar = zzpn.zzb;
            zzuc zzucVar = this.zzc;
            int r112 = this.zzp + this.zzo;
            zzaf zzafVar = this.zzz;
            Objects.requireNonNull(zzafVar);
            zzucVar.zzc(r112, new zztt(zzafVar, zzpnVar, null));
        }
        int r9 = this.zzo + 1;
        this.zzo = r9;
        int r10 = this.zzh;
        if (r9 == r10) {
            int r92 = r10 + 1000;
            int[] r113 = new int[r92];
            long[] jArr = new long[r92];
            long[] jArr2 = new long[r92];
            int[] r142 = new int[r92];
            int[] r15 = new int[r92];
            zzaal[] zzaalVarArr = new zzaal[r92];
            int r1 = this.zzq;
            int r102 = r10 - r1;
            System.arraycopy(this.zzj, r1, jArr, 0, r102);
            System.arraycopy(this.zzm, this.zzq, jArr2, 0, r102);
            System.arraycopy(this.zzl, this.zzq, r142, 0, r102);
            System.arraycopy(this.zzk, this.zzq, r15, 0, r102);
            System.arraycopy(this.zzn, this.zzq, zzaalVarArr, 0, r102);
            System.arraycopy(this.zzi, this.zzq, r113, 0, r102);
            int r12 = this.zzq;
            System.arraycopy(this.zzj, 0, jArr, r102, r12);
            System.arraycopy(this.zzm, 0, jArr2, r102, r12);
            System.arraycopy(this.zzl, 0, r142, r102, r12);
            System.arraycopy(this.zzk, 0, r15, r102, r12);
            System.arraycopy(this.zzn, 0, zzaalVarArr, r102, r12);
            System.arraycopy(this.zzi, 0, r113, r102, r12);
            this.zzj = jArr;
            this.zzm = jArr2;
            this.zzl = r142;
            this.zzk = r15;
            this.zzn = zzaalVarArr;
            this.zzi = r113;
            this.zzq = 0;
            this.zzh = r92;
        }
    }

    private final void zzH() {
        if (this.zzC != null) {
            this.zzC = null;
            this.zzg = null;
        }
    }

    private final synchronized void zzI() {
        this.zzr = 0;
        this.zza.zzg();
    }

    private final boolean zzJ() {
        return this.zzr != this.zzo;
    }

    private final boolean zzK(int r3) {
        if (this.zzC != null) {
            return (this.zzl[r3] & 1073741824) != 0 ? false : false;
        }
        return true;
    }

    private final synchronized boolean zzL(zzaf zzafVar) {
        this.zzx = false;
        if (zzel.zzT(zzafVar, this.zzz)) {
            return false;
        }
        if (!this.zzc.zzf() && ((zztt) this.zzc.zzb()).zza.equals(zzafVar)) {
            this.zzz = ((zztt) this.zzc.zzb()).zza;
        } else {
            this.zzz = zzafVar;
        }
        zzaf zzafVar2 = this.zzz;
        this.zzA = zzbt.zzf(zzafVar2.zzm, zzafVar2.zzj);
        this.zzB = false;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zztt zzttVar) {
        zzpn zzpnVar = zzttVar.zzb;
        int r0 = zzpm.zza;
    }

    private final int zzz(int r7, int r8, long j, boolean z) {
        int r1 = -1;
        for (int r2 = 0; r2 < r8; r2++) {
            int r3 = (this.zzm[r7] > j ? 1 : (this.zzm[r7] == j ? 0 : -1));
            if (r3 > 0) {
                break;
            }
            if (!z || (this.zzl[r7] & 1) != 0) {
                r1 = r2;
                if (r3 == 0) {
                    break;
                }
            }
            r7++;
            if (r7 == this.zzh) {
                r7 = 0;
            }
        }
        return r1;
    }

    public final int zza() {
        return this.zzp + this.zzr;
    }

    public final synchronized int zzb(long j, boolean z) {
        int r0 = this.zzr;
        int zzA = zzA(r0);
        if (zzJ() && j >= this.zzm[zzA]) {
            if (j > this.zzu && z) {
                return this.zzo - r0;
            }
            int zzz = zzz(zzA, this.zzo - r0, j, true);
            if (zzz == -1) {
                return 0;
            }
            return zzz;
        }
        return 0;
    }

    public final int zzc() {
        return this.zzp + this.zzo;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r9 != 0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzd(com.google.android.gms.internal.ads.zzje r9, com.google.android.gms.internal.ads.zzgg r10, int r11, boolean r12) {
        /*
            r8 = this;
            r0 = r11 & 2
            r1 = 1
            if (r0 == 0) goto L7
            r5 = 1
            goto L9
        L7:
            r0 = 0
            r5 = 0
        L9:
            com.google.android.gms.internal.ads.zztr r7 = r8.zzb
            r2 = r8
            r3 = r9
            r4 = r10
            r6 = r12
            int r9 = r2.zzB(r3, r4, r5, r6, r7)
            r12 = -4
            if (r9 != r12) goto L3e
            boolean r9 = r10.zzg()
            if (r9 != 0) goto L3d
            r9 = r11 & 1
            r11 = r11 & 4
            if (r11 != 0) goto L34
            if (r9 == 0) goto L2c
            com.google.android.gms.internal.ads.zztp r9 = r8.zza
            com.google.android.gms.internal.ads.zztr r11 = r8.zzb
            r9.zzd(r10, r11)
            goto L3d
        L2c:
            com.google.android.gms.internal.ads.zztp r9 = r8.zza
            com.google.android.gms.internal.ads.zztr r11 = r8.zzb
            r9.zze(r10, r11)
            goto L37
        L34:
            if (r9 == 0) goto L37
            goto L3d
        L37:
            int r9 = r8.zzr
            int r9 = r9 + r1
            r8.zzr = r9
            return r12
        L3d:
            r9 = -4
        L3e:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztv.zzd(com.google.android.gms.internal.ads.zzje, com.google.android.gms.internal.ads.zzgg, int, boolean):int");
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final /* synthetic */ int zze(zzr zzrVar, int r2, boolean z) {
        return zzaak.zza(this, zzrVar, r2, z);
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final int zzf(zzr zzrVar, int r2, boolean z, int r4) throws IOException {
        return this.zza.zza(zzrVar, r2, z);
    }

    public final synchronized long zzg() {
        return this.zzu;
    }

    public final synchronized zzaf zzh() {
        if (this.zzx) {
            return null;
        }
        return this.zzz;
    }

    public final void zzi(long j, boolean z, boolean z2) {
        this.zza.zzc(zzC(j, false, z2));
    }

    public final void zzj() {
        this.zza.zzc(zzD());
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final void zzk(zzaf zzafVar) {
        this.zzy = zzafVar;
        boolean zzL = zzL(zzafVar);
        zztu zztuVar = this.zzf;
        if (zztuVar == null || !zzL) {
            return;
        }
        zztuVar.zzJ(zzafVar);
    }

    public final void zzm() throws IOException {
        zzpp zzppVar = this.zzC;
        if (zzppVar != null) {
            throw zzppVar.zza();
        }
    }

    public final void zzn() {
        zzj();
        zzH();
    }

    public final void zzo() {
        zzp(true);
        zzH();
    }

    public final void zzp(boolean z) {
        this.zza.zzf();
        this.zzo = 0;
        this.zzp = 0;
        this.zzq = 0;
        this.zzr = 0;
        this.zzw = true;
        this.zzs = Long.MIN_VALUE;
        this.zzt = Long.MIN_VALUE;
        this.zzu = Long.MIN_VALUE;
        this.zzv = false;
        this.zzc.zzd();
        if (z) {
            this.zzy = null;
            this.zzz = null;
            this.zzx = true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final /* synthetic */ void zzq(zzed zzedVar, int r2) {
        zzaak.zzb(this, zzedVar, r2);
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final void zzr(zzed zzedVar, int r2, int r3) {
        this.zza.zzh(zzedVar, r2);
    }

    @Override // com.google.android.gms.internal.ads.zzaam
    public final void zzs(long j, int r11, int r12, int r13, zzaal zzaalVar) {
        int r0 = r11 & 1;
        if (this.zzw) {
            if (r0 == 0) {
                return;
            }
            this.zzw = false;
        }
        if (this.zzA) {
            if (j < this.zzs) {
                return;
            }
            if (r0 == 0) {
                if (!this.zzB) {
                    Log.w("SampleQueue", "Overriding unexpected non-sync sample for format: ".concat(String.valueOf(String.valueOf(this.zzz))));
                    this.zzB = true;
                }
                r11 |= 1;
            }
        }
        zzF(j, r11, (this.zza.zzb() - r12) - r13, r12, zzaalVar);
    }

    public final void zzt(long j) {
        this.zzs = j;
    }

    public final void zzu(zztu zztuVar) {
        this.zzf = zztuVar;
    }

    public final synchronized void zzv(int r4) {
        boolean z = false;
        if (r4 >= 0) {
            try {
                if (this.zzr + r4 <= this.zzo) {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        zzdd.zzd(z);
        this.zzr += r4;
    }

    public final synchronized boolean zzw() {
        return this.zzv;
    }

    public final synchronized boolean zzx(boolean z) {
        boolean z2 = true;
        if (zzJ()) {
            if (((zztt) this.zzc.zza(this.zzp + this.zzr)).zza != this.zzg) {
                return true;
            }
            return zzK(zzA(this.zzr));
        }
        if (!z && !this.zzv) {
            zzaf zzafVar = this.zzz;
            if (zzafVar == null) {
                z2 = false;
            } else if (zzafVar == this.zzg) {
                return false;
            }
        }
        return z2;
    }

    public final synchronized boolean zzy(long j, boolean z) {
        zzI();
        int r0 = this.zzr;
        int zzA = zzA(r0);
        if (!zzJ() || j < this.zzm[zzA] || (j > this.zzu && !z)) {
            return false;
        }
        int zzz = zzz(zzA, this.zzo - r0, j, true);
        if (zzz == -1) {
            return false;
        }
        this.zzs = j;
        this.zzr += zzz;
        return true;
    }

    private final void zzG(zzaf zzafVar, zzje zzjeVar) {
        zzaf zzafVar2 = this.zzg;
        zzx zzxVar = zzafVar2 == null ? null : zzafVar2.zzp;
        this.zzg = zzafVar;
        zzx zzxVar2 = zzafVar.zzp;
        zzjeVar.zza = zzafVar.zzc(this.zzd.zza(zzafVar));
        zzjeVar.zzb = this.zzC;
        if (zzafVar2 == null || !zzel.zzT(zzxVar, zzxVar2)) {
            zzpp zzppVar = zzafVar.zzp != null ? new zzpp(new zzpg(new zzpr(1), PlaybackException.ERROR_CODE_DRM_SCHEME_UNSUPPORTED)) : null;
            this.zzC = zzppVar;
            zzjeVar.zzb = zzppVar;
        }
    }

    private final long zzE(int r12) {
        int r122;
        long j = this.zzt;
        long j2 = Long.MIN_VALUE;
        if (r12 != 0) {
            int zzA = zzA(r12 - 1);
            for (int r7 = 0; r7 < r12; r7++) {
                j2 = Math.max(j2, this.zzm[zzA]);
                if ((this.zzl[zzA] & 1) != 0) {
                    break;
                }
                zzA--;
                if (zzA == -1) {
                    zzA = this.zzh - 1;
                }
            }
        }
        this.zzt = Math.max(j, j2);
        this.zzo -= r12;
        int r0 = this.zzp + r12;
        this.zzp = r0;
        int r1 = this.zzq + r12;
        this.zzq = r1;
        int r3 = this.zzh;
        if (r1 >= r3) {
            this.zzq = r1 - r3;
        }
        int r13 = this.zzr - r12;
        this.zzr = r13;
        if (r13 < 0) {
            this.zzr = 0;
        }
        this.zzc.zze(r0);
        if (this.zzo == 0) {
            int r123 = this.zzq;
            if (r123 == 0) {
                r123 = this.zzh;
            }
            return this.zzj[r123 - 1] + this.zzk[r122];
        }
        return this.zzj[this.zzq];
    }
}
