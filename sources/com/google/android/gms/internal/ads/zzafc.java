package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafc {
    private static final byte[] zza = zzel.zzaa("OpusHead");

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00ae, code lost:
        if (r3 != 13) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair zza(com.google.android.gms.internal.ads.zzaet r11) {
        /*
            com.google.android.gms.internal.ads.zzed r11 = r11.zza
            r0 = 8
            r11.zzF(r0)
            r1 = 0
            r2 = r1
            r3 = r2
        La:
            int r4 = r11.zza()
            if (r4 < r0) goto Lda
            int r4 = r11.zzc()
            int r5 = r11.zze()
            int r6 = r11.zze()
            r7 = 1835365473(0x6d657461, float:4.4382975E27)
            if (r6 != r7) goto L75
            r11.zzF(r4)
            int r2 = r4 + r5
            r11.zzG(r0)
            zzd(r11)
        L2c:
            int r6 = r11.zzc()
            if (r6 >= r2) goto L73
            int r6 = r11.zzc()
            int r7 = r11.zze()
            int r8 = r11.zze()
            r9 = 1768715124(0x696c7374, float:1.7865732E25)
            if (r8 != r9) goto L6e
            r11.zzF(r6)
            int r6 = r6 + r7
            r11.zzG(r0)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L4f:
            int r7 = r11.zzc()
            if (r7 >= r6) goto L5f
            com.google.android.gms.internal.ads.zzbp r7 = com.google.android.gms.internal.ads.zzafj.zza(r11)
            if (r7 == 0) goto L4f
            r2.add(r7)
            goto L4f
        L5f:
            boolean r6 = r2.isEmpty()
            if (r6 == 0) goto L66
            goto L73
        L66:
            com.google.android.gms.internal.ads.zzbq r6 = new com.google.android.gms.internal.ads.zzbq
            r6.<init>(r2)
            r2 = r6
            goto Ld4
        L6e:
            int r6 = r6 + r7
            r11.zzF(r6)
            goto L2c
        L73:
            r2 = r1
            goto Ld4
        L75:
            r7 = 1936553057(0x736d7461, float:1.8813092E31)
            if (r6 != r7) goto Ld4
            r11.zzF(r4)
            int r3 = r4 + r5
            r6 = 12
            r11.zzG(r6)
        L84:
            int r7 = r11.zzc()
            if (r7 >= r3) goto Ld3
            int r7 = r11.zzc()
            int r8 = r11.zze()
            int r9 = r11.zze()
            r10 = 1935766900(0x73617574, float:1.7862687E31)
            if (r9 != r10) goto Lce
            r3 = 14
            if (r8 >= r3) goto La0
            goto Ld3
        La0:
            r3 = 5
            r11.zzG(r3)
            int r3 = r11.zzk()
            r7 = 1123024896(0x42f00000, float:120.0)
            if (r3 == r6) goto Lb1
            r6 = 13
            if (r3 == r6) goto Lb5
            goto Ld3
        Lb1:
            if (r3 != r6) goto Lb5
            r7 = 1131413504(0x43700000, float:240.0)
        Lb5:
            r3 = 1
            r11.zzG(r3)
            int r6 = r11.zzk()
            com.google.android.gms.internal.ads.zzbq r8 = new com.google.android.gms.internal.ads.zzbq
            com.google.android.gms.internal.ads.zzbp[] r3 = new com.google.android.gms.internal.ads.zzbp[r3]
            com.google.android.gms.internal.ads.zzadt r9 = new com.google.android.gms.internal.ads.zzadt
            r9.<init>(r7, r6)
            r6 = 0
            r3[r6] = r9
            r8.<init>(r3)
            r3 = r8
            goto Ld4
        Lce:
            int r7 = r7 + r8
            r11.zzF(r7)
            goto L84
        Ld3:
            r3 = r1
        Ld4:
            int r4 = r4 + r5
            r11.zzF(r4)
            goto La
        Lda:
            android.util.Pair r11 = android.util.Pair.create(r2, r3)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafc.zza(com.google.android.gms.internal.ads.zzaet):android.util.Pair");
    }

    public static zzbq zzb(zzaes zzaesVar) {
        zzadn zzadnVar;
        zzaet zzb = zzaesVar.zzb(Atom.TYPE_hdlr);
        zzaet zzb2 = zzaesVar.zzb(Atom.TYPE_keys);
        zzaet zzb3 = zzaesVar.zzb(Atom.TYPE_ilst);
        if (zzb == null || zzb2 == null || zzb3 == null || zzg(zzb.zza) != 1835299937) {
            return null;
        }
        zzed zzedVar = zzb2.zza;
        zzedVar.zzF(12);
        int zze = zzedVar.zze();
        String[] strArr = new String[zze];
        for (int r5 = 0; r5 < zze; r5++) {
            int zze2 = zzedVar.zze();
            zzedVar.zzG(4);
            strArr[r5] = zzedVar.zzx(zze2 - 8, zzfrs.zzc);
        }
        zzed zzedVar2 = zzb3.zza;
        zzedVar2.zzF(8);
        ArrayList arrayList = new ArrayList();
        while (zzedVar2.zza() > 8) {
            int zzc = zzedVar2.zzc();
            int zze3 = zzedVar2.zze();
            int zze4 = zzedVar2.zze() - 1;
            if (zze4 < 0 || zze4 >= zze) {
                Log.w("AtomParsers", "Skipped metadata with unknown key index: " + zze4);
            } else {
                String str = strArr[zze4];
                int r9 = zzc + zze3;
                int r10 = zzafj.zzb;
                while (true) {
                    int zzc2 = zzedVar2.zzc();
                    if (zzc2 >= r9) {
                        zzadnVar = null;
                        break;
                    }
                    int zze5 = zzedVar2.zze();
                    if (zzedVar2.zze() != 1684108385) {
                        zzedVar2.zzF(zzc2 + zze5);
                    } else {
                        int zze6 = zzedVar2.zze();
                        int zze7 = zzedVar2.zze();
                        int r11 = zze5 - 16;
                        byte[] bArr = new byte[r11];
                        zzedVar2.zzB(bArr, 0, r11);
                        zzadnVar = new zzadn(str, bArr, zze7, zze6);
                        break;
                    }
                }
                if (zzadnVar != null) {
                    arrayList.add(zzadnVar);
                }
            }
            zzedVar2.zzF(zzc + zze3);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new zzbq(arrayList);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b7, code lost:
        if (r7 == 0) goto L639;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x0669, code lost:
        if (r1 != 3) goto L545;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x0762, code lost:
        if (r25 == null) goto L586;
     */
    /* JADX WARN: Removed duplicated region for block: B:378:0x07d1  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x07e9  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x07ed  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0822  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x09d9  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0aa0 A[LOOP:13: B:487:0x0aa0->B:491:0x0aaa, LOOP_START, PHI: r23 
      PHI: (r23v8 int) = (r23v7 int), (r23v9 int) binds: [B:486:0x0a9e, B:491:0x0aaa] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0ab3  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0afe  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x0b13  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0b16  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0b71  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x0b8f  */
    /* JADX WARN: Removed duplicated region for block: B:606:0x0e0a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:632:0x0a99 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List zzc(com.google.android.gms.internal.ads.zzaes r58, com.google.android.gms.internal.ads.zzzu r59, long r60, com.google.android.gms.internal.ads.zzx r62, boolean r63, boolean r64, com.google.android.gms.internal.ads.zzfru r65) throws com.google.android.gms.internal.ads.zzbu {
        /*
            Method dump skipped, instructions count: 3609
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafc.zzc(com.google.android.gms.internal.ads.zzaes, com.google.android.gms.internal.ads.zzzu, long, com.google.android.gms.internal.ads.zzx, boolean, boolean, com.google.android.gms.internal.ads.zzfru):java.util.List");
    }

    public static void zzd(zzed zzedVar) {
        int zzc = zzedVar.zzc();
        zzedVar.zzG(4);
        if (zzedVar.zze() != 1751411826) {
            zzc += 4;
        }
        zzedVar.zzF(zzc);
    }

    private static int zze(int r1) {
        if (r1 == 1936684398) {
            return 1;
        }
        if (r1 == 1986618469) {
            return 2;
        }
        if (r1 == 1952807028 || r1 == 1935832172 || r1 == 1937072756 || r1 == 1668047728) {
            return 3;
        }
        return r1 == 1835365473 ? 5 : -1;
    }

    private static int zzf(zzed zzedVar) {
        int zzk = zzedVar.zzk();
        int r1 = zzk & 127;
        while ((zzk & 128) == 128) {
            zzk = zzedVar.zzk();
            r1 = (r1 << 7) | (zzk & 127);
        }
        return r1;
    }

    private static int zzg(zzed zzedVar) {
        zzedVar.zzF(16);
        return zzedVar.zze();
    }

    private static Pair zzh(zzaes zzaesVar) {
        zzaet zzb = zzaesVar.zzb(Atom.TYPE_elst);
        if (zzb == null) {
            return null;
        }
        zzed zzedVar = zzb.zza;
        zzedVar.zzF(8);
        int zze = zzaeu.zze(zzedVar.zze());
        int zzn = zzedVar.zzn();
        long[] jArr = new long[zzn];
        long[] jArr2 = new long[zzn];
        for (int r4 = 0; r4 < zzn; r4++) {
            jArr[r4] = zze == 1 ? zzedVar.zzt() : zzedVar.zzs();
            jArr2[r4] = zze == 1 ? zzedVar.zzr() : zzedVar.zze();
            if (zzedVar.zzy() == 1) {
                zzedVar.zzG(2);
            } else {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
        }
        return Pair.create(jArr, jArr2);
    }

    private static Pair zzi(zzed zzedVar) {
        zzedVar.zzF(8);
        int zze = zzaeu.zze(zzedVar.zze());
        zzedVar.zzG(zze == 0 ? 8 : 16);
        long zzs = zzedVar.zzs();
        zzedVar.zzG(zze == 0 ? 4 : 8);
        int zzo = zzedVar.zzo();
        StringBuilder sb = new StringBuilder();
        sb.append((char) (((zzo >> 10) & 31) + 96));
        sb.append((char) (((zzo >> 5) & 31) + 96));
        sb.append((char) ((zzo & 31) + 96));
        return Pair.create(Long.valueOf(zzs), sb.toString());
    }

    private static Pair zzj(zzed zzedVar, int r18, int r19) throws zzbu {
        Integer num;
        zzaft zzaftVar;
        Pair create;
        int r3;
        int r14;
        byte[] bArr;
        int zzc = zzedVar.zzc();
        while (zzc - r18 < r19) {
            zzedVar.zzF(zzc);
            int zze = zzedVar.zze();
            zzzj.zzb(zze > 0, "childAtomSize must be positive");
            if (zzedVar.zze() == 1936289382) {
                int r7 = zzc + 8;
                int r9 = -1;
                int r10 = 0;
                String str = null;
                Integer num2 = null;
                while (r7 - zzc < zze) {
                    zzedVar.zzF(r7);
                    int zze2 = zzedVar.zze();
                    int zze3 = zzedVar.zze();
                    if (zze3 == 1718775137) {
                        num2 = Integer.valueOf(zzedVar.zze());
                    } else if (zze3 == 1935894637) {
                        zzedVar.zzG(4);
                        str = zzedVar.zzx(4, zzfrs.zzc);
                    } else if (zze3 == 1935894633) {
                        r9 = r7;
                        r10 = zze2;
                    }
                    r7 += zze2;
                }
                if (C1856C.CENC_TYPE_cenc.equals(str) || C1856C.CENC_TYPE_cbc1.equals(str) || C1856C.CENC_TYPE_cens.equals(str) || C1856C.CENC_TYPE_cbcs.equals(str)) {
                    zzzj.zzb(num2 != null, "frma atom is mandatory");
                    zzzj.zzb(r9 != -1, "schi atom is mandatory");
                    int r32 = r9 + 8;
                    while (true) {
                        if (r32 - r9 >= r10) {
                            num = num2;
                            zzaftVar = null;
                            break;
                        }
                        zzedVar.zzF(r32);
                        int zze4 = zzedVar.zze();
                        if (zzedVar.zze() == 1952804451) {
                            int zze5 = zzedVar.zze();
                            zzedVar.zzG(1);
                            if (zzaeu.zze(zze5) == 0) {
                                zzedVar.zzG(1);
                                r3 = 0;
                                r14 = 0;
                            } else {
                                int zzk = zzedVar.zzk();
                                r3 = zzk & 15;
                                r14 = (zzk & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                            }
                            boolean z = zzedVar.zzk() == 1;
                            int zzk2 = zzedVar.zzk();
                            byte[] bArr2 = new byte[16];
                            zzedVar.zzB(bArr2, 0, 16);
                            if (z && zzk2 == 0) {
                                int zzk3 = zzedVar.zzk();
                                byte[] bArr3 = new byte[zzk3];
                                zzedVar.zzB(bArr3, 0, zzk3);
                                bArr = bArr3;
                            } else {
                                bArr = null;
                            }
                            num = num2;
                            zzaftVar = new zzaft(z, str, zzk2, bArr2, r14, r3, bArr);
                        } else {
                            r32 += zze4;
                        }
                    }
                    zzzj.zzb(zzaftVar != null, "tenc atom is mandatory");
                    int r5 = zzel.zza;
                    create = Pair.create(num, zzaftVar);
                } else {
                    create = null;
                }
                if (create != null) {
                    return create;
                }
            }
            zzc += zze;
        }
        return null;
    }

    private static zzaew zzk(zzed zzedVar, int r7) {
        zzedVar.zzF(r7 + 12);
        zzedVar.zzG(1);
        zzf(zzedVar);
        zzedVar.zzG(2);
        int zzk = zzedVar.zzk();
        if ((zzk & 128) != 0) {
            zzedVar.zzG(2);
        }
        if ((zzk & 64) != 0) {
            zzedVar.zzG(zzedVar.zzo());
        }
        if ((zzk & 32) != 0) {
            zzedVar.zzG(2);
        }
        zzedVar.zzG(1);
        zzf(zzedVar);
        String zzd = zzbt.zzd(zzedVar.zzk());
        if (MimeTypes.AUDIO_MPEG.equals(zzd) || MimeTypes.AUDIO_DTS.equals(zzd) || MimeTypes.AUDIO_DTS_HD.equals(zzd)) {
            return new zzaew(zzd, null, -1, -1);
        }
        zzedVar.zzG(4);
        int zzn = zzedVar.zzn();
        int zzn2 = zzedVar.zzn();
        zzedVar.zzG(1);
        int zzf = zzf(zzedVar);
        byte[] bArr = new byte[zzf];
        zzedVar.zzB(bArr, 0, zzf);
        if (zzn2 <= 0) {
            zzn2 = -1;
        }
        return new zzaew(zzd, bArr, zzn2, zzn > 0 ? zzn : -1);
    }

    private static ByteBuffer zzl() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zzm(com.google.android.gms.internal.ads.zzed r24, int r25, int r26, int r27, int r28, java.lang.String r29, boolean r30, com.google.android.gms.internal.ads.zzx r31, com.google.android.gms.internal.ads.zzaey r32, int r33) throws com.google.android.gms.internal.ads.zzbu {
        /*
            Method dump skipped, instructions count: 987
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafc.zzm(com.google.android.gms.internal.ads.zzed, int, int, int, int, java.lang.String, boolean, com.google.android.gms.internal.ads.zzx, com.google.android.gms.internal.ads.zzaey, int):void");
    }

    private static void zzn(zzed zzedVar, int r1, int r2, int r3, zzaey zzaeyVar) {
        zzedVar.zzF(r2 + 16);
        zzedVar.zzv((char) 0);
        String zzv = zzedVar.zzv((char) 0);
        if (zzv != null) {
            zzad zzadVar = new zzad();
            zzadVar.zzG(r3);
            zzadVar.zzS(zzv);
            zzaeyVar.zzb = zzadVar.zzY();
        }
    }

    private static boolean zzo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length;
        int r1 = length - 1;
        return jArr[0] <= j2 && j2 < jArr[zzel.zzf(4, 0, r1)] && jArr[zzel.zzf(length + (-4), 0, r1)] < j3 && j3 <= j;
    }
}
