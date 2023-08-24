package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgnf implements Iterable, Serializable {
    private static final Comparator zza;
    public static final zzgnf zzb = new zzgnb(zzgox.zzd);
    private static final zzgne zzd;
    private int zzc = 0;

    static {
        int r0 = zzgmq.zza;
        zzd = new zzgne(null);
        zza = new zzgmw();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzB(int r3, int r4) {
        if (((r4 - (r3 + 1)) | r3) < 0) {
            if (r3 < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + r3);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + r3 + ", " + r4);
        }
    }

    public static zzgnc zzt() {
        return new zzgnc(128);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzgnf zzu(Iterable iterable) {
        int size;
        if (!(iterable instanceof Collection)) {
            Iterator it = iterable.iterator();
            size = 0;
            while (it.hasNext()) {
                it.next();
                size++;
            }
        } else {
            size = iterable.size();
        }
        if (size == 0) {
            return zzb;
        }
        return zzc(iterable.iterator(), size);
    }

    public static zzgnf zzv(byte[] bArr) {
        return zzw(bArr, 0, bArr.length);
    }

    public static zzgnf zzw(byte[] bArr, int r4, int r5) {
        zzq(r4, r4 + r5, bArr.length);
        byte[] bArr2 = new byte[r5];
        System.arraycopy(bArr, r4, bArr2, 0, r5);
        return new zzgnb(bArr2);
    }

    public static zzgnf zzx(String str) {
        return new zzgnb(str.getBytes(zzgox.zzb));
    }

    public static zzgnf zzy(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        int r1 = 256;
        while (true) {
            byte[] bArr = new byte[r1];
            int r4 = 0;
            while (r4 < r1) {
                int read = inputStream.read(bArr, r4, r1 - r4);
                if (read == -1) {
                    break;
                }
                r4 += read;
            }
            zzgnf zzw = r4 == 0 ? null : zzw(bArr, 0, r4);
            if (zzw != null) {
                arrayList.add(zzw);
                r1 = Math.min(r1 + r1, 8192);
            } else {
                return zzu(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgnf zzz(byte[] bArr) {
        return new zzgnb(bArr);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int r0 = this.zzc;
        if (r0 == 0) {
            int zzd2 = zzd();
            r0 = zzi(zzd2, 0, zzd2);
            if (r0 == 0) {
                r0 = 1;
            }
            this.zzc = r0;
        }
        return r0;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzgrf.zza(this) : zzgrf.zza(zzk(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public final String zzA(Charset charset) {
        return zzd() == 0 ? "" : zzm(charset);
    }

    @Deprecated
    public final void zzC(byte[] bArr, int r4, int r5, int r6) {
        zzq(0, r6, zzd());
        zzq(r5, r5 + r6, bArr.length);
        if (r6 > 0) {
            zze(bArr, 0, r5, r6);
        }
    }

    public final boolean zzD() {
        return zzd() == 0;
    }

    public final byte[] zzE() {
        int zzd2 = zzd();
        if (zzd2 == 0) {
            return zzgox.zzd;
        }
        byte[] bArr = new byte[zzd2];
        zze(bArr, 0, 0, zzd2);
        return bArr;
    }

    public abstract byte zza(int r1);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzb(int r1);

    public abstract int zzd();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void zze(byte[] bArr, int r2, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int zzf();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean zzh();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int zzi(int r1, int r2, int r3);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int zzj(int r1, int r2, int r3);

    public abstract zzgnf zzk(int r1, int r2);

    public abstract zzgnn zzl();

    protected abstract String zzm(Charset charset);

    public abstract ByteBuffer zzn();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzo(zzgmu zzgmuVar) throws IOException;

    public abstract boolean zzp();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzr() {
        return this.zzc;
    }

    @Override // java.lang.Iterable
    /* renamed from: zzs */
    public zzgmz iterator() {
        return new zzgmv(this);
    }

    private static zzgnf zzc(Iterator it, int r4) {
        if (r4 > 0) {
            if (r4 == 1) {
                return (zzgnf) it.next();
            }
            int r0 = r4 >>> 1;
            zzgnf zzc = zzc(it, r0);
            zzgnf zzc2 = zzc(it, r4 - r0);
            if (Integer.MAX_VALUE - zzc.zzd() < zzc2.zzd()) {
                int zzd2 = zzc.zzd();
                int zzd3 = zzc2.zzd();
                throw new IllegalArgumentException("ByteString would be too long: " + zzd2 + "+" + zzd3);
            }
            return zzgqp.zzG(zzc, zzc2);
        }
        throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(r4)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int r3, int r4, int r5) {
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
