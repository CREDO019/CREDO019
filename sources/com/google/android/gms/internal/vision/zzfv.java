package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzfv extends zzft {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzsk;
    private int zzsl;
    private int zzsm;
    private int zzsn;
    private int zzso;

    private zzfv(byte[] bArr, int r3, int r4, boolean z) {
        super();
        this.zzso = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = r4 + r3;
        this.pos = r3;
        this.zzsm = r3;
        this.zzsk = z;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzex() throws IOException {
        if (zzdt()) {
            this.zzsn = 0;
            return 0;
        }
        int zzfa = zzfa();
        this.zzsn = zzfa;
        if ((zzfa >>> 3) != 0) {
            return zzfa;
        }
        throw zzhc.zzgp();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final void zzaq(int r2) throws zzhc {
        if (this.zzsn != r2) {
            throw zzhc.zzgq();
        }
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final boolean zzar(int r6) throws IOException {
        int zzex;
        int r0 = r6 & 7;
        int r1 = 0;
        if (r0 == 0) {
            if (this.limit - this.pos >= 10) {
                while (r1 < 10) {
                    byte[] bArr = this.buffer;
                    int r3 = this.pos;
                    this.pos = r3 + 1;
                    if (bArr[r3] < 0) {
                        r1++;
                    }
                }
                throw zzhc.zzgo();
            }
            while (r1 < 10) {
                if (zzff() < 0) {
                    r1++;
                }
            }
            throw zzhc.zzgo();
            return true;
        } else if (r0 == 1) {
            zzav(8);
            return true;
        } else if (r0 == 2) {
            zzav(zzfa());
            return true;
        } else if (r0 != 3) {
            if (r0 != 4) {
                if (r0 == 5) {
                    zzav(4);
                    return true;
                }
                throw zzhc.zzgr();
            }
            return false;
        } else {
            do {
                zzex = zzex();
                if (zzex == 0) {
                    break;
                }
            } while (zzar(zzex));
            zzaq(((r6 >>> 3) << 3) | 4);
            return true;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzfd());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzfc());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzdw() throws IOException {
        return zzfb();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzdx() throws IOException {
        return zzfb();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzdy() throws IOException {
        return zzfa();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzdz() throws IOException {
        return zzfd();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzea() throws IOException {
        return zzfc();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final boolean zzeb() throws IOException {
        return zzfb() != 0;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final String readString() throws IOException {
        int zzfa = zzfa();
        if (zzfa > 0) {
            int r1 = this.limit;
            int r2 = this.pos;
            if (zzfa <= r1 - r2) {
                String str = new String(this.buffer, r2, zzfa, zzgt.UTF_8);
                this.pos += zzfa;
                return str;
            }
        }
        if (zzfa == 0) {
            return "";
        }
        if (zzfa < 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final String zzec() throws IOException {
        int zzfa = zzfa();
        if (zzfa > 0) {
            int r1 = this.limit;
            int r2 = this.pos;
            if (zzfa <= r1 - r2) {
                String zzh = zzjs.zzh(this.buffer, r2, zzfa);
                this.pos += zzfa;
                return zzh;
            }
        }
        if (zzfa == 0) {
            return "";
        }
        if (zzfa <= 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final zzfh zzed() throws IOException {
        byte[] bArr;
        int zzfa = zzfa();
        if (zzfa > 0) {
            int r1 = this.limit;
            int r2 = this.pos;
            if (zzfa <= r1 - r2) {
                zzfh zza = zzfh.zza(this.buffer, r2, zzfa);
                this.pos += zzfa;
                return zza;
            }
        }
        if (zzfa == 0) {
            return zzfh.zzrx;
        }
        if (zzfa > 0) {
            int r12 = this.limit;
            int r22 = this.pos;
            if (zzfa <= r12 - r22) {
                int r0 = zzfa + r22;
                this.pos = r0;
                bArr = Arrays.copyOfRange(this.buffer, r22, r0);
                return zzfh.zzd(bArr);
            }
        }
        if (zzfa <= 0) {
            if (zzfa == 0) {
                bArr = zzgt.zzxc;
                return zzfh.zzd(bArr);
            }
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzee() throws IOException {
        return zzfa();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzef() throws IOException {
        return zzfa();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzeg() throws IOException {
        return zzfc();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzeh() throws IOException {
        return zzfd();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzei() throws IOException {
        return zzau(zzfa());
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzej() throws IOException {
        return zzr(zzfb());
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
        if (r2[r3] >= 0) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzfa() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.pos
            int r1 = r5.limit
            if (r1 == r0) goto L6b
            byte[] r2 = r5.buffer
            int r3 = r0 + 1
            r0 = r2[r0]
            if (r0 < 0) goto L11
            r5.pos = r3
            return r0
        L11:
            int r1 = r1 - r3
            r4 = 9
            if (r1 < r4) goto L6b
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 7
            r0 = r0 ^ r3
            if (r0 >= 0) goto L22
            r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
            goto L68
        L22:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r1 = r1 << 14
            r0 = r0 ^ r1
            if (r0 < 0) goto L2f
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L2d:
            r1 = r3
            goto L68
        L2f:
            int r1 = r3 + 1
            r3 = r2[r3]
            int r3 = r3 << 21
            r0 = r0 ^ r3
            if (r0 >= 0) goto L3d
            r2 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L68
        L3d:
            int r3 = r1 + 1
            r1 = r2[r1]
            int r4 = r1 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r3 = r2[r3]
            if (r3 >= 0) goto L68
            int r3 = r1 + 1
            r1 = r2[r1]
            if (r1 >= 0) goto L2d
            int r1 = r3 + 1
            r2 = r2[r3]
            if (r2 < 0) goto L6b
        L68:
            r5.pos = r1
            return r0
        L6b:
            long r0 = r5.zzey()
            int r1 = (int) r0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfv.zzfa():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b0, code lost:
        if (r2[r0] >= 0) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final long zzfb() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 189
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzfv.zzfb():long");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzft
    public final long zzey() throws IOException {
        long j = 0;
        for (int r2 = 0; r2 < 64; r2 += 7) {
            byte zzff = zzff();
            j |= (zzff & Byte.MAX_VALUE) << r2;
            if ((zzff & 128) == 0) {
                return j;
            }
        }
        throw zzhc.zzgo();
    }

    private final int zzfc() throws IOException {
        int r0 = this.pos;
        if (this.limit - r0 < 4) {
            throw zzhc.zzgm();
        }
        byte[] bArr = this.buffer;
        this.pos = r0 + 4;
        return ((bArr[r0 + 3] & 255) << 24) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16);
    }

    private final long zzfd() throws IOException {
        int r0 = this.pos;
        if (this.limit - r0 < 8) {
            throw zzhc.zzgm();
        }
        byte[] bArr = this.buffer;
        this.pos = r0 + 8;
        return ((bArr[r0 + 7] & 255) << 56) | (bArr[r0] & 255) | ((bArr[r0 + 1] & 255) << 8) | ((bArr[r0 + 2] & 255) << 16) | ((bArr[r0 + 3] & 255) << 24) | ((bArr[r0 + 4] & 255) << 32) | ((bArr[r0 + 5] & 255) << 40) | ((bArr[r0 + 6] & 255) << 48);
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzas(int r2) throws zzhc {
        if (r2 < 0) {
            throw zzhc.zzgn();
        }
        int zzez = r2 + zzez();
        int r0 = this.zzso;
        if (zzez > r0) {
            throw zzhc.zzgm();
        }
        this.zzso = zzez;
        zzfe();
        return r0;
    }

    private final void zzfe() {
        int r0 = this.limit + this.zzsl;
        this.limit = r0;
        int r1 = r0 - this.zzsm;
        int r2 = this.zzso;
        if (r1 > r2) {
            int r12 = r1 - r2;
            this.zzsl = r12;
            this.limit = r0 - r12;
            return;
        }
        this.zzsl = 0;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final void zzat(int r1) {
        this.zzso = r1;
        zzfe();
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final boolean zzdt() throws IOException {
        return this.pos == this.limit;
    }

    @Override // com.google.android.gms.internal.vision.zzft
    public final int zzez() {
        return this.pos - this.zzsm;
    }

    private final byte zzff() throws IOException {
        int r0 = this.pos;
        if (r0 == this.limit) {
            throw zzhc.zzgm();
        }
        byte[] bArr = this.buffer;
        this.pos = r0 + 1;
        return bArr[r0];
    }

    private final void zzav(int r3) throws IOException {
        if (r3 >= 0) {
            int r0 = this.limit;
            int r1 = this.pos;
            if (r3 <= r0 - r1) {
                this.pos = r1 + r3;
                return;
            }
        }
        if (r3 < 0) {
            throw zzhc.zzgn();
        }
        throw zzhc.zzgm();
    }
}
