package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpc implements zzne {
    private int zzb;
    private float zzc = 1.0f;
    private float zzd = 1.0f;
    private zznc zze = zznc.zza;
    private zznc zzf;
    private zznc zzg;
    private zznc zzh;
    private boolean zzi;
    private zzpb zzj;
    private ByteBuffer zzk;
    private ShortBuffer zzl;
    private ByteBuffer zzm;
    private long zzn;
    private long zzo;
    private boolean zzp;

    public zzpc() {
        zznc zzncVar = zznc.zza;
        this.zzf = zzncVar;
        this.zzg = zzncVar;
        this.zzh = zzncVar;
        ByteBuffer byteBuffer = zza;
        this.zzk = byteBuffer;
        this.zzl = byteBuffer.asShortBuffer();
        this.zzm = zza;
        this.zzb = -1;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final zznc zza(zznc zzncVar) throws zznd {
        if (zzncVar.zzd == 2) {
            int r0 = this.zzb;
            if (r0 == -1) {
                r0 = zzncVar.zzb;
            }
            this.zze = zzncVar;
            zznc zzncVar2 = new zznc(r0, zzncVar.zzc, 2);
            this.zzf = zzncVar2;
            this.zzi = true;
            return zzncVar2;
        }
        throw new zznd(zzncVar);
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final ByteBuffer zzb() {
        int zza;
        zzpb zzpbVar = this.zzj;
        if (zzpbVar != null && (zza = zzpbVar.zza()) > 0) {
            if (this.zzk.capacity() < zza) {
                ByteBuffer order = ByteBuffer.allocateDirect(zza).order(ByteOrder.nativeOrder());
                this.zzk = order;
                this.zzl = order.asShortBuffer();
            } else {
                this.zzk.clear();
                this.zzl.clear();
            }
            zzpbVar.zzd(this.zzl);
            this.zzo += zza;
            this.zzk.limit(zza);
            this.zzm = this.zzk;
        }
        ByteBuffer byteBuffer = this.zzm;
        this.zzm = zza;
        return byteBuffer;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zzc() {
        if (zzg()) {
            zznc zzncVar = this.zze;
            this.zzg = zzncVar;
            this.zzh = this.zzf;
            if (this.zzi) {
                this.zzj = new zzpb(zzncVar.zzb, zzncVar.zzc, this.zzc, this.zzd, this.zzh.zzb);
            } else {
                zzpb zzpbVar = this.zzj;
                if (zzpbVar != null) {
                    zzpbVar.zzc();
                }
            }
        }
        this.zzm = zza;
        this.zzn = 0L;
        this.zzo = 0L;
        this.zzp = false;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zzd() {
        zzpb zzpbVar = this.zzj;
        if (zzpbVar != null) {
            zzpbVar.zze();
        }
        this.zzp = true;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zze(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            zzpb zzpbVar = this.zzj;
            Objects.requireNonNull(zzpbVar);
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.zzn += remaining;
            zzpbVar.zzf(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zzf() {
        this.zzc = 1.0f;
        this.zzd = 1.0f;
        this.zze = zznc.zza;
        zznc zzncVar = zznc.zza;
        this.zzf = zzncVar;
        this.zzg = zzncVar;
        this.zzh = zzncVar;
        ByteBuffer byteBuffer = zza;
        this.zzk = byteBuffer;
        this.zzl = byteBuffer.asShortBuffer();
        this.zzm = zza;
        this.zzb = -1;
        this.zzi = false;
        this.zzj = null;
        this.zzn = 0L;
        this.zzo = 0L;
        this.zzp = false;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final boolean zzg() {
        if (this.zzf.zzb != -1) {
            return Math.abs(this.zzc + (-1.0f)) >= 1.0E-4f || Math.abs(this.zzd + (-1.0f)) >= 1.0E-4f || this.zzf.zzb != this.zze.zzb;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final boolean zzh() {
        zzpb zzpbVar;
        return this.zzp && ((zzpbVar = this.zzj) == null || zzpbVar.zza() == 0);
    }

    public final long zzi(long j) {
        long j2 = this.zzo;
        if (j2 >= 1024) {
            long j3 = this.zzn;
            zzpb zzpbVar = this.zzj;
            Objects.requireNonNull(zzpbVar);
            long zzb = j3 - zzpbVar.zzb();
            int r1 = this.zzh.zzb;
            int r2 = this.zzg.zzb;
            if (r1 == r2) {
                return zzel.zzw(j, zzb, j2);
            }
            return zzel.zzw(j, zzb * r1, j2 * r2);
        }
        return (long) (this.zzc * j);
    }

    public final void zzj(float f) {
        if (this.zzd != f) {
            this.zzd = f;
            this.zzi = true;
        }
    }

    public final void zzk(float f) {
        if (this.zzc != f) {
            this.zzc = f;
            this.zzi = true;
        }
    }
}
