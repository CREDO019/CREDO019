package com.google.android.gms.internal.ads;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzqe {
    private long zza;
    private long zzb;
    private boolean zzc;

    private final long zzd(long j) {
        return this.zza + Math.max(0L, ((this.zzb - 529) * 1000000) / j);
    }

    public final long zza(zzaf zzafVar) {
        return zzd(zzafVar.zzA);
    }

    public final long zzb(zzaf zzafVar, zzgg zzggVar) {
        if (this.zzb == 0) {
            this.zza = zzggVar.zzd;
        }
        if (this.zzc) {
            return zzggVar.zzd;
        }
        ByteBuffer byteBuffer = zzggVar.zzb;
        Objects.requireNonNull(byteBuffer);
        int r4 = 0;
        for (int r1 = 0; r1 < 4; r1++) {
            r4 = (r4 << 8) | (byteBuffer.get(r1) & 255);
        }
        int zzc = zzzz.zzc(r4);
        if (zzc == -1) {
            this.zzc = true;
            this.zzb = 0L;
            this.zza = zzggVar.zzd;
            Log.w("C2Mp3TimestampTracker", "MPEG audio header is invalid.");
            return zzggVar.zzd;
        }
        long zzd = zzd(zzafVar.zzA);
        this.zzb += zzc;
        return zzd;
    }

    public final void zzc() {
        this.zza = 0L;
        this.zzb = 0L;
        this.zzc = false;
    }
}
