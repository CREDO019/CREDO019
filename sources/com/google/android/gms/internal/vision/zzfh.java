package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzfh implements Serializable, Iterable<Byte> {
    public static final zzfh zzrx = new zzfr(zzgt.zzxc);
    private static final zzfn zzry;
    private static final Comparator<zzfh> zzrz;
    private int zzmi = 0;

    /* JADX INFO: Access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public abstract boolean equals(Object obj);

    public abstract int size();

    protected abstract String zza(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzfi zzfiVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zza(byte[] bArr, int r2, int r3, int r4);

    public abstract byte zzan(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzao(int r1);

    protected abstract int zzb(int r1, int r2, int r3);

    public abstract boolean zzes();

    public abstract zzfh zzf(int r1, int r2);

    public static zzfh zza(byte[] bArr, int r3, int r4) {
        zzc(r3, r3 + r4, bArr.length);
        return new zzfr(zzry.zzd(bArr, r3, r4));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfh zzd(byte[] bArr) {
        return new zzfr(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfh zzb(byte[] bArr, int r2, int r3) {
        return new zzfo(bArr, r2, r3);
    }

    public static zzfh zzw(String str) {
        return new zzfr(str.getBytes(zzgt.UTF_8));
    }

    public final String zzer() {
        return size() == 0 ? "" : zza(zzgt.UTF_8);
    }

    public final int hashCode() {
        int r0 = this.zzmi;
        if (r0 == 0) {
            int size = size();
            r0 = zzb(size, 0, size);
            if (r0 == 0) {
                r0 = 1;
            }
            this.zzmi = r0;
        }
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfp zzap(int r2) {
        return new zzfp(r2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzet() {
        return this.zzmi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r3, int r4, int r5) {
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

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        objArr[2] = size() <= 50 ? zzjf.zzd(this) : String.valueOf(zzjf.zzd(zzf(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new zzfk(this);
    }

    static {
        zzry = zzfa.zzdr() ? new zzfu(null) : new zzfl(null);
        zzrz = new zzfj();
    }
}
