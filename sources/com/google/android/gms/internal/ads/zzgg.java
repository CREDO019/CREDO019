package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgg extends zzga {
    public final zzgd zza = new zzgd();
    public ByteBuffer zzb;
    public boolean zzc;
    public long zzd;
    public ByteBuffer zze;
    private final int zzf;

    static {
        zzbh.zzb("media3.decoder");
    }

    public zzgg(int r1, int r2) {
        this.zzf = r1;
    }

    private final ByteBuffer zzl(int r3) {
        int r0 = this.zzf;
        if (r0 == 1) {
            return ByteBuffer.allocate(r3);
        }
        if (r0 == 2) {
            return ByteBuffer.allocateDirect(r3);
        }
        ByteBuffer byteBuffer = this.zzb;
        throw new zzgf(byteBuffer == null ? 0 : byteBuffer.capacity(), r3);
    }

    @Override // com.google.android.gms.internal.ads.zzga
    public void zzb() {
        super.zzb();
        ByteBuffer byteBuffer = this.zzb;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
        ByteBuffer byteBuffer2 = this.zze;
        if (byteBuffer2 != null) {
            byteBuffer2.clear();
        }
        this.zzc = false;
    }

    @EnsuresNonNull({"data"})
    public final void zzi(int r4) {
        ByteBuffer byteBuffer = this.zzb;
        if (byteBuffer == null) {
            this.zzb = zzl(r4);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = byteBuffer.position();
        int r42 = r4 + position;
        if (capacity >= r42) {
            this.zzb = byteBuffer;
            return;
        }
        ByteBuffer zzl = zzl(r42);
        zzl.order(byteBuffer.order());
        if (position > 0) {
            byteBuffer.flip();
            zzl.put(byteBuffer);
        }
        this.zzb = zzl;
    }

    public final void zzj() {
        ByteBuffer byteBuffer = this.zzb;
        if (byteBuffer != null) {
            byteBuffer.flip();
        }
        ByteBuffer byteBuffer2 = this.zze;
        if (byteBuffer2 != null) {
            byteBuffer2.flip();
        }
    }

    public final boolean zzk() {
        return zzd(1073741824);
    }
}
