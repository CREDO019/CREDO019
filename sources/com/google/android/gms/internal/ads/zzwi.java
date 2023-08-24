package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.p001v4.media.session.PlaybackStateCompat;
import com.amplitude.api.Constants;
import com.google.android.exoplayer2.ExoPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwi implements zzwe, zzfx {
    public static final zzfuv zza = zzfuv.zzs(4800000L, 3100000L, 2100000L, 1500000L, 800000L);
    public static final zzfuv zzb = zzfuv.zzs(1500000L, 1000000L, 730000L, 440000L, 170000L);
    public static final zzfuv zzc = zzfuv.zzs(2200000L, 1400000L, 1100000L, 910000L, 620000L);
    public static final zzfuv zzd = zzfuv.zzs(3000000L, 1900000L, 1400000L, 1000000L, 660000L);
    public static final zzfuv zze = zzfuv.zzs(6000000L, 4100000L, 3200000L, Long.valueOf((long) Constants.SESSION_TIMEOUT_MILLIS), 1000000L);
    public static final zzfuv zzf = zzfuv.zzs(2800000L, 2400000L, 1600000L, 1100000L, 950000L);
    private static zzwi zzg;
    private final zzfuy zzh;
    private final zzwc zzi;
    private final zzwy zzj;
    private final zzde zzk;
    private final boolean zzl;
    private int zzm;
    private long zzn;
    private long zzo;
    private int zzp;
    private long zzq;
    private long zzr;
    private long zzs;
    private long zzt;

    @Deprecated
    public zzwi() {
        zzfuy.zzd();
        zzde zzdeVar = zzde.zza;
        throw null;
    }

    private final long zzi(int r3) {
        Long l = (Long) this.zzh.get(Integer.valueOf(r3));
        if (l == null) {
            l = (Long) this.zzh.get(0);
        }
        if (l == null) {
            l = 1000000L;
        }
        return l.longValue();
    }

    private final void zzj(int r10, long j, long j2) {
        int r4;
        long j3;
        if (r10 == 0) {
            if (j != 0) {
                j3 = j;
            } else if (j2 == this.zzt) {
                return;
            } else {
                j3 = 0;
            }
            r4 = 0;
        } else {
            r4 = r10;
            j3 = j;
        }
        this.zzt = j2;
        this.zzi.zzb(r4, j3, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzk(int r9) {
        int r0 = this.zzp;
        if (r0 == 0 || this.zzl) {
            if (r0 == r9) {
                return;
            }
            this.zzp = r9;
            if (r9 != 1 && r9 != 0 && r9 != 8) {
                this.zzs = zzi(r9);
                long elapsedRealtime = SystemClock.elapsedRealtime();
                zzj(this.zzm > 0 ? (int) (elapsedRealtime - this.zzn) : 0, this.zzo, this.zzs);
                this.zzn = elapsedRealtime;
                this.zzo = 0L;
                this.zzr = 0L;
                this.zzq = 0L;
                this.zzj.zzc();
            }
        }
    }

    private static boolean zzl(zzfa zzfaVar, boolean z) {
        return z && !zzfaVar.zzb(8);
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0136, code lost:
        if (r3.equals("VU") != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x014e, code lost:
        if (r3.equals("VI") != false) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0166, code lost:
        if (r3.equals("VE") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0170, code lost:
        if (r3.equals("VC") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x017a, code lost:
        if (r3.equals("VA") != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x0192, code lost:
        if (r3.equals("UY") != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01aa, code lost:
        if (r3.equals("UG") != false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x01b4, code lost:
        if (r3.equals("UA") != false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01cc, code lost:
        if (r3.equals("TW") != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x01d6, code lost:
        if (r3.equals("TV") != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x01fc, code lost:
        if (r3.equals("TO") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0206, code lost:
        if (r3.equals("TN") != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0210, code lost:
        if (r3.equals("TM") != false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x021a, code lost:
        if (r3.equals("TL") != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0232, code lost:
        if (r3.equals("TJ") != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x024a, code lost:
        if (r3.equals("TG") != false) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x0254, code lost:
        if (r3.equals("TD") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x025e, code lost:
        if (r3.equals("TC") != false) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0275, code lost:
        if (r3.equals("SY") != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x027f, code lost:
        if (r3.equals("SX") != false) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0286, code lost:
        return new int[]{1, 2, 1, 0, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x028d, code lost:
        if (r3.equals("SV") != false) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x02c1, code lost:
        if (r3.equals("SO") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x02d9, code lost:
        if (r3.equals("SM") != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:226:0x02e3, code lost:
        if (r3.equals("SL") != false) goto L147;
     */
    /* JADX WARN: Code restructure failed: missing block: B:228:0x02ea, code lost:
        return new int[]{3, 3, 4, 3, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:230:0x02f1, code lost:
        if (r3.equals("SK") != false) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:233:0x02fb, code lost:
        if (r3.equals("SI") != false) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:236:0x0305, code lost:
        if (r3.equals("SH") != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:243:0x031d, code lost:
        if (r3.equals("SE") != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:246:0x0327, code lost:
        if (r3.equals("SD") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x0331, code lost:
        if (r3.equals("SC") != false) goto L179;
     */
    /* JADX WARN: Code restructure failed: missing block: B:251:0x0338, code lost:
        return new int[]{4, 2, 1, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:269:0x0377, code lost:
        if (r3.equals("RS") != false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:276:0x038f, code lost:
        if (r3.equals("RE") != false) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:287:0x03b5, code lost:
        if (r3.equals("PT") != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:298:0x03db, code lost:
        if (r3.equals("PM") != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0044, code lost:
        if (r3.equals("CI") != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:301:0x03e5, code lost:
        if (r3.equals("PL") != false) goto L311;
     */
    /* JADX WARN: Code restructure failed: missing block: B:304:0x03ee, code lost:
        if (r3.equals("PK") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:311:0x0406, code lost:
        if (r3.equals(com.google.android.gms.ads.RequestConfiguration.MAX_AD_CONTENT_RATING_PG) != false) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:314:0x0410, code lost:
        if (r3.equals("PF") != false) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:317:0x041a, code lost:
        if (r3.equals("PE") != false) goto L329;
     */
    /* JADX WARN: Code restructure failed: missing block: B:320:0x0424, code lost:
        if (r3.equals("PA") != false) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:327:0x043b, code lost:
        if (r3.equals("NZ") != false) goto L311;
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x0442, code lost:
        return new int[]{1, 1, 2, 2, 4, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:331:0x0449, code lost:
        if (r3.equals("NU") != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:334:0x0452, code lost:
        if (r3.equals("NR") != false) goto L341;
     */
    /* JADX WARN: Code restructure failed: missing block: B:336:0x0459, code lost:
        return new int[]{4, 2, 2, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x046e, code lost:
        if (r3.equals("NO") != false) goto L351;
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x0486, code lost:
        if (r3.equals("NI") != false) goto L215;
     */
    /* JADX WARN: Code restructure failed: missing block: B:351:0x048d, code lost:
        return new int[]{2, 3, 3, 3, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:357:0x04a2, code lost:
        if (r3.equals("NE") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:364:0x04ba, code lost:
        if (r3.equals("NA") != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005c, code lost:
        if (r3.equals("CG") != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:391:0x0518, code lost:
        if (r3.equals("MT") != false) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:394:0x0522, code lost:
        if (r3.equals("MS") != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:397:0x052c, code lost:
        if (r3.equals("MR") != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:399:0x0533, code lost:
        return new int[]{4, 2, 4, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0066, code lost:
        if (r3.equals("CF") != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:401:0x053a, code lost:
        if (r3.equals("MQ") != false) goto L403;
     */
    /* JADX WARN: Code restructure failed: missing block: B:404:0x0544, code lost:
        if (r3.equals("MP") != false) goto L407;
     */
    /* JADX WARN: Code restructure failed: missing block: B:407:0x054d, code lost:
        if (r3.equals("MO") != false) goto L407;
     */
    /* JADX WARN: Code restructure failed: missing block: B:409:0x0554, code lost:
        return new int[]{0, 2, 4, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x0577, code lost:
        if (r3.equals("ML") != false) goto L321;
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x057e, code lost:
        return new int[]{4, 3, 3, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x0593, code lost:
        if (r3.equals("MH") != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:430:0x059d, code lost:
        if (r3.equals("MG") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x05a7, code lost:
        if (r3.equals("MF") != false) goto L287;
     */
    /* JADX WARN: Code restructure failed: missing block: B:435:0x05ae, code lost:
        return new int[]{1, 2, 1, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:441:0x05c3, code lost:
        if (r3.equals("MD") != false) goto L279;
     */
    /* JADX WARN: Code restructure failed: missing block: B:443:0x05ca, code lost:
        return new int[]{1, 0, 0, 0, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:453:0x05ed, code lost:
        if (r3.equals("LY") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:456:0x05f7, code lost:
        if (r3.equals("LV") != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x007e, code lost:
        if (r3.equals("BN") != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:471:0x062b, code lost:
        if (r3.equals("LR") != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:478:0x0643, code lost:
        if (r3.equals("LI") != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:481:0x064d, code lost:
        if (r3.equals("LC") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:496:0x0681, code lost:
        if (r3.equals("KY") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x068b, code lost:
        if (r3.equals("KW") != false) goto L487;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0088, code lost:
        if (r3.equals("BM") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:506:0x06a3, code lost:
        if (r3.equals("KP") != false) goto L495;
     */
    /* JADX WARN: Code restructure failed: missing block: B:509:0x06ad, code lost:
        if (r3.equals("KN") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:512:0x06b7, code lost:
        if (r3.equals("KM") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:515:0x06c1, code lost:
        if (r3.equals("KI") != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:517:0x06c8, code lost:
        return new int[]{4, 2, 4, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:523:0x06dd, code lost:
        if (r3.equals(com.google.zxing.client.result.ExpandedProductParsedResult.KILOGRAM) != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:525:0x06e4, code lost:
        return new int[]{2, 1, 1, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:527:0x06eb, code lost:
        if (r3.equals("KE") != false) goto L495;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0092, code lost:
        if (r3.equals("BL") != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:542:0x071f, code lost:
        if (r3.equals("JE") != false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:544:0x0726, code lost:
        return new int[]{4, 2, 2, 3, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:550:0x073b, code lost:
        if (r3.equals("IS") != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:561:0x0761, code lost:
        if (r3.equals("IO") != false) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:563:0x0768, code lost:
        return new int[]{4, 2, 2, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:569:0x077d, code lost:
        if (r3.equals("IM") != false) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:571:0x0784, code lost:
        return new int[]{0, 2, 1, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:573:0x078b, code lost:
        if (r3.equals("IL") != false) goto L549;
     */
    /* JADX WARN: Code restructure failed: missing block: B:576:0x0795, code lost:
        if (r3.equals("IE") != false) goto L553;
     */
    /* JADX WARN: Code restructure failed: missing block: B:587:0x07bb, code lost:
        if (r3.equals("HT") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:590:0x07c5, code lost:
        if (r3.equals("HR") != false) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:601:0x07ea, code lost:
        if (r3.equals("GW") != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:604:0x07f4, code lost:
        if (r3.equals("GU") != false) goto L329;
     */
    /* JADX WARN: Code restructure failed: missing block: B:606:0x07fb, code lost:
        return new int[]{1, 2, 4, 4, 4, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:612:0x0810, code lost:
        if (r3.equals("GR") != false) goto L247;
     */
    /* JADX WARN: Code restructure failed: missing block: B:614:0x0817, code lost:
        return new int[]{1, 0, 0, 0, 1, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:620:0x082c, code lost:
        if (r3.equals("GP") != false) goto L403;
     */
    /* JADX WARN: Code restructure failed: missing block: B:622:0x0833, code lost:
        return new int[]{2, 1, 2, 3, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:628:0x0848, code lost:
        if (r3.equals("GM") != false) goto L597;
     */
    /* JADX WARN: Code restructure failed: missing block: B:631:0x0852, code lost:
        if (r3.equals("GL") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:634:0x085c, code lost:
        if (r3.equals("GI") != false) goto L603;
     */
    /* JADX WARN: Code restructure failed: missing block: B:637:0x0866, code lost:
        if (r3.equals("GH") != false) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:639:0x086d, code lost:
        return new int[]{3, 3, 3, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:641:0x0874, code lost:
        if (r3.equals("GG") != false) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:643:0x087b, code lost:
        return new int[]{0, 2, 0, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:645:0x0882, code lost:
        if (r3.equals("GF") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:652:0x089a, code lost:
        if (r3.equals("GD") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:659:0x08b2, code lost:
        if (r3.equals("GA") != false) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:661:0x08b9, code lost:
        return new int[]{3, 4, 1, 0, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:667:0x08ce, code lost:
        if (r3.equals("FO") != false) goto L603;
     */
    /* JADX WARN: Code restructure failed: missing block: B:674:0x08e6, code lost:
        if (r3.equals("FK") != false) goto L495;
     */
    /* JADX WARN: Code restructure failed: missing block: B:676:0x08ed, code lost:
        return new int[]{3, 2, 2, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00c6, code lost:
        if (r3.equals("AR") != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:690:0x091e, code lost:
        if (r3.equals("ES") != false) goto L553;
     */
    /* JADX WARN: Code restructure failed: missing block: B:692:0x0925, code lost:
        return new int[]{0, 1, 1, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:694:0x092c, code lost:
        if (r3.equals("ER") != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:696:0x0933, code lost:
        return new int[]{4, 2, 2, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:698:0x093a, code lost:
        if (r3.equals("EG") != false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:700:0x0941, code lost:
        return new int[]{3, 4, 3, 3, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:702:0x0948, code lost:
        if (r3.equals("EE") != false) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:704:0x094f, code lost:
        return new int[]{0, 0, 0, 0, 0, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00d0, code lost:
        if (r3.equals("AQ") != false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:710:0x0964, code lost:
        if (r3.equals("DZ") != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:712:0x096b, code lost:
        return new int[]{3, 4, 4, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:718:0x0980, code lost:
        if (r3.equals("DM") != false) goto L603;
     */
    /* JADX WARN: Code restructure failed: missing block: B:725:0x0998, code lost:
        if (r3.equals("DJ") != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:732:0x09af, code lost:
        if (r3.equals("CZ") != false) goto L351;
     */
    /* JADX WARN: Code restructure failed: missing block: B:734:0x09b6, code lost:
        return new int[]{0, 0, 2, 0, 1, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:736:0x09bd, code lost:
        if (r3.equals("CY") != false) goto L487;
     */
    /* JADX WARN: Code restructure failed: missing block: B:738:0x09c4, code lost:
        return new int[]{1, 0, 0, 0, 0, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00da, code lost:
        if (r3.equals("ZW") != false) goto L83;
     */
    /* JADX WARN: Code restructure failed: missing block: B:740:0x09cb, code lost:
        if (r3.equals("CX") != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:742:0x09d2, code lost:
        return new int[]{1, 2, 2, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:744:0x09d9, code lost:
        if (r3.equals("CW") != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:746:0x09e0, code lost:
        return new int[]{2, 2, 0, 0, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:752:0x09f5, code lost:
        if (r3.equals("CU") != false) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:754:0x09fc, code lost:
        return new int[]{4, 3, 4, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:772:0x0a3b, code lost:
        if (r3.equals("CL") != false) goto L549;
     */
    /* JADX WARN: Code restructure failed: missing block: B:774:0x0a42, code lost:
        return new int[]{1, 2, 2, 2, 3, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:776:0x0a49, code lost:
        if (r3.equals("CK") != false) goto L325;
     */
    /* JADX WARN: Code restructure failed: missing block: B:778:0x0a50, code lost:
        return new int[]{2, 2, 2, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:792:0x0a81, code lost:
        if (r3.equals("BQ") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:799:0x0a99, code lost:
        if (r3.equals("BI") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:801:0x0aa0, code lost:
        return new int[]{4, 4, 4, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:807:0x0ab5, code lost:
        if (r3.equals("BG") != false) goto L243;
     */
    /* JADX WARN: Code restructure failed: missing block: B:809:0x0abc, code lost:
        return new int[]{0, 0, 0, 0, 1, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:823:0x0aed, code lost:
        if (r3.equals("AZ") != false) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:825:0x0af4, code lost:
        return new int[]{3, 2, 3, 3, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:839:0x0b25, code lost:
        if (r3.equals("AF") != false) goto L597;
     */
    /* JADX WARN: Code restructure failed: missing block: B:841:0x0b2c, code lost:
        return new int[]{4, 3, 3, 4, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:847:0x0b41, code lost:
        if (r3.equals("AD") != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:849:0x0b48, code lost:
        return new int[]{1, 2, 0, 0, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0100, code lost:
        if (r3.equals("YT") != false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:867:0x0b87, code lost:
        if (r3.equals("BB") != false) goto L603;
     */
    /* JADX WARN: Code restructure failed: missing block: B:869:0x0b8e, code lost:
        return new int[]{0, 2, 0, 0, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:875:0x0ba3, code lost:
        if (r3.equals("AX") != false) goto L237;
     */
    /* JADX WARN: Code restructure failed: missing block: B:877:0x0baa, code lost:
        return new int[]{0, 2, 2, 2, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x010a, code lost:
        if (r3.equals("YE") != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:887:0x0bcd, code lost:
        if (r3.equals("AL") != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:889:0x0bd4, code lost:
        return new int[]{1, 1, 1, 1, 2, 2};
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0114, code lost:
        if (r3.equals("XK") != false) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x012c, code lost:
        if (r3.equals("WF") != false) goto L111;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] zzm(java.lang.String r3) {
        /*
            Method dump skipped, instructions count: 6360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzwi.zzm(java.lang.String):int[]");
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final synchronized void zza(zzev zzevVar, zzfa zzfaVar, boolean z, int r4) {
        if (zzl(zzfaVar, z)) {
            this.zzo += r4;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final synchronized void zzb(zzev zzevVar, zzfa zzfaVar, boolean z) {
        if (zzl(zzfaVar, z)) {
            zzdd.zzf(this.zzm > 0);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            int r3 = (int) (elapsedRealtime - this.zzn);
            this.zzq += r3;
            long j = this.zzr;
            long j2 = this.zzo;
            this.zzr = j + j2;
            if (r3 > 0) {
                this.zzj.zzb((int) Math.sqrt(j2), (((float) j2) * 8000.0f) / r3);
                if (this.zzq >= ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS || this.zzr >= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED) {
                    this.zzs = this.zzj.zza(0.5f);
                }
                zzj(r3, this.zzo, this.zzs);
                this.zzn = elapsedRealtime;
                this.zzo = 0L;
            }
            this.zzm--;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final void zzc(zzev zzevVar, zzfa zzfaVar, boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzfx
    public final synchronized void zzd(zzev zzevVar, zzfa zzfaVar, boolean z) {
        if (zzl(zzfaVar, z)) {
            if (this.zzm == 0) {
                this.zzn = SystemClock.elapsedRealtime();
            }
            this.zzm++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzwe
    public final void zzf(zzwd zzwdVar) {
        this.zzi.zzc(zzwdVar);
    }

    public static synchronized zzwi zzg(Context context) {
        zzwi zzwiVar;
        synchronized (zzwi.class) {
            if (zzg == null) {
                Context applicationContext = context == null ? null : context.getApplicationContext();
                int[] zzm = zzm(zzel.zzL(context));
                HashMap hashMap = new HashMap(8);
                hashMap.put(0, 1000000L);
                zzfuv zzfuvVar = zza;
                hashMap.put(2, (Long) zzfuvVar.get(zzm[0]));
                hashMap.put(3, (Long) zzb.get(zzm[1]));
                hashMap.put(4, (Long) zzc.get(zzm[2]));
                hashMap.put(5, (Long) zzd.get(zzm[3]));
                hashMap.put(10, (Long) zze.get(zzm[4]));
                hashMap.put(9, (Long) zzf.get(zzm[5]));
                hashMap.put(7, (Long) zzfuvVar.get(zzm[0]));
                zzg = new zzwi(applicationContext, hashMap, 2000, zzde.zza, true, null);
            }
            zzwiVar = zzg;
        }
        return zzwiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzwe
    public final void zze(Handler handler, zzwd zzwdVar) {
        Objects.requireNonNull(zzwdVar);
        this.zzi.zza(handler, zzwdVar);
    }

    /* synthetic */ zzwi(Context context, Map map, int r3, zzde zzdeVar, boolean z, zzwh zzwhVar) {
        this.zzh = zzfuy.zzc(map);
        this.zzi = new zzwc();
        this.zzj = new zzwy(2000);
        this.zzk = zzdeVar;
        this.zzl = true;
        if (context != null) {
            zzeb zzb2 = zzeb.zzb(context);
            int zza2 = zzb2.zza();
            this.zzp = zza2;
            this.zzs = zzi(zza2);
            zzb2.zzd(new zzwg(this));
            return;
        }
        this.zzp = 0;
        this.zzs = zzi(0);
    }
}