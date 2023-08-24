package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabz {
    private static final String[] zza = {"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};
    private static final String[] zzb = {"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
    private static final String[] zzc = {"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};

    public static zzabv zza(String str) throws IOException {
        long j;
        try {
            XmlPullParser newPullParser = XmlPullParserFactory.newInstance().newPullParser();
            newPullParser.setInput(new StringReader(str));
            newPullParser.next();
            if (!zzem.zzc(newPullParser, "x:xmpmeta")) {
                throw zzbu.zza("Couldn't find xmp metadata", null);
            }
            zzfuv zzo = zzfuv.zzo();
            long j2 = -9223372036854775807L;
            do {
                newPullParser.next();
                if (zzem.zzc(newPullParser, "rdf:Description")) {
                    String[] strArr = zza;
                    int r6 = 0;
                    for (int r7 = 0; r7 < 4; r7++) {
                        String zza2 = zzem.zza(newPullParser, strArr[r7]);
                        if (zza2 != null) {
                            if (Integer.parseInt(zza2) != 1) {
                                return null;
                            } else {
                                String[] strArr2 = zzb;
                                int r72 = 0;
                                while (true) {
                                    if (r72 >= 4) {
                                        break;
                                    }
                                    String zza3 = zzem.zza(newPullParser, strArr2[r72]);
                                    if (zza3 != null) {
                                        j = Long.parseLong(zza3);
                                        if (j == -1) {
                                        }
                                    } else {
                                        r72++;
                                    }
                                }
                                j = -9223372036854775807L;
                                String[] strArr3 = zzc;
                                while (true) {
                                    if (r6 < 2) {
                                        String zza4 = zzem.zza(newPullParser, strArr3[r6]);
                                        if (zza4 != null) {
                                            zzo = zzfuv.zzq(new zzabu(MimeTypes.IMAGE_JPEG, "Primary", 0L, 0L), new zzabu(MimeTypes.VIDEO_MP4, "MotionPhoto", Long.parseLong(zza4), 0L));
                                            break;
                                        }
                                        r6++;
                                    } else {
                                        zzo = zzfuv.zzo();
                                        break;
                                    }
                                }
                                j2 = j;
                            }
                        }
                    }
                    return null;
                } else if (zzem.zzc(newPullParser, "Container:Directory")) {
                    zzo = zzb(newPullParser, "Container", "Item");
                } else if (zzem.zzc(newPullParser, "GContainer:Directory")) {
                    zzo = zzb(newPullParser, "GContainer", "GContainerItem");
                }
            } while (!zzem.zzb(newPullParser, "x:xmpmeta"));
            if (zzo.isEmpty()) {
                return null;
            }
            return new zzabv(j2, zzo);
        } catch (zzbu | NumberFormatException | XmlPullParserException unused) {
            Log.w("MotionPhotoXmpParser", "Ignoring unexpected XMP metadata");
            return null;
        }
    }

    private static zzfuv zzb(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        String concat = str.concat(":Item");
        String concat2 = str.concat(":Directory");
        zzfus zzi = zzfuv.zzi();
        do {
            xmlPullParser.next();
            if (zzem.zzc(xmlPullParser, concat)) {
                String zza2 = zzem.zza(xmlPullParser, str2.concat(":Mime"));
                String zza3 = zzem.zza(xmlPullParser, str2.concat(":Semantic"));
                String zza4 = zzem.zza(xmlPullParser, str2.concat(":Length"));
                String zza5 = zzem.zza(xmlPullParser, str2.concat(":Padding"));
                if (zza2 != null && zza3 != null) {
                    zzi.zze(new zzabu(zza2, zza3, zza4 != null ? Long.parseLong(zza4) : 0L, zza5 != null ? Long.parseLong(zza5) : 0L));
                } else {
                    return zzfuv.zzo();
                }
            }
        } while (!zzem.zzb(xmlPullParser, concat2));
        return zzi.zzg();
    }
}
