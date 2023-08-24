package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.audio.SilenceSkippingAudioProcessor;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpa extends zzob {
    private int zzd;
    private boolean zze;
    private byte[] zzf = zzel.zzf;
    private byte[] zzg = zzel.zzf;
    private int zzh;
    private int zzi;
    private int zzj;
    private boolean zzk;
    private long zzl;

    private final int zzq(long j) {
        return (int) ((j * this.zzb.zzb) / 1000000);
    }

    private final int zzr(ByteBuffer byteBuffer) {
        for (int position = byteBuffer.position(); position < byteBuffer.limit(); position += 2) {
            if (Math.abs((int) byteBuffer.getShort(position)) > 1024) {
                int r4 = this.zzd;
                return r4 * (position / r4);
            }
        }
        return byteBuffer.limit();
    }

    private final void zzs(byte[] bArr, int r4) {
        zzj(r4).put(bArr, 0, r4).flip();
        if (r4 > 0) {
            this.zzk = true;
        }
    }

    private final void zzt(ByteBuffer byteBuffer, byte[] bArr, int r7) {
        int min = Math.min(byteBuffer.remaining(), this.zzj);
        int r1 = this.zzj - min;
        System.arraycopy(bArr, r7 - r1, this.zzg, 0, r1);
        byteBuffer.position(byteBuffer.limit() - min);
        byteBuffer.get(this.zzg, r1, min);
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zze(ByteBuffer byteBuffer) {
        int position;
        while (byteBuffer.hasRemaining() && !zzn()) {
            int r0 = this.zzh;
            if (r0 == 0) {
                int limit = byteBuffer.limit();
                byteBuffer.limit(Math.min(limit, byteBuffer.position() + this.zzf.length));
                int limit2 = byteBuffer.limit();
                while (true) {
                    limit2 -= 2;
                    if (limit2 >= byteBuffer.position()) {
                        if (Math.abs((int) byteBuffer.getShort(limit2)) > 1024) {
                            int r3 = this.zzd;
                            position = ((limit2 / r3) * r3) + r3;
                            break;
                        }
                    } else {
                        position = byteBuffer.position();
                        break;
                    }
                }
                if (position == byteBuffer.position()) {
                    this.zzh = 1;
                } else {
                    byteBuffer.limit(position);
                    int remaining = byteBuffer.remaining();
                    zzj(remaining).put(byteBuffer).flip();
                    if (remaining > 0) {
                        this.zzk = true;
                    }
                }
                byteBuffer.limit(limit);
            } else if (r0 == 1) {
                int limit3 = byteBuffer.limit();
                int zzr = zzr(byteBuffer);
                int position2 = zzr - byteBuffer.position();
                byte[] bArr = this.zzf;
                int length = bArr.length;
                int r6 = this.zzi;
                int r5 = length - r6;
                if (zzr >= limit3 || position2 >= r5) {
                    int min = Math.min(position2, r5);
                    byteBuffer.limit(byteBuffer.position() + min);
                    byteBuffer.get(this.zzf, this.zzi, min);
                    int r32 = this.zzi + min;
                    this.zzi = r32;
                    byte[] bArr2 = this.zzf;
                    if (r32 == bArr2.length) {
                        if (this.zzk) {
                            zzs(bArr2, this.zzj);
                            long j = this.zzl;
                            int r1 = this.zzi;
                            int r52 = this.zzj;
                            this.zzl = j + ((r1 - (r52 + r52)) / this.zzd);
                            r32 = r1;
                        } else {
                            this.zzl += (r32 - this.zzj) / this.zzd;
                        }
                        zzt(byteBuffer, this.zzf, r32);
                        this.zzi = 0;
                        this.zzh = 2;
                    }
                    byteBuffer.limit(limit3);
                } else {
                    zzs(bArr, r6);
                    this.zzi = 0;
                    this.zzh = 0;
                }
            } else {
                int limit4 = byteBuffer.limit();
                int zzr2 = zzr(byteBuffer);
                byteBuffer.limit(zzr2);
                this.zzl += byteBuffer.remaining() / this.zzd;
                zzt(byteBuffer, this.zzg, this.zzj);
                if (zzr2 < limit4) {
                    zzs(this.zzg, this.zzj);
                    this.zzh = 0;
                    byteBuffer.limit(limit4);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzob, com.google.android.gms.internal.ads.zzne
    public final boolean zzg() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzob
    public final zznc zzi(zznc zzncVar) throws zznd {
        if (zzncVar.zzd == 2) {
            return this.zze ? zzncVar : zznc.zza;
        }
        throw new zznd(zzncVar);
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzk() {
        if (this.zze) {
            this.zzd = this.zzb.zze;
            int zzq = zzq(SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) * this.zzd;
            if (this.zzf.length != zzq) {
                this.zzf = new byte[zzq];
            }
            int zzq2 = zzq(SilenceSkippingAudioProcessor.DEFAULT_PADDING_SILENCE_US) * this.zzd;
            this.zzj = zzq2;
            if (this.zzg.length != zzq2) {
                this.zzg = new byte[zzq2];
            }
        }
        this.zzh = 0;
        this.zzl = 0L;
        this.zzi = 0;
        this.zzk = false;
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzl() {
        int r0 = this.zzi;
        if (r0 > 0) {
            zzs(this.zzf, r0);
        }
        if (this.zzk) {
            return;
        }
        this.zzl += this.zzj / this.zzd;
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzm() {
        this.zze = false;
        this.zzj = 0;
        this.zzf = zzel.zzf;
        this.zzg = zzel.zzf;
    }

    public final long zzo() {
        return this.zzl;
    }

    public final void zzp(boolean z) {
        this.zze = z;
    }
}
