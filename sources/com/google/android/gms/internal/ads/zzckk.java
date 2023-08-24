package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzckk {
    private long zza;

    public final long zza(ByteBuffer byteBuffer) {
        zzalq zzalqVar;
        zzalp zzalpVar;
        long j = this.zza;
        if (j > 0) {
            return j;
        }
        try {
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.flip();
            Iterator it = new zzall(new zzckj(duplicate), zzckm.zzb).zze().iterator();
            while (true) {
                zzalqVar = null;
                if (!it.hasNext()) {
                    zzalpVar = null;
                    break;
                }
                zzaln zzalnVar = (zzaln) it.next();
                if (zzalnVar instanceof zzalp) {
                    zzalpVar = (zzalp) zzalnVar;
                    break;
                }
            }
            Iterator it2 = zzalpVar.zze().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                zzaln zzalnVar2 = (zzaln) it2.next();
                if (zzalnVar2 instanceof zzalq) {
                    zzalqVar = (zzalq) zzalnVar2;
                    break;
                }
            }
            long zzd = (zzalqVar.zzd() * 1000) / zzalqVar.zze();
            this.zza = zzd;
            return zzd;
        } catch (IOException | RuntimeException unused) {
            return 0L;
        }
    }
}
