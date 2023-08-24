package com.google.android.gms.internal.ads;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.mp4.Sniffer;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafr {
    private static final int[] zza = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, Sniffer.BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};

    public static boolean zza(zzzg zzzgVar) throws IOException {
        return zzc(zzzgVar, true, false);
    }

    public static boolean zzb(zzzg zzzgVar, boolean z) throws IOException {
        return zzc(zzzgVar, false, false);
    }

    private static boolean zzc(zzzg zzzgVar, boolean z, boolean z2) throws IOException {
        boolean z3;
        long zzd = zzzgVar.zzd();
        long j = -1;
        long j2 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        int r7 = (zzd > (-1L) ? 1 : (zzd == (-1L) ? 0 : -1));
        if (r7 != 0 && zzd <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j2 = zzd;
        }
        int r6 = (int) j2;
        zzed zzedVar = new zzed(64);
        boolean z4 = false;
        int r9 = 0;
        boolean z5 = false;
        while (r9 < r6) {
            zzedVar.zzC(8);
            if (!zzzgVar.zzm(zzedVar.zzH(), z4 ? 1 : 0, 8, true)) {
                break;
            }
            long zzs = zzedVar.zzs();
            int zze = zzedVar.zze();
            int r11 = 16;
            if (zzs == 1) {
                zzzgVar.zzh(zzedVar.zzH(), 8, 8);
                zzedVar.zzE(16);
                zzs = zzedVar.zzr();
            } else {
                if (zzs == 0) {
                    long zzd2 = zzzgVar.zzd();
                    if (zzd2 != j) {
                        zzs = (zzd2 - zzzgVar.zze()) + 8;
                    }
                }
                r11 = 8;
            }
            long j3 = r11;
            if (zzs < j3) {
                return z4;
            }
            r9 += r11;
            if (zze == 1836019574) {
                r6 += (int) zzs;
                if (r7 != 0 && r6 > zzd) {
                    r6 = (int) zzd;
                }
                j = -1;
            } else if (zze == 1836019558 || zze == 1836475768) {
                z3 = true;
                break;
            } else {
                long j4 = zzd;
                if ((r9 + zzs) - j3 >= r6) {
                    break;
                }
                int r1 = (int) (zzs - j3);
                r9 += r1;
                if (zze == 1718909296) {
                    if (r1 < 8) {
                        return false;
                    }
                    zzedVar.zzC(r1);
                    zzzgVar.zzh(zzedVar.zzH(), 0, r1);
                    int r12 = r1 >> 2;
                    for (int r2 = 0; r2 < r12; r2++) {
                        if (r2 == 1) {
                            zzedVar.zzG(4);
                        } else {
                            int zze2 = zzedVar.zze();
                            if ((zze2 >>> 8) != 3368816) {
                                if (zze2 == 1751476579) {
                                    zze2 = Sniffer.BRAND_HEIC;
                                }
                                int[] r4 = zza;
                                for (int r8 = 0; r8 < 29; r8++) {
                                    if (r4[r8] != zze2) {
                                    }
                                }
                                continue;
                            }
                            z5 = true;
                            break;
                        }
                    }
                    if (!z5) {
                        return false;
                    }
                } else if (r1 != 0) {
                    zzzgVar.zzg(r1);
                }
                zzd = j4;
                j = -1;
                z4 = false;
            }
        }
        z3 = false;
        return z5 && z == z3;
    }
}
