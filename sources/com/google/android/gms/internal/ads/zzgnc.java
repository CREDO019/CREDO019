package com.google.android.gms.internal.ads;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgnc extends OutputStream {
    private static final byte[] zza = new byte[0];
    private int zzd;
    private int zzf;
    private final int zzb = 128;
    private final ArrayList zzc = new ArrayList();
    private byte[] zze = new byte[128];

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgnc(int r2) {
    }

    private final void zzd(int r4) {
        this.zzc.add(new zzgnb(this.zze));
        int length = this.zzd + this.zze.length;
        this.zzd = length;
        this.zze = new byte[Math.max(this.zzb, Math.max(r4, length >>> 1))];
        this.zzf = 0;
    }

    public final String toString() {
        return String.format("<ByteString.Output@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zza()));
    }

    @Override // java.io.OutputStream
    public final synchronized void write(int r4) {
        if (this.zzf == this.zze.length) {
            zzd(1);
        }
        byte[] bArr = this.zze;
        int r1 = this.zzf;
        this.zzf = r1 + 1;
        bArr[r1] = (byte) r4;
    }

    public final synchronized int zza() {
        return this.zzd + this.zzf;
    }

    public final synchronized zzgnf zzb() {
        int r0 = this.zzf;
        byte[] bArr = this.zze;
        if (r0 >= bArr.length) {
            this.zzc.add(new zzgnb(this.zze));
            this.zze = zza;
        } else if (r0 > 0) {
            this.zzc.add(new zzgnb(Arrays.copyOf(bArr, r0)));
        }
        this.zzd += this.zzf;
        this.zzf = 0;
        return zzgnf.zzu(this.zzc);
    }

    public final synchronized void zzc() {
        this.zzc.clear();
        this.zzd = 0;
        this.zzf = 0;
    }

    @Override // java.io.OutputStream
    public final synchronized void write(byte[] bArr, int r5, int r6) {
        byte[] bArr2 = this.zze;
        int length = bArr2.length;
        int r2 = this.zzf;
        int r1 = length - r2;
        if (r6 <= r1) {
            System.arraycopy(bArr, r5, bArr2, r2, r6);
            this.zzf += r6;
            return;
        }
        System.arraycopy(bArr, r5, bArr2, r2, r1);
        int r62 = r6 - r1;
        zzd(r62);
        System.arraycopy(bArr, r5 + r1, this.zze, 0, r62);
        this.zzf = r62;
    }
}
