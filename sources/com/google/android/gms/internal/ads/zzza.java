package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzza implements zzzm {
    private static final int[] zza = {5, 4, 12, 8, 3, 10, 9, 11, 6, 2, 0, 1, 7, 16, 15, 14};
    private static final zzyz zzc = new zzyz(new zzyy() { // from class: com.google.android.gms.internal.ads.zzyw
        @Override // com.google.android.gms.internal.ads.zzyy
        public final Constructor zza() {
            if (Boolean.TRUE.equals(Class.forName("androidx.media3.decoder.flac.FlacLibrary").getMethod("isAvailable", new Class[0]).invoke(null, new Object[0]))) {
                return Class.forName("androidx.media3.decoder.flac.FlacExtractor").asSubclass(zzzf.class).getConstructor(Integer.TYPE);
            }
            return null;
        }
    });
    private static final zzyz zzd = new zzyz(new zzyy() { // from class: com.google.android.gms.internal.ads.zzyx
        @Override // com.google.android.gms.internal.ads.zzyy
        public final Constructor zza() {
            return Class.forName("androidx.media3.decoder.midi.MidiExtractor").asSubclass(zzzf.class).getConstructor(new Class[0]);
        }
    });

    private static final void zzc(int r3, List list) {
        switch (r3) {
            case 0:
                list.add(new zzago());
                return;
            case 1:
                list.add(new zzagr());
                return;
            case 2:
                list.add(new zzagu(0));
                return;
            case 3:
                list.add(new zzaau(0));
                return;
            case 4:
                zzzf zza2 = zzc.zza(0);
                if (zza2 != null) {
                    list.add(zza2);
                    return;
                } else {
                    list.add(new zzabl(0));
                    return;
                }
            case 5:
                list.add(new zzabo());
                return;
            case 6:
                list.add(new zzaef(0));
                return;
            case 7:
                list.add(new zzaen(0));
                return;
            case 8:
                list.add(new zzafi(0, null));
                list.add(new zzafn(0));
                return;
            case 9:
                list.add(new zzagc());
                return;
            case 10:
                list.add(new zzahx());
                return;
            case 11:
                list.add(new zzaih(1, 0, TsExtractor.DEFAULT_TIMESTAMP_SEARCH_BYTES));
                return;
            case 12:
                list.add(new zzait());
                return;
            case 13:
            default:
                return;
            case 14:
                list.add(new zzabt());
                return;
            case 15:
                zzzf zza3 = zzd.zza(new Object[0]);
                if (zza3 != null) {
                    list.add(zza3);
                    return;
                }
                return;
            case 16:
                list.add(new zzaaz());
                return;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzm
    public final synchronized zzzf[] zza() {
        return zzb(Uri.EMPTY, new HashMap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:211:0x031a, code lost:
        if (r9 == r3) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x031c, code lost:
        zzc(r9, r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01a3 A[Catch: all -> 0x033e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0016, B:8:0x001d, B:113:0x01a3, B:114:0x01a6, B:212:0x031c, B:213:0x031f, B:215:0x0324, B:218:0x032a, B:219:0x032d, B:220:0x0330, B:117:0x01af, B:119:0x01b7, B:122:0x01c1, B:125:0x01cc, B:127:0x01d4, B:130:0x01de, B:133:0x01e9, B:136:0x01f4, B:139:0x01ff, B:141:0x0207, B:143:0x020f, B:146:0x0219, B:148:0x0227, B:151:0x0231, B:154:0x023c, B:156:0x0244, B:158:0x0252, B:160:0x0260, B:163:0x0270, B:165:0x027e, B:168:0x0288, B:170:0x0290, B:172:0x0298, B:174:0x02a0, B:177:0x02a9, B:179:0x02b1, B:182:0x02c0, B:184:0x02c8, B:187:0x02d1, B:189:0x02d9, B:192:0x02e2, B:194:0x02ea, B:197:0x02f3, B:12:0x003f, B:13:0x0047, B:94:0x0174, B:15:0x004c, B:18:0x0058, B:21:0x0064, B:24:0x0070, B:27:0x007c, B:30:0x0087, B:33:0x0092, B:36:0x009d, B:39:0x00a9, B:42:0x00b5, B:45:0x00c1, B:48:0x00cd, B:51:0x00d8, B:54:0x00e3, B:57:0x00ee, B:60:0x00fa, B:63:0x0106, B:66:0x0112, B:69:0x011d, B:72:0x0128, B:75:0x0133, B:78:0x013e, B:81:0x0148, B:84:0x0153, B:87:0x015e, B:90:0x0169), top: B:226:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01af A[Catch: all -> 0x033e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0016, B:8:0x001d, B:113:0x01a3, B:114:0x01a6, B:212:0x031c, B:213:0x031f, B:215:0x0324, B:218:0x032a, B:219:0x032d, B:220:0x0330, B:117:0x01af, B:119:0x01b7, B:122:0x01c1, B:125:0x01cc, B:127:0x01d4, B:130:0x01de, B:133:0x01e9, B:136:0x01f4, B:139:0x01ff, B:141:0x0207, B:143:0x020f, B:146:0x0219, B:148:0x0227, B:151:0x0231, B:154:0x023c, B:156:0x0244, B:158:0x0252, B:160:0x0260, B:163:0x0270, B:165:0x027e, B:168:0x0288, B:170:0x0290, B:172:0x0298, B:174:0x02a0, B:177:0x02a9, B:179:0x02b1, B:182:0x02c0, B:184:0x02c8, B:187:0x02d1, B:189:0x02d9, B:192:0x02e2, B:194:0x02ea, B:197:0x02f3, B:12:0x003f, B:13:0x0047, B:94:0x0174, B:15:0x004c, B:18:0x0058, B:21:0x0064, B:24:0x0070, B:27:0x007c, B:30:0x0087, B:33:0x0092, B:36:0x009d, B:39:0x00a9, B:42:0x00b5, B:45:0x00c1, B:48:0x00cd, B:51:0x00d8, B:54:0x00e3, B:57:0x00ee, B:60:0x00fa, B:63:0x0106, B:66:0x0112, B:69:0x011d, B:72:0x0128, B:75:0x0133, B:78:0x013e, B:81:0x0148, B:84:0x0153, B:87:0x015e, B:90:0x0169), top: B:226:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0324 A[Catch: all -> 0x033e, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0016, B:8:0x001d, B:113:0x01a3, B:114:0x01a6, B:212:0x031c, B:213:0x031f, B:215:0x0324, B:218:0x032a, B:219:0x032d, B:220:0x0330, B:117:0x01af, B:119:0x01b7, B:122:0x01c1, B:125:0x01cc, B:127:0x01d4, B:130:0x01de, B:133:0x01e9, B:136:0x01f4, B:139:0x01ff, B:141:0x0207, B:143:0x020f, B:146:0x0219, B:148:0x0227, B:151:0x0231, B:154:0x023c, B:156:0x0244, B:158:0x0252, B:160:0x0260, B:163:0x0270, B:165:0x027e, B:168:0x0288, B:170:0x0290, B:172:0x0298, B:174:0x02a0, B:177:0x02a9, B:179:0x02b1, B:182:0x02c0, B:184:0x02c8, B:187:0x02d1, B:189:0x02d9, B:192:0x02e2, B:194:0x02ea, B:197:0x02f3, B:12:0x003f, B:13:0x0047, B:94:0x0174, B:15:0x004c, B:18:0x0058, B:21:0x0064, B:24:0x0070, B:27:0x007c, B:30:0x0087, B:33:0x0092, B:36:0x009d, B:39:0x00a9, B:42:0x00b5, B:45:0x00c1, B:48:0x00cd, B:51:0x00d8, B:54:0x00e3, B:57:0x00ee, B:60:0x00fa, B:63:0x0106, B:66:0x0112, B:69:0x011d, B:72:0x0128, B:75:0x0133, B:78:0x013e, B:81:0x0148, B:84:0x0153, B:87:0x015e, B:90:0x0169), top: B:226:0x0001 }] */
    @Override // com.google.android.gms.internal.ads.zzzm
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized com.google.android.gms.internal.ads.zzzf[] zzb(android.net.Uri r21, java.util.Map r22) {
        /*
            Method dump skipped, instructions count: 996
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzza.zzb(android.net.Uri, java.util.Map):com.google.android.gms.internal.ads.zzzf[]");
    }
}
