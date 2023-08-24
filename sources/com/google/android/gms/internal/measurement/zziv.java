package com.google.android.gms.internal.measurement;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zziv extends zziy {
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziv(byte[] bArr, int r2, int r3) {
        super(bArr);
        zzj(0, r3, bArr.length);
        this.zzc = r3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zziy, com.google.android.gms.internal.measurement.zzjb
    public final byte zzb(int r2) {
        return this.zza[r2];
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zziy, com.google.android.gms.internal.measurement.zzjb
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zziy, com.google.android.gms.internal.measurement.zzjb
    public final byte zza(int r5) {
        int r0 = this.zzc;
        if (((r0 - (r5 + 1)) | r5) < 0) {
            if (r5 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + r5);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + r5 + ", " + r0);
        }
        return this.zza[r5];
    }
}
