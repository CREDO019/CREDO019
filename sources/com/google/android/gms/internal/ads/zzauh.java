package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzauh implements zzath {
    private zzaug zzd;
    private ByteBuffer zzg;
    private ShortBuffer zzh;
    private ByteBuffer zzi;
    private long zzj;
    private long zzk;
    private boolean zzl;
    private float zze = 1.0f;
    private float zzf = 1.0f;
    private int zzb = -1;
    private int zzc = -1;

    public zzauh() {
        ByteBuffer byteBuffer = zza;
        this.zzg = byteBuffer;
        this.zzh = byteBuffer.asShortBuffer();
        this.zzi = zza;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final int zzb() {
        return 2;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final ByteBuffer zzc() {
        ByteBuffer byteBuffer = this.zzi;
        this.zzi = zza;
        return byteBuffer;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zzd() {
        zzaug zzaugVar = new zzaug(this.zzc, this.zzb);
        this.zzd = zzaugVar;
        zzaugVar.zzf(this.zze);
        this.zzd.zze(this.zzf);
        this.zzi = zza;
        this.zzj = 0L;
        this.zzk = 0L;
        this.zzl = false;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zze() {
        this.zzd.zzc();
        this.zzl = true;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zzf(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.zzj += remaining;
            this.zzd.zzd(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int zza = this.zzd.zza() * this.zzb;
        int r7 = zza + zza;
        if (r7 > 0) {
            if (this.zzg.capacity() < r7) {
                ByteBuffer order = ByteBuffer.allocateDirect(r7).order(ByteOrder.nativeOrder());
                this.zzg = order;
                this.zzh = order.asShortBuffer();
            } else {
                this.zzg.clear();
                this.zzh.clear();
            }
            this.zzd.zzb(this.zzh);
            this.zzk += r7;
            this.zzg.limit(r7);
            this.zzi = this.zzg;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final void zzg() {
        this.zzd = null;
        ByteBuffer byteBuffer = zza;
        this.zzg = byteBuffer;
        this.zzh = byteBuffer.asShortBuffer();
        this.zzi = zza;
        this.zzb = -1;
        this.zzc = -1;
        this.zzj = 0L;
        this.zzk = 0L;
        this.zzl = false;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final boolean zzh(int r2, int r3, int r4) throws zzatg {
        if (r4 == 2) {
            if (this.zzc == r2 && this.zzb == r3) {
                return false;
            }
            this.zzc = r2;
            this.zzb = r3;
            return true;
        }
        throw new zzatg(r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final boolean zzi() {
        return Math.abs(this.zze + (-1.0f)) >= 0.01f || Math.abs(this.zzf + (-1.0f)) >= 0.01f;
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final boolean zzj() {
        zzaug zzaugVar;
        return this.zzl && ((zzaugVar = this.zzd) == null || zzaugVar.zza() == 0);
    }

    public final float zzk(float f) {
        this.zzf = zzban.zza(1.0f, 0.1f, 8.0f);
        return 1.0f;
    }

    public final float zzl(float f) {
        float zza = zzban.zza(f, 0.1f, 8.0f);
        this.zze = zza;
        return zza;
    }

    public final long zzm() {
        return this.zzj;
    }

    public final long zzn() {
        return this.zzk;
    }
}
