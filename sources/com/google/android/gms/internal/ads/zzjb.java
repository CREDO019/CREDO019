package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.MediaPeriodQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzjb implements Handler.Callback, zzsd, zzvv, zzjq, zzgv, zzjt {
    private boolean zzC;
    private boolean zzD;
    private boolean zzE;
    private int zzF;
    private zzja zzG;
    private long zzH;
    private int zzI;
    private boolean zzJ;
    private zzgy zzK;
    private final zzhu zzM;
    private final zzgt zzN;
    private final zzjy[] zza;
    private final Set zzb;
    private final zzjz[] zzc;
    private final zzvw zzd;
    private final zzvx zze;
    private final zzjf zzf;
    private final zzwe zzg;
    private final zzdn zzh;
    private final HandlerThread zzi;
    private final Looper zzj;
    private final zzcm zzk;
    private final zzck zzl;
    private final long zzm;
    private final zzgw zzn;
    private final ArrayList zzo;
    private final zzde zzp;
    private final zzjk zzq;
    private final zzjr zzr;
    private zzkb zzs;
    private zzjs zzt;
    private zziz zzu;
    private boolean zzv;
    private boolean zzx;
    private boolean zzy;
    private boolean zzz;
    private int zzA = 0;
    private boolean zzB = false;
    private boolean zzw = false;
    private long zzL = C1856C.TIME_UNSET;

    public zzjb(zzjy[] zzjyVarArr, zzvw zzvwVar, zzvx zzvxVar, zzjf zzjfVar, zzwe zzweVar, int r18, boolean z, zzkm zzkmVar, zzkb zzkbVar, zzgt zzgtVar, long j, boolean z2, Looper looper, zzde zzdeVar, zzhu zzhuVar, zzmz zzmzVar, byte[] bArr) {
        this.zzM = zzhuVar;
        this.zza = zzjyVarArr;
        this.zzd = zzvwVar;
        this.zze = zzvxVar;
        this.zzf = zzjfVar;
        this.zzg = zzweVar;
        int r9 = 0;
        this.zzs = zzkbVar;
        this.zzN = zzgtVar;
        this.zzp = zzdeVar;
        this.zzm = zzjfVar.zza();
        zzjfVar.zzf();
        zzjs zzh = zzjs.zzh(zzvxVar);
        this.zzt = zzh;
        this.zzu = new zziz(zzh);
        int length = zzjyVarArr.length;
        this.zzc = new zzjz[2];
        while (true) {
            int length2 = zzjyVarArr.length;
            if (r9 < 2) {
                zzjyVarArr[r9].zzq(r9, zzmzVar);
                this.zzc[r9] = zzjyVarArr[r9].zzj();
                r9++;
            } else {
                this.zzn = new zzgw(this, zzdeVar);
                this.zzo = new ArrayList();
                this.zzb = Collections.newSetFromMap(new IdentityHashMap());
                this.zzk = new zzcm();
                this.zzl = new zzck();
                zzvwVar.zzq(this, zzweVar);
                this.zzJ = true;
                Handler handler = new Handler(looper);
                this.zzq = new zzjk(zzkmVar, handler);
                this.zzr = new zzjr(this, zzkmVar, handler, zzmzVar);
                HandlerThread handlerThread = new HandlerThread("ExoPlayer:Playback", -16);
                this.zzi = handlerThread;
                handlerThread.start();
                Looper looper2 = handlerThread.getLooper();
                this.zzj = looper2;
                this.zzh = zzdeVar.zzb(looper2, this);
                return;
            }
        }
    }

    private final void zzA(zzjy zzjyVar) throws zzgy {
        if (zzac(zzjyVar)) {
            this.zzn.zzd(zzjyVar);
            zzaj(zzjyVar);
            zzjyVar.zzn();
            this.zzF--;
        }
    }

    private final void zzB() throws zzgy {
        int length = this.zza.length;
        zzC(new boolean[2]);
    }

    private final void zzC(boolean[] zArr) throws zzgy {
        zzjh zze = this.zzq.zze();
        zzvx zzi = zze.zzi();
        int r4 = 0;
        while (true) {
            int length = this.zza.length;
            if (r4 >= 2) {
                break;
            }
            if (!zzi.zzb(r4) && this.zzb.remove(this.zza[r4])) {
                this.zza[r4].zzA();
            }
            r4++;
        }
        int r42 = 0;
        while (true) {
            int length2 = this.zza.length;
            if (r42 < 2) {
                if (zzi.zzb(r42)) {
                    boolean z = zArr[r42];
                    zzjy zzjyVar = this.zza[r42];
                    if (!zzac(zzjyVar)) {
                        zzjh zze2 = this.zzq.zze();
                        boolean z2 = zze2 == this.zzq.zzd();
                        zzvx zzi2 = zze2.zzi();
                        zzka zzkaVar = zzi2.zzb[r42];
                        zzaf[] zzah = zzah(zzi2.zzc[r42]);
                        boolean z3 = zzaf() && this.zzt.zze == 3;
                        boolean z4 = !z && z3;
                        this.zzF++;
                        this.zzb.add(zzjyVar);
                        zzjyVar.zzo(zzkaVar, zzah, zze2.zzc[r42], this.zzH, z4, z2, zze2.zzf(), zze2.zze());
                        zzjyVar.zzp(11, new zziu(this));
                        this.zzn.zze(zzjyVar);
                        if (z3) {
                            zzjyVar.zzE();
                        }
                    }
                }
                r42++;
            } else {
                zze.zzg = true;
                return;
            }
        }
    }

    private final void zzD(IOException iOException, int r3) {
        zzgy zzc = zzgy.zzc(iOException, r3);
        zzjh zzd = this.zzq.zzd();
        if (zzd != null) {
            zzc = zzc.zza(zzd.zzf.zza);
        }
        zzdu.zza("ExoPlayerImplInternal", "Playback error", zzc);
        zzU(false, false);
        this.zzt = this.zzt.zze(zzc);
    }

    private final void zzE(boolean z) {
        long zzc;
        zzjh zzc2 = this.zzq.zzc();
        zzsg zzsgVar = zzc2 == null ? this.zzt.zzb : zzc2.zzf.zza;
        boolean z2 = !this.zzt.zzk.equals(zzsgVar);
        if (z2) {
            this.zzt = this.zzt.zza(zzsgVar);
        }
        zzjs zzjsVar = this.zzt;
        if (zzc2 == null) {
            zzc = zzjsVar.zzs;
        } else {
            zzc = zzc2.zzc();
        }
        zzjsVar.zzq = zzc;
        this.zzt.zzr = zzt();
        if ((z2 || z) && zzc2 != null && zzc2.zzd) {
            zzX(zzc2.zzh(), zzc2.zzi());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:182:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x035d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01bd  */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r14v0, types: [com.google.android.gms.internal.ads.zzck] */
    /* JADX WARN: Type inference failed for: r14v1, types: [com.google.android.gms.internal.ads.zzja] */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.google.android.gms.internal.ads.zzcn] */
    /* JADX WARN: Type inference failed for: r29v0, types: [com.google.android.gms.internal.ads.zzcn] */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzF(com.google.android.gms.internal.ads.zzcn r29, boolean r30) throws com.google.android.gms.internal.ads.zzgy {
        /*
            Method dump skipped, instructions count: 942
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjb.zzF(com.google.android.gms.internal.ads.zzcn, boolean):void");
    }

    private final void zzG(zzby zzbyVar, boolean z) throws zzgy {
        zzH(zzbyVar, zzbyVar.zzc, true, z);
    }

    private final void zzH(zzby zzbyVar, float f, boolean z, boolean z2) throws zzgy {
        int r3;
        zzjb zzjbVar = this;
        if (z) {
            if (z2) {
                zzjbVar.zzu.zza(1);
            }
            zzjs zzjsVar = zzjbVar.zzt;
            zzjbVar = this;
            zzjbVar.zzt = new zzjs(zzjsVar.zza, zzjsVar.zzb, zzjsVar.zzc, zzjsVar.zzd, zzjsVar.zze, zzjsVar.zzf, zzjsVar.zzg, zzjsVar.zzh, zzjsVar.zzi, zzjsVar.zzj, zzjsVar.zzk, zzjsVar.zzl, zzjsVar.zzm, zzbyVar, zzjsVar.zzq, zzjsVar.zzr, zzjsVar.zzs, zzjsVar.zzo, zzjsVar.zzp);
        }
        float f2 = zzbyVar.zzc;
        zzjh zzd = zzjbVar.zzq.zzd();
        while (true) {
            r3 = 0;
            if (zzd == null) {
                break;
            }
            zzvq[] zzvqVarArr = zzd.zzi().zzc;
            int length = zzvqVarArr.length;
            while (r3 < length) {
                zzvq zzvqVar = zzvqVarArr[r3];
                r3++;
            }
            zzd = zzd.zzg();
        }
        zzjy[] zzjyVarArr = zzjbVar.zza;
        int length2 = zzjyVarArr.length;
        while (r3 < 2) {
            zzjy zzjyVar = zzjyVarArr[r3];
            if (zzjyVar != null) {
                zzjyVar.zzD(f, zzbyVar.zzc);
            }
            r3++;
        }
    }

    private final void zzI() {
        long zze;
        long j;
        boolean zzg;
        if (zzab()) {
            zzjh zzc = this.zzq.zzc();
            long zzu = zzu(zzc.zzd());
            if (zzc == this.zzq.zzd()) {
                zze = this.zzH;
                j = zzc.zze();
            } else {
                zze = this.zzH - zzc.zze();
                j = zzc.zzf.zzb;
            }
            zzg = this.zzf.zzg(zze - j, zzu, this.zzn.zzc().zzc);
        } else {
            zzg = false;
        }
        this.zzz = zzg;
        if (zzg) {
            this.zzq.zzc().zzk(this.zzH);
        }
        zzW();
    }

    private final void zzJ() {
        boolean z;
        this.zzu.zzc(this.zzt);
        z = this.zzu.zzg;
        if (z) {
            zzhu zzhuVar = this.zzM;
            zzhuVar.zza.zzT(this.zzu);
            this.zzu = new zziz(this.zzt);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzK(boolean r31, boolean r32, boolean r33, boolean r34) {
        /*
            Method dump skipped, instructions count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjb.zzK(boolean, boolean, boolean, boolean):void");
    }

    private final void zzL() {
        zzjh zzd = this.zzq.zzd();
        boolean z = false;
        if (zzd != null && zzd.zzf.zzh && this.zzw) {
            z = true;
        }
        this.zzx = z;
    }

    private final void zzM(long j) throws zzgy {
        zzvq[] zzvqVarArr;
        zzjh zzd = this.zzq.zzd();
        long zze = j + (zzd == null ? MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US : zzd.zze());
        this.zzH = zze;
        this.zzn.zzf(zze);
        zzjy[] zzjyVarArr = this.zza;
        int length = zzjyVarArr.length;
        for (int r0 = 0; r0 < 2; r0++) {
            zzjy zzjyVar = zzjyVarArr[r0];
            if (zzac(zzjyVar)) {
                zzjyVar.zzB(this.zzH);
            }
        }
        for (zzjh zzd2 = this.zzq.zzd(); zzd2 != null; zzd2 = zzd2.zzg()) {
            for (zzvq zzvqVar : zzd2.zzi().zzc) {
            }
        }
    }

    private final void zzN(zzcn zzcnVar, zzcn zzcnVar2) {
        if (zzcnVar.zzo() && zzcnVar2.zzo()) {
            return;
        }
        int size = this.zzo.size() - 1;
        if (size >= 0) {
            zziy zziyVar = (zziy) this.zzo.get(size);
            Object obj = zziyVar.zzb;
            zzjv zzjvVar = zziyVar.zza;
            int r2 = zzel.zza;
            zzjv zzjvVar2 = zziyVar.zza;
            throw null;
        }
        Collections.sort(this.zzo);
    }

    private final void zzO(long j, long j2) {
        this.zzh.zze(2);
        this.zzh.zzi(2, j + j2);
    }

    private final void zzP(boolean z) throws zzgy {
        zzsg zzsgVar = this.zzq.zzd().zzf.zza;
        long zzw = zzw(zzsgVar, this.zzt.zzs, true, false);
        if (zzw != this.zzt.zzs) {
            zzjs zzjsVar = this.zzt;
            this.zzt = zzz(zzsgVar, zzw, zzjsVar.zzc, zzjsVar.zzd, z, 5);
        }
    }

    private final void zzQ(zzjv zzjvVar) throws zzgy {
        if (zzjvVar.zzb() == this.zzj) {
            zzai(zzjvVar);
            int r3 = this.zzt.zze;
            if (r3 == 3 || r3 == 2) {
                this.zzh.zzh(2);
                return;
            }
            return;
        }
        this.zzh.zzb(15, zzjvVar).zza();
    }

    private final void zzR(boolean z, int r4, boolean z2, int r6) throws zzgy {
        zzvq[] zzvqVarArr;
        this.zzu.zza(z2 ? 1 : 0);
        this.zzu.zzb(r6);
        this.zzt = this.zzt.zzd(z, r4);
        this.zzy = false;
        for (zzjh zzd = this.zzq.zzd(); zzd != null; zzd = zzd.zzg()) {
            for (zzvq zzvqVar : zzd.zzi().zzc) {
            }
        }
        if (!zzaf()) {
            zzV();
            zzY();
            return;
        }
        int r3 = this.zzt.zze;
        if (r3 == 3) {
            zzT();
            this.zzh.zzh(2);
        } else if (r3 == 2) {
            this.zzh.zzh(2);
        }
    }

    private final void zzS(int r4) {
        zzjs zzjsVar = this.zzt;
        if (zzjsVar.zze != r4) {
            if (r4 != 2) {
                this.zzL = C1856C.TIME_UNSET;
            }
            this.zzt = zzjsVar.zzf(r4);
        }
    }

    private final void zzT() throws zzgy {
        this.zzy = false;
        this.zzn.zzh();
        zzjy[] zzjyVarArr = this.zza;
        int length = zzjyVarArr.length;
        for (int r0 = 0; r0 < 2; r0++) {
            zzjy zzjyVar = zzjyVarArr[r0];
            if (zzac(zzjyVar)) {
                zzjyVar.zzE();
            }
        }
    }

    private final void zzU(boolean z, boolean z2) {
        zzK(z || !this.zzC, false, true, false);
        this.zzu.zza(z2 ? 1 : 0);
        this.zzf.zzd();
        zzS(1);
    }

    private final void zzV() throws zzgy {
        this.zzn.zzi();
        zzjy[] zzjyVarArr = this.zza;
        int length = zzjyVarArr.length;
        for (int r1 = 0; r1 < 2; r1++) {
            zzjy zzjyVar = zzjyVarArr[r1];
            if (zzac(zzjyVar)) {
                zzaj(zzjyVar);
            }
        }
    }

    private final void zzW() {
        zzjh zzc = this.zzq.zzc();
        boolean z = this.zzz || (zzc != null && zzc.zza.zzp());
        zzjs zzjsVar = this.zzt;
        if (z != zzjsVar.zzg) {
            this.zzt = new zzjs(zzjsVar.zza, zzjsVar.zzb, zzjsVar.zzc, zzjsVar.zzd, zzjsVar.zze, zzjsVar.zzf, z, zzjsVar.zzh, zzjsVar.zzi, zzjsVar.zzj, zzjsVar.zzk, zzjsVar.zzl, zzjsVar.zzm, zzjsVar.zzn, zzjsVar.zzq, zzjsVar.zzr, zzjsVar.zzs, zzjsVar.zzo, zzjsVar.zzp);
        }
    }

    private final void zzX(zzue zzueVar, zzvx zzvxVar) {
        this.zzf.zze(this.zza, zzueVar, zzvxVar.zzc);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00ac A[LOOP:0: B:30:0x009c->B:37:0x00ac, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009b A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x009b -> B:30:0x009c). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzY() throws com.google.android.gms.internal.ads.zzgy {
        /*
            Method dump skipped, instructions count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjb.zzY():void");
    }

    private final void zzZ(zzcn zzcnVar, zzsg zzsgVar, zzcn zzcnVar2, zzsg zzsgVar2, long j) {
        if (!zzag(zzcnVar, zzsgVar)) {
            zzby zzbyVar = zzsgVar.zzb() ? zzby.zza : this.zzt.zzn;
            if (this.zzn.zzc().equals(zzbyVar)) {
                return;
            }
            this.zzn.zzg(zzbyVar);
            return;
        }
        zzcnVar.zze(zzcnVar.zzn(zzsgVar.zza, this.zzl).zzd, this.zzk, 0L);
        zzgt zzgtVar = this.zzN;
        zzaw zzawVar = this.zzk.zzk;
        int r4 = zzel.zza;
        zzgtVar.zzd(zzawVar);
        if (j != C1856C.TIME_UNSET) {
            this.zzN.zze(zzs(zzcnVar, zzsgVar.zza, j));
            return;
        }
        if (zzel.zzT(!zzcnVar2.zzo() ? zzcnVar2.zze(zzcnVar2.zzn(zzsgVar2.zza, this.zzl).zzd, this.zzk, 0L).zzc : null, this.zzk.zzc)) {
            return;
        }
        this.zzN.zze(C1856C.TIME_UNSET);
    }

    private final synchronized void zzaa(zzfsv zzfsvVar, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime() + 500;
        boolean z = false;
        for (long j2 = 500; !Boolean.valueOf(((zzis) zzfsvVar).zza.zzv).booleanValue() && j2 > 0; j2 = elapsedRealtime - SystemClock.elapsedRealtime()) {
            try {
                wait(j2);
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private final boolean zzab() {
        zzjh zzc = this.zzq.zzc();
        return (zzc == null || zzc.zzd() == Long.MIN_VALUE) ? false : true;
    }

    private static boolean zzac(zzjy zzjyVar) {
        return zzjyVar.zzbe() != 0;
    }

    private final boolean zzad() {
        zzjh zzd = this.zzq.zzd();
        long j = zzd.zzf.zze;
        if (zzd.zzd) {
            return j == C1856C.TIME_UNSET || this.zzt.zzs < j || !zzaf();
        }
        return false;
    }

    private static boolean zzae(zzjs zzjsVar, zzck zzckVar) {
        zzsg zzsgVar = zzjsVar.zzb;
        zzcn zzcnVar = zzjsVar.zza;
        return zzcnVar.zzo() || zzcnVar.zzn(zzsgVar.zza, zzckVar).zzg;
    }

    private final boolean zzaf() {
        zzjs zzjsVar = this.zzt;
        return zzjsVar.zzl && zzjsVar.zzm == 0;
    }

    private final boolean zzag(zzcn zzcnVar, zzsg zzsgVar) {
        if (!zzsgVar.zzb() && !zzcnVar.zzo()) {
            zzcnVar.zze(zzcnVar.zzn(zzsgVar.zza, this.zzl).zzd, this.zzk, 0L);
            if (this.zzk.zzb()) {
                zzcm zzcmVar = this.zzk;
                if (zzcmVar.zzi && zzcmVar.zzf != C1856C.TIME_UNSET) {
                    return true;
                }
            }
        }
        return false;
    }

    private static zzaf[] zzah(zzvq zzvqVar) {
        int zzc = zzvqVar != null ? zzvqVar.zzc() : 0;
        zzaf[] zzafVarArr = new zzaf[zzc];
        for (int r0 = 0; r0 < zzc; r0++) {
            zzafVarArr[r0] = zzvqVar.zzd(r0);
        }
        return zzafVarArr;
    }

    private static final void zzai(zzjv zzjvVar) throws zzgy {
        zzjvVar.zzj();
        try {
            zzjvVar.zzc().zzp(zzjvVar.zza(), zzjvVar.zzg());
        } finally {
            zzjvVar.zzh(true);
        }
    }

    private static final void zzaj(zzjy zzjyVar) throws zzgy {
        if (zzjyVar.zzbe() == 2) {
            zzjyVar.zzF();
        }
    }

    private static final void zzak(zzjy zzjyVar, long j) {
        zzjyVar.zzC();
        if (zzjyVar instanceof zzuh) {
            zzuh zzuhVar = (zzuh) zzjyVar;
            throw null;
        }
    }

    static Object zze(zzcm zzcmVar, zzck zzckVar, int r11, boolean z, Object obj, zzcn zzcnVar, zzcn zzcnVar2) {
        int zza = zzcnVar.zza(obj);
        int zzb = zzcnVar.zzb();
        int r2 = 0;
        int r4 = zza;
        int r13 = -1;
        while (true) {
            if (r2 >= zzb || r13 != -1) {
                break;
            }
            r4 = zzcnVar.zzi(r4, zzckVar, zzcmVar, r11, z);
            if (r4 == -1) {
                r13 = -1;
                break;
            }
            r13 = zzcnVar2.zza(zzcnVar.zzf(r4));
            r2++;
        }
        if (r13 == -1) {
            return null;
        }
        return zzcnVar2.zzf(r13);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ void zzr(zzjv zzjvVar) {
        try {
            zzai(zzjvVar);
        } catch (zzgy e) {
            zzdu.zza("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
            throw new RuntimeException(e);
        }
    }

    private final long zzs(zzcn zzcnVar, Object obj, long j) {
        zzcnVar.zze(zzcnVar.zzn(obj, this.zzl).zzd, this.zzk, 0L);
        zzcm zzcmVar = this.zzk;
        if (zzcmVar.zzf != C1856C.TIME_UNSET && zzcmVar.zzb()) {
            zzcm zzcmVar2 = this.zzk;
            if (zzcmVar2.zzi) {
                return zzel.zzv(zzel.zzt(zzcmVar2.zzg) - this.zzk.zzf) - j;
            }
        }
        return C1856C.TIME_UNSET;
    }

    private final long zzt() {
        return zzu(this.zzt.zzq);
    }

    private final long zzu(long j) {
        zzjh zzc = this.zzq.zzc();
        if (zzc == null) {
            return 0L;
        }
        return Math.max(0L, j - (this.zzH - zzc.zze()));
    }

    private final long zzv(zzsg zzsgVar, long j, boolean z) throws zzgy {
        return zzw(zzsgVar, j, this.zzq.zzd() != this.zzq.zze(), z);
    }

    private final long zzw(zzsg zzsgVar, long j, boolean z, boolean z2) throws zzgy {
        zzV();
        this.zzy = false;
        if (z2 || this.zzt.zze == 3) {
            zzS(2);
        }
        zzjh zzd = this.zzq.zzd();
        zzjh zzjhVar = zzd;
        while (zzjhVar != null && !zzsgVar.equals(zzjhVar.zzf.zza)) {
            zzjhVar = zzjhVar.zzg();
        }
        if (z || zzd != zzjhVar || (zzjhVar != null && zzjhVar.zze() + j < 0)) {
            zzjy[] zzjyVarArr = this.zza;
            int length = zzjyVarArr.length;
            for (int r9 = 0; r9 < 2; r9++) {
                zzA(zzjyVarArr[r9]);
            }
            if (zzjhVar != null) {
                while (this.zzq.zzd() != zzjhVar) {
                    this.zzq.zza();
                }
                this.zzq.zzm(zzjhVar);
                zzjhVar.zzp(MediaPeriodQueue.INITIAL_RENDERER_POSITION_OFFSET_US);
                zzB();
            }
        }
        if (zzjhVar != null) {
            this.zzq.zzm(zzjhVar);
            if (!zzjhVar.zzd) {
                zzjhVar.zzf = zzjhVar.zzf.zzb(j);
            } else if (zzjhVar.zze) {
                j = zzjhVar.zza.zze(j);
                zzjhVar.zza.zzj(j - this.zzm, false);
            }
            zzM(j);
            zzI();
        } else {
            this.zzq.zzi();
            zzM(j);
        }
        zzE(false);
        this.zzh.zzh(2);
        return j;
    }

    private final Pair zzx(zzcn zzcnVar) {
        long j = 0;
        if (zzcnVar.zzo()) {
            return Pair.create(zzjs.zzi(), 0L);
        }
        Pair zzl = zzcnVar.zzl(this.zzk, this.zzl, zzcnVar.zzg(this.zzB), C1856C.TIME_UNSET);
        zzsg zzh = this.zzq.zzh(zzcnVar, zzl.first, 0L);
        long longValue = ((Long) zzl.second).longValue();
        if (zzh.zzb()) {
            zzcnVar.zzn(zzh.zza, this.zzl);
            if (zzh.zzc == this.zzl.zze(zzh.zzb)) {
                this.zzl.zzi();
            }
        } else {
            j = longValue;
        }
        return Pair.create(zzh, Long.valueOf(j));
    }

    private static Pair zzy(zzcn zzcnVar, zzja zzjaVar, boolean z, int r15, boolean z2, zzcm zzcmVar, zzck zzckVar) {
        Pair zzl;
        zzcn zzcnVar2 = zzjaVar.zza;
        if (zzcnVar.zzo()) {
            return null;
        }
        zzcn zzcnVar3 = true == zzcnVar2.zzo() ? zzcnVar : zzcnVar2;
        try {
            zzl = zzcnVar3.zzl(zzcmVar, zzckVar, zzjaVar.zzb, zzjaVar.zzc);
        } catch (IndexOutOfBoundsException unused) {
        }
        if (zzcnVar.equals(zzcnVar3)) {
            return zzl;
        }
        if (zzcnVar.zza(zzl.first) != -1) {
            return (zzcnVar3.zzn(zzl.first, zzckVar).zzg && zzcnVar3.zze(zzckVar.zzd, zzcmVar, 0L).zzo == zzcnVar3.zza(zzl.first)) ? zzcnVar.zzl(zzcmVar, zzckVar, zzcnVar.zzn(zzl.first, zzckVar).zzd, zzjaVar.zzc) : zzl;
        }
        Object zze = zze(zzcmVar, zzckVar, r15, z2, zzl.first, zzcnVar3, zzcnVar);
        if (zze != null) {
            return zzcnVar.zzl(zzcmVar, zzckVar, zzcnVar.zzn(zze, zzckVar).zzd, C1856C.TIME_UNSET);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.android.gms.internal.ads.zzjs zzz(com.google.android.gms.internal.ads.zzsg r17, long r18, long r20, long r22, boolean r24, int r25) {
        /*
            r16 = this;
            r0 = r16
            r2 = r17
            r5 = r20
            boolean r1 = r0.zzJ
            r3 = 0
            r4 = 1
            if (r1 != 0) goto L21
            com.google.android.gms.internal.ads.zzjs r1 = r0.zzt
            long r7 = r1.zzs
            int r1 = (r18 > r7 ? 1 : (r18 == r7 ? 0 : -1))
            if (r1 != 0) goto L21
            com.google.android.gms.internal.ads.zzjs r1 = r0.zzt
            com.google.android.gms.internal.ads.zzsg r1 = r1.zzb
            boolean r1 = r2.equals(r1)
            if (r1 != 0) goto L1f
            goto L21
        L1f:
            r1 = 0
            goto L22
        L21:
            r1 = 1
        L22:
            r0.zzJ = r1
            r16.zzL()
            com.google.android.gms.internal.ads.zzjs r1 = r0.zzt
            com.google.android.gms.internal.ads.zzue r7 = r1.zzh
            com.google.android.gms.internal.ads.zzvx r8 = r1.zzi
            java.util.List r1 = r1.zzj
            com.google.android.gms.internal.ads.zzjr r9 = r0.zzr
            boolean r9 = r9.zzi()
            if (r9 == 0) goto L96
            com.google.android.gms.internal.ads.zzjk r1 = r0.zzq
            com.google.android.gms.internal.ads.zzjh r1 = r1.zzd()
            if (r1 != 0) goto L42
            com.google.android.gms.internal.ads.zzue r7 = com.google.android.gms.internal.ads.zzue.zza
            goto L46
        L42:
            com.google.android.gms.internal.ads.zzue r7 = r1.zzh()
        L46:
            if (r1 != 0) goto L4b
            com.google.android.gms.internal.ads.zzvx r8 = r0.zze
            goto L4f
        L4b:
            com.google.android.gms.internal.ads.zzvx r8 = r1.zzi()
        L4f:
            com.google.android.gms.internal.ads.zzvq[] r9 = r8.zzc
            com.google.android.gms.internal.ads.zzfus r10 = new com.google.android.gms.internal.ads.zzfus
            r10.<init>()
            int r11 = r9.length
            r12 = 0
            r13 = 0
        L59:
            if (r12 >= r11) goto L79
            r14 = r9[r12]
            if (r14 == 0) goto L76
            com.google.android.gms.internal.ads.zzaf r14 = r14.zzd(r3)
            com.google.android.gms.internal.ads.zzbq r14 = r14.zzk
            if (r14 != 0) goto L72
            com.google.android.gms.internal.ads.zzbq r14 = new com.google.android.gms.internal.ads.zzbq
            com.google.android.gms.internal.ads.zzbp[] r15 = new com.google.android.gms.internal.ads.zzbp[r3]
            r14.<init>(r15)
            r10.zze(r14)
            goto L76
        L72:
            r10.zze(r14)
            r13 = 1
        L76:
            int r12 = r12 + 1
            goto L59
        L79:
            if (r13 == 0) goto L80
            com.google.android.gms.internal.ads.zzfuv r3 = r10.zzg()
            goto L84
        L80:
            com.google.android.gms.internal.ads.zzfuv r3 = com.google.android.gms.internal.ads.zzfuv.zzo()
        L84:
            if (r1 == 0) goto L94
            com.google.android.gms.internal.ads.zzji r4 = r1.zzf
            long r9 = r4.zzc
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r11 == 0) goto L94
            com.google.android.gms.internal.ads.zzji r4 = r4.zza(r5)
            r1.zzf = r4
        L94:
            r13 = r3
            goto Lad
        L96:
            com.google.android.gms.internal.ads.zzjs r3 = r0.zzt
            com.google.android.gms.internal.ads.zzsg r3 = r3.zzb
            boolean r3 = r2.equals(r3)
            if (r3 != 0) goto Lac
            com.google.android.gms.internal.ads.zzue r1 = com.google.android.gms.internal.ads.zzue.zza
            com.google.android.gms.internal.ads.zzvx r3 = r0.zze
            com.google.android.gms.internal.ads.zzfuv r4 = com.google.android.gms.internal.ads.zzfuv.zzo()
            r11 = r1
            r12 = r3
            r13 = r4
            goto Laf
        Lac:
            r13 = r1
        Lad:
            r11 = r7
            r12 = r8
        Laf:
            if (r24 == 0) goto Lb8
            com.google.android.gms.internal.ads.zziz r1 = r0.zzu
            r3 = r25
            r1.zzd(r3)
        Lb8:
            com.google.android.gms.internal.ads.zzjs r1 = r0.zzt
            long r9 = r16.zzt()
            r2 = r17
            r3 = r18
            r5 = r20
            r7 = r22
            com.google.android.gms.internal.ads.zzjs r1 = r1.zzb(r2, r3, r5, r7, r9, r11, r12, r13)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjb.zzz(com.google.android.gms.internal.ads.zzsg, long, long, long, boolean, int):com.google.android.gms.internal.ads.zzjs");
    }

    /* JADX WARN: Code restructure failed: missing block: B:427:0x0897, code lost:
        if (zzad() != false) goto L455;
     */
    /* JADX WARN: Code restructure failed: missing block: B:464:0x092d, code lost:
        if (r2 == false) goto L491;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:319:0x06d6 A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x074b A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0769 A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:366:0x07b0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:370:0x07be A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:371:0x07c5 A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:405:0x083c A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:477:0x0968  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x09db A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:506:0x09e7 A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:519:0x0a10  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0a26 A[Catch: RuntimeException -> 0x0ad0, IOException -> 0x0afb, zzri -> 0x0b03, zzew -> 0x0b0b, zzbu -> 0x0b13, zzpg -> 0x0b2a, zzgy -> 0x0b33, TryCatch #7 {zzbu -> 0x0b13, zzew -> 0x0b0b, zzgy -> 0x0b33, zzpg -> 0x0b2a, zzri -> 0x0b03, IOException -> 0x0afb, RuntimeException -> 0x0ad0, blocks: (B:3:0x0006, B:4:0x0011, B:7:0x0016, B:8:0x001b, B:12:0x0022, B:14:0x0026, B:19:0x0033, B:20:0x003a, B:21:0x0042, B:25:0x0049, B:27:0x0052, B:29:0x0060, B:30:0x0068, B:31:0x0073, B:32:0x0087, B:33:0x009f, B:34:0x00bb, B:36:0x00ca, B:37:0x00ce, B:38:0x00df, B:40:0x00ee, B:41:0x010a, B:42:0x011d, B:43:0x0126, B:45:0x0138, B:46:0x0144, B:47:0x0154, B:48:0x015d, B:52:0x0164, B:54:0x016c, B:56:0x0170, B:58:0x0176, B:60:0x017e, B:62:0x0186, B:63:0x0189, B:65:0x018e, B:72:0x019b, B:73:0x019c, B:77:0x01a3, B:79:0x01b1, B:80:0x01b4, B:81:0x01b9, B:83:0x01c9, B:84:0x01cc, B:85:0x01d1, B:87:0x01e8, B:89:0x01ec, B:91:0x01fa, B:95:0x0204, B:97:0x0209, B:99:0x020f, B:103:0x0217, B:105:0x021f, B:107:0x0245, B:111:0x024e, B:113:0x0270, B:114:0x0273, B:115:0x0279, B:117:0x027e, B:119:0x028e, B:121:0x0294, B:122:0x0298, B:124:0x029c, B:125:0x02a1, B:126:0x02a6, B:130:0x02c7, B:132:0x02d2, B:127:0x02aa, B:129:0x02b4, B:133:0x02df, B:135:0x02eb, B:136:0x02f7, B:138:0x0303, B:140:0x032b, B:141:0x034b, B:142:0x0350, B:143:0x0362, B:150:0x036d, B:151:0x036e, B:152:0x0375, B:153:0x037d, B:154:0x0392, B:156:0x03be, B:216:0x04d1, B:201:0x049f, B:200:0x049b, B:221:0x04e0, B:222:0x04ef, B:157:0x03df, B:161:0x03f2, B:163:0x0402, B:165:0x0419, B:167:0x0423, B:223:0x04f0, B:225:0x04ff, B:228:0x0509, B:230:0x0518, B:232:0x0524, B:234:0x0553, B:235:0x0558, B:236:0x055c, B:238:0x0560, B:240:0x056d, B:309:0x06af, B:311:0x06b7, B:313:0x06bf, B:316:0x06c4, B:317:0x06d0, B:319:0x06d6, B:321:0x06de, B:325:0x06ef, B:327:0x06f5, B:328:0x070f, B:330:0x0715, B:332:0x071a, B:334:0x071f, B:336:0x0723, B:338:0x0729, B:340:0x072d, B:342:0x0735, B:344:0x073b, B:346:0x0745, B:349:0x074b, B:350:0x074e, B:352:0x0757, B:354:0x0769, B:356:0x0771, B:358:0x0779, B:362:0x0782, B:364:0x07a9, B:368:0x07b4, B:370:0x07be, B:371:0x07c5, B:373:0x07d7, B:374:0x07ed, B:376:0x07f3, B:406:0x083f, B:379:0x07fc, B:381:0x0803, B:385:0x080c, B:387:0x0816, B:393:0x0823, B:395:0x0829, B:405:0x083c, B:408:0x084f, B:410:0x0855, B:414:0x0862, B:416:0x086a, B:418:0x086e, B:419:0x0879, B:421:0x087f, B:475:0x0961, B:478:0x0969, B:480:0x096e, B:482:0x0976, B:484:0x0984, B:485:0x098b, B:486:0x098f, B:488:0x0995, B:490:0x099e, B:492:0x09a4, B:494:0x09af, B:501:0x09d3, B:503:0x09db, B:504:0x09e1, B:506:0x09e7, B:510:0x09f5, B:512:0x09f9, B:516:0x0a09, B:524:0x0a20, B:526:0x0a26, B:527:0x0a81, B:515:0x0a01, B:508:0x09ee, B:517:0x0a0c, B:521:0x0a13, B:522:0x0a19, B:495:0x09b6, B:498:0x09c4, B:499:0x09cb, B:500:0x09cc, B:422:0x0888, B:424:0x088f, B:426:0x0893, B:454:0x090b, B:456:0x0917, B:431:0x08a0, B:433:0x08a4, B:435:0x08b6, B:437:0x08c4, B:439:0x08d0, B:443:0x08d9, B:445:0x08e3, B:451:0x08ee, B:457:0x091b, B:459:0x0922, B:461:0x0926, B:465:0x092f, B:467:0x093d, B:469:0x0945, B:471:0x094f, B:472:0x0954, B:473:0x0959, B:474:0x095e, B:407:0x0848, B:528:0x0a89, B:243:0x057a, B:245:0x0580, B:248:0x0586, B:251:0x0591, B:253:0x0597, B:256:0x05a5, B:258:0x05ab, B:259:0x05b3, B:260:0x05b6, B:262:0x05be, B:264:0x05cc, B:266:0x0608, B:268:0x0612, B:271:0x061d, B:273:0x0625, B:274:0x0628, B:276:0x062c, B:278:0x0632, B:280:0x063c, B:282:0x0646, B:284:0x0657, B:286:0x065d, B:287:0x0668, B:288:0x066b, B:290:0x0674, B:293:0x0679, B:295:0x067f, B:297:0x0687, B:299:0x068d, B:301:0x0693, B:305:0x06a1, B:307:0x06a9, B:308:0x06ac, B:239:0x056a, B:529:0x0a91, B:533:0x0a98, B:534:0x0aa0, B:538:0x0abe), top: B:596:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:614:0x071d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:631:0x083f A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v26, types: [com.google.android.gms.internal.ads.zzwe, com.google.android.gms.internal.ads.zzfx] */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean handleMessage(android.os.Message r47) {
        /*
            Method dump skipped, instructions count: 3030
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzjb.handleMessage(android.os.Message):boolean");
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zza(zzby zzbyVar) {
        this.zzh.zzb(16, zzbyVar).zza();
    }

    public final Looper zzb() {
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Boolean zzd() {
        return Boolean.valueOf(this.zzv);
    }

    @Override // com.google.android.gms.internal.ads.zztx
    public final /* bridge */ /* synthetic */ void zzg(zzty zztyVar) {
        this.zzh.zzb(9, (zzse) zztyVar).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzjq
    public final void zzh() {
        this.zzh.zzh(22);
    }

    @Override // com.google.android.gms.internal.ads.zzsd
    public final void zzi(zzse zzseVar) {
        this.zzh.zzb(8, zzseVar).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzvv
    public final void zzj() {
        this.zzh.zzh(10);
    }

    public final void zzk() {
        this.zzh.zza(0).zza();
    }

    public final void zzl(zzcn zzcnVar, int r4, long j) {
        this.zzh.zzb(3, new zzja(zzcnVar, r4, j)).zza();
    }

    @Override // com.google.android.gms.internal.ads.zzjt
    public final synchronized void zzm(zzjv zzjvVar) {
        if (!this.zzv && this.zzi.isAlive()) {
            this.zzh.zzb(14, zzjvVar).zza();
            return;
        }
        Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        zzjvVar.zzh(false);
    }

    public final void zzn(boolean z, int r4) {
        this.zzh.zzc(1, z ? 1 : 0, r4).zza();
    }

    public final void zzo() {
        this.zzh.zza(6).zza();
    }

    public final synchronized boolean zzp() {
        if (!this.zzv && this.zzi.isAlive()) {
            this.zzh.zzh(7);
            zzaa(new zzis(this), 500L);
            return this.zzv;
        }
        return true;
    }

    public final void zzq(List list, int r12, long j, zztz zztzVar) {
        this.zzh.zzb(17, new zziw(list, zztzVar, r12, j, null, null)).zza();
    }
}
