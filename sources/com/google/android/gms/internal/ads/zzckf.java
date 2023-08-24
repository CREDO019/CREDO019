package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzckf implements zzazi {
    private final zzazi zza;
    private final long zzb;
    private final zzazi zzc;
    private long zzd;
    private Uri zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzckf(zzazi zzaziVar, int r2, zzazi zzaziVar2) {
        this.zza = zzaziVar;
        this.zzb = r2;
        this.zzc = zzaziVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final int zza(byte[] bArr, int r10, int r11) throws IOException {
        int r2;
        long j = this.zzd;
        long j2 = this.zzb;
        if (j < j2) {
            int zza = this.zza.zza(bArr, r10, (int) Math.min(r11, j2 - j));
            long j3 = this.zzd + zza;
            this.zzd = j3;
            r2 = zza;
            j = j3;
        } else {
            r2 = 0;
        }
        if (j >= this.zzb) {
            int zza2 = this.zzc.zza(bArr, r10 + r2, r11 - r2);
            this.zzd += zza2;
            return r2 + zza2;
        }
        return r2;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final Uri zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final void zzd() throws IOException {
        this.zza.zzd();
        this.zzc.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final long zzb(zzazk zzazkVar) throws IOException {
        zzazk zzazkVar2;
        this.zze = zzazkVar.zza;
        long j = zzazkVar.zzc;
        long j2 = this.zzb;
        zzazk zzazkVar3 = null;
        if (j >= j2) {
            zzazkVar2 = null;
        } else {
            long j3 = zzazkVar.zzd;
            zzazkVar2 = new zzazk(zzazkVar.zza, null, j, j, j3 != -1 ? Math.min(j3, j2 - j) : j2 - j, null, 0);
        }
        long j4 = zzazkVar.zzd;
        if (j4 == -1 || zzazkVar.zzc + j4 > this.zzb) {
            long max = Math.max(this.zzb, zzazkVar.zzc);
            long j5 = zzazkVar.zzd;
            zzazkVar3 = new zzazk(zzazkVar.zza, null, max, max, j5 != -1 ? Math.min(j5, (zzazkVar.zzc + j5) - this.zzb) : -1L, null, 0);
        }
        long zzb = zzazkVar2 != null ? this.zza.zzb(zzazkVar2) : 0L;
        long zzb2 = zzazkVar3 != null ? this.zzc.zzb(zzazkVar3) : 0L;
        this.zzd = zzazkVar.zzc;
        if (zzb2 == -1) {
            return -1L;
        }
        return zzb + zzb2;
    }
}
