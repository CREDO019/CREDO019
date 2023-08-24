package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgms {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r3, zzgmr zzgmrVar) throws zzgoz {
        int zzj = zzj(bArr, r3, zzgmrVar);
        int r0 = zzgmrVar.zza;
        if (r0 < 0) {
            throw zzgoz.zzf();
        }
        if (r0 <= bArr.length - zzj) {
            if (r0 == 0) {
                zzgmrVar.zzc = zzgnf.zzb;
                return zzj;
            }
            zzgmrVar.zzc = zzgnf.zzw(bArr, zzj, r0);
            return zzj + r0;
        }
        throw zzgoz.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int r3) {
        return ((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(zzgqq zzgqqVar, byte[] bArr, int r10, int r11, int r12, zzgmr zzgmrVar) throws IOException {
        zzgqa zzgqaVar = (zzgqa) zzgqqVar;
        Object zze = zzgqaVar.zze();
        int zzc = zzgqaVar.zzc(zze, bArr, r10, r11, r12, zzgmrVar);
        zzgqaVar.zzf(zze);
        zzgmrVar.zzc = zze;
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(zzgqq zzgqqVar, byte[] bArr, int r8, int r9, zzgmr zzgmrVar) throws IOException {
        int r0 = r8 + 1;
        int r82 = bArr[r8];
        if (r82 < 0) {
            r0 = zzk(r82, bArr, r0, zzgmrVar);
            r82 = zzgmrVar.zza;
        }
        int r3 = r0;
        if (r82 < 0 || r82 > r9 - r3) {
            throw zzgoz.zzj();
        }
        Object zze = zzgqqVar.zze();
        int r83 = r82 + r3;
        zzgqqVar.zzi(zze, bArr, r3, r83, zzgmrVar);
        zzgqqVar.zzf(zze);
        zzgmrVar.zzc = zze;
        return r83;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(zzgqq zzgqqVar, int r3, byte[] bArr, int r5, int r6, zzgow zzgowVar, zzgmr zzgmrVar) throws IOException {
        int zzd = zzd(zzgqqVar, bArr, r5, r6, zzgmrVar);
        zzgowVar.add(zzgmrVar.zzc);
        while (zzd < r6) {
            int zzj = zzj(bArr, zzd, zzgmrVar);
            if (r3 != zzgmrVar.zza) {
                break;
            }
            zzd = zzd(zzgqqVar, bArr, zzj, r6, zzgmrVar);
            zzgowVar.add(zzgmrVar.zzc);
        }
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int r3, zzgow zzgowVar, zzgmr zzgmrVar) throws IOException {
        zzgoo zzgooVar = (zzgoo) zzgowVar;
        int zzj = zzj(bArr, r3, zzgmrVar);
        int r0 = zzgmrVar.zza + zzj;
        while (zzj < r0) {
            zzj = zzj(bArr, zzj, zzgmrVar);
            zzgooVar.zzh(zzgmrVar.zza);
        }
        if (zzj == r0) {
            return zzj;
        }
        throw zzgoz.zzj();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(byte[] bArr, int r4, zzgmr zzgmrVar) throws zzgoz {
        int zzj = zzj(bArr, r4, zzgmrVar);
        int r0 = zzgmrVar.zza;
        if (r0 >= 0) {
            if (r0 == 0) {
                zzgmrVar.zzc = "";
                return zzj;
            }
            zzgmrVar.zzc = new String(bArr, zzj, r0, zzgox.zzb);
            return zzj + r0;
        }
        throw zzgoz.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(byte[] bArr, int r2, zzgmr zzgmrVar) throws zzgoz {
        int zzj = zzj(bArr, r2, zzgmrVar);
        int r0 = zzgmrVar.zza;
        if (r0 >= 0) {
            if (r0 == 0) {
                zzgmrVar.zzc = "";
                return zzj;
            }
            zzgmrVar.zzc = zzgrw.zzh(bArr, zzj, r0);
            return zzj + r0;
        }
        throw zzgoz.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int r9, byte[] bArr, int r11, int r12, zzgri zzgriVar, zzgmr zzgmrVar) throws zzgoz {
        if ((r9 >>> 3) != 0) {
            int r0 = r9 & 7;
            if (r0 == 0) {
                int zzm = zzm(bArr, r11, zzgmrVar);
                zzgriVar.zzh(r9, Long.valueOf(zzgmrVar.zzb));
                return zzm;
            } else if (r0 == 1) {
                zzgriVar.zzh(r9, Long.valueOf(zzn(bArr, r11)));
                return r11 + 8;
            } else if (r0 == 2) {
                int zzj = zzj(bArr, r11, zzgmrVar);
                int r122 = zzgmrVar.zza;
                if (r122 < 0) {
                    throw zzgoz.zzf();
                }
                if (r122 <= bArr.length - zzj) {
                    if (r122 == 0) {
                        zzgriVar.zzh(r9, zzgnf.zzb);
                    } else {
                        zzgriVar.zzh(r9, zzgnf.zzw(bArr, zzj, r122));
                    }
                    return zzj + r122;
                }
                throw zzgoz.zzj();
            } else if (r0 != 3) {
                if (r0 == 5) {
                    zzgriVar.zzh(r9, Integer.valueOf(zzb(bArr, r11)));
                    return r11 + 4;
                }
                throw zzgoz.zzc();
            } else {
                int r02 = (r9 & (-8)) | 4;
                zzgri zze = zzgri.zze();
                int r1 = 0;
                while (true) {
                    if (r11 >= r12) {
                        break;
                    }
                    int zzj2 = zzj(bArr, r11, zzgmrVar);
                    int r112 = zzgmrVar.zza;
                    if (r112 == r02) {
                        r1 = r112;
                        r11 = zzj2;
                        break;
                    }
                    r1 = r112;
                    r11 = zzi(r112, bArr, zzj2, r12, zze, zzgmrVar);
                }
                if (r11 > r12 || r1 != r02) {
                    throw zzgoz.zzg();
                }
                zzgriVar.zzh(r9, zze);
                return r11;
            }
        }
        throw zzgoz.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(byte[] bArr, int r2, zzgmr zzgmrVar) {
        int r0 = r2 + 1;
        byte b = bArr[r2];
        if (b >= 0) {
            zzgmrVar.zza = b;
            return r0;
        }
        return zzk(b, bArr, r0, zzgmrVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int r1, byte[] bArr, int r3, zzgmr zzgmrVar) {
        int r12 = r1 & 127;
        int r0 = r3 + 1;
        byte b = bArr[r3];
        if (b >= 0) {
            zzgmrVar.zza = r12 | (b << 7);
            return r0;
        }
        int r13 = r12 | ((b & Byte.MAX_VALUE) << 7);
        int r32 = r0 + 1;
        byte b2 = bArr[r0];
        if (b2 >= 0) {
            zzgmrVar.zza = r13 | (b2 << Ascii.f1129SO);
            return r32;
        }
        int r14 = r13 | ((b2 & Byte.MAX_VALUE) << 14);
        int r02 = r32 + 1;
        byte b3 = bArr[r32];
        if (b3 >= 0) {
            zzgmrVar.zza = r14 | (b3 << Ascii.NAK);
            return r02;
        }
        int r15 = r14 | ((b3 & Byte.MAX_VALUE) << 21);
        int r33 = r02 + 1;
        byte b4 = bArr[r02];
        if (b4 >= 0) {
            zzgmrVar.zza = r15 | (b4 << Ascii.f1122FS);
            return r33;
        }
        int r16 = r15 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int r03 = r33 + 1;
            if (bArr[r33] >= 0) {
                zzgmrVar.zza = r16;
                return r03;
            }
            r33 = r03;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(int r2, byte[] bArr, int r4, int r5, zzgow zzgowVar, zzgmr zzgmrVar) {
        zzgoo zzgooVar = (zzgoo) zzgowVar;
        int zzj = zzj(bArr, r4, zzgmrVar);
        zzgooVar.zzh(zzgmrVar.zza);
        while (zzj < r5) {
            int zzj2 = zzj(bArr, zzj, zzgmrVar);
            if (r2 != zzgmrVar.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzgmrVar);
            zzgooVar.zzh(zzgmrVar.zza);
        }
        return zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(byte[] bArr, int r10, zzgmr zzgmrVar) {
        byte b;
        int r0 = r10 + 1;
        long j = bArr[r10];
        if (j >= 0) {
            zzgmrVar.zzb = j;
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
        zzgmrVar.zzb = j2;
        return r102;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzn(byte[] bArr, int r8) {
        return ((bArr[r8 + 7] & 255) << 56) | (bArr[r8] & 255) | ((bArr[r8 + 1] & 255) << 8) | ((bArr[r8 + 2] & 255) << 16) | ((bArr[r8 + 3] & 255) << 24) | ((bArr[r8 + 4] & 255) << 32) | ((bArr[r8 + 5] & 255) << 40) | ((bArr[r8 + 6] & 255) << 48);
    }
}
