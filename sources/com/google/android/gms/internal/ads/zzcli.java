package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcli extends zzckz implements zzcim {
    public static final /* synthetic */ int zzd = 0;
    private zzcin zze;
    private String zzf;
    private boolean zzg;
    private boolean zzh;
    private zzckr zzi;
    private long zzj;
    private long zzk;

    public zzcli(zzciw zzciwVar, zzciv zzcivVar) {
        super(zzciwVar);
        zzcin zzckeVar;
        Context context = zzciwVar.getContext();
        if (zzcivVar.zzm) {
            zzckeVar = new zzcma(context, zzcivVar, (zzciw) this.zzc.get());
        } else {
            zzckeVar = new zzcke(context, zzcivVar, (zzciw) this.zzc.get());
        }
        this.zze = zzckeVar;
        zzckeVar.zzI(this);
    }

    protected static final String zzu(String str) {
        return "cache:".concat(String.valueOf(zzcgg.zze(str)));
    }

    private static String zzw(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        return str + "/" + canonicalName + ParameterizedMessage.ERROR_MSG_SEPARATOR + message;
    }

    private final void zzx(long j) {
        com.google.android.gms.ads.internal.util.zzs.zza.postDelayed(new Runnable() { // from class: com.google.android.gms.internal.ads.zzclg
            @Override // java.lang.Runnable
            public final void run() {
                zzcli.this.zzt();
            }
        }, j);
    }

    @Override // com.google.android.gms.internal.ads.zzckz, com.google.android.gms.common.api.Releasable
    public final void release() {
        zzcin zzcinVar = this.zze;
        if (zzcinVar != null) {
            zzcinVar.zzI(null);
            this.zze.zzE();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzC(int r1, int r2) {
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzb() {
        synchronized (this) {
            this.zzg = true;
            notify();
            release();
        }
        String str = this.zzf;
        if (str != null) {
            zzc(this.zzf, zzu(str), "externalAbort", "Programmatic precache abort.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzh(int r2) {
        this.zze.zzG(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzi(final boolean z, final long j) {
        final zzciw zzciwVar = (zzciw) this.zzc.get();
        if (zzciwVar != null) {
            zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzclh
                @Override // java.lang.Runnable
                public final void run() {
                    zzciw zzciwVar2 = zzciw.this;
                    boolean z2 = z;
                    long j2 = j;
                    int r4 = zzcli.zzd;
                    zzciwVar2.zzx(z2, j2);
                }
            });
        }
    }

    public final zzcin zzj() {
        synchronized (this) {
            this.zzh = true;
            notify();
        }
        this.zze.zzI(null);
        zzcin zzcinVar = this.zze;
        this.zze = null;
        return zzcinVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzk(String str, Exception exc) {
        com.google.android.gms.ads.internal.util.zze.zzk("Precache error", exc);
        com.google.android.gms.ads.internal.zzt.zzp().zzs(exc, "VideoStreamExoPlayerCache.onError");
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzl(String str, Exception exc) {
        com.google.android.gms.ads.internal.util.zze.zzk("Precache exception", exc);
        com.google.android.gms.ads.internal.zzt.zzp().zzs(exc, "VideoStreamExoPlayerCache.onException");
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzm(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzn(int r2) {
        this.zze.zzH(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzo(int r2) {
        this.zze.zzJ(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzp(int r2) {
        this.zze.zzK(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final boolean zzq(String str) {
        return zzr(str, new String[]{str});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r5v1, types: [com.google.android.gms.internal.ads.zzckz] */
    /* JADX WARN: Type inference failed for: r5v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v11, types: [int] */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r6v19 */
    @Override // com.google.android.gms.internal.ads.zzckz
    public final boolean zzr(String str, String[] strArr) {
        String str2;
        String str3;
        zzcli zzcliVar;
        long j;
        long j2;
        long j3;
        ?? r1;
        long j4;
        String str4;
        long j5;
        long j6;
        zzcli zzcliVar2 = this;
        String str5 = str;
        zzcliVar2.zzf = str5;
        String zzu = zzu(str);
        String str6 = "error";
        try {
            Uri[] uriArr = new Uri[strArr.length];
            for (int r2 = 0; r2 < strArr.length; r2++) {
                uriArr[r2] = Uri.parse(strArr[r2]);
            }
            zzcliVar2.zze.zzC(uriArr, zzcliVar2.zzb);
            zzciw zzciwVar = (zzciw) zzcliVar2.zzc.get();
            if (zzciwVar != null) {
                zzciwVar.zzv(zzu, zzcliVar2);
            }
            Clock zzB = com.google.android.gms.ads.internal.zzt.zzB();
            long currentTimeMillis = zzB.currentTimeMillis();
            long longValue = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzw)).longValue();
            long longValue2 = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzv)).longValue() * 1000;
            long intValue = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzu)).intValue();
            boolean booleanValue = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue();
            long j7 = -1;
            while (true) {
                synchronized (this) {
                    try {
                        if (zzB.currentTimeMillis() - currentTimeMillis > longValue2) {
                            throw new IOException("Timeout reached. Limit: " + longValue2 + " ms");
                        } else if (!zzcliVar2.zzg) {
                            if (zzcliVar2.zzh) {
                                break;
                            } else if (zzcliVar2.zze.zzR()) {
                                long zzz = zzcliVar2.zze.zzz();
                                if (zzz > 0) {
                                    long zzv = zzcliVar2.zze.zzv();
                                    if (zzv != j7) {
                                        try {
                                            j = intValue;
                                            long j8 = zzz;
                                            j2 = longValue2;
                                            j4 = longValue;
                                            str4 = zzu;
                                            try {
                                                zzg(str, zzu, zzv, j8, zzv > 0, booleanValue ? zzcliVar2.zze.zzA() : -1L, booleanValue ? zzcliVar2.zze.zzx() : -1L, booleanValue ? zzcliVar2.zze.zzB() : -1L, zzcin.zzs(), zzcin.zzu());
                                                j6 = zzv;
                                                j5 = zzz;
                                                str2 = j8;
                                            } catch (Throwable th) {
                                                th = th;
                                                zzcliVar = this;
                                                str2 = str;
                                                str3 = str4;
                                                try {
                                                    throw th;
                                                } catch (Exception e) {
                                                    e = e;
                                                    String str7 = str6;
                                                    com.google.android.gms.ads.internal.util.zze.zzj("Failed to preload url " + str2 + " Exception: " + e.getMessage());
                                                    com.google.android.gms.ads.internal.zzt.zzp().zzs(e, "VideoStreamExoPlayerCache.preload");
                                                    release();
                                                    zzcliVar.zzc(str2, str3, str7, zzw(str7, e));
                                                    return false;
                                                }
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            zzcliVar = this;
                                            str2 = str;
                                            str3 = zzu;
                                        }
                                    } else {
                                        j = intValue;
                                        j2 = longValue2;
                                        j4 = longValue;
                                        str4 = zzu;
                                        j5 = zzz;
                                        j6 = j7;
                                        str2 = intValue;
                                    }
                                    zzcliVar = (zzv > j5 ? 1 : (zzv == j5 ? 0 : -1));
                                    if (zzcliVar < 0) {
                                        try {
                                            zzcli zzcliVar3 = this;
                                            str2 = str;
                                            str3 = str4;
                                            if (zzcliVar3.zze.zzw() < j || zzv <= 0) {
                                                j3 = j4;
                                                r1 = j6;
                                                zzcliVar = zzcliVar3;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            throw th;
                                        }
                                    } else {
                                        zze(str, str4, j5);
                                    }
                                } else {
                                    j = intValue;
                                    j2 = longValue2;
                                    str2 = str5;
                                    str3 = zzu;
                                    zzcliVar = zzcliVar2;
                                    j3 = longValue;
                                    r1 = j7;
                                }
                                try {
                                    try {
                                        zzcliVar.wait(j3);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        str6 = r1;
                                        throw th;
                                    }
                                } catch (InterruptedException unused) {
                                    throw new IOException("Wait interrupted.");
                                }
                            } else {
                                throw new IOException("ExoPlayer was released during preloading.");
                            }
                        } else {
                            throw new IOException("Abort requested before buffering finished. ");
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        str2 = str5;
                        str3 = zzu;
                        zzcliVar = zzcliVar2;
                    }
                }
                longValue = j3;
                zzcliVar2 = zzcliVar;
                str5 = str2;
                zzu = str3;
                intValue = j;
                longValue2 = j2;
                j7 = r1;
            }
            return true;
        } catch (Exception e2) {
            e = e2;
            str2 = str5;
            str3 = zzu;
            zzcliVar = zzcliVar2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final boolean zzs(String str, String[] strArr, zzckr zzckrVar) {
        this.zzf = str;
        this.zzi = zzckrVar;
        String zzu = zzu(str);
        try {
            Uri[] uriArr = new Uri[strArr.length];
            for (int r2 = 0; r2 < strArr.length; r2++) {
                uriArr[r2] = Uri.parse(strArr[r2]);
            }
            this.zze.zzC(uriArr, this.zzb);
            zzciw zzciwVar = (zzciw) this.zzc.get();
            if (zzciwVar != null) {
                zzciwVar.zzv(zzu, this);
            }
            this.zzj = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
            this.zzk = -1L;
            zzx(0L);
            return true;
        } catch (Exception e) {
            String message = e.getMessage();
            com.google.android.gms.ads.internal.util.zze.zzj("Failed to preload url " + str + " Exception: " + message);
            com.google.android.gms.ads.internal.zzt.zzp().zzs(e, "VideoStreamExoPlayerCache.preload");
            release();
            zzc(str, zzu, "error", zzw("error", e));
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22, types: [com.google.android.gms.internal.ads.zzckz, com.google.android.gms.internal.ads.zzcli] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26, types: [com.google.android.gms.internal.ads.zzcli] */
    /* JADX WARN: Type inference failed for: r2v31 */
    public final /* synthetic */ void zzt() {
        String str;
        zzcli zzcliVar;
        zzcli zzcliVar2;
        zzbiw zzc;
        long longValue;
        long intValue;
        zzcli zzcliVar3;
        long j;
        long j2;
        String str2;
        long j3;
        String zzu = zzu(this.zzf);
        String str3 = "error";
        try {
            zzbiq zzbiqVar = zzbiy.zzv;
            zzc = com.google.android.gms.ads.internal.client.zzay.zzc();
            longValue = ((Long) zzc.zzb(zzbiqVar)).longValue() * 1000;
            intValue = ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzu)).intValue();
            zzcliVar = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbB)).booleanValue();
            try {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
            str = zzu;
            zzcliVar = this;
        }
        synchronized (this) {
            try {
                int r5 = ((com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() - this.zzj) > longValue ? 1 : ((com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() - this.zzj) == longValue ? 0 : -1));
                if (r5 <= 0) {
                    try {
                        if (!this.zzg) {
                            if (!this.zzh) {
                                if (!this.zze.zzR()) {
                                    throw new IOException("ExoPlayer was released during preloading.");
                                }
                                long zzz = this.zze.zzz();
                                if (zzz > 0) {
                                    long zzv = this.zze.zzv();
                                    if (zzv != this.zzk) {
                                        try {
                                            j2 = intValue;
                                            str2 = zzu;
                                        } catch (Throwable th2) {
                                            th = th2;
                                            zzcliVar = this;
                                            str = zzu;
                                        }
                                        try {
                                            zzg(this.zzf, zzu, zzv, zzz, zzv > 0, zzcliVar != 0 ? this.zze.zzA() : -1L, zzcliVar != 0 ? this.zze.zzx() : -1L, zzcliVar != 0 ? this.zze.zzB() : -1L, zzcin.zzs(), zzcin.zzu());
                                            zzcliVar = this;
                                            j = zzv;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            zzcliVar = this;
                                            str = str2;
                                            throw th;
                                        }
                                        try {
                                            zzcliVar.zzk = j;
                                            j3 = zzz;
                                            zzcliVar = zzcliVar;
                                        } catch (Throwable th4) {
                                            th = th4;
                                            str = str2;
                                            throw th;
                                        }
                                    } else {
                                        j = zzv;
                                        j2 = intValue;
                                        str2 = zzu;
                                        zzcliVar = this;
                                        j3 = zzz;
                                    }
                                    if (j >= j3) {
                                        zzcliVar.zze(zzcliVar.zzf, str2, j3);
                                        zzcliVar2 = zzcliVar;
                                    } else {
                                        int r6 = (zzcliVar.zze.zzw() > j2 ? 1 : (zzcliVar.zze.zzw() == j2 ? 0 : -1));
                                        zzcliVar3 = zzcliVar;
                                        if (r6 >= 0) {
                                            zzcliVar3 = zzcliVar;
                                            if (j > 0) {
                                                zzcliVar2 = zzcliVar;
                                            }
                                        }
                                    }
                                } else {
                                    zzcliVar3 = this;
                                }
                                zzcliVar3.zzx(((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzw)).longValue());
                                return;
                            }
                            zzcliVar2 = this;
                            com.google.android.gms.ads.internal.zzt.zzz().zzc(zzcliVar2.zzi);
                        }
                        throw new IOException("Abort requested before buffering finished. ");
                    } catch (Throwable th5) {
                        th = th5;
                        str3 = zzc;
                        str = r5;
                    }
                } else {
                    str = zzu;
                    zzcliVar = this;
                    try {
                        throw new IOException("Timeout reached. Limit: " + longValue + " ms");
                    } catch (Throwable th6) {
                        th = th6;
                        str3 = "downloadTimeout";
                    }
                }
            } catch (Throwable th7) {
                th = th7;
                str = zzu;
                zzcliVar = this;
            }
            try {
                throw th;
            } catch (Exception e2) {
                e = e2;
                String str4 = str3;
                com.google.android.gms.ads.internal.util.zze.zzj("Failed to preload url " + zzcliVar.zzf + " Exception: " + e.getMessage());
                com.google.android.gms.ads.internal.zzt.zzp().zzs(e, "VideoStreamExoPlayerCache.preload");
                release();
                zzcliVar.zzc(zzcliVar.zzf, str, str4, zzw(str4, e));
                zzcliVar2 = zzcliVar;
                com.google.android.gms.ads.internal.zzt.zzz().zzc(zzcliVar2.zzi);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcim
    public final void zzv() {
        com.google.android.gms.ads.internal.util.zze.zzj("Precache onRenderedFirstFrame");
    }
}
