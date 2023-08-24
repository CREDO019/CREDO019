package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzclf extends zzckz implements zzazx {
    private String zzd;
    private final zzciv zze;
    private boolean zzf;
    private final zzcle zzg;
    private final zzckk zzh;
    private ByteBuffer zzi;
    private boolean zzj;
    private final Object zzk;
    private final String zzl;
    private final int zzm;
    private boolean zzn;

    public zzclf(zzciw zzciwVar, zzciv zzcivVar) {
        super(zzciwVar);
        this.zze = zzcivVar;
        this.zzg = new zzcle();
        this.zzh = new zzckk();
        this.zzk = new Object();
        this.zzl = zzciwVar != null ? zzciwVar.zzt() : "";
        this.zzm = zzciwVar != null ? zzciwVar.zzh() : 0;
    }

    protected static final String zzt(String str) {
        return "cache:".concat(String.valueOf(zzcgg.zze(str)));
    }

    private final void zzu() {
        int zza = (int) this.zzg.zza();
        int zza2 = (int) this.zzh.zza(this.zzi);
        int position = this.zzi.position();
        int round = Math.round(zza2 * (position / zza));
        boolean z = round > 0;
        int zzs = zzcin.zzs();
        int zzu = zzcin.zzu();
        String str = this.zzd;
        zzf(str, zzt(str), position, zza, round, zza2, z, zzs, zzu);
    }

    @Override // com.google.android.gms.internal.ads.zzckz
    public final void zzb() {
        this.zzf = true;
    }

    public final String zzi() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzazx
    public final /* bridge */ /* synthetic */ void zzj(Object obj, int r2) {
    }

    @Override // com.google.android.gms.internal.ads.zzazx
    public final /* bridge */ /* synthetic */ void zzk(Object obj, zzazk zzazkVar) {
        this.zzg.zzb((zzazm) obj);
    }

    public final ByteBuffer zzl() {
        synchronized (this.zzk) {
            ByteBuffer byteBuffer = this.zzi;
            if (byteBuffer != null && !this.zzj) {
                byteBuffer.flip();
                this.zzj = true;
            }
            this.zzf = true;
        }
        return this.zzi;
    }

    public final boolean zzm() {
        return this.zzn;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:? -> B:44:0x0140). Please submit an issue!!! */
    @Override // com.google.android.gms.internal.ads.zzckz
    public final boolean zzq(String str) {
        String str2;
        this.zzd = str;
        String zzt = zzt(str);
        String str3 = "error";
        try {
            String str4 = this.zzb;
            zzciv zzcivVar = this.zze;
            zzazi zzazmVar = new zzazm(str4, null, this, zzcivVar.zzd, zzcivVar.zzf, true, null);
            if (this.zze.zzj) {
                zzazmVar = new zzcjs(this.zza, zzazmVar, this.zzl, this.zzm, null, null, null);
            }
            zzazmVar.zzb(new zzazk(Uri.parse(str), null, 0L, 0L, -1L, null, 0));
            zzciw zzciwVar = (zzciw) this.zzc.get();
            if (zzciwVar != null) {
                zzciwVar.zzv(zzt, this);
            }
            Clock zzB = com.google.android.gms.ads.internal.zzt.zzB();
            long currentTimeMillis = zzB.currentTimeMillis();
            long longValue = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzw)).longValue();
            long longValue2 = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzv)).longValue();
            this.zzi = ByteBuffer.allocate(this.zze.zzc);
            int r8 = 8192;
            byte[] bArr = new byte[8192];
            long j = currentTimeMillis;
            while (true) {
                int zza = zzazmVar.zza(bArr, 0, Math.min(this.zzi.remaining(), r8));
                zzazi zzaziVar = zzazmVar;
                if (zza != -1) {
                    synchronized (this.zzk) {
                        try {
                            if (this.zzf) {
                                str2 = str3;
                            } else {
                                str2 = str3;
                                str3 = null;
                                try {
                                    this.zzi.put(bArr, 0, zza);
                                } catch (Throwable th) {
                                    th = th;
                                    throw th;
                                }
                            }
                            try {
                                if (this.zzi.remaining() <= 0) {
                                    zzu();
                                    return true;
                                }
                                try {
                                    if (!this.zzf) {
                                        long currentTimeMillis2 = zzB.currentTimeMillis();
                                        if (currentTimeMillis2 - j >= longValue) {
                                            zzu();
                                            j = currentTimeMillis2;
                                        }
                                        if (currentTimeMillis2 - currentTimeMillis > 1000 * longValue2) {
                                            throw new IOException("Timeout exceeded. Limit: " + longValue2 + " sec");
                                        }
                                        zzazmVar = zzaziVar;
                                        str3 = str2;
                                        r8 = 8192;
                                    } else {
                                        throw new IOException("Precache abort at " + this.zzi.limit() + " bytes");
                                    }
                                } catch (Exception e) {
                                    e = e;
                                    String str5 = e.getClass().getCanonicalName() + ParameterizedMessage.ERROR_MSG_SEPARATOR + e.getMessage();
                                    com.google.android.gms.ads.internal.util.zze.zzj("Failed to preload url " + str + " Exception: " + str5);
                                    zzc(str, zzt, str3, str5);
                                    return false;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                str3 = str2;
                                String str52 = e.getClass().getCanonicalName() + ParameterizedMessage.ERROR_MSG_SEPARATOR + e.getMessage();
                                com.google.android.gms.ads.internal.util.zze.zzj("Failed to preload url " + str + " Exception: " + str52);
                                zzc(str, zzt, str3, str52);
                                return false;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                } else {
                    this.zzn = true;
                    zze(str, zzt, (int) this.zzh.zza(this.zzi));
                    return true;
                }
            }
        } catch (Exception e3) {
            e = e3;
            str2 = str3;
        }
    }
}
