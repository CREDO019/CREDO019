package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaia {
    private final List zza;
    private final zzaam[] zzb;

    public zzaia(List list) {
        this.zza = list;
        this.zzb = new zzaam[list.size()];
    }

    public final void zza(long j, zzed zzedVar) {
        zzys.zza(j, zzedVar, this.zzb);
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
            String str2 = zzafVar.zzb;
            if (str2 == null) {
                str2 = zzailVar.zzb();
            }
            zzad zzadVar = new zzad();
            zzadVar.zzH(str2);
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
