package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzclo implements zzev {
    private final zzev zza;
    private final long zzb;
    private final zzev zzc;
    private long zzd;
    private Uri zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzclo(zzev zzevVar, int r2, zzev zzevVar2) {
        this.zza = zzevVar;
        this.zzb = r2;
        this.zzc = zzevVar2;
    }

    @Override // com.google.android.gms.internal.ads.zzr
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

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) throws IOException {
        zzfa zzfaVar2;
        this.zze = zzfaVar.zza;
        long j = zzfaVar.zzf;
        long j2 = this.zzb;
        zzfa zzfaVar3 = null;
        if (j >= j2) {
            zzfaVar2 = null;
        } else {
            long j3 = zzfaVar.zzg;
            zzfaVar2 = new zzfa(zzfaVar.zza, null, j, j, j3 != -1 ? Math.min(j3, j2 - j) : j2 - j, null, 0);
        }
        long j4 = zzfaVar.zzg;
        if (j4 == -1 || zzfaVar.zzf + j4 > this.zzb) {
            long max = Math.max(this.zzb, zzfaVar.zzf);
            long j5 = zzfaVar.zzg;
            zzfaVar3 = new zzfa(zzfaVar.zza, null, max, max, j5 != -1 ? Math.min(j5, (zzfaVar.zzf + j5) - this.zzb) : -1L, null, 0);
        }
        long zzb = zzfaVar2 != null ? this.zza.zzb(zzfaVar2) : 0L;
        long zzb2 = zzfaVar3 != null ? this.zzc.zzb(zzfaVar3) : 0L;
        this.zzd = zzfaVar.zzf;
        if (zzb == -1 || zzb2 == -1) {
            return -1L;
        }
        return zzb + zzb2;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() throws IOException {
        this.zza.zzd();
        this.zzc.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzev, com.google.android.gms.internal.ads.zzfs
    public final Map zze() {
        return zzfuy.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzf(zzfx zzfxVar) {
    }
}
