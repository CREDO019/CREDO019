package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzoc extends zzob {
    private int[] zzd;
    private int[] zze;

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zze(ByteBuffer byteBuffer) {
        int[] r0 = this.zze;
        Objects.requireNonNull(r0);
        int[] r02 = r0;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        ByteBuffer zzj = zzj(((limit - position) / this.zzb.zze) * this.zzc.zze);
        while (position < limit) {
            for (int r6 : r02) {
                zzj.putShort(byteBuffer.getShort(r6 + r6 + position));
            }
            position += this.zzb.zze;
        }
        byteBuffer.position(limit);
        zzj.flip();
    }

    @Override // com.google.android.gms.internal.ads.zzob
    public final zznc zzi(zznc zzncVar) throws zznd {
        int[] r0 = this.zzd;
        if (r0 == null) {
            return zznc.zza;
        }
        if (zzncVar.zzd != 2) {
            throw new zznd(zzncVar);
        }
        boolean z = zzncVar.zzc != r0.length;
        int r3 = 0;
        while (true) {
            int length = r0.length;
            if (r3 >= length) {
                return z ? new zznc(zzncVar.zzb, length, 2) : zznc.zza;
            }
            int r6 = r0[r3];
            if (r6 >= zzncVar.zzc) {
                throw new zznd(zzncVar);
            }
            z |= r6 != r3;
            r3++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzk() {
        this.zze = this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzm() {
        this.zze = null;
        this.zzd = null;
    }

    public final void zzo(int[] r1) {
        this.zzd = r1;
    }
}
