package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeq extends zzep {
    private final byte[] zza;
    private Uri zzb;
    private int zzc;
    private int zzd;
    private boolean zze;

    public zzeq(byte[] bArr) {
        super(false);
        Objects.requireNonNull(bArr);
        zzdd.zzd(bArr.length > 0);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.ads.zzr
    public final int zza(byte[] bArr, int r4, int r5) {
        if (r5 == 0) {
            return 0;
        }
        int r0 = this.zzd;
        if (r0 == 0) {
            return -1;
        }
        int min = Math.min(r5, r0);
        System.arraycopy(this.zza, this.zzc, bArr, r4, min);
        this.zzc += min;
        this.zzd -= min;
        zzg(min);
        return min;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) throws IOException {
        this.zzb = zzfaVar.zza;
        zzi(zzfaVar);
        long j = zzfaVar.zzf;
        int length = this.zza.length;
        if (j <= length) {
            int r1 = (int) j;
            this.zzc = r1;
            int r2 = length - r1;
            this.zzd = r2;
            long j2 = zzfaVar.zzg;
            if (j2 != -1) {
                this.zzd = (int) Math.min(r2, j2);
            }
            this.zze = true;
            zzj(zzfaVar);
            long j3 = zzfaVar.zzg;
            return j3 != -1 ? j3 : this.zzd;
        }
        throw new zzew(2008);
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() {
        if (this.zze) {
            this.zze = false;
            zzh();
        }
        this.zzb = null;
    }
}
