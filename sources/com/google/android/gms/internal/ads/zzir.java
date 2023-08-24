package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.ExoPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzir extends zzm implements zzhj {
    public static final /* synthetic */ int zzd = 0;
    private final zzkk zzA;
    private final zzkl zzB;
    private final long zzC;
    private int zzD;
    private int zzE;
    private boolean zzF;
    private int zzG;
    private zzkb zzH;
    private zzcc zzI;
    private zzbm zzJ;
    private zzbm zzK;
    private zzaf zzL;
    private zzaf zzM;
    private AudioTrack zzN;
    private Object zzO;
    private Surface zzP;
    private int zzQ;
    private int zzR;
    private int zzS;
    private zzgq zzT;
    private zzgq zzU;
    private int zzV;
    private zzk zzW;
    private float zzX;
    private boolean zzY;
    private zzdc zzZ;
    private boolean zzaa;
    private boolean zzab;
    private zzt zzac;
    private zzda zzad;
    private zzbm zzae;
    private zzjs zzaf;
    private int zzag;
    private long zzah;
    private final zzhu zzai;
    private zztz zzaj;
    final zzvx zzb;
    final zzcc zzc;
    private final zzdg zze;
    private final Context zzf;
    private final zzcg zzg;
    private final zzjy[] zzh;
    private final zzvw zzi;
    private final zzdn zzj;
    private final zzjb zzk;
    private final zzdt zzl;
    private final CopyOnWriteArraySet zzm;
    private final zzck zzn;
    private final List zzo;
    private final boolean zzp;
    private final zzsf zzq;
    private final zzkm zzr;
    private final Looper zzs;
    private final zzwe zzt;
    private final zzde zzu;
    private final zzin zzv;
    private final zzip zzw;
    private final zzgk zzx;
    private final zzgo zzy;
    private final zzkj zzz;

    static {
        zzbh.zzb("media3.exoplayer");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v6, types: [com.google.android.gms.internal.ads.zzwd, java.lang.Object, com.google.android.gms.internal.ads.zzkm] */
    public zzir(zzhi zzhiVar, zzcg zzcgVar) {
        zzmz zza;
        zzdg zzdgVar = new zzdg(zzde.zza);
        this.zze = zzdgVar;
        try {
            String hexString = Integer.toHexString(System.identityHashCode(this));
            String str = zzel.zze;
            Log.i("ExoPlayerImpl", "Init " + hexString + " [AndroidXMedia3/1.0.0-beta01] [" + str + "]");
            Context applicationContext = zzhiVar.zza.getApplicationContext();
            this.zzf = applicationContext;
            ?? apply = zzhiVar.zzh.apply(zzhiVar.zzb);
            this.zzr = apply;
            this.zzW = zzhiVar.zzj;
            this.zzQ = 1;
            this.zzY = false;
            this.zzC = ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
            zzin zzinVar = new zzin(this, null);
            this.zzv = zzinVar;
            zzip zzipVar = new zzip(null);
            this.zzw = zzipVar;
            Handler handler = new Handler(zzhiVar.zzi);
            zzjy[] zza2 = ((zzhc) zzhiVar.zzc).zza.zza(handler, zzinVar, zzinVar, zzinVar, zzinVar);
            this.zzh = zza2;
            int length = zza2.length;
            zzvw zzvwVar = (zzvw) zzhiVar.zze.zza();
            this.zzi = zzvwVar;
            this.zzq = zzhi.zza(((zzhd) zzhiVar.zzd).zza);
            zzwi zzg = zzwi.zzg(((zzhg) zzhiVar.zzg).zza);
            this.zzt = zzg;
            this.zzp = true;
            this.zzH = zzhiVar.zzk;
            Looper looper = zzhiVar.zzi;
            this.zzs = looper;
            zzde zzdeVar = zzhiVar.zzb;
            this.zzu = zzdeVar;
            this.zzg = zzcgVar;
            zzdt zzdtVar = new zzdt(looper, zzdeVar, new zzdr() { // from class: com.google.android.gms.internal.ads.zzht
                @Override // com.google.android.gms.internal.ads.zzdr
                public final void zza(Object obj, zzaa zzaaVar) {
                    zzcd zzcdVar = (zzcd) obj;
                }
            });
            this.zzl = zzdtVar;
            CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
            this.zzm = copyOnWriteArraySet;
            this.zzo = new ArrayList();
            this.zzaj = new zztz(0);
            int length2 = zza2.length;
            zzvx zzvxVar = new zzvx(new zzka[2], new zzvq[2], zzcy.zza, null);
            this.zzb = zzvxVar;
            this.zzn = new zzck();
            zzca zzcaVar = new zzca();
            zzcaVar.zzc(1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 20, 30, 21, 22, 23, 24, 25, 26, 27, 28, 31);
            zzvwVar.zzl();
            zzcaVar.zzd(29, true);
            zzcc zze = zzcaVar.zze();
            this.zzc = zze;
            zzca zzcaVar2 = new zzca();
            zzcaVar2.zzb(zze);
            zzcaVar2.zza(4);
            zzcaVar2.zza(10);
            this.zzI = zzcaVar2.zze();
            this.zzj = zzdeVar.zzb(looper, null);
            zzhu zzhuVar = new zzhu(this);
            this.zzai = zzhuVar;
            this.zzaf = zzjs.zzh(zzvxVar);
            apply.zzS(zzcgVar, looper);
            if (zzel.zza < 31) {
                zza = new zzmz();
            } else {
                zza = zzig.zza(applicationContext, this, true);
            }
            this.zzk = new zzjb(zza2, zzvwVar, zzvxVar, (zzjf) zzhiVar.zzf.zza(), zzg, 0, false, apply, this.zzH, zzhiVar.zzm, 500L, false, looper, zzdeVar, zzhuVar, zza, null);
            this.zzX = 1.0f;
            zzbm zzbmVar = zzbm.zza;
            this.zzJ = zzbmVar;
            this.zzK = zzbmVar;
            this.zzae = zzbmVar;
            this.zzag = -1;
            if (zzel.zza < 21) {
                AudioTrack audioTrack = this.zzN;
                if (audioTrack != null && audioTrack.getAudioSessionId() != 0) {
                    this.zzN.release();
                    this.zzN = null;
                }
                if (this.zzN == null) {
                    this.zzN = new AudioTrack(3, 4000, 4, 2, 2, 0, 0);
                }
                this.zzV = this.zzN.getAudioSessionId();
            } else {
                this.zzV = zzel.zzi(applicationContext);
            }
            this.zzZ = zzdc.zza;
            this.zzaa = true;
            Objects.requireNonNull(apply);
            zzdtVar.zzb(apply);
            zzg.zze(new Handler(looper), apply);
            copyOnWriteArraySet.add(zzinVar);
            this.zzx = new zzgk(zzhiVar.zza, handler, zzinVar);
            this.zzy = new zzgo(zzhiVar.zza, handler, zzinVar);
            zzel.zzT(null, null);
            zzkj zzkjVar = new zzkj(zzhiVar.zza, handler, zzinVar);
            this.zzz = zzkjVar;
            int r3 = this.zzW.zzc;
            zzkjVar.zzf(3);
            this.zzA = new zzkk(zzhiVar.zza);
            this.zzB = new zzkl(zzhiVar.zza);
            this.zzac = zzam(zzkjVar);
            this.zzad = zzda.zza;
            zzvwVar.zzi(this.zzW);
            zzaq(1, 10, Integer.valueOf(this.zzV));
            zzaq(2, 10, Integer.valueOf(this.zzV));
            zzaq(1, 3, this.zzW);
            zzaq(2, 4, Integer.valueOf(this.zzQ));
            zzaq(2, 5, 0);
            zzaq(1, 9, Boolean.valueOf(this.zzY));
            zzaq(2, 7, zzipVar);
            zzaq(6, 8, zzipVar);
            zzdgVar.zze();
        } catch (Throwable th) {
            this.zze.zze();
            throw th;
        }
    }

    public static /* bridge */ /* synthetic */ zzkj zzB(zzir zzirVar) {
        return zzirVar.zzz;
    }

    public static /* bridge */ /* synthetic */ void zzG(zzir zzirVar, zzt zztVar) {
        zzirVar.zzac = zztVar;
    }

    public static /* bridge */ /* synthetic */ void zzN(zzir zzirVar, SurfaceTexture surfaceTexture) {
        Surface surface = new Surface(surfaceTexture);
        zzirVar.zzas(surface);
        zzirVar.zzP = surface;
    }

    private final int zzag() {
        if (this.zzaf.zza.zzo()) {
            return this.zzag;
        }
        zzjs zzjsVar = this.zzaf;
        return zzjsVar.zza.zzn(zzjsVar.zzb.zza, this.zzn).zzd;
    }

    public static int zzah(boolean z, int r2) {
        return (!z || r2 == 1) ? 1 : 2;
    }

    private final long zzai(zzjs zzjsVar) {
        if (zzjsVar.zza.zzo()) {
            return zzel.zzv(this.zzah);
        }
        if (zzjsVar.zzb.zzb()) {
            return zzjsVar.zzs;
        }
        zzcn zzcnVar = zzjsVar.zza;
        zzsg zzsgVar = zzjsVar.zzb;
        long j = zzjsVar.zzs;
        zzak(zzcnVar, zzsgVar, j);
        return j;
    }

    private static long zzaj(zzjs zzjsVar) {
        zzcm zzcmVar = new zzcm();
        zzck zzckVar = new zzck();
        zzjsVar.zza.zzn(zzjsVar.zzb.zza, zzckVar);
        long j = zzjsVar.zzc;
        if (j == C1856C.TIME_UNSET) {
            long j2 = zzjsVar.zza.zze(zzckVar.zzd, zzcmVar, 0L).zzm;
            return 0L;
        }
        return j;
    }

    private final long zzak(zzcn zzcnVar, zzsg zzsgVar, long j) {
        zzcnVar.zzn(zzsgVar.zza, this.zzn);
        return j;
    }

    private final Pair zzal(zzcn zzcnVar, int r8, long j) {
        if (zzcnVar.zzo()) {
            this.zzag = r8;
            if (j == C1856C.TIME_UNSET) {
                j = 0;
            }
            this.zzah = j;
            return null;
        }
        if (r8 == -1 || r8 >= zzcnVar.zzc()) {
            r8 = zzcnVar.zzg(false);
            long j2 = zzcnVar.zze(r8, this.zza, 0L).zzm;
            j = zzel.zzz(0L);
        }
        return zzcnVar.zzl(this.zza, this.zzn, r8, zzel.zzv(j));
    }

    public static zzt zzam(zzkj zzkjVar) {
        return new zzt(0, zzkjVar.zzb(), zzkjVar.zza());
    }

    private final zzjs zzan(zzjs zzjsVar, zzcn zzcnVar, Pair pair) {
        zzsg zzsgVar;
        zzvx zzvxVar;
        int r2;
        zzjs zzb;
        zzdd.zzd(zzcnVar.zzo() || pair != null);
        zzcn zzcnVar2 = zzjsVar.zza;
        zzjs zzg = zzjsVar.zzg(zzcnVar);
        if (zzcnVar.zzo()) {
            zzsg zzi = zzjs.zzi();
            long zzv = zzel.zzv(this.zzah);
            zzjs zza = zzg.zzb(zzi, zzv, zzv, zzv, 0L, zzue.zza, this.zzb, zzfuv.zzo()).zza(zzi);
            zza.zzq = zza.zzs;
            return zza;
        }
        Object obj = zzg.zzb.zza;
        int r8 = zzel.zza;
        boolean z = !obj.equals(pair.first);
        zzsg zzsgVar2 = z ? new zzsg(pair.first) : zzg.zzb;
        long longValue = ((Long) pair.second).longValue();
        long zzv2 = zzel.zzv(zzk());
        if (!zzcnVar2.zzo()) {
            zzcnVar2.zzn(obj, this.zzn);
        }
        if (z || longValue < zzv2) {
            zzdd.zzf(!zzsgVar2.zzb());
            zzue zzueVar = z ? zzue.zza : zzg.zzh;
            if (z) {
                zzsgVar = zzsgVar2;
                zzvxVar = this.zzb;
            } else {
                zzsgVar = zzsgVar2;
                zzvxVar = zzg.zzi;
            }
            zzjs zza2 = zzg.zzb(zzsgVar, longValue, longValue, longValue, 0L, zzueVar, zzvxVar, z ? zzfuv.zzo() : zzg.zzj).zza(zzsgVar);
            zza2.zzq = longValue;
            return zza2;
        }
        if (r2 == 0) {
            int zza3 = zzcnVar.zza(zzg.zzk.zza);
            if (zza3 != -1 && zzcnVar.zzd(zza3, this.zzn, false).zzd == zzcnVar.zzn(zzsgVar2.zza, this.zzn).zzd) {
                return zzg;
            }
            zzcnVar.zzn(zzsgVar2.zza, this.zzn);
            long zzg2 = zzsgVar2.zzb() ? this.zzn.zzg(zzsgVar2.zzb, zzsgVar2.zzc) : this.zzn.zze;
            zzb = zzg.zzb(zzsgVar2, zzg.zzs, zzg.zzs, zzg.zzd, zzg2 - zzg.zzs, zzg.zzh, zzg.zzi, zzg.zzj).zza(zzsgVar2);
            zzb.zzq = zzg2;
        } else {
            zzdd.zzf(!zzsgVar2.zzb());
            long max = Math.max(0L, zzg.zzr - (longValue - zzv2));
            long j = zzg.zzq;
            if (zzg.zzk.equals(zzg.zzb)) {
                j = longValue + max;
            }
            zzb = zzg.zzb(zzsgVar2, longValue, longValue, longValue, max, zzg.zzh, zzg.zzi, zzg.zzj);
            zzb.zzq = j;
        }
        return zzb;
    }

    private final zzjv zzao(zzju zzjuVar) {
        int zzag = zzag();
        zzjb zzjbVar = this.zzk;
        return new zzjv(zzjbVar, zzjuVar, this.zzaf.zza, zzag == -1 ? 0 : zzag, this.zzu, zzjbVar.zzb());
    }

    public final void zzap(final int r3, final int r4) {
        if (r3 == this.zzR && r4 == this.zzS) {
            return;
        }
        this.zzR = r3;
        this.zzS = r4;
        zzdt zzdtVar = this.zzl;
        zzdtVar.zzd(24, new zzdq() { // from class: com.google.android.gms.internal.ads.zzhx
            @Override // com.google.android.gms.internal.ads.zzdq
            public final void zza(Object obj) {
                int r0 = r3;
                int r1 = r4;
                int r2 = zzir.zzd;
                ((zzcd) obj).zzr(r0, r1);
            }
        });
        zzdtVar.zzc();
    }

    private final void zzaq(int r5, int r6, Object obj) {
        zzjy[] zzjyVarArr = this.zzh;
        int length = zzjyVarArr.length;
        for (int r1 = 0; r1 < 2; r1++) {
            zzjy zzjyVar = zzjyVarArr[r1];
            if (zzjyVar.zzb() == r5) {
                zzjv zzao = zzao(zzjyVar);
                zzao.zzf(r6);
                zzao.zze(obj);
                zzao.zzd();
            }
        }
    }

    public final void zzar() {
        zzaq(1, 2, Float.valueOf(this.zzX * this.zzy.zza()));
    }

    public final void zzas(Object obj) {
        boolean z;
        ArrayList<zzjv> arrayList = new ArrayList();
        zzjy[] zzjyVarArr = this.zzh;
        int length = zzjyVarArr.length;
        int r3 = 0;
        while (true) {
            z = true;
            if (r3 >= 2) {
                break;
            }
            zzjy zzjyVar = zzjyVarArr[r3];
            if (zzjyVar.zzb() == 2) {
                zzjv zzao = zzao(zzjyVar);
                zzao.zzf(1);
                zzao.zze(obj);
                zzao.zzd();
                arrayList.add(zzao);
            }
            r3++;
        }
        Object obj2 = this.zzO;
        if (obj2 == null || obj2 == obj) {
            z = false;
        } else {
            try {
                for (zzjv zzjvVar : arrayList) {
                    zzjvVar.zzi(this.zzC);
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (TimeoutException unused2) {
            }
            z = false;
            Object obj3 = this.zzO;
            Surface surface = this.zzP;
            if (obj3 == surface) {
                surface.release();
                this.zzP = null;
            }
        }
        this.zzO = obj;
        if (z) {
            zzat(false, zzgy.zzd(new zzjd(3), 1003));
        }
    }

    private final void zzat(boolean z, zzgy zzgyVar) {
        zzjs zzjsVar = this.zzaf;
        zzjs zza = zzjsVar.zza(zzjsVar.zzb);
        zza.zzq = zza.zzs;
        zza.zzr = 0L;
        zzjs zzf = zza.zzf(1);
        if (zzgyVar != null) {
            zzf = zzf.zze(zzgyVar);
        }
        zzjs zzjsVar2 = zzf;
        this.zzD++;
        this.zzk.zzo();
        zzav(zzjsVar2, 0, 1, false, zzjsVar2.zza.zzo() && !this.zzaf.zza.zzo(), 4, zzai(zzjsVar2), -1);
    }

    public final void zzau(boolean z, int r12, int r13) {
        int r2 = 0;
        boolean z2 = z && r12 != -1;
        if (z2 && r12 != 1) {
            r2 = 1;
        }
        zzjs zzjsVar = this.zzaf;
        if (zzjsVar.zzl == z2 && zzjsVar.zzm == r2) {
            return;
        }
        this.zzD++;
        zzjs zzd2 = zzjsVar.zzd(z2, r2);
        this.zzk.zzn(z2, r2);
        zzav(zzd2, 0, r13, false, false, 5, C1856C.TIME_UNSET, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:222:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzav(final com.google.android.gms.internal.ads.zzjs r44, final int r45, final int r46, boolean r47, boolean r48, final int r49, long r50, int r52) {
        /*
            Method dump skipped, instructions count: 1035
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzir.zzav(com.google.android.gms.internal.ads.zzjs, int, int, boolean, boolean, int, long, int):void");
    }

    public final void zzaw() {
        int zzh = zzh();
        if (zzh == 2 || zzh == 3) {
            zzax();
            boolean z = this.zzaf.zzp;
            zzq();
            zzq();
        }
    }

    private final void zzax() {
        this.zze.zzb();
        if (Thread.currentThread() != this.zzs.getThread()) {
            String zzI = zzel.zzI("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://exoplayer.dev/issues/player-accessed-on-wrong-thread", Thread.currentThread().getName(), this.zzs.getThread().getName());
            if (this.zzaa) {
                throw new IllegalStateException(zzI);
            }
            zzdu.zzb("ExoPlayerImpl", zzI, this.zzab ? null : new IllegalStateException());
            this.zzab = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzay(zzjs zzjsVar) {
        return zzjsVar.zze == 3 && zzjsVar.zzl && zzjsVar.zzm == 0;
    }

    public static /* bridge */ /* synthetic */ zzt zzx(zzir zzirVar) {
        return zzirVar.zzac;
    }

    public static /* bridge */ /* synthetic */ zzt zzy(zzkj zzkjVar) {
        return zzam(zzkjVar);
    }

    public static /* bridge */ /* synthetic */ zzdt zzz(zzir zzirVar) {
        return zzirVar.zzl;
    }

    public final zzgy zzA() {
        zzax();
        return this.zzaf.zzf;
    }

    public final /* synthetic */ void zzS(zziz zzizVar) {
        long j;
        boolean z;
        long j2;
        int r1 = this.zzD - zzizVar.zzb;
        this.zzD = r1;
        boolean z2 = true;
        if (zzizVar.zzc) {
            this.zzE = zzizVar.zzd;
            this.zzF = true;
        }
        if (zzizVar.zze) {
            this.zzG = zzizVar.zzf;
        }
        if (r1 == 0) {
            zzcn zzcnVar = zzizVar.zza.zza;
            if (!this.zzaf.zza.zzo() && zzcnVar.zzo()) {
                this.zzag = -1;
                this.zzah = 0L;
            }
            if (!zzcnVar.zzo()) {
                List zzw = ((zzjw) zzcnVar).zzw();
                zzdd.zzf(zzw.size() == this.zzo.size());
                for (int r5 = 0; r5 < zzw.size(); r5++) {
                    ((zziq) this.zzo.get(r5)).zzb = (zzcn) zzw.get(r5);
                }
            }
            if (this.zzF) {
                if (zzizVar.zza.zzb.equals(this.zzaf.zzb) && zzizVar.zza.zzd == this.zzaf.zzs) {
                    z2 = false;
                }
                if (z2) {
                    if (zzcnVar.zzo() || zzizVar.zza.zzb.zzb()) {
                        j2 = zzizVar.zza.zzd;
                    } else {
                        zzjs zzjsVar = zzizVar.zza;
                        zzsg zzsgVar = zzjsVar.zzb;
                        j2 = zzjsVar.zzd;
                        zzak(zzcnVar, zzsgVar, j2);
                    }
                    z = z2;
                    j = j2;
                } else {
                    j = -9223372036854775807L;
                    z = z2;
                }
            } else {
                j = -9223372036854775807L;
                z = false;
            }
            this.zzF = false;
            zzav(zzizVar.zza, 1, this.zzG, false, z, this.zzE, j, -1);
        }
    }

    public final /* synthetic */ void zzT(final zziz zzizVar) {
        this.zzj.zzg(new Runnable() { // from class: com.google.android.gms.internal.ads.zzhv
            @Override // java.lang.Runnable
            public final void run() {
                zzir.this.zzS(zzizVar);
            }
        });
    }

    public final /* synthetic */ void zzU(zzcd zzcdVar) {
        zzcdVar.zza(this.zzI);
    }

    public final void zzW() {
        zzax();
        boolean zzq = zzq();
        int zzb = this.zzy.zzb(zzq, 2);
        zzau(zzq, zzb, zzah(zzq, zzb));
        zzjs zzjsVar = this.zzaf;
        if (zzjsVar.zze != 1) {
            return;
        }
        zzjs zze = zzjsVar.zze(null);
        zzjs zzf = zze.zzf(true == zze.zza.zzo() ? 4 : 2);
        this.zzD++;
        this.zzk.zzk();
        zzav(zzf, 1, 1, false, false, 5, C1856C.TIME_UNSET, -1);
    }

    public final void zzX() {
        AudioTrack audioTrack;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = zzel.zze;
        String zza = zzbh.zza();
        Log.i("ExoPlayerImpl", "Release " + hexString + " [AndroidXMedia3/1.0.0-beta01] [" + str + "] [" + zza + "]");
        zzax();
        if (zzel.zza < 21 && (audioTrack = this.zzN) != null) {
            audioTrack.release();
            this.zzN = null;
        }
        this.zzz.zze();
        this.zzy.zzd();
        if (!this.zzk.zzp()) {
            zzdt zzdtVar = this.zzl;
            zzdtVar.zzd(10, new zzdq() { // from class: com.google.android.gms.internal.ads.zzhw
                @Override // com.google.android.gms.internal.ads.zzdq
                public final void zza(Object obj) {
                    ((zzcd) obj).zzl(zzgy.zzd(new zzjd(1), 1003));
                }
            });
            zzdtVar.zzc();
        }
        this.zzl.zze();
        this.zzj.zzd(null);
        this.zzt.zzf(this.zzr);
        zzjs zzf = this.zzaf.zzf(1);
        this.zzaf = zzf;
        zzjs zza2 = zzf.zza(zzf.zzb);
        this.zzaf = zza2;
        zza2.zzq = zza2.zzs;
        this.zzaf.zzr = 0L;
        this.zzr.zzQ();
        this.zzi.zzh();
        Surface surface = this.zzP;
        if (surface != null) {
            surface.release();
            this.zzP = null;
        }
        this.zzZ = zzdc.zza;
    }

    public final void zzY(zzkp zzkpVar) {
        this.zzr.zzR(zzkpVar);
    }

    public final void zzZ(zzsi zzsiVar) {
        zzax();
        List singletonList = Collections.singletonList(zzsiVar);
        zzax();
        zzax();
        zzag();
        zzl();
        this.zzD++;
        if (!this.zzo.isEmpty()) {
            int size = this.zzo.size();
            for (int r4 = size - 1; r4 >= 0; r4--) {
                this.zzo.remove(r4);
            }
            this.zzaj = this.zzaj.zzh(0, size);
        }
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < singletonList.size(); r1++) {
            zzjp zzjpVar = new zzjp((zzsi) singletonList.get(r1), this.zzp);
            arrayList.add(zzjpVar);
            this.zzo.add(r1, new zziq(zzjpVar.zzb, zzjpVar.zza.zzA()));
        }
        this.zzaj = this.zzaj.zzg(0, arrayList.size());
        zzjw zzjwVar = new zzjw(this.zzo, this.zzaj, null);
        if (zzjwVar.zzo() || zzjwVar.zzc() >= 0) {
            int zzg = zzjwVar.zzg(false);
            zzjs zzan = zzan(this.zzaf, zzjwVar, zzal(zzjwVar, zzg, C1856C.TIME_UNSET));
            int r9 = zzan.zze;
            if (zzg != -1 && r9 != 1) {
                r9 = (zzjwVar.zzo() || zzg >= zzjwVar.zzc()) ? 4 : 2;
            }
            zzjs zzf = zzan.zzf(r9);
            this.zzk.zzq(arrayList, zzg, zzel.zzv(C1856C.TIME_UNSET), this.zzaj);
            zzav(zzf, 0, 1, false, (this.zzaf.zzb.zza.equals(zzf.zzb.zza) || this.zzaf.zza.zzo()) ? false : true, 4, zzai(zzf), -1);
            return;
        }
        throw new zzag(zzjwVar, -1, C1856C.TIME_UNSET);
    }

    public final void zzaa(boolean z) {
        zzax();
        int zzb = this.zzy.zzb(z, zzh());
        zzau(z, zzb, zzah(z, zzb));
    }

    public final void zzab(boolean z) {
        this.zzaa = false;
    }

    public final void zzac(Surface surface) {
        zzax();
        zzas(surface);
        int r1 = surface == null ? 0 : -1;
        zzap(r1, r1);
    }

    public final void zzad(float f) {
        zzax();
        final float zza = zzel.zza(f, 0.0f, 1.0f);
        if (this.zzX == zza) {
            return;
        }
        this.zzX = zza;
        zzar();
        zzdt zzdtVar = this.zzl;
        zzdtVar.zzd(22, new zzdq() { // from class: com.google.android.gms.internal.ads.zzhy
            @Override // com.google.android.gms.internal.ads.zzdq
            public final void zza(Object obj) {
                float f2 = zza;
                int r1 = zzir.zzd;
                ((zzcd) obj).zzv(f2);
            }
        });
        zzdtVar.zzc();
    }

    public final void zzae() {
        zzax();
        zzax();
        this.zzy.zzb(zzq(), 1);
        zzat(false, null);
        this.zzZ = zzdc.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzd() {
        zzax();
        if (zzs()) {
            return this.zzaf.zzb.zzb;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zze() {
        zzax();
        if (zzs()) {
            return this.zzaf.zzb.zzc;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzf() {
        zzax();
        int zzag = zzag();
        if (zzag == -1) {
            return 0;
        }
        return zzag;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzg() {
        zzax();
        if (this.zzaf.zza.zzo()) {
            return 0;
        }
        zzjs zzjsVar = this.zzaf;
        return zzjsVar.zza.zza(zzjsVar.zzb.zza);
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzh() {
        zzax();
        return this.zzaf.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzi() {
        zzax();
        return this.zzaf.zzm;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final int zzj() {
        zzax();
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final long zzk() {
        zzax();
        if (zzs()) {
            zzjs zzjsVar = this.zzaf;
            zzjsVar.zza.zzn(zzjsVar.zzb.zza, this.zzn);
            zzjs zzjsVar2 = this.zzaf;
            if (zzjsVar2.zzc != C1856C.TIME_UNSET) {
                return zzel.zzz(0L) + zzel.zzz(this.zzaf.zzc);
            }
            long j = zzjsVar2.zza.zze(zzf(), this.zza, 0L).zzm;
            return zzel.zzz(0L);
        }
        return zzl();
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final long zzl() {
        zzax();
        return zzel.zzz(zzai(this.zzaf));
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final long zzm() {
        zzax();
        return zzel.zzz(this.zzaf.zzr);
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final zzcn zzn() {
        zzax();
        return this.zzaf.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final zzcy zzo() {
        zzax();
        return this.zzaf.zzi.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final void zzp(int r14, long j) {
        zzax();
        this.zzr.zzx();
        zzcn zzcnVar = this.zzaf.zza;
        if (r14 >= 0 && (zzcnVar.zzo() || r14 < zzcnVar.zzc())) {
            this.zzD++;
            if (zzs()) {
                Log.w("ExoPlayerImpl", "seekTo ignored because an ad is playing");
                zziz zzizVar = new zziz(this.zzaf);
                zzizVar.zza(1);
                this.zzai.zza.zzT(zzizVar);
                return;
            }
            int r5 = zzh() != 1 ? 2 : 1;
            int zzf = zzf();
            zzjs zzan = zzan(this.zzaf.zzf(r5), zzcnVar, zzal(zzcnVar, r14, j));
            this.zzk.zzl(zzcnVar, r14, zzel.zzv(j));
            zzav(zzan, 0, 1, true, true, 1, zzai(zzan), zzf);
            return;
        }
        throw new zzag(zzcnVar, r14, j);
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final boolean zzq() {
        zzax();
        return this.zzaf.zzl;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final boolean zzr() {
        zzax();
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzcg
    public final boolean zzs() {
        zzax();
        return this.zzaf.zzb.zzb();
    }

    public final int zzu() {
        zzax();
        int length = this.zzh.length;
        return 2;
    }

    public final long zzv() {
        zzax();
        if (!zzs()) {
            zzax();
            if (this.zzaf.zza.zzo()) {
                return this.zzah;
            }
            zzjs zzjsVar = this.zzaf;
            long j = 0;
            if (zzjsVar.zzk.zzd != zzjsVar.zzb.zzd) {
                return zzel.zzz(zzjsVar.zza.zze(zzf(), this.zza, 0L).zzn);
            }
            long j2 = zzjsVar.zzq;
            if (this.zzaf.zzk.zzb()) {
                zzjs zzjsVar2 = this.zzaf;
                zzjsVar2.zza.zzn(zzjsVar2.zzk.zza, this.zzn).zzh(this.zzaf.zzk.zzb);
            } else {
                j = j2;
            }
            zzjs zzjsVar3 = this.zzaf;
            zzak(zzjsVar3.zza, zzjsVar3.zzk, j);
            return zzel.zzz(j);
        }
        zzjs zzjsVar4 = this.zzaf;
        if (zzjsVar4.zzk.equals(zzjsVar4.zzb)) {
            return zzel.zzz(this.zzaf.zzq);
        }
        return zzw();
    }

    public final long zzw() {
        zzax();
        if (!zzs()) {
            zzcn zzn = zzn();
            return zzn.zzo() ? C1856C.TIME_UNSET : zzel.zzz(zzn.zze(zzf(), this.zza, 0L).zzn);
        }
        zzjs zzjsVar = this.zzaf;
        zzsg zzsgVar = zzjsVar.zzb;
        zzjsVar.zza.zzn(zzsgVar.zza, this.zzn);
        return zzel.zzz(this.zzn.zzg(zzsgVar.zzb, zzsgVar.zzc));
    }

    public final void zzR(zzkp zzkpVar) {
        Objects.requireNonNull(zzkpVar);
        this.zzr.zzw(zzkpVar);
    }
}
