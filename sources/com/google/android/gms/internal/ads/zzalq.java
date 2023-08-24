package com.google.android.gms.internal.ads;

import androidx.core.view.MotionEventCompat;
import java.nio.ByteBuffer;
import java.util.Date;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzalq extends zzgua {
    private Date zza;
    private Date zzh;
    private long zzi;
    private long zzj;
    private double zzk;
    private float zzl;
    private zzguk zzm;
    private long zzn;

    public zzalq() {
        super("mvhd");
        this.zzk = 1.0d;
        this.zzl = 1.0f;
        this.zzm = zzguk.zza;
    }

    public final String toString() {
        return "MovieHeaderBox[creationTime=" + this.zza + ";modificationTime=" + this.zzh + ";timescale=" + this.zzi + ";duration=" + this.zzj + ";rate=" + this.zzk + ";volume=" + this.zzl + ";matrix=" + this.zzm + ";nextTrackId=" + this.zzn + "]";
    }

    public final long zzd() {
        return this.zzj;
    }

    public final long zze() {
        return this.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzgty
    public final void zzf(ByteBuffer byteBuffer) {
        zzi(byteBuffer);
        if (zzh() == 1) {
            this.zza = zzguf.zza(zzalm.zzf(byteBuffer));
            this.zzh = zzguf.zza(zzalm.zzf(byteBuffer));
            this.zzi = zzalm.zze(byteBuffer);
            this.zzj = zzalm.zzf(byteBuffer);
        } else {
            this.zza = zzguf.zza(zzalm.zze(byteBuffer));
            this.zzh = zzguf.zza(zzalm.zze(byteBuffer));
            this.zzi = zzalm.zze(byteBuffer);
            this.zzj = zzalm.zze(byteBuffer);
        }
        this.zzk = zzalm.zzb(byteBuffer);
        byte[] bArr = new byte[2];
        byteBuffer.get(bArr);
        this.zzl = ((short) ((bArr[1] & 255) | ((short) ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)))) / 256.0f;
        zzalm.zzd(byteBuffer);
        zzalm.zze(byteBuffer);
        zzalm.zze(byteBuffer);
        double zzb = zzalm.zzb(byteBuffer);
        double zzb2 = zzalm.zzb(byteBuffer);
        double zza = zzalm.zza(byteBuffer);
        this.zzm = new zzguk(zzb, zzb2, zzalm.zzb(byteBuffer), zzalm.zzb(byteBuffer), zza, zzalm.zza(byteBuffer), zzalm.zza(byteBuffer), zzalm.zzb(byteBuffer), zzalm.zzb(byteBuffer));
        byteBuffer.getInt();
        byteBuffer.getInt();
        byteBuffer.getInt();
        byteBuffer.getInt();
        byteBuffer.getInt();
        byteBuffer.getInt();
        this.zzn = zzalm.zze(byteBuffer);
    }
}
