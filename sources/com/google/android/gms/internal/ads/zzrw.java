package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzrw implements zzev {
    private final zzev zza;
    private final int zzb;
    private final zzrv zzc;
    private final byte[] zzd;
    private int zze;

    public zzrw(zzev zzevVar, int r4, zzrv zzrvVar) {
        zzdd.zzd(r4 > 0);
        this.zza = zzevVar;
        this.zzb = r4;
        this.zzc = zzrvVar;
        this.zzd = new byte[1];
        this.zze = r4;
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r8, int r9) throws IOException {
        int r0 = this.zze;
        if (r0 == 0) {
            int r4 = 0;
            if (this.zza.zza(this.zzd, 0, 1) != -1) {
                int r02 = (this.zzd[0] & 255) << 4;
                if (r02 != 0) {
                    byte[] bArr2 = new byte[r02];
                    int r3 = r02;
                    while (r3 > 0) {
                        int zza = this.zza.zza(bArr2, r4, r3);
                        if (zza != -1) {
                            r4 += zza;
                            r3 -= zza;
                        }
                    }
                    while (r02 > 0) {
                        int r32 = r02 - 1;
                        if (bArr2[r32] != 0) {
                            break;
                        }
                        r02 = r32;
                    }
                    if (r02 > 0) {
                        this.zzc.zza(new zzed(bArr2, r02));
                    }
                }
                r0 = this.zzb;
                this.zze = r0;
            }
            return -1;
        }
        int zza2 = this.zza.zza(bArr, r8, Math.min(r0, r9));
        if (zza2 != -1) {
            this.zze -= zza2;
        }
        return zza2;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        return this.zza.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.ads.zzev, com.google.android.gms.internal.ads.zzfs
    public final Map zze() {
        return this.zza.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzf(zzfx zzfxVar) {
        Objects.requireNonNull(zzfxVar);
        this.zza.zzf(zzfxVar);
    }
}
