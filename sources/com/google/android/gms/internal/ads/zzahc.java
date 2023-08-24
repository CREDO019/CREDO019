package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzahc {
    private static final byte[] zzd = {0, 0, 1};
    public int zza;
    public int zzb;
    public byte[] zzc = new byte[128];
    private boolean zze;
    private int zzf;

    public zzahc(int r1) {
    }

    public final void zza(byte[] bArr, int r5, int r6) {
        if (this.zze) {
            int r62 = r6 - r5;
            byte[] bArr2 = this.zzc;
            int length = bArr2.length;
            int r2 = this.zza + r62;
            if (length < r2) {
                this.zzc = Arrays.copyOf(bArr2, r2 + r2);
            }
            System.arraycopy(bArr, r5, this.zzc, this.zza, r62);
            this.zza += r62;
        }
    }

    public final void zzb() {
        this.zze = false;
        this.zza = 0;
        this.zzf = 0;
    }

    public final boolean zzc(int r9, int r10) {
        int r0 = this.zzf;
        if (r0 != 0) {
            if (r0 != 1) {
                if (r0 != 2) {
                    if (r0 != 3) {
                        if (r9 == 179 || r9 == 181) {
                            this.zza -= r10;
                            this.zze = false;
                            return true;
                        }
                    } else if ((r9 & PsExtractor.VIDEO_STREAM_MASK) != 32) {
                        Log.w("H263Reader", "Unexpected start code value");
                        zzb();
                    } else {
                        this.zzb = this.zza;
                        this.zzf = 4;
                    }
                } else if (r9 > 31) {
                    Log.w("H263Reader", "Unexpected start code value");
                    zzb();
                } else {
                    this.zzf = 3;
                }
            } else if (r9 != 181) {
                Log.w("H263Reader", "Unexpected start code value");
                zzb();
            } else {
                this.zzf = 2;
            }
        } else if (r9 == 176) {
            this.zzf = 1;
            this.zze = true;
        }
        zza(zzd, 0, 3);
        return false;
    }
}
