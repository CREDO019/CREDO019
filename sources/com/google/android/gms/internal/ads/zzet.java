package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.Base64;
import java.io.IOException;
import java.net.URLDecoder;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzet extends zzep {
    private zzfa zza;
    private byte[] zzb;
    private int zzc;
    private int zzd;

    public zzet() {
        super(false);
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
        System.arraycopy(zzel.zzH(this.zzb), this.zzc, bArr, r4, min);
        this.zzc += min;
        this.zzd -= min;
        zzg(min);
        return min;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final long zzb(zzfa zzfaVar) throws IOException {
        zzi(zzfaVar);
        this.zza = zzfaVar;
        Uri uri = zzfaVar.zza;
        String scheme = uri.getScheme();
        zzdd.zze("data".equals(scheme), "Unsupported scheme: ".concat(String.valueOf(scheme)));
        String[] zzag = zzel.zzag(uri.getSchemeSpecificPart(), ",");
        if (zzag.length != 2) {
            throw zzbu.zzb("Unexpected URI format: ".concat(String.valueOf(String.valueOf(uri))), null);
        }
        String str = zzag[1];
        if (zzag[0].contains(";base64")) {
            try {
                this.zzb = Base64.decode(str, 0);
            } catch (IllegalArgumentException e) {
                throw zzbu.zzb("Error while parsing Base64 encoded string: ".concat(String.valueOf(str)), e);
            }
        } else {
            this.zzb = zzel.zzaa(URLDecoder.decode(str, zzfrs.zza.name()));
        }
        long j = zzfaVar.zzf;
        int length = this.zzb.length;
        if (j > length) {
            this.zzb = null;
            throw new zzew(2008);
        }
        int r1 = (int) j;
        this.zzc = r1;
        int r2 = length - r1;
        this.zzd = r2;
        long j2 = zzfaVar.zzg;
        if (j2 != -1) {
            this.zzd = (int) Math.min(r2, j2);
        }
        zzj(zzfaVar);
        long j3 = zzfaVar.zzg;
        return j3 != -1 ? j3 : this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final Uri zzc() {
        zzfa zzfaVar = this.zza;
        if (zzfaVar != null) {
            return zzfaVar.zza;
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzev
    public final void zzd() {
        if (this.zzb != null) {
            this.zzb = null;
            zzh();
        }
        this.zza = null;
    }
}
