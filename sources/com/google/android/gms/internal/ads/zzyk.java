package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyk {
    public final List zza;
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final float zze;
    public final String zzf;

    private zzyk(List list, int r2, int r3, int r4, float f, String str) {
        this.zza = list;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = f;
        this.zzf = str;
    }

    public static zzyk zza(zzed zzedVar) throws zzbu {
        String str;
        int r5;
        int r6;
        float f;
        try {
            zzedVar.zzG(4);
            int zzk = (zzedVar.zzk() & 3) + 1;
            if (zzk == 3) {
                throw new IllegalStateException();
            }
            ArrayList arrayList = new ArrayList();
            int zzk2 = zzedVar.zzk() & 31;
            for (int r2 = 0; r2 < zzk2; r2++) {
                arrayList.add(zzb(zzedVar));
            }
            int zzk3 = zzedVar.zzk();
            for (int r52 = 0; r52 < zzk3; r52++) {
                arrayList.add(zzb(zzedVar));
            }
            if (zzk2 > 0) {
                zzaab zzd = zzaac.zzd((byte[]) arrayList.get(0), zzk + 1, ((byte[]) arrayList.get(0)).length);
                int r0 = zzd.zze;
                int r1 = zzd.zzf;
                float f2 = zzd.zzg;
                str = zzdf.zza(zzd.zza, zzd.zzb, zzd.zzc);
                r5 = r0;
                r6 = r1;
                f = f2;
            } else {
                str = null;
                r5 = -1;
                r6 = -1;
                f = 1.0f;
            }
            return new zzyk(arrayList, zzk, r5, r6, f, str);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw zzbu.zza("Error parsing AVC config", e);
        }
    }

    private static byte[] zzb(zzed zzedVar) {
        int zzo = zzedVar.zzo();
        int zzc = zzedVar.zzc();
        zzedVar.zzG(zzo);
        return zzdf.zzc(zzedVar.zzH(), zzc, zzo);
    }
}
