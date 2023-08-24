package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaio {
    private final List zza;
    private final zzaam[] zzb;

    public zzaio(List list) {
        this.zza = list;
        this.zzb = new zzaam[list.size()];
    }

    public final void zza(long j, zzed zzedVar) {
        if (zzedVar.zza() < 9) {
            return;
        }
        int zze = zzedVar.zze();
        int zze2 = zzedVar.zze();
        int zzk = zzedVar.zzk();
        if (zze == 434 && zze2 == 1195456820 && zzk == 3) {
            zzys.zzb(j, zzedVar, this.zzb);
        }
    }

    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        for (int r1 = 0; r1 < this.zzb.length; r1++) {
            zzailVar.zzc();
            zzaam zzv = zzziVar.zzv(zzailVar.zza(), 3);
            zzaf zzafVar = (zzaf) this.zza.get(r1);
            String str = zzafVar.zzm;
            boolean z = true;
            if (!MimeTypes.APPLICATION_CEA608.equals(str) && !MimeTypes.APPLICATION_CEA708.equals(str)) {
                z = false;
            }
            zzdd.zze(z, "Invalid closed caption mime type provided: ".concat(String.valueOf(str)));
            zzad zzadVar = new zzad();
            zzadVar.zzH(zzailVar.zzb());
            zzadVar.zzS(str);
            zzadVar.zzU(zzafVar.zze);
            zzadVar.zzK(zzafVar.zzd);
            zzadVar.zzu(zzafVar.zzE);
            zzadVar.zzI(zzafVar.zzo);
            zzv.zzk(zzadVar.zzY());
            this.zzb[r1] = zzv;
        }
    }
}
