package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.Log;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaua {
    private Method zzA;
    private int zzB;
    private long zzC;
    private long zzD;
    private int zzE;
    private long zzF;
    private long zzG;
    private int zzH;
    private int zzI;
    private long zzJ;
    private long zzK;
    private long zzL;
    private float zzM;
    private zzath[] zzN;
    private ByteBuffer[] zzO;
    private ByteBuffer zzP;
    private ByteBuffer zzQ;
    private byte[] zzR;
    private int zzS;
    private int zzT;
    private boolean zzU;
    private boolean zzV;
    private int zzW;
    private boolean zzX;
    private long zzY;
    private final zzaub zza;
    private final zzauh zzb;
    private final zzath[] zzc;
    private final zzatw zzd;
    private final ConditionVariable zze = new ConditionVariable(true);
    private final long[] zzf;
    private final zzats zzg;
    private final LinkedList zzh;
    private AudioTrack zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private boolean zzn;
    private int zzo;
    private long zzp;
    private zzasw zzq;
    private zzasw zzr;
    private long zzs;
    private long zzt;
    private int zzu;
    private int zzv;
    private long zzw;
    private long zzx;
    private boolean zzy;
    private long zzz;

    public zzaua(zzatf zzatfVar, zzath[] zzathVarArr, zzatw zzatwVar) {
        this.zzd = zzatwVar;
        if (zzban.zza >= 18) {
            try {
                Class[] clsArr = null;
                this.zzA = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (zzban.zza >= 19) {
            this.zzg = new zzatt();
        } else {
            this.zzg = new zzats(null);
        }
        zzaub zzaubVar = new zzaub();
        this.zza = zzaubVar;
        zzauh zzauhVar = new zzauh();
        this.zzb = zzauhVar;
        this.zzc = r1;
        System.arraycopy(zzathVarArr, 0, r1, 2, 0);
        zzath[] zzathVarArr2 = {new zzauf(), zzaubVar, zzauhVar};
        this.zzf = new long[10];
        this.zzM = 1.0f;
        this.zzI = 0;
        this.zzW = 0;
        this.zzr = zzasw.zza;
        this.zzT = -1;
        this.zzN = new zzath[0];
        this.zzO = new ByteBuffer[0];
        this.zzh = new LinkedList();
    }

    private final long zzp(long j) {
        return (j * this.zzj) / 1000000;
    }

    private final long zzq(long j) {
        return (j * 1000000) / this.zzj;
    }

    private final long zzr() {
        return this.zzn ? this.zzG : this.zzF / this.zzE;
    }

    private final void zzs(long j) throws zzatz {
        ByteBuffer byteBuffer;
        int length = this.zzN.length;
        int r1 = length;
        while (r1 >= 0) {
            if (r1 > 0) {
                byteBuffer = this.zzO[r1 - 1];
            } else {
                byteBuffer = this.zzP;
                if (byteBuffer == null) {
                    byteBuffer = zzath.zza;
                }
            }
            if (r1 == length) {
                zzz(byteBuffer, j);
            } else {
                zzath zzathVar = this.zzN[r1];
                zzathVar.zzf(byteBuffer);
                ByteBuffer zzc = zzathVar.zzc();
                this.zzO[r1] = zzc;
                if (zzc.hasRemaining()) {
                    r1++;
                }
            }
            if (byteBuffer.hasRemaining()) {
                return;
            }
            r1--;
        }
    }

    private final void zzt() {
        ArrayList arrayList = new ArrayList();
        zzath[] zzathVarArr = this.zzc;
        for (int r3 = 0; r3 < 3; r3++) {
            zzath zzathVar = zzathVarArr[r3];
            if (zzathVar.zzi()) {
                arrayList.add(zzathVar);
            } else {
                zzathVar.zzd();
            }
        }
        int size = arrayList.size();
        this.zzN = (zzath[]) arrayList.toArray(new zzath[size]);
        this.zzO = new ByteBuffer[size];
        for (int r2 = 0; r2 < size; r2++) {
            zzath zzathVar2 = this.zzN[r2];
            zzathVar2.zzd();
            this.zzO[r2] = zzathVar2.zzc();
        }
    }

    private final void zzu() {
        this.zzw = 0L;
        this.zzv = 0;
        this.zzu = 0;
        this.zzx = 0L;
        this.zzy = false;
        this.zzz = 0L;
    }

    private final void zzv() {
        if (zzx()) {
            if (zzban.zza >= 21) {
                this.zzi.setVolume(this.zzM);
                return;
            }
            AudioTrack audioTrack = this.zzi;
            float f = this.zzM;
            audioTrack.setStereoVolume(f, f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0038  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0032 -> B:9:0x0012). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zzw() throws com.google.android.gms.internal.ads.zzatz {
        /*
            r9 = this;
            int r0 = r9.zzT
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L14
            boolean r0 = r9.zzn
            if (r0 == 0) goto Lf
            com.google.android.gms.internal.ads.zzath[] r0 = r9.zzN
            int r0 = r0.length
            goto L10
        Lf:
            r0 = 0
        L10:
            r9.zzT = r0
        L12:
            r0 = 1
            goto L15
        L14:
            r0 = 0
        L15:
            int r4 = r9.zzT
            com.google.android.gms.internal.ads.zzath[] r5 = r9.zzN
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L38
            r4 = r5[r4]
            if (r0 == 0) goto L28
            r4.zze()
        L28:
            r9.zzs(r7)
            boolean r0 = r4.zzj()
            if (r0 != 0) goto L32
            return r3
        L32:
            int r0 = r9.zzT
            int r0 = r0 + r2
            r9.zzT = r0
            goto L12
        L38:
            java.nio.ByteBuffer r0 = r9.zzQ
            if (r0 == 0) goto L44
            r9.zzz(r0, r7)
            java.nio.ByteBuffer r0 = r9.zzQ
            if (r0 == 0) goto L44
            return r3
        L44:
            r9.zzT = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaua.zzw():boolean");
    }

    private final boolean zzx() {
        return this.zzi != null;
    }

    private final boolean zzy() {
        int r0;
        return zzban.zza < 23 && ((r0 = this.zzm) == 5 || r0 == 6);
    }

    private final boolean zzz(ByteBuffer byteBuffer, long j) throws zzatz {
        int write;
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.zzQ;
            if (byteBuffer2 != null) {
                zzazy.zzc(byteBuffer2 == byteBuffer);
            } else {
                this.zzQ = byteBuffer;
                if (zzban.zza < 21) {
                    int remaining = byteBuffer.remaining();
                    byte[] bArr = this.zzR;
                    if (bArr == null || bArr.length < remaining) {
                        this.zzR = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.zzR, 0, remaining);
                    byteBuffer.position(position);
                    this.zzS = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (zzban.zza < 21) {
                int zza = this.zzo - ((int) (this.zzF - (this.zzg.zza() * this.zzE)));
                if (zza > 0) {
                    write = this.zzi.write(this.zzR, this.zzS, Math.min(remaining2, zza));
                    if (write > 0) {
                        this.zzS += write;
                        byteBuffer.position(byteBuffer.position() + write);
                    }
                } else {
                    write = 0;
                }
            } else {
                write = this.zzi.write(byteBuffer, remaining2, 1);
            }
            this.zzY = SystemClock.elapsedRealtime();
            if (write >= 0) {
                boolean z = this.zzn;
                if (!z) {
                    this.zzF += write;
                }
                if (write == remaining2) {
                    if (z) {
                        this.zzG += this.zzH;
                    }
                    this.zzQ = null;
                    return true;
                }
                return false;
            }
            throw new zzatz(write);
        }
        return true;
    }

    public final long zza(boolean z) {
        long zzb;
        long j;
        long j2;
        zzasw zzaswVar;
        long j3;
        long j4;
        Method method;
        if (!zzx() || this.zzI == 0) {
            return Long.MIN_VALUE;
        }
        if (this.zzi.getPlayState() == 3) {
            long zzb2 = this.zzg.zzb();
            if (zzb2 != 0) {
                long nanoTime = System.nanoTime() / 1000;
                if (nanoTime - this.zzx >= 30000) {
                    long[] jArr = this.zzf;
                    int r10 = this.zzu;
                    jArr[r10] = zzb2 - nanoTime;
                    this.zzu = (r10 + 1) % 10;
                    int r102 = this.zzv;
                    if (r102 < 10) {
                        this.zzv = r102 + 1;
                    }
                    this.zzx = nanoTime;
                    this.zzw = 0L;
                    int r9 = 0;
                    while (true) {
                        int r103 = this.zzv;
                        if (r9 >= r103) {
                            break;
                        }
                        this.zzw += this.zzf[r9] / r103;
                        r9++;
                    }
                }
                if (!zzy() && nanoTime - this.zzz >= 500000) {
                    boolean zzh = this.zzg.zzh();
                    this.zzy = zzh;
                    if (zzh) {
                        long zzd = this.zzg.zzd() / 1000;
                        long zzc = this.zzg.zzc();
                        if (zzd < this.zzK) {
                            this.zzy = false;
                        } else if (Math.abs(zzd - nanoTime) > 5000000) {
                            Log.w("AudioTrack", "Spurious audio timestamp (system clock mismatch): " + zzc + ", " + zzd + ", " + nanoTime + ", " + zzb2);
                            this.zzy = false;
                        } else if (Math.abs(zzq(zzc) - zzb2) > 5000000) {
                            Log.w("AudioTrack", "Spurious audio timestamp (frame position mismatch): " + zzc + ", " + zzd + ", " + nanoTime + ", " + zzb2);
                            this.zzy = false;
                        }
                    }
                    if (this.zzA != null && !this.zzn) {
                        try {
                            Object[] objArr = null;
                            long intValue = (((Integer) method.invoke(this.zzi, null)).intValue() * 1000) - this.zzp;
                            this.zzL = intValue;
                            long max = Math.max(intValue, 0L);
                            this.zzL = max;
                            if (max > 5000000) {
                                Log.w("AudioTrack", "Ignoring impossibly large audio latency: " + max);
                                this.zzL = 0L;
                            }
                        } catch (Exception unused) {
                            this.zzA = null;
                        }
                    }
                    this.zzz = nanoTime;
                }
            }
        }
        long nanoTime2 = System.nanoTime() / 1000;
        if (this.zzy) {
            zzb = zzq(this.zzg.zzc() + zzp(nanoTime2 - (this.zzg.zzd() / 1000)));
        } else {
            zzb = this.zzv == 0 ? this.zzg.zzb() : nanoTime2 + this.zzw;
            if (!z) {
                zzb -= this.zzL;
            }
        }
        long j5 = this.zzJ;
        while (!this.zzh.isEmpty()) {
            j2 = ((zzaty) this.zzh.getFirst()).zzc;
            if (zzb < j2) {
                break;
            }
            zzaty zzatyVar = (zzaty) this.zzh.remove();
            zzaswVar = zzatyVar.zza;
            this.zzr = zzaswVar;
            j3 = zzatyVar.zzc;
            this.zzt = j3;
            j4 = zzatyVar.zzb;
            this.zzs = j4 - this.zzJ;
        }
        if (this.zzr.zzb == 1.0f) {
            j = (zzb + this.zzs) - this.zzt;
        } else {
            if (this.zzh.isEmpty()) {
                zzauh zzauhVar = this.zzb;
                if (zzauhVar.zzn() >= 1024) {
                    j = zzban.zzj(zzb - this.zzt, zzauhVar.zzm(), zzauhVar.zzn()) + this.zzs;
                }
            }
            j = ((long) (this.zzr.zzb * (zzb - this.zzt))) + this.zzs;
        }
        return j5 + j;
    }

    public final zzasw zzc() {
        return this.zzr;
    }

    public final zzasw zzd(zzasw zzaswVar) {
        if (this.zzn) {
            zzasw zzaswVar2 = zzasw.zza;
            this.zzr = zzaswVar2;
            return zzaswVar2;
        }
        float zzl = this.zzb.zzl(zzaswVar.zzb);
        zzauh zzauhVar = this.zzb;
        float f = zzaswVar.zzc;
        zzauhVar.zzk(1.0f);
        zzasw zzaswVar3 = new zzasw(zzl, 1.0f);
        zzasw zzaswVar4 = this.zzq;
        if (zzaswVar4 == null) {
            if (!this.zzh.isEmpty()) {
                zzaswVar4 = ((zzaty) this.zzh.getLast()).zza;
            } else {
                zzaswVar4 = this.zzr;
            }
        }
        if (!zzaswVar3.equals(zzaswVar4)) {
            if (zzx()) {
                this.zzq = zzaswVar3;
            } else {
                this.zzr = zzaswVar3;
            }
        }
        return this.zzr;
    }

    public final void zzf() {
        if (this.zzI == 1) {
            this.zzI = 2;
        }
    }

    public final void zzg() {
        this.zzV = false;
        if (zzx()) {
            zzu();
            this.zzg.zzf();
        }
    }

    public final void zzh() {
        this.zzV = true;
        if (zzx()) {
            this.zzK = System.nanoTime() / 1000;
            this.zzi.play();
        }
    }

    public final void zzi() throws zzatz {
        if (!this.zzU && zzx() && zzw()) {
            this.zzg.zze(zzr());
            this.zzU = true;
        }
    }

    public final void zzj() {
        zzk();
        zzath[] zzathVarArr = this.zzc;
        for (int r2 = 0; r2 < 3; r2++) {
            zzathVarArr[r2].zzg();
        }
        this.zzW = 0;
        this.zzV = false;
    }

    public final void zzl(float f) {
        if (this.zzM != f) {
            this.zzM = f;
            zzv();
        }
    }

    public final boolean zzm(ByteBuffer byteBuffer, long j) throws zzatv, zzatz {
        int r4;
        zzato zzatoVar;
        zzato zzatoVar2;
        ByteBuffer byteBuffer2 = this.zzP;
        zzazy.zzc(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (!zzx()) {
            this.zze.block();
            int r15 = this.zzW;
            if (r15 == 0) {
                this.zzi = new AudioTrack(3, this.zzj, this.zzk, this.zzm, this.zzo, 1);
            } else {
                this.zzi = new AudioTrack(3, this.zzj, this.zzk, this.zzm, this.zzo, 1, r15);
            }
            int state = this.zzi.getState();
            if (state == 1) {
                int audioSessionId = this.zzi.getAudioSessionId();
                if (this.zzW != audioSessionId) {
                    this.zzW = audioSessionId;
                    zzatoVar2 = ((zzaud) this.zzd).zza.zzb;
                    zzatoVar2.zzb(audioSessionId);
                }
                this.zzg.zzg(this.zzi, zzy());
                zzv();
                this.zzX = false;
                if (this.zzV) {
                    zzh();
                }
            } else {
                try {
                    this.zzi.release();
                } catch (Exception unused) {
                } finally {
                    this.zzi = null;
                }
                throw new zzatv(state, this.zzj, this.zzk, this.zzo);
            }
        }
        if (zzy()) {
            if (this.zzi.getPlayState() == 2) {
                this.zzX = false;
                return false;
            } else if (this.zzi.getPlayState() == 1 && this.zzg.zza() != 0) {
                return false;
            }
        }
        boolean z = this.zzX;
        boolean zzn = zzn();
        this.zzX = zzn;
        if (z && !zzn && this.zzi.getPlayState() != 1) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j2 = this.zzY;
            zzatw zzatwVar = this.zzd;
            int r152 = this.zzo;
            long zzb = zzasd.zzb(this.zzp);
            zzatoVar = ((zzaud) zzatwVar).zza.zzb;
            zzatoVar.zzc(r152, zzb, elapsedRealtime - j2);
        }
        if (this.zzP == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (this.zzn && this.zzH == 0) {
                int r42 = this.zzm;
                if (r42 == 7 || r42 == 8) {
                    int position = byteBuffer.position();
                    r4 = ((((byteBuffer.get(position + 5) & 252) >> 2) | ((byteBuffer.get(position + 4) & 1) << 6)) + 1) * 32;
                } else if (r42 == 5) {
                    int r43 = zzate.zza;
                    r4 = 1536;
                } else if (r42 == 6) {
                    r4 = zzate.zza(byteBuffer);
                } else {
                    throw new IllegalStateException("Unexpected audio encoding: " + r42);
                }
                this.zzH = r4;
            }
            if (this.zzq != null) {
                if (!zzw()) {
                    return false;
                }
                this.zzh.add(new zzaty(this.zzq, Math.max(0L, j), zzq(zzr()), null));
                this.zzq = null;
                zzt();
            }
            if (this.zzI == 0) {
                this.zzJ = Math.max(0L, j);
                this.zzI = 1;
            } else {
                long zzq = this.zzJ + zzq(this.zzn ? this.zzD : this.zzC / this.zzB);
                if (this.zzI == 1 && Math.abs(zzq - j) > 200000) {
                    Log.e("AudioTrack", "Discontinuity detected [expected " + zzq + ", got " + j + "]");
                    this.zzI = 2;
                }
                if (this.zzI == 2) {
                    this.zzJ += j - zzq;
                    this.zzI = 1;
                    ((zzaud) this.zzd).zza.zzh = true;
                }
            }
            if (this.zzn) {
                this.zzD += this.zzH;
            } else {
                this.zzC += byteBuffer.remaining();
            }
            this.zzP = byteBuffer;
        }
        if (this.zzn) {
            zzz(this.zzP, j);
        } else {
            zzs(j);
        }
        if (this.zzP.hasRemaining()) {
            return false;
        }
        this.zzP = null;
        return true;
    }

    public final boolean zzn() {
        if (zzx()) {
            if (zzr() > this.zzg.zza()) {
                return true;
            }
            if (zzy() && this.zzi.getPlayState() == 2 && this.zzi.getPlaybackHeadPosition() == 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzo() {
        return !zzx() || (this.zzU && !zzn());
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zze(java.lang.String r6, int r7, int r8, int r9, int r10, int[] r11) throws com.google.android.gms.internal.ads.zzatu {
        /*
            Method dump skipped, instructions count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaua.zze(java.lang.String, int, int, int, int, int[]):void");
    }

    public final void zzk() {
        zzasw zzaswVar;
        if (zzx()) {
            this.zzC = 0L;
            this.zzD = 0L;
            this.zzF = 0L;
            this.zzG = 0L;
            this.zzH = 0;
            zzasw zzaswVar2 = this.zzq;
            if (zzaswVar2 != null) {
                this.zzr = zzaswVar2;
                this.zzq = null;
            } else if (!this.zzh.isEmpty()) {
                zzaswVar = ((zzaty) this.zzh.getLast()).zza;
                this.zzr = zzaswVar;
            }
            this.zzh.clear();
            this.zzs = 0L;
            this.zzt = 0L;
            this.zzP = null;
            this.zzQ = null;
            int r3 = 0;
            while (true) {
                zzath[] zzathVarArr = this.zzN;
                if (r3 >= zzathVarArr.length) {
                    break;
                }
                zzath zzathVar = zzathVarArr[r3];
                zzathVar.zzd();
                this.zzO[r3] = zzathVar.zzc();
                r3++;
            }
            this.zzU = false;
            this.zzT = -1;
            this.zzI = 0;
            this.zzL = 0L;
            zzu();
            if (this.zzi.getPlayState() == 3) {
                this.zzi.pause();
            }
            AudioTrack audioTrack = this.zzi;
            this.zzi = null;
            this.zzg.zzg(null, false);
            this.zze.close();
            new zzatq(this, audioTrack).start();
        }
    }
}
