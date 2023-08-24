package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzu {
    private static final Pattern zzc = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int zza = -1;
    public int zzb = -1;

    private final boolean zzc(String str) {
        Matcher matcher = zzc.matcher(str);
        if (matcher.find()) {
            try {
                String group = matcher.group(1);
                int r2 = zzel.zza;
                int parseInt = Integer.parseInt(group, 16);
                int parseInt2 = Integer.parseInt(matcher.group(2), 16);
                if (parseInt > 0 || parseInt2 > 0) {
                    this.zza = parseInt;
                    this.zzb = parseInt2;
                    return true;
                }
                return false;
            } catch (NumberFormatException unused) {
                return false;
            }
        }
        return false;
    }

    public final boolean zza() {
        return (this.zza == -1 || this.zzb == -1) ? false : true;
    }

    public final boolean zzb(zzbq zzbqVar) {
        for (int r1 = 0; r1 < zzbqVar.zza(); r1++) {
            zzbp zzb = zzbqVar.zzb(r1);
            if (zzb instanceof zzact) {
                zzact zzactVar = (zzact) zzb;
                if ("iTunSMPB".equals(zzactVar.zzb) && zzc(zzactVar.zzc)) {
                    return true;
                }
            } else if (zzb instanceof zzadc) {
                zzadc zzadcVar = (zzadc) zzb;
                if ("com.apple.iTunes".equals(zzadcVar.zza) && "iTunSMPB".equals(zzadcVar.zzb) && zzc(zzadcVar.zzc)) {
                    return true;
                }
            } else {
                continue;
            }
        }
        return false;
    }
}
