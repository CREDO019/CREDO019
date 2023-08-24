package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.extractor.avi.AviExtractor;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabd implements zzaav {
    public final zzfuv zza;
    private final int zzb;

    private zzabd(int r1, zzfuv zzfuvVar) {
        this.zzb = r1;
        this.zza = zzfuvVar;
    }

    public static zzabd zzc(int r16, zzed zzedVar) {
        String str;
        zzfus zzfusVar = new zzfus();
        int zzd = zzedVar.zzd();
        int r3 = -2;
        while (zzedVar.zza() > 8) {
            int zzg = zzedVar.zzg();
            int zzc = zzedVar.zzc() + zzedVar.zzg();
            zzedVar.zzE(zzc);
            zzaav zzaavVar = null;
            if (zzg != 1414744396) {
                switch (zzg) {
                    case AviExtractor.FOURCC_strf /* 1718776947 */:
                        if (r3 != 2) {
                            if (r3 == 1) {
                                int zzi = zzedVar.zzi();
                                String str2 = zzi != 1 ? zzi != 85 ? zzi != 255 ? zzi != 8192 ? zzi != 8193 ? null : MimeTypes.AUDIO_DTS : MimeTypes.AUDIO_AC3 : MimeTypes.AUDIO_AAC : MimeTypes.AUDIO_MPEG : MimeTypes.AUDIO_RAW;
                                if (str2 == null) {
                                    Log.w("StreamFormatChunk", "Ignoring track with unsupported format tag " + zzi);
                                    break;
                                } else {
                                    int zzi2 = zzedVar.zzi();
                                    int zzg2 = zzedVar.zzg();
                                    zzedVar.zzG(6);
                                    int zzn = zzel.zzn(zzedVar.zzo());
                                    int zzi3 = zzedVar.zzi();
                                    byte[] bArr = new byte[zzi3];
                                    zzedVar.zzB(bArr, 0, zzi3);
                                    zzad zzadVar = new zzad();
                                    zzadVar.zzS(str2);
                                    zzadVar.zzw(zzi2);
                                    zzadVar.zzT(zzg2);
                                    if (MimeTypes.AUDIO_RAW.equals(str2) && zzn != 0) {
                                        zzadVar.zzN(zzn);
                                    }
                                    if (MimeTypes.AUDIO_AAC.equals(str2) && zzi3 > 0) {
                                        zzadVar.zzI(zzfuv.zzp(bArr));
                                    }
                                    zzaavVar = new zzabe(zzadVar.zzY());
                                    break;
                                }
                            } else {
                                Log.w("StreamFormatChunk", "Ignoring strf box for unsupported track type: ".concat(zzel.zzO(r3)));
                                break;
                            }
                        } else {
                            zzedVar.zzG(4);
                            int zzg3 = zzedVar.zzg();
                            int zzg4 = zzedVar.zzg();
                            zzedVar.zzG(4);
                            int zzg5 = zzedVar.zzg();
                            switch (zzg5) {
                                case 808802372:
                                case 877677894:
                                case 1145656883:
                                case 1145656920:
                                case 1482049860:
                                case 1684633208:
                                case 2021026148:
                                    str = MimeTypes.VIDEO_MP4V;
                                    break;
                                case 826496577:
                                case 828601953:
                                case 875967048:
                                    str = MimeTypes.VIDEO_H264;
                                    break;
                                case 842289229:
                                    str = MimeTypes.VIDEO_MP42;
                                    break;
                                case 859066445:
                                    str = MimeTypes.VIDEO_MP43;
                                    break;
                                case 1196444237:
                                case 1735420525:
                                    str = MimeTypes.VIDEO_MJPEG;
                                    break;
                                default:
                                    str = null;
                                    break;
                            }
                            if (str == null) {
                                Log.w("StreamFormatChunk", "Ignoring track with unsupported compression " + zzg5);
                                break;
                            } else {
                                zzad zzadVar2 = new zzad();
                                zzadVar2.zzX(zzg3);
                                zzadVar2.zzF(zzg4);
                                zzadVar2.zzS(str);
                                zzaavVar = new zzabe(zzadVar2.zzY());
                                break;
                            }
                        }
                    case AviExtractor.FOURCC_avih /* 1751742049 */:
                        zzaavVar = zzaba.zzb(zzedVar);
                        break;
                    case AviExtractor.FOURCC_strh /* 1752331379 */:
                        zzaavVar = zzabb.zzb(zzedVar);
                        break;
                    case AviExtractor.FOURCC_strn /* 1852994675 */:
                        zzaavVar = zzabf.zzb(zzedVar);
                        break;
                }
            } else {
                zzaavVar = zzc(zzedVar.zzg(), zzedVar);
            }
            if (zzaavVar != null) {
                if (zzaavVar.zza() == 1752331379) {
                    int r32 = ((zzabb) zzaavVar).zza;
                    if (r32 == 1935960438) {
                        r3 = 2;
                    } else if (r32 == 1935963489) {
                        r3 = 1;
                    } else if (r32 != 1937012852) {
                        Log.w("AviStreamHeaderChunk", "Found unsupported streamType fourCC: ".concat(String.valueOf(Integer.toHexString(r32))));
                        r3 = -1;
                    } else {
                        r3 = 3;
                    }
                }
                zzfusVar.zze(zzaavVar);
            }
            zzedVar.zzF(zzc);
            zzedVar.zzE(zzd);
        }
        return new zzabd(r16, zzfusVar.zzg());
    }

    @Override // com.google.android.gms.internal.ads.zzaav
    public final int zza() {
        return this.zzb;
    }

    public final zzaav zzb(Class cls) {
        zzfuv zzfuvVar = this.zza;
        int size = zzfuvVar.size();
        int r2 = 0;
        while (r2 < size) {
            zzaav zzaavVar = (zzaav) zzfuvVar.get(r2);
            r2++;
            if (zzaavVar.getClass() == cls) {
                return zzaavVar;
            }
        }
        return null;
    }
}
