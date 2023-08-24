package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzw {
    private final zzed zza = new zzed(10);

    public final zzbq zza(zzzg zzzgVar, zzacx zzacxVar) throws IOException {
        zzbq zzbqVar = null;
        int r2 = 0;
        while (true) {
            try {
                ((zzyv) zzzgVar).zzm(this.zza.zzH(), 0, 10, false);
                this.zza.zzF(0);
                if (this.zza.zzm() != 4801587) {
                    break;
                }
                this.zza.zzG(3);
                int zzj = this.zza.zzj();
                int r4 = zzj + 10;
                if (zzbqVar == null) {
                    byte[] bArr = new byte[r4];
                    System.arraycopy(this.zza.zzH(), 0, bArr, 0, 10);
                    ((zzyv) zzzgVar).zzm(bArr, 10, zzj, false);
                    zzbqVar = zzacz.zza(bArr, r4, zzacxVar, new zzaca());
                } else {
                    ((zzyv) zzzgVar).zzl(zzj, false);
                }
                r2 += r4;
            } catch (EOFException unused) {
            }
        }
        zzzgVar.zzj();
        ((zzyv) zzzgVar).zzl(r2, false);
        return zzbqVar;
    }
}
