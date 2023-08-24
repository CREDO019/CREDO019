package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.primitives.SignedBytes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzagw implements zzaik {
    private final List zza;

    public zzagw() {
        this(0);
    }

    private final zzaia zzb(zzaij zzaijVar) {
        return new zzaia(zzd(zzaijVar));
    }

    private final zzaio zzc(zzaij zzaijVar) {
        return new zzaio(zzd(zzaijVar));
    }

    private final List zzd(zzaij zzaijVar) {
        String str;
        int r6;
        List list;
        zzed zzedVar = new zzed(zzaijVar.zzd);
        List list2 = this.zza;
        while (zzedVar.zza() > 0) {
            int zzk = zzedVar.zzk();
            int zzc = zzedVar.zzc() + zzedVar.zzk();
            if (zzk == 134) {
                list2 = new ArrayList();
                int zzk2 = zzedVar.zzk() & 31;
                for (int r4 = 0; r4 < zzk2; r4++) {
                    String zzx = zzedVar.zzx(3, zzfrs.zzc);
                    int zzk3 = zzedVar.zzk();
                    int r7 = zzk3 & 128;
                    if (r7 != 0) {
                        r6 = zzk3 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        str = MimeTypes.APPLICATION_CEA608;
                        r6 = 1;
                    }
                    byte zzk4 = (byte) zzedVar.zzk();
                    zzedVar.zzG(1);
                    if (r7 != 0) {
                        int r72 = zzdf.zza;
                        list = Collections.singletonList((zzk4 & SignedBytes.MAX_POWER_OF_TWO) != 0 ? new byte[]{1} : new byte[]{0});
                    } else {
                        list = null;
                    }
                    zzad zzadVar = new zzad();
                    zzadVar.zzS(str);
                    zzadVar.zzK(zzx);
                    zzadVar.zzu(r6);
                    zzadVar.zzI(list);
                    list2.add(zzadVar.zzY());
                }
            }
            zzedVar.zzF(zzc);
        }
        return list2;
    }

    @Override // com.google.android.gms.internal.ads.zzaik
    public final zzaim zza(int r3, zzaij zzaijVar) {
        if (r3 != 2) {
            if (r3 == 3 || r3 == 4) {
                return new zzahq(new zzahn(zzaijVar.zzb));
            }
            if (r3 == 21) {
                return new zzahq(new zzahl());
            }
            if (r3 == 27) {
                return new zzahq(new zzahi(zzb(zzaijVar), false, false));
            }
            if (r3 == 36) {
                return new zzahq(new zzahk(zzb(zzaijVar)));
            }
            if (r3 == 89) {
                return new zzahq(new zzagy(zzaijVar.zzc));
            }
            if (r3 == 138) {
                return new zzahq(new zzagx(zzaijVar.zzb));
            }
            if (r3 == 172) {
                return new zzahq(new zzags(zzaijVar.zzb));
            }
            if (r3 == 257) {
                return new zzahz(new zzahp(MimeTypes.APPLICATION_AIT));
            }
            if (r3 != 128) {
                if (r3 != 129) {
                    if (r3 == 134) {
                        return new zzahz(new zzahp(MimeTypes.APPLICATION_SCTE35));
                    }
                    if (r3 != 135) {
                        switch (r3) {
                            case 15:
                                return new zzahq(new zzagv(false, zzaijVar.zzb));
                            case 16:
                                return new zzahq(new zzahe(zzc(zzaijVar)));
                            case 17:
                                return new zzahq(new zzahm(zzaijVar.zzb));
                            default:
                                return null;
                        }
                    }
                }
                return new zzahq(new zzagp(zzaijVar.zzb));
            }
        }
        return new zzahq(new zzahb(zzc(zzaijVar)));
    }

    public zzagw(int r1) {
        this.zza = zzfuv.zzo();
    }
}
