package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzos implements zznw {
    private boolean zzA;
    private boolean zzB;
    private long zzC;
    private float zzD;
    private zzne[] zzE;
    private ByteBuffer[] zzF;
    private ByteBuffer zzG;
    private int zzH;
    private ByteBuffer zzI;
    private byte[] zzJ;
    private int zzK;
    private int zzL;
    private boolean zzM;
    private boolean zzN;
    private boolean zzO;
    private boolean zzP;
    private int zzQ;
    private zzl zzR;
    private long zzS;
    private boolean zzT;
    private boolean zzU;
    private final zzoi zzV;
    private final zznb zza;
    private final zzoc zzb;
    private final zzpd zzc;
    private final zzne[] zzd;
    private final zzne[] zze;
    private final ConditionVariable zzf;
    private final zzoa zzg;
    private final ArrayDeque zzh;
    private zzoq zzi;
    private final zzol zzj;
    private final zzol zzk;
    private final zzof zzl;
    private zzmz zzm;
    private zznt zzn;
    private zzoh zzo;
    private zzoh zzp;
    private AudioTrack zzq;
    private zzk zzr;
    private zzok zzs;
    private zzok zzt;
    private final zzby zzu;
    private long zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private int zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzos(zzog zzogVar, zzor zzorVar) {
        zznb zznbVar;
        zzoi zzoiVar;
        zznbVar = zzogVar.zzb;
        this.zza = zznbVar;
        zzoiVar = zzogVar.zzc;
        this.zzV = zzoiVar;
        int r2 = zzel.zza;
        this.zzl = zzogVar.zza;
        this.zzf = new ConditionVariable(true);
        this.zzg = new zzoa(new zzon(this, null));
        zzoc zzocVar = new zzoc();
        this.zzb = zzocVar;
        zzpd zzpdVar = new zzpd();
        this.zzc = zzpdVar;
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new zzoz(), zzocVar, zzpdVar);
        Collections.addAll(arrayList, zzoiVar.zze());
        this.zzd = (zzne[]) arrayList.toArray(new zzne[0]);
        this.zze = new zzne[]{new zzov()};
        this.zzD = 1.0f;
        this.zzr = zzk.zza;
        this.zzQ = 0;
        this.zzR = new zzl(0, 0.0f);
        this.zzt = new zzok(zzby.zza, false, 0L, 0L, null);
        this.zzu = zzby.zza;
        this.zzL = -1;
        this.zzE = new zzne[0];
        this.zzF = new ByteBuffer[0];
        this.zzh = new ArrayDeque();
        this.zzj = new zzol(100L);
        this.zzk = new zzol(100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zzE() {
        zzoh zzohVar = this.zzp;
        return zzohVar.zzc == 0 ? this.zzv / zzohVar.zzb : this.zzw;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zzF() {
        zzoh zzohVar = this.zzp;
        return zzohVar.zzc == 0 ? this.zzx / zzohVar.zzd : this.zzy;
    }

    private final AudioTrack zzG(zzoh zzohVar) throws zzns {
        try {
            return zzohVar.zzb(false, this.zzr, this.zzQ);
        } catch (zzns e) {
            zznt zzntVar = this.zzn;
            if (zzntVar != null) {
                zzntVar.zza(e);
            }
            throw e;
        }
    }

    private final zzok zzH() {
        zzok zzokVar = this.zzs;
        return zzokVar != null ? zzokVar : !this.zzh.isEmpty() ? (zzok) this.zzh.getLast() : this.zzt;
    }

    private final void zzI(long j) {
        zzby zzbyVar;
        boolean z;
        if (zzT()) {
            zzoi zzoiVar = this.zzV;
            zzbyVar = zzH().zza;
            zzoiVar.zzc(zzbyVar);
        } else {
            zzbyVar = zzby.zza;
        }
        zzby zzbyVar2 = zzbyVar;
        if (zzT()) {
            zzoi zzoiVar2 = this.zzV;
            boolean z2 = zzH().zzb;
            zzoiVar2.zzd(z2);
            z = z2;
        } else {
            z = false;
        }
        this.zzh.add(new zzok(zzbyVar2, z, Math.max(0L, j), this.zzp.zza(zzF()), null));
        zzne[] zzneVarArr = this.zzp.zzi;
        ArrayList arrayList = new ArrayList();
        for (zzne zzneVar : zzneVarArr) {
            if (zzneVar.zzg()) {
                arrayList.add(zzneVar);
            } else {
                zzneVar.zzc();
            }
        }
        int size = arrayList.size();
        this.zzE = (zzne[]) arrayList.toArray(new zzne[size]);
        this.zzF = new ByteBuffer[size];
        zzJ();
        zznt zzntVar = this.zzn;
        if (zzntVar != null) {
            zzoy.zzU(((zzox) zzntVar).zza).zzs(z);
        }
    }

    private final void zzJ() {
        int r0 = 0;
        while (true) {
            zzne[] zzneVarArr = this.zzE;
            if (r0 >= zzneVarArr.length) {
                return;
            }
            zzne zzneVar = zzneVarArr[r0];
            zzneVar.zzc();
            this.zzF[r0] = zzneVar.zzb();
            r0++;
        }
    }

    private final void zzK() {
        if (this.zzp.zzc()) {
            this.zzT = true;
        }
    }

    private final void zzL() {
        if (this.zzN) {
            return;
        }
        this.zzN = true;
        this.zzg.zzc(zzF());
        this.zzq.stop();
    }

    private final void zzM(long j) throws zznv {
        ByteBuffer byteBuffer;
        int length = this.zzE.length;
        int r1 = length;
        while (r1 >= 0) {
            if (r1 > 0) {
                byteBuffer = this.zzF[r1 - 1];
            } else {
                byteBuffer = this.zzG;
                if (byteBuffer == null) {
                    byteBuffer = zzne.zza;
                }
            }
            if (r1 == length) {
                zzP(byteBuffer, j);
            } else {
                zzne zzneVar = this.zzE[r1];
                if (r1 > this.zzL) {
                    zzneVar.zze(byteBuffer);
                }
                ByteBuffer zzb = zzneVar.zzb();
                this.zzF[r1] = zzb;
                if (zzb.hasRemaining()) {
                    r1++;
                }
            }
            if (byteBuffer.hasRemaining()) {
                return;
            }
            r1--;
        }
    }

    private final void zzN(zzby zzbyVar, boolean z) {
        zzok zzH = zzH();
        if (zzbyVar.equals(zzH.zza) && z == zzH.zzb) {
            return;
        }
        zzok zzokVar = new zzok(zzbyVar, z, C1856C.TIME_UNSET, C1856C.TIME_UNSET, null);
        if (zzR()) {
            this.zzs = zzokVar;
        } else {
            this.zzt = zzokVar;
        }
    }

    private final void zzO() {
        if (zzR()) {
            if (zzel.zza >= 21) {
                this.zzq.setVolume(this.zzD);
                return;
            }
            AudioTrack audioTrack = this.zzq;
            float f = this.zzD;
            audioTrack.setStereoVolume(f, f);
        }
    }

    private final void zzP(ByteBuffer byteBuffer, long j) throws zznv {
        int write;
        zznt zzntVar;
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.zzI;
            if (byteBuffer2 != null) {
                zzdd.zzd(byteBuffer2 == byteBuffer);
            } else {
                this.zzI = byteBuffer;
                if (zzel.zza < 21) {
                    int remaining = byteBuffer.remaining();
                    byte[] bArr = this.zzJ;
                    if (bArr == null || bArr.length < remaining) {
                        this.zzJ = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.zzJ, 0, remaining);
                    byteBuffer.position(position);
                    this.zzK = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (zzel.zza < 21) {
                int zza = this.zzg.zza(this.zzx);
                if (zza > 0) {
                    write = this.zzq.write(this.zzJ, this.zzK, Math.min(remaining2, zza));
                    if (write > 0) {
                        this.zzK += write;
                        byteBuffer.position(byteBuffer.position() + write);
                    }
                } else {
                    write = 0;
                }
            } else {
                write = this.zzq.write(byteBuffer, remaining2, 1);
            }
            this.zzS = SystemClock.elapsedRealtime();
            if (write < 0) {
                if ((zzel.zza < 24 || write != -6) && write != -32) {
                    r0 = false;
                }
                if (r0) {
                    zzK();
                }
                zznv zznvVar = new zznv(write, this.zzp.zza, r0);
                zznt zzntVar2 = this.zzn;
                if (zzntVar2 != null) {
                    zzntVar2.zza(zznvVar);
                }
                if (zznvVar.zzb) {
                    throw zznvVar;
                }
                this.zzk.zzb(zznvVar);
                return;
            }
            this.zzk.zza();
            if (zzS(this.zzq)) {
                if (this.zzy > 0) {
                    this.zzU = false;
                }
                if (this.zzO && (zzntVar = this.zzn) != null && write < remaining2 && !this.zzU) {
                    zzoy zzoyVar = ((zzox) zzntVar).zza;
                    if (zzoy.zzT(zzoyVar) != null) {
                        zzoy.zzT(zzoyVar).zza();
                    }
                }
            }
            int r2 = this.zzp.zzc;
            if (r2 == 0) {
                this.zzx += write;
            }
            if (write == remaining2) {
                if (r2 != 0) {
                    zzdd.zzf(byteBuffer == this.zzG);
                    this.zzy += this.zzz * this.zzH;
                }
                this.zzI = null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0029 -> B:5:0x0009). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zzQ() throws com.google.android.gms.internal.ads.zznv {
        /*
            r9 = this;
            int r0 = r9.zzL
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto Lb
            r9.zzL = r3
        L9:
            r0 = 1
            goto Lc
        Lb:
            r0 = 0
        Lc:
            int r4 = r9.zzL
            com.google.android.gms.internal.ads.zzne[] r5 = r9.zzE
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L2f
            r4 = r5[r4]
            if (r0 == 0) goto L1f
            r4.zzd()
        L1f:
            r9.zzM(r7)
            boolean r0 = r4.zzh()
            if (r0 != 0) goto L29
            return r3
        L29:
            int r0 = r9.zzL
            int r0 = r0 + r2
            r9.zzL = r0
            goto L9
        L2f:
            java.nio.ByteBuffer r0 = r9.zzI
            if (r0 == 0) goto L3b
            r9.zzP(r0, r7)
            java.nio.ByteBuffer r0 = r9.zzI
            if (r0 == 0) goto L3b
            return r3
        L3b:
            r9.zzL = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzos.zzQ():boolean");
    }

    private final boolean zzR() {
        return this.zzq != null;
    }

    private static boolean zzS(AudioTrack audioTrack) {
        return zzel.zza >= 29 && audioTrack.isOffloadedPlayback();
    }

    private final boolean zzT() {
        if (MimeTypes.AUDIO_RAW.equals(this.zzp.zza.zzm)) {
            int r0 = this.zzp.zza.zzB;
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final int zza(zzaf zzafVar) {
        if (!MimeTypes.AUDIO_RAW.equals(zzafVar.zzm)) {
            if (!this.zzT) {
                int r0 = zzel.zza;
            }
            return this.zza.zza(zzafVar) != null ? 2 : 0;
        } else if (zzel.zzV(zzafVar.zzB)) {
            return zzafVar.zzB != 2 ? 1 : 2;
        } else {
            int r4 = zzafVar.zzB;
            Log.w("DefaultAudioSink", "Invalid PCM encoding: " + r4);
            return 0;
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final long zzb(boolean z) {
        long zzs;
        if (!zzR() || this.zzB) {
            return Long.MIN_VALUE;
        }
        long min = Math.min(this.zzg.zzb(z), this.zzp.zza(zzF()));
        while (!this.zzh.isEmpty() && min >= ((zzok) this.zzh.getFirst()).zzd) {
            this.zzt = (zzok) this.zzh.remove();
        }
        zzok zzokVar = this.zzt;
        long j = min - zzokVar.zzd;
        if (zzokVar.zza.equals(zzby.zza)) {
            zzs = this.zzt.zzc + j;
        } else if (this.zzh.isEmpty()) {
            zzs = this.zzV.zza(j) + this.zzt.zzc;
        } else {
            zzok zzokVar2 = (zzok) this.zzh.getFirst();
            zzs = zzokVar2.zzc - zzel.zzs(zzokVar2.zzd - min, this.zzt.zza.zzc);
        }
        return zzs + this.zzp.zza(this.zzV.zzb());
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final zzby zzc() {
        return zzH().zza;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzd(zzaf zzafVar, int r19, int[] r20) throws zznr {
        int r9;
        zzne[] zzneVarArr;
        int intValue;
        int r0;
        int intValue2;
        int r7;
        int r6;
        int zzf;
        int[] r8;
        if (MimeTypes.AUDIO_RAW.equals(zzafVar.zzm)) {
            zzdd.zzd(zzel.zzV(zzafVar.zzB));
            r0 = zzel.zzo(zzafVar.zzB, zzafVar.zzz);
            int r62 = zzafVar.zzB;
            zzne[] zzneVarArr2 = this.zzd;
            this.zzc.zzq(zzafVar.zzC, zzafVar.zzD);
            if (zzel.zza < 21 && zzafVar.zzz == 8 && r20 == null) {
                r8 = new int[6];
                for (int r92 = 0; r92 < 6; r92++) {
                    r8[r92] = r92;
                }
            } else {
                r8 = r20;
            }
            this.zzb.zzo(r8);
            zznc zzncVar = new zznc(zzafVar.zzA, zzafVar.zzz, zzafVar.zzB);
            for (zzne zzneVar : zzneVarArr2) {
                try {
                    zznc zza = zzneVar.zza(zzncVar);
                    if (true == zzneVar.zzg()) {
                        zzncVar = zza;
                    }
                } catch (zznd e) {
                    throw new zznr(e, zzafVar);
                }
            }
            int r82 = zzncVar.zzd;
            int r93 = zzncVar.zzb;
            int r72 = zzncVar.zzc;
            int zzj = zzel.zzj(r72);
            zzneVarArr = zzneVarArr2;
            r6 = zzel.zzo(r82, r72);
            r7 = r93;
            r9 = 0;
            intValue = r82;
            intValue2 = zzj;
        } else {
            zzne[] zzneVarArr3 = new zzne[0];
            int r63 = zzafVar.zzA;
            int r73 = zzel.zza;
            Pair zza2 = this.zza.zza(zzafVar);
            if (zza2 == null) {
                throw new zznr("Unable to configure passthrough for: ".concat(String.valueOf(String.valueOf(zzafVar))), zzafVar);
            }
            r9 = 2;
            zzneVarArr = zzneVarArr3;
            intValue = ((Integer) zza2.first).intValue();
            r0 = -1;
            intValue2 = ((Integer) zza2.second).intValue();
            r7 = r63;
            r6 = -1;
        }
        int minBufferSize = AudioTrack.getMinBufferSize(r7, intValue2, intValue);
        zzdd.zzf(minBufferSize != -2);
        int r13 = 250000;
        if (r9 == 0) {
            zzf = zzel.zzf(minBufferSize * 4, zzou.zza(250000, r7, r6), zzou.zza(750000, r7, r6));
        } else if (r9 == 1) {
            zzf = zzfxa.zza((zzou.zzb(intValue) * 50000000) / 1000000);
        } else {
            int r4 = 5;
            if (intValue == 5) {
                r13 = 500000;
                intValue = 5;
            } else {
                r4 = intValue;
            }
            zzf = zzfxa.zza((r13 * zzou.zzb(intValue)) / 1000000);
            r6 = r6;
            intValue = r4;
        }
        int max = (((Math.max(minBufferSize, zzf) + r6) - 1) / r6) * r6;
        if (intValue == 0) {
            throw new zznr("Invalid output encoding (mode=" + r9 + ") for: " + String.valueOf(zzafVar), zzafVar);
        } else if (intValue2 == 0) {
            throw new zznr("Invalid output channel config (mode=" + r9 + ") for: " + String.valueOf(zzafVar), zzafVar);
        } else {
            this.zzT = false;
            zzoh zzohVar = new zzoh(zzafVar, r0, r9, r6, r7, intValue2, intValue, max, zzneVarArr);
            if (zzR()) {
                this.zzo = zzohVar;
            } else {
                this.zzp = zzohVar;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zze() {
        if (zzR()) {
            this.zzv = 0L;
            this.zzw = 0L;
            this.zzx = 0L;
            this.zzy = 0L;
            this.zzU = false;
            this.zzz = 0;
            this.zzt = new zzok(zzH().zza, zzH().zzb, 0L, 0L, null);
            this.zzC = 0L;
            this.zzs = null;
            this.zzh.clear();
            this.zzG = null;
            this.zzH = 0;
            this.zzI = null;
            this.zzN = false;
            this.zzM = false;
            this.zzL = -1;
            this.zzc.zzp();
            zzJ();
            if (this.zzg.zzh()) {
                this.zzq.pause();
            }
            if (zzS(this.zzq)) {
                zzoq zzoqVar = this.zzi;
                Objects.requireNonNull(zzoqVar);
                zzoqVar.zzb(this.zzq);
            }
            AudioTrack audioTrack = this.zzq;
            this.zzq = null;
            if (zzel.zza < 21 && !this.zzP) {
                this.zzQ = 0;
            }
            zzoh zzohVar = this.zzo;
            if (zzohVar != null) {
                this.zzp = zzohVar;
                this.zzo = null;
            }
            this.zzg.zzd();
            this.zzf.close();
            new zzod(this, "ExoPlayer:AudioTrackReleaseThread", audioTrack).start();
        }
        this.zzk.zza();
        this.zzj.zza();
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzf() {
        this.zzA = true;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzg() {
        this.zzO = false;
        if (zzR() && this.zzg.zzk()) {
            this.zzq.pause();
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzh() {
        this.zzO = true;
        if (zzR()) {
            this.zzg.zzf();
            this.zzq.play();
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzi() throws zznv {
        if (!this.zzM && zzR() && zzQ()) {
            zzL();
            this.zzM = true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzj() {
        zze();
        for (zzne zzneVar : this.zzd) {
            zzneVar.zzf();
        }
        zzne[] zzneVarArr = this.zze;
        int length = zzneVarArr.length;
        for (int r1 = 0; r1 <= 0; r1++) {
            zzneVarArr[r1].zzf();
        }
        this.zzO = false;
        this.zzT = false;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzk(zzk zzkVar) {
        if (this.zzr.equals(zzkVar)) {
            return;
        }
        this.zzr = zzkVar;
        zze();
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzl(int r2) {
        if (this.zzQ != r2) {
            this.zzQ = r2;
            this.zzP = r2 != 0;
            zze();
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzm(zzl zzlVar) {
        if (this.zzR.equals(zzlVar)) {
            return;
        }
        int r0 = zzlVar.zza;
        float f = zzlVar.zzb;
        if (this.zzq != null) {
            int r02 = this.zzR.zza;
        }
        this.zzR = zzlVar;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzn(zznt zzntVar) {
        this.zzn = zzntVar;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzo(zzby zzbyVar) {
        zzN(new zzby(zzel.zza(zzbyVar.zzc, 0.1f, 8.0f), zzel.zza(zzbyVar.zzd, 0.1f, 8.0f)), zzH().zzb);
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzp(zzmz zzmzVar) {
        this.zzm = zzmzVar;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzq(boolean z) {
        zzN(zzH().zza, z);
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final void zzr(float f) {
        if (this.zzD != f) {
            this.zzD = f;
            zzO();
        }
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final boolean zzs(ByteBuffer byteBuffer, long j, int r28) throws zzns, zznv {
        AudioTrack zzG;
        zzmz zzmzVar;
        boolean z;
        int zza;
        int r0;
        int r9;
        int r6;
        byte b;
        int r02;
        int r92;
        ByteBuffer byteBuffer2 = this.zzG;
        boolean z2 = false;
        zzdd.zzd(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (this.zzo != null) {
            if (!zzQ()) {
                return false;
            }
            zzoh zzohVar = this.zzo;
            zzoh zzohVar2 = this.zzp;
            if (zzohVar2.zzc != zzohVar.zzc || zzohVar2.zzg != zzohVar.zzg || zzohVar2.zze != zzohVar.zze || zzohVar2.zzf != zzohVar.zzf || zzohVar2.zzd != zzohVar.zzd) {
                zzL();
                if (zzt()) {
                    return false;
                }
                zze();
            } else {
                this.zzp = zzohVar;
                this.zzo = null;
                if (zzS(this.zzq)) {
                    if (this.zzq.getPlayState() == 3) {
                        this.zzq.setOffloadEndOfStream();
                    }
                    AudioTrack audioTrack = this.zzq;
                    zzaf zzafVar = this.zzp.zza;
                    audioTrack.setOffloadDelayPadding(zzafVar.zzC, zzafVar.zzD);
                    this.zzU = true;
                }
            }
            zzI(j);
        }
        if (!zzR()) {
            try {
                this.zzf.block();
                try {
                    zzoh zzohVar3 = this.zzp;
                    Objects.requireNonNull(zzohVar3);
                    zzG = zzG(zzohVar3);
                } catch (zzns e) {
                    zzoh zzohVar4 = this.zzp;
                    if (zzohVar4.zzh > 1000000) {
                        zzoh zzohVar5 = new zzoh(zzohVar4.zza, zzohVar4.zzb, zzohVar4.zzc, zzohVar4.zzd, zzohVar4.zze, zzohVar4.zzf, zzohVar4.zzg, 1000000, zzohVar4.zzi);
                        try {
                            zzG = zzG(zzohVar5);
                            this.zzp = zzohVar5;
                        } catch (zzns e2) {
                            try {
                                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(e, e2);
                            } catch (Exception unused) {
                            }
                            zzK();
                            throw e;
                        }
                    }
                    zzK();
                    throw e;
                }
                this.zzq = zzG;
                if (zzS(zzG)) {
                    AudioTrack audioTrack2 = this.zzq;
                    if (this.zzi == null) {
                        this.zzi = new zzoq(this);
                    }
                    this.zzi.zza(audioTrack2);
                    AudioTrack audioTrack3 = this.zzq;
                    zzaf zzafVar2 = this.zzp.zza;
                    audioTrack3.setOffloadDelayPadding(zzafVar2.zzC, zzafVar2.zzD);
                }
                if (zzel.zza >= 31 && (zzmzVar = this.zzm) != null) {
                    zzoe.zza(this.zzq, zzmzVar);
                }
                this.zzQ = this.zzq.getAudioSessionId();
                zzoa zzoaVar = this.zzg;
                AudioTrack audioTrack4 = this.zzq;
                zzoh zzohVar6 = this.zzp;
                zzoaVar.zze(audioTrack4, zzohVar6.zzc == 2, zzohVar6.zzg, zzohVar6.zzd, zzohVar6.zzh);
                zzO();
                int r03 = this.zzR.zza;
                this.zzB = true;
                z2 = false;
            } catch (zzns e3) {
                if (e3.zzb) {
                    throw e3;
                }
                this.zzj.zzb(e3);
                return false;
            }
        }
        this.zzj.zza();
        if (this.zzB) {
            this.zzC = Math.max(0L, j);
            this.zzA = z2;
            this.zzB = z2;
            zzI(j);
            if (this.zzO) {
                zzh();
            }
        }
        if (this.zzg.zzj(zzF())) {
            if (this.zzG == null) {
                zzdd.zzd(byteBuffer.order() == ByteOrder.LITTLE_ENDIAN);
                if (!byteBuffer.hasRemaining()) {
                    return true;
                }
                zzoh zzohVar7 = this.zzp;
                if (zzohVar7.zzc != 0 && this.zzz == 0) {
                    int r04 = zzohVar7.zzg;
                    switch (r04) {
                        case 5:
                        case 6:
                        case 18:
                            z = true;
                            zza = zzyg.zza(byteBuffer);
                            break;
                        case 7:
                        case 8:
                            int r05 = zzzc.zza;
                            int position = byteBuffer.position();
                            byte b2 = byteBuffer.get(position);
                            if (b2 != -2) {
                                if (b2 == -1) {
                                    r9 = 2;
                                    r6 = (byteBuffer.get(position + 4) & 7) << 4;
                                    b = byteBuffer.get(position + 7);
                                } else if (b2 == 31) {
                                    r9 = 2;
                                    r6 = (byteBuffer.get(position + 5) & 7) << 4;
                                    b = byteBuffer.get(position + 6);
                                } else {
                                    r6 = (byteBuffer.get(position + 4) & 1) << 6;
                                    r02 = byteBuffer.get(position + 5) & 252;
                                    r9 = 2;
                                    r0 = (r02 >> r9) | r6;
                                    z = true;
                                }
                                r02 = b & 60;
                                r0 = (r02 >> r9) | r6;
                                z = true;
                            } else {
                                z = true;
                                r0 = ((byteBuffer.get(position + 4) & 252) >> 2) | ((byteBuffer.get(position + 5) & 1) << 6);
                            }
                            zza = (r0 + (z ? 1 : 0)) * 32;
                            break;
                        case 9:
                            zza = zzzz.zzc(zzel.zzk(byteBuffer, byteBuffer.position()));
                            if (zza == -1) {
                                throw new IllegalArgumentException();
                            }
                            z = true;
                            break;
                        case 10:
                        case 16:
                            zza = 1024;
                            z = true;
                            break;
                        case 11:
                        case 12:
                            zza = 2048;
                            z = true;
                            break;
                        case 13:
                        default:
                            throw new IllegalStateException("Unexpected audio encoding: " + r04);
                        case 14:
                            int r06 = zzyg.zza;
                            int position2 = byteBuffer.position();
                            int limit = byteBuffer.limit() - 10;
                            int r93 = position2;
                            while (true) {
                                if (r93 > limit) {
                                    r92 = -1;
                                } else if ((zzel.zzk(byteBuffer, r93 + 4) & (-2)) == -126718022) {
                                    r92 = r93 - position2;
                                } else {
                                    r93++;
                                }
                            }
                            if (r92 == -1) {
                                zza = 0;
                            } else {
                                zza = (40 << ((byteBuffer.get((byteBuffer.position() + r92) + ((byteBuffer.get((byteBuffer.position() + r92) + 7) & 255) == 187 ? 9 : 8)) >> 4) & 7)) * 16;
                            }
                            z = true;
                            break;
                        case 15:
                            zza = 512;
                            z = true;
                            break;
                        case 17:
                            int r07 = zzyj.zza;
                            byte[] bArr = new byte[16];
                            int position3 = byteBuffer.position();
                            byteBuffer.get(bArr);
                            byteBuffer.position(position3);
                            zza = zzyj.zza(new zzec(bArr, 16)).zzc;
                            z = true;
                            break;
                    }
                    this.zzz = zza;
                    if (zza == 0) {
                        return z;
                    }
                }
                if (this.zzs != null) {
                    if (!zzQ()) {
                        return false;
                    }
                    zzI(j);
                    this.zzs = null;
                }
                long zzE = this.zzC + (((zzE() - this.zzc.zzo()) * 1000000) / this.zzp.zza.zzA);
                if (!this.zzA && Math.abs(zzE - j) > 200000) {
                    this.zzn.zza(new zznu(j, zzE));
                    this.zzA = true;
                }
                if (this.zzA) {
                    if (!zzQ()) {
                        return false;
                    }
                    long j2 = j - zzE;
                    this.zzC += j2;
                    this.zzA = false;
                    zzI(j);
                    zznt zzntVar = this.zzn;
                    if (zzntVar != null && j2 != 0) {
                        ((zzox) zzntVar).zza.zzab();
                    }
                }
                if (this.zzp.zzc == 0) {
                    this.zzv += byteBuffer.remaining();
                } else {
                    this.zzw += this.zzz * r28;
                }
                this.zzG = byteBuffer;
                this.zzH = r28;
            }
            zzM(j);
            if (!this.zzG.hasRemaining()) {
                this.zzG = null;
                this.zzH = 0;
                return true;
            } else if (this.zzg.zzi(zzF())) {
                Log.w("DefaultAudioSink", "Resetting stalled audio track");
                zze();
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final boolean zzt() {
        return zzR() && this.zzg.zzg(zzF());
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final boolean zzu() {
        return !zzR() || (this.zzM && !zzt());
    }

    @Override // com.google.android.gms.internal.ads.zznw
    public final boolean zzv(zzaf zzafVar) {
        return zza(zzafVar) != 0;
    }
}
