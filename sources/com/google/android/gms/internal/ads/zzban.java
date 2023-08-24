package com.google.android.gms.internal.ads;

import android.os.Build;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzban {
    public static final int zza;
    public static final String zzb;
    public static final String zzc;
    public static final String zzd;
    public static final String zze;
    private static final Pattern zzf;
    private static final Pattern zzg;
    private static final Pattern zzh;

    static {
        int r0 = (Build.VERSION.SDK_INT == 25 && Build.VERSION.CODENAME.charAt(0) == 'O') ? 26 : Build.VERSION.SDK_INT;
        zza = r0;
        String str = Build.DEVICE;
        zzb = str;
        String str2 = Build.MANUFACTURER;
        zzc = str2;
        String str3 = Build.MODEL;
        zzd = str3;
        zze = str + ", " + str3 + ", " + str2 + ", " + r0;
        zzf = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)([\\.,](\\d+))?([Zz]|((\\+|\\-)(\\d?\\d):?(\\d\\d)))?");
        zzg = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
        zzh = Pattern.compile("%([A-Fa-f0-9]{2})");
    }

    public static float zza(float f, float f2, float f3) {
        return Math.max(0.1f, Math.min(f, 8.0f));
    }

    public static int zzb(long[] jArr, long j, boolean z, boolean z2) {
        int r5;
        int binarySearch = Arrays.binarySearch(jArr, j);
        if (binarySearch < 0) {
            r5 = ~binarySearch;
        } else {
            do {
                binarySearch++;
                if (binarySearch >= jArr.length) {
                    break;
                }
            } while (jArr[binarySearch] == j);
            r5 = z ? binarySearch - 1 : binarySearch;
        }
        return z2 ? Math.min(jArr.length - 1, r5) : r5;
    }

    public static int zzc(long[] jArr, long j, boolean z, boolean z2) {
        int r3;
        int binarySearch = Arrays.binarySearch(jArr, j);
        if (binarySearch < 0) {
            r3 = -(binarySearch + 2);
        } else {
            do {
                binarySearch--;
                if (binarySearch < 0) {
                    break;
                }
            } while (jArr[binarySearch] == j);
            r3 = binarySearch + 1;
        }
        return z2 ? Math.max(0, r3) : r3;
    }

    public static int zzd(int r0, int r1) {
        return ((r0 + r1) - 1) / r1;
    }

    public static int zze(int r0, int r1, int r2) {
        return Math.max(r1, Math.min(r0, r2));
    }

    public static int zzf(int r1) {
        return r1 != 1 ? 13107200 : 3538944;
    }

    public static int zzg(String str) {
        int length = str.length();
        zzazy.zzc(length <= 4);
        int r2 = 0;
        for (int r1 = 0; r1 < length; r1++) {
            r2 = (r2 << 8) | str.charAt(r1);
        }
        return r2;
    }

    public static int zzh(int r1) {
        if (r1 != 8) {
            if (r1 != 16) {
                if (r1 != 24) {
                    return r1 != 32 ? 0 : 1073741824;
                }
                return Integer.MIN_VALUE;
            }
            return 2;
        }
        return 3;
    }

    public static int zzi(int r2, int r3) {
        if (r2 != Integer.MIN_VALUE) {
            if (r2 != 1073741824) {
                if (r2 != 2) {
                    if (r2 == 3) {
                        return r3;
                    }
                    throw new IllegalArgumentException();
                }
                return r3 + r3;
            }
            return r3 * 4;
        }
        return r3 * 3;
    }

    public static long zzj(long j, long j2, long j3) {
        int r2 = (j3 > j2 ? 1 : (j3 == j2 ? 0 : -1));
        if (r2 < 0 || j3 % j2 != 0) {
            return (r2 >= 0 || j2 % j3 != 0) ? (long) (j * (j2 / j3)) : j * (j2 / j3);
        }
        return j / (j3 / j2);
    }

    public static String zzk(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        int r1 = 0;
        while (true) {
            int length = objArr.length;
            if (r1 >= length) {
                return sb.toString();
            }
            sb.append(objArr[r1].getClass().getSimpleName());
            if (r1 < length - 1) {
                sb.append(", ");
            }
            r1++;
        }
    }

    public static ExecutorService zzl(String str) {
        return Executors.newSingleThreadExecutor(new zzbam("Loader:ExtractorMediaPeriod"));
    }

    public static void zzm(zzazi zzaziVar) {
        if (zzaziVar != null) {
            try {
                zzaziVar.zzd();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean zzo(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static byte[] zzp(String str) {
        byte[] bArr = new byte[38];
        for (int r1 = 0; r1 < 38; r1++) {
            int r2 = r1 + r1;
            bArr[r1] = (byte) ((Character.digit("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78".charAt(r2), 16) << 4) + Character.digit("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78".charAt(r2 + 1), 16));
        }
        return bArr;
    }

    public static byte[] zzq(String str) {
        return str.getBytes(Charset.defaultCharset());
    }

    public static void zzn(long[] jArr, long j, long j2) {
        int r0 = 0;
        int r3 = (j2 > 1000000L ? 1 : (j2 == 1000000L ? 0 : -1));
        if (r3 >= 0 && j2 % 1000000 == 0) {
            long j3 = j2 / 1000000;
            while (r0 < jArr.length) {
                jArr[r0] = jArr[r0] / j3;
                r0++;
            }
        } else if (r3 >= 0 || 1000000 % j2 != 0) {
            double d = 1000000.0d / j2;
            while (r0 < jArr.length) {
                jArr[r0] = (long) (jArr[r0] * d);
                r0++;
            }
        } else {
            long j4 = 1000000 / j2;
            while (r0 < jArr.length) {
                jArr[r0] = jArr[r0] * j4;
                r0++;
            }
        }
    }
}
