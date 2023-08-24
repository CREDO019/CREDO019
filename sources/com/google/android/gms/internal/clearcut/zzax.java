package com.google.android.gms.internal.clearcut;

import com.google.common.base.Ascii;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzax {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r2, byte[] bArr, int r4, int r5, zzay zzayVar) throws zzco {
        if ((r2 >>> 3) != 0) {
            int r0 = r2 & 7;
            if (r0 != 0) {
                if (r0 != 1) {
                    if (r0 != 2) {
                        if (r0 != 3) {
                            if (r0 == 5) {
                                return r4 + 4;
                            }
                            throw zzco.zzbm();
                        }
                        int r22 = (r2 & (-8)) | 4;
                        int r02 = 0;
                        while (r4 < r5) {
                            r4 = zza(bArr, r4, zzayVar);
                            r02 = zzayVar.zzfd;
                            if (r02 == r22) {
                                break;
                            }
                            r4 = zza(r02, bArr, r4, r5, zzayVar);
                        }
                        if (r4 > r5 || r02 != r22) {
                            throw zzco.zzbo();
                        }
                        return r4;
                    }
                    return zza(bArr, r4, zzayVar) + zzayVar.zzfd;
                }
                return r4 + 8;
            }
            return zzb(bArr, r4, zzayVar);
        }
        throw zzco.zzbm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r2, byte[] bArr, int r4, int r5, zzcn<?> zzcnVar, zzay zzayVar) {
        zzch zzchVar = (zzch) zzcnVar;
        int zza = zza(bArr, r4, zzayVar);
        while (true) {
            zzchVar.zzac(zzayVar.zzfd);
            if (zza >= r5) {
                break;
            }
            int zza2 = zza(bArr, zza, zzayVar);
            if (r2 != zzayVar.zzfd) {
                break;
            }
            zza = zza(bArr, zza2, zzayVar);
        }
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r9, byte[] bArr, int r11, int r12, zzey zzeyVar, zzay zzayVar) throws IOException {
        if ((r9 >>> 3) != 0) {
            int r0 = r9 & 7;
            if (r0 == 0) {
                int zzb = zzb(bArr, r11, zzayVar);
                zzeyVar.zzb(r9, Long.valueOf(zzayVar.zzfe));
                return zzb;
            } else if (r0 == 1) {
                zzeyVar.zzb(r9, Long.valueOf(zzd(bArr, r11)));
                return r11 + 8;
            } else if (r0 == 2) {
                int zza = zza(bArr, r11, zzayVar);
                int r122 = zzayVar.zzfd;
                zzeyVar.zzb(r9, r122 == 0 ? zzbb.zzfi : zzbb.zzb(bArr, zza, r122));
                return zza + r122;
            } else if (r0 != 3) {
                if (r0 == 5) {
                    zzeyVar.zzb(r9, Integer.valueOf(zzc(bArr, r11)));
                    return r11 + 4;
                }
                throw zzco.zzbm();
            } else {
                zzey zzeb = zzey.zzeb();
                int r7 = (r9 & (-8)) | 4;
                int r02 = 0;
                while (true) {
                    if (r11 >= r12) {
                        break;
                    }
                    int zza2 = zza(bArr, r11, zzayVar);
                    int r112 = zzayVar.zzfd;
                    r02 = r112;
                    if (r112 == r7) {
                        r11 = zza2;
                        break;
                    }
                    int zza3 = zza(r02, bArr, zza2, r12, zzeb, zzayVar);
                    r02 = r112;
                    r11 = zza3;
                }
                if (r11 > r12 || r02 != r7) {
                    throw zzco.zzbo();
                }
                zzeyVar.zzb(r9, zzeb);
                return r11;
            }
        }
        throw zzco.zzbm();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r1, byte[] bArr, int r3, zzay zzayVar) {
        int r2;
        int r22;
        int r12 = r1 & 127;
        int r0 = r3 + 1;
        byte b = bArr[r3];
        if (b < 0) {
            int r13 = r12 | ((b & Byte.MAX_VALUE) << 7);
            int r32 = r0 + 1;
            byte b2 = bArr[r0];
            if (b2 >= 0) {
                r2 = b2 << Ascii.f1129SO;
            } else {
                r12 = r13 | ((b2 & Byte.MAX_VALUE) << 14);
                r0 = r32 + 1;
                byte b3 = bArr[r32];
                if (b3 >= 0) {
                    r22 = b3 << Ascii.NAK;
                } else {
                    r13 = r12 | ((b3 & Byte.MAX_VALUE) << 21);
                    r32 = r0 + 1;
                    byte b4 = bArr[r0];
                    if (b4 >= 0) {
                        r2 = b4 << Ascii.f1122FS;
                    } else {
                        int r14 = r13 | ((b4 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int r02 = r32 + 1;
                            if (bArr[r32] >= 0) {
                                zzayVar.zzfd = r14;
                                return r02;
                            }
                            r32 = r02;
                        }
                    }
                }
            }
            zzayVar.zzfd = r13 | r2;
            return r32;
        }
        r22 = b << 7;
        zzayVar.zzfd = r12 | r22;
        return r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r2, zzay zzayVar) {
        int r0 = r2 + 1;
        byte b = bArr[r2];
        if (b >= 0) {
            zzayVar.zzfd = b;
            return r0;
        }
        return zza(b, bArr, r0, zzayVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(byte[] bArr, int r3, zzcn<?> zzcnVar, zzay zzayVar) throws IOException {
        zzch zzchVar = (zzch) zzcnVar;
        int zza = zza(bArr, r3, zzayVar);
        int r0 = zzayVar.zzfd + zza;
        while (zza < r0) {
            zza = zza(bArr, zza, zzayVar);
            zzchVar.zzac(zzayVar.zzfd);
        }
        if (zza == r0) {
            return zza;
        }
        throw zzco.zzbl();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int r10, zzay zzayVar) {
        byte b;
        int r0 = r10 + 1;
        long j = bArr[r10];
        if (j >= 0) {
            zzayVar.zzfe = j;
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
        zzayVar.zzfe = j2;
        return r102;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int r3) {
        return ((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int r4, zzay zzayVar) {
        int zza = zza(bArr, r4, zzayVar);
        int r0 = zzayVar.zzfd;
        if (r0 == 0) {
            zzayVar.zzff = "";
            return zza;
        }
        zzayVar.zzff = new String(bArr, zza, r0, zzci.UTF_8);
        return zza + r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(byte[] bArr, int r5, zzay zzayVar) throws IOException {
        int zza = zza(bArr, r5, zzayVar);
        int r0 = zzayVar.zzfd;
        if (r0 == 0) {
            zzayVar.zzff = "";
            return zza;
        }
        int r1 = zza + r0;
        if (zzff.zze(bArr, zza, r1)) {
            zzayVar.zzff = new String(bArr, zza, r0, zzci.UTF_8);
            return r1;
        }
        throw zzco.zzbp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzd(byte[] bArr, int r8) {
        return ((bArr[r8 + 7] & 255) << 56) | (bArr[r8] & 255) | ((bArr[r8 + 1] & 255) << 8) | ((bArr[r8 + 2] & 255) << 16) | ((bArr[r8 + 3] & 255) << 24) | ((bArr[r8 + 4] & 255) << 32) | ((bArr[r8 + 5] & 255) << 40) | ((bArr[r8 + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zze(byte[] bArr, int r1) {
        return Double.longBitsToDouble(zzd(bArr, r1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(byte[] bArr, int r2, zzay zzayVar) {
        int zza = zza(bArr, r2, zzayVar);
        int r0 = zzayVar.zzfd;
        if (r0 == 0) {
            zzayVar.zzff = zzbb.zzfi;
            return zza;
        }
        zzayVar.zzff = zzbb.zzb(bArr, zza, r0);
        return zza + r0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzf(byte[] bArr, int r1) {
        return Float.intBitsToFloat(zzc(bArr, r1));
    }
}
