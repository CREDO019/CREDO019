package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzob implements zzne {
    protected zznc zzb;
    protected zznc zzc;
    private zznc zzd;
    private zznc zze;
    private ByteBuffer zzf;
    private ByteBuffer zzg;
    private boolean zzh;

    public zzob() {
        ByteBuffer byteBuffer = zza;
        this.zzf = byteBuffer;
        this.zzg = byteBuffer;
        this.zzd = zznc.zza;
        zznc zzncVar = zznc.zza;
        this.zze = zzncVar;
        this.zzb = zzncVar;
        this.zzc = zzncVar;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final zznc zza(zznc zzncVar) throws zznd {
        this.zzd = zzncVar;
        this.zze = zzi(zzncVar);
        return zzg() ? this.zze : zznc.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public ByteBuffer zzb() {
        ByteBuffer byteBuffer = this.zzg;
        this.zzg = zza;
        return byteBuffer;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zzc() {
        this.zzg = zza;
        this.zzh = false;
        this.zzb = this.zzd;
        this.zzc = this.zze;
        zzk();
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zzd() {
        this.zzh = true;
        zzl();
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public final void zzf() {
        zzc();
        this.zzf = zza;
        this.zzd = zznc.zza;
        zznc zzncVar = zznc.zza;
        this.zze = zzncVar;
        this.zzb = zzncVar;
        this.zzc = zzncVar;
        zzm();
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public boolean zzg() {
        return this.zze != zznc.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzne
    public boolean zzh() {
        return this.zzh && this.zzg == zza;
    }

    protected zznc zzi(zznc zzncVar) throws zznd {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ByteBuffer zzj(int r2) {
        if (this.zzf.capacity() < r2) {
            this.zzf = ByteBuffer.allocateDirect(r2).order(ByteOrder.nativeOrder());
        } else {
            this.zzf.clear();
        }
        ByteBuffer byteBuffer = this.zzf;
        this.zzg = byteBuffer;
        return byteBuffer;
    }

    protected void zzk() {
    }

    protected void zzl() {
    }

    protected void zzm() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzn() {
        return this.zzg.hasRemaining();
    }
}
