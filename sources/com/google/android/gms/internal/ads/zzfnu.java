package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.common.util.Hex;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfnu {
    final File zza;
    private final File zzb;
    private final SharedPreferences zzc;
    private final int zzd;

    public zzfnu(Context context, int r4) {
        this.zzc = context.getSharedPreferences("pcvmspf", 0);
        File dir = context.getDir("pccache", 0);
        zzfnv.zza(dir, false);
        this.zzb = dir;
        File dir2 = context.getDir("tmppccache", 0);
        zzfnv.zza(dir2, true);
        this.zza = dir2;
        this.zzd = r4;
    }

    static String zza(zzaru zzaruVar) {
        return Hex.bytesToStringLowercase(zzaruVar.zzas().zzE());
    }

    private final File zze() {
        File file = new File(this.zzb, Integer.toString(this.zzd - 1));
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    private final String zzf() {
        int r0 = this.zzd;
        StringBuilder sb = new StringBuilder();
        sb.append("FBAMTD");
        sb.append(r0 - 1);
        return sb.toString();
    }

    private final String zzg() {
        int r0 = this.zzd;
        StringBuilder sb = new StringBuilder();
        sb.append("LATMTD");
        sb.append(r0 - 1);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0162  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzb(com.google.android.gms.internal.ads.zzarr r8, com.google.android.gms.internal.ads.zzfoa r9) {
        /*
            Method dump skipped, instructions count: 382
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfnu.zzb(com.google.android.gms.internal.ads.zzarr, com.google.android.gms.internal.ads.zzfoa):boolean");
    }

    final zzaru zzc(int r6) {
        String string;
        if (r6 == 1) {
            string = this.zzc.getString(zzg(), null);
        } else {
            string = this.zzc.getString(zzf(), null);
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            zzaru zzh = zzaru.zzh(zzgnf.zzv(Hex.stringToBytes(string)));
            String zzk = zzh.zzk();
            File zzb = zzfnv.zzb(zzk, "pcam.jar", zze());
            if (!zzb.exists()) {
                zzb = zzfnv.zzb(zzk, "pcam", zze());
            }
            File zzb2 = zzfnv.zzb(zzk, "pcbc", zze());
            if (zzb.exists()) {
                if (zzb2.exists()) {
                    return zzh;
                }
            }
        } catch (zzgoz unused) {
        }
        return null;
    }

    public final zzfnt zzd(int r6) {
        zzaru zzc = zzc(1);
        if (zzc == null) {
            return null;
        }
        String zzk = zzc.zzk();
        File zzb = zzfnv.zzb(zzk, "pcam.jar", zze());
        if (!zzb.exists()) {
            zzb = zzfnv.zzb(zzk, "pcam", zze());
        }
        return new zzfnt(zzc, zzb, zzfnv.zzb(zzk, "pcbc", zze()), zzfnv.zzb(zzk, "pcopt", zze()));
    }
}
