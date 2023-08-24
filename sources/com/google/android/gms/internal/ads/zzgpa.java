package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgpa extends InputStream {
    private Iterator zza;
    private ByteBuffer zzb;
    private int zzc = 0;
    private int zzd;
    private int zze;
    private boolean zzf;
    private byte[] zzg;
    private int zzh;
    private long zzi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgpa(Iterable iterable) {
        this.zza = iterable.iterator();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) it.next();
            this.zzc++;
        }
        this.zzd = -1;
        if (zzb()) {
            return;
        }
        this.zzb = zzgox.zze;
        this.zzd = 0;
        this.zze = 0;
        this.zzi = 0L;
    }

    private final void zza(int r2) {
        int r0 = this.zze + r2;
        this.zze = r0;
        if (r0 == this.zzb.limit()) {
            zzb();
        }
    }

    private final boolean zzb() {
        this.zzd++;
        if (this.zza.hasNext()) {
            ByteBuffer byteBuffer = (ByteBuffer) this.zza.next();
            this.zzb = byteBuffer;
            this.zze = byteBuffer.position();
            if (this.zzb.hasArray()) {
                this.zzf = true;
                this.zzg = this.zzb.array();
                this.zzh = this.zzb.arrayOffset();
            } else {
                this.zzf = false;
                this.zzi = zzgrr.zze(this.zzb);
                this.zzg = null;
            }
            return true;
        }
        return false;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        byte zza;
        if (this.zzd == this.zzc) {
            return -1;
        }
        if (this.zzf) {
            zza = this.zzg[this.zze + this.zzh];
            zza(1);
        } else {
            zza = zzgrr.zza(this.zze + this.zzi);
            zza(1);
        }
        return zza & 255;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int r5, int r6) throws IOException {
        if (this.zzd == this.zzc) {
            return -1;
        }
        int limit = this.zzb.limit();
        int r1 = this.zze;
        int r0 = limit - r1;
        if (r6 > r0) {
            r6 = r0;
        }
        if (this.zzf) {
            System.arraycopy(this.zzg, r1 + this.zzh, bArr, r5, r6);
            zza(r6);
        } else {
            int position = this.zzb.position();
            ByteBuffer byteBuffer = (ByteBuffer) this.zzb.position(this.zze);
            this.zzb.get(bArr, r5, r6);
            ByteBuffer byteBuffer2 = (ByteBuffer) this.zzb.position(position);
            zza(r6);
        }
        return r6;
    }
}
