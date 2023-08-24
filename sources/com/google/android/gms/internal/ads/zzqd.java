package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzqd extends zzgg {
    private long zzf;
    private int zzg;
    private int zzh;

    public zzqd() {
        super(2, 0);
        this.zzh = 32;
    }

    @Override // com.google.android.gms.internal.ads.zzgg, com.google.android.gms.internal.ads.zzga
    public final void zzb() {
        super.zzb();
        this.zzg = 0;
    }

    public final int zzl() {
        return this.zzg;
    }

    public final long zzm() {
        return this.zzf;
    }

    public final void zzn(int r1) {
        this.zzh = r1;
    }

    public final boolean zzo(zzgg zzggVar) {
        ByteBuffer byteBuffer;
        zzdd.zzd(!zzggVar.zzd(1073741824));
        zzdd.zzd(!zzggVar.zzd(268435456));
        zzdd.zzd(!zzggVar.zzd(4));
        if (zzp()) {
            if (this.zzg >= this.zzh || zzggVar.zzd(Integer.MIN_VALUE) != zzd(Integer.MIN_VALUE)) {
                return false;
            }
            ByteBuffer byteBuffer2 = zzggVar.zzb;
            if (byteBuffer2 != null && (byteBuffer = this.zzb) != null && byteBuffer.position() + byteBuffer2.remaining() > 3072000) {
                return false;
            }
        }
        int r0 = this.zzg;
        this.zzg = r0 + 1;
        if (r0 == 0) {
            this.zzd = zzggVar.zzd;
            if (zzggVar.zzd(1)) {
                zzc(1);
            }
        }
        if (zzggVar.zzd(Integer.MIN_VALUE)) {
            zzc(Integer.MIN_VALUE);
        }
        ByteBuffer byteBuffer3 = zzggVar.zzb;
        if (byteBuffer3 != null) {
            zzi(byteBuffer3.remaining());
            this.zzb.put(byteBuffer3);
        }
        this.zzf = zzggVar.zzd;
        return true;
    }

    public final boolean zzp() {
        return this.zzg > 0;
    }
}
