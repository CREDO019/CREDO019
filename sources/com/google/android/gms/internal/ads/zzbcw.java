package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbcw extends zzbcn {
    private MessageDigest zzb;
    private final int zzc;
    private final int zzd;

    public zzbcw(int r3) {
        int r0 = r3 >> 3;
        this.zzc = (r3 & 7) > 0 ? r0 + 1 : r0;
        this.zzd = r3;
    }

    @Override // com.google.android.gms.internal.ads.zzbcn
    public final byte[] zzb(String str) {
        synchronized (this.zza) {
            MessageDigest zza = zza();
            this.zzb = zza;
            if (zza == null) {
                return new byte[0];
            }
            zza.reset();
            this.zzb.update(str.getBytes(Charset.forName("UTF-8")));
            byte[] digest = this.zzb.digest();
            int length = digest.length;
            int r3 = this.zzc;
            if (length > r3) {
                length = r3;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(digest, 0, bArr, 0, length);
            if ((this.zzd & 7) > 0) {
                long j = 0;
                for (int r2 = 0; r2 < length; r2++) {
                    if (r2 > 0) {
                        j <<= 8;
                    }
                    j += bArr[r2] & 255;
                }
                long j2 = j >>> (8 - (this.zzd & 7));
                int r4 = this.zzc;
                while (true) {
                    r4--;
                    if (r4 < 0) {
                        break;
                    }
                    bArr[r4] = (byte) (255 & j2);
                    j2 >>>= 8;
                }
            }
            return bArr;
        }
    }
}
