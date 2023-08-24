package com.google.android.gms.internal.ads;

import android.util.Log;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.net.HttpHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafj {
    static final String[] zza = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Afro-Punk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop", "Abstract", "Art Rock", "Baroque", "Bhangra", "Big beat", "Breakbeat", "Chillout", "Downtempo", "Dub", "EBM", "Eclectic", "Electro", "Electroclash", "Emo", "Experimental", "Garage", "Global", "IDM", "Illbient", "Industro-Goth", "Jam Band", "Krautrock", "Leftfield", "Lounge", "Math Rock", "New Romantic", "Nu-Breakz", "Post-Punk", "Post-Rock", "Psytrance", "Shoegaze", "Space Rock", "Trop Rock", "World Music", "Neoclassical", "Audiobook", "Audio theatre", "Neue Deutsche Welle", "Podcast", "Indie-Rock", "G-Funk", "Dubstep", "Garage Rock", "Psybient"};
    public static final /* synthetic */ int zzb = 0;

    public static zzbp zza(zzed zzedVar) {
        String str;
        int zzc = zzedVar.zzc() + zzedVar.zze();
        int zze = zzedVar.zze();
        int r2 = (zze >> 24) & 255;
        zzbp zzbpVar = null;
        try {
            if (r2 == 169 || r2 == 253) {
                int r22 = zze & ViewCompat.MEASURED_SIZE_MASK;
                if (r22 == 6516084) {
                    int zze2 = zzedVar.zze();
                    if (zzedVar.zze() == 1684108385) {
                        zzedVar.zzG(8);
                        String zzw = zzedVar.zzw(zze2 - 16);
                        zzbpVar = new zzact(C1856C.LANGUAGE_UNDETERMINED, zzw, zzw);
                    } else {
                        Log.w("MetadataUtil", "Failed to parse comment attribute: ".concat(zzaeu.zzf(zze)));
                    }
                    return zzbpVar;
                } else if (r22 == 7233901 || r22 == 7631467) {
                    return zze(zze, "TIT2", zzedVar);
                } else {
                    if (r22 == 6516589 || r22 == 7828084) {
                        return zze(zze, "TCOM", zzedVar);
                    }
                    if (r22 == 6578553) {
                        return zze(zze, "TDRC", zzedVar);
                    }
                    if (r22 == 4280916) {
                        return zze(zze, "TPE1", zzedVar);
                    }
                    if (r22 == 7630703) {
                        return zze(zze, "TSSE", zzedVar);
                    }
                    if (r22 == 6384738) {
                        return zze(zze, "TALB", zzedVar);
                    }
                    if (r22 == 7108978) {
                        return zze(zze, "USLT", zzedVar);
                    }
                    if (r22 == 6776174) {
                        return zze(zze, "TCON", zzedVar);
                    }
                    if (r22 == 6779504) {
                        return zze(zze, "TIT1", zzedVar);
                    }
                }
            } else if (zze == 1735291493) {
                int zzb2 = zzb(zzedVar);
                String str2 = (zzb2 <= 0 || zzb2 > 192) ? null : zza[zzb2 - 1];
                if (str2 != null) {
                    zzbpVar = new zzadi("TCON", null, str2);
                } else {
                    Log.w("MetadataUtil", "Failed to parse standard genre code");
                }
                return zzbpVar;
            } else if (zze == 1684632427) {
                return zzd(1684632427, "TPOS", zzedVar);
            } else {
                if (zze == 1953655662) {
                    return zzd(1953655662, "TRCK", zzedVar);
                }
                if (zze == 1953329263) {
                    return zzc(1953329263, "TBPM", zzedVar, true, false);
                }
                if (zze == 1668311404) {
                    return zzc(1668311404, "TCMP", zzedVar, true, true);
                }
                if (zze == 1668249202) {
                    int zze3 = zzedVar.zze();
                    if (zzedVar.zze() == 1684108385) {
                        int zze4 = zzedVar.zze() & ViewCompat.MEASURED_SIZE_MASK;
                        if (zze4 == 13) {
                            str = MimeTypes.IMAGE_JPEG;
                        } else if (zze4 == 14) {
                            str = "image/png";
                            zze4 = 14;
                        } else {
                            str = null;
                        }
                        if (str == null) {
                            Log.w("MetadataUtil", "Unrecognized cover art flags: " + zze4);
                        } else {
                            zzedVar.zzG(4);
                            int r1 = zze3 - 16;
                            byte[] bArr = new byte[r1];
                            zzedVar.zzB(bArr, 0, r1);
                            zzbpVar = new zzacl(str, null, 3, bArr);
                        }
                    } else {
                        Log.w("MetadataUtil", "Failed to parse cover art attribute");
                    }
                    return zzbpVar;
                } else if (zze == 1631670868) {
                    return zze(1631670868, "TPE2", zzedVar);
                } else {
                    if (zze == 1936682605) {
                        return zze(1936682605, "TSOT", zzedVar);
                    }
                    if (zze == 1936679276) {
                        return zze(1936679276, "TSO2", zzedVar);
                    }
                    if (zze == 1936679282) {
                        return zze(1936679282, "TSOA", zzedVar);
                    }
                    if (zze == 1936679265) {
                        return zze(1936679265, "TSOP", zzedVar);
                    }
                    if (zze == 1936679791) {
                        return zze(1936679791, "TSOC", zzedVar);
                    }
                    if (zze == 1920233063) {
                        return zzc(1920233063, "ITUNESADVISORY", zzedVar, false, false);
                    }
                    if (zze == 1885823344) {
                        return zzc(1885823344, "ITUNESGAPLESS", zzedVar, false, true);
                    }
                    if (zze == 1936683886) {
                        return zze(1936683886, "TVSHOWSORT", zzedVar);
                    }
                    if (zze == 1953919848) {
                        return zze(1953919848, "TVSHOW", zzedVar);
                    }
                    if (zze == 757935405) {
                        String str3 = null;
                        String str4 = null;
                        int r3 = -1;
                        int r4 = -1;
                        while (zzedVar.zzc() < zzc) {
                            int zzc2 = zzedVar.zzc();
                            int zze5 = zzedVar.zze();
                            int zze6 = zzedVar.zze();
                            zzedVar.zzG(4);
                            if (zze6 == 1835360622) {
                                str3 = zzedVar.zzw(zze5 - 12);
                            } else if (zze6 == 1851878757) {
                                str4 = zzedVar.zzw(zze5 - 12);
                            } else {
                                if (zze6 == 1684108385) {
                                    r4 = zze5;
                                }
                                if (zze6 == 1684108385) {
                                    r3 = zzc2;
                                }
                                zzedVar.zzG(zze5 - 12);
                            }
                        }
                        if (str3 != null && str4 != null && r3 != -1) {
                            zzedVar.zzF(r3);
                            zzedVar.zzG(16);
                            zzbpVar = new zzadc(str3, str4, zzedVar.zzw(r4 - 16));
                        }
                        return zzbpVar;
                    }
                }
            }
            Log.d("MetadataUtil", "Skipped unknown metadata entry: " + zzaeu.zzf(zze));
            return null;
        } finally {
            zzedVar.zzF(zzc);
        }
    }

    private static int zzb(zzed zzedVar) {
        zzedVar.zzG(4);
        if (zzedVar.zze() == 1684108385) {
            zzedVar.zzG(8);
            return zzedVar.zzk();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }

    private static zzada zzc(int r0, String str, zzed zzedVar, boolean z, boolean z2) {
        int zzb2 = zzb(zzedVar);
        if (z2) {
            zzb2 = Math.min(1, zzb2);
        }
        if (zzb2 < 0) {
            Log.w("MetadataUtil", "Failed to parse uint8 attribute: ".concat(zzaeu.zzf(r0)));
            return null;
        } else if (z) {
            return new zzadi(str, null, Integer.toString(zzb2));
        } else {
            return new zzact(C1856C.LANGUAGE_UNDETERMINED, str, Integer.toString(zzb2));
        }
    }

    private static zzadi zzd(int r4, String str, zzed zzedVar) {
        int zze = zzedVar.zze();
        if (zzedVar.zze() == 1684108385 && zze >= 22) {
            zzedVar.zzG(10);
            int zzo = zzedVar.zzo();
            if (zzo > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(zzo);
                String sb2 = sb.toString();
                int zzo2 = zzedVar.zzo();
                if (zzo2 > 0) {
                    sb2 = sb2 + "/" + zzo2;
                }
                return new zzadi(str, null, sb2);
            }
        }
        Log.w("MetadataUtil", "Failed to parse index/count attribute: ".concat(zzaeu.zzf(r4)));
        return null;
    }

    private static zzadi zze(int r4, String str, zzed zzedVar) {
        int zze = zzedVar.zze();
        if (zzedVar.zze() == 1684108385) {
            zzedVar.zzG(8);
            return new zzadi(str, null, zzedVar.zzw(zze - 16));
        }
        Log.w("MetadataUtil", "Failed to parse text attribute: ".concat(zzaeu.zzf(r4)));
        return null;
    }
}
