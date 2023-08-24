package com.google.android.gms.internal.ads;

import ai.api.util.VoiceActivityDetector;
import com.facebook.imagepipeline.memory.BitmapCounterConfig;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzyg {
    public static final /* synthetic */ int zza = 0;
    private static final int[] zzb = {1, 2, 3, 6};
    private static final int[] zzc = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] zzd = {24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] zze = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] zzf = {32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, VoiceActivityDetector.FRAME_SIZE_IN_BYTES, BitmapCounterConfig.DEFAULT_MAX_BITMAP_COUNT, 448, 512, 576, 640};
    private static final int[] zzg = {69, 87, 104, 121, 139, 174, JfifUtil.MARKER_RST0, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static int zza(ByteBuffer byteBuffer) {
        if (((byteBuffer.get(byteBuffer.position() + 5) & 248) >> 3) > 10) {
            return zzb[((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? (byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4 : 3] * 256;
        }
        return 1536;
    }

    public static int zzb(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            int r4 = ((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1;
            return r4 + r4;
        }
        byte b = bArr[4];
        return zzf((b & 192) >> 6, b & Utf8.REPLACEMENT_BYTE);
    }

    public static zzaf zzc(zzed zzedVar, String str, String str2, zzx zzxVar) {
        int r0 = zzc[(zzedVar.zzk() & 192) >> 6];
        int zzk = zzedVar.zzk();
        int r1 = zze[(zzk & 56) >> 3];
        if ((zzk & 4) != 0) {
            r1++;
        }
        zzad zzadVar = new zzad();
        zzadVar.zzH(str);
        zzadVar.zzS(MimeTypes.AUDIO_AC3);
        zzadVar.zzw(r1);
        zzadVar.zzT(r0);
        zzadVar.zzB(zzxVar);
        zzadVar.zzK(str2);
        return zzadVar.zzY();
    }

    public static zzaf zzd(zzed zzedVar, String str, String str2, zzx zzxVar) {
        zzedVar.zzG(2);
        int r1 = zzc[(zzedVar.zzk() & 192) >> 6];
        int zzk = zzedVar.zzk();
        int r3 = zze[(zzk & 14) >> 1];
        if ((zzk & 1) != 0) {
            r3++;
        }
        if (((zzedVar.zzk() & 30) >> 1) > 0 && (2 & zzedVar.zzk()) != 0) {
            r3 += 2;
        }
        String str3 = (zzedVar.zza() <= 0 || (zzedVar.zzk() & 1) == 0) ? MimeTypes.AUDIO_E_AC3 : MimeTypes.AUDIO_E_AC3_JOC;
        zzad zzadVar = new zzad();
        zzadVar.zzH(str);
        zzadVar.zzS(str3);
        zzadVar.zzw(r3);
        zzadVar.zzT(r1);
        zzadVar.zzB(zzxVar);
        zzadVar.zzK(str2);
        return zzadVar.zzY();
    }

    public static zzyf zze(zzec zzecVar) {
        String str;
        int r24;
        int r23;
        int r22;
        int r21;
        int r25;
        int zzc2;
        int r14;
        int r15;
        int r1;
        int r3;
        int r2;
        int zzb2 = zzecVar.zzb();
        zzecVar.zzj(40);
        int zzc3 = zzecVar.zzc(5);
        zzecVar.zzh(zzb2);
        int r4 = -1;
        if (zzc3 > 10) {
            zzecVar.zzj(16);
            int zzc4 = zzecVar.zzc(2);
            if (zzc4 == 0) {
                r4 = 0;
            } else if (zzc4 == 1) {
                r4 = 1;
            } else if (zzc4 == 2) {
                r4 = 2;
            }
            zzecVar.zzj(3);
            int zzc5 = zzecVar.zzc(11) + 1;
            int r10 = zzc5 + zzc5;
            int zzc6 = zzecVar.zzc(2);
            if (zzc6 == 3) {
                r15 = zzd[zzecVar.zzc(2)];
                zzc2 = 3;
                r14 = 6;
            } else {
                zzc2 = zzecVar.zzc(2);
                r14 = zzb[zzc2];
                r15 = zzc[zzc6];
            }
            int r11 = r14 * 256;
            int zzc7 = zzecVar.zzc(3);
            boolean zzl = zzecVar.zzl();
            int r18 = zze[zzc7] + (zzl ? 1 : 0);
            zzecVar.zzj(10);
            if (zzecVar.zzl()) {
                zzecVar.zzj(8);
            }
            if (zzc7 == 0) {
                zzecVar.zzj(5);
                if (zzecVar.zzl()) {
                    zzecVar.zzj(8);
                }
                r1 = 0;
                zzc7 = 0;
            } else {
                r1 = zzc7;
            }
            if (r4 == 1) {
                if (zzecVar.zzl()) {
                    zzecVar.zzj(16);
                }
                r3 = 1;
            } else {
                r3 = r4;
            }
            if (zzecVar.zzl()) {
                if (r1 > 2) {
                    zzecVar.zzj(2);
                }
                if ((r1 & 1) != 0 && r1 > 2) {
                    zzecVar.zzj(6);
                }
                if ((r1 & 4) != 0) {
                    zzecVar.zzj(6);
                }
                if (zzl && zzecVar.zzl()) {
                    zzecVar.zzj(5);
                }
                if (r3 == 0) {
                    if (zzecVar.zzl()) {
                        zzecVar.zzj(6);
                    }
                    if (r1 == 0 && zzecVar.zzl()) {
                        zzecVar.zzj(6);
                    }
                    if (zzecVar.zzl()) {
                        zzecVar.zzj(6);
                    }
                    int zzc8 = zzecVar.zzc(2);
                    if (zzc8 == 1) {
                        zzecVar.zzj(5);
                    } else if (zzc8 == 2) {
                        zzecVar.zzj(12);
                    } else if (zzc8 == 3) {
                        int zzc9 = zzecVar.zzc(5);
                        if (zzecVar.zzl()) {
                            zzecVar.zzj(5);
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(4);
                            }
                            if (zzecVar.zzl()) {
                                if (zzecVar.zzl()) {
                                    zzecVar.zzj(4);
                                }
                                if (zzecVar.zzl()) {
                                    zzecVar.zzj(4);
                                }
                            }
                        }
                        if (zzecVar.zzl()) {
                            zzecVar.zzj(5);
                            if (zzecVar.zzl()) {
                                zzecVar.zzj(7);
                                if (zzecVar.zzl()) {
                                    zzecVar.zzj(8);
                                    zzecVar.zzj((zzc9 + 2) * 8);
                                    zzecVar.zzd();
                                }
                            }
                        }
                        zzecVar.zzj((zzc9 + 2) * 8);
                        zzecVar.zzd();
                    }
                    if (r1 < 2) {
                        if (zzecVar.zzl()) {
                            zzecVar.zzj(14);
                        }
                        if (zzc7 == 0 && zzecVar.zzl()) {
                            zzecVar.zzj(14);
                        }
                    }
                    if (zzecVar.zzl()) {
                        if (zzc2 == 0) {
                            zzecVar.zzj(5);
                            r3 = 0;
                            zzc2 = 0;
                        } else {
                            for (int r32 = 0; r32 < r14; r32++) {
                                if (zzecVar.zzl()) {
                                    zzecVar.zzj(5);
                                }
                            }
                        }
                    }
                    r3 = 0;
                }
            }
            if (zzecVar.zzl()) {
                zzecVar.zzj(5);
                if (r1 == 2) {
                    zzecVar.zzj(4);
                    r1 = 2;
                }
                if (r1 >= 6) {
                    zzecVar.zzj(2);
                }
                if (zzecVar.zzl()) {
                    r2 = 8;
                    zzecVar.zzj(8);
                } else {
                    r2 = 8;
                }
                if (r1 == 0 && zzecVar.zzl()) {
                    zzecVar.zzj(r2);
                }
                if (zzc6 < 3) {
                    zzecVar.zzi();
                }
            }
            if (r3 == 0 && zzc2 != 3) {
                zzecVar.zzi();
            }
            if (r3 == 2 && (zzc2 == 3 || zzecVar.zzl())) {
                zzecVar.zzj(6);
            }
            str = (zzecVar.zzl() && zzecVar.zzc(6) == 1 && zzecVar.zzc(8) == 1) ? MimeTypes.AUDIO_E_AC3_JOC : MimeTypes.AUDIO_E_AC3;
            r21 = r4;
            r24 = r10;
            r25 = r11;
            r23 = r15;
            r22 = r18;
        } else {
            zzecVar.zzj(32);
            int zzc10 = zzecVar.zzc(2);
            String str2 = zzc10 == 3 ? null : MimeTypes.AUDIO_AC3;
            int zzf2 = zzf(zzc10, zzecVar.zzc(6));
            zzecVar.zzj(8);
            int zzc11 = zzecVar.zzc(3);
            if ((zzc11 & 1) != 0 && zzc11 != 1) {
                zzecVar.zzj(2);
            }
            if ((zzc11 & 4) != 0) {
                zzecVar.zzj(2);
            }
            if (zzc11 == 2) {
                zzecVar.zzj(2);
            }
            str = str2;
            r24 = zzf2;
            r23 = zzc10 < 3 ? zzc[zzc10] : -1;
            r22 = zze[zzc11] + (zzecVar.zzl() ? 1 : 0);
            r21 = -1;
            r25 = 1536;
        }
        return new zzyf(str, r21, r22, r23, r24, r25, null);
    }

    private static int zzf(int r2, int r3) {
        int r0 = r3 / 2;
        if (r2 < 0 || r2 >= 3 || r3 < 0 || r0 >= 19) {
            return -1;
        }
        int r22 = zzc[r2];
        if (r22 == 44100) {
            int r23 = zzg[r0] + (r3 & 1);
            return r23 + r23;
        }
        int r32 = zzf[r0];
        return r22 == 32000 ? r32 * 6 : r32 * 4;
    }
}
