package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzjd extends zzjf {
    private final byte[] zzb;
    private int zzc;
    private int zzd;
    private int zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzjd(byte[] bArr, int r2, int r3, boolean z, zzjc zzjcVar) {
        super(null);
        this.zze = Integer.MAX_VALUE;
        this.zzb = bArr;
        this.zzc = 0;
    }

    public final int zza(int r4) throws zzkm {
        int r42 = this.zze;
        this.zze = 0;
        int r1 = this.zzc + this.zzd;
        this.zzc = r1;
        if (r1 > 0) {
            this.zzd = r1;
            this.zzc = 0;
        } else {
            this.zzd = 0;
        }
        return r42;
    }
}
