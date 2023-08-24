package com.google.android.gms.internal.ads;

import android.net.Uri;
import java.io.IOException;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzazg implements zzazi {
    private final byte[] zza;
    private Uri zzb;
    private int zzc;
    private int zzd;

    @Override // com.google.android.gms.internal.ads.zzazi
    public final int zza(byte[] bArr, int r4, int r5) throws IOException {
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
        return min;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final long zzb(zzazk zzazkVar) throws IOException {
        this.zzb = zzazkVar.zza;
        long j = zzazkVar.zzc;
        int r2 = (int) j;
        this.zzc = r2;
        long j2 = zzazkVar.zzd;
        long j3 = -1;
        if (j2 == -1) {
            j2 = this.zza.length - j;
        } else {
            j3 = j2;
        }
        int r8 = (int) j2;
        this.zzd = r8;
        if (r8 <= 0 || r2 + r8 > this.zza.length) {
            int length = this.zza.length;
            throw new IOException("Unsatisfiable range: [" + r2 + ", " + j3 + "], length: " + length);
        }
        return r8;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final Uri zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzazi
    public final void zzd() throws IOException {
        this.zzb = null;
    }

    public zzazg(byte[] bArr) {
        Objects.requireNonNull(bArr);
        zzazy.zzc(bArr.length > 0);
        this.zza = bArr;
    }
}
