package com.google.android.gms.internal.ads;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzawj {
    private static final int[] zza = {zzban.zzg("isom"), zzban.zzg("iso2"), zzban.zzg("iso3"), zzban.zzg("iso4"), zzban.zzg("iso5"), zzban.zzg("iso6"), zzban.zzg("avc1"), zzban.zzg("hvc1"), zzban.zzg("hev1"), zzban.zzg("mp41"), zzban.zzg("mp42"), zzban.zzg("3g2a"), zzban.zzg("3g2b"), zzban.zzg("3gr6"), zzban.zzg("3gs6"), zzban.zzg("3ge6"), zzban.zzg("3gg6"), zzban.zzg("M4V "), zzban.zzg("M4A "), zzban.zzg("f4v "), zzban.zzg("kddi"), zzban.zzg("M4VP"), zzban.zzg("qt  "), zzban.zzg("MSNV")};

    public static boolean zza(zzauu zzauuVar) throws IOException, InterruptedException {
        return zzc(zzauuVar, true);
    }

    public static boolean zzb(zzauu zzauuVar) throws IOException, InterruptedException {
        return zzc(zzauuVar, false);
    }

    private static boolean zzc(zzauu zzauuVar, boolean z) throws IOException, InterruptedException {
        boolean z2;
        long zzc = zzauuVar.zzc();
        int r2 = (int) ((zzc == -1 || zzc > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) ? 4096L : 4096L);
        zzbag zzbagVar = new zzbag(64);
        int r4 = 0;
        boolean z3 = false;
        while (r4 < r2) {
            zzbagVar.zzs(8);
            zzauuVar.zzg(zzbagVar.zza, 0, 8, false);
            long zzm = zzbagVar.zzm();
            int zze = zzbagVar.zze();
            int r13 = 16;
            if (zzm == 1) {
                zzauuVar.zzg(zzbagVar.zza, 8, 8, false);
                zzbagVar.zzu(16);
                zzm = zzbagVar.zzn();
            } else {
                r13 = 8;
            }
            long j = r13;
            if (zzm < j) {
                return false;
            }
            r4 += r13;
            if (zze != zzavs.zzE) {
                if (zze == zzavs.zzN || zze == zzavs.zzP) {
                    z2 = true;
                    break;
                } else if ((r4 + zzm) - j >= r2) {
                    break;
                } else {
                    int r6 = (int) (zzm - j);
                    r4 += r6;
                    if (zze == zzavs.zzd) {
                        if (r6 < 8) {
                            return false;
                        }
                        zzbagVar.zzs(r6);
                        zzauuVar.zzg(zzbagVar.zza, 0, r6, false);
                        int r62 = r6 >> 2;
                        for (int r7 = 0; r7 < r62; r7++) {
                            if (r7 == 1) {
                                zzbagVar.zzw(4);
                            } else {
                                int zze2 = zzbagVar.zze();
                                if ((zze2 >>> 8) != zzban.zzg("3gp")) {
                                    int[] r9 = zza;
                                    int length = r9.length;
                                    for (int r10 = 0; r10 < 24; r10++) {
                                        if (r9[r10] != zze2) {
                                        }
                                    }
                                    continue;
                                }
                                z3 = true;
                                break;
                            }
                        }
                        if (!z3) {
                            return false;
                        }
                    } else if (r6 != 0) {
                        zzauuVar.zzf(r6, false);
                    }
                }
            }
        }
        z2 = false;
        return z3 && z == z2;
    }
}
