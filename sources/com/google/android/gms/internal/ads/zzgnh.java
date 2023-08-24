package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgnh extends zzgnn {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private final int zzi;
    private int zzj;
    private int zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgnh(byte[] bArr, int r2, int r3, boolean z, zzgng zzgngVar) {
        super(null);
        this.zzk = Integer.MAX_VALUE;
        this.zze = bArr;
        this.zzf = r3 + r2;
        this.zzh = r2;
        this.zzi = r2;
    }

    private final void zzJ() {
        int r0 = this.zzf + this.zzg;
        this.zzf = r0;
        int r1 = r0 - this.zzi;
        int r2 = this.zzk;
        if (r1 <= r2) {
            this.zzg = 0;
            return;
        }
        int r12 = r1 - r2;
        this.zzg = r12;
        this.zzf = r0 - r12;
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final void zzA(int r1) {
        this.zzk = r1;
        zzJ();
    }

    public final void zzB(int r3) throws IOException {
        if (r3 >= 0) {
            int r0 = this.zzf;
            int r1 = this.zzh;
            if (r3 <= r0 - r1) {
                this.zzh = r1 + r3;
                return;
            }
        }
        if (r3 < 0) {
            throw zzgoz.zzf();
        }
        throw zzgoz.zzj();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzC() throws IOException {
        return this.zzh == this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        int r0 = this.zzh;
        if (r0 == this.zzf) {
            throw zzgoz.zzj();
        }
        byte[] bArr = this.zze;
        this.zzh = r0 + 1;
        return bArr[r0];
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
        return this.zzh - this.zzi;
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zze(int r3) throws zzgoz {
        if (r3 >= 0) {
            int r32 = r3 + (this.zzh - this.zzi);
            if (r32 >= 0) {
                int r0 = this.zzk;
                if (r32 <= r0) {
                    this.zzk = r32;
                    zzJ();
                    return r0;
                }
                throw zzgoz.zzj();
            }
            throw zzgoz.zzg();
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
        int r0 = this.zzh;
        if (this.zzf - r0 < 4) {
            throw zzgoz.zzj();
        }
        byte[] bArr = this.zze;
        this.zzh = r0 + 4;
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
        int r0 = this.zzh;
        if (this.zzf - r0 < 8) {
            throw zzgoz.zzj();
        }
        byte[] bArr = this.zze;
        this.zzh = r0 + 8;
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
        if (zzj > 0) {
            int r1 = this.zzf;
            int r2 = this.zzh;
            if (zzj <= r1 - r2) {
                zzgnf zzw = zzgnf.zzw(this.zze, r2, zzj);
                this.zzh += zzj;
                return zzw;
            }
        }
        if (zzj != 0) {
            if (zzj > 0) {
                int r12 = this.zzf;
                int r22 = this.zzh;
                if (zzj <= r12 - r22) {
                    int r0 = zzj + r22;
                    this.zzh = r0;
                    return zzgnf.zzz(Arrays.copyOfRange(this.zze, r22, r0));
                }
            }
            if (zzj <= 0) {
                throw zzgoz.zzf();
            }
            throw zzgoz.zzj();
        }
        return zzgnf.zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final String zzx() throws IOException {
        int zzj = zzj();
        if (zzj > 0) {
            int r1 = this.zzf;
            int r2 = this.zzh;
            if (zzj <= r1 - r2) {
                String str = new String(this.zze, r2, zzj, zzgox.zzb);
                this.zzh += zzj;
                return str;
            }
        }
        if (zzj == 0) {
            return "";
        }
        if (zzj < 0) {
            throw zzgoz.zzf();
        }
        throw zzgoz.zzj();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final String zzy() throws IOException {
        int zzj = zzj();
        if (zzj > 0) {
            int r1 = this.zzf;
            int r2 = this.zzh;
            if (zzj <= r1 - r2) {
                String zzh = zzgrw.zzh(this.zze, r2, zzj);
                this.zzh += zzj;
                return zzh;
            }
        }
        if (zzj == 0) {
            return "";
        }
        if (zzj <= 0) {
            throw zzgoz.zzf();
        }
        throw zzgoz.zzj();
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
            int r0 = r5.zzh
            int r1 = r5.zzf
            if (r1 != r0) goto L7
            goto L6c
        L7:
            byte[] r2 = r5.zze
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L12
            r5.zzh = r3
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
            r5.zzh = r1
            return r0
        L6c:
            long r0 = r5.zzs()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgnh.zzj():int");
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzE(int r6) throws IOException {
        int zzm;
        int r0 = r6 & 7;
        int r1 = 0;
        if (r0 == 0) {
            if (this.zzf - this.zzh < 10) {
                while (r1 < 10) {
                    if (zza() < 0) {
                        r1++;
                    }
                }
                throw zzgoz.zze();
            }
            while (r1 < 10) {
                byte[] bArr = this.zze;
                int r3 = this.zzh;
                this.zzh = r3 + 1;
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
        int r02 = this.zzh;
        int r1 = this.zzf;
        if (r1 != r02) {
            byte[] bArr = this.zze;
            int r3 = r02 + 1;
            byte b = bArr[r02];
            if (b >= 0) {
                this.zzh = r3;
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
                                                    this.zzh = r12;
                                                    return j2;
                                                }
                                            }
                                        }
                                    }
                                }
                                j2 = j3 ^ j6;
                                r12 = r6;
                                this.zzh = r12;
                                return j2;
                            }
                            j4 = 266354560;
                            j = j5 ^ j4;
                        }
                    }
                    r12 = r32;
                    j2 = j;
                    this.zzh = r12;
                    return j2;
                }
                r0 = r03 ^ (-128);
                j2 = r0;
                this.zzh = r12;
                return j2;
            }
        }
        return zzs();
    }
}
