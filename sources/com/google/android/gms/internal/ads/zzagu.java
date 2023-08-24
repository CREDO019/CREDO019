package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzagu implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzagt
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzagu.zza;
            return new zzzf[]{new zzagu(0)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private final zzagv zzb;
    private final zzed zzc;
    private final zzed zzd;
    private final zzec zze;
    private zzzi zzf;
    private long zzg;
    private long zzh;
    private boolean zzi;
    private boolean zzj;

    public zzagu() {
        this(0);
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        zzdd.zzb(this.zzf);
        int zza2 = zzzgVar.zza(this.zzc.zzH(), 0, 2048);
        if (!this.zzj) {
            this.zzf.zzL(new zzaah(C1856C.TIME_UNSET, 0L));
            this.zzj = true;
        }
        if (zza2 == -1) {
            return -1;
        }
        this.zzc.zzF(0);
        this.zzc.zzE(zza2);
        if (!this.zzi) {
            this.zzb.zzd(this.zzg, 4);
            this.zzi = true;
        }
        this.zzb.zza(this.zzc);
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzf = zzziVar;
        this.zzb.zzb(zzziVar, new zzail(Integer.MIN_VALUE, 0, 1));
        zzziVar.zzB();
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        this.zzi = false;
        this.zzb.zze();
        this.zzg = j2;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        zzyv zzyvVar;
        int r1 = 0;
        while (true) {
            zzyvVar = (zzyv) zzzgVar;
            zzyvVar.zzm(this.zzd.zzH(), 0, 10, false);
            this.zzd.zzF(0);
            if (this.zzd.zzm() != 4801587) {
                break;
            }
            this.zzd.zzG(3);
            int zzj = this.zzd.zzj();
            r1 += zzj + 10;
            zzyvVar.zzl(zzj, false);
        }
        zzzgVar.zzj();
        zzyvVar.zzl(r1, false);
        if (this.zzh == -1) {
            this.zzh = r1;
        }
        int r4 = r1;
        int r2 = 0;
        int r5 = 0;
        do {
            zzyvVar.zzm(this.zzd.zzH(), 0, 2, false);
            this.zzd.zzF(0);
            if (zzagv.zzf(this.zzd.zzo())) {
                r2++;
                if (r2 >= 4 && r5 > 188) {
                    return true;
                }
                zzyvVar.zzm(this.zzd.zzH(), 0, 4, false);
                this.zze.zzh(14);
                int zzc = this.zze.zzc(13);
                if (zzc <= 6) {
                    r4++;
                    zzzgVar.zzj();
                    zzyvVar.zzl(r4, false);
                } else {
                    zzyvVar.zzl(zzc - 6, false);
                    r5 += zzc;
                }
            } else {
                r4++;
                zzzgVar.zzj();
                zzyvVar.zzl(r4, false);
            }
            r2 = 0;
            r5 = 0;
        } while (r4 - r1 < 8192);
        return false;
    }

    public zzagu(int r3) {
        this.zzb = new zzagv(true, null);
        this.zzc = new zzed(2048);
        this.zzh = -1L;
        zzed zzedVar = new zzed(10);
        this.zzd = zzedVar;
        byte[] zzH = zzedVar.zzH();
        this.zze = new zzec(zzH, zzH.length);
    }
}
