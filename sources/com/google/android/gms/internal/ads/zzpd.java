package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpd extends zzob {
    private int zzd;
    private int zze;
    private boolean zzf;
    private int zzg;
    private byte[] zzh = zzel.zzf;
    private int zzi;
    private long zzj;

    @Override // com.google.android.gms.internal.ads.zzob, com.google.android.gms.internal.ads.zzne
    public final ByteBuffer zzb() {
        int r0;
        if (super.zzh() && (r0 = this.zzi) > 0) {
            zzj(r0).put(this.zzh, 0, this.zzi).flip();
            this.zzi = 0;
        }
        return super.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zze(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int r2 = limit - position;
        if (r2 == 0) {
            return;
        }
        int min = Math.min(r2, this.zzg);
        this.zzj += min / this.zzb.zze;
        this.zzg -= min;
        byteBuffer.position(position + min);
        if (this.zzg > 0) {
            return;
        }
        int r22 = r2 - min;
        int length = (this.zzi + r22) - this.zzh.length;
        ByteBuffer zzj = zzj(length);
        int zzf = zzel.zzf(length, 0, this.zzi);
        zzj.put(this.zzh, 0, zzf);
        int zzf2 = zzel.zzf(length - zzf, 0, r22);
        byteBuffer.limit(byteBuffer.position() + zzf2);
        zzj.put(byteBuffer);
        byteBuffer.limit(limit);
        int r23 = r22 - zzf2;
        int r0 = this.zzi - zzf;
        this.zzi = r0;
        byte[] bArr = this.zzh;
        System.arraycopy(bArr, zzf, bArr, 0, r0);
        byteBuffer.get(this.zzh, this.zzi, r23);
        this.zzi += r23;
        zzj.flip();
    }

    @Override // com.google.android.gms.internal.ads.zzob, com.google.android.gms.internal.ads.zzne
    public final boolean zzh() {
        return super.zzh() && this.zzi == 0;
    }

    @Override // com.google.android.gms.internal.ads.zzob
    public final zznc zzi(zznc zzncVar) throws zznd {
        if (zzncVar.zzd == 2) {
            this.zzf = true;
            return (this.zzd == 0 && this.zze == 0) ? zznc.zza : zzncVar;
        }
        throw new zznd(zzncVar);
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzk() {
        if (this.zzf) {
            this.zzf = false;
            int r0 = this.zze;
            int r2 = this.zzb.zze;
            this.zzh = new byte[r0 * r2];
            this.zzg = this.zzd * r2;
        }
        this.zzi = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzl() {
        int r0;
        if (this.zzf) {
            if (this.zzi > 0) {
                this.zzj += r0 / this.zzb.zze;
            }
            this.zzi = 0;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzob
    protected final void zzm() {
        this.zzh = zzel.zzf;
    }

    public final long zzo() {
        return this.zzj;
    }

    public final void zzp() {
        this.zzj = 0L;
    }

    public final void zzq(int r1, int r2) {
        this.zzd = r1;
        this.zze = r2;
    }
}
