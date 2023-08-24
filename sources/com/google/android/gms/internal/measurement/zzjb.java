package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public abstract class zzjb implements Iterable, Serializable {
    private static final Comparator zza;
    public static final zzjb zzb = new zziy(zzkk.zzd);
    private static final zzja zzd;
    private int zzc = 0;

    static {
        int r0 = zzin.zza;
        zzd = new zzja(null);
        zza = new zzit();
    }

    public static zzjb zzl(byte[] bArr, int r4, int r5) {
        zzj(r4, r4 + r5, bArr.length);
        byte[] bArr2 = new byte[r5];
        System.arraycopy(bArr, r4, bArr2, 0, r5);
        return new zziy(bArr2);
    }

    public static zzjb zzm(String str) {
        return new zziy(str.getBytes(zzkk.zzb));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int r0 = this.zzc;
        if (r0 == 0) {
            int zzd2 = zzd();
            r0 = zze(zzd2, 0, zzd2);
            if (r0 == 0) {
                r0 = 1;
            }
            this.zzc = r0;
        }
        return r0;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzis(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzmj.zza(this) : zzmj.zza(zzf(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzb(int r1);

    public abstract int zzd();

    protected abstract int zze(int r1, int r2, int r3);

    public abstract zzjb zzf(int r1, int r2);

    protected abstract String zzg(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzh(zzir zzirVar) throws IOException;

    public abstract boolean zzi();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzk() {
        return this.zzc;
    }

    public final String zzn(Charset charset) {
        return zzd() == 0 ? "" : zzg(charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int r3, int r4, int r5) {
        int r0 = r4 - r3;
        if ((r3 | r4 | r0 | (r5 - r4)) < 0) {
            if (r3 < 0) {
                throw new IndexOutOfBoundsException("Beginning index: " + r3 + " < 0");
            } else if (r4 < r3) {
                throw new IndexOutOfBoundsException("Beginning index larger than ending index: " + r3 + ", " + r4);
            } else {
                throw new IndexOutOfBoundsException("End index: " + r4 + " >= " + r5);
            }
        }
        return r0;
    }
}
