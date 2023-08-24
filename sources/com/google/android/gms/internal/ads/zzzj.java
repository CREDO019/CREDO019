package com.google.android.gms.internal.ads;

import java.io.EOFException;
import java.io.IOException;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzj {
    public static int zza(zzzg zzzgVar, byte[] bArr, int r5, int r6) throws IOException {
        int r0 = 0;
        while (r0 < r6) {
            int zzb = zzzgVar.zzb(bArr, r5 + r0, r6 - r0);
            if (zzb == -1) {
                break;
            }
            r0 += zzb;
        }
        return r0;
    }

    @Pure
    public static void zzb(boolean z, String str) throws zzbu {
        if (!z) {
            throw zzbu.zza(str, null);
        }
    }

    public static boolean zzc(zzzg zzzgVar, byte[] bArr, int r2, int r3, boolean z) throws IOException {
        try {
            return zzzgVar.zzm(bArr, 0, r3, z);
        } catch (EOFException e) {
            if (z) {
                return false;
            }
            throw e;
        }
    }

    public static boolean zzd(zzzg zzzgVar, byte[] bArr, int r3, int r4) throws IOException {
        try {
            ((zzyv) zzzgVar).zzn(bArr, r3, r4, false);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    public static boolean zze(zzzg zzzgVar, int r2) throws IOException {
        try {
            ((zzyv) zzzgVar).zzo(r2, false);
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }
}
