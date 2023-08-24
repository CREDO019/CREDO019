package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabi implements zzyq {
    private final zzzs zza;
    private final int zzb;
    private final zzzn zzc = new zzzn();

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzabi(zzzs zzzsVar, int r2, zzabh zzabhVar) {
        this.zza = zzzsVar;
        this.zzb = r2;
    }

    private final long zzc(zzzg zzzgVar) throws IOException {
        while (zzzgVar.zze() < zzzgVar.zzd() - 6) {
            zzzs zzzsVar = this.zza;
            int r3 = this.zzb;
            zzzn zzznVar = this.zzc;
            long zze = zzzgVar.zze();
            byte[] bArr = new byte[2];
            zzyv zzyvVar = (zzyv) zzzgVar;
            zzyvVar.zzm(bArr, 0, 2, false);
            if ((((bArr[0] & 255) << 8) | (bArr[1] & 255)) != r3) {
                zzzgVar.zzj();
                zzyvVar.zzl((int) (zze - zzzgVar.zzf()), false);
            } else {
                zzed zzedVar = new zzed(16);
                System.arraycopy(bArr, 0, zzedVar.zzH(), 0, 2);
                zzedVar.zzE(zzzj.zza(zzzgVar, zzedVar.zzH(), 2, 14));
                zzzgVar.zzj();
                zzyvVar.zzl((int) (zze - zzzgVar.zzf()), false);
                if (zzzo.zzc(zzedVar, zzzsVar, r3, zzznVar)) {
                    break;
                }
            }
            zzyvVar.zzl(1, false);
        }
        if (zzzgVar.zze() >= zzzgVar.zzd() - 6) {
            ((zzyv) zzzgVar).zzl((int) (zzzgVar.zzd() - zzzgVar.zze()), false);
            return this.zza.zzj;
        }
        return this.zzc.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzyq
    public final zzyp zza(zzzg zzzgVar, long j) throws IOException {
        long zzf = zzzgVar.zzf();
        long zzc = zzc(zzzgVar);
        long zze = zzzgVar.zze();
        ((zzyv) zzzgVar).zzl(Math.max(6, this.zza.zzc), false);
        long zzc2 = zzc(zzzgVar);
        return (zzc > j || zzc2 <= j) ? zzc2 <= j ? zzyp.zzf(zzc2, zzzgVar.zze()) : zzyp.zzd(zzc, zzf) : zzyp.zze(zze);
    }

    @Override // com.google.android.gms.internal.ads.zzyq
    public final /* synthetic */ void zzb() {
    }
}
