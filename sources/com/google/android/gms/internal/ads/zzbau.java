package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbau {
    public final List zza;
    public final int zzb;

    private zzbau(List list, int r2) {
        this.zza = list;
        this.zzb = r2;
    }

    public static zzbau zza(zzbag zzbagVar) throws zzasv {
        try {
            zzbagVar.zzw(21);
            int zzg = zzbagVar.zzg() & 3;
            int zzg2 = zzbagVar.zzg();
            int zzc = zzbagVar.zzc();
            int r5 = 0;
            for (int r4 = 0; r4 < zzg2; r4++) {
                zzbagVar.zzw(1);
                int zzj = zzbagVar.zzj();
                for (int r7 = 0; r7 < zzj; r7++) {
                    int zzj2 = zzbagVar.zzj();
                    r5 += zzj2 + 4;
                    zzbagVar.zzw(zzj2);
                }
            }
            zzbagVar.zzv(zzc);
            byte[] bArr = new byte[r5];
            int r72 = 0;
            for (int r42 = 0; r42 < zzg2; r42++) {
                zzbagVar.zzw(1);
                int zzj3 = zzbagVar.zzj();
                for (int r9 = 0; r9 < zzj3; r9++) {
                    int zzj4 = zzbagVar.zzj();
                    System.arraycopy(zzbae.zza, 0, bArr, r72, 4);
                    int r73 = r72 + 4;
                    System.arraycopy(zzbagVar.zza, zzbagVar.zzc(), bArr, r73, zzj4);
                    r72 = r73 + zzj4;
                    zzbagVar.zzw(zzj4);
                }
            }
            return new zzbau(r5 == 0 ? null : Collections.singletonList(bArr), zzg + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new zzasv("Error parsing HEVC config", e);
        }
    }
}
