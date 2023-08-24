package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgnl extends zzgnn {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgnl(InputStream inputStream, int r2, zzgnk zzgnkVar) {
        super(null);
        this.zzl = Integer.MAX_VALUE;
        zzgox.zzf(inputStream, "input");
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    private final List zzJ(int r7) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (r7 > 0) {
            int min = Math.min(r7, 4096);
            byte[] bArr = new byte[min];
            int r3 = 0;
            while (r3 < min) {
                int read = this.zze.read(bArr, r3, min - r3);
                if (read == -1) {
                    throw zzgoz.zzj();
                }
                this.zzk += read;
                r3 += read;
            }
            r7 -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzK() {
        int r0 = this.zzg + this.zzh;
        this.zzg = r0;
        int r1 = this.zzk + r0;
        int r2 = this.zzl;
        if (r1 <= r2) {
            this.zzh = 0;
            return;
        }
        int r12 = r1 - r2;
        this.zzh = r12;
        this.zzg = r0 - r12;
    }

    private final void zzL(int r3) throws IOException {
        if (zzM(r3)) {
            return;
        }
        if (r3 > (Integer.MAX_VALUE - this.zzk) - this.zzi) {
            throw zzgoz.zzi();
        }
        throw zzgoz.zzj();
    }

    private final boolean zzM(int r8) throws IOException {
        int r0 = this.zzi;
        int r1 = this.zzg;
        if (r0 + r8 <= r1) {
            throw new IllegalStateException("refillBuffer() called when " + r8 + " bytes were already available in buffer");
        }
        int r2 = this.zzk;
        if (r8 <= (Integer.MAX_VALUE - r2) - r0 && r2 + r0 + r8 <= this.zzl) {
            if (r0 > 0) {
                if (r1 > r0) {
                    byte[] bArr = this.zzf;
                    System.arraycopy(bArr, r0, bArr, 0, r1 - r0);
                }
                r2 = this.zzk + r0;
                this.zzk = r2;
                r1 = this.zzg - r0;
                this.zzg = r1;
                this.zzi = 0;
            }
            try {
                int read = this.zze.read(this.zzf, r1, Math.min(4096 - r1, (Integer.MAX_VALUE - r2) - r1));
                if (read == 0 || read < -1 || read > 4096) {
                    throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read > 0) {
                    this.zzg += read;
                    zzK();
                    if (this.zzg >= r8) {
                        return true;
                    }
                    return zzM(r8);
                } else {
                    return false;
                }
            } catch (zzgoz e) {
                e.zzk();
                throw e;
            }
        }
        return false;
    }

    private final byte[] zzN(int r5, boolean z) throws IOException {
        byte[] zzO = zzO(r5);
        if (zzO != null) {
            return zzO;
        }
        int r6 = this.zzi;
        int r0 = this.zzg;
        int r1 = r0 - r6;
        this.zzk += r0;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzJ = zzJ(r5 - r1);
        byte[] bArr = new byte[r5];
        System.arraycopy(this.zzf, r6, bArr, 0, r1);
        for (byte[] bArr2 : zzJ) {
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, r1, length);
            r1 += length;
        }
        return bArr;
    }

    private final byte[] zzO(int r6) throws IOException {
        if (r6 == 0) {
            return zzgox.zzd;
        }
        if (r6 >= 0) {
            int r0 = this.zzk;
            int r1 = this.zzi;
            int r2 = r0 + r1 + r6;
            if (C1856C.RATE_UNSET_INT + r2 <= 0) {
                int r3 = this.zzl;
                if (r2 > r3) {
                    zzB((r3 - r0) - r1);
                    throw zzgoz.zzj();
                }
                int r02 = this.zzg - r1;
                int r12 = r6 - r02;
                if (r12 >= 4096) {
                    try {
                        if (r12 > this.zze.available()) {
                            return null;
                        }
                    } catch (zzgoz e) {
                        e.zzk();
                        throw e;
                    }
                }
                byte[] bArr = new byte[r6];
                System.arraycopy(this.zzf, this.zzi, bArr, 0, r02);
                this.zzk += this.zzg;
                this.zzi = 0;
                this.zzg = 0;
                while (r02 < r6) {
                    try {
                        int read = this.zze.read(bArr, r02, r6 - r02);
                        if (read == -1) {
                            throw zzgoz.zzj();
                        }
                        this.zzk += read;
                        r02 += read;
                    } catch (zzgoz e2) {
                        e2.zzk();
                        throw e2;
                    }
                }
                return bArr;
            }
            throw zzgoz.zzi();
        }
        throw zzgoz.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final void zzA(int r1) {
        this.zzl = r1;
        zzK();
    }

    public final void zzB(int r9) throws IOException {
        int r0 = this.zzg;
        int r1 = this.zzi;
        int r02 = r0 - r1;
        if (r9 <= r02 && r9 >= 0) {
            this.zzi = r1 + r9;
        } else if (r9 >= 0) {
            int r2 = this.zzk;
            int r3 = r2 + r1;
            int r4 = this.zzl;
            if (r3 + r9 > r4) {
                zzB((r4 - r2) - r1);
                throw zzgoz.zzj();
            }
            this.zzk = r3;
            this.zzg = 0;
            this.zzi = 0;
            while (r02 < r9) {
                try {
                    long j = r9 - r02;
                    try {
                        long skip = this.zze.skip(j);
                        int r7 = (skip > 0L ? 1 : (skip == 0L ? 0 : -1));
                        if (r7 < 0 || skip > j) {
                            throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                        } else if (r7 == 0) {
                            break;
                        } else {
                            r02 += (int) skip;
                        }
                    } catch (zzgoz e) {
                        e.zzk();
                        throw e;
                    }
                } finally {
                    this.zzk += r02;
                    zzK();
                }
            }
            if (r02 >= r9) {
                return;
            }
            int r03 = this.zzg;
            int r12 = r03 - this.zzi;
            this.zzi = r03;
            zzL(1);
            while (true) {
                int r22 = r9 - r12;
                int r32 = this.zzg;
                if (r22 <= r32) {
                    this.zzi = r22;
                    return;
                }
                r12 += r32;
                this.zzi = r32;
                zzL(1);
            }
        } else {
            throw zzgoz.zzf();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzC() throws IOException {
        return this.zzi == this.zzg && !zzM(1);
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        if (this.zzi == this.zzg) {
            zzL(1);
        }
        byte[] bArr = this.zzf;
        int r1 = this.zzi;
        this.zzi = r1 + 1;
        return bArr[r1];
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzd() {
        return this.zzk + this.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zze(int r3) throws zzgoz {
        if (r3 >= 0) {
            int r32 = r3 + this.zzk + this.zzi;
            int r0 = this.zzl;
            if (r32 <= r0) {
                this.zzl = r32;
                zzK();
                return r0;
            }
            throw zzgoz.zzj();
        }
        throw zzgoz.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzf() throws IOException {
        return zzj();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzg() throws IOException {
        return zzi();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzh() throws IOException {
        return zzj();
    }

    public final int zzi() throws IOException {
        int r0 = this.zzi;
        if (this.zzg - r0 < 4) {
            zzL(4);
            r0 = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = r0 + 4;
        return ((bArr[r0 + 3] & 255) << 24) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16);
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzk() throws IOException {
        return zzi();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzl() throws IOException {
        return zzF(zzj());
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzm() throws IOException {
        if (zzC()) {
            this.zzj = 0;
            return 0;
        }
        int zzj = zzj();
        this.zzj = zzj;
        if ((zzj >>> 3) != 0) {
            return zzj;
        }
        throw zzgoz.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zzn() throws IOException {
        return zzj();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final long zzo() throws IOException {
        return zzq();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final long zzp() throws IOException {
        return zzr();
    }

    public final long zzq() throws IOException {
        int r0 = this.zzi;
        if (this.zzg - r0 < 8) {
            zzL(8);
            r0 = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = r0 + 8;
        return ((bArr[r0 + 7] & 255) << 56) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16) | ((bArr[r0 + 3] & 255) << 24) | ((bArr[r0 + 4] & 255) << 32) | ((bArr[r0 + 5] & 255) << 40) | ((bArr[r0 + 6] & 255) << 48);
    }

    final long zzs() throws IOException {
        long j = 0;
        for (int r2 = 0; r2 < 64; r2 += 7) {
            byte zza = zza();
            j |= (zza & Byte.MAX_VALUE) << r2;
            if ((zza & 128) == 0) {
                return j;
            }
        }
        throw zzgoz.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final long zzt() throws IOException {
        return zzq();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final long zzu() throws IOException {
        return zzG(zzr());
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final long zzv() throws IOException {
        return zzr();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final zzgnf zzw() throws IOException {
        int zzj = zzj();
        int r1 = this.zzg;
        int r2 = this.zzi;
        if (zzj <= r1 - r2 && zzj > 0) {
            zzgnf zzw = zzgnf.zzw(this.zzf, r2, zzj);
            this.zzi += zzj;
            return zzw;
        } else if (zzj != 0) {
            byte[] zzO = zzO(zzj);
            if (zzO != null) {
                return zzgnf.zzv(zzO);
            }
            int r12 = this.zzi;
            int r22 = this.zzg;
            int r3 = r22 - r12;
            this.zzk += r22;
            this.zzi = 0;
            this.zzg = 0;
            List<byte[]> zzJ = zzJ(zzj - r3);
            byte[] bArr = new byte[zzj];
            System.arraycopy(this.zzf, r12, bArr, 0, r3);
            for (byte[] bArr2 : zzJ) {
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, r3, length);
                r3 += length;
            }
            return zzgnf.zzz(bArr);
        } else {
            return zzgnf.zzb;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final String zzx() throws IOException {
        int zzj = zzj();
        if (zzj > 0) {
            int r1 = this.zzg;
            int r2 = this.zzi;
            if (zzj <= r1 - r2) {
                String str = new String(this.zzf, r2, zzj, zzgox.zzb);
                this.zzi += zzj;
                return str;
            }
        }
        if (zzj == 0) {
            return "";
        }
        if (zzj <= this.zzg) {
            zzL(zzj);
            String str2 = new String(this.zzf, this.zzi, zzj, zzgox.zzb);
            this.zzi += zzj;
            return str2;
        }
        return new String(zzN(zzj, false), zzgox.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final String zzy() throws IOException {
        byte[] zzN;
        int zzj = zzj();
        int r1 = this.zzi;
        int r2 = this.zzg;
        if (zzj <= r2 - r1 && zzj > 0) {
            zzN = this.zzf;
            this.zzi = r1 + zzj;
        } else if (zzj == 0) {
            return "";
        } else {
            if (zzj <= r2) {
                zzL(zzj);
                zzN = this.zzf;
                this.zzi = zzj;
            } else {
                zzN = zzN(zzj, false);
            }
            r1 = 0;
        }
        return zzgrw.zzh(zzN, r1, zzj);
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final void zzz(int r2) throws zzgoz {
        if (this.zzj != r2) {
            throw zzgoz.zzb();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
        if (r2[r3] >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzj() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.zzi
            int r1 = r5.zzg
            if (r1 != r0) goto L7
            goto L6c
        L7:
            byte[] r2 = r5.zzf
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r5.zzi = r3
            return r0
        L12:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6c
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L23
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L69
        L23:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L30
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2e:
            r1 = r3
            goto L69
        L30:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3e
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L69
        L3e:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2e
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L69
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2e
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L69
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2e
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6c
        L69:
            r5.zzi = r1
            return r0
        L6c:
            long r0 = r5.zzs()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgnl.zzj():int");
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzE(int r6) throws IOException {
        int zzm;
        int r0 = r6 & 7;
        int r1 = 0;
        if (r0 == 0) {
            if (this.zzg - this.zzi < 10) {
                while (r1 < 10) {
                    if (zza() < 0) {
                        r1++;
                    }
                }
                throw zzgoz.zze();
            }
            while (r1 < 10) {
                byte[] bArr = this.zzf;
                int r3 = this.zzi;
                this.zzi = r3 + 1;
                if (bArr[r3] < 0) {
                    r1++;
                }
            }
            throw zzgoz.zze();
            return true;
        } else if (r0 == 1) {
            zzB(8);
            return true;
        } else if (r0 == 2) {
            zzB(zzj());
            return true;
        } else if (r0 != 3) {
            if (r0 != 4) {
                if (r0 == 5) {
                    zzB(4);
                    return true;
                }
                throw zzgoz.zza();
            }
            return false;
        } else {
            do {
                zzm = zzm();
                if (zzm == 0) {
                    break;
                }
            } while (zzE(zzm));
            zzz(((r6 >>> 3) << 3) | 4);
            return true;
        }
    }

    public final long zzr() throws IOException {
        long j;
        long j2;
        long j3;
        long j4;
        int r0;
        int r02 = this.zzi;
        int r1 = this.zzg;
        if (r1 != r02) {
            byte[] bArr = this.zzf;
            int r3 = r02 + 1;
            byte b = bArr[r02];
            if (b >= 0) {
                this.zzi = r3;
                return b;
            } else if (r1 - r3 >= 9) {
                int r12 = r3 + 1;
                int r03 = b ^ (bArr[r3] << 7);
                if (r03 >= 0) {
                    int r32 = r12 + 1;
                    int r04 = r03 ^ (bArr[r12] << Ascii.f1129SO);
                    if (r04 >= 0) {
                        j = r04 ^ 16256;
                    } else {
                        r12 = r32 + 1;
                        int r05 = r04 ^ (bArr[r32] << Ascii.NAK);
                        if (r05 < 0) {
                            r0 = r05 ^ (-2080896);
                        } else {
                            r32 = r12 + 1;
                            long j5 = (bArr[r12] << 28) ^ r05;
                            if (j5 < 0) {
                                int r6 = r32 + 1;
                                long j6 = j5 ^ (bArr[r32] << 35);
                                if (j6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    r32 = r6 + 1;
                                    j5 = j6 ^ (bArr[r6] << 42);
                                    if (j5 >= 0) {
                                        j4 = 4363953127296L;
                                    } else {
                                        r6 = r32 + 1;
                                        j6 = j5 ^ (bArr[r32] << 49);
                                        if (j6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            r32 = r6 + 1;
                                            j = (j6 ^ (bArr[r6] << 56)) ^ 71499008037633920L;
                                            if (j < 0) {
                                                r6 = r32 + 1;
                                                if (bArr[r32] >= 0) {
                                                    j2 = j;
                                                    r12 = r6;
                                                    this.zzi = r12;
                                                    return j2;
                                                }
                                            }
                                        }
                                    }
                                }
                                j2 = j3 ^ j6;
                                r12 = r6;
                                this.zzi = r12;
                                return j2;
                            }
                            j4 = 266354560;
                            j = j5 ^ j4;
                        }
                    }
                    r12 = r32;
                    j2 = j;
                    this.zzi = r12;
                    return j2;
                }
                r0 = r03 ^ (-128);
                j2 = r0;
                this.zzi = r12;
                return j2;
            }
        }
        return zzs();
    }
}
