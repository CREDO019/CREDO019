package com.google.android.gms.internal.ads;

import android.support.p001v4.media.session.PlaybackStateCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzanq {
    static boolean zza = false;
    private static MessageDigest zzc;
    private static final Object zzd = new Object();
    private static final Object zze = new Object();
    static final CountDownLatch zzb = new CountDownLatch(1);

    public static String zza(zzamx zzamxVar, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] zzg;
        byte[] zzaw = zzamxVar.zzaw();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcu)).booleanValue()) {
            Vector zzb2 = zzb(zzaw, 255);
            if (zzb2 == null || zzb2.size() == 0) {
                zzg = zzg(zzf(4096).zzaw(), str, true);
            } else {
                zzanj zza2 = zzank.zza();
                int size = zzb2.size();
                for (int r5 = 0; r5 < size; r5++) {
                    zza2.zza(zzgnf.zzv(zzg((byte[]) zzb2.get(r5), str, false)));
                }
                zza2.zzb(zzgnf.zzv(zze(zzaw)));
                zzg = ((zzank) zza2.zzal()).zzaw();
            }
        } else if (zzaqc.zza == null) {
            throw new GeneralSecurityException();
        } else {
            byte[] zza3 = zzaqc.zza.zza(zzaw, str != null ? str.getBytes() : new byte[0]);
            zzanj zza4 = zzank.zza();
            zza4.zza(zzgnf.zzv(zza3));
            zza4.zzc(3);
            zzg = ((zzank) zza4.zzal()).zzaw();
        }
        return zzanm.zza(zzg, true);
    }

    static Vector zzb(byte[] bArr, int r8) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        int r0 = (length + 254) / 255;
        Vector vector = new Vector();
        for (int r3 = 0; r3 < r0; r3++) {
            int r4 = r3 * 255;
            try {
                int length2 = bArr.length;
                if (length2 - r4 > 255) {
                    length2 = r4 + 255;
                }
                vector.add(Arrays.copyOfRange(bArr, r4, length2));
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    public static void zzd() {
        synchronized (zze) {
            if (!zza) {
                zza = true;
                new Thread(new zzanp(null)).start();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x001e, code lost:
        r1.reset();
        r1.update(r6);
        r6 = com.google.android.gms.internal.ads.zzanq.zzc.digest();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] zze(byte[] r6) throws java.security.NoSuchAlgorithmException {
        /*
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzanq.zzd
            monitor-enter(r0)
            zzd()     // Catch: java.lang.Throwable -> L34
            r1 = 0
            java.util.concurrent.CountDownLatch r2 = com.google.android.gms.internal.ads.zzanq.zzb     // Catch: java.lang.InterruptedException -> L1b java.lang.Throwable -> L34
            r3 = 2
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.lang.InterruptedException -> L1b java.lang.Throwable -> L34
            boolean r2 = r2.await(r3, r5)     // Catch: java.lang.InterruptedException -> L1b java.lang.Throwable -> L34
            if (r2 != 0) goto L14
            goto L1c
        L14:
            java.security.MessageDigest r2 = com.google.android.gms.internal.ads.zzanq.zzc     // Catch: java.lang.Throwable -> L34
            if (r2 != 0) goto L19
            goto L1c
        L19:
            r1 = r2
            goto L1c
        L1b:
        L1c:
            if (r1 == 0) goto L2c
            r1.reset()     // Catch: java.lang.Throwable -> L34
            r1.update(r6)     // Catch: java.lang.Throwable -> L34
            java.security.MessageDigest r6 = com.google.android.gms.internal.ads.zzanq.zzc     // Catch: java.lang.Throwable -> L34
            byte[] r6 = r6.digest()     // Catch: java.lang.Throwable -> L34
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L34
            return r6
        L2c:
            java.security.NoSuchAlgorithmException r6 = new java.security.NoSuchAlgorithmException     // Catch: java.lang.Throwable -> L34
            java.lang.String r1 = "Cannot compute hash"
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L34
            throw r6     // Catch: java.lang.Throwable -> L34
        L34:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L34
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanq.zze(byte[]):byte[]");
    }

    static zzamx zzf(int r2) {
        zzamh zza2 = zzamx.zza();
        zza2.zzC(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        return (zzamx) zza2.zzal();
    }

    private static byte[] zzg(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] array;
        int r1 = true != z ? 255 : 239;
        if (bArr.length > r1) {
            bArr = zzf(4096).zzaw();
        }
        int length = bArr.length;
        if (length < r1) {
            byte[] bArr2 = new byte[r1 - length];
            new SecureRandom().nextBytes(bArr2);
            array = ByteBuffer.allocate(r1 + 1).put((byte) length).put(bArr).put(bArr2).array();
        } else {
            array = ByteBuffer.allocate(r1 + 1).put((byte) length).put(bArr).array();
        }
        if (z) {
            array = ByteBuffer.allocate(256).put(zze(array)).put(array).array();
        }
        byte[] bArr3 = new byte[256];
        zzanr[] zzanrVarArr = new zzaoq().zzcG;
        int length2 = zzanrVarArr.length;
        for (int r2 = 0; r2 < 12; r2++) {
            zzanrVarArr[r2].zza(array, bArr3);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzgmm(str.getBytes("UTF-8")).zza(bArr3);
        }
        return bArr3;
    }
}
