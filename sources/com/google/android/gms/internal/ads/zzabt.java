package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzabt implements zzzf {
    private zzzi zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private zzadq zzg;
    private zzzg zzh;
    private zzabw zzi;
    private zzafn zzj;
    private final zzed zza = new zzed(6);
    private long zzf = -1;

    private final int zze(zzzg zzzgVar) throws IOException {
        this.zza.zzC(2);
        ((zzyv) zzzgVar).zzm(this.zza.zzH(), 0, 2, false);
        return this.zza.zzo();
    }

    private final void zzf() {
        zzg(new zzbp[0]);
        zzzi zzziVar = this.zzb;
        Objects.requireNonNull(zzziVar);
        zzziVar.zzB();
        this.zzb.zzL(new zzaah(C1856C.TIME_UNSET, 0L));
        this.zzc = 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x015e  */
    @Override // com.google.android.gms.internal.ads.zzzf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(com.google.android.gms.internal.ads.zzzg r24, com.google.android.gms.internal.ads.zzaaf r25) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 459
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzabt.zza(com.google.android.gms.internal.ads.zzzg, com.google.android.gms.internal.ads.zzaaf):int");
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzb = zzziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        if (zze(zzzgVar) != 65496) {
            return false;
        }
        int zze = zze(zzzgVar);
        this.zzd = zze;
        if (zze == 65504) {
            this.zza.zzC(2);
            zzyv zzyvVar = (zzyv) zzzgVar;
            zzyvVar.zzm(this.zza.zzH(), 0, 2, false);
            zzyvVar.zzl(this.zza.zzo() - 2, false);
            zze = zze(zzzgVar);
            this.zzd = zze;
        }
        if (zze == 65505) {
            zzyv zzyvVar2 = (zzyv) zzzgVar;
            zzyvVar2.zzl(2, false);
            this.zza.zzC(6);
            zzyvVar2.zzm(this.zza.zzH(), 0, 6, false);
            if (this.zza.zzs() == 1165519206 && this.zza.zzo() == 0) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        if (j == 0) {
            this.zzc = 0;
            this.zzj = null;
        } else if (this.zzc == 5) {
            zzafn zzafnVar = this.zzj;
            Objects.requireNonNull(zzafnVar);
            zzafnVar.zzc(j, j2);
        }
    }

    private final void zzg(zzbp... zzbpVarArr) {
        zzzi zzziVar = this.zzb;
        Objects.requireNonNull(zzziVar);
        zzaam zzv = zzziVar.zzv(1024, 4);
        zzad zzadVar = new zzad();
        zzadVar.zzz(MimeTypes.IMAGE_JPEG);
        zzadVar.zzM(new zzbq(zzbpVarArr));
        zzv.zzk(zzadVar.zzY());
    }
}
