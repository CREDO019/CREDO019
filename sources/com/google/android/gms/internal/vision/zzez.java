package com.google.android.gms.internal.vision;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzez {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r2, zzfb zzfbVar) {
        int r0 = r2 + 1;
        byte b = bArr[r2];
        if (b >= 0) {
            zzfbVar.zzro = b;
            return r0;
        }
        return zza(b, bArr, r0, zzfbVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r1, byte[] bArr, int r3, zzfb zzfbVar) {
        int r12 = r1 & 127;
        int r0 = r3 + 1;
        byte b = bArr[r3];
        if (b >= 0) {
            zzfbVar.zzro = r12 | (b << 7);
            return r0;
        }
        int r13 = r12 | ((b & Byte.MAX_VALUE) << 7);
        int r32 = r0 + 1;
        byte b2 = bArr[r0];
        if (b2 >= 0) {
            zzfbVar.zzro = r13 | (b2 << Ascii.f1129SO);
            return r32;
        }
        int r14 = r13 | ((b2 & Byte.MAX_VALUE) << 14);
        int r02 = r32 + 1;
        byte b3 = bArr[r32];
        if (b3 >= 0) {
            zzfbVar.zzro = r14 | (b3 << Ascii.NAK);
            return r02;
        }
        int r15 = r14 | ((b3 & Byte.MAX_VALUE) << 21);
        int r33 = r02 + 1;
        byte b4 = bArr[r02];
        if (b4 >= 0) {
            zzfbVar.zzro = r15 | (b4 << Ascii.f1122FS);
            return r33;
        }
        int r16 = r15 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int r03 = r33 + 1;
            if (bArr[r33] >= 0) {
                zzfbVar.zzro = r16;
                return r03;
            }
            r33 = r03;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int r10, zzfb zzfbVar) {
        byte b;
        int r0 = r10 + 1;
        long j = bArr[r10];
        if (j >= 0) {
            zzfbVar.zzrp = j;
            return r0;
        }
        int r102 = r0 + 1;
        byte b2 = bArr[r0];
        long j2 = (j & 127) | ((b2 & Byte.MAX_VALUE) << 7);
        int r3 = 7;
        while (b2 < 0) {
            int r02 = r102 + 1;
            r3 += 7;
            j2 |= (b & Byte.MAX_VALUE) << r3;
            b2 = bArr[r102];
            r102 = r02;
        }
        zzfbVar.zzrp = j2;
        return r102;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r3) {
        return ((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzb(byte[] bArr, int r8) {
        return ((bArr[r8 + 7] & 255) << 56) | (bArr[r8] & 255) | ((bArr[r8 + 1] & 255) << 8) | ((bArr[r8 + 2] & 255) << 16) | ((bArr[r8 + 3] & 255) << 24) | ((bArr[r8 + 4] & 255) << 32) | ((bArr[r8 + 5] & 255) << 40) | ((bArr[r8 + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzc(byte[] bArr, int r1) {
        return Double.longBitsToDouble(zzb(bArr, r1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzd(byte[] bArr, int r1) {
        return Float.intBitsToFloat(zza(bArr, r1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int r4, zzfb zzfbVar) throws zzhc {
        int zza = zza(bArr, r4, zzfbVar);
        int r0 = zzfbVar.zzro;
        if (r0 >= 0) {
            if (r0 == 0) {
                zzfbVar.zzrq = "";
                return zza;
            }
            zzfbVar.zzrq = new String(bArr, zza, r0, zzgt.UTF_8);
            return zza + r0;
        }
        throw zzhc.zzgn();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(byte[] bArr, int r2, zzfb zzfbVar) throws zzhc {
        int zza = zza(bArr, r2, zzfbVar);
        int r0 = zzfbVar.zzro;
        if (r0 >= 0) {
            if (r0 == 0) {
                zzfbVar.zzrq = "";
                return zza;
            }
            zzfbVar.zzrq = zzjs.zzh(bArr, zza, r0);
            return zza + r0;
        }
        throw zzhc.zzgn();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(byte[] bArr, int r3, zzfb zzfbVar) throws zzhc {
        int zza = zza(bArr, r3, zzfbVar);
        int r0 = zzfbVar.zzro;
        if (r0 < 0) {
            throw zzhc.zzgn();
        }
        if (r0 <= bArr.length - zza) {
            if (r0 == 0) {
                zzfbVar.zzrq = zzfh.zzrx;
                return zza;
            }
            zzfbVar.zzrq = zzfh.zza(bArr, zza, r0);
            return zza + r0;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzir zzirVar, byte[] bArr, int r8, int r9, zzfb zzfbVar) throws IOException {
        int r0 = r8 + 1;
        int r82 = bArr[r8];
        if (r82 < 0) {
            r0 = zza(r82, bArr, r0, zzfbVar);
            r82 = zzfbVar.zzro;
        }
        int r3 = r0;
        if (r82 < 0 || r82 > r9 - r3) {
            throw zzhc.zzgm();
        }
        Object newInstance = zzirVar.newInstance();
        int r83 = r82 + r3;
        zzirVar.zza(newInstance, bArr, r3, r83, zzfbVar);
        zzirVar.zzg(newInstance);
        zzfbVar.zzrq = newInstance;
        return r83;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzir zzirVar, byte[] bArr, int r10, int r11, int r12, zzfb zzfbVar) throws IOException {
        zzig zzigVar = (zzig) zzirVar;
        Object newInstance = zzigVar.newInstance();
        int zza = zzigVar.zza((zzig) newInstance, bArr, r10, r11, r12, zzfbVar);
        zzigVar.zzg(newInstance);
        zzfbVar.zzrq = newInstance;
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r2, byte[] bArr, int r4, int r5, zzgz<?> zzgzVar, zzfb zzfbVar) {
        zzgu zzguVar = (zzgu) zzgzVar;
        int zza = zza(bArr, r4, zzfbVar);
        zzguVar.zzbl(zzfbVar.zzro);
        while (zza < r5) {
            int zza2 = zza(bArr, zza, zzfbVar);
            if (r2 != zzfbVar.zzro) {
                break;
            }
            zza = zza(bArr, zza2, zzfbVar);
            zzguVar.zzbl(zzfbVar.zzro);
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r3, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzgu zzguVar = (zzgu) zzgzVar;
        int zza = zza(bArr, r3, zzfbVar);
        int r0 = zzfbVar.zzro + zza;
        while (zza < r0) {
            zza = zza(bArr, zza, zzfbVar);
            zzguVar.zzbl(zzfbVar.zzro);
        }
        if (zza == r0) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int r4, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzhq zzhqVar = (zzhq) zzgzVar;
        int zza = zza(bArr, r4, zzfbVar);
        int r0 = zzfbVar.zzro + zza;
        while (zza < r0) {
            zza = zzb(bArr, zza, zzfbVar);
            zzhqVar.zzac(zzfbVar.zzrp);
        }
        if (zza == r0) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int r2, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzgu zzguVar = (zzgu) zzgzVar;
        int zza = zza(bArr, r2, zzfbVar);
        int r4 = zzfbVar.zzro + zza;
        while (zza < r4) {
            zzguVar.zzbl(zza(bArr, zza));
            zza += 4;
        }
        if (zza == r4) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(byte[] bArr, int r3, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzhq zzhqVar = (zzhq) zzgzVar;
        int zza = zza(bArr, r3, zzfbVar);
        int r5 = zzfbVar.zzro + zza;
        while (zza < r5) {
            zzhqVar.zzac(zzb(bArr, zza));
            zza += 8;
        }
        if (zza == r5) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(byte[] bArr, int r2, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzgo zzgoVar = (zzgo) zzgzVar;
        int zza = zza(bArr, r2, zzfbVar);
        int r4 = zzfbVar.zzro + zza;
        while (zza < r4) {
            zzgoVar.zzu(zzd(bArr, zza));
            zza += 4;
        }
        if (zza == r4) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int r3, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzgb zzgbVar = (zzgb) zzgzVar;
        int zza = zza(bArr, r3, zzfbVar);
        int r5 = zzfbVar.zzro + zza;
        while (zza < r5) {
            zzgbVar.zzc(zzc(bArr, zza));
            zza += 8;
        }
        if (zza == r5) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(byte[] bArr, int r7, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzff zzffVar = (zzff) zzgzVar;
        int zza = zza(bArr, r7, zzfbVar);
        int r0 = zzfbVar.zzro + zza;
        while (zza < r0) {
            zza = zzb(bArr, zza, zzfbVar);
            zzffVar.addBoolean(zzfbVar.zzrp != 0);
        }
        if (zza == r0) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(byte[] bArr, int r3, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzgu zzguVar = (zzgu) zzgzVar;
        int zza = zza(bArr, r3, zzfbVar);
        int r0 = zzfbVar.zzro + zza;
        while (zza < r0) {
            zza = zza(bArr, zza, zzfbVar);
            zzguVar.zzbl(zzft.zzau(zzfbVar.zzro));
        }
        if (zza == r0) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(byte[] bArr, int r4, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        zzhq zzhqVar = (zzhq) zzgzVar;
        int zza = zza(bArr, r4, zzfbVar);
        int r0 = zzfbVar.zzro + zza;
        while (zza < r0) {
            zza = zzb(bArr, zza, zzfbVar);
            zzhqVar.zzac(zzft.zzr(zzfbVar.zzrp));
        }
        if (zza == r0) {
            return zza;
        }
        throw zzhc.zzgm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(zzir<?> zzirVar, int r3, byte[] bArr, int r5, int r6, zzgz<?> zzgzVar, zzfb zzfbVar) throws IOException {
        int zza = zza(zzirVar, bArr, r5, r6, zzfbVar);
        zzgzVar.add(zzfbVar.zzrq);
        while (zza < r6) {
            int zza2 = zza(bArr, zza, zzfbVar);
            if (r3 != zzfbVar.zzro) {
                break;
            }
            zza = zza(zzirVar, bArr, zza2, r6, zzfbVar);
            zzgzVar.add(zzfbVar.zzrq);
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r9, byte[] bArr, int r11, int r12, zzjm zzjmVar, zzfb zzfbVar) throws zzhc {
        if ((r9 >>> 3) != 0) {
            int r0 = r9 & 7;
            if (r0 == 0) {
                int zzb = zzb(bArr, r11, zzfbVar);
                zzjmVar.zzb(r9, Long.valueOf(zzfbVar.zzrp));
                return zzb;
            } else if (r0 == 1) {
                zzjmVar.zzb(r9, Long.valueOf(zzb(bArr, r11)));
                return r11 + 8;
            } else if (r0 == 2) {
                int zza = zza(bArr, r11, zzfbVar);
                int r122 = zzfbVar.zzro;
                if (r122 < 0) {
                    throw zzhc.zzgn();
                }
                if (r122 <= bArr.length - zza) {
                    if (r122 == 0) {
                        zzjmVar.zzb(r9, zzfh.zzrx);
                    } else {
                        zzjmVar.zzb(r9, zzfh.zza(bArr, zza, r122));
                    }
                    return zza + r122;
                }
                throw zzhc.zzgm();
            } else if (r0 != 3) {
                if (r0 == 5) {
                    zzjmVar.zzb(r9, Integer.valueOf(zza(bArr, r11)));
                    return r11 + 4;
                }
                throw zzhc.zzgp();
            } else {
                zzjm zzih = zzjm.zzih();
                int r7 = (r9 & (-8)) | 4;
                int r02 = 0;
                while (true) {
                    if (r11 >= r12) {
                        break;
                    }
                    int zza2 = zza(bArr, r11, zzfbVar);
                    int r112 = zzfbVar.zzro;
                    r02 = r112;
                    if (r112 == r7) {
                        r11 = zza2;
                        break;
                    }
                    int zza3 = zza(r02, bArr, zza2, r12, zzih, zzfbVar);
                    r02 = r112;
                    r11 = zza3;
                }
                if (r11 > r12 || r02 != r7) {
                    throw zzhc.zzgs();
                }
                zzjmVar.zzb(r9, zzih);
                return r11;
            }
        }
        throw zzhc.zzgp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r2, byte[] bArr, int r4, int r5, zzfb zzfbVar) throws zzhc {
        if ((r2 >>> 3) != 0) {
            int r0 = r2 & 7;
            if (r0 != 0) {
                if (r0 != 1) {
                    if (r0 != 2) {
                        if (r0 != 3) {
                            if (r0 == 5) {
                                return r4 + 4;
                            }
                            throw zzhc.zzgp();
                        }
                        int r22 = (r2 & (-8)) | 4;
                        int r02 = 0;
                        while (r4 < r5) {
                            r4 = zza(bArr, r4, zzfbVar);
                            r02 = zzfbVar.zzro;
                            if (r02 == r22) {
                                break;
                            }
                            r4 = zza(r02, bArr, r4, r5, zzfbVar);
                        }
                        if (r4 > r5 || r02 != r22) {
                            throw zzhc.zzgs();
                        }
                        return r4;
                    }
                    return zza(bArr, r4, zzfbVar) + zzfbVar.zzro;
                }
                return r4 + 8;
            }
            return zzb(bArr, r4, zzfbVar);
        }
        throw zzhc.zzgp();
    }
}
