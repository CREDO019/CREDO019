package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaun extends zzaui {
    public final zzaul zza = new zzaul();
    public ByteBuffer zzb;
    public long zzc;

    public zzaun(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzaui
    public final void zzb() {
        super.zzb();
        ByteBuffer byteBuffer = this.zzb;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    public final void zzh(int r4) throws IllegalStateException {
        ByteBuffer byteBuffer = this.zzb;
        if (byteBuffer == null) {
            this.zzb = zzj(r4);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = this.zzb.position();
        int r42 = r4 + position;
        if (capacity >= r42) {
            return;
        }
        ByteBuffer zzj = zzj(r42);
        if (position > 0) {
            this.zzb.position(0);
            this.zzb.limit(position);
            zzj.put(this.zzb);
        }
        this.zzb = zzj;
    }

    public final boolean zzi() {
        return zzd(1073741824);
    }

    private final ByteBuffer zzj(int r5) {
        ByteBuffer byteBuffer = this.zzb;
        int capacity = byteBuffer == null ? 0 : byteBuffer.capacity();
        throw new IllegalStateException("Buffer too small (" + capacity + " < " + r5 + ")");
    }
}
