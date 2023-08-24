package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.metadata.id3.InternalFrame;
import com.google.common.net.HttpHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzawf {
    private static final int zza = zzban.zzg("nam");
    private static final int zzb = zzban.zzg("trk");
    private static final int zzc = zzban.zzg("cmt");
    private static final int zzd = zzban.zzg("day");
    private static final int zze = zzban.zzg("ART");
    private static final int zzf = zzban.zzg("too");
    private static final int zzg = zzban.zzg("alb");
    private static final int zzh = zzban.zzg("com");
    private static final int zzi = zzban.zzg("wrt");
    private static final int zzj = zzban.zzg("lyr");
    private static final int zzk = zzban.zzg("gen");
    private static final int zzl = zzban.zzg("covr");
    private static final int zzm = zzban.zzg("gnre");
    private static final int zzn = zzban.zzg("grp");
    private static final int zzo = zzban.zzg("disk");
    private static final int zzp = zzban.zzg("trkn");
    private static final int zzq = zzban.zzg("tmpo");
    private static final int zzr = zzban.zzg("cpil");
    private static final int zzs = zzban.zzg("aART");
    private static final int zzt = zzban.zzg("sonm");
    private static final int zzu = zzban.zzg("soal");
    private static final int zzv = zzban.zzg("soar");
    private static final int zzw = zzban.zzg("soaa");
    private static final int zzx = zzban.zzg("soco");
    private static final int zzy = zzban.zzg("rtng");
    private static final int zzz = zzban.zzg("pgap");
    private static final int zzA = zzban.zzg("sosn");
    private static final int zzB = zzban.zzg("tvsh");
    private static final int zzC = zzban.zzg(InternalFrame.f230ID);
    private static final String[] zzD = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    /* JADX WARN: Removed duplicated region for block: B:13:0x0035 A[Catch: all -> 0x01d3, TryCatch #0 {all -> 0x01d3, blocks: (B:4:0x001c, B:6:0x0021, B:8:0x0027, B:10:0x002e, B:13:0x0035, B:14:0x003c, B:17:0x0045, B:19:0x0049, B:22:0x0053, B:24:0x0057, B:27:0x0061, B:29:0x0067, B:32:0x0071, B:34:0x0075, B:37:0x007f, B:39:0x0084, B:41:0x0090, B:48:0x00ab, B:49:0x00c0, B:50:0x00d2, B:53:0x00db, B:55:0x00df, B:58:0x00e9, B:60:0x00ed, B:63:0x00f7, B:65:0x00fb, B:68:0x0105, B:70:0x0109, B:73:0x0113, B:75:0x0117, B:78:0x0121, B:80:0x0125, B:83:0x012f, B:85:0x0133, B:88:0x013d, B:90:0x0141, B:93:0x014b, B:95:0x014f, B:98:0x0159, B:100:0x015d, B:103:0x0167, B:106:0x016f, B:108:0x0175, B:110:0x0188, B:111:0x018f, B:113:0x0193, B:114:0x019a, B:120:0x01a3, B:121:0x01a9, B:123:0x01b1, B:127:0x01bc, B:186:0x0280, B:133:0x01da, B:135:0x01de, B:137:0x01ea, B:138:0x01fb, B:141:0x020c, B:143:0x0210, B:146:0x0216, B:148:0x021a, B:151:0x0220, B:153:0x0224, B:156:0x022e, B:158:0x0232, B:161:0x023c, B:163:0x0240, B:166:0x024a, B:168:0x024e, B:171:0x0258, B:173:0x025c, B:176:0x0266, B:178:0x026a, B:181:0x0272, B:183:0x0276, B:189:0x029c, B:192:0x02a6), top: B:197:0x001a }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003c A[Catch: all -> 0x01d3, TRY_LEAVE, TryCatch #0 {all -> 0x01d3, blocks: (B:4:0x001c, B:6:0x0021, B:8:0x0027, B:10:0x002e, B:13:0x0035, B:14:0x003c, B:17:0x0045, B:19:0x0049, B:22:0x0053, B:24:0x0057, B:27:0x0061, B:29:0x0067, B:32:0x0071, B:34:0x0075, B:37:0x007f, B:39:0x0084, B:41:0x0090, B:48:0x00ab, B:49:0x00c0, B:50:0x00d2, B:53:0x00db, B:55:0x00df, B:58:0x00e9, B:60:0x00ed, B:63:0x00f7, B:65:0x00fb, B:68:0x0105, B:70:0x0109, B:73:0x0113, B:75:0x0117, B:78:0x0121, B:80:0x0125, B:83:0x012f, B:85:0x0133, B:88:0x013d, B:90:0x0141, B:93:0x014b, B:95:0x014f, B:98:0x0159, B:100:0x015d, B:103:0x0167, B:106:0x016f, B:108:0x0175, B:110:0x0188, B:111:0x018f, B:113:0x0193, B:114:0x019a, B:120:0x01a3, B:121:0x01a9, B:123:0x01b1, B:127:0x01bc, B:186:0x0280, B:133:0x01da, B:135:0x01de, B:137:0x01ea, B:138:0x01fb, B:141:0x020c, B:143:0x0210, B:146:0x0216, B:148:0x021a, B:151:0x0220, B:153:0x0224, B:156:0x022e, B:158:0x0232, B:161:0x023c, B:163:0x0240, B:166:0x024a, B:168:0x024e, B:171:0x0258, B:173:0x025c, B:176:0x0266, B:178:0x026a, B:181:0x0272, B:183:0x0276, B:189:0x029c, B:192:0x02a6), top: B:197:0x001a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.internal.ads.zzaxc zza(com.google.android.gms.internal.ads.zzbag r13) {
        /*
            Method dump skipped, instructions count: 692
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawf.zza(com.google.android.gms.internal.ads.zzbag):com.google.android.gms.internal.ads.zzaxc");
    }

    private static int zzb(zzbag zzbagVar) {
        zzbagVar.zzw(4);
        if (zzbagVar.zze() == zzavs.zzaH) {
            zzbagVar.zzw(8);
            return zzbagVar.zzg();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }

    private static zzaxj zzc(int r0, String str, zzbag zzbagVar, boolean z, boolean z2) {
        int zzb2 = zzb(zzbagVar);
        if (z2) {
            zzb2 = Math.min(1, zzb2);
        }
        if (zzb2 < 0) {
            Log.w("MetadataUtil", "Failed to parse uint8 attribute: ".concat(zzavs.zzg(r0)));
            return null;
        } else if (z) {
            return new zzaxl(str, null, Integer.toString(zzb2));
        } else {
            return new zzaxh(C1856C.LANGUAGE_UNDETERMINED, str, Integer.toString(zzb2));
        }
    }

    private static zzaxl zzd(int r4, String str, zzbag zzbagVar) {
        int zze2 = zzbagVar.zze();
        if (zzbagVar.zze() == zzavs.zzaH && zze2 >= 22) {
            zzbagVar.zzw(10);
            int zzj2 = zzbagVar.zzj();
            if (zzj2 > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(zzj2);
                String sb2 = sb.toString();
                int zzj3 = zzbagVar.zzj();
                if (zzj3 > 0) {
                    sb2 = sb2 + "/" + zzj3;
                }
                return new zzaxl(str, null, sb2);
            }
        }
        Log.w("MetadataUtil", "Failed to parse index/count attribute: ".concat(zzavs.zzg(r4)));
        return null;
    }

    private static zzaxl zze(int r4, String str, zzbag zzbagVar) {
        int zze2 = zzbagVar.zze();
        if (zzbagVar.zze() == zzavs.zzaH) {
            zzbagVar.zzw(8);
            return new zzaxl(str, null, zzbagVar.zzo(zze2 - 16));
        }
        Log.w("MetadataUtil", "Failed to parse text attribute: ".concat(zzavs.zzg(r4)));
        return null;
    }
}
