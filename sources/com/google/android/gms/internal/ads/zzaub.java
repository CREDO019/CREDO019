package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaub implements zzath {
    private int zzb;
    private int zzc;
    private int[] zzd;
    private boolean zze;
    private int[] zzf;
    private ByteBuffer zzg;
    private ByteBuffer zzh;
    private boolean zzi;

    public zzaub() {
        ByteBuffer byteBuffer = zza;
        this.zzg = byteBuffer;
        this.zzh = byteBuffer;
        this.zzb = -1;
        this.zzc = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final int zza() {
        int[] r0 = this.zzf;
        return r0 == null ? this.zzb : r0.length;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final int zzb() {
        return 2;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final ByteBuffer zzc() {
        ByteBuffer byteBuffer = this.zzh;
        this.zzh = zza;
        return byteBuffer;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zzd() {
        this.zzh = zza;
        this.zzi = false;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zze() {
        this.zzi = true;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zzf(ByteBuffer byteBuffer) {
        int[] r2;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int r22 = this.zzb;
        int length = ((limit - position) / (r22 + r22)) * this.zzf.length;
        int r3 = length + length;
        if (this.zzg.capacity() < r3) {
            this.zzg = ByteBuffer.allocateDirect(r3).order(ByteOrder.nativeOrder());
        } else {
            this.zzg.clear();
        }
        while (position < limit) {
            for (int r5 : this.zzf) {
                this.zzg.putShort(byteBuffer.getShort(r5 + r5 + position));
            }
            int r23 = this.zzb;
            position += r23 + r23;
        }
        byteBuffer.position(limit);
        this.zzg.flip();
        this.zzh = this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zzg() {
        zzd();
        this.zzg = zza;
        this.zzb = -1;
        this.zzc = -1;
        this.zzf = null;
        this.zze = false;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final boolean zzh(int r6, int r7, int r8) throws zzatg {
        boolean z = !Arrays.equals(this.zzd, this.zzf);
        int[] r2 = this.zzd;
        this.zzf = r2;
        if (r2 == null) {
            this.zze = false;
            return z;
        } else if (r8 == 2) {
            if (!z && this.zzc == r6 && this.zzb == r7) {
                return false;
            }
            this.zzc = r6;
            this.zzb = r7;
            this.zze = r7 != r2.length;
            int r82 = 0;
            while (true) {
                int[] r0 = this.zzf;
                if (r82 >= r0.length) {
                    return true;
                }
                int r02 = r0[r82];
                if (r02 < r7) {
                    this.zze = (r02 != r82) | this.zze;
                    r82++;
                } else {
                    throw new zzatg(r6, r7, 2);
                }
            }
        } else {
            throw new zzatg(r6, r7, r8);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final boolean zzi() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final boolean zzj() {
        return this.zzi && this.zzh == zza;
    }

    public final void zzk(int[] r1) {
        this.zzd = r1;
    }
}
