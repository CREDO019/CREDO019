package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzip {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r3, zzio zzioVar) throws zzkm {
        int zzj = zzj(bArr, r3, zzioVar);
        int r0 = zzioVar.zza;
        if (r0 < 0) {
            throw zzkm.zzd();
        }
        if (r0 <= bArr.length - zzj) {
            if (r0 == 0) {
                zzioVar.zzc = zzjb.zzb;
                return zzj;
            }
            zzioVar.zzc = zzjb.zzl(bArr, zzj, r0);
            return zzj + r0;
        }
        throw zzkm.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int r3) {
        return ((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(zzlu zzluVar, byte[] bArr, int r10, int r11, int r12, zzio zzioVar) throws IOException {
        zzlm zzlmVar = (zzlm) zzluVar;
        Object zze = zzlmVar.zze();
        int zzc = zzlmVar.zzc(zze, bArr, r10, r11, r12, zzioVar);
        zzlmVar.zzf(zze);
        zzioVar.zzc = zze;
        return zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(zzlu zzluVar, byte[] bArr, int r8, int r9, zzio zzioVar) throws IOException {
        int r0 = r8 + 1;
        int r82 = bArr[r8];
        if (r82 < 0) {
            r0 = zzk(r82, bArr, r0, zzioVar);
            r82 = zzioVar.zza;
        }
        int r3 = r0;
        if (r82 < 0 || r82 > r9 - r3) {
            throw zzkm.zzf();
        }
        Object zze = zzluVar.zze();
        int r83 = r82 + r3;
        zzluVar.zzh(zze, bArr, r3, r83, zzioVar);
        zzluVar.zzf(zze);
        zzioVar.zzc = zze;
        return r83;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(zzlu zzluVar, int r3, byte[] bArr, int r5, int r6, zzkj zzkjVar, zzio zzioVar) throws IOException {
        int zzd = zzd(zzluVar, bArr, r5, r6, zzioVar);
        zzkjVar.add(zzioVar.zzc);
        while (zzd < r6) {
            int zzj = zzj(bArr, zzd, zzioVar);
            if (r3 != zzioVar.zza) {
                break;
            }
            zzd = zzd(zzluVar, bArr, zzj, r6, zzioVar);
            zzkjVar.add(zzioVar.zzc);
        }
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int r3, zzkj zzkjVar, zzio zzioVar) throws IOException {
        zzkd zzkdVar = (zzkd) zzkjVar;
        int zzj = zzj(bArr, r3, zzioVar);
        int r0 = zzioVar.zza + zzj;
        while (zzj < r0) {
            zzj = zzj(bArr, zzj, zzioVar);
            zzkdVar.zzh(zzioVar.zza);
        }
        if (zzj == r0) {
            return zzj;
        }
        throw zzkm.zzf();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(byte[] bArr, int r4, zzio zzioVar) throws zzkm {
        int zzj = zzj(bArr, r4, zzioVar);
        int r0 = zzioVar.zza;
        if (r0 >= 0) {
            if (r0 == 0) {
                zzioVar.zzc = "";
                return zzj;
            }
            zzioVar.zzc = new String(bArr, zzj, r0, zzkk.zzb);
            return zzj + r0;
        }
        throw zzkm.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(byte[] bArr, int r2, zzio zzioVar) throws zzkm {
        int zzj = zzj(bArr, r2, zzioVar);
        int r0 = zzioVar.zza;
        if (r0 >= 0) {
            if (r0 == 0) {
                zzioVar.zzc = "";
                return zzj;
            }
            zzioVar.zzc = zzna.zzd(bArr, zzj, r0);
            return zzj + r0;
        }
        throw zzkm.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(int r9, byte[] bArr, int r11, int r12, zzmm zzmmVar, zzio zzioVar) throws zzkm {
        if ((r9 >>> 3) != 0) {
            int r0 = r9 & 7;
            if (r0 == 0) {
                int zzm = zzm(bArr, r11, zzioVar);
                zzmmVar.zzh(r9, Long.valueOf(zzioVar.zzb));
                return zzm;
            } else if (r0 == 1) {
                zzmmVar.zzh(r9, Long.valueOf(zzn(bArr, r11)));
                return r11 + 8;
            } else if (r0 == 2) {
                int zzj = zzj(bArr, r11, zzioVar);
                int r122 = zzioVar.zza;
                if (r122 < 0) {
                    throw zzkm.zzd();
                }
                if (r122 <= bArr.length - zzj) {
                    if (r122 == 0) {
                        zzmmVar.zzh(r9, zzjb.zzb);
                    } else {
                        zzmmVar.zzh(r9, zzjb.zzl(bArr, zzj, r122));
                    }
                    return zzj + r122;
                }
                throw zzkm.zzf();
            } else if (r0 != 3) {
                if (r0 == 5) {
                    zzmmVar.zzh(r9, Integer.valueOf(zzb(bArr, r11)));
                    return r11 + 4;
                }
                throw zzkm.zzb();
            } else {
                int r02 = (r9 & (-8)) | 4;
                zzmm zze = zzmm.zze();
                int r1 = 0;
                while (true) {
                    if (r11 >= r12) {
                        break;
                    }
                    int zzj2 = zzj(bArr, r11, zzioVar);
                    int r112 = zzioVar.zza;
                    if (r112 == r02) {
                        r1 = r112;
                        r11 = zzj2;
                        break;
                    }
                    r1 = r112;
                    r11 = zzi(r112, bArr, zzj2, r12, zze, zzioVar);
                }
                if (r11 > r12 || r1 != r02) {
                    throw zzkm.zze();
                }
                zzmmVar.zzh(r9, zze);
                return r11;
            }
        }
        throw zzkm.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(byte[] bArr, int r2, zzio zzioVar) {
        int r0 = r2 + 1;
        byte b = bArr[r2];
        if (b >= 0) {
            zzioVar.zza = b;
            return r0;
        }
        return zzk(b, bArr, r0, zzioVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int r1, byte[] bArr, int r3, zzio zzioVar) {
        int r12 = r1 & 127;
        int r0 = r3 + 1;
        byte b = bArr[r3];
        if (b >= 0) {
            zzioVar.zza = r12 | (b << 7);
            return r0;
        }
        int r13 = r12 | ((b & Byte.MAX_VALUE) << 7);
        int r32 = r0 + 1;
        byte b2 = bArr[r0];
        if (b2 >= 0) {
            zzioVar.zza = r13 | (b2 << Ascii.f1129SO);
            return r32;
        }
        int r14 = r13 | ((b2 & Byte.MAX_VALUE) << 14);
        int r02 = r32 + 1;
        byte b3 = bArr[r32];
        if (b3 >= 0) {
            zzioVar.zza = r14 | (b3 << Ascii.NAK);
            return r02;
        }
        int r15 = r14 | ((b3 & Byte.MAX_VALUE) << 21);
        int r33 = r02 + 1;
        byte b4 = bArr[r02];
        if (b4 >= 0) {
            zzioVar.zza = r15 | (b4 << Ascii.f1122FS);
            return r33;
        }
        int r16 = r15 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int r03 = r33 + 1;
            if (bArr[r33] >= 0) {
                zzioVar.zza = r16;
                return r03;
            }
            r33 = r03;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(int r2, byte[] bArr, int r4, int r5, zzkj zzkjVar, zzio zzioVar) {
        zzkd zzkdVar = (zzkd) zzkjVar;
        int zzj = zzj(bArr, r4, zzioVar);
        zzkdVar.zzh(zzioVar.zza);
        while (zzj < r5) {
            int zzj2 = zzj(bArr, zzj, zzioVar);
            if (r2 != zzioVar.zza) {
                break;
            }
            zzj = zzj(bArr, zzj2, zzioVar);
            zzkdVar.zzh(zzioVar.zza);
        }
        return zzj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(byte[] bArr, int r10, zzio zzioVar) {
        byte b;
        int r0 = r10 + 1;
        long j = bArr[r10];
        if (j >= 0) {
            zzioVar.zzb = j;
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
        zzioVar.zzb = j2;
        return r102;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzn(byte[] bArr, int r8) {
        return ((bArr[r8 + 7] & 255) << 56) | (bArr[r8] & 255) | ((bArr[r8 + 1] & 255) << 8) | ((bArr[r8 + 2] & 255) << 16) | ((bArr[r8 + 3] & 255) << 24) | ((bArr[r8 + 4] & 255) << 32) | ((bArr[r8 + 5] & 255) << 40) | ((bArr[r8 + 6] & 255) << 48);
    }
}
