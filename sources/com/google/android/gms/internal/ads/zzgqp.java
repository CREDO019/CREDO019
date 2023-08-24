package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgqp extends zzgnf {
    static final int[] zza = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141, 267914296, 433494437, 701408733, 1134903170, 1836311903, Integer.MAX_VALUE};
    private final int zzc;
    private final zzgnf zzd;
    private final zzgnf zze;
    private final int zzf;
    private final int zzg;

    private zzgqp(zzgnf zzgnfVar, zzgnf zzgnfVar2) {
        this.zzd = zzgnfVar;
        this.zze = zzgnfVar2;
        int zzd = zzgnfVar.zzd();
        this.zzf = zzd;
        this.zzc = zzd + zzgnfVar2.zzd();
        this.zzg = Math.max(zzgnfVar.zzf(), zzgnfVar2.zzf()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgnf zzG(zzgnf zzgnfVar, zzgnf zzgnfVar2) {
        if (zzgnfVar2.zzd() == 0) {
            return zzgnfVar;
        }
        if (zzgnfVar.zzd() == 0) {
            return zzgnfVar2;
        }
        int zzd = zzgnfVar.zzd() + zzgnfVar2.zzd();
        if (zzd < 128) {
            return zzH(zzgnfVar, zzgnfVar2);
        }
        if (zzgnfVar instanceof zzgqp) {
            zzgqp zzgqpVar = (zzgqp) zzgnfVar;
            if (zzgqpVar.zze.zzd() + zzgnfVar2.zzd() < 128) {
                return new zzgqp(zzgqpVar.zzd, zzH(zzgqpVar.zze, zzgnfVar2));
            } else if (zzgqpVar.zzd.zzf() > zzgqpVar.zze.zzf() && zzgqpVar.zzg > zzgnfVar2.zzf()) {
                return new zzgqp(zzgqpVar.zzd, new zzgqp(zzgqpVar.zze, zzgnfVar2));
            }
        }
        if (zzd >= zzc(Math.max(zzgnfVar.zzf(), zzgnfVar2.zzf()) + 1)) {
            return new zzgqp(zzgnfVar, zzgnfVar2);
        }
        return zzgql.zza(new zzgql(null), zzgnfVar, zzgnfVar2);
    }

    private static zzgnf zzH(zzgnf zzgnfVar, zzgnf zzgnfVar2) {
        int zzd = zzgnfVar.zzd();
        int zzd2 = zzgnfVar2.zzd();
        byte[] bArr = new byte[zzd + zzd2];
        zzgnfVar.zzC(bArr, 0, 0, zzd);
        zzgnfVar2.zzC(bArr, 0, zzd, zzd2);
        return new zzgnb(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r2) {
        int[] r0 = zza;
        int length = r0.length;
        if (r2 >= 47) {
            return Integer.MAX_VALUE;
        }
        return r0[r2];
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final boolean equals(Object obj) {
        boolean zzg;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgnf)) {
            return false;
        }
        zzgnf zzgnfVar = (zzgnf) obj;
        if (this.zzc != zzgnfVar.zzd()) {
            return false;
        }
        if (this.zzc == 0) {
            return true;
        }
        int zzr = zzr();
        int zzr2 = zzgnfVar.zzr();
        if (zzr != 0 && zzr2 != 0 && zzr != zzr2) {
            return false;
        }
        zzgqn zzgqnVar = new zzgqn(this, null);
        zzgna next = zzgqnVar.next();
        zzgqn zzgqnVar2 = new zzgqn(zzgnfVar, null);
        zzgna next2 = zzgqnVar2.next();
        int r3 = 0;
        int r6 = 0;
        int r7 = 0;
        while (true) {
            int zzd = next.zzd() - r3;
            int zzd2 = next2.zzd() - r6;
            int min = Math.min(zzd, zzd2);
            if (r3 == 0) {
                zzg = next.zzg(next2, r6, min);
            } else {
                zzg = next2.zzg(next, r3, min);
            }
            if (!zzg) {
                return false;
            }
            r7 += min;
            int r11 = this.zzc;
            if (r7 >= r11) {
                if (r7 == r11) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (min == zzd) {
                next = zzgqnVar.next();
                r3 = 0;
            } else {
                r3 += min;
            }
            if (min == zzd2) {
                next2 = zzgqnVar2.next();
                r6 = 0;
            } else {
                r6 += min;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnf, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzgqj(this);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final byte zza(int r2) {
        zzB(r2, this.zzc);
        return zzb(r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final byte zzb(int r3) {
        int r0 = this.zzf;
        return r3 < r0 ? this.zzd.zzb(r3) : this.zze.zzb(r3 - r0);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final int zzd() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final void zze(byte[] bArr, int r4, int r5, int r6) {
        int r0 = this.zzf;
        if (r4 + r6 <= r0) {
            this.zzd.zze(bArr, r4, r5, r6);
        } else if (r4 >= r0) {
            this.zze.zze(bArr, r4 - r0, r5, r6);
        } else {
            int r02 = r0 - r4;
            this.zzd.zze(bArr, r4, r5, r02);
            this.zze.zze(bArr, 0, r5 + r02, r6 - r02);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final int zzf() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final boolean zzh() {
        return this.zzc >= zzc(this.zzg);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final int zzi(int r3, int r4, int r5) {
        int r0 = this.zzf;
        if (r4 + r5 <= r0) {
            return this.zzd.zzi(r3, r4, r5);
        }
        if (r4 >= r0) {
            return this.zze.zzi(r3, r4 - r0, r5);
        }
        int r02 = r0 - r4;
        return this.zze.zzi(this.zzd.zzi(r3, r4, r02), 0, r5 - r02);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final int zzj(int r3, int r4, int r5) {
        int r0 = this.zzf;
        if (r4 + r5 <= r0) {
            return this.zzd.zzj(r3, r4, r5);
        }
        if (r4 >= r0) {
            return this.zze.zzj(r3, r4 - r0, r5);
        }
        int r02 = r0 - r4;
        return this.zze.zzj(this.zzd.zzj(r3, r4, r02), 0, r5 - r02);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final zzgnf zzk(int r4, int r5) {
        int zzq = zzq(r4, r5, this.zzc);
        if (zzq == 0) {
            return zzgnf.zzb;
        }
        if (zzq == this.zzc) {
            return this;
        }
        int r0 = this.zzf;
        if (r5 <= r0) {
            return this.zzd.zzk(r4, r5);
        }
        if (r4 >= r0) {
            return this.zze.zzk(r4 - r0, r5 - r0);
        }
        zzgnf zzgnfVar = this.zzd;
        return new zzgqp(zzgnfVar.zzk(r4, zzgnfVar.zzd()), this.zze.zzk(0, r5 - this.zzf));
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final zzgnn zzl() {
        ArrayList<ByteBuffer> arrayList = new ArrayList();
        zzgqn zzgqnVar = new zzgqn(this, null);
        while (zzgqnVar.hasNext()) {
            arrayList.add(zzgqnVar.next().zzn());
        }
        int r1 = zzgnn.zzd;
        boolean z = false;
        int r4 = 0;
        for (ByteBuffer byteBuffer : arrayList) {
            r4 += byteBuffer.remaining();
            if (byteBuffer.hasArray()) {
                z |= true;
            } else {
                z = byteBuffer.isDirect() ? z | true : z | true;
            }
        }
        if (z) {
            return new zzgnj(arrayList, r4, true, null);
        }
        return zzgnn.zzH(new zzgpa(arrayList), 4096);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    protected final String zzm(Charset charset) {
        return new String(zzE(), charset);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final ByteBuffer zzn() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgnf
    public final void zzo(zzgmu zzgmuVar) throws IOException {
        this.zzd.zzo(zzgmuVar);
        this.zze.zzo(zzgmuVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final boolean zzp() {
        int zzj = this.zzd.zzj(0, 0, this.zzf);
        zzgnf zzgnfVar = this.zze;
        return zzgnfVar.zzj(zzj, 0, zzgnfVar.zzd()) == 0;
    }

    @Override // com.google.android.gms.internal.ads.zzgnf
    public final zzgmz zzs() {
        return new zzgqj(this);
    }
}
