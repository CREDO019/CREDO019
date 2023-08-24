package com.google.android.gms.internal.vision;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzfo extends zzfr {
    private final int zzsb;
    private final int zzsc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfo(byte[] bArr, int r3, int r4) {
        super(bArr);
        zzc(r3, r3 + r4, bArr.length);
        this.zzsb = r3;
        this.zzsc = r4;
    }

    @Override // com.google.android.gms.internal.vision.zzfr, com.google.android.gms.internal.vision.zzfh
    public final byte zzan(int r5) {
        int size = size();
        if (((size - (r5 + 1)) | r5) < 0) {
            if (r5 < 0) {
                StringBuilder sb = new StringBuilder(22);
                sb.append("Index < 0: ");
                sb.append(r5);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Index > length: ");
            sb2.append(r5);
            sb2.append(", ");
            sb2.append(size);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
        return this.zzse[this.zzsb + r5];
    }

    @Override // com.google.android.gms.internal.vision.zzfr, com.google.android.gms.internal.vision.zzfh
    final byte zzao(int r3) {
        return this.zzse[this.zzsb + r3];
    }

    @Override // com.google.android.gms.internal.vision.zzfr, com.google.android.gms.internal.vision.zzfh
    public final int size() {
        return this.zzsc;
    }

    @Override // com.google.android.gms.internal.vision.zzfr
    protected final int zzeu() {
        return this.zzsb;
    }

    @Override // com.google.android.gms.internal.vision.zzfr, com.google.android.gms.internal.vision.zzfh
    protected final void zza(byte[] bArr, int r3, int r4, int r5) {
        System.arraycopy(this.zzse, zzeu(), bArr, 0, r5);
    }
}
