package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

/* loaded from: classes2.dex */
public abstract class zzbb implements Serializable, Iterable<Byte> {
    public static final zzbb zzfi = new zzbi(zzci.zzkt);
    private static final zzbf zzfj;
    private int zzfk = 0;

    static {
        zzfj = zzaw.zzx() ? new zzbj(null) : new zzbd(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int r3, int r4, int r5) {
        int r0 = r4 - r3;
        if ((r3 | r4 | r0 | (r5 - r4)) < 0) {
            if (r3 < 0) {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Beginning index: ");
                sb.append(r3);
                sb.append(" < 0");
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (r4 < r3) {
                StringBuilder sb2 = new StringBuilder(66);
                sb2.append("Beginning index larger than ending index: ");
                sb2.append(r3);
                sb2.append(", ");
                sb2.append(r4);
                throw new IndexOutOfBoundsException(sb2.toString());
            } else {
                StringBuilder sb3 = new StringBuilder(37);
                sb3.append("End index: ");
                sb3.append(r4);
                sb3.append(" >= ");
                sb3.append(r5);
                throw new IndexOutOfBoundsException(sb3.toString());
            }
        }
        return r0;
    }

    public static zzbb zzb(byte[] bArr, int r3, int r4) {
        return new zzbi(zzfj.zzc(bArr, r3, r4));
    }

    public static zzbb zzf(String str) {
        return new zzbi(str.getBytes(zzci.UTF_8));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzbg zzk(int r2) {
        return new zzbg(r2, null);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int r0 = this.zzfk;
        if (r0 == 0) {
            int size = size();
            r0 = zza(size, 0, size);
            if (r0 == 0) {
                r0 = 1;
            }
            this.zzfk = r0;
        }
        return r0;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzbc(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }

    protected abstract int zza(int r1, int r2, int r3);

    public abstract zzbb zza(int r1, int r2);

    protected abstract String zza(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzba zzbaVar) throws IOException;

    public abstract boolean zzaa();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzab() {
        return this.zzfk;
    }

    public abstract byte zzj(int r1);

    public final String zzz() {
        return size() == 0 ? "" : zza(zzci.UTF_8);
    }
}
