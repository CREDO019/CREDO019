package com.google.android.gms.internal.ads;

import android.util.Base64;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaas {
    public static int zza(int r1) {
        int r0 = 0;
        while (r1 > 0) {
            r0++;
            r1 >>>= 1;
        }
        return r0;
    }

    public static zzbq zzb(List list) {
        ArrayList arrayList = new ArrayList();
        for (int r2 = 0; r2 < list.size(); r2++) {
            String str = (String) list.get(r2);
            String[] zzah = zzel.zzah(str, "=");
            if (zzah.length != 2) {
                Log.w("VorbisUtil", "Failed to parse Vorbis comment: ".concat(String.valueOf(str)));
            } else if (zzah[0].equals("METADATA_BLOCK_PICTURE")) {
                try {
                    arrayList.add(zzacf.zzb(new zzed(Base64.decode(zzah[1], 0))));
                } catch (RuntimeException e) {
                    zzdu.zzb("VorbisUtil", "Failed to parse vorbis picture", e);
                }
            } else {
                arrayList.add(new zzadv(zzah[0], zzah[1]));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new zzbq(arrayList);
    }

    public static zzaap zzc(zzed zzedVar, boolean z, boolean z2) throws zzbu {
        if (z) {
            zzd(3, zzedVar, false);
        }
        String zzx = zzedVar.zzx((int) zzedVar.zzq(), zzfrs.zzc);
        long zzq = zzedVar.zzq();
        String[] strArr = new String[(int) zzq];
        int length = zzx.length() + 15;
        for (int r0 = 0; r0 < zzq; r0++) {
            String zzx2 = zzedVar.zzx((int) zzedVar.zzq(), zzfrs.zzc);
            strArr[r0] = zzx2;
            length = length + 4 + zzx2.length();
        }
        if (z2 && (zzedVar.zzk() & 1) == 0) {
            throw zzbu.zza("framing bit expected to be set", null);
        }
        return new zzaap(zzx, strArr, length + 1);
    }

    public static boolean zzd(int r4, zzed zzedVar, boolean z) throws zzbu {
        if (zzedVar.zza() < 7) {
            if (z) {
                return false;
            }
            int zza = zzedVar.zza();
            throw zzbu.zza("too short header: " + zza, null);
        } else if (zzedVar.zzk() != r4) {
            if (z) {
                return false;
            }
            throw zzbu.zza("expected header type ".concat(String.valueOf(Integer.toHexString(r4))), null);
        } else if (zzedVar.zzk() == 118 && zzedVar.zzk() == 111 && zzedVar.zzk() == 114 && zzedVar.zzk() == 98 && zzedVar.zzk() == 105 && zzedVar.zzk() == 115) {
            return true;
        } else {
            if (z) {
                return false;
            }
            throw zzbu.zza("expected characters 'vorbis'", null);
        }
    }
}
