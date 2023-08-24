package com.google.android.gms.internal.ads;

import android.os.IBinder;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfpp extends zzfqj {
    private IBinder zza;
    private String zzb;
    private int zzc;
    private float zzd;
    private int zze;
    private String zzf;
    private byte zzg;

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zza(String str) {
        this.zzf = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zzb(String str) {
        this.zzb = str;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zzc(int r1) {
        this.zzg = (byte) (this.zzg | 8);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zzd(int r1) {
        this.zzc = r1;
        this.zzg = (byte) (this.zzg | 2);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zze(float f) {
        this.zzd = f;
        this.zzg = (byte) (this.zzg | 4);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zzf(boolean z) {
        this.zzg = (byte) (this.zzg | 1);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zzg(IBinder iBinder) {
        Objects.requireNonNull(iBinder, "Null windowToken");
        this.zza = iBinder;
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqj zzh(int r1) {
        this.zze = r1;
        this.zzg = (byte) (this.zzg | 16);
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfqj
    public final zzfqk zzi() {
        IBinder iBinder;
        if (this.zzg != 31 || (iBinder = this.zza) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" windowToken");
            }
            if ((this.zzg & 1) == 0) {
                sb.append(" stableSessionToken");
            }
            if ((this.zzg & 2) == 0) {
                sb.append(" layoutGravity");
            }
            if ((this.zzg & 4) == 0) {
                sb.append(" layoutVerticalMargin");
            }
            if ((this.zzg & 8) == 0) {
                sb.append(" displayMode");
            }
            if ((this.zzg & 16) == 0) {
                sb.append(" windowWidthPx");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzfpr(iBinder, false, this.zzb, this.zzc, this.zzd, 0, null, this.zze, this.zzf, null);
    }
}
