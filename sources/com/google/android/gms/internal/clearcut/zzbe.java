package com.google.android.gms.internal.clearcut;

/* loaded from: classes2.dex */
final class zzbe extends zzbi {
    private final int zzfm;
    private final int zzfn;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbe(byte[] bArr, int r3, int r4) {
        super(bArr);
        zzb(r3, r3 + r4, bArr.length);
        this.zzfm = r3;
        this.zzfn = r4;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi, com.google.android.gms.internal.clearcut.zzbb
    public final int size() {
        return this.zzfn;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi
    protected final int zzac() {
        return this.zzfm;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbi, com.google.android.gms.internal.clearcut.zzbb
    public final byte zzj(int r5) {
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
        return this.zzfp[this.zzfm + r5];
    }
}
