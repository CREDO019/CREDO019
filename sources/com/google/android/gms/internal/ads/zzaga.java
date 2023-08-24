package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaga extends zzagk {
    private zzzs zza;
    private zzafz zzb;

    private static boolean zzd(byte[] bArr) {
        return bArr[0] == -1;
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    protected final long zza(zzed zzedVar) {
        if (zzd(zzedVar.zzH())) {
            int r0 = (zzedVar.zzH()[2] & 255) >> 4;
            if (r0 != 6) {
                if (r0 == 7) {
                    r0 = 7;
                }
                int zza = zzzo.zza(zzedVar, r0);
                zzedVar.zzF(0);
                return zza;
            }
            zzedVar.zzG(4);
            zzedVar.zzu();
            int zza2 = zzzo.zza(zzedVar, r0);
            zzedVar.zzF(0);
            return zza2;
        }
        return -1L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzagk
    public final void zzb(boolean z) {
        super.zzb(z);
        if (z) {
            this.zza = null;
            this.zzb = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagk
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    protected final boolean zzc(zzed zzedVar, long j, zzagh zzaghVar) {
        byte[] zzH = zzedVar.zzH();
        zzzs zzzsVar = this.zza;
        if (zzzsVar == null) {
            zzzs zzzsVar2 = new zzzs(zzH, 17);
            this.zza = zzzsVar2;
            zzaghVar.zza = zzzsVar2.zzc(Arrays.copyOfRange(zzH, 9, zzedVar.zzd()), null);
            return true;
        } else if ((zzH[0] & Byte.MAX_VALUE) == 3) {
            zzzr zzb = zzzp.zzb(zzedVar);
            zzzs zzf = zzzsVar.zzf(zzb);
            this.zza = zzf;
            this.zzb = new zzafz(zzf, zzb);
            return true;
        } else if (zzd(zzH)) {
            zzafz zzafzVar = this.zzb;
            if (zzafzVar != null) {
                zzafzVar.zza(j);
                zzaghVar.zzb = this.zzb;
            }
            Objects.requireNonNull(zzaghVar.zza);
            return false;
        } else {
            return true;
        }
    }
}
