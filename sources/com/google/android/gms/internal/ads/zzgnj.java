package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgnj extends zzgnn {
    private final Iterable zze;
    private final Iterator zzf;
    private ByteBuffer zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private long zzm;
    private long zzn;
    private long zzo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgnj(Iterable iterable, int r2, boolean z, zzgni zzgniVar) {
        super(null);
        this.zzj = Integer.MAX_VALUE;
        this.zzh = r2;
        this.zze = iterable;
        this.zzf = iterable.iterator();
        this.zzl = 0;
        if (r2 == 0) {
            this.zzg = zzgox.zze;
            this.zzm = 0L;
            this.zzn = 0L;
            this.zzo = 0L;
            return;
        }
        zzN();
    }

    private final int zzJ() {
        return (int) (((this.zzh - this.zzl) - this.zzm) + this.zzn);
    }

    private final void zzK() throws zzgoz {
        if (!this.zzf.hasNext()) {
            throw zzgoz.zzj();
        }
        zzN();
    }

    private final void zzL(byte[] bArr, int r12, int r13) throws IOException {
        if (r13 > zzJ()) {
            if (r13 > 0) {
                throw zzgoz.zzj();
            }
            return;
        }
        int r122 = r13;
        while (r122 > 0) {
            if (this.zzo - this.zzm == 0) {
                zzK();
            }
            int min = Math.min(r122, (int) (this.zzo - this.zzm));
            long j = min;
            zzgrr.zzo(this.zzm, bArr, r13 - r122, j);
            r122 -= min;
            this.zzm += j;
        }
    }

    private final void zzM() {
        int r0 = this.zzh + this.zzi;
        this.zzh = r0;
        int r1 = this.zzj;
        if (r0 <= r1) {
            this.zzi = 0;
            return;
        }
        int r12 = r0 - r1;
        this.zzi = r12;
        this.zzh = r0 - r12;
    }

    private final void zzN() {
        ByteBuffer byteBuffer = (ByteBuffer) this.zzf.next();
        this.zzg = byteBuffer;
        this.zzl += (int) (this.zzm - this.zzn);
        long position = byteBuffer.position();
        this.zzm = position;
        this.zzn = position;
        this.zzo = this.zzg.limit();
        long zze = zzgrr.zze(this.zzg);
        this.zzm += zze;
        this.zzn += zze;
        this.zzo += zze;
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final void zzA(int r1) {
        this.zzj = r1;
        zzM();
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzC() throws IOException {
        return (((long) this.zzl) + this.zzm) - this.zzn == ((long) this.zzh);
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        if (this.zzo - this.zzm == 0) {
            zzK();
        }
        long j = this.zzm;
        this.zzm = 1 + j;
        return zzgrr.zza(j);
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
        return (int) ((this.zzl + this.zzm) - this.zzn);
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final int zze(int r2) throws zzgoz {
        if (r2 >= 0) {
            int zzd = r2 + zzd();
            int r0 = this.zzj;
            if (zzd <= r0) {
                this.zzj = zzd;
                zzM();
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
        int zza;
        byte zza2;
        long j = this.zzo;
        long j2 = this.zzm;
        if (j - j2 >= 4) {
            this.zzm = 4 + j2;
            zza = (zzgrr.zza(j2) & 255) | ((zzgrr.zza(1 + j2) & 255) << 8) | ((zzgrr.zza(2 + j2) & 255) << 16);
            zza2 = zzgrr.zza(j2 + 3);
        } else {
            zza = (zza() & 255) | ((zza() & 255) << 8) | ((zza() & 255) << 16);
            zza2 = zza();
        }
        return zza | ((zza2 & 255) << 24);
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
            this.zzk = 0;
            return 0;
        }
        int zzj = zzj();
        this.zzk = zzj;
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
        long zza;
        byte zza2;
        long j = this.zzo;
        long j2 = this.zzm;
        if (j - j2 >= 8) {
            this.zzm = 8 + j2;
            zza = (zzgrr.zza(j2) & 255) | ((zzgrr.zza(1 + j2) & 255) << 8) | ((zzgrr.zza(2 + j2) & 255) << 16) | ((zzgrr.zza(3 + j2) & 255) << 24) | ((zzgrr.zza(4 + j2) & 255) << 32) | ((zzgrr.zza(5 + j2) & 255) << 40) | ((zzgrr.zza(6 + j2) & 255) << 48);
            zza2 = zzgrr.zza(j2 + 7);
        } else {
            zza = (zza() & 255) | ((zza() & 255) << 8) | ((zza() & 255) << 16) | ((zza() & 255) << 24) | ((zza() & 255) << 32) | ((zza() & 255) << 40) | ((zza() & 255) << 48);
            zza2 = zza();
        }
        return zza | ((zza2 & 255) << 56);
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
            long j = zzj;
            long j2 = this.zzo;
            long j3 = this.zzm;
            if (j <= j2 - j3) {
                byte[] bArr = new byte[zzj];
                zzgrr.zzo(j3, bArr, 0L, j);
                this.zzm += j;
                return zzgnf.zzz(bArr);
            }
        }
        if (zzj > 0 && zzj <= zzJ()) {
            byte[] bArr2 = new byte[zzj];
            zzL(bArr2, 0, zzj);
            return zzgnf.zzz(bArr2);
        } else if (zzj == 0) {
            return zzgnf.zzb;
        } else {
            if (zzj < 0) {
                throw zzgoz.zzf();
            }
            throw zzgoz.zzj();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final String zzx() throws IOException {
        int zzj = zzj();
        if (zzj > 0) {
            long j = zzj;
            long j2 = this.zzo;
            long j3 = this.zzm;
            if (j <= j2 - j3) {
                byte[] bArr = new byte[zzj];
                zzgrr.zzo(j3, bArr, 0L, j);
                String str = new String(bArr, zzgox.zzb);
                this.zzm += j;
                return str;
            }
        }
        if (zzj > 0 && zzj <= zzJ()) {
            byte[] bArr2 = new byte[zzj];
            zzL(bArr2, 0, zzj);
            return new String(bArr2, zzgox.zzb);
        } else if (zzj == 0) {
            return "";
        } else {
            if (zzj < 0) {
                throw zzgoz.zzf();
            }
            throw zzgoz.zzj();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final String zzy() throws IOException {
        int zzj = zzj();
        if (zzj > 0) {
            long j = zzj;
            long j2 = this.zzo;
            long j3 = this.zzm;
            if (j <= j2 - j3) {
                String zzg = zzgrw.zzg(this.zzg, (int) (j3 - this.zzn), zzj);
                this.zzm += j;
                return zzg;
            }
        }
        if (zzj >= 0 && zzj <= zzJ()) {
            byte[] bArr = new byte[zzj];
            zzL(bArr, 0, zzj);
            return zzgrw.zzh(bArr, 0, zzj);
        } else if (zzj == 0) {
            return "";
        } else {
            if (zzj <= 0) {
                throw zzgoz.zzf();
            }
            throw zzgoz.zzj();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final void zzz(int r2) throws zzgoz {
        if (this.zzk != r2) {
            throw zzgoz.zzb();
        }
    }

    public final void zzB(int r7) throws IOException {
        if (r7 < 0 || r7 > ((this.zzh - this.zzl) - this.zzm) + this.zzn) {
            if (r7 < 0) {
                throw zzgoz.zzf();
            }
            throw zzgoz.zzj();
        }
        while (r7 > 0) {
            if (this.zzo - this.zzm == 0) {
                zzK();
            }
            int min = Math.min(r7, (int) (this.zzo - this.zzm));
            r7 -= min;
            this.zzm += min;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgnn
    public final boolean zzE(int r6) throws IOException {
        int zzm;
        int r0 = r6 & 7;
        if (r0 == 0) {
            for (int r1 = 0; r1 < 10; r1++) {
                if (zza() >= 0) {
                    return true;
                }
            }
            throw zzgoz.zze();
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

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0087, code lost:
        if (com.google.android.gms.internal.ads.zzgrr.zza(r4) >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzj() throws java.io.IOException {
        /*
            r10 = this;
            long r0 = r10.zzm
            long r2 = r10.zzo
            int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r4 != 0) goto La
            goto L8c
        La:
            r2 = 1
            long r4 = r0 + r2
            byte r0 = com.google.android.gms.internal.ads.zzgrr.zza(r0)
            if (r0 < 0) goto L1a
            long r4 = r10.zzm
            long r4 = r4 + r2
            r10.zzm = r4
            return r0
        L1a:
            long r6 = r10.zzo
            long r8 = r10.zzm
            long r6 = r6 - r8
            r8 = 10
            int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r1 < 0) goto L8c
            long r6 = r4 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r4)
            int r1 = r1 << 7
            r0 = r0 ^ r1
            if (r0 >= 0) goto L33
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L89
        L33:
            long r4 = r6 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r6)
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L42
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L40:
            r6 = r4
            goto L89
        L42:
            long r6 = r4 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r4)
            int r1 = r1 << 21
            r0 = r0 ^ r1
            if (r0 >= 0) goto L52
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L89
        L52:
            long r4 = r6 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r6)
            int r6 = r1 << 28
            r0 = r0 ^ r6
            r6 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r6
            if (r1 >= 0) goto L40
            long r6 = r4 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r4)
            if (r1 >= 0) goto L89
            long r4 = r6 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r6)
            if (r1 >= 0) goto L40
            long r6 = r4 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r4)
            if (r1 >= 0) goto L89
            long r4 = r6 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r6)
            if (r1 >= 0) goto L40
            long r6 = r4 + r2
            byte r1 = com.google.android.gms.internal.ads.zzgrr.zza(r4)
            if (r1 < 0) goto L8c
        L89:
            r10.zzm = r6
            return r0
        L8c:
            long r0 = r10.zzs()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgnj.zzj():int");
    }

    public final long zzr() throws IOException {
        long zza;
        long j;
        long j2;
        int r0;
        long j3 = this.zzm;
        if (this.zzo != j3) {
            long j4 = j3 + 1;
            byte zza2 = zzgrr.zza(j3);
            if (zza2 >= 0) {
                this.zzm++;
                return zza2;
            } else if (this.zzo - this.zzm >= 10) {
                long j5 = j4 + 1;
                int zza3 = zza2 ^ (zzgrr.zza(j4) << 7);
                if (zza3 >= 0) {
                    long j6 = j5 + 1;
                    int zza4 = zza3 ^ (zzgrr.zza(j5) << Ascii.f1129SO);
                    if (zza4 >= 0) {
                        zza = zza4 ^ 16256;
                    } else {
                        j5 = j6 + 1;
                        int zza5 = zza4 ^ (zzgrr.zza(j6) << Ascii.NAK);
                        if (zza5 < 0) {
                            r0 = zza5 ^ (-2080896);
                        } else {
                            j6 = j5 + 1;
                            long zza6 = zza5 ^ (zzgrr.zza(j5) << 28);
                            if (zza6 < 0) {
                                long j7 = j6 + 1;
                                long zza7 = zza6 ^ (zzgrr.zza(j6) << 35);
                                if (zza7 < 0) {
                                    j = -34093383808L;
                                } else {
                                    j6 = j7 + 1;
                                    zza6 = zza7 ^ (zzgrr.zza(j7) << 42);
                                    if (zza6 >= 0) {
                                        j2 = 4363953127296L;
                                    } else {
                                        j7 = j6 + 1;
                                        zza7 = zza6 ^ (zzgrr.zza(j6) << 49);
                                        if (zza7 < 0) {
                                            j = -558586000294016L;
                                        } else {
                                            j6 = j7 + 1;
                                            zza = (zza7 ^ (zzgrr.zza(j7) << 56)) ^ 71499008037633920L;
                                            if (zza < 0) {
                                                long j8 = 1 + j6;
                                                if (zzgrr.zza(j6) >= 0) {
                                                    j5 = j8;
                                                    this.zzm = j5;
                                                    return zza;
                                                }
                                            }
                                        }
                                    }
                                }
                                zza = zza7 ^ j;
                                j5 = j7;
                                this.zzm = j5;
                                return zza;
                            }
                            j2 = 266354560;
                            zza = zza6 ^ j2;
                        }
                    }
                    j5 = j6;
                    this.zzm = j5;
                    return zza;
                }
                r0 = zza3 ^ (-128);
                zza = r0;
                this.zzm = j5;
                return zza;
            }
        }
        return zzs();
    }
}
